package com.JSKP.KP.service;

import com.JSKP.KP.exception.BadRequest;
import com.JSKP.KP.exception.NotEnoughMoney;
import com.JSKP.KP.exception.NotFoundException;
import com.JSKP.KP.model.Card;
import com.JSKP.KP.repo.CardRepo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public class CardService {
    private final CardRepo card_repo;

    public CardService(CardRepo card_repo) {
        this.card_repo = card_repo;
    }

    public Iterable<Card> getCards(){
        return card_repo.findAll();
    }

    public Card authCorrect(Card card,String number,String password){
        if(card.getNumber().equals(card.getNumber()) && card.getPassword().equals(card.getPassword())){
            return card;
        }else{
            throw new BadRequest();
        }

    }
    public Boolean isEnoughMoney(Card card, BigDecimal sumRemove){
        if(card.getMoney().compareTo(sumRemove) != -1) return true;
        else throw new NotEnoughMoney();
    }

    public Boolean isCorrectSum(BigDecimal sum){
        if(sum.compareTo(new BigDecimal(0))<=0) throw new BadRequest();
        return true;
    }

    public Card addMoney(Card card,BigDecimal addSum){
        isCorrectSum(addSum);
        card.setMoney(card.getMoney().add(addSum));
        return card_repo.save(card);
    }

    public Card removeMoney(Card card,BigDecimal removeSum){
        isCorrectSum(removeSum);
        isEnoughMoney(card,removeSum);
        card.setMoney(card.getMoney().subtract(removeSum));
        return card_repo.save(card);
    }

    public Card transferMoney(Card cardFrom,Card cardTo,BigDecimal transferSum){
        isCorrectSum(transferSum);
        isEnoughMoney(cardFrom,transferSum);
        cardFrom.setMoney(cardFrom.getMoney().subtract(transferSum));
        cardTo.setMoney(cardTo.getMoney().add(transferSum));
        card_repo.save(cardTo);
        return card_repo.save(cardFrom);
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
