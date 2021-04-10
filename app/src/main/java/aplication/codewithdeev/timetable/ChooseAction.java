package aplication.codewithdeev.timetable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ChooseAction extends AppCompatActivity {
    CardView ChangeSchedule,Announcement,ClassCancelled,ChangeSubjects;
    BottomSheetDialog dialog;
    TextInputEditText txtTimings,txtTimings1;
    EditText announcementText;
    Button btnChangeTime,btnMakeAnnouncement;
    Intent intent1;
    String course,date;
    FCMIdService fcmIdService;
    SimpleDateFormat simpleDateformat;
    Date now;
    CentralStorage storage;
    FirebaseFirestore db;
    Map<String,String>newData;
    String timings="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_action);

        db = FirebaseFirestore.getInstance();
        now = new Date();
        simpleDateformat = new SimpleDateFormat("EEEE");
        date=simpleDateformat.format(now);
        newData = new HashMap<>();
        Intent intent=getIntent();
        course=intent.getStringExtra("CourseName");
        ChangeSchedule=findViewById(R.id.ChangeSchedule);
        Announcement=findViewById(R.id.Announcement);
        ClassCancelled=findViewById(R.id.ClassCancelled);
        ChangeSubjects=findViewById(R.id.ChangeSubjects);

        ChangeSubjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent1=new Intent(ChooseAction.this,BBAFifthSemesterSubjects.class);
                intent1.putExtra("CourseName",course);
                startActivity(intent1);
                ChooseAction.this.finish();

            }
        });
        ChangeSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog=new BottomSheetDialog(ChooseAction.this);
                View dialogView=getLayoutInflater().inflate(R.layout.modal_layout_timming,null);
                txtTimings=dialogView.findViewById(R.id.txtTimings);
                txtTimings1=dialogView.findViewById(R.id.txtTimings1);
                btnChangeTime=dialogView.findViewById(R.id.btnChangeTime);
                dialog.setContentView(dialogView);
                dialog.show();
                btnChangeTime.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        timings = txtTimings.getText().toString().trim()+"-"+txtTimings1.getText().toString().trim();
                        String pos = course.substring(5);
                        String input = "Time "+timings;
                        db.collection("Time_Table")
                                .document(date)
                                .update(course,input)
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(ChooseAction.this,
                                                e.getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                })
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        //success
                                        Toast.makeText(ChooseAction.this, "Class Rescheduled",
                                                Toast.LENGTH_LONG).show();
                                    }
                                });
                        Intent intent = new Intent(ChooseAction.this,TimeTableStructure.class);
                        course=course.substring(0,5);
                        intent.putExtra("GET_COURSES_NAME",course);
                        startActivity(intent);
                        ChooseAction.this.finish();
                    }
                });

            }
        });
        Announcement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog=new BottomSheetDialog(ChooseAction.this);
                View dialogView=getLayoutInflater().inflate(R.layout.layout_announcement,null);
                dialog.setContentView(dialogView);
                dialog.show();
                btnMakeAnnouncement = dialogView.findViewById(R.id.btnMakeAnnouncement);
                announcementText = dialogView.findViewById(R.id.announcementText);
                btnMakeAnnouncement.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String announcement = announcementText.getText().toString().trim();
                        String input = "Announcement "+announcement;
                        db.collection("Time_Table")
                                .document(date)
                                .update(course,input)
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(ChooseAction.this,
                                                e.getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                })
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        //success
                                        Toast.makeText(ChooseAction.this, "New Announcement",
                                                Toast.LENGTH_LONG).show();
                                    }
                                });
                        Intent intent = new Intent(ChooseAction.this,TimeTableStructure.class);
                        course=course.substring(0,5);
                        intent.putExtra("GET_COURSES_NAME",course);
                        startActivity(intent);
                        ChooseAction.this.finish();
                    }
                });

            }
        });
        //intent=new Intent(ChooseAction.this,TimeTableStructure.class);
        ClassCancelled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db.collection("Time_Table")
                        .document(date)
                        .update(course,"Cancelled")
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(ChooseAction.this,
                                        e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        })
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                //success
                                Toast.makeText(ChooseAction.this, "Class Cancelled",
                                        Toast.LENGTH_LONG).show();
                            }
                        });
                //fcmIdService.sendNotification("One Of Your Class Has Been Cancelled");
                Intent intent = new Intent(ChooseAction.this,TimeTableStructure.class);
                course=course.substring(0,5);
                intent.putExtra("GET_COURSES_NAME",course);
                startActivity(intent);
                ChooseAction.this.finish();
               // timeTableStructure.getInstance().finish();
            }
        });

    }
}