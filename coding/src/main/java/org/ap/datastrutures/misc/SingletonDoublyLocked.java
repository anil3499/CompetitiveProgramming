package org.ap.datastrutures.misc;

import java.io.Serializable;
/*
https://dzone.com/articles/prevent-breaking-a-singleton-class-pattern
 */
public class SingletonDoublyLocked implements Serializable, Cloneable {

    private static SingletonDoublyLocked INSTANCE;

    private SingletonDoublyLocked() {
    }

    public static SingletonDoublyLocked getInstance() {
        //First check - To avoid more than one instance creation of Singleton class.
        if (INSTANCE == null) {
            synchronized (SingletonDoublyLocked.class) {
                //Second check - To avoid more than one instance creation of
                //Singleton class.
                if (INSTANCE == null) {
                    INSTANCE = new SingletonDoublyLocked();
                }
            }
        }
        return INSTANCE;
    }

    protected Object readResolve() {
        return INSTANCE;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return INSTANCE;
    }
}
