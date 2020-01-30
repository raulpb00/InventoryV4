package es.raulprieto.inventory.data.db.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Section implements Parcelable {
    @Ignore
    public static final String TAG = "section";

    @NonNull
    private String name;
    @NonNull
    @PrimaryKey
    private String shortName;
    @NonNull
    private String dependency; // Dependency shortname, change to Dependency object
    @NonNull
    private String description;
    @NonNull
    private String uriImage;

    public Section(String name, String shortName, String dependency, String description, String uriImage) {
        this.name = name;
        this.shortName = shortName;
        this.dependency = dependency;
        this.description = description;
        this.uriImage = uriImage;
    }

    protected Section(Parcel in) {
        name = in.readString();
        shortName = in.readString();
        dependency = in.readString();
        description = in.readString();
        uriImage = in.readString();
    }

    public static final Creator<Section> CREATOR = new Creator<Section>() {
        @Override
        public Section createFromParcel(Parcel in) {
            return new Section(in);
        }

        @Override
        public Section[] newArray(int size) {
            return new Section[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(shortName);
        dest.writeString(dependency);
        dest.writeString(description);
        dest.writeString(uriImage);
    }
}
