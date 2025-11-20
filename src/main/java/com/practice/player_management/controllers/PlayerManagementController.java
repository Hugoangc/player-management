package com.practice.player_management.controllers;

import com.practice.player_management.enums.GroupCodename;
import com.practice.player_management.exceptions.CodenameGroupUnavailableException;
import com.practice.player_management.models.Player;
import com.practice.player_management.services.PlayerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/player_management")
public class PlayerManagementController {
    private final PlayerService playerService;

    @GetMapping
    public String PlayerManagementPage(Model model) {
        return getViewAndModel(model, new Player(null, null, null, null, null));
    }
    @PostMapping
    public String playerManagement(@ModelAttribute @Valid Player player, BindingResult bindingResult, Model model) throws  Exception {
        if(bindingResult.hasErrors()){
            return getViewAndModel(model, player);
        }
        try {
            playerService.playerRegister(player);
            return "redirect:/player_management";
        } catch (CodenameGroupUnavailableException e) {
            bindingResult.rejectValue("group_codename", "error.group_codename", e.getMessage());
            return getViewAndModel(model, player);
        }
    }
    private String getViewAndModel(Model model, Player player) {
        model.addAttribute("player", player);
        model.addAttribute("groupsCodenames", GroupCodename.values());
        return "player_management";
    }
}
