package com.hellokoding.springboot.restful.participant;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ParticipantController {
    @GetMapping("/")
    public String list(){
        return "participants";
    }
}
