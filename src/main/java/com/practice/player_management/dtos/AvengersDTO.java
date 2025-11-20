package com.practice.player_management.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record AvengersDTO (@JsonProperty("vingadores") List<Codename> avengers) implements CodenameDTO{

    @Override
    public List<String> getCodenames(){
        return avengers
                .stream()
                .map(Codename::codename)
                .toList();
    }
}
@JsonIgnoreProperties(ignoreUnknown = true)
record Codename(
        @JsonProperty("codinome") String codename
){



}