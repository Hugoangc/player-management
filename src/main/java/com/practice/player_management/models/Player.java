package com.practice.player_management.models;

import com.practice.player_management.enums.GroupCodename;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record Player (
        @NotBlank String name,
        @NotBlank @Email String email,
        String phone,
        String codename,
        @NotNull GroupCodename codenameGroup

){
    public Player withCodename(String codename){
        return new Player(name, email, phone, codename, codenameGroup);
    }
}
