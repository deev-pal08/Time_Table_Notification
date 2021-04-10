package aplication.codewithdeev.timetable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class ChooseSemester extends AppCompatActivity {
    Spinner spinnerSem;
    CustomSpinnerAdapter adapter;
    String courSem;
    Intent intent,intent1;
    ImageButton btnA,btnB;
    String sem;
    String data[]={"SELECT SEMESTER","FIRST SEMESTER","SECOND SEMESTER","THIRD SEMESTER","FOURTH SEMESTER","FIFTH SEMESTER","SIXTH SEMESTER"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_semester);
        btnA=findViewById(R.id.btnA);
        btnB=findViewById(R.id.btnB);

        intent=getIntent();
        courSem=intent.getStringExtra(ChooseCourse.message);
        spinnerSem=findViewById(R.id.spinnerSemester);
        adapter=new CustomSpinnerAdapter(ChooseSemester.this,data);
        spinnerSem.setAdapter(adapter);
        intent1=new Intent(ChooseSemester.this,TimeTableStructure.class);

        spinnerSem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if(position>0) {
                    if (spinnerSem.getSelectedItem().equals("FIRST SEMESTER")) {
                        courSem=courSem.substring(0,3);
                        courSem += "1";
                    }
                    else if (spinnerSem.getSelectedItem().equals("SECOND SEMESTER")) {
                        courSem=courSem.substring(0,3);
                        courSem += "2";
                    }
                    else if (spinnerSem.getSelectedItem().equals("THIRD SEMESTER")) {
                        courSem=courSem.substring(0,3);
                        courSem += "3";
                    }
                    else if (spinnerSem.getSelectedItem().equals("FOURTH SEMESTER")) {
                        courSem=courSem.substring(0,3);
                        courSem += "4";
                    }
                    else if (spinnerSem.getSelectedItem().equals("FIFTH SEMESTER")) {
                        courSem=courSem.substring(0,3);
                        courSem += "5";
                    }
                    else if (spinnerSem.getSelectedItem().equals("SIXTH SEMESTER")) {
                        courSem=courSem.substring(0,3);
                        courSem += "6";
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                courSem=courSem.substring(0,4);
                courSem+="A";
                Toast.makeText(ChooseSemester.this, courSem, Toast.LENGTH_LONG).show();
                intent1.putExtra("GET_COURSES_NAME",courSem);
                startActivity(intent1);
            }
        });
        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                courSem=courSem.substring(0,4);
                courSem+="B";
                Toast.makeText(ChooseSemester.this, courSem, Toast.LENGTH_LONG).show();
                intent1.putExtra("GET_COURSES_NAME",courSem);
                startActivity(intent1);
            }
        });
    }
}