package esadrcanfer.us.alumno.autotesting;

import java.util.ArrayList;
import java.util.List;

public class NoteStore {
    private static List<String> notes;
    private static List<String> selectedItems;
    private static List<String> selectedRads;
    private static List<Boolean> selectedOption1;
    private static List<Boolean> selectedOption2;
    private static List<Boolean> selectedOption3;



    public static List<String> getNotes(){
        if(notes == null && selectedItems == null && selectedRads == null && selectedOption1 == null && selectedOption2 == null && selectedOption3 == null){
            notes = new ArrayList<>();
            notes.add("My first note");

            selectedItems = new ArrayList<>();
            selectedItems.add("Receta");

            selectedRads = new ArrayList<>();
            selectedRads.add("Critica");

            selectedOption1 = new ArrayList<>();
            selectedOption1.add(true);

            selectedOption2 = new ArrayList<>();
            selectedOption2.add(false);

            selectedOption3 = new ArrayList<>();
            selectedOption3.add(false);
        }
        return new ArrayList<>(notes);
    }

    public static List<String> getSelectedItems() {
        return selectedItems;
    }

    public static List<String> getSelectedRads() {
        return selectedRads;
    }

    public static List<Boolean> getSelectedOption1() {
        return selectedOption1;
    }

    public static List<Boolean> getSelectedOption2() {
        return selectedOption2;
    }

    public static List<Boolean> getSelectedOption3() {
        return selectedOption3;
    }

    public static void addNotes(String note, String item, String rad, Boolean firstBox, Boolean secondBox, Boolean thirdBox){
        if(notes == null && selectedItems == null && selectedRads == null && selectedOption1 == null && selectedOption2 == null && selectedOption3 == null){
            notes = new ArrayList<>();
            notes.add("My first note");

            selectedItems = new ArrayList<>();
            selectedItems.add("Receta");

            selectedRads = new ArrayList<>();
            selectedRads.add("Critica");

            selectedOption1 = new ArrayList<>();
            selectedOption1.add(true);

            selectedOption2 = new ArrayList<>();
            selectedOption2.add(false);

            selectedOption3 = new ArrayList<>();
            selectedOption3.add(false);
        }
        notes.add(note);
        selectedItems.add(item);
        selectedRads.add(rad);
        selectedOption1.add(firstBox);
        selectedOption2.add(secondBox);
        selectedOption3.add(thirdBox);
    }

    public static void updateNote(String note, Integer pos, String item, String rad, Boolean firstBox, Boolean secondBox, Boolean thirdBox){
        notes.set(pos, note);
        selectedItems.set(pos, item);
        selectedRads.set(pos, rad);
        selectedOption1.set(pos, firstBox);
        selectedOption2.set(pos, secondBox);
        selectedOption3.set(pos, thirdBox);
    }

    public static void deleteNote(String note, Integer pos){
        notes.remove(note);
        selectedItems.remove(selectedItems.get(pos));
        selectedRads.remove(selectedRads.get(pos));
        selectedOption1.remove(selectedOption1.get(pos));
        selectedOption2.remove(selectedOption2.get(pos));
        selectedOption3.remove(selectedOption3.get(pos));
    }
}
