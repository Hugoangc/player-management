package com.practice.player_management.controllers;


import com.practice.player_management.services.PlayerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("players_response")
public class PlayersResponseController {
    private final PlayerService playerService;

    @GetMapping
    public String playersResponse(Model model){
        model.addAttribute("players", playerService.playersResponse());
        return "players_response";
    }
}
