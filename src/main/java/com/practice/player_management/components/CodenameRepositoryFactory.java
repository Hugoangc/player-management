package com.practice.player_management.components;


import com.practice.player_management.enums.GroupCodename;
import com.practice.player_management.repositories.AvengersRepository;
import com.practice.player_management.repositories.CodenameRepository;
import com.practice.player_management.repositories.JusticeLeagueRepository;
import org.springframework.stereotype.Component;

@Component
public class CodenameRepositoryFactory {
    private final JusticeLeagueRepository justiceLeagueRepository;
    private final AvengersRepository avengersRepository;

    public CodenameRepositoryFactory(JusticeLeagueRepository justiceLeagueRepository,AvengersRepository avengersRepository ){
        this.justiceLeagueRepository = justiceLeagueRepository;
        this.avengersRepository = avengersRepository;
    }

    public CodenameRepository create (GroupCodename groupCodename){
        return switch (groupCodename){
            case JUSTICE_LEAGUE ->  justiceLeagueRepository;
            case AVENGERS ->   avengersRepository;
        };
    }
}
