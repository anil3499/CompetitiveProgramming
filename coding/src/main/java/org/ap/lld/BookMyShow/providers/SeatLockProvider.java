package org.ap.lld.BookMyShow.providers;



import org.ap.lld.BookMyShow.model.Seat;
import org.ap.lld.BookMyShow.model.Show;

import java.util.List;

public interface SeatLockProvider {

    void lockSeats(Show show, List<Seat> seat, String user);
    void unlockSeats(Show show, List<Seat> seat, String user);
    boolean validateLock(Show show, Seat seat, String user);

    List<Seat> getLockedSeats(Show show);
}
