package aplication.codewithdeev.timetable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.ArrayList;

public class BBAFirstSemesterSubject extends AppCompatActivity {
    Spinner spinnerSem;
    RadioGroup rGroup;
    ArrayList<String>semesters;
    ArrayAdapter adapter;
    String course;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_b_a_first_semester_subject);

        rGroup=findViewById(R.id.rGroup);
        spinnerSem=findViewById(R.id.spinnerSemester);

        semesters.add("FIRST SEMSTER");
        semesters.add("SECOND SEMSTER");
        semesters.add("THIRD SEMSTER");
        semesters.add("FOURTH SEMSTER");
        semesters.add("FIFTH SEMSTER");
        semesters.add("SIXTH SEMSTER");
        adapter=new ArrayAdapter(BBAFirstSemesterSubject.this,R.layout.support_simple_spinner_dropdown_item,semesters);
        spinnerSem.setAdapter(adapter);

        spinnerSem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                course+=spinnerSem.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        rGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                if(id==R.id.sectionA){
                    course+="A";
                }else if(id==R.id.sectionB){
                    course+="B";
                }
            }
        });
    }
}