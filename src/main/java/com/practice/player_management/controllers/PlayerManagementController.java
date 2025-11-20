package com.practice.player_management.controllers;

import com.practice.player_management.enums.GroupCodename;
import com.practice.player_management.models.Player;
import com.practice.player_management.services.PlayerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("player-management")
public class PlayerManagementController {
    private final PlayerService playerService;

    @GetMapping
    public String PlayerManagementPage(Model model) {
        model.addAttribute("groupsCodenames", GroupCodename.values());
        return "player-management";
    }
    @PostMapping
    public String playerManagement(@ModelAttribute Player player){
        try {
            playerService.playerRegister(player);
            return "redirect:/player-management";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
