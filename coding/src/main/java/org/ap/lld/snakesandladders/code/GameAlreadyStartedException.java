package org.ap.lld.snakesandladders.code;


public class GameAlreadyStartedException extends Exception {
    public GameAlreadyStartedException(String message) {
        super(message);
    }
}