package com.JSKP.KP.service;

import com.JSKP.KP.exception.NotFoundException;
import com.JSKP.KP.model.Card;
import com.JSKP.KP.repo.Card_repo;
import org.springframework.stereotype.Service;


@Service
public class Card_service {
    private final Card_repo card_repo;

    public Card_service(Card_repo card_repo) {
        this.card_repo = card_repo;
    }

    public Iterable<Card> getCards(){
        return card_repo.findAll();
    }

    public Card isExists(String number){
        return card_repo.findCardByNumber(number);
    }
    public Card isExists(Card card){
        return card_repo.findCardByNumber(card.getNumber());
    }

    public Card getCard(String number){
        Card exists = isExists(number);
        if(exists==null) throw new NotFoundException();
        return exists;
    }

    public Card addCard(Card card){
        return card_repo.save(card);
    }

    public Card updateCard(Card card){
        Card exists = isExists(card);
        if(exists!=null){
            return card_repo.save(card);
        }
        else throw new NotFoundException();

    }

    public void deleteCard(Card card){
        card_repo.delete(card);
    }


}
