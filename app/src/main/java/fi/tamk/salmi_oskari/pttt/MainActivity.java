package fi.tamk.salmi_oskari.pttt;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Main activity for the application
 */
public class MainActivity extends AppCompatActivity {


    /**
     * Local storage for all projects
     */
    ArrayList<Project> allProjects = new ArrayList<>();


    /**
     * ArrayAdapater for projects to be used with a listview
     */
    private ArrayAdapter<Project> projectAdapter;

    /* test data for projects */
    ArrayList<ProjectTask> testTasks1 = new ArrayList<>();
    ArrayList<ProjectTask> testTasks2 = new ArrayList<>();
    ArrayList<ProjectTask> testTasks3 = new ArrayList<>();
    ArrayList<Person> testPersonData = new ArrayList<>();

    /**
     * Variable to store users chosen project's index
     */
    private int chosenIndex;


    /**
     * Lifecycle method onCreate
     *
     * @param savedInstanceState Bundle to save local data to
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // generate testdata on first startup
        if (savedInstanceState == null) {
            // generateTestData();
        }


        // Restore state of allProjets
        if (savedInstanceState != null) {
            allProjects = savedInstanceState.getParcelableArrayList("allProjects");
        }


        // put contents of allProjects to listview visible
        ListView projectListView = (ListView) findViewById(R.id.projectListView);
        projectAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, allProjects);
        projectListView.setAdapter(projectAdapter);
        projectListView.setOnItemClickListener(ListListener());

    }


    /**
     * OnItemClickListener to listen for user input inside listview
     *
     * @return a new OnItemClickListener
     */
    @NonNull
    private AdapterView.OnItemClickListener ListListener() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                final Project temp = (Project) parent.getItemAtPosition(position);
                chosenIndex = position;


                AlertDialog.Builder projectAlert = new AlertDialog.Builder(MainActivity.this);
                projectAlert.setTitle(allProjects.get(position).getTitle());


                // dialog stuff for projects
                projectAlert.setPositiveButton("View", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        Intent i = new Intent(MainActivity.this, SingleProjectView.class);

                        // pass project-object to other activity for modification
                        i.putExtra("passedObject", temp);
                        startActivityForResult(i, 1);

                    }
                });


                projectAlert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });


                projectAlert.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // CONfirmation dialog for deletion

                        AlertDialog.Builder deleteAlert = new AlertDialog.Builder(MainActivity.this);
                        deleteAlert.setTitle("Are you sure you want to delete project: "
                                + allProjects.get(position).getTitle() + " ?");


                        deleteAlert.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                allProjects.remove(position);
                                projectAdapter.notifyDataSetChanged();
                            }
                        });


                        deleteAlert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });

                        deleteAlert.show();
                    }
                });

                projectAlert.show();

            }
        };
    }

    /**
     * Listener method for activityresult gained from SingleProjectView
     *
     * @param requestCode requestcode that was received
     * @param resultCode  resultcode that was received
     * @param data        intent sent from activity
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        // update project to match user's added tasks
        allProjects.set(chosenIndex, (Project) data.getExtras().get("project"));

    }


    /**
     * Listener method for InstanceState restore
     *
     * @param savedInstanceState saved instance state that was restored
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        // restore users choice from bundle, so can modify correct project
        chosenIndex = savedInstanceState.getInt("index");

        super.onRestoreInstanceState(savedInstanceState);
    }

    /**
     * Listener method for saving instance state
     *
     * @param outState state that wil be saved
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {


        // save users choice so can modify the correct project later
        outState.putInt("index", chosenIndex);
        outState.putParcelableArrayList("allProjects", allProjects);

        super.onSaveInstanceState(outState);
    }


    /**
     * Method for generating test data to use for development purposes
     */
    private void generateTestData() {

        testPersonData.add(new Person("pekka"));
        testPersonData.add(new Person("tauno"));
        testPersonData.add(new Person("kerttu"));


        testTasks1.add(new ProjectTask("debuggign", 10));
        testTasks1.add(new ProjectTask("lazing around", 666));

        testTasks2.add(new ProjectTask("visual design", 6));
        testTasks2.add(new ProjectTask("background research", 23));
        testTasks2.add(new ProjectTask("documentation", 4));

        testTasks3.add(new ProjectTask("stuff", 6766));
        testTasks3.add(new ProjectTask("sleeping", 2));
        testTasks3.add(new ProjectTask("boxing", 13.5f));


        allProjects.add(new Project("testOne", "desc1", testTasks1, testPersonData));
        allProjects.add(new Project("testTwo", "desc2", testTasks2, testPersonData));
        allProjects.add(new Project("testThree", "desc3", testTasks3, testPersonData));


    }


    /**
     * Method for creating a new project
     *
     * @param view View which invoked the method
     */
    public void addProject(View view) {

        // customize alert dialog
        LinearLayoutCompat layout = new LinearLayoutCompat(this);
        layout.setOrientation(LinearLayoutCompat.VERTICAL);

        final EditText editTextOne = new EditText(this);
        editTextOne.setHint("Title");

        final EditText editTextTwo = new EditText(this);
        editTextTwo.setHint("Description");

        layout.addView(editTextOne);
        layout.addView(editTextTwo);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("New Project");
        alert.setView(layout);

        alert.setPositiveButton("add", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {


                String titleText;
                String descriptionText;


                if (!TextUtils.isEmpty(editTextOne.getText())) {
                    titleText = editTextOne.getText().toString();
                    descriptionText = editTextTwo.getText().toString();
                    allProjects.add(new Project(titleText, descriptionText));
                    projectAdapter.notifyDataSetChanged();

                }
            }
        });

        alert.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        });

        alert.show();


    }
}