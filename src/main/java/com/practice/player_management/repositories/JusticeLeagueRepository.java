package com.practice.player_management.repositories;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.practice.player_management.dtos.CodenameDTO;
import com.practice.player_management.dtos.JusticeLeagueDTO;
import com.practice.player_management.enums.GroupCodename;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClient;

import java.util.List;

@Repository
public class JusticeLeagueRepository implements CodenameRepository {
    @Override
    public CodenameDTO searchCodenames() throws Exception {
        var codenames = RestClient.builder()
                .baseUrl(GroupCodename.JUSTICE_LEAGUE.getUrl())
                .build()
                .get()
                .retrieve()
                .body(String.class);

        var xmlMapper = new XmlMapper();
        return xmlMapper.readValue(codenames, JusticeLeagueDTO.class);
    }
}
