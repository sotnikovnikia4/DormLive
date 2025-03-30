package ru.dormlive.backend.service;

import com.querydsl.core.types.dsl.BooleanExpression;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.dormlive.backend.dto.AdDTO;
import ru.dormlive.backend.dto.RateDTO;
import ru.dormlive.backend.dto.UserDTO;
import ru.dormlive.backend.model.Advertisement;
import ru.dormlive.backend.model.QUser;
import ru.dormlive.backend.model.User;
import ru.dormlive.backend.repository.AdvertisementRepository;
import ru.dormlive.backend.repository.UserRepository;
import ru.dormlive.backend.security.UserDetailsImpl;
import ru.dormlive.backend.util.Converter;
import ru.dormlive.backend.util.exception.NotFoundException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class RatingService {
    private final AdvertisementRepository adRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    public AdDTO likeAd(String adId) {
        User user = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();

        if(!ObjectId.isValid(adId)){
            throw new ValidationException("Id is invalid");
        }

        Optional<Advertisement> ad = adRepository.findById(new ObjectId(adId));
        if(ad.isEmpty()){
            throw new NotFoundException("Ad does not exists");
        }

        if(Objects.equals(ad.get().getAuthor(), user.getNickname())){
            throw new ValidationException("Самолайк - это плохо!");
        }
        else if(ad.get().getUsersWithLikes().contains(user.getNickname())){
            throw new ValidationException("User has already liked this ad");
        }

        ad.get().getUsersWithLikes().add(user.getNickname());
        adRepository.save(ad.get());

        return Converter.convertToAdDTO(ad.get());
    }

    public List<RateDTO> getRating(int count) {
        User user = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();

        BooleanExpression expression = QUser.user.dorm.eq(user.getId());

        return userRepository.findAll(expression, PageRequest.of(0, count, Sort.by("coins"))).getContent().stream().map(Converter::convertToRateDTO).toList();
    }

    public UserDTO addCoins(String nickname, int coins) {
        Optional<User> user = userService.findUserByNickname(nickname);
        if(user.isEmpty()){
            throw new NotFoundException("User not found");
        }

        user.get().setCoins(user.get().getCoins() + coins);
        userRepository.save(user.get());

        return Converter.convertToUserDTO(user.get());
    }
}
