package aplication.codewithdeev.timetable;

import androidx.annotation.NonNull;
import java.text.SimpleDateFormat;
import java.util.Date;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firestore.v1beta1.MapValue;
import com.google.firestore.v1beta1.Value;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeTableStructure extends AppCompatActivity {
//    String BBA5AtimmingsMON[]={"11:00 AM","12:00 PM","1:00 PM","2:00 PM","3:00 PM"};
//    String BBA5ACodesMON[]={"BBAN 504","BBAN 501"," ", "BBAN 502","BBAN 503", };
//    String BBA5ASubMON[]={"ENTREPRENEURSHIP DEVELOPMENT","FINANCIAL MANAGEMENT","BREAK",
//            "MARKETING MANAGEMENT","HUMAN RESOURCE MANAGEMENT"};
//    String BBA5AteachersMON[]={"Sangita Ghosh","Ananya Banerjee Panda"," ","Bratin Maiti","Shreyasi Ray"};
    String courSec;
    RecyclerView rView;
    GridLayoutManager manager;
    CentralStorage storage;
    TimeTableStruct adapter;
    FirebaseFirestore db;
    Intent intent;
    Spinner customSpinner;
    SimpleDateFormat simpleDateformat;
    CustomSpinnerAdapter arrayAdapter;
    Date now;
    String date,currDate;
    ProgressBar timeTableProgress;
    List<ModalClass> monday= new ArrayList<>();
    List<ModalClass> tuesday= new ArrayList<>();
    List<ModalClass> wednesday= new ArrayList<>();
    List<ModalClass> thursday= new ArrayList<>();
    List<ModalClass> friday= new ArrayList<>();
    List<ModalClass> Holiday= new ArrayList<>();
    Map<String, Object> mapChange;
    String days[]={"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
    String days1[]={"Tuesday","Monday","Wednesday","Thursday","Friday","Saturday","Sunday"};
    String days2[]={"Wednesday","Monday","Tuesday","Thursday","Friday","Saturday","Sunday"};
    String days3[]={"Thursday","Monday","Tuesday","Wednesday","Friday","Saturday","Sunday"};
    String days4[]={"Friday","Monday","Tuesday","Wednesday","Thursday","Saturday","Sunday"};
    String days5[]={"Saturday","Monday","Tuesday","Wednesday","Thursday","Friday","Sunday"};
    String days6[]={"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.time_table_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:
                showLogoutDialog();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showLogoutDialog(){
        AlertDialog.Builder alertBuilder=new AlertDialog.Builder(TimeTableStructure.this);
        alertBuilder.create();
        alertBuilder.setMessage("Are you sure to exit?");
        alertBuilder.setCancelable(false);
        alertBuilder.setPositiveButton("CONFIRM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                new CentralStorage(TimeTableStructure.this).clearData();
                Toast.makeText(TimeTableStructure.this, "LOGOUT SUCCESSFUL", Toast.LENGTH_LONG).show();
                startActivity(new Intent(TimeTableStructure.this,ChooseOption.class));
                TimeTableStructure.this.finish();
            }
        });
        alertBuilder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                Toast.makeText(TimeTableStructure.this, "OPERATION CANCELLED", Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });
        alertBuilder.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table_structure);
        customSpinner=findViewById(R.id.customSpinner);
        now = new Date();
        simpleDateformat = new SimpleDateFormat("EEEE");
        date=simpleDateformat.format(now);
        currDate = simpleDateformat.format(now);
        intent=getIntent();
        courSec=intent.getStringExtra("GET_COURSES_NAME");
        rView=findViewById(R.id.rcView);
        manager=new GridLayoutManager(TimeTableStructure.this,1);
        rView.setLayoutManager(manager);
        mapChange = new HashMap<>();
        timeTableProgress = findViewById(R.id.timeTableProgress);
        db = FirebaseFirestore.getInstance();

        if(date.equals("Saturday") || date.equals("Sunday")){
            ModalClass modal = new ModalClass(" ","No Classes For Today"," "," ","0");
            Holiday.add(modal);
        }
        if(date.equals("Monday"))
            arrayAdapter=new CustomSpinnerAdapter(TimeTableStructure.this,days);
        else if(date.equals("Tuesday"))
            arrayAdapter=new CustomSpinnerAdapter(TimeTableStructure.this,days1);
        else if(date.equals("Wednesday"))
            arrayAdapter=new CustomSpinnerAdapter(TimeTableStructure.this,days2);
        else if(date.equals("Thursday"))
            arrayAdapter=new CustomSpinnerAdapter(TimeTableStructure.this,days3);
        else if(date.equals("Friday"))
            arrayAdapter=new CustomSpinnerAdapter(TimeTableStructure.this,days4);
        else if(date.equals("Saturday"))
            arrayAdapter=new CustomSpinnerAdapter(TimeTableStructure.this,days5);
        else
            arrayAdapter=new CustomSpinnerAdapter(TimeTableStructure.this,days6);

        customSpinner.setAdapter(arrayAdapter);
        customSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                    date=customSpinner.getSelectedItem().toString().trim();
                    getTimeTable(date);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
    public void getTimeTable(final String date) {

        db.collection("Time_Table")
                .document(currDate)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot snapshot = task.getResult();
                            mapChange = snapshot.getData();
                            Log.i("TAG", "onComplete: "+String.valueOf(mapChange.get("BCA1A0")));
                        }
                    }
                });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                db.collection("Time_Table")
                        .document(courSec)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot snapshot = task.getResult();

                                    Map<String, Object> map = snapshot.getData();
                                    Map<String,Object> map1 = (Map<String, Object>) map.get("MONDAY");
                                    Map<String,Object> map2 = (Map<String, Object>) map.get("TUESDAY");
                                    Map<String,Object> map3 = (Map<String, Object>) map.get("WEDNESDAY");
                                    Map<String,Object> map4 = (Map<String, Object>) map.get("THURSDAY");
                                    Map<String,Object> map5 = (Map<String, Object>) map.get("FRIDAY");
                                    if(date.equals("Monday"))
                                        getSchedule(map1,monday,"Monday",mapChange);
                                    else if(date.equals("Tuesday"))
                                        getSchedule(map2,tuesday,"Tuesday",mapChange);
                                    else if(date.equals("Wednesday"))
                                        getSchedule(map3,wednesday,"Wednesday",mapChange);
                                    else if(date.equals("Thursday"))
                                        getSchedule(map4,thursday,"Thursday",mapChange);
                                    else if(date.equals("Friday"))
                                        getSchedule(map5,friday,"Friday",mapChange);
                                    else
                                        holiday(Holiday,date);
                                } else {
                                    Toast.makeText(TimeTableStructure.this,
                                            "" + task.getException(),
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        },5000);

    }

    public void holiday(List<ModalClass>day,String date){
        adapter = new TimeTableStruct(this,day,courSec,date);
        rView.setAdapter(adapter);
    }
    public void getSchedule(Map<String, Object> map1,List<ModalClass>day,String date,Map<String,Object>mapChange) {
        String times = "";
        String subjectName = "";
        String subjectCode = "";
        for(int i =0;i<map1.size();i++) {
            if(courSec.equals("BBA5A") || courSec.equals("BBA5B")){
                Map<String, Object> mon = (Map<String, Object>) map1.get(String.valueOf(i+1));
                ModalClass modal = new ModalClass(String.valueOf(mon.get("TIME")),
                        String.valueOf(mon.get("SUBJECT NAME")),
                        String.valueOf(mon.get("TEACHER NAME")),
                        String.valueOf(mon.get("SUBJECT CODE")),
                        String.valueOf(i)
                );
                day.add(modal);
            }else {
                Map<String, Object> mon = (Map<String, Object>) map1.get(String.valueOf(i + 1));
                 ModalClass modal = new ModalClass(String.valueOf(mon.get("TIME")),
                        String.valueOf(mon.get("SUBJECTNAME")),
                        String.valueOf(mon.get("TEACHERNAME")),
                        String.valueOf(mon.get("SUBJECTCODE")),
                        String.valueOf(i)
                );
                String course = courSec+modal.getPosition().trim();

                if (currDate.equals(date)) {
                    if (mapChange.containsKey(course)) {
                        String type = String.valueOf(mapChange.get(course));
                        if (type.equals("Cancelled")) {
                            modal.setSubjectTecaher(" ");
                            modal.setSubjectName(modal.getSubjectName() + "\nThis Class Is Cancelled");
                            //Toast.makeText(this, ""+modal.getSubjectName(), Toast.LENGTH_SHORT).show();
                        }else if (type.substring(0, 4).equals("Time")) {
                            String timeChange[] = type.split(" ");
                            times = timeChange[1];
                            subjectName = modal.getSubjectName();
                            subjectCode = modal.getSubjectCode();
                            modal.setSubjectName("The Class is Rescheduled To\n"+times);

                        } else if (type.substring(0, 12).equals("Announcement")) {
                           modal.setSubjectName(type.substring(13));
                        } else {
                            String subName[] = type.split(" ");
                            modal.setSubjectCode(subName[0] + " " + subName[1]);
                            String sub = "";
                            for (int j = 2; j < subName.length; j++) {
                                sub += subName[j] + " ";
                            }
                            modal.setSubjectName(sub);
                        }
                    }
                }
                if (!times.isEmpty() && modal.getTimming().equals(times)) {
                    modal.setSubjectName(subjectName);
                    modal.setSubjectCode(subjectCode);
                }
                day.add(modal);
            }
        }
        timeTableProgress.setVisibility(View.INVISIBLE);
        adapter = new TimeTableStruct(this,day,courSec,date);
        rView.setAdapter(adapter);

    }

}