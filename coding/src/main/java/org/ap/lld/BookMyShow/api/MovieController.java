package org.ap.lld.BookMyShow.api;


import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.ap.lld.BookMyShow.services.MovieService;

@AllArgsConstructor
public class MovieController {
    final private MovieService movieService;

    public String createMovie(@NonNull final String movieName) {
        return movieService.createMovie(movieName).getId();
    }

}
