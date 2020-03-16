package com.example.danamontask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.danamontask.Database.DatabaseHelper;
import com.example.danamontask.Model.User;

public class LoginActivity extends AppCompatActivity {

    //aaaa

    EditText edtEmail, edtPassword;
    Button btnSignUp, btnSumbit;
    private SQLiteDatabase db;
    private SQLiteOpenHelper openHelper;
    private Cursor cursor;
    private Cursor cursorTwo;

    boolean isAdminChoosed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnSumbit = findViewById(R.id.btnSumbit);
        openHelper = new DatabaseHelper(this);
        db = openHelper.getReadableDatabase();
        final User user = new User();

        //isAdminChoosed = getIntent().getExtras().getBoolean("isAdminChoosed");
        Log.e("boolean", String.valueOf(isAdminChoosed));

        btnSumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nameStr = "";
                String emailStr = edtEmail.getText().toString().trim();
                String passwordStr = edtPassword.getText().toString().trim();

                if (emailStr.isEmpty() && passwordStr.isEmpty()){

                    Toast.makeText(LoginActivity.this, "Input something", Toast.LENGTH_SHORT).show();

                }else if (emailStr.isEmpty()){

                    Toast.makeText(LoginActivity.this, "Please input phone number", Toast.LENGTH_SHORT).show();

                }else if(passwordStr.isEmpty()){

                    Toast.makeText(LoginActivity.this, "Please input password", Toast.LENGTH_SHORT).show();

                }else{

                    String loginAdmin = "SELECT * FROM " + DatabaseHelper.TABLE_USER + " WHERE " + DatabaseHelper.COLUMN_USER_EMAIL + "=? AND " + DatabaseHelper.COLUMN_USER_PASSWORD + "=? AND " + DatabaseHelper.COLUMN_USER_ROLE + "='Admin'";
                    String loginUser = "SELECT * FROM " + DatabaseHelper.TABLE_USER + " WHERE " + DatabaseHelper.COLUMN_USER_EMAIL + "=? AND " + DatabaseHelper.COLUMN_USER_PASSWORD + "=? AND " + DatabaseHelper.COLUMN_USER_ROLE + "='Normal User'";

                    cursor = db.rawQuery(loginAdmin, new String[]{emailStr, passwordStr});
                    cursorTwo = db.rawQuery(loginUser, new String[]{emailStr, passwordStr});

                    if (cursor != null) {

                        //  user.setUsername(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_USER_NAME)));

                        if (cursor.getCount() > 0) {

                            Intent i = new Intent(LoginActivity.this, MainActivity.class);
                            i.putExtra("isAdminOpened", true);
                            startActivity(i);
                            Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                            finish();

                        } else {

                            if (cursorTwo != null) {

                                if (cursorTwo.getCount() > 0) {

                                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                    //i.putExtra("name", emailStr);
                                    i.putExtra("isAdminOpened", false);
                                    startActivity(i);
                                    Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                                    finish();

                                } else {

                                    Toast.makeText(LoginActivity.this, "No data", Toast.LENGTH_SHORT).show();

                                }
                            }
                        }

                        //cursor.close();
                    }

                }

            }

        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(i);
                finish();

            }
        });

    }
}
