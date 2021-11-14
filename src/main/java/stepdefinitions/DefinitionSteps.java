package stepdefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.softserve.pages.CabinetLoginPage;
import com.softserve.pages.FavoriteMoviesPage;
import com.softserve.pages.MoviesPage;
import com.softserve.pages.ShowtimesPage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DefinitionSteps {

    private CabinetLoginPage cabinetLoginPage;
    private ShowtimesPage showtimesPage;
    private MoviesPage moviesPage;
    private FavoriteMoviesPage favoriteMoviesPage;
    private String[] selectedTop3Film;

    @Before
    public void testsSetUp() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = false;
        Configuration.screenshots = false;
        Configuration.headless = false;
        Configuration.browser = Browsers.CHROME;
    }

    @After
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().close();
        }
    }

    protected static WebDriver getDriver() {
        return WebDriverRunner.getSelenideDriver().getWebDriver();
    }

    @Given("User is on the Login page")
    public void userOnTheLoginPage() {
        cabinetLoginPage = new CabinetLoginPage();
    }

    @And("User checks URL address")
    public void checkUrlAddress() {
        Assert.assertTrue(cabinetLoginPage.getCurrentUrl().equalsIgnoreCase("https://cabinet.planetakino.ua/auth"));
    }

    @Then("User login to app")
    public void userLoginToApp() {
        cabinetLoginPage.login();
        Assert.assertTrue(cabinetLoginPage.getCurrentUrl().equalsIgnoreCase("https://cabinet.planetakino.ua/"));
    }
    
    @And("User navigates to Showtimes page")
    public void userOpensShowtimesPage() {
        showtimesPage = cabinetLoginPage.openShowtimesPage();
    }
    
    @And("User checks if {string} technology is presents")
    public void userCheckIsTechnologypresents(final String technology) {
        String errorMessage = technology.concat(" Technology does not present");
        Assert.assertTrue(showtimesPage.isTechnologyPresents(technology), errorMessage);
    }
    
    @When("User navigates to Movies page")
    public void userOpensMoviesPage() {
        moviesPage = cabinetLoginPage.openMoviesPage();
    }
    
    @And("User add Top 3 films to Favorites")
    public void userAddTop3FilmsToFavorites() {
        selectedTop3Film = moviesPage.getTop3FilmNames(moviesPage.getMoviesList());
        moviesPage.addToSelectedFilmsToFavorites(selectedTop3Film);
    }
    
    @Then("User navigates to FavoriteMovies page")
    public void userOpensFavoriteMoviesPage() {
        favoriteMoviesPage = moviesPage.openFavoriteMoviesPage();
    }
    
    @And("User checks if all selected films were added")
    public void userCheckIsSelectedFilmsInFavorites() {
        Assert.assertTrue(favoriteMoviesPage.getNimberOfAddedMovies()==3, "The number of movies do not equil added movies");
        Assert.assertTrue(favoriteMoviesPage.isFavoriteMoviesPresent(selectedTop3Film), "Not all selected movies present on the Favorites");
    }
}
