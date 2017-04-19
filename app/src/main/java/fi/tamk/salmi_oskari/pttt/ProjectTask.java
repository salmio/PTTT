package fi.tamk.salmi_oskari.pttt;


import java.io.Serializable;


/**
 * An entity class reprenting a task-object inside project-object
 */
class ProjectTask implements Serializable {

    /**
     * Current title of task
     */
    private String title;

    /**
     * Current time spent for task
     */
    private float time;

    /* placeholder until person class is implemented */
    private String person;


    /**
     * A constructor for task
     *
     * @param title String to be set as title
     * @param time  float to be set as time
     */
    public ProjectTask(String title, float time) {
        this.title = title;
        this.time = time;
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
     * @param title String to be set as title
     */
    public void setTitle(String title) {
        this.title = title;
    }


    /**
     * Getter method for time
     *
     * @return float current time spent
     */
    public float getTime() {
        return time;
    }


    /**
     * Setter method for time
     *
     * @param time float to set as time
     */
    public void setTime(float time) {
        this.time = time;
    }


    /**
     * Tostring method for printing
     */
    @Override
    public String toString() {
        return getTitle() + ": " + getTime() + "h";
    }
}
