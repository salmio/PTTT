package fi.tamk.salmi_oskari.pttt;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * An entity class representing a person-object in the software
 */
public class Person implements Parcelable {

    /* name of object */
    private String name;


    /**
     * A constructor for Person-object
     *
     * @param name String to be set as name
     */
    public Person(String name) {
        this.name = name;

    }


    /**
     * Getter method for name
     *
     * @return String current value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for name
     *
     * @param name String value to be set as name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Tostring method for printing
     */
    @Override
    public String toString() {
        return this.getName();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
    }

    protected Person(Parcel in) {
        this.name = in.readString();
    }

    public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel source) {
            return new Person(source);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
}
