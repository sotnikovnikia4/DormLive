package ru.dormlive.backend.controller;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.dormlive.backend.dto.AdCreationDTO;
import ru.dormlive.backend.dto.AdDTO;
import ru.dormlive.backend.dto.AdEditDTO;
import ru.dormlive.backend.service.AdService;
import ru.dormlive.backend.util.ErrorMethods;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ad")
public class AdvertisementController {
    private final AdService adService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public AdDTO create(@Valid @RequestBody AdCreationDTO adCreationDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new ValidationException(ErrorMethods.formErrorMessage(bindingResult));
        }

        return adService.create(adCreationDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/delete")
    public void delete(@RequestParam("id") String id) {
        adService.delete(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/update")
    public AdDTO update(@Valid @RequestBody AdEditDTO adEditDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new ValidationException(ErrorMethods.formErrorMessage(bindingResult));
        }

        return adService.update(adEditDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/get")
    public AdDTO get(@RequestParam("id") String id) {
        return adService.getById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/set-actual")
    public AdDTO setActual(@RequestParam("id") String id) {

        return adService.setActual(id, true);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/unset-actual")
    public AdDTO unsetActual(@RequestParam("id") String id) {

        return adService.setActual(id, false);
    }

    //получение всех объявлений конкретной общаги

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/get-all")
    public List<AdDTO> getAll(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "countPerPage", required = false) Integer countPerPage,
            @RequestParam(value = "category", required = false) String category
    ) {
        if(page == null && countPerPage == null) {
            return adService.getAll(category);
        }
        else if(page != null && countPerPage != null) {
            return adService.getAll(page, countPerPage, category);
        }
        else{
            throw new ValidationException("page and countPerPage should be both null or not null");
        }



    }
}
