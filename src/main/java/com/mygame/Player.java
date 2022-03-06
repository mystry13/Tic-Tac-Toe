package com.mygame;

public class Player {
    private String character;
    private int playerId = 0;

    public Player(String character) {
        this.character = character;
        this.assignIdToPlayer(character);
    }

    private void assignIdToPlayer(String character) {
        if (character.equals("❌")) {
            this.playerId = 1;
        } else {
            this.playerId = 2;
        }
    }

    public int getPlayerId() {
        return playerId;
    }

    public String getCharacter() {
        return character;
    }
}
