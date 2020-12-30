package com.example.android.applicationlab;

import java.util.ArrayList;

class ListItem {

    String Title;
    String Tasks;

    public ListItem() {
    }

    public ListItem(String title,String tasks) {

        Title = title;
        Tasks=tasks;
    }


    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getTasks() {
        return Tasks;
    }

    public void setTasks(String tasks) {
        Tasks = tasks;
    }
}
