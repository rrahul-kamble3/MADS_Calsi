package com.app.madscalsi.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.app.madscalsi.Adapter.HistoryAdapter;
import com.app.madscalsi.Entity.Employee;
import com.app.madscalsi.Entity.Skill;
import com.app.madscalsi.Model.HistoryModel;
import com.app.madscalsi.R;
import com.app.madscalsi.Utility.MyConstants;
import com.app.madscalsi.Utility.PrefManager;

import java.util.ArrayList;
import java.util.Collections;

import io.realm.Realm;
import io.realm.RealmResults;

public class HistoryActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private HistoryAdapter adapter;
    private Realm mRealm;
    private ArrayList<HistoryModel> models;
    private ArrayList<HistoryModel> lastTen;
    private PrefManager prefManager;
    private int userID;
    private         int count=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        recyclerView = findViewById(R.id.recyclerView);
        mRealm = Realm.getDefaultInstance();
        prefManager = new PrefManager(this);
        getHistory();


    }
    private void getHistory() {
        models = new ArrayList<>();
        lastTen = new ArrayList<>();
        userID = prefManager.getIntValue(this, MyConstants.USER_ID);
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Skill> results = realm.where(Skill.class).equalTo(Skill.PROPERTY_ID, userID).findAllSortedAsync(Skill.PROPERTY_ID);
                for (Skill history : results) {
                    count++;
                        HistoryModel model = new HistoryModel();
                        model.setId(userID);
                        model.setExpression(history.expression);
                        model.setResult(history.result);
                        models.add(model);
                }
            }
        });
        setRecyclerview();
    }

    private void setRecyclerview() {
        if (models.size()>0) {
            Collections.reverse(models);
            for (int i=0;i<models.size();i++){
                if (i<10) {
                    lastTen.add(models.get(i));
                }
            }
            recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            adapter = new HistoryAdapter(HistoryActivity.this,lastTen);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
        }
    }
}