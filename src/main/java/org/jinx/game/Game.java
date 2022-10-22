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

    /**
     * randomly fills the deck with cards
     */
    public void fillDeck() {

        for (CardColor color : CardColor.values()) {
            for (int i = 1; i < 7; i++) {
                deck.add(new NumberCard(Integer.toString(i), color));
            }
        }

        Collections.shuffle(deck);
    }

    /**
     * takes cards from deck onto field
     */
    private void setField() {

        for (int i = 0; i < field.length; i++) {
            field[i] = deck.pop();
        }
    }

    /**
     * prints a 4x4 cardfield
     */
    private void printField() {

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(field[i * 4 + j] == null ? "\t\t\t" : field[i * 4 + j].toString() + " \t");
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

            System.out.println("\nAktiver Spieler: " + pc.getCurrentPlayer().getName());

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
                //available = false;
                break;
            }

            // show player available cards
            System.out.println("AVAILABLE CARDS");
            for (NumberCard card : availableCards) {
                System.out.print(card.toString() + " ");
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

                    System.out.println("Spieler: " + pc.getCurrentPlayer().getName() +"\n" + pc.getCurrentPlayer().getCards().toString() + "\n");

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

        printHands();
        findHighest();

    }

    /**
     * finds highest NumberCard in playerhand
     */
    private void findHighest(){
        if (!pc.getCurrentPlayer().getCards().isEmpty()) {

            NumberCard max = pc.getCurrentPlayer().getCards().get(0);

            for (int i = 0; i < pc.getCurrentPlayer().getCards().size(); i++) {
                if (Integer.parseInt(pc.getCurrentPlayer().getCards().get(i).getName()) > Integer.parseInt(max.getName())) {
                    max = pc.getCurrentPlayer().getCards().get(i);

                }
            }

            List<NumberCard> temp = new ArrayList<>();
            for (int i = 0; i < pc.getCurrentPlayer().getCards().size(); i++) {

                if (max.getName().equals(pc.getCurrentPlayer().getCards().get(i).getName())) {
                    temp.add(pc.getCurrentPlayer().getCards().get(i));
                }
            }
            discard(temp);
        }
    }

    /**
     * discards highest NumberCard from playerhand
     * @param highest highest # to be discarded
     */
    private void discard(List<NumberCard> highest){
        Scanner scanner = new Scanner(System.in);
        System.out.println(highest);

        System.out.println("Welche Karte möchtest du wegwerfen?");
        int index = scanner.nextInt();

        if (index <= 0 || index > highest.size()) {
            System.out.println("Falsche Eingabe");
            discard(highest);

        } else {

            pc.getCurrentPlayer().getCards().remove(highest.get(index - 1));

            System.out.println("NACH WEGWURF ----------------");
            printHands();
        }
    }

    /**
     * Prints hands of all players
     */
    private void printHands() {
        //print player hands
        for (Player player : pc.getPlayers()) {
            System.out.println("Spieler: " + player.getName());
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

    /**
     * shows all available cards to choose from
     * @param diceNumber number the player rolled
     * @return returns list of available cards
     */
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
