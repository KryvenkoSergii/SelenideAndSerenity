package com.softserve.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.softserve.entities.Film;
import com.softserve.pages.CabinetLoginPage;
import com.softserve.pages.FavoriteMoviesPage;
import com.softserve.pages.MoviesPage;
import com.softserve.pages.ShowtimesPage;

public class PlanetaKinoTestSuite extends BaseTest {

    @Test
    public void selectTop3FilmsAndAddToFavorites() {
        CabinetLoginPage cabinetLoginPage = new CabinetLoginPage();
        cabinetLoginPage.login();
        //
        ShowtimesPage showtimesPage = cabinetLoginPage.openShowtimesPage();
        Assert.assertTrue(showtimesPage.isTechnologyPresents("4DX"), "4DX Technology does not present");
        //
        MoviesPage moviesPage = cabinetLoginPage.openMoviesPage();
        String[] selectedTop3Film = moviesPage.getTop3FilmNames(moviesPage.getMoviesList());
        moviesPage.addToSelectedFilmsToFavorites(selectedTop3Film);
        //
        FavoriteMoviesPage favoriteMoviesPage = moviesPage.openFavoriteMoviesPage();
        Assert.assertTrue(favoriteMoviesPage.getNimberOfAddedMovies()==3, "The number of movies do not equil added movies");
        Assert.assertTrue(favoriteMoviesPage.isFavoriteMoviesPresent(selectedTop3Film), "Not all selected movies present on the Favorites");
    }
}
