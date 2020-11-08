package com.JSKP.KP.controller;

import com.JSKP.KP.service.Card_service;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
@RequestMapping
public class MainController {
    private final Card_service card_service;

    public MainController(Card_service card_service) {
        this.card_service = card_service;
    }

    @GetMapping
    public String getMain(Model model){
        model.addAttribute("frontendData",card_service.getCards());
        return "main";
    }
}
