package fi.tamk.salmi_oskari.pttt;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;


import java.util.ArrayList;

/**
 * An activity for a view of a single project item
 */
public class SingleProjectView extends AppCompatActivity {

    /**
     * variable containing all of the current chosen projects tasks
     */
    ArrayList<ProjectTask> allTasks;

    /**
     * variable containing all of the current persons assigned to project
     */
    ArrayList<Person> allPersons = new ArrayList<>();


    /**
     * ArrayAdapter to use with task listview
     */
    private ArrayAdapter<ProjectTask> projectTaskAdapter;


    /**
     * ArrayAdapter to use with person listview
     */
    private ArrayAdapter<Person> personAdapter;

    /**
     * Current project that was chosen
     */
    Project project;


    /**
     * Charsequence array to help using names
     */
    CharSequence[] csNames;


    /**
     * items currently selected in dialog
     */
    boolean[] selectedItems;


    /**
     * Lifecycle method onCreate
     *
     * @param savedInstanceState saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_project_view);


        receiveData();


        selectedItems = new boolean[allPersons.size()];

    }

    /**
     * Lifecycle method onPause
     */
    @Override
    protected void onPause() {


        Intent returnIntent = new Intent();

        project.setTasks(allTasks);
        project.setPersons(allPersons);
        // pass modified project to main activity
        returnIntent.putExtra("project", project);
        setResult(1, returnIntent);

        super.onPause();


    }


    /**
     * Listener method for the back-button
     */
    @Override
    public void onBackPressed() {

        Intent returnIntent = new Intent();

        // pass modified project to main activity
        returnIntent.putExtra("project", project);
        setResult(1, returnIntent);

        super.onBackPressed();
    }

    /**
     * Helper method to get tasks from intent and allocate them to arraylist and listview
     */
    private void receiveData() {


        Intent i = getIntent();
        project = (Project) i.getParcelableExtra("passedObject");
        allTasks = project.getTasks();
        allPersons = project.getPersons();
        ListView taskListView = (ListView) findViewById(R.id.taskListView);
        ListView personListView = (ListView) findViewById(R.id.personListView);


        setTitle(project.getTitle());

        projectTaskAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, allTasks);

        // initialize personadapter, aswell
        personAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, allPersons);

        taskListView.setAdapter(projectTaskAdapter);
        personListView.setAdapter(personAdapter);

        taskListView.setOnItemClickListener(personListener());

    }


    /**
     * Listener method for button to add a new task and create an alertdialog
     *
     * @param view the view that invoked method
     */
    public void addTask(View view) {

        // customize alert dialog
        LinearLayoutCompat layout = new LinearLayoutCompat(this);
        layout.setOrientation(LinearLayoutCompat.VERTICAL);

        final EditText editTextOne = new EditText(this);
        editTextOne.setHint("Title");

        final EditText editTextTwo = new EditText(this);
        editTextTwo.setHint("Time");
        editTextTwo.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

        layout.addView(editTextOne);
        layout.addView(editTextTwo);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("New Task");
        alert.setView(layout);

        alert.setPositiveButton("add", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                float timeFloat = 0;

                String titleText;

                if (!TextUtils.isEmpty(editTextTwo.getText())) {
                    timeFloat = Float.parseFloat(editTextTwo.getText().toString());
                }

                if (!TextUtils.isEmpty(editTextOne.getText()) || timeFloat != 0) {
                    titleText = editTextOne.getText().toString();

                    ProjectTask toAdd = new ProjectTask(titleText, timeFloat);
                    toAdd.setSelectedItems(selectedItems);
                    allTasks.add(toAdd);
                    projectTaskAdapter.notifyDataSetChanged();

                }
            }
        });

        alert.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        });

        alert.show();

    }

    /**
     * Listener method for button to add a new person to project and create an alertdialog
     *
     * @param view view which invoked the method
     */
    public void addPerson(View view) {
        // customize alert dialog
        LinearLayoutCompat layout = new LinearLayoutCompat(this);
        layout.setOrientation(LinearLayoutCompat.VERTICAL);

        final EditText editTextOne = new EditText(this);
        editTextOne.setHint("Name");

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("New Person");
        alert.setView(layout);

        layout.addView(editTextOne);


        alert.setPositiveButton("add", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {


                String nameText;


                if (!TextUtils.isEmpty(editTextOne.getText())) {
                    nameText = editTextOne.getText().toString();
                    allPersons.add(new Person(nameText));

                    increaseSelectedSize();

                    personAdapter.notifyDataSetChanged();

                }
            }
        });

        alert.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        });

        alert.show();
    }


    @NonNull
    private AdapterView.OnItemClickListener personListener() {


        return new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {


                ArrayList<String> names = new ArrayList<>();


                for (int i = 0; i < allPersons.size(); i++) {
                    names.add(allPersons.get(i).getName());
                }


                csNames = names.toArray(new CharSequence[allTasks.size()]);
                AlertDialog.Builder personAlert = new AlertDialog.Builder(SingleProjectView.this);
                personAlert.setTitle("Modify persons in task");



                selectedItems = allTasks.get(position).getSelectedItems();


                personAlert.setMultiChoiceItems(csNames, selectedItems, new DialogInterface.OnMultiChoiceClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int index, boolean isChecked) {


                        if (isChecked) {

                            selectedItems[index] = true;

                        } else if (!isChecked) {

                            selectedItems[index] = false;

                        }
                    }
                });

                personAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        // add persons chosen to current task
                        for (int i = 0; i < selectedItems.length; i++) {
                            if (selectedItems[i] == true &&
                                    !allTasks.get(position).getPersons().contains(allPersons.get(i))) {

                                allTasks.get(position).addPerson(allPersons.get(i));
                                projectTaskAdapter.notifyDataSetChanged();

                            } else if (selectedItems[i] != true) {

                                allTasks.get(position).removePerson(allPersons.get(i));
                                projectTaskAdapter.notifyDataSetChanged();
                            }
                        }

                        allTasks.get(position).setSelectedItems(selectedItems);


                    }
                });


                personAlert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

                personAlert.show();

            }
        };
    }


    /**
     * Method for refactoring a primitive array to increase it's size by 1
     */
    public void increaseSelectedSize() {


        // refactoring of array to increase size by 1
        // TODO implement arraylist to replace
        boolean[] tmp = selectedItems;
        selectedItems = new boolean[selectedItems.length + 1];

        for (int i = 0; i < tmp.length; i++) {
            selectedItems[i] = tmp[i];
        }

        for (int i = 0; i < allTasks.size(); i++) {

            boolean[] temp = allTasks.get(i).getSelectedItems();

            boolean[] selectedTemp = new boolean[temp.length + 1];

            for (int b = 0; b < temp.length; b++) {
                selectedTemp[i] = temp[i];
            }

            allTasks.get(i).setSelectedItems(selectedTemp);

        }

    }
}
