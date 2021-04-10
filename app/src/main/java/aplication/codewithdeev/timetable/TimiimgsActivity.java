package aplication.codewithdeev.timetable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class TimiimgsActivity extends AppCompatActivity {
    private FirebaseUser user;
    TextInputEditText emailId,oldPsw,newPsw;
    Button btn_change_Psw;
    ProgressBar progressBar;
    String email,newPassword,oldPassword;
    AuthCredential credential;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timiimgs);

        user = FirebaseAuth.getInstance().getCurrentUser();
        emailId=findViewById(R.id.emailChangeID);
        oldPsw=findViewById(R.id.pswChangeOld);
        newPsw=findViewById(R.id.pswChangeNew);
        btn_change_Psw=findViewById(R.id.btn_change_password);
        progressBar=findViewById(R.id.progressBarChange);

        email=emailId.getText().toString().trim();
        newPassword=newPsw.getText().toString().trim();
        oldPassword=oldPsw.getText().toString().trim();
//
//        btn_change_Psw.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                credential = EmailAuthProvider.getCredential(email,oldPassword);
//
//                if (TextUtils.isEmpty(email)) {
//                    Toast.makeText(getApplication(), "Enter your registered email id", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                progressBar.setVisibility(View.VISIBLE);
//
//                user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if(task.isSuccessful()){
//                            user.updatePassword(newPassword).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                @Override
//                                public void onComplete(@NonNull Task<Void> task) {
//                                    if(!task.isSuccessful()){
//                                        Toast.makeText(TimiimgsActivity.this, "Something Went Wrong Try After Some Time", Toast.LENGTH_LONG).show();
//                                    }else {
//                                        Toast.makeText(TimiimgsActivity.this, "Password Successfully Changed", Toast.LENGTH_LONG).show();
//                                    }
//                                }
//                            });
//                        }else {
//                            Toast.makeText(TimiimgsActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
//                        }
//                        progressBar.setVisibility(View.GONE);
//                    }
//                });
//            }
//        });

    }
}