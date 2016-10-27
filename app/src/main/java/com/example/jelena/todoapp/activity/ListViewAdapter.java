package com.example.jelena.todoapp.activity;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.jelena.todoapp.R;
import com.example.jelena.todoapp.activity.db.DBTask;
import com.example.jelena.todoapp.activity.model.Task;
import java.util.List;

/**
 * Created by Jelena on 25/10/2016.
 */
public class ListViewAdapter extends ArrayAdapter<Task>{

    private ListTaskActivity activity;
    private DBTask db;
    private List<Task> taskList;

    public ListViewAdapter(ListTaskActivity context, int resource, List<Task> objects, DBTask db) {

        super(context, resource, objects);
        this.activity = context;
        this.db = db;
        this.taskList = objects;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewData viewData;

        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if(convertView == null) {
            convertView = inflater.inflate(R.layout.item_listview, parent, false);
            viewData = new ViewData(convertView);
            convertView.setTag(viewData);
        } else {
            viewData = (ViewData) convertView.getTag();
        }

        viewData.title.setText(getItem(position).getTitle());

        //Promena kad je task zavrsen
        if(getItem(position).getStatus()==1){
            viewData.title.setTextColor(Color.RED);
        }else {
            viewData.title.setTextColor(Color.GREEN);
        }

        return convertView;
    }

    public static class ViewData {
        private TextView title;

        public ViewData(View v){
            title = (TextView)v.findViewById(R.id.item_title);

        }
    }

}
