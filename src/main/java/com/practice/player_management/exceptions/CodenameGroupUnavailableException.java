package com.practice.player_management.exceptions;

public class CodenameGroupUnavailableException extends IllegalArgumentException {

    public CodenameGroupUnavailableException() {
        super("There are no condenames available for the selected group.");

    }
}


