package com.practice.player_management.repositories;

import com.practice.player_management.enums.GroupCodename;

import java.util.List;

public interface CodenameRepository {
    List<String> searchCodenames() throws Exception;
}
