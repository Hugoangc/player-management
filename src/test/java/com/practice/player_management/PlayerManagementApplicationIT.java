package com.practice.player_management;


import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.hasToString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.practice.player_management.enums.GroupCodename;
import com.practice.player_management.models.Player;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class PlayerManagementApplicationIT {

    @Autowired
    private MockMvc mockMvc;

	@Test
	void registerListPlayerSucess()  throws Exception{
        var player = new Player("Test", "test@test.com",
                "123456", null, GroupCodename.AVENGERS);
        mockMvc
             .perform(post("/player_management")
                .param("name", player.name())
                        .param("email", player.email())
                        .param("phone", player.phone())
                        .param("codenameGroup", player.codenameGroup().name()))
                .andDo(print())
                .andExpect(status().is3xxRedirection()) // Verifica redirecionamento
                .andExpect(redirectedUrl("/players_response"));

        mockMvc
                .perform(get("/players_response"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("players_response"))
                .andExpect(model().attribute("players", hasSize(1)))
                .andExpect(model().attribute("players", contains(allOf(
                        hasToString(containsString(player.name())),
                        hasToString(containsString(player.email())),
                        hasToString(containsString(player.phone())),
                        hasToString(containsString(player.codenameGroup().name()))))));
    }
}
