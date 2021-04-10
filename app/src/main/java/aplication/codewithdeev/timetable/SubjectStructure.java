package aplication.codewithdeev.timetable;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SubjectStructure extends RecyclerView.Adapter<SubjectStructure.ViewHolder> {
    Context context;
    String rTitle[];
    String rSTitle[];
    CentralStorage storage;
    String pos,course;
    SimpleDateFormat simpleDateformat;
    Date now;
    FirebaseFirestore db;

    SubjectStructure(Context c,String title[],String sTitle[],String pos,String course){
        this.context=c;
        this.rTitle=title;
        this.rSTitle=sTitle;
        this.pos=pos;
        this.course=course;

    }


    @NonNull
    @Override
    public SubjectStructure.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myView= LayoutInflater.from(context).inflate(R.layout.subjects,parent,false);

        return new ViewHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull final SubjectStructure.ViewHolder holder, final int position) {
        holder.tCodes.setText(rTitle[position]);
        holder.tSubName.setText(rSTitle[position]);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = FirebaseFirestore.getInstance();
                String type = holder.tCodes.getText().toString().trim()+" "+holder.tSubName.getText().toString().trim();
                String date = "";
                now = new Date();
                simpleDateformat = new SimpleDateFormat("EEEE");
                date=simpleDateformat.format(now);
                String courses=course+pos;
                db.collection("Time_Table")
                        .document(date)
                        .update(courses,type)
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(context,
                                        e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        })
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                //success
                                Toast.makeText(context, "Subject Changed",
                                        Toast.LENGTH_LONG).show();
                            }
                        });
                Intent intent = new Intent(context,TimeTableStructure.class);
                intent.putExtra("GET_COURSES_NAME",course);
                context.startActivity(intent);
                ((Activity)context).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return rTitle.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout layout;
        TextView tCodes;
        TextView tSubName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout=itemView.findViewById(R.id.linearLayout);
            tCodes=itemView.findViewById(R.id.subCodes);
            tSubName=itemView.findViewById(R.id.subjectName);
        }
    }
}
