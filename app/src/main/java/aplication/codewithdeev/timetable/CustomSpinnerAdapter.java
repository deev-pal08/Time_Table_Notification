package aplication.codewithdeev.timetable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CustomSpinnerAdapter extends ArrayAdapter<String> {
    Context context;
    String days[];

    public CustomSpinnerAdapter(Context c,String days[]){
        super(c,0,days);
        this.context=c;
        this.days=days;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return customView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return customView(position, convertView, parent);
    }

    public View customView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    if(convertView==null) {
        convertView = LayoutInflater.from(context).inflate(R.layout.custom_spinner_layout, parent, false);
    }
        TextView spinnerText=convertView.findViewById(R.id.customSpinnerText);
        spinnerText.setText(days[position]);
        return convertView;
    }
}
