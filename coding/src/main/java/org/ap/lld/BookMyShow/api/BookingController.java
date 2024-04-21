package org.ap.lld.BookMyShow.api;


import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.ap.lld.BookMyShow.model.Seat;
import org.ap.lld.BookMyShow.model.Show;
import org.ap.lld.BookMyShow.services.BookingService;
import org.ap.lld.BookMyShow.services.ShowService;
import org.ap.lld.BookMyShow.services.TheatreService;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class BookingController {
    private final ShowService showService;
    private final BookingService bookingService;
    private final TheatreService theatreService;

    public String createBooking(@NonNull final String userId, @NonNull final String showId,
                                @NonNull final List<String> seatsIds) {
        final Show show = showService.getShow(showId);
        final List<Seat> seats = seatsIds.stream().map(theatreService::getSeat).collect(Collectors.toList());
        return bookingService.createBooking(userId, show, seats).getId();
    }
}
