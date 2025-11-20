package com.practice.player_management;


import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.practice.player_management.enums.GroupCodename;
import com.practice.player_management.models.Player;
import com.practice.player_management.services.CodenameService;
import com.practice.player_management.services.PlayerService;
import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
class PlayerManagementApplicationIT {

    @MockBean
    private PlayerService playerService;

    @Autowired
    public MockMvc mockMvc;

	@Test
	void registerListPlayerSucess()  throws Exception{

        var player = new Player("Test", "test@test.com",
                "123456", null, GroupCodename.JUSTICE_LEAGUE);
        when(playerService.playerRegister(any(Player.class))).thenReturn(player);
        when(playerService.playersResponse()).thenReturn(List.of(player));



        mockMvc
             .perform(post("/player_management")
                .param("name", player.name())
                        .param("email", player.email())
                        .param("phone", player.phone())
                        .param("codenameGroup", player.codenameGroup().name()))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/players_response"));

        mockMvc
                .perform(get("/players_response"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("players_response"))
                .andExpect(model().attribute("players", hasItem(player)));
    }
}
