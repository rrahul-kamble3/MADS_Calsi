package com.app.madscalsi.Entity;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Employee extends RealmObject {


    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_USERNAME = "username";
    public static final String PROPERTY_PASSWORD = "password";

    @PrimaryKey
    public int id;
    @Required
    public String username;
    @Required
    public String password;

}
