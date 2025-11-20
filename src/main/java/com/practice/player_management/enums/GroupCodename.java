package com.practice.player_management.enums;

import lombok.Getter;

@Getter
public enum GroupCodename {
    AVENGERS("Avengers", "https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/vingadores.json"),
    JUSTICE_LEAGUE("The Justice League", "https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/liga_da_justica.xml");



    GroupCodename(String name, String url){
        this.name = name;
        this.url = url;
    }

    private final String name;
    private final String url;

}
