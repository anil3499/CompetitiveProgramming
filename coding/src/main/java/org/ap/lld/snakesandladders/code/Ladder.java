package org.ap.lld.snakesandladders.code;

public class Ladder extends SpecialEntity{

    public Ladder(int start, int end) {
        super(start, end);
    }

    @Override
    public String getID() {
        return "L_"+ this.getEndPosition();
    }
}