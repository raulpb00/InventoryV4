package es.raulprieto.inventory.data.db.model;


import androidx.annotation.NonNull;

import java.io.Serializable;

public class Dependency implements Serializable {
    public static final String TAG = "dependency";

    private String name;
    private String shortName;
    private String description;
    private String inventory;
    private String uriImage;

    public Dependency(String name, String shortName, String description, String inventory, String uriImage) {
        this.name = name;
        this.shortName = shortName;
        this.inventory = inventory;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInventory() {
        return inventory;
    }

    public void setInventory(String inventory) {
        this.inventory = inventory;
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
