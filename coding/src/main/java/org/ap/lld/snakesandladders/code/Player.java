package org.ap.lld.snakesandladders.code;


import lombok.Getter;
import lombok.Setter;

public class Player{

    @Getter
    @Setter
    int position;

    @Getter
    String name;

    public Player(String name)
    {
        this.name = name;
        this.position = 0;
    }
}
