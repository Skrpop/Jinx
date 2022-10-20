package org.jinx.player;

import org.jinx.card.Card;

import java.util.ArrayList;
import java.util.List;

public class Player {

    /**
     * Name of the player
     */
    private final String name;

    /**
     * Stores if the Player used the redo funtion in game to get the old dice result back.
     * If true, then player Highscore should not be listed after game
     */
    private boolean usedRedo = false;

    /**
     * Stores the cards of the player in game
     */
    private List<Card> cards;

    /**
     * Standard COnstructor for the Player
     * @param name
     */
    public Player(String name) {
        this.name = name;
        this.cards = new ArrayList<>();
    }

    /* ---------- Getter and Setter Methods ---------- */
    public String getName() {
        return name;
    }

    public boolean isUsedRedo() {
        return usedRedo;
    }

    public void setUsedRedo(boolean usedRedo) {
        this.usedRedo = usedRedo;
    }
}
