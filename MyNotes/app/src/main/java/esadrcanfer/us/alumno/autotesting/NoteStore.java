package esadrcanfer.us.alumno.autotesting;

import java.util.ArrayList;
import java.util.List;

public class NoteStore {
    private static List<String> notes;
    private static String selectedItem;

    public static List<String> getNotes(){
        if(notes == null){
            notes = new ArrayList<>();
            notes.add("My first note");
            selectedItem = "Receta";
        }
        return new ArrayList<>(notes);
    }

    public static String getSelectedItem() {
        return selectedItem;
    }

    public static void addNotes(String note, String item){
        if(notes == null){
            notes = new ArrayList<>();
            notes.add("My first note");
            selectedItem = "Receta";
        }
        notes.add(note);
        selectedItem = item;
    }

    public static void updateNote(String note, Integer pos, String item){
        notes.set(pos, note);
        selectedItem = item;
    }

    public static void deleteNote(String note){
        notes.remove(note);
        selectedItem = null;
    }
}
