package fi.tamk.salmi_oskari.pttt;


import java.io.Serializable;
import java.util.ArrayList;

/**
 * An entity class reprenting a project-object in the software
 */
class Project implements Serializable {

    private String title;
    private String description;
    private float time;
    private ArrayList<ProjectTask> tasks = new ArrayList<>();


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
     * Tostring method for printing
     */
    @Override
    public String toString() {
        return "Title: " + getTitle() + ", Desc: " + getDescription();
    }


}
