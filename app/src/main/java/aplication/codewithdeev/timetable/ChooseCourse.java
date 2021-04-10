package aplication.codewithdeev.timetable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseCourse extends AppCompatActivity {
    CardView BCA,BBA,BMS;
    public static final String message= "GET_STREAM_NAME";
    String bca="BCA";
    String bba="BBA";
    String bms="BMS";
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_course);

        BCA=findViewById(R.id.BCAcard);
        BBA=findViewById(R.id.BBAcard);
        BMS=findViewById(R.id.BMScard);
        intent=new Intent(ChooseCourse.this,ChooseSemester.class);

        BCA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra(message,bca);
                startActivity(intent);
            }
        });

        BBA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra(message,bba);
                startActivity(intent);
            }
        });

        BMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra(message,bms);
                startActivity(intent);
            }
        });
    }
}