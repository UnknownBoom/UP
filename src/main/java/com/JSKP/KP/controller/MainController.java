package com.JSKP.KP.controller;

import com.JSKP.KP.service.CardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class MainController {
    private final CardService card_service;

    public MainController(CardService card_service) {
        this.card_service = card_service;
    }

    @GetMapping
    public String getMain(Model model){
        model.addAttribute("frontendData",card_service.getCards());
        return "main";
    }
}
