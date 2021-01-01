package com.example.android.applicationlab;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.FirebaseDatabase;

public class Task implements Parcelable {
    public String listId;
    private String title;
    private String body;
    private String date;
    String id;

    public Task() {
        this.id = generateTaskID();
    }

    public Task(String listId) {
        this.id = generateTaskID();
        this.listId=listId;
    }


    protected Task(Parcel in) {
        listId = in.readString();
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

    public String getListId() {
        return listId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setListId(String listId) {
        this.listId = listId;
    }

    public static String generateTaskID() {
        return FirebaseDatabase.getInstance().getReference().child("User").child("task").push().getKey();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(listId);
        parcel.writeString(title);
        parcel.writeString(body);
        parcel.writeString(date);
        parcel.writeString(id);
    }
}