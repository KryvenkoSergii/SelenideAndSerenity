package com.softserve.pages;

import static com.codeborne.selenide.Selenide.$$;

import java.util.Arrays;
import java.util.List;

import com.codeborne.selenide.SelenideElement;

public class FavoriteMoviesPage extends BasePage {
    
    private List<SelenideElement> favoriteMovieElements = $$("div.content div.masonry>div.masonry__item");

    public FavoriteMoviesPage() {
        super(readProjectProperties.getFavoriteMoviesUrl());
        logger.info("open Favorite Movies Page");
        wait.awaitFor(() -> driver.getCurrentUrl().contains("/profile/favorite-movies"));
    }
    
    public int getNimberOfAddedMovies() {
        return favoriteMovieElements.size();
    }
    
    public boolean isFavoriteMoviesPresent(String[] favoriteMovieNames) {
        int matches = 0;
        for (SelenideElement favoriteMovieElement : favoriteMovieElements) {
            SelenideElement title = favoriteMovieElement.$("div.masonry__movie-title");
            if (Arrays.asList(favoriteMovieNames).contains(title.getText().trim())) {
//                System.out.println(title.getText());
                matches++;
            }
        }
        return matches == favoriteMovieNames.length ? true : false;
    }

}
