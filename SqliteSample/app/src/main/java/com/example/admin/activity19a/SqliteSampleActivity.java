package com.example.admin.activity19a;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class SqliteSampleActivity extends Activity {
    EditText edtUser, edtPass;
    Button btnSave, btnView;

    MyDatabase myDatabase = new MyDatabase(this,"",null,1);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_insert);
        edtUser = (EditText)findViewById(R.id.activity_first_edit_username);
        edtPass = (EditText)findViewById(R.id.activity_first_edit_password);
        btnSave = (Button) findViewById(R.id.btn_click);
        btnView = (Button) findViewById(R.id.btn_view);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDatabase.insertUser(edtUser.getText().toString(), edtPass.getText().toString());
                edtUser.setText("");
                edtPass.setText("");
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               ArrayList<User> list =  myDatabase.getUsers();
               for(int i=0;i<list.size();i++) {
                   Log.d("User", list.get(i).getName());
                   Log.d("Userpass",list.get(i).getPass());
               }
            }
        });
    }
}
