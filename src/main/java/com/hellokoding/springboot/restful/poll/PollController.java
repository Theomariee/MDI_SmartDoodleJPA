package com.hellokoding.springboot.restful.poll;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PollController {
    @GetMapping("/")
    public String list(){
        return "polls";
    }
    @GetMapping("/poll")
    public String listPolls(){
        return "polls";
    }
    @GetMapping("/participant")
    public String listParticipants(){
        return "polls";
    }
}
