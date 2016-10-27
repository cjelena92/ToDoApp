package com.example.jelena.todoapp.activity;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.example.jelena.todoapp.R;
import com.example.jelena.todoapp.activity.db.DBTask;
import com.example.jelena.todoapp.activity.model.Task;
import java.util.ArrayList;
import java.util.List;

public class ListTaskActivity extends AppCompatActivity {

    ListView listView;
    ListViewAdapter adapter;
    DBTask db;
    List<Task> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_task);

        listView = (ListView) findViewById(R.id.listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListTaskActivity.this,DisplayDataActivity.class);
                int idTask = adapter.getItem(position).getId();
                int status = adapter.getItem(position).getStatus();
                String title = adapter.getItem(position).getTitle();
                String description = adapter.getItem(position).getDescription();
                intent.putExtra("id",String.valueOf(idTask));
                intent.putExtra("status",String.valueOf(status));
                intent.putExtra("title",String.valueOf(title));
                intent.putExtra("description",String.valueOf(description));
                startActivity(intent);

            }
        });

        db = new DBTask(this);
        taskList = new ArrayList<>();
        showTask();
    }

    public void showTask(){
        taskList = db.getAllTasks();
        adapter = new ListViewAdapter(this, R.layout.item_listview,taskList,db);
        listView.setAdapter(adapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() ==R.id.add){
            newTaskDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void newTaskDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(ListTaskActivity.this);
        alertDialog.setTitle("Add new task");

        LinearLayout layout = new LinearLayout(this);
        layout.setPadding(10,10,10,10);
        layout.setOrientation(LinearLayout.VERTICAL);

        final EditText title = new EditText(this);
        title.setHint("Title");
        layout.addView(title);

        final EditText description = new EditText(this);
        description.setHint("Description");
        layout.addView(description);

        alertDialog.setView(layout);

        alertDialog.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Task task = new Task(getText(title),getText(description),0);
                db.addTask(task);
                showTask();

            }
        });
        alertDialog.setNegativeButton("Cancel",null);
        alertDialog.show();

    }

    private String getText(TextView textView){
        return textView.getText().toString().trim();
    }

}
