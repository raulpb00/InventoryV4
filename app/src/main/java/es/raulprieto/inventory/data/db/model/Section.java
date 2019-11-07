package es.raulprieto.inventory.data.db.model;

import androidx.annotation.NonNull;

public class Section {
    private String name;
    private String shortName;
    private String dependency; // Dependency shortname, change to Dependency object
    private String description;
    private String uriImage;

    public Section(String name, String shortName, String dependency, String description, String uriImage) {
        this.name = name;
        this.shortName = shortName;
        this.dependency = dependency;
        this.description = description;
        this.uriImage = uriImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getDependency() {
        return dependency;
    }

    public void setDependency(String dependency) {
        this.dependency = dependency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUriImage() {
        return uriImage;
    }

    public void setUriImage(String uriImage) {
        this.uriImage = uriImage;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
