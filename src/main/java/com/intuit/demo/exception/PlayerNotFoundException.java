package com.intuit.demo.exception;

public class PlayerNotFoundException extends RuntimeException{
    public PlayerNotFoundException(String playerId){
        super("Player not found with id: " + playerId);
    }
}
