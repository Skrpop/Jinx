package org.jinx.card;

public class NumberCard extends Card {

    private final CardColor color;

    public NumberCard(String name, CardColor color) {
        super(name);
        this.color = color;
    }

    public CardColor getColor() {
        return this.color;
    }

    @Override
    public String toString() {

//        String output = "";
//
//        output += "-".repeat(color.name().length() + 2) + "\n";
//
//        output += "| " + getName() + " ".repeat(color.name().length() - getName().length() - 2) + " |\n";
//
//        output += "|" + " ".repeat(color.name().length()) + "|\n";
//
//        output += "" + color.name() + " |\n";
//        output += "-".repeat(color.name().length() + 2);

        return "|" + getName() + " " + getColor() + "|";
//        return output;

    }
}
