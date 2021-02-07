package esadrcanfer.us.alumno.autotesting;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    String selectedItem = null;

    public void setItem(String item){
        this.selectedItem = item;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ListView listview = (ListView) findViewById(R.id.listView1);
        final NotesAdapter adapter = new NotesAdapter(this, NoteStore.getNotes(), NoteStore.getSelectedItems(), NoteStore.getSelectedRads());
        listview.setAdapter(adapter);
    }
    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }

    public void createNote(View view){
        Intent intent = new Intent(view.getContext(), CreateNoteActivity.class);
        startActivityForResult(intent, 0);
    }

}

