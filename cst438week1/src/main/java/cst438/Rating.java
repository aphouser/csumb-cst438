package cst438;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Rating {

  @Autowired
  MovieRepository MovieRepository;

  @GetMapping("/movies/new")
  public String createMovie( Model model) {
    Movie movie = new Movie();
    model.addAttribute("movie", movie);
    return "movie_form";
  }

  @PostMapping("/movies/new")
  public String processMovieForm(@Valid Movie movie,
      BindingResult result,
      Model model) {
    if (result.hasErrors()) {
      return "movie_form";
    }
    MovieRepository.save(movie);
    return "movie_show";
  }

  @GetMapping("/movies")
  public String getAllMovies(Model model) {
    Iterable<Movie> movies = MovieRepository.findAllMovieRatingsOrderByTitleTimeDesc();
    model.addAttribute("movieList", movies);
    return "movie_list";
  }
}