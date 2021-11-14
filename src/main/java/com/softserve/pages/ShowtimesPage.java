package com.softserve.pages;

import static com.codeborne.selenide.Selenide.$$;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.codeborne.selenide.SelenideElement;
import com.softserve.entities.Film;

public class ShowtimesPage extends BasePage {
    
    private List<SelenideElement> showtimesElements = $$("section.movies div.movie");

    public ShowtimesPage() {
        super(readProjectProperties.getLvivShowtimesUrl());
        logger.info("open Showtimes Page");
        wait.awaitFor(() -> driver.getCurrentUrl().equalsIgnoreCase("https://planetakino.ua/lvov2/showtimes/#today"));
        wait.awaitFor(() -> showtimesElements.size()>0);
    }
    
    public Map<String, Integer> getShowtimesMap(){
        logger.info("start getShowtimesMap()");
        return getFilmList().stream().collect(Collectors.toMap(Film::getName, Film::getNumberTechnologies));
    }
    
    public List<Film> getFilmList(){
        logger.info("start getFilmList()");
        List<Film> films = new ArrayList<Film>();
        for (SelenideElement selenideElement : showtimesElements) {
            String filmName = selenideElement.$("section.movie-info>a").getText();
            Map<String, Integer> technologies = new HashMap<String, Integer>();
            for (SelenideElement technology : selenideElement.$$("section.movie-info div.tech")) {
                String name = technology.$("span").getText();
                int numberOfSeances = technology.$$("div.seances>app-seance-chips").size();
                technologies.put(name, numberOfSeances);
            }
            String ageRank = selenideElement.$("div.movie div.movie__agelimit").getText();
            films.add(new Film(filmName, technologies, ageRank));
        }
        return films;
    }
    
    public boolean isTechnologyPresents(String technology) {
        logger.info("start isTechnologyPresents()");
        boolean is4dxPresents = false;
        for (Film film : openShowtimesPage().getFilmList()) {
//            System.out.println(film.toString());
            for (String key : film.getTechnologies().keySet()) {
                if (key.contains("4DX")) {
                    is4dxPresents = true;
                }
            }
        }
        return is4dxPresents;
    }
}
