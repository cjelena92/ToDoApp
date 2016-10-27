package com.example.jelena.todoapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.jelena.todoapp.R;
import com.example.jelena.todoapp.activity.db.DBTask;
import com.example.jelena.todoapp.activity.model.Task;

public class DisplayDataActivity extends AppCompatActivity {

    TextView showTitle;
    TextView showDescription;
    String id=null;
    DBTask db;
    Button btnDelete;
    Button btnDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);

        showTitle = (TextView) findViewById(R.id.showtitle);
        showDescription = (TextView) findViewById(R.id.showdescription);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnDone = (Button) findViewById(R.id.btnDone);
        db = new DBTask(this);

        id = getIntent().getStringExtra("id");

        final String status = getIntent().getStringExtra("status");

        final String title = getIntent().getStringExtra("title");

        final String description = getIntent().getStringExtra("description");

        showTitle.setText(title);
        showDescription.setText(description);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteTask(Integer.parseInt(id));
                Intent i=new Intent(DisplayDataActivity.this,ListTaskActivity.class);
                startActivity(i);
            }
        });

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Task task = new Task();
                task.setId(Integer.parseInt(id));
                task.setTitle(title);
                task.setDescription(description);
                task.setStatus(1);
                db.updateTask(task);
                Toast.makeText(DisplayDataActivity.this, "Done!", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(DisplayDataActivity.this,ListTaskActivity.class);
                startActivity(i);
            }
        });
    }


}
