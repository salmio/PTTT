package fi.tamk.salmi_oskari.pttt;


import java.io.Serializable;
import java.util.ArrayList;


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

    /**
     * ArrayList containing persons included in task
     */
    private ArrayList<Person> persons = new ArrayList<>();

    /**
     * Array of boolean flags indicating if a button is chosen or not
     *
     * TODO implement this better
     */
    boolean[] selectedItems = new boolean[3];


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
     * Getter method for Persons
     *
     * @return ArrayList containing all persons contained in task
     */
    public ArrayList<Person> getPersons() {
        return persons;
    }


    /**
     * Setter method for persons
     *
     * @param persons ArrayList to be set as current Persons
     */
    public void setPersons(ArrayList<Person> persons) {
        this.persons = persons;
    }


    /**
     * Method to add person to projecttask
     *
     * @param person Person to be aded
     */
    public void addPerson(Person person) {
        persons.add(person);
    }

    /**
     * Method to remove a person from the task
     *
     * @param person person object to be removed
     */
    public void removePerson(Person person) {
        persons.remove(person);
    }


    /**
     * Getter method for selected items array
     *
     * @return boolean[] array of selected items
     */
    public boolean[] getSelectedItems() {
        return selectedItems;
    }


    /**
     * Setter method for selected items
     *
     * @param selectedItems array to be set as selected items
     */
    public void setSelectedItems(boolean[] selectedItems) {
        this.selectedItems = selectedItems;
    }

    /**
     * Tostring method for printing
     */
    @Override
    public String toString() {
        if (persons.size() != 0) {
            return getTitle() + ": " + getTime() + "h" + "\n" + persons;

        } else {
            return getTitle() + ": " + getTime() + "h";
        }
    }


}
