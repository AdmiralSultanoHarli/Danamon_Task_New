package com.example.danamontask.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.danamontask.Database.DatabaseHelper;
import com.example.danamontask.Model.User;
import com.example.danamontask.R;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private Context context;
    private ArrayList<User> userList;
    private ArrayList<User> mArrayList;

    private DatabaseHelper helper;

    public UserAdapter(Context context, ArrayList<User> userList){

        this.context = context;
        this.userList = userList;
        this.mArrayList = userList;
        helper = new DatabaseHelper(context);

    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_table, viewGroup, false);
        UserAdapter.ViewHolder viewHolder = new UserAdapter.ViewHolder(v);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {

        final User user = userList.get(position);

        holder.id.setText(String.valueOf(user.getId()));
        holder.name.setText(user.getUsername());
        holder.email.setText(user.getEmail());
        holder.role.setText(user.getRole());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                deleteTaskDialog(user);

            }
        });

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    private void deleteTaskDialog(final User user){

        /*LayoutInflater inflater = LayoutInflater.from(context);
        View subView = inflater.inflate(R.layout.add_data_layout, null);*/

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Delete Data?");
        //builder.setView(subView);
        builder.create();

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //delete row from database

                helper.deleteData(user.getId());

                //refresh the activity page
                ((Activity) context).finish();
                context.startActivity(((Activity) context).getIntent());

            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                return;

            }
        });

        builder.show();

    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView id, name, email, role;
        public Button delete;

        public ViewHolder(View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.id);
            name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);
            role = itemView.findViewById(R.id.role);
            delete = itemView.findViewById(R.id.delete);
        }
    }

}
