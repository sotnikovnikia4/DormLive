package ru.dormlive.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.dormlive.backend.dto.AdDTO;
import ru.dormlive.backend.dto.RateDTO;
import ru.dormlive.backend.dto.UserDTO;
import ru.dormlive.backend.service.RatingService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rating")
public class RatingController {
    private final RatingService ratingService;

    @PutMapping("/like-ad")
    public AdDTO likeAd(@RequestParam String adId){
        return ratingService.likeAd(adId);
    }

    @GetMapping("/get")
    public List<RateDTO> getRating(@RequestParam(required = false, defaultValue = "50") int count){
        return ratingService.getRating(count);
    }

    @PutMapping("/add-coins")
    public UserDTO addCoins(@RequestParam String nickname, @RequestParam int coins){
        return ratingService.addCoins(nickname, coins);
    }
}
