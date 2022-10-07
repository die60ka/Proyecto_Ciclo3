package com.utp.misiontic.crm.ropa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class crmClothesController {

    @GetMapping(value = {"/", "/index", "/index.html"})
    public String goToIndex(Model model) {
        model.addAttribute("page", "home");
        return "index";
    }

    @GetMapping("/contact")
    public String goToContact(Model model) {
        model.addAttribute("page", "contact");
        return "contact";
    }

    @GetMapping("/login")
    public String goToLogin(Model model) {
        return "login";
    }


}
