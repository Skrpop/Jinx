package org.jinx.card;

public abstract class LuckyCard extends Card {

    public LuckyCard(String name) {
        super(name);
    }

    public void use() {
        effect();
    }

    public abstract void effect();
}
