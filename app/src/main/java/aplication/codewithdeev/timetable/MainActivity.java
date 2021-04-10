package aplication.codewithdeev.timetable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    Button btnSubmit;

    private FirebaseAuth mAuth;
    TextInputEditText teacherUserName,teacherPassword;
    String tu,tp;
    CentralStorage storage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        storage=new CentralStorage(MainActivity.this);

        teacherUserName=findViewById(R.id.teachersUserName);
        teacherPassword=findViewById(R.id.teachersPassword);
        btnSubmit=findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tu=teacherUserName.getText().toString().trim();
                tp=teacherPassword.getText().toString().trim();

                if(tu.isEmpty()){
                    teacherUserName.setError("USERNAME CANNOT BE EMPTY");
                }
                else if(tp.isEmpty()){
                    teacherPassword.setError("PASSWORD CANNOT BE EMPTY");
                }
                else {
                    mAuth.signInWithEmailAndPassword(tu,tp)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                            Toast.makeText(MainActivity.this, "Signed In Successfully", Toast.LENGTH_SHORT).show();
                                            storage.setType("USER","Teacher");
                                            storage.setData("TYPE","Teacher");
                                            startActivity(new Intent(MainActivity.this,ChooseCourse.class));
                                            MainActivity.this.finish();
                                    } else {
                                        Toast.makeText(MainActivity.this,
                                                "SignIn failed",
                                                Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }
            }
        });
    }
    public void passwordChange(View view){
        startActivity(new Intent(MainActivity.this,PasswordChangeActivity.class));
    }
}