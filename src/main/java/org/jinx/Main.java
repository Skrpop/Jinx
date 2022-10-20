package org.jinx;

import org.jinx.game.GameController;
import org.jinx.game.PlayerController;

import java.util.logging.Logger;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {
        System.out.println("Hello");
        LOGGER.info("Start Game...");
        PlayerController pc = PlayerController.getPlayerControllerInstance();
        pc.addPlayers();
//        GameController.start();
    }
}