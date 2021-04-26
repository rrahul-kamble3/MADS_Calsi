package com.app.madscalsi.Activity;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.app.madscalsi.Entity.Skill;
import com.app.madscalsi.R;
import com.app.madscalsi.Utility.MyConstants;
import com.app.madscalsi.Utility.PrefManager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.exceptions.RealmPrimaryKeyConstraintException;


public class MainActivity extends Activity implements View.OnClickListener, View.OnTouchListener
{
    private int openParenthesis = 0;

    private boolean dotUsed = false;

    private boolean equalClicked = false;
    private String lastExpression = "";

    private final static int EXCEPTION = -1;
    private final static int IS_NUMBER = 0;
    private final static int IS_OPERAND = 1;
    private final static int IS_OPEN_PARENTHESIS = 2;
    private final static int IS_CLOSE_PARENTHESIS = 3;
    private final static int IS_DOT = 4;
    public final static int RESULT_HISTORY_PICKER = 102;
    private PrefManager prefManager;
    private int userID;

    Button buttonNumber0;
    Button buttonNumber1;
    Button buttonNumber2;
    Button buttonNumber3;
    Button buttonNumber4;
    Button buttonNumber5;
    Button buttonNumber6;
    Button buttonNumber7;
    Button buttonNumber8;
    Button buttonNumber9;

    Button buttonClear;
    ImageButton buttonParentheses;
    Button buttonDivision;
    Button buttonMultiplication;
    Button buttonSubtraction;
    Button buttonAddition;
    Button buttonEqual;
    Button buttonDot;

    TextView textViewInputNumbers;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViewVariables();
        setOnClickListeners();
        setOnTouchListener();
    }

    private void initializeViewVariables()
    {
        prefManager = new PrefManager(this);
        buttonNumber0 = (Button) findViewById(R.id.button_zero);
        buttonNumber1 = (Button) findViewById(R.id.button_one);
        buttonNumber2 = (Button) findViewById(R.id.button_two);
        buttonNumber3 = (Button) findViewById(R.id.button_three);
        buttonNumber4 = (Button) findViewById(R.id.button_four);
        buttonNumber5 = (Button) findViewById(R.id.button_five);
        buttonNumber6 = (Button) findViewById(R.id.button_six);
        buttonNumber7 = (Button) findViewById(R.id.button_seven);
        buttonNumber8 = (Button) findViewById(R.id.button_eight);
        buttonNumber9 = (Button) findViewById(R.id.button_nine);

        buttonClear = (Button) findViewById(R.id.button_clear);
        buttonParentheses = (ImageButton) findViewById(R.id.imgBtn_history);
        buttonDivision = (Button) findViewById(R.id.button_division);
        buttonMultiplication = (Button) findViewById(R.id.button_multiplication);
        buttonSubtraction = (Button) findViewById(R.id.button_subtraction);
        buttonAddition = (Button) findViewById(R.id.button_addition);
        buttonEqual = (Button) findViewById(R.id.button_equal);
        buttonDot = (Button) findViewById(R.id.button_dot);
        textViewInputNumbers = (TextView) findViewById(R.id.textView_input_numbers);
    }

    private void setOnClickListeners()
    {
        buttonNumber0.setOnClickListener(this);
        buttonNumber1.setOnClickListener(this);
        buttonNumber2.setOnClickListener(this);
        buttonNumber3.setOnClickListener(this);
        buttonNumber4.setOnClickListener(this);
        buttonNumber5.setOnClickListener(this);
        buttonNumber6.setOnClickListener(this);
        buttonNumber7.setOnClickListener(this);
        buttonNumber8.setOnClickListener(this);
        buttonNumber9.setOnClickListener(this);

        buttonClear.setOnClickListener(this);
        buttonParentheses.setOnClickListener(this);
        buttonDivision.setOnClickListener(this);
        buttonMultiplication.setOnClickListener(this);
        buttonSubtraction.setOnClickListener(this);
        buttonAddition.setOnClickListener(this);
        buttonEqual.setOnClickListener(this);
        buttonDot.setOnClickListener(this);
    }

    private void setOnTouchListener()
    {
        buttonNumber0.setOnTouchListener(this);
        buttonNumber1.setOnTouchListener(this);
        buttonNumber2.setOnTouchListener(this);
        buttonNumber3.setOnTouchListener(this);
        buttonNumber4.setOnTouchListener(this);
        buttonNumber5.setOnTouchListener(this);
        buttonNumber6.setOnTouchListener(this);
        buttonNumber7.setOnTouchListener(this);
        buttonNumber8.setOnTouchListener(this);
        buttonNumber9.setOnTouchListener(this);

        buttonClear.setOnTouchListener(this);
        buttonParentheses.setOnTouchListener(this);
        buttonDivision.setOnTouchListener(this);
        buttonMultiplication.setOnTouchListener(this);
        buttonSubtraction.setOnTouchListener(this);
        buttonAddition.setOnTouchListener(this);
        buttonDot.setOnTouchListener(this);
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.button_zero:
                if (addNumber("0")) equalClicked = false;
                break;
            case R.id.button_one:
                if (addNumber("1")) equalClicked = false;
                break;
            case R.id.button_two:
                if (addNumber("2")) equalClicked = false;
                break;
            case R.id.button_three:
                if (addNumber("3")) equalClicked = false;
                break;
            case R.id.button_four:
                if (addNumber("4")) equalClicked = false;
                break;
            case R.id.button_five:
                if (addNumber("5")) equalClicked = false;
                break;
            case R.id.button_six:
                if (addNumber("6")) equalClicked = false;
                break;
            case R.id.button_seven:
                if (addNumber("7")) equalClicked = false;
                break;
            case R.id.button_eight:
                if (addNumber("8")) equalClicked = false;
                break;
            case R.id.button_nine:
                if (addNumber("9")) equalClicked = false;
                break;
            case R.id.button_addition:
                if (addOperand("+")) equalClicked = false;
                break;
            case R.id.button_subtraction:
                if (addOperand("-")) equalClicked = false;
                break;
            case R.id.button_multiplication:
                if (addOperand("x")) equalClicked = false;
                break;
            case R.id.button_division:
                if (addOperand("\u00F7")) equalClicked = false;
                break;
            case R.id.button_dot:
                if (addDot()) equalClicked = false;
                break;
            case R.id.imgBtn_history:
                displayHistory();
                break;
            case R.id.button_clear:
                textViewInputNumbers.setText("");
                openParenthesis = 0;
                dotUsed = false;
                equalClicked = false;
                break;
            case R.id.button_equal:
                if (textViewInputNumbers.getText().toString() != null && !textViewInputNumbers.getText().toString().equals("")) {
                    if (validateInputs(textViewInputNumbers.getText().toString())) {
                        calculate(textViewInputNumbers.getText().toString());
                    }
                    else {
                        Toast.makeText(this, "Enter correct input for calculation", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }

    }

    public boolean validateInputs(String input) {
        String query = input.replaceAll("%", "/100").replaceAll("x", "*").replaceAll("[^\\x00-\\x7F]", "/").toString();
        boolean hasOperator = query.contains("*") || query.contains("+") || query.contains("/") || query.contains("-");
        ArrayList<String> operandList = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(query, "*+/-", true);
        while (st.hasMoreTokens()) {
            String token = st.nextToken();

            if ("*+/-".contains(token)) {
                continue;
            } else {
                operandList.add(token);
            }

        }
        return hasOperator && operandList.size()>=2 ;
    }

    private void displayHistory() {
        Intent intent=new Intent(MainActivity.this,HistoryActivity.class);
        startActivityForResult(intent, RESULT_HISTORY_PICKER);// Activity is started with requestCode 2
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(resultCode!=RESULT_CANCELED && requestCode==RESULT_HISTORY_PICKER )
        {
            String message=data.getStringExtra("sum");
            textViewInputNumbers.setText(message);
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent)
    {
        switch (motionEvent.getAction())
        {
            case MotionEvent.ACTION_DOWN:
            {
                view.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                view.invalidate();
                break;
            }
            case MotionEvent.ACTION_UP:
            {
                view.getBackground().clearColorFilter();
                view.invalidate();
                break;
            }
        }
        return false;
    }

    private boolean addDot()
    {
        boolean done = false;

        if (textViewInputNumbers.getText().length() == 0)
        {
            textViewInputNumbers.setText("0.");
            dotUsed = true;
            done = true;
        } else if (dotUsed == true)
        {
        } else if (defineLastCharacter(textViewInputNumbers.getText().charAt(textViewInputNumbers.getText().length() - 1) + "") == IS_OPERAND)
        {
            textViewInputNumbers.setText(textViewInputNumbers.getText() + "0.");
            done = true;
            dotUsed = true;
        } else if (defineLastCharacter(textViewInputNumbers.getText().charAt(textViewInputNumbers.getText().length() - 1) + "") == IS_NUMBER)
        {
            textViewInputNumbers.setText(textViewInputNumbers.getText() + ".");
            done = true;
            dotUsed = true;
        }
        return done;
    }

    private boolean addParenthesis()
    {
        boolean done = false;
        int operationLength = textViewInputNumbers.getText().length();

        if (operationLength == 0)
        {
            textViewInputNumbers.setText(textViewInputNumbers.getText() + "(");
            dotUsed = false;
            openParenthesis++;
            done = true;
        } else if (openParenthesis > 0 && operationLength > 0)
        {
            String lastInput = textViewInputNumbers.getText().charAt(operationLength - 1) + "";
            switch (defineLastCharacter(lastInput))
            {
                case IS_NUMBER:
                    textViewInputNumbers.setText(textViewInputNumbers.getText() + ")");
                    done = true;
                    openParenthesis--;
                    dotUsed = false;
                    break;
                case IS_OPERAND:
                    textViewInputNumbers.setText(textViewInputNumbers.getText() + "(");
                    done = true;
                    openParenthesis++;
                    dotUsed = false;
                    break;
                case IS_OPEN_PARENTHESIS:
                    textViewInputNumbers.setText(textViewInputNumbers.getText() + "(");
                    done = true;
                    openParenthesis++;
                    dotUsed = false;
                    break;
                case IS_CLOSE_PARENTHESIS:
                    textViewInputNumbers.setText(textViewInputNumbers.getText() + ")");
                    done = true;
                    openParenthesis--;
                    dotUsed = false;
                    break;
            }
        } else if (openParenthesis == 0 && operationLength > 0)
        {
            String lastInput = textViewInputNumbers.getText().charAt(operationLength - 1) + "";
            if (defineLastCharacter(lastInput) == IS_OPERAND)
            {
                textViewInputNumbers.setText(textViewInputNumbers.getText() + "(");
                done = true;
                dotUsed = false;
                openParenthesis++;
            } else
            {
                textViewInputNumbers.setText(textViewInputNumbers.getText() + "x(");
                done = true;
                dotUsed = false;
                openParenthesis++;
            }
        }
        return done;
    }

    private boolean addOperand(String operand)
    {
        boolean done = false;
        int operationLength = textViewInputNumbers.getText().length();
        if (operationLength > 0)
        {
            String lastInput = textViewInputNumbers.getText().charAt(operationLength - 1) + "";

            if ((lastInput.equals("+") || lastInput.equals("-") || lastInput.equals("*") || lastInput.equals("\u00F7") || lastInput.equals("%")))
            {
                Toast.makeText(getApplicationContext(), "Wrong format", Toast.LENGTH_LONG).show();
            } else if (operand.equals("%") && defineLastCharacter(lastInput) == IS_NUMBER)
            {
                textViewInputNumbers.setText(textViewInputNumbers.getText() + operand);
                dotUsed = false;
                equalClicked = false;
                lastExpression = "";
                done = true;
            } else if (!operand.equals("%"))
            {
                textViewInputNumbers.setText(textViewInputNumbers.getText() + operand);
                dotUsed = false;
                equalClicked = false;
                lastExpression = "";
                done = true;
            }
        } else
        {
            Toast.makeText(getApplicationContext(), "Wrong Format. Operand Without any numbers?", Toast.LENGTH_LONG).show();
        }
        return done;
    }

    private boolean addNumber(String number)
    {
        boolean done = false;
        int operationLength = textViewInputNumbers.getText().length();
        if (operationLength > 0)
        {
            String lastCharacter = textViewInputNumbers.getText().charAt(operationLength - 1) + "";
            int lastCharacterState = defineLastCharacter(lastCharacter);

            if (operationLength == 1 && lastCharacterState == IS_NUMBER && lastCharacter.equals("0"))
            {
                textViewInputNumbers.setText(number);
                done = true;
            } else if (lastCharacterState == IS_OPEN_PARENTHESIS)
            {
                textViewInputNumbers.setText(textViewInputNumbers.getText() + number);
                done = true;
            } else if (lastCharacterState == IS_CLOSE_PARENTHESIS || lastCharacter.equals("%"))
            {
                textViewInputNumbers.setText(textViewInputNumbers.getText() + "x" + number);
                done = true;
            } else if (lastCharacterState == IS_NUMBER || lastCharacterState == IS_OPERAND || lastCharacterState == IS_DOT)
            {
                textViewInputNumbers.setText(textViewInputNumbers.getText() + number);
                done = true;
            }
        } else
        {
            textViewInputNumbers.setText(textViewInputNumbers.getText() + number);
            done = true;
        }
        return done;
    }


    private void calculate(String input){
        String result = "";
        String temp = input;
        if (equalClicked)
        {
            temp = input + lastExpression;
        } else
        {
            saveLastExpression(input);
        }
        String query = temp.replaceAll("%", "/100").replaceAll("x", "*").replaceAll("[^\\x00-\\x7F]", "/").toString();
        String myString =query;

        ArrayList<String> operatorList = new ArrayList<String>();
        ArrayList<String> operandList = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(myString, "*+/-", true);
        while (st.hasMoreTokens()) {
            String token = st.nextToken();

            if ("*+/-".contains(token)) {
                operatorList.add(token);
            } else {
                operandList.add(token);
            }
        }

        //Multi
        for (int i = 0; i<operatorList.size(); i++){
            if (operatorList.size()>0) {

                if (operatorList.get(i).equals("*")){
                    int preValue = i;
                    int postValue = i+1;
                    double result_multiplication = Double.parseDouble(operandList.get(i)) * Double.parseDouble(operandList.get(postValue));
                    operatorList.remove(preValue);
                    operandList.set(preValue,Double.toString(result_multiplication));
                    operandList.remove(postValue);
                    i--;
                }

            }
        }

        //Add
        for (int i = 0; i<operatorList.size(); i++){
            if (operatorList.size()>0) {

                if (operatorList.get(i).equals("+")){
                    int preValue = i;
                    int postValue = i+1;
                    double result_addition = Double.parseDouble(operandList.get(i)) + Double.parseDouble(operandList.get(postValue));
                    operatorList.remove(preValue);
                    operandList.set(preValue,Double.toString(result_addition));
                    operandList.remove(postValue);
                    i--;
                }

            }
        }

        //Div
        int divCount=operatorList.size();
        for (int i = 0; i<divCount; i++){
            if (operatorList.get(i).equals("/")){
                int preValue = i;
                int postValue = i+1;
                double result_division = Double.parseDouble(operandList.get(i)) / Double.parseDouble(operandList.get(postValue));
                operandList.set(preValue,Double.toString(result_division));
                operandList.remove(postValue);
                operatorList.remove(preValue);
                i--;
            }

            divCount=operatorList.size();

//            Log.d("herejhgush",divCount+"___"+operatorList.get(i));

        }

        //Sub
        for (int i = 0; i<operatorList.size(); i++){
            if (operatorList.size()>0) {

                if (operatorList.get(i).equals("-")){
                    int preValue = i;
                    int postValue = i+1;
                    double result_subtraction = Double.parseDouble(operandList.get(i)) - Double.parseDouble(operandList.get(postValue));
                    operatorList.remove(preValue);
                    operandList.set(preValue,Double.toString(result_subtraction));
                    operandList.remove(postValue);
                    i--;
                }

            }
        }
        String finalResult = operandList.get(0);
        textViewInputNumbers.setText(finalResult);


        System.out.println("rslt___Operators________:" + operatorList);
        System.out.println("rslt___Operands_________:" + operandList);
        saveHistory(input,finalResult);

    }

    private void saveHistory(String input, String finalResult) {
        userID = prefManager.getIntValue(this, MyConstants.USER_ID);

        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    try {
                        if (!input.trim().isEmpty()) {
                            Skill skill = new Skill();
                            Number currentIdNum = realm.where(Skill.class).max(Skill.PROPERTY_ID);
                            int nextId;
                            if(currentIdNum == null) {
                                nextId = 1;
                            } else {
                                nextId = currentIdNum.intValue() + 1;
                            }

                            skill.id = userID;
                            skill.expression = input;
                            skill.result = finalResult;

                            realm.insert(skill);
                        }

                    } catch (RealmPrimaryKeyConstraintException e) {
                        Toast.makeText(getApplicationContext(), "Primary Key exists, Press Update instead", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } finally {
            if (realm != null) {
                realm.close();
            }
        }
    }


    private void saveLastExpression(String input)
    {
        String lastOfExpression = input.charAt(input.length() - 1) + "";
        if (input.length() > 1)
        {
            if (lastOfExpression.equals(")"))
            {
                lastExpression = ")";
                int numberOfCloseParenthesis = 1;

                for (int i = input.length() - 2; i >= 0; i--)
                {
                    if (numberOfCloseParenthesis > 0)
                    {
                        String last = input.charAt(i) + "";
                        if (last.equals(")"))
                        {
                            numberOfCloseParenthesis++;
                        } else if (last.equals("("))
                        {
                            numberOfCloseParenthesis--;
                        }
                        lastExpression = last + lastExpression;
                    } else if (defineLastCharacter(input.charAt(i) + "") == IS_OPERAND)
                    {
                        lastExpression = input.charAt(i) + lastExpression;
                        break;
                    } else
                    {
                        lastExpression = "";
                    }
                }
            } else if (defineLastCharacter(lastOfExpression + "") == IS_NUMBER)
            {
                lastExpression = lastOfExpression;
                for (int i = input.length() - 2; i >= 0; i--)
                {
                    String last = input.charAt(i) + "";
                    if (defineLastCharacter(last) == IS_NUMBER || defineLastCharacter(last) == IS_DOT)
                    {
                        lastExpression = last + lastExpression;
                    } else if (defineLastCharacter(last) == IS_OPERAND)
                    {
                        lastExpression = last + lastExpression;
                        break;
                    }
                    if (i == 0)
                    {
                        lastExpression = "";
                    }
                }
            }
        }
    }

    private int defineLastCharacter(String lastCharacter)
    {
        try
        {
            Integer.parseInt(lastCharacter);
            return IS_NUMBER;
        } catch (NumberFormatException e)
        {
        }

        if ((lastCharacter.equals("+") || lastCharacter.equals("-") || lastCharacter.equals("x") || lastCharacter.equals("\u00F7") || lastCharacter.equals("%")))
            return IS_OPERAND;

        if (lastCharacter.equals("("))
            return IS_OPEN_PARENTHESIS;

        if (lastCharacter.equals(")"))
            return IS_CLOSE_PARENTHESIS;

        if (lastCharacter.equals("."))
            return IS_DOT;

        return -1;
    }



}
