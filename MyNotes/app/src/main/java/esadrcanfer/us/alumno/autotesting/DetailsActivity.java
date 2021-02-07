package esadrcanfer.us.alumno.autotesting;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class DetailsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private Integer id;
    RadioButton c1, c2, c3;

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

            String radString = extras.getString("radioChosen");

            c1 = (RadioButton) findViewById(R.id.idRadio1);
            c2 = (RadioButton) findViewById(R.id.idRadio2);
            c3 = (RadioButton) findViewById(R.id.idRadio3);

            if(c1.getText().toString().equals(radString)){
                c1.setChecked(true);
            }

            if(c2.getText().toString().equals(radString)){
                c2.setChecked(true);
            }

            if(c3.getText().toString().equals(radString)){
                c3.setChecked(true);
            }

        }
    }

    public void save(View view) {
        EditText edit = (EditText)findViewById(R.id.editText1);
        if(edit.getText().length() != 0) {
            // find spinner object here
            Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
            //get selected value here
            String itemStr = spinner1.getSelectedItem().toString();

            String radStr = null; // store the text corresponding to  the RadioButton which is clicked

            if(c1.isChecked() == true){
                radStr = c1.getText().toString();
            }

            if(c2.isChecked() == true){
                radStr = c2.getText().toString();
            }

            if(c3.isChecked() == true){
                radStr = c3.getText().toString();
            }

            NoteStore.updateNote(edit.getText().toString(), id, itemStr, radStr);

            //put selected vale and start new activity
            Intent intent = new Intent(view.getContext(), MainActivity.class);
            intent.putExtra("data", itemStr);
            intent.putExtra("radioChosen", radStr);

            startActivityForResult(intent, 0);
        }
    }

    public void delete(View view) {
        NoteStore.deleteNote(NoteStore.getNotes().get(id), id);
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

    public void onRadioButtonClicked(View view) {
        String radStr = null;

        if(c1.isChecked() == true){
            radStr = c1.getText().toString();
        }

        if(c2.isChecked() == true){
            radStr = c2.getText().toString();
        }

        if(c3.isChecked() == true){
            radStr = c3.getText().toString();
        }

        Toast.makeText(this, radStr, Toast.LENGTH_SHORT).show();
    }
}
