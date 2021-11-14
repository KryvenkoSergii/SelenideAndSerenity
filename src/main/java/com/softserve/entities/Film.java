package com.softserve.entities;

import java.util.HashMap;
import java.util.Map;

public class Film {

    private String name;
    private Map<String, Integer> technologies;
    private String ageRank;

    public Film() {
        this.name = "";
        this.technologies = new HashMap<String, Integer>();
        this.ageRank = "";
    }

    public Film(String name, Map<String, Integer> technologies, String ageRank) {
        this.name = name;
        this.technologies = technologies;
        this.ageRank = ageRank;
    }

    public String getName() {
        return name;
    }

    public Map<String, Integer> getTechnologies() {
        return technologies;
    }

    public String getAgeRank() {
        return ageRank;
    }

    public int getNumberTechnologies() {
        return technologies.size();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTechnologies(Map<String, Integer> technologies) {
        this.technologies = technologies;
    }

    public void setAgeRank(String ageRank) {
        this.ageRank = ageRank;
    }

    @Override
    public String toString() {
        return "Film [name=" + name + ", technologies=" + technologies + ", ageRank=" + ageRank + "]";
    }

}
