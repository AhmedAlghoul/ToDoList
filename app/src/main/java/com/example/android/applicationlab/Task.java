package com.example.android.applicationlab;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.FirebaseDatabase;

public class Task implements Parcelable{
    private ListItem listItem;
    private String title;
    private String body;
    private String date;
    private String id;

    public Task() {
    }

    public Task(String title, String body, String date) {
//        this.id=generateTaskID();
        this.title = title;
        this.body = body;
        this.date = date;
    }


    protected Task(Parcel in) {
        listItem = in.readParcelable(ListItem.class.getClassLoader());
        title = in.readString();
        body = in.readString();
        date = in.readString();
        id = in.readString();
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ListItem getListItem() {
        return listItem;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setListItem(ListItem listItem) {
        this.listItem = listItem;
    }

    public static String generateTaskID(){
        return FirebaseDatabase.getInstance().getReference().child("User").child("task").push().getKey();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(listItem, i);
        parcel.writeString(title);
        parcel.writeString(body);
        parcel.writeString(date);
        parcel.writeString(id);
    }
}
