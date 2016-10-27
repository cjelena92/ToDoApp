package com.example.jelena.todoapp.activity.model;

/**
 * Created by Jelena on 19/10/2016.
 */

public class Task {
    private int id;
    private String title;
    private String description;
    private int status;

    public Task(){
        this.title = null;
        this.description = null;
        this.status = 0;
    }
    public Task(String title, String description, int status) {
        super();

        this.title = title;
        this.description = description;
        this.status = status;
    }

    public Task(int id,String title, String description, int status) {
        super();
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;

    }


}

