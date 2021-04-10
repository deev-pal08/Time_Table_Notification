package aplication.codewithdeev.timetable;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TimeTableStruct extends RecyclerView.Adapter<TimeTableStruct.ViewHolder> {
    CentralStorage storage;
    String type;
    Context context;
    List<ModalClass>list;
    String course;
    String date;
    SimpleDateFormat simpleDateformat;
    Date now;
    String currDate;

    public TimeTableStruct(Context context, List<ModalClass> list,String course,String date) {
        this.context = context;
        this.list= list;
        this.course=course;
        this.date=date;
    }


    @NonNull
    @Override
    public TimeTableStruct.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myView= LayoutInflater.from(context).inflate(R.layout.table_structure,parent,false);

        return new ViewHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull final TimeTableStruct.ViewHolder holder, final int position) {
        now = new Date();
        simpleDateformat = new SimpleDateFormat("EEEE");
        currDate=simpleDateformat.format(now);
        storage=new CentralStorage(context);
        type=storage.getType("USER");
        holder.tCodes.setText(list.get(position).getSubjectCode());
        holder.tTime.setText(list.get(position).getTimming());
        holder.tSub.setText(list.get(position).getSubjectName());
        holder.tTeacher.setText(list.get(position).getSubjectTecaher());
        if(type.equals("Teacher") &&  currDate.equals(date)) {
            if(date.equals("Monday") || date.equals("Tuesday") || date.equals("Wednesday") || date.equals("Thursday") || date.equals("Friday")) {
                holder.layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, ChooseAction.class);
                        intent.putExtra("CourseName", course+position);
                        context.startActivity(intent);
                        ((Activity)context).finish();
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout layout;
        TextView tCodes;
        TextView tTime;
        TextView tSub;
        TextView tTeacher;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout=itemView.findViewById(R.id.recyclerLayout);
            tCodes=itemView.findViewById(R.id.subCode);
            tTime=itemView.findViewById(R.id.subTiming);
            tSub=itemView.findViewById(R.id.subName);
            tTeacher=itemView.findViewById(R.id.subTeacher);
        }
    }
}
