package org.jinx.game;

import org.jinx.player.Player;

import java.util.ArrayList;
import java.util.List;

public class GameController {

    private PlayerController pc;
    /**
     * Stores current players
     */
    private List<Player> players;

    /**
     * Basic Constructor of the GameController class
     */
    public GameController() {
        players = new ArrayList<>();
        pc = PlayerController.getPlayerControllerInstance();

    }

    /**
     * Method starts the game
     */
    public void start() {
        Game g1 = new Game();
        pc.addPlayers();
//        pc.next();
        g1.fillDeck();

        // i is the current round
        g1.play(0);
    }

    /**
     * Method writes Highscore of Player to highscore.txt file
     */
    private void writeHighScoreToFile() {

    }
}
