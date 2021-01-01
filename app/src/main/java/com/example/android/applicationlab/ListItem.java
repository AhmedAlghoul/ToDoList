package com.example.android.applicationlab;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ListItem  implements Parcelable{
    public String id;
    private String Title;
    private int number;
    private ArrayList<Task> tasks;

    public ListItem() {
    }

    public ListItem(String title) {
        this.Title = title;
        tasks=new ArrayList<>();
        this.id=generateListID();
        this.number=tasks.size();
    }

    protected ListItem(Parcel in) {
        Title = in.readString();
        number = in.readInt();
        tasks = in.createTypedArrayList(Task.CREATOR);
    }

    public static final Creator<ListItem> CREATOR = new Creator<ListItem>() {
        @Override
        public ListItem createFromParcel(Parcel in) {
            return new ListItem(in);
        }

        @Override
        public ListItem[] newArray(int size) {
            return new ListItem[size];
        }
    };

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(Title);
        parcel.writeInt(number);
        parcel.writeTypedList(tasks);
    }
    public static String generateListID(){
        return FirebaseDatabase.getInstance().getReference().child("User").child("list").push().getKey();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
