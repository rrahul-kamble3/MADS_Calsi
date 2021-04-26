package com.app.madscalsi.Entity;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;


public class Skill extends RealmObject {

    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_EXPRESSION = "expression";
    public static final String PROPERTY_RESULT = "result";

    public int id;
    @Required
    public String expression;
    @Required
    public String result;
}
