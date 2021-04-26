package com.app.madscalsi.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.madscalsi.Entity.Employee;
import com.app.madscalsi.R;
import com.app.madscalsi.Utility.MyConstants;
import com.app.madscalsi.Utility.PrefManager;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import io.realm.exceptions.RealmPrimaryKeyConstraintException;

public class LoginActivity extends AppCompatActivity {
    Realm mRealm;
    private Button btnLogin;
    private EditText etUsername,etPassword;
    private PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializeView();
        mRealm = Realm.getDefaultInstance();
        readEmployeeRecords();
    }

    private void initializeView() {
        btnLogin = findViewById(R.id.button_two_button);
        etUsername = findViewById(R.id.username);
        etPassword = findViewById(R.id.password);
        prefManager = new PrefManager(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                mRealm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        RealmResults<Employee> results = realm.where(Employee.class).equalTo(Employee.PROPERTY_USERNAME, username).findAllSortedAsync(Employee.PROPERTY_ID);
                        for (Employee employee : results) {
                            if (employee.password.equals(password)){
                                prefManager.saveInt(LoginActivity.this, MyConstants.USER_ID,employee.id);
                                prefManager.saveString(LoginActivity.this,MyConstants.USER_NAME,employee.username);
                                Toast.makeText(LoginActivity.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                            }
                            else {
                                Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }
        });
    }
    public String validate(String userName, String password)
    {
        if(userName.equals("admin") && password.equals("pass@123"))
            return "Login is successful";
        else
            return "Invalid login!";
    }
    private void readEmployeeRecords() {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                RealmResults<Employee> results = realm.where(Employee.class).findAll();
                if (results.isEmpty()) {
                    insertTestLogin();
                }
            }
        });
    }

    private void insertTestLogin() {
        ArrayList<Integer> userID = new ArrayList<>();
        ArrayList<String> userName = new ArrayList<>();
        ArrayList<String> userPassword = new ArrayList<>();

        userID.add(1);
        userID.add(2);
        userID.add(3);

        userName.add("admin");
        userName.add("mads");
        userName.add("calsi");

        userPassword.add("pass@123");
        userPassword.add("pass@456");
        userPassword.add("pass@789");

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<userID.size();i++) {

                    Realm realm = null;
                    try {
                        realm = Realm.getDefaultInstance();
                        int finalI = i;
                        realm.executeTransaction(new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {


                                try {


                                    if (!userName.get(finalI).toString().trim().isEmpty()) {
                                        Employee employee = new Employee();
                                        employee.id = userID.get(finalI);
                                        employee.username = userName.get(finalI);
                                        employee.password = userPassword.get(finalI);

                                        realm.insertOrUpdate(employee);
                                    }

                                    if (realm != null) {
                                        realm.close();
                                    }

                                } catch (RealmPrimaryKeyConstraintException e) {
                                    Toast.makeText(getApplicationContext(), "Primary Key exists, Press Update instead", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } catch (Exception e){

                    }
                }
            }
        },100);

    }


}