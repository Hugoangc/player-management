package com.practice.player_management.repositories;

import com.practice.player_management.enums.GroupCodename;
import com.practice.player_management.models.Player;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class PlayerRepository {
    private final JdbcClient jdbcClient;

    public Player save(Player player) {
        jdbcClient.sql("""
        INSERT INTO PLAYERS (name, email, phone, codename, codename_group)
        VALUES (:name, :email, :phone, :codename, :codenameGroup)
        """)
                .param("name", player.name())
                .param("email", player.email())
                .param("phone", player.phone())
                .param("codename", player.codename())
                .param("codenameGroup", player.codenameGroup().name())
                .update();


        return player;
    }

    public List<String> listCodenamesAvailable(GroupCodename codenameGroup) {
        return jdbcClient.sql("SELECT distinct(codename) FROM PLAYERS WHERE codename_group = :codenameGroup")
                .param("codenameGroup", codenameGroup.name())
                .query(String.class)
                .list();
    }

    public List<Player> listPlayers() {
        return jdbcClient.sql("SELECT * FROM PLAYERS ORDER BY LOWER(name), id")
                .query(Player.class)
                .list();
    }

    public List<String> listCodenamesPerGroup(GroupCodename groupCodename) {
        return jdbcClient.sql("SELECT distinct(codename) FROM PLAYERS WHERE codename_group = :codenameGroup")
                .param("codenameGroup", groupCodename.name())
                .query(String.class)
                .list();
    }

    public List<Player> playersResponse(){
        return jdbcClient.sql("SELECT * FROM PLAYERS ORDER BY LOWER(name), id")
                .query(Player.class)
                .list();
    }
}