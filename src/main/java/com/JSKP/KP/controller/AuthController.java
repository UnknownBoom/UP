package com.JSKP.KP.controller;

import com.JSKP.KP.exception.BadRequest;
import com.JSKP.KP.exception.NotFoundException;
import com.JSKP.KP.model.Card;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {
    @PostMapping("{number}")
    public Card getAuthCards(@PathVariable("number") Card cardFromDb, @RequestBody Card card){
        if(cardFromDb==null){
            throw new NotFoundException();
        }
        if(cardFromDb.getNumber().equals(card.getNumber()) && cardFromDb.getPassword().equals(card.getPassword())){
            return cardFromDb;
        }
        else{
            throw new BadRequest();
        }

    }
}
