package ru.veprev.avynotes;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;


public class NotesStructure implements Parcelable {

    private static final Random random = new Random();
    private static NotesStructure[] notes;
    static ArrayList<NotesStructure> listOfNotes;
    private static int counter;

    private int id;


    private String name;
    private String description;
    private LocalDateTime date;

    public NotesStructure(String name, String description, LocalDateTime date) {
        this.name = name;
        this.description = description;
        this.date = date;
    }

    {
        id = ++counter;
    }

    public static final Creator<NotesStructure> CREATOR = new Creator<NotesStructure>() {
        @Override
        public NotesStructure createFromParcel(Parcel in) {
            return new NotesStructure(in);
        }

        @Override
        public NotesStructure[] newArray(int size) {
            return new NotesStructure[size];
        }
    };


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public static NotesStructure[] getNotes(){
        return notes;
    }

    public static ArrayList<NotesStructure> getAllNotes(){
        return listOfNotes;
    }


    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    static {
        notes = new NotesStructure[10];
        for (int i = 0; i < notes.length; i++) {
            notes[i] = NotesStructure.getNote(i);
        }
    }

//    static {
//        listOfNotes = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            listOfNotes.add(getNote(i));
//        }
//    }

    @SuppressLint("DefaultLocale")
    public static NotesStructure getNote(int index){
        String name = String.format("Заметка %d", index + 1);
        String description = String.format("Описание заметки %d", index + 1);
        LocalDateTime date = LocalDateTime.now().plusDays(-random.nextInt(10));
        return new NotesStructure(name, description, date);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeSerializable(getDate());
    }

    protected NotesStructure(Parcel in) {
        id = in.readInt();
        name = in.readString();
        description = in.readString();
        date = (LocalDateTime)in.readSerializable();
    }
}
