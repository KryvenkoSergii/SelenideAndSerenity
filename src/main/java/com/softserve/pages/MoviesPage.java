package com.softserve.pages;

import static com.codeborne.selenide.Selenide.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.codeborne.selenide.SelenideElement;
import com.softserve.utils.MapUtil;

public class MoviesPage extends BasePage {

    private List<SelenideElement> moviesElements = $$(
            "div[class='content__section movies__section'] div.tile-list-wr>div.movie-block");
    private SelenideElement closePopUpAddedToWishlist = $(
            "div.pp-block.popup-movies-wishlist-subscribed>div.modal-dialog.modal-md.vertical-center button.close.pp-block__close i");

    public MoviesPage() {
        super(readProjectProperties.getLvivMoviesUrl());
        logger.info("open Movies Page");
        wait.awaitFor(() -> driver.getCurrentUrl().contains("/movies/"));
        wait.awaitFor(() -> moviesElements.size() > 0);
    }

    public Map<String, Float> getMoviesList() {
        logger.info("start getMoviesList()");
        Map<String, Float> films = new HashMap<String, Float>();
        for (SelenideElement movieElement : moviesElements) {
            movieElement.hover();
            String name = movieElement.$("div.movie-block__flip-back div.movie-block__title-inner").getText();
            float rating = 0f;
            if (movieElement.$("div.movie-block__flip-back div.movie-block__imdb-rating").exists()) {
                String rat = movieElement.$("div.movie-block__flip-back div.movie-block__imdb-rating").getText();
                rating = Float.parseFloat(rat);
            }
//            System.out.println("name = " + name + "; rating = " + rating);
            films.put(name, rating);
        }
        return films;
    }

    public String[] getTop3FilmNames(Map<String, Float> films) {
        logger.info("start getTop3FilmNames()");
        Map<String, Float> sortedFilms = MapUtil.sortByValueDesc(films);
        int numberSelectedMovies = 0;
        String[] selectedTop3Film = new String[3];
        for (String key : sortedFilms.keySet()) {
//            System.out.println(key + " : " + sortedFilms.get(key));
            selectedTop3Film[numberSelectedMovies] = key;
            numberSelectedMovies++;
            if (numberSelectedMovies == 3) {
                break;
            }
        }
        return selectedTop3Film;
    }

    public void addToSelectedFilmsToFavorites(String[] selectedTop3Film) {
        logger.info("start selectTop3FilmsAndAddToFavorites()");
        //
        for (SelenideElement movieElement : moviesElements) {
//            movieElement.scrollTo();
            movieElement.hover();
            SelenideElement title = movieElement.$("div.movie-block__flip-back div.movie-block__title-inner");
            SelenideElement addToFavorites = movieElement
                    .$("div.movie-block__flip-back>span.movie-block__info-icon-wishlist.movie-block__info-icon_wishlist-pass");
            if (Arrays.asList(selectedTop3Film).contains(title.getText())) {
                addToFavorites.click();
                logger.info("added film " + title.getText() + " to Favorites");
                wait.awaitFor(() -> closePopUpAddedToWishlist.isDisplayed());
                closePopUpAddedToWishlist.click();
//                System.out.println(title.getText());
            }
        }

    }

}
