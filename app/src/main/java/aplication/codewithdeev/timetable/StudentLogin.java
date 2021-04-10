package aplication.codewithdeev.timetable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class StudentLogin extends AppCompatActivity {

    Button submitBtn;
    TextInputEditText stuUserName,courses,section;
    String su,c,s;
    CentralStorage storage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);

        storage=new CentralStorage(StudentLogin.this);

        stuUserName=findViewById(R.id.stuUserName);

        courses=findViewById(R.id.course);
        section=findViewById(R.id.section);

        submitBtn=findViewById(R.id.submitBtn);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                su=stuUserName.getText().toString().trim();
                c=courses.getText().toString().trim();
                s=section.getText().toString().trim();
                String msg=c+s;
                if(su.isEmpty()){
                    stuUserName.setError("Username Cannot Be Empty");
                }
                else if(c.isEmpty()){
                    courses.setError("Course Cannot Be Empty");
                }
                else if(!c.equals("BCA") && !c.equals("BBA") && !c.equals("BMS")){
                    courses.setError("Enter Valid Stream");
                }
                else if(s.isEmpty()){
                    section.setError("SECTION CANNOT BE EMPTY");
                }
                else if(!s.equals("1A") && !s.equals("2A") && !s.equals("3A") && !s.equals("4A") &&
                        !s.equals("5A") && !s.equals("6A") && !s.equals("1B") && !s.equals("2B") &&
                        !s.equals("3B") && !s.equals("4B") && !s.equals("5B") && !s.equals("6B")){
                    section.setError("Enter Valid Section");
                }
                else {
                    storage.setType("USER","Student");
                    storage.setData("TYPE",msg);
                    Intent intent = new Intent(StudentLogin.this, TimeTableStructure.class);
                    intent.putExtra("GET_COURSES_NAME",msg);
                    startActivity(intent);
                }
            }
        });
    }
}