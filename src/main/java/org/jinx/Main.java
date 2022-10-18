package org.jinx;

import org.jinx.game.GameController;

import java.util.logging.Logger;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(this.getClass().getName());
    public static void main(String[] args) {

        LOGGER.info("Start Game...");
        GameController.start();
    }
}