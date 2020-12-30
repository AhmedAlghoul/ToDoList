package com.example.android.applicationlab;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ListItem  implements Parcelable{
    private String id;
    private String Title;
    private int number;
    private ArrayList<Task> Tasks;

    public ListItem() {
    }

    public ListItem(String title) {
        this.Title = title;
//        this.id=generateListID();
        this.number=0;
    }

    protected ListItem(Parcel in) {
        Title = in.readString();
        number = in.readInt();
        Tasks = in.createTypedArrayList(Task.CREATOR);
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
        return Tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        Tasks = tasks;
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
        parcel.writeTypedList(Tasks);
    }
    public static String generateListID(){
        return FirebaseDatabase.getInstance().getReference().child("User").child("list").push().getKey();
    }
}
