package com.JSKP.KP.controller;

import com.JSKP.KP.exception.NotFoundException;
import com.JSKP.KP.model.Card;
import com.JSKP.KP.service.CardService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {
    private final CardService card_service;

    public AuthController(CardService card_service) {
        this.card_service = card_service;
    }

    @PostMapping("{number}")
    public Card getAuthCards(@PathVariable("number") Card cardFromDb, @RequestBody Card card){
        if(cardFromDb==null){
            throw new NotFoundException();
        }
        return card_service.authCorrect(cardFromDb,card.getNumber(),card.getPassword());
    }
}
