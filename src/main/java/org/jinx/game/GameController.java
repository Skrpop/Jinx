package org.jinx.game;

import org.jinx.player.Player;

import java.util.ArrayList;
import java.util.List;

public class GameController {

    /**
     * Stores current players
     */
    private List<Player> players;

    /**
     * Basic Constructor of the GameController class
     */
    public GameController() {
        players = new ArrayList<>();
    }

    /**
     * Method starts the game
     */
    public static void start() {

    }

    /**
     * Method writes Highscore of Player to highscore.txt file
     */
    private void writeHighScoreToFile() {

    }
}
