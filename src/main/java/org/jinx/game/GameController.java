package org.jinx.game;

import org.jinx.card.NumberCard;
import org.jinx.player.Player;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
//
        g1.setDeckCSV();

        // i is the current round
        for(int i = 1; i < 4; i++){
            g1.play(i);
        }

        writeHighScoreToFile();

    }

    /**
     * Method writes Highscore of Player to highscore.txt file
     */
    private void writeHighScoreToFile() {
        try {
            FileWriter myWriter = new FileWriter("Highscore.txt", true);

            for(Player player : pc.getPlayers()){
                int total = 0;
                for(NumberCard card : player.getCards()){
                    total += Integer.parseInt(card.getName());
                }
                myWriter.append(String.valueOf(total)).append("\t" + player.getName());
                myWriter.append("\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        }

    /**
     * Method prints highscore at start of game
     */
    private void printHighscore(){

    }
}
