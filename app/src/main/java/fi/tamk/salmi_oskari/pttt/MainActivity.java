package fi.tamk.salmi_oskari.pttt;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Main activity for the application
 */
public class MainActivity extends AppCompatActivity {

    /* Local storage for all projects */
    ArrayList<Project> allProjects = new ArrayList<>();

    /* adapter for listview */
    private ArrayAdapter<Project> adapter;

    /* test data for projects */
    ArrayList<ProjectTask> testTasks1 = new ArrayList<>();
    ArrayList<ProjectTask> testTasks2 = new ArrayList<>();
    ArrayList<ProjectTask> testTasks3 = new ArrayList<>();

    /**
     * Variable to store users chosen project's index
     */
    private int chosenIndex;

    private SharedPreferences prefs;


    /**
     * Lifecycle method onCreate
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        generateTestData();


        ListView projectListView = (ListView) findViewById(R.id.projectListView);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, allProjects);
        projectListView.setAdapter(adapter);
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
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Project temp = (Project) parent.getItemAtPosition(position);
                chosenIndex = position;


                Intent i = new Intent(getApplicationContext(), SingleProjectView.class);

                // pass project-object to other activity for modification
                i.putExtra("passedObject", temp);

                startActivityForResult(i, 1);
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


        // save users choice so can modify the correct project later
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

        // restore users choice from bundle, so can modify correct project
        outState.putInt("index", chosenIndex);
        super.onSaveInstanceState(outState);
    }


    /**
     * Method for generating test data to use for development purposes
     */
    private void generateTestData() {
        testTasks1.add(new ProjectTask("debuggign", 10));
        testTasks1.add(new ProjectTask("lazing around", 666));

        testTasks2.add(new ProjectTask("visual design", 6));
        testTasks2.add(new ProjectTask("background research", 23));
        testTasks2.add(new ProjectTask("documentation", 4));

        testTasks3.add(new ProjectTask("stuff", 6766));
        testTasks3.add(new ProjectTask("sleeping", 2));
        testTasks3.add(new ProjectTask("boxing", 13.5f));

        allProjects.add(new Project("testOne", "desc1", testTasks1));
        allProjects.add(new Project("testTwo", "desc2", testTasks2));
        allProjects.add(new Project("testThree", "desc3", testTasks3));

    }


}