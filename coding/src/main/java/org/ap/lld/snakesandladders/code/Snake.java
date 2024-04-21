package org.ap.lld.snakesandladders.code;

public class Snake extends SpecialEntity{

    public Snake(int start, int end) {
        super(start, end);
    }

    @Override
    public String getID() {
        return "S_"+ this.getEndPosition();
    }
}