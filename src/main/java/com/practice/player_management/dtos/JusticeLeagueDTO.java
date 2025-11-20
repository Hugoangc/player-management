package com.practice.player_management.dtos;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "liga_da_justica")
public record JusticeLeagueDTO(
    @JacksonXmlProperty(localName = "codinomes") CodenamesDTO codenames) implements CodenameDTO{

    @Override
    public List<String> getCodenames(){
        return codenames.codenames();
    }

}

record CodenamesDTO(
        @JacksonXmlElementWrapper(useWrapping = false)
        @JacksonXmlProperty(localName = "codinome")
        List<String> codenames
) { }
//record CodenamesDTO(
//    @JacksonXmlElementWrapper(useWrapping = false)
//    @JacksonXmlProperty(localName = "codinome")
//    List<String> codenames){
//
//    }

