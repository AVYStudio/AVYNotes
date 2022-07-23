package ru.veprev.avynotes;

import android.annotation.SuppressLint;

import java.time.LocalDateTime;
import java.util.Random;


public class NotesStructure {

    private static final Random random = new Random();
    private static NotesStructure[] notes;

    private int id;
    private String name;
    private String description;
    private LocalDateTime date;

    public NotesStructure(String name, String description, LocalDateTime date) {
        this.name = name;
        this.description = description;
        this.date = date;
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

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    static {
        notes = new NotesStructure[10];
        for (int i = 0; i < notes.length; i++) {
            notes[i] = NotesStructure.getNote(i);
        }
    }

    @SuppressLint("DefaultLocale")
    public static NotesStructure getNote(int index){
        String name = String.format("Заметка %d", index + 1);
        String description = String.format("Описание заметки %d", index + 1);
        LocalDateTime date = LocalDateTime.now().plusDays(-random.nextInt(10));
        return new NotesStructure(name, description, date);
    }
}
