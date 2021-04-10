package aplication.codewithdeev.timetable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ChooseOption extends AppCompatActivity {
    ImageButton btnStudent,btnTeacher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_option);

        btnStudent=findViewById(R.id.Student);
        btnTeacher=findViewById(R.id.Teacher);

        btnStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChooseOption.this,StudentLogin.class));
                ChooseOption.this.finish();
            }
        });
        btnTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChooseOption.this,MainActivity.class));
                ChooseOption.this.finish();
            }
        });
    }
}