package com.softserve.utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ReadProjectProperties {

    private Properties properties;
    private final String PROPERTIES_PATH = "src\\main\\resources\\project.properties";

    public ReadProjectProperties() {
        properties = new Properties();
        try {
            properties.load(new FileReader(PROPERTIES_PATH));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int getImplicitWaitDelay() {
        return Integer.parseInt(properties.getProperty("implicit.wait.delay"));
    }

    public int geExplicitWaitDelay() {
        return Integer.parseInt(properties.getProperty("explicit.wait.delay"));
    }

    public String getPlanetaKinoUrl() {
        return properties.getProperty("url.planetakino");
    }

    public String getCabinetPlanetaKinoUrl() {
        return properties.getProperty("url.cabinet.planetakino");
    }
    
    public String getLvivShowtimesUrl() {
        return properties.getProperty("url.cabinet.planetakino.lviv.showtimes");
    }
    
    public String getLvivMoviesUrl() {
        return properties.getProperty("url.cabinet.planetakino.lviv.movies");
    }
    
    public String getFavoriteMoviesUrl() {
        return properties.getProperty("url.cabinet.planetakino.favorite-movies");
    }

}
