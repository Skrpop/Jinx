package org.jinx.card;

public class NumberCard extends Card {

    private final CardColor color;

    public NumberCard(String name, CardColor color) {
        super(name);
        this.color = color;
    }
}
