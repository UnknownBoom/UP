package com.JSKP.KP.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
@RequestMapping
public class MainController {
    @GetMapping
    public String getMain(Model model){

        List<Map<String, String>> cards = new ArrayList<Map<String, String>>() {{
            add(new HashMap<String, String>() {{ put("card_number", "1"); put("owner_name", "First message"); }});
            add(new HashMap<String, String>() {{ put("card_number", "2"); put("owner_name", "Second message"); }});
            add(new HashMap<String, String>() {{ put("card_number", "3"); put("owner_name", "Third message"); }});
        }};

        model.addAttribute("frontendData",cards);

        return "main";
    }
}
