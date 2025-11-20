package com.practice.player_management.repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.player_management.dtos.AvengersDTO;
import com.practice.player_management.enums.GroupCodename;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClient;

import java.util.List;

@Repository
public class AvengersRepository implements CodenameRepository {


    @Override
    public List<String> searchCodenames() throws Exception{
        var codenames = RestClient
                .builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.TEXT_PLAIN_VALUE)
                .baseUrl(GroupCodename.AVENGERS.getUrl())
                .build()
                .get()
                .retrieve()
                .body(String.class);
        var objectMapper = new ObjectMapper();
        var avengers = objectMapper.readValue(codenames, AvengersDTO.class);

        return avengers.getCodenames();

    }
}
