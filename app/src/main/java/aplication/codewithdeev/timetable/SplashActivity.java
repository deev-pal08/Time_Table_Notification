package aplication.codewithdeev.timetable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SplashActivity extends AppCompatActivity {
    CentralStorage storage;
    String type,data,date;
    SimpleDateFormat simpleDateformat;
    Date now;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        now = new Date();
        simpleDateformat = new SimpleDateFormat("EEEE");
        date=simpleDateformat.format(now);

        storage=new CentralStorage(SplashActivity.this);
        type=storage.getType("USER");
        data=storage.getData("TYPE").trim();
        if(!storage.getDate("Date").equals(date)){
            storage.removeData("Date");
        }
        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        if(type.isEmpty()){
                            startActivity(new Intent(SplashActivity.this,ChooseOption.class));
                            SplashActivity.this.finish();
                        }else{
                            if(type.equals("Student")){
                                Intent intent = new Intent(SplashActivity.this, TimeTableStructure.class);
                                intent.putExtra("GET_COURSES_NAME",data);
                                startActivity(intent);
                                SplashActivity.this.finish();
                            }else if(type.equals("Teacher")){
                                startActivity(new Intent(SplashActivity.this,ChooseCourse.class));
                                SplashActivity.this.finish();
                            }
                        }
                    }
                },5000
        );
    }
}