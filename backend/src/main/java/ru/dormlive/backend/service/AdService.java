package ru.dormlive.backend.service;

import com.querydsl.core.types.dsl.BooleanExpression;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.dormlive.backend.dto.AdCreationDTO;
import ru.dormlive.backend.dto.AdDTO;
import ru.dormlive.backend.dto.AdEditDTO;
import ru.dormlive.backend.dto.UserDTO;
import ru.dormlive.backend.util.Converter;
import ru.dormlive.backend.util.exception.NotFoundException;
import ru.dormlive.backend.model.Advertisement;
import ru.dormlive.backend.model.QAdvertisement;
import ru.dormlive.backend.model.User;
import ru.dormlive.backend.repository.AdvertisementRepository;
import ru.dormlive.backend.repository.UserRepository;
import ru.dormlive.backend.security.UserDetailsImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdService {
    private final AdvertisementRepository adRepository;
    private final UserRepository userRepository;

    @PreAuthorize("isAuthenticated()")
    public AdDTO create(AdCreationDTO adCreationDTO) {
        User user = ((UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();

        Advertisement ad = Advertisement.builder()
                .title(adCreationDTO.getTitle())
                .description(adCreationDTO.getDescription())
                .category(adCreationDTO.getCategory())
                .publishedAt(LocalDateTime.now())
                .author(user.getNickname())
                .dorm(user.getDorm())
                .actual(true)
                .place(adCreationDTO.getPlace()).build();

        ad = adRepository.save(ad);

        return Converter.convertToAdDTO(ad);
    }

    @PreAuthorize("isAuthenticated()")
    public void delete(String id) {
        if(!ObjectId.isValid(id)){
            throw new ValidationException("Id is invalid");
        }

        Optional<Advertisement> ad = adRepository.findById(new ObjectId(id));
        if(ad.isEmpty()){
            throw new NotFoundException("Ad does not exists");
        }

        User user = ((UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        if(!ad.get().getAuthor().equals(user.getNickname())){
            throw new ValidationException("User did not create this ad");
        }

        adRepository.delete(ad.get());
    }

    @PreAuthorize("isAuthenticated()")
    public AdDTO update(AdEditDTO adDTO) {
        if(!ObjectId.isValid(adDTO.getId())){
            throw new ValidationException("Id is invalid");
        }

        Optional<Advertisement> ad = adRepository.findById(new ObjectId(adDTO.getId()));
        if(ad.isEmpty()){
            throw new ValidationException("Ad with id does not exists");
        }

        User user = ((UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        if(!ad.get().getAuthor().equals(user.getNickname())){
            throw new ValidationException("User did not create this ad");
        }

        ad.get().setTitle(adDTO.getTitle());
        ad.get().setDescription(adDTO.getDescription());
        ad.get().setCategory(adDTO.getCategory());
        ad.get().setPlace(adDTO.getPlace());

        ad = Optional.of(adRepository.save(ad.get()));

        return Converter.convertToAdDTO(ad.get());
    }

    public AdDTO getById(String id) {
        if(!ObjectId.isValid(id)){
            throw new ValidationException("Id is invalid");
        }

        Optional<Advertisement> ad = adRepository.findById(new ObjectId(id));

        if(ad.isEmpty()){
            throw new NotFoundException("Ad with id does not exists");
        }

        return Converter.convertToAdDTO(ad.get());
    }

    @PreAuthorize("isAuthenticated()")
    public AdDTO setActual(String id, boolean actual) {
        if(!ObjectId.isValid(id)){
            throw new ValidationException("Id is invalid");
        }

        Optional<Advertisement> ad = adRepository.findById(new ObjectId(id));

        if(ad.isEmpty()){
            throw new NotFoundException("Ad with id does not exists");
        }

        ad.get().setActual(actual);
        return Converter.convertToAdDTO(adRepository.save(ad.get()));
    }

    @PreAuthorize("isAuthenticated()")
    public List<AdDTO> getAll(String category) {
        BooleanExpression expression = getPredicateWithCategoryAndDorm(category);

        List<Advertisement> list = new ArrayList<>();
        adRepository.findAll(expression).forEach(list::add);

        return list.stream().map(Converter::convertToAdDTO).toList();
    }

    @PreAuthorize("isAuthenticated()")
    public List<AdDTO> getAll(Integer page, Integer countPerPage, String category) {
        BooleanExpression expression = getPredicateWithCategoryAndDorm(category);

        List<Advertisement> list = new ArrayList<>();
        page--;
        adRepository.findAll(expression, PageRequest.of(page, countPerPage)).forEach(list::add);

        return list.stream().map(Converter::convertToAdDTO).toList();
    }

    private BooleanExpression getPredicateWithCategoryAndDorm(String category) {
        User user = ((UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();

        BooleanExpression expression = QAdvertisement.advertisement.dorm.eq(user.getDorm()).and(QAdvertisement.advertisement.actual);

        if(category != null){
            expression = expression.and(QAdvertisement.advertisement.category.equalsIgnoreCase(category));
        }

        return expression;
    }
}
