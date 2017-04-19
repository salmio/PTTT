package fi.tamk.salmi_oskari.pttt;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
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
     * adapter to use with listview
     */
    private ArrayAdapter<ProjectTask> adapter;

    /**
     * Current project that was chosen
     */
    Project project;

    /**
     * Lifecycle method onCreate
     *
     * @param savedInstanceState saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_project_view);


        receiveTasks();

    }

    /**
     * Lifecycle method onPause
     */
    @Override
    protected void onPause() {


        Intent returnIntent = new Intent();

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
    private void receiveTasks() {


        Intent i = getIntent();
        project = (Project) i.getSerializableExtra("passedObject");
        allTasks = project.getTasks();
        ListView taskListView = (ListView) findViewById(R.id.taskListView);

        setTitle(project.getTitle());

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, allTasks);

        taskListView.setAdapter(adapter);

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
        alert.setTitle("New task");
        alert.setView(layout);

        alert.setPositiveButton("add", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                float timeFloat = 0;

                String titleText = "no title";

                if (!TextUtils.isEmpty(editTextTwo.getText())) {
                    timeFloat = Float.parseFloat(editTextTwo.getText().toString());

                }

                if (!TextUtils.isEmpty(editTextOne.getText()) || timeFloat != 0) {
                    titleText = editTextOne.getText().toString();
                    allTasks.add(new ProjectTask(titleText, timeFloat));
                    adapter.notifyDataSetChanged();
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
