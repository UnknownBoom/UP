package com.JSKP.KP.controller;

import com.JSKP.KP.exception.BadRequest;
import com.JSKP.KP.exception.NotEnoughMoney;
import com.JSKP.KP.exception.NotFoundException;
import com.JSKP.KP.model.Card;
import com.JSKP.KP.model.MyBigDecimal;
import com.JSKP.KP.repo.CardRepo;
import com.JSKP.KP.service.CardService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("operation")
public class CardOperationController {
    private final CardRepo card_repo;
    
    private final CardService card_service;

    public CardOperationController(CardRepo card_repo, CardService card_service) {
        this.card_repo = card_repo;
        this.card_service = card_service;
    }
    

    @PutMapping("add/{number}")
    public Card addMoney(@PathVariable("number") Card cardFromDb,@RequestBody MyBigDecimal addSum){
        if(cardFromDb==null) throw new NotFoundException();
        return card_service.addMoney(cardFromDb,addSum.getDecimal());
    }

    @PutMapping("remove/{number}")
    public Card removeMoney(@PathVariable("number") Card cardFromDb, @RequestBody MyBigDecimal removeSum){
        if(cardFromDb==null) throw new NotFoundException();
       return card_service.removeMoney(cardFromDb,removeSum.getDecimal());
    }

    @PutMapping("transfer/{FromNumber}/{ToNumber}")
    public Card transferMoney(@PathVariable("FromNumber") Card cardFromDbFrom,
                            @PathVariable("ToNumber") Card cardFromDbTo,
                            @RequestBody MyBigDecimal transferSum){
        if(cardFromDbFrom ==null || cardFromDbTo==null) throw new NotFoundException();
        return card_service.transferMoney(cardFromDbFrom,cardFromDbTo,transferSum.getDecimal());
    }
}
