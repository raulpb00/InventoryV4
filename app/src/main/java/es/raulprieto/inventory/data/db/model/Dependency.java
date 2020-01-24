package es.raulprieto.inventory.data.db.model;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Objects;

@Entity
public class Dependency implements Serializable {
    @Ignore
    public static final String TAG = "dependency";

    @NonNull
    private String name;
    @PrimaryKey
    @NonNull
    private String shortName;
    @NonNull
    private String description;
    @NonNull
    private String inventory;
    @NonNull
    private String uriImage;

    @Ignore
    public Dependency() {

    }

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

    @Override
    public int hashCode() {
        return Objects.hash(shortName);
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dependency that = (Dependency) o;

        return shortName.equals(that.shortName);
    }
}
