package esadrcanfer.us.alumno.autotesting;

import java.util.ArrayList;
import java.util.List;

public class NoteStore {
    private static List<String> notes;
    private static List<String> selectedItems;
    private static List<String> selectedRads;

    public static List<String> getNotes(){
        if(notes == null && selectedItems == null && selectedRads == null){
            notes = new ArrayList<>();
            notes.add("My first note");

            selectedItems = new ArrayList<>();
            selectedItems.add("Receta");

            selectedRads = new ArrayList<>();
            selectedRads.add("Critica");
        }
        return new ArrayList<>(notes);
    }

    public static List<String> getSelectedItems() {
        return selectedItems;
    }

    public static List<String> getSelectedRads() {
        return selectedRads;
    }

    public static void addNotes(String note, String item, String rad){
        if(notes == null && selectedItems == null && selectedRads == null ){
            notes = new ArrayList<>();
            notes.add("My first note");
            selectedItems.add("Receta");
            selectedRads.add("Critica");
        }
        notes.add(note);
        selectedItems.add(item);
        selectedRads.add(rad);
    }

    public static void updateNote(String note, Integer pos, String item, String rad){
        notes.set(pos, note);
        selectedItems.set(pos, item);
        selectedRads.set(pos, rad);
    }

    public static void deleteNote(String note, Integer pos){
        notes.remove(note);
        selectedItems.remove(selectedItems.get(pos));
        selectedRads.remove(selectedRads.get(pos));
    }
}
