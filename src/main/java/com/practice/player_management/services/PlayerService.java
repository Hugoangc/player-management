package com.practice.player_management.services;

import com.practice.player_management.enums.GroupCodename;
import com.practice.player_management.models.Player;
import com.practice.player_management.repositories.PlayerRepository;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final CodenameService codenameService;

    public Player playerRegister(Player player) throws Exception{
        var codenameInUse = listCodenamesInUse(player.groupCodename());
        var newCodename = codenameService.generateCodename(player.groupCodename(), codenameInUse);
        var newPlayer = new Player(player.name(), player.email(), player.phone(), newCodename, player.groupCodename());
        return playerRepository.save(newPlayer);
    }


    private List<String> listCodenamesInUse(@NotNull GroupCodename groupCodename) {
        return playerRepository.listCodenamesPerGroup(groupCodename);
    }
}
