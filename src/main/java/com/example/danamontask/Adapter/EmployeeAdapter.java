package com.example.danamontask.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.danamontask.Model.DataApi;
import com.example.danamontask.R;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder> {

    private Context context;
    private List<DataApi> dataApiList;

    public EmployeeAdapter(Context context, List<DataApi> dataApiList){

        this.context = context;
        this.dataApiList = dataApiList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.employee_table, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        DataApi dataApi = dataApiList.get(position);

        holder.id.setText(String.valueOf(dataApi.getId()));
        holder.name.setText(dataApi.getUsername());
        holder.salary.setText(dataApi.getSalary());
        holder.age.setText(dataApi.getAge());

    }

    @Override
    public int getItemCount() {
        return dataApiList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView id, name, salary, age;

        public ViewHolder(View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.id);
            name = itemView.findViewById(R.id.name);
            salary = itemView.findViewById(R.id.salary);
            age = itemView.findViewById(R.id.age);
        }
    }

}
