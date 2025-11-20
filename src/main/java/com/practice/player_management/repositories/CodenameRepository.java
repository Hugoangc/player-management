package com.practice.player_management.repositories;

import com.practice.player_management.dtos.CodenameDTO;
import com.practice.player_management.enums.GroupCodename;

import java.util.List;

public interface CodenameRepository {
    CodenameDTO searchCodenames() throws Exception;
}
