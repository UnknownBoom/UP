package com.JSKP.KP.controller;

import com.JSKP.KP.exception.BadRequest;
import com.JSKP.KP.exception.NotEnoughMoney;
import com.JSKP.KP.exception.NotFoundException;
import com.JSKP.KP.model.Card;
import com.JSKP.KP.model.MyBigDecimal;
import com.JSKP.KP.repo.Card_repo;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("operation")
public class CardOperationController {
    private final Card_repo card_repo;

    public CardOperationController(Card_repo card_repo) {
        this.card_repo = card_repo;
    }

    public Boolean isEnoughMoney(Card card, BigDecimal sumRemove){
        return card.getMoney().compareTo(sumRemove) != -1;
    }
    public Boolean isEnoughMoney(Card card, Card card1){
        return card.getMoney().compareTo(card1.getMoney()) != -1;
    }


    @PutMapping("add/{number}")
    public Card addMoney(@PathVariable("number") Card cardFromDb,@RequestBody MyBigDecimal addSum){
        if(cardFromDb==null) throw new NotFoundException();
        if(addSum.getDecimal().compareTo(new BigDecimal(0))<=0) throw new BadRequest();
        cardFromDb.setMoney(cardFromDb.getMoney().add(addSum.getDecimal()));
        return card_repo.save(cardFromDb);
    }

    @PutMapping("remove/{number}")
    public Card removeMoney(@PathVariable("number") Card cardFromDb, @RequestBody MyBigDecimal sumRemove){
        if(cardFromDb==null) throw new NotFoundException();
        if(sumRemove.getDecimal().compareTo(new BigDecimal(0))<=0) throw new BadRequest();
        if(!isEnoughMoney(cardFromDb,sumRemove.getDecimal())) throw new NotEnoughMoney();
        cardFromDb.setMoney(cardFromDb.getMoney().subtract(sumRemove.getDecimal()));
        return card_repo.save(cardFromDb);
    }
    @PutMapping("transfer/{FromNumber}/{ToNumber}")
    public Card transferMoney(@PathVariable("FromNumber") Card cardFromDbFrom,
                            @PathVariable("ToNumber") Card cardFromDbTo,
                            @RequestBody MyBigDecimal TransferSum){
        if(cardFromDbFrom ==null || cardFromDbTo==null) throw new NotFoundException();
        if(!isEnoughMoney(cardFromDbFrom,cardFromDbTo)) throw new NotEnoughMoney();
        if(TransferSum.getDecimal().compareTo(new BigDecimal(0))<=0) throw new BadRequest();
        cardFromDbFrom.setMoney(cardFromDbFrom.getMoney().subtract(TransferSum.getDecimal()));
        cardFromDbTo.setMoney(cardFromDbTo.getMoney().add(TransferSum.getDecimal()));
        card_repo.save(cardFromDbTo);
        return card_repo.save(cardFromDbFrom);
    }
}
