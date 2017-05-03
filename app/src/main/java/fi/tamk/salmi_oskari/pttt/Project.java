package fi.tamk.salmi_oskari.pttt;


import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * An entity class representing a project-object in the software
 */
class Project implements Parcelable {

    /**
     * Title for project
     */
    private String title;

    /**
     * description for project
     */
    private String description;

    /**
     * Time spent on project
     */
    private float time;

    /**
     * ArrayList containing all tasks assigned to project
     */
    private ArrayList<ProjectTask> tasks = new ArrayList<>();

    /**
     * ArrayList containing all persons assigned to project
     */
    private ArrayList<Person> persons = new ArrayList<>();



    /**
     * A constructor for Project-object
     *
     * @param title String to set as title
     * @param description String to be set as description
     */
    public Project(String title, String description) {
        this.title = title;
        this.description= description;
    }



    /**
     * A constructor for Project-object
     *
     * @param title string to set as title
     * @param time  float -value to set as time
     */
    public Project(String title, float time) {
        this.title = title;
        this.time = time;
    }

    /**
     * A constructor for Project-object
     *
     * @param title string to set as title
     * @param time  float -value to set as time
     * @param tasks an ArrayList of tasks to set
     */
    public Project(String title, float time, ArrayList<ProjectTask> tasks) {
        this.title = title;
        this.time = time;
        this.tasks = tasks;
    }

    /**
     * A constructor for Project-object
     *
     * @param title       string to set as title
     * @param description string to set as description
     * @param time        float -value to set as time
     * @param tasks       an ArrayList of tasks to set
     */
    public Project(String title, String description, float time, ArrayList<ProjectTask> tasks) {
        this.title = title;
        this.description = description;
        this.time = time;
        this.tasks = tasks;
    }


    /**
     * A constructor for Project-object
     *
     * @param title       string to set as title
     * @param description string to set as description
     * @param tasks       an ArrayList of tasks to set
     */
    public Project(String title, String description, ArrayList<ProjectTask> tasks) {
        this.title = title;
        this.description = description;
        this.tasks = tasks;
    }

    /**
     * A constructor for Project-object
     *
     * @param title string to set as title
     * @param description string to set as description
     * @param tasks an ArrayList of tasks to set
     * @param persons an ArayList to be set as persons
     */
    public Project(String title, String description, ArrayList<ProjectTask> tasks, ArrayList<Person> persons) {
        this.title = title;
        this.description = description;
        this.tasks = tasks;
        this.persons = persons;
    }

    /**
     * Getter method for title
     *
     * @return String current title
     */
    public String getTitle() {
        return title;
    }


    /**
     * Setter method for title
     *
     * @param title string to set as title
     */
    public void setTitle(String title) {
        this.title = title;
    }


    /**
     * Getter method for description
     *
     * @return String current description
     */
    public String getDescription() {
        return description;
    }


    /**
     * Setter method for description
     *
     * @param description String to set as description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter method for time
     *
     * @return float current time
     */
    public float getTime() {
        return time;
    }

    /**
     * Setter method for time
     *
     * @param time float time to be set
     */
    public void setTime(float time) {
        this.time = time;
    }


    /**
     * Getter method for tasks
     *
     * @return ArrayList of current tasks
     */
    public ArrayList<ProjectTask> getTasks() {
        return tasks;
    }


    /**
     * Setter method for tasks
     *
     * @param tasks an ArrayList of tasks to be set
     */
    public void setTasks(ArrayList<ProjectTask> tasks) {
        this.tasks = tasks;
    }


    /**
     * A method to add a singular task to project
     *
     * @param task a ProjectTask object to add
     */
    public void addTask(ProjectTask task) {
        tasks.add(task);
    }


    /**
     * Getter method for persons
     *
     * @return arraylist containing persons
     */
    public ArrayList<Person> getPersons() {
        return persons;
    }

    /**
     * Setter method for persons
     *
     * @param persons arraylist to set as persons
     */
    public void setPersons(ArrayList<Person> persons) {
        this.persons = persons;
    }

    /**
     * Tostring method for printing
     */
    @Override
    public String toString() {
        return "Title: " + getTitle() + "\n     Desc: " + getDescription();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeFloat(this.time);
        dest.writeList(this.persons);
        dest.writeList(this.tasks);
    }

    protected Project(Parcel in) {
        this.title = in.readString();
        this.description = in.readString();
        this.time = in.readFloat();
        this.persons = new ArrayList<Person>();
        in.readList(this.persons, Person.class.getClassLoader());
        this.tasks = new ArrayList<ProjectTask>();
        in.readList(this.tasks, ProjectTask.class.getClassLoader());
    }

    public static final Parcelable.Creator<Project> CREATOR = new Parcelable.Creator<Project>() {
        @Override
        public Project createFromParcel(Parcel source) {
            return new Project(source);
        }

        @Override
        public Project[] newArray(int size) {
            return new Project[size];
        }
    };
}
