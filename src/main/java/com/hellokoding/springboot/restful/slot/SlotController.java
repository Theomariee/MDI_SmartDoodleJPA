package com.hellokoding.springboot.restful.slot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SlotController {
    @GetMapping("/")
    public String list(){
        return "slots";
    }
}
