package esadrcanfer.us.alumno.autotesting;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class DetailsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private Integer id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = new Integer(extras.get("noteid").toString());
            EditText edit = (EditText)findViewById(R.id.editText1);
            edit.setText(NoteStore.getNotes().get(id));
            String dataString = extras.getString("data");
            Spinner spinner = (Spinner) findViewById(R.id.spinner1);
            // Create an ArrayAdapter using the string array and a default spinner layout
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.notes_array, android.R.layout.simple_spinner_item);
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            // Apply the adapter to the spinner
            spinner.setAdapter(adapter);

            spinner.setSelection(getIndex(spinner, dataString));

            spinner.setOnItemSelectedListener(this);


        }
    }

    public void save(View view) {
        EditText edit = (EditText)findViewById(R.id.editText1);
        if(edit.getText().length() != 0) {
            // find spinner object here
            Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
            //get selected value here
            String itemStr = spinner1.getSelectedItem().toString();
            NoteStore.updateNote(edit.getText().toString(), id, itemStr);
            //put selected vale and start new activity
            Intent intent = new Intent(view.getContext(), MainActivity.class);
            intent.putExtra("data", itemStr);
            startActivityForResult(intent, 0);
        }
    }

    public void delete(View view) {
        NoteStore.deleteNote(NoteStore.getNotes().get(id));
        Intent intent = new Intent(view.getContext(), MainActivity.class);
        startActivityForResult(intent, 0);
    }

    public void cancel(View view) {
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Bundle extras = getIntent().getExtras();
        String dataString = extras.getString("data");

        String selected = adapterView.getItemAtPosition(i).toString();
        if(!selected.equals(dataString)){
            Toast.makeText(this, selected, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, dataString, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private int getIndex(Spinner spinner, String myString){
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                return i;
            }
        }

        return 0;
    }
}
