package com.JSKP.KP.controller;

import com.JSKP.KP.exception.NotFoundException;
import com.JSKP.KP.model.Card;
import com.JSKP.KP.service.Card_service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
public class CardController {
    private final Card_service card_service;

    public CardController(Card_service card_service) {
        this.card_service = card_service;
    }

    @GetMapping
    public Iterable<Card> getCards(Model model){
        return card_service.getCards();
    }

    @GetMapping("{number}")
    public Card getCard(@PathVariable("number") Card card){
        return card;
    }

    @PostMapping
    public Card addCards(@RequestBody Card card){
        return card_service.addCard(card);
    }

    @PutMapping
    public Card updateCards(@RequestBody Card card){
        return card_service.updateCard(card);
    }

    @DeleteMapping("{number}")
    public void deleteCards(@PathVariable("number")Card card){
        card_service.deleteCard(card);
    }
}
