package com.example.danamontask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.danamontask.Adapter.EmployeeAdapter;
import com.example.danamontask.Adapter.UserAdapter;
import com.example.danamontask.Database.DatabaseHelper;
import com.example.danamontask.Model.User;
import com.example.danamontask.Model.DataApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mList;

    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private ArrayList<DataApi> dataApiList;
    private RecyclerView.Adapter adapter;

    private ArrayList<User> userList = new ArrayList<>();
    private UserAdapter userAdapter;
    private DatabaseHelper helper;

    private String url = "http://dummy.restapiexample.com/api/v1/employees";

    public boolean isAdminDataOpened = false;

    TextView columnOne, columnTwo, columnThree, columnFour, dataKind, userName, btnLogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mList = findViewById(R.id.employeeRecyclerview);
        columnOne = findViewById(R.id.columnOne);
        columnTwo = findViewById(R.id.columnTwo);
        columnThree = findViewById(R.id.columnThree);
        columnFour = findViewById(R.id.columnFour);
        dataKind = findViewById(R.id.dataKind);
        userName = findViewById(R.id.userName);
        btnLogOut = findViewById(R.id.btnLogOut);

        Intent intent = getIntent();
        String usrNameStr = intent.getStringExtra("name");
        isAdminDataOpened = getIntent().getExtras().getBoolean("isAdminOpened");

        User user = new User();
        userName.setText(user.getUsername());

        Log.e("USER", "user " + user.getUsername());

        helper = new DatabaseHelper(this);
        userList = helper.listUser();

        dataApiList = new ArrayList<>();
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dividerItemDecoration = new DividerItemDecoration(mList.getContext(), linearLayoutManager.getOrientation());
        mList.setHasFixedSize(true);
        mList.setLayoutManager(linearLayoutManager);
        mList.addItemDecoration(dividerItemDecoration);

        if (isAdminDataOpened == false){

            dataKind.setText("Employees");
            columnOne.setText("id");
            columnTwo.setText("Name");
            columnThree.setText("Salary");
            columnFour.setText("Age");
            adapter = new EmployeeAdapter(getApplicationContext(), dataApiList);
            getDataEmployee();
            mList.setAdapter(adapter);


        }else{

            if (userList.size() > 0) {

                dataKind.setText("User Management");
                columnOne.setText("id");
                columnTwo.setText("Name");
                columnThree.setText("Email");
                columnFour.setText("Role");
                adapter = new UserAdapter(getApplicationContext(), userList);
                userAdapter = new UserAdapter(this, userList);
                mList.setAdapter(userAdapter);

            }

        }

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signOutTask();

            }
        });

    }

    private void getDataEmployee() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                    try {

                        JSONArray jsonArray = response.getJSONArray("data");

                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            DataApi dataApi = new DataApi();

                            dataApi.setId(jsonObject.getString("id"));
                            dataApi.setUsername(jsonObject.getString("employee_name"));
                            dataApi.setSalary(jsonObject.getString("employee_salary"));
                            dataApi.setAge(jsonObject.getString("employee_age"));

                            dataApiList.add(dataApi);

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }

                adapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
                progressDialog.dismiss();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }

    private void signOutTask(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Logging Out");
        builder.setMessage("Are you sure want to logging out?");
        builder.create();

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //SaveSharedPreference.setThereIsUser(getActivity(), false);
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();

            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {



            }
        });

        builder.show();

    }

    @Override
    public void onBackPressed() {

        //super.onBackPressed();
        signOutTask();

    }
}
