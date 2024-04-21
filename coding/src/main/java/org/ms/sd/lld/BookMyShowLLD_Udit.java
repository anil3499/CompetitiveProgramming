package org.ms.sd.lld;/*
package ms.sd.lld;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import com.google.common.collect.ImmutableList;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;
*/
/*
Implement a Movie Ticket Booking Application for a Theatre. Refer to the following details:

A Theatre has Screens that run Shows for different Movies. Each Show has a particular Movie, start time, duration, and is played in a particular Screen in the theatre. Each Screen has an arrangement of Seats that can be booked by Users.
Assume all Users are registered, authenticated, and logged in to the Application.
Once a User selects a particular show to book tickets for, a UserBookingSession starts. Within this UserBookingSession, a User will be able to get the Available Seats for the show and select the Seats he wishes to book. It is a ‘good to have’ for the Application to have limits on the number of seats a User can book in a Ticket.
Once the user has selected a group of seats, these seats should become TEMPORARILY_UNAVAILABLE to all other Users.
The User then proceeds to make payment which can either be SUCCESS or FAILURE.
If Payment FAILED, user can retry Payment for a maximum number of times. Beyond maximum retries, the seats are made AVAILABLE.
If Payment SUCCEEDS, Ticket or Booking Confirmation is generated and made available to the User. The UserBookingSession is closed and the Seats are made PERMANENTLY_UNAVAILABLE.
A User can also explicitly close the UserBookingSession after selecting seats and before making payment. In this case, the seats selected are made AVAILABLE once again.
Problems:
Demonstrate the following scenarios:
2 concurrent Users U1, U2 in the application. The Users can retrieve Available Shows and select one show.

Case 1:
Say U1 and U2 select same show.
U1 requests for and gets all Available Seats for this show.
U1 selects group of seats and proceeds to pay.
U2 requests for and gets all Available Seats for this show. U2 should not see the seats selected by U1 as AVAILABLE. 5 .Payment succeeded for U1.
U1 receives Ticket with Seats confirmed.
Case 2:
Say U1 and U2 select same show.
U1 and U2 requests for and gets all Available Seats for this show.
U1 selects group of seats.
U1 proceeds to pay.
U2 requests for and gets all Available Seats for this show. U2 should NOT see the seats selected by U1 as AVAILABLE.
Payment failed for U1. Assume maximum retries as zero just for the demo. Also show in another scenario where U1’s UserBookingSession is explicitly closed by U1 before payment is completed.
U2 again requests for and gets all Available Seats for this show. U2 should now see the seats previously selected by U1 as AVAILABLE.
Case 3:
Say U1 and U2 select same show.
U1 and U2 request for and get all Available Seats for this show.
U1 selects group of seats and proceeds to pay.
U2 selects overlapping group of seats and proceeds to pay. U2 should be notified that “one or more of the selected seats are not available at this moment”.
Bonus:
Have a configurable timeout for a UserBookingSession. Show that if User selects and Payment is not completed by timeout, then the UserBookingSession is closed and the seats selected are made AVAILABLE.

Expectations
Create the sample data yourself. You can put it into a file, test case or main driver program itself.
Code should be demo-able. Either by using a main driver program or test cases.
Code should be modular. Code should have basic OO design. Please do not jam in responsibilities of one class into another.
Code should be extensible. Wherever applicable, use interfaces and contracts between different methods. It should be easy to add/remove functionality without re­writing entire codebase.
Code should handle edge cases properly and fail gracefully.
Code should be legible, readable and DRY
 *//*


//model=====================================================
enum BookingStatus {
    Created,
    Confirmed,
    Expired
}

@AllArgsConstructor
@Getter
class Movie {

    private final String id;
    private final String name;

    //Other metadata
}

@Getter
class Screen {

    private final String id;
    private final String name;
    private final Theatre theatre;
    //Other screen metadata.

    private final List<Seat> seats;

    public Screen(@NonNull final String id, @NonNull final String name, @NonNull final Theatre theatre) {
        this.id = id;
        this.name = name;
        this.theatre = theatre;
        this.seats = new ArrayList<>();
    }

    public void addSeat(@NonNull final Seat seat) {
        this.seats.add(seat);
    }

}

@AllArgsConstructor
@Getter
class Seat {

    private final String id;
    private final int rowNo;
    private final int seatNo;
}

@Getter
class Booking {

    private final String id;
    private final Show show;
    private final List<Seat> seatsBooked;
    private final String user;
    private BookingStatus bookingStatus;

    public Booking(@NonNull final String id, @NonNull final Show show, @NonNull final String user,
                   @NonNull final List<Seat> seatsBooked) {
        this.id = id;
        this.show = show;
        this.seatsBooked = seatsBooked;
        this.user = user;
        this.bookingStatus = BookingStatus.Created;
    }

    public boolean isConfirmed() {
        return this.bookingStatus == BookingStatus.Confirmed;
    }

    public void confirmBooking() {
        if (this.bookingStatus != BookingStatus.Created) {
            throw new InvalidStateException();
        }
        this.bookingStatus = BookingStatus.Confirmed;
    }

    public void expireBooking() {
        if (this.bookingStatus != BookingStatus.Created) {
            throw new InvalidStateException();
        }
        this.bookingStatus = BookingStatus.Expired;
    }
}

@AllArgsConstructor
@Getter
class Show {

    private final String id;
    private final Movie movie;
    private final Screen screen;
    private final Date startTime;
    private final Integer durationInSeconds;
}

@Getter
class Theatre {

    private final String id;
    private final String name;
    private final List<Screen> screens;
    //Other theatre metadata.

    public Theatre(@NonNull final String id, @NonNull final String name) {
        this.id = id;
        this.name = name;
        this.screens = new ArrayList<>();
    }

    public void addScreen(@NonNull final  Screen screen) {
        screens.add(screen);
    }
}


@AllArgsConstructor
@Getter
class SeatLock {
    private Seat seat;
    private Show show;
    private Integer timeoutInSeconds;
    private Date lockTime;
    private String lockedBy;

    public boolean isLockExpired() {
        final Instant lockInstant = lockTime.toInstant().plusSeconds(timeoutInSeconds);
        final Instant currentInstant = new Date().toInstant();
        return lockInstant.isBefore(currentInstant);
    }
}

//controllers=============================================================================================
@AllArgsConstructor
class BookingController {
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

@AllArgsConstructor
class MovieController {
    final private MovieService movieService;

    public String createMovie(@NonNull final String movieName) {
        return movieService.createMovie(movieName).getId();
    }

}

class PaymentsController {
    private final PaymentsService paymentsService;
    private final BookingService bookingService;

    public PaymentsController(PaymentsService paymentsService, BookingService bookingService) {
        this.paymentsService = paymentsService;
        this.bookingService = bookingService;
    }

    public void paymentFailed(@NonNull final String bookingId, @NonNull final String user) {
        paymentsService.processPaymentFailed(bookingService.getBooking(bookingId), user);
    }

    public void paymentSuccess(@NonNull final  String bookingId, @NonNull final String user) {
        bookingService.confirmBooking(bookingService.getBooking(bookingId), user);
    }

}

@AllArgsConstructor
class ShowController {
    private final SeatAvailabilityService seatAvailabilityService;
    private final ShowService showService;
    private final TheatreService theatreService;
    private final MovieService movieService;

    public String createShow(@NonNull final String movieId, @NonNull final String screenId, @NonNull final Date startTime,
                             @NonNull final Integer durationInSeconds) {
        final Screen screen = theatreService.getScreen(screenId);
        final Movie movie = movieService.getMovie(movieId);
        return showService.createShow(movie, screen, startTime, durationInSeconds).getId();
    }

    public List<String> getAvailableSeats(@NonNull final String showId) {
        final Show show = showService.getShow(showId);
        final List<Seat> availableSeats = seatAvailabilityService.getAvailableSeats(show);
        return availableSeats.stream().map(Seat::getId).collect(Collectors.toList());
    }
}

@AllArgsConstructor
class TheatreController {
    final private TheatreService theatreService;

    public String createTheatre(@NonNull final String theatreName) {
        return theatreService.createTheatre(theatreName).getId();
    }

    public String createScreenInTheatre(@NonNull final String screenName, @NonNull final String theatreId) {
        final Theatre theatre = theatreService.getTheatre(theatreId);
        return theatreService.createScreenInTheatre(screenName, theatre).getId();
    }

    public String createSeatInScreen(@NonNull final Integer rowNo, @NonNull final Integer seatNo, @NonNull final String screenId) {
        final Screen screen = theatreService.getScreen(screenId);
        return theatreService.createSeatInScreen(rowNo, seatNo, screen).getId();
    }
}

//services====================================================================================================
class BookingService {

    private final Map<String, Booking> showBookings;
    private final SeatLockProvider seatLockProvider;

    public BookingService(SeatLockProvider seatLockProvider) {
        this.seatLockProvider = seatLockProvider;
        this.showBookings = new HashMap<>();
    }

    public Booking getBooking(@NonNull final String bookingId) {
        if (!showBookings.containsKey(bookingId)) {
            throw new NotFoundException();
        }
        return showBookings.get(bookingId);
    }

    public List<Booking> getAllBookings(@NonNull final Show show) {
        List<Booking> response = new ArrayList<>();
        for (Booking booking : showBookings.values()) {
            if (booking.getShow().equals(show)) {
                response.add(booking);
            }
        }

        return response;
    }

    public Booking createBooking(@NonNull final String userId, @NonNull final Show show,
                                 @NonNull final List<Seat> seats) {
        if (isAnySeatAlreadyBooked(show, seats)) {
            throw new SeatPermanentlyUnavailableException();
        }
        seatLockProvider.lockSeats(show, seats, userId);
        final String bookingId = UUID.randomUUID().toString();
        final Booking newBooking = new Booking(bookingId, show, userId, seats);
        showBookings.put(bookingId, newBooking);
        return newBooking;
        // TODO: Create timer for booking expiry
    }

    public List<Seat> getBookedSeats(@NonNull final Show show) {
        return getAllBookings(show).stream()
                .filter(Booking::isConfirmed)
                .map(Booking::getSeatsBooked)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public void confirmBooking(@NonNull final Booking booking, @NonNull final String user) {
        if (!booking.getUser().equals(user)) {
            throw new BadRequestException();
        }

        for (Seat seat : booking.getSeatsBooked()) {
            if (!seatLockProvider.validateLock(booking.getShow(), seat, user)) {
                throw new BadRequestException();
            }
        }
        booking.confirmBooking();
    }

    private boolean isAnySeatAlreadyBooked(final Show show, final List<Seat> seats) {
        final List<Seat> bookedSeats = getBookedSeats(show);
        for (Seat seat : seats) {
            if (bookedSeats.contains(seat)) {
                return true;
            }
        }
        return false;
    }
}

class MovieService {

    private final Map<String, Movie> movies;

    public MovieService() {
        this.movies = new HashMap<>();
    }

    public Movie getMovie(@NonNull final String movieId) {
        if (!movies.containsKey(movieId)) {
            throw new NotFoundException();
        }
        return movies.get(movieId);
    }

    public Movie createMovie(@NonNull final String movieName) {
        String movieId = UUID.randomUUID().toString();
        Movie movie = new Movie(movieId, movieName);
        movies.put(movieId, movie);
        return movie;
    }

}

class PaymentsService {

    Map<Booking, Integer> bookingFailures;
    private final Integer allowedRetries;
    private final SeatLockProvider seatLockProvider;

    public PaymentsService(@NonNull final Integer allowedRetries, SeatLockProvider seatLockProvider) {
        this.allowedRetries = allowedRetries;
        this.seatLockProvider = seatLockProvider;
        bookingFailures = new HashMap<>();
    }

    public void processPaymentFailed(@NonNull final Booking booking, @NonNull final String user) {
        if (!booking.getUser().equals(user)) {
            throw new BadRequestException();
        }
        if (!bookingFailures.containsKey(booking)) {
            bookingFailures.put(booking, 0);
        }
        final Integer currentFailuresCount = bookingFailures.get(booking);
        final Integer newFailuresCount = currentFailuresCount + 1;
        bookingFailures.put(booking, newFailuresCount);
        if (newFailuresCount > allowedRetries) {
            seatLockProvider.unlockSeats(booking.getShow(), booking.getSeatsBooked(), booking.getUser());
        }
    }
}

class SeatAvailabilityService {
    private final BookingService bookingService;
    private final SeatLockProvider seatLockProvider;

    public SeatAvailabilityService(@NonNull final BookingService bookingService,
                                   @NonNull final SeatLockProvider seatLockProvider) {
        this.bookingService = bookingService;
        this.seatLockProvider = seatLockProvider;
    }

    public List<Seat> getAvailableSeats(@NonNull final Show show) {
        final List<Seat> allSeats = show.getScreen().getSeats();
        final List<Seat> unavailableSeats = getUnavailableSeats(show);

        final List<Seat> availableSeats = new ArrayList<>(allSeats);
        availableSeats.removeAll(unavailableSeats);
        return availableSeats;
    }

    private List<Seat> getUnavailableSeats(@NonNull final Show show) {
        final List<Seat> unavailableSeats = bookingService.getBookedSeats(show);
        unavailableSeats.addAll(seatLockProvider.getLockedSeats(show));
        return unavailableSeats;
    }

}

class ShowService {

    private final Map<String, Show> shows;

    public ShowService() {
        this.shows = new HashMap<>();
    }

    public Show getShow(@NonNull final String showId) {
        if (!shows.containsKey(showId)) {
            throw new NotFoundException();
        }
        return shows.get(showId);
    }

    public Show createShow(@NonNull final Movie movie, @NonNull final Screen screen, @NonNull final Date startTime,
                           @NonNull final Integer durationInSeconds) {
        if (!checkIfShowCreationAllowed(screen, startTime, durationInSeconds)) {
            throw new ScreenAlreadyOccupiedException();
        }
        String showId = UUID.randomUUID().toString();
        final Show show = new Show(showId, movie, screen, startTime, durationInSeconds);
        this.shows.put(showId, show);
        return show;
    }

    private List<Show> getShowsForScreen(final Screen screen) {
        final List<Show> response = new ArrayList<>();
        for (Show show : shows.values()) {
            if (show.getScreen().equals(screen)) {
                response.add(show);
            }
        }
        return response;
    }

    private boolean checkIfShowCreationAllowed(final Screen screen, final Date startTime, final Integer durationInSeconds) {
        // TODO: Implement this. This method will return whether the screen is free at a particular time for
        // specific duration. This function will be helpful in checking whether the show can be scheduled in that slot
        // or not.
        return true;
    }
}

class TheatreService {

    private final Map<String, Theatre> theatres;
    private final Map<String, Screen> screens;
    private final Map<String, Seat> seats;

    public TheatreService() {
        this.theatres = new HashMap<>();
        this.screens = new HashMap<>();
        this.seats = new HashMap<>();
    }

    public Seat getSeat(@NonNull final String seatId) {
        if (!seats.containsKey(seatId)) {
            throw new NotFoundException();
        }
        return seats.get(seatId);
    }

    public Theatre getTheatre(@NonNull final String theatreId) {
        if (!theatres.containsKey(theatreId)) {
            throw new NotFoundException();
        }
        return theatres.get(theatreId);
    }

    public Screen getScreen(@NonNull final String screenId) {
        if (!screens.containsKey(screenId)) {
            throw new NotFoundException();
        }
        return screens.get(screenId);
    }

    public Theatre createTheatre(@NonNull final String theatreName) {
        String theatreId = UUID.randomUUID().toString();
        Theatre theatre = new Theatre(theatreId, theatreName);
        theatres.put(theatreId, theatre);
        return theatre;
    }

    public Screen createScreenInTheatre(@NonNull final String screenName, @NonNull final Theatre theatre) {
        Screen screen = createScreen(screenName, theatre);
        theatre.addScreen(screen);
        return screen;
    }

    public Seat createSeatInScreen(@NonNull final Integer rowNo, @NonNull final Integer seatNo, @NonNull final Screen screen) {
        String seatId = UUID.randomUUID().toString();
        Seat seat = new Seat(seatId, rowNo, seatNo);
        seats.put(seatId, seat);
        screen.addSeat(seat);

        return seat;
    }

    private Screen createScreen(final String screenName, final Theatre theatre) {
        String screenId = UUID.randomUUID().toString();
        Screen screen = new Screen(screenId, screenName, theatre);
        screens.put(screenId, screen);
        return screen;
    }

}

//provider================================================================================================
class InMemorySeatLockProvider implements SeatLockProvider {

    private final Integer lockTimeout; // Bonus feature.
    private final Map<Show, Map<Seat, SeatLock>> locks;

    public InMemorySeatLockProvider(@NonNull final Integer lockTimeout) {
        this.locks = new HashMap<>();
        this.lockTimeout = lockTimeout;
    }

    @Override
    synchronized public void lockSeats(@NonNull final Show show, @NonNull final List<Seat> seats,
                                       @NonNull final String user) {
        for (Seat seat : seats) {
            if (isSeatLocked(show, seat)) {
                throw new SeatTemporaryUnavailableException();
            }
        }

        for (Seat seat : seats) {
            lockSeat(show, seat, user, lockTimeout);
        }
    }

    @Override
    public void unlockSeats(@NonNull final Show show, @NonNull final List<Seat> seats, @NonNull final String user) {
        for (Seat seat: seats) {
            if (validateLock(show, seat, user)) {
                unlockSeat(show, seat);
            }
        }
    }

    @Override
    public boolean validateLock(@NonNull final Show show, @NonNull final Seat seat, @NonNull final String user) {
        return isSeatLocked(show, seat) && locks.get(show).get(seat).getLockedBy().equals(user);
    }

    @Override
    public List<Seat> getLockedSeats(@NonNull final Show show) {
        if (!locks.containsKey(show)) {
            return ImmutableList.of();
        }
        final List<Seat> lockedSeats = new ArrayList<>();

        for (Seat seat : locks.get(show).keySet()) {
            if (isSeatLocked(show, seat)) {
                lockedSeats.add(seat);
            }
        }
        return lockedSeats;
    }

    private void unlockSeat(final Show show, final Seat seat) {
        if (!locks.containsKey(show)) {
            return;
        }
        locks.get(show).remove(seat);
    }

    private void lockSeat(final Show show, final Seat seat, final String user, final Integer timeoutInSeconds) {
        if (!locks.containsKey(show)) {
            locks.put(show, new HashMap<>());
        }
        final SeatLock lock = new SeatLock(seat, show, timeoutInSeconds, new Date(), user);
        locks.get(show).put(seat, lock);
    }

    private boolean isSeatLocked(final Show show, final Seat seat) {
        return locks.containsKey(show) && locks.get(show).containsKey(seat) && !locks.get(show).get(seat).isLockExpired();
    }
}

interface SeatLockProvider {

    void lockSeats(Show show, List<Seat> seat, String user);
    void unlockSeats(Show show, List<Seat> seat, String user);
    boolean validateLock(Show show, Seat seat, String user);

    List<Seat> getLockedSeats(Show show);
}


//exception=================================================================================
class AlreadyExistsException extends RuntimeException {
}
class BadRequestException extends RuntimeException {
}
class InvalidStateException extends RuntimeException {
}
class NotFoundException extends RuntimeException {
}
class ScreenAlreadyOccupiedException extends RuntimeException {
}
class SeatPermanentlyUnavailableException extends RuntimeException {
}
class SeatTemporaryUnavailableException extends RuntimeException {
}



public class BookMyShowLLD_Udit {
}
*/
