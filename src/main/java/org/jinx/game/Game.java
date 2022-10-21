package org.jinx.game;

import org.jinx.card.CardColor;
import org.jinx.card.LuckyCard;
import org.jinx.card.NumberCard;
import org.jinx.player.Player;


import java.util.*;


public class Game {

    private final PlayerController pc = PlayerController.getPlayerControllerInstance();

    private final int numberOfLuckyCards = 12;
    private final Stack<NumberCard> deck;
    private final LuckyCard[] luckyDeck = new LuckyCard[numberOfLuckyCards];

    private final int FIELDSIZE = 16;
    NumberCard[] field = new NumberCard[FIELDSIZE];

    public Game() {
        deck = new Stack<>();
    }

    public void fillDeck() {

        for (CardColor color : CardColor.values()) {
            for (int i = 1; i < 7; i++) {
                deck.add(new NumberCard(Integer.toString(i), color));
            }
        }

        Collections.shuffle(deck);
    }

    private void setField() {

        for (int i = 0; i < field.length; i++) {
            field[i] = deck.pop();
        }
    }

    private void printField() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(field[i * 4 + j] == null ? "null" : field[i * 4 + j].toString() + "\t");
            }

            System.out.println();
        }

    }

    /**
     * This method controls the gameflow for each round
     */
    public void play(int currentRound) {

        Scanner scanner = new Scanner(System.in);

        setField();
        boolean available = true;
        System.out.println("Runde " + currentRound);
        while (available) {
            printField();
            pc.next(); // Player ändern

            System.out.println("Aktiver Spieler " + pc.getCurrentPlayer().getName());

            System.out.println("Drücken sie eine Taste um zu Würfeln");

            scanner.next();
            //wuerfel 2 mal werfen
            int wuerfelergebnis = dice();
            System.out.println("Wuerfel: " + wuerfelergebnis +
                    "\nNochmal wuerfeln? [yes|no]");

            if (scanner.next().equals("yes")) {
                wuerfelergebnis = dice();
                System.out.println("Wuerfel: " + wuerfelergebnis);
            }

            List<NumberCard> availableCards = showAvailableCards(wuerfelergebnis);

            // if true, then the round is over
            if (availableCards.isEmpty()) {
                System.out.println("ENDE");
                available = false;
                break;
            }

            // show player available cards
            System.out.println("AVAILABLE CARDS");
            for (NumberCard card : availableCards) {
                System.out.print(card.toString() + "\t");
            }

            System.out.println("\n---------------\n");

            boolean chosen = false;
            while (!chosen) {

                System.out.println("Wählen sie eine Karte aus: ");
                int index = scanner.nextInt();

                if (index <= 0 || index > availableCards.size()) {
                    System.out.println("Falsche Eingabe");
                } else {
                    NumberCard card = availableCards.get(index - 1);
                    pc.getCurrentPlayer().getCards().add(card);
                    chosen = true;
                    System.out.println(pc.getCurrentPlayer().getCards().toString() + "\n");
                    for (int i = 0; i < field.length; i++) {
                        if (field[i] != null && field[i].equals(card)) {
                            field[i] = null;
                            break;
                        }
                    }
                }
            }
        }


        // Remove cards of player that has equal color as the cards in the field
        // ! move this to a separate method later !
        for (Player player : pc.getPlayers()) {
            List<NumberCard> tempCards = new ArrayList<>();
            for (NumberCard cardsOfPlayer : player.getCards()) {
                for (NumberCard cardInField : field) {
                    if (cardInField != null && cardsOfPlayer.getColor().equals(cardInField.getColor())) {
                        tempCards.add(cardsOfPlayer);
                        break;
                    }
                }

            }
            player.getCards().removeAll(tempCards);
        }

        for (Player player : pc.getPlayers()) {
            for (NumberCard card : player.getCards()) {
                System.out.println(card.toString());
            }
            System.out.println();
        }


    }

    /**
     * This methods simulates a dice
     *
     * @return Returns an int value from 1-6
     */
    private int dice() {
        return (int) (Math.random() * 6) + 1;
    }

    private List<NumberCard> showAvailableCards(int diceNumber) {

        List<NumberCard> cards = new ArrayList<>();

        for (NumberCard c : field) {
            if (c != null && c.getName().equals(Integer.toString(diceNumber))) {
                cards.add(c);
            }
        }

        return cards;
    }


}
