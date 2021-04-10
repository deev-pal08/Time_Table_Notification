package aplication.codewithdeev.timetable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ChooseSection extends AppCompatActivity {
    ImageButton btnA,btnB;
    String courSem;
    Intent intent,intent1;
    public static final String message="GET_COURSE_NAME";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_section);
    }
}