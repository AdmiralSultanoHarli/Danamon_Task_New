package com.example.danamontask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.danamontask.Database.DatabaseHelper;

public class SignUpActivity extends AppCompatActivity {

    EditText edtUser, edtEmail, edtPassword;
    RadioButton admRadio, userRadio;
    Button btnCancel, btnSumbit;

    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    private static int TIME_CHANGING = 300;
    boolean isAdminChoosed = false;

    String roleStr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        openHelper = new DatabaseHelper(this);
        edtUser = findViewById(R.id.edtUser);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        admRadio = findViewById(R.id.admRadio);
        userRadio = findViewById(R.id.userRadio);
        btnCancel = findViewById(R.id.btnCancel);
        btnSumbit = findViewById(R.id.btnSumbit);
        btnSumbit.setEnabled(false);

        btnSumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db = openHelper.getWritableDatabase();
                final String nameStr = edtUser.getText().toString().trim();
                final String emailStr = edtEmail.getText().toString().trim();
                final String passwordStr = edtPassword.getText().toString().trim();



                if (nameStr.isEmpty() || emailStr.isEmpty() || passwordStr.isEmpty()){

                    Toast.makeText(SignUpActivity.this, "Input something", Toast.LENGTH_SHORT).show();

                }else{

                    if (isAdminChoosed == true) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                insertData(nameStr, emailStr, passwordStr, roleStr);
                                Toast.makeText(SignUpActivity.this, "SignUp Successful", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(SignUpActivity.this, LoginActivity.class);
                                i.putExtra("isAdminChoosed", true);
                                startActivity(i);
                                finish();

                            }
                        }, TIME_CHANGING);

                    }else {

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                insertData(nameStr, emailStr, passwordStr, roleStr);
                                Toast.makeText(SignUpActivity.this, "SignUp Successful", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(SignUpActivity.this, LoginActivity.class);
                                i.putExtra("isAdminChoosed", false);
                                startActivity(i);
                                finish();

                            }
                        }, TIME_CHANGING);

                    }


                }

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(i);

            }
        });

        final MainActivity mainActivity = new MainActivity();

        admRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if (admRadio.isChecked()){

                    admRadio.setChecked(true);
                    userRadio.setChecked(false);
                    btnSumbit.setEnabled(true);
                    roleStr = "Admin";
                    isAdminChoosed = true;
                    //mainActivity.isAdminDataOpened = true;

                }

            }
        });

        userRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (userRadio.isChecked()){

                    userRadio.setChecked(true);
                    admRadio.setChecked(false);
                    btnSumbit.setEnabled(true);
                    roleStr = "Normal User";
                    isAdminChoosed = false;
                    //mainActivity.isAdminDataOpened = false;

                }

            }
        });

    }

    public void insertData(String nameStr, String emailstr, String passwordStr, String roleStr){

        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COLUMN_USER_NAME, nameStr);
        contentValues.put(DatabaseHelper.COLUMN_USER_EMAIL, emailstr);
        contentValues.put(DatabaseHelper.COLUMN_USER_PASSWORD, passwordStr);
        contentValues.put(DatabaseHelper.COLUMN_USER_ROLE, roleStr);

        long id = db.insert(DatabaseHelper.TABLE_USER, null, contentValues);

    }

}
