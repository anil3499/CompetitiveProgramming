package org.ap.lld.snakesandladders.code;

public abstract class SpecialEntity {

    private int start;
    private int end;

    public SpecialEntity(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public abstract String getID();

    public int getActionPosition()
    {
        return this.start;
    }

    public int getEndPosition()
    {
        return this.end;
    }
}