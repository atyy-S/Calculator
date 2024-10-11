package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView ResultDisplay, SolutionDisplay;
    MaterialButton buttonC,buttonBrackOpen,buttonBrackClose;
    MaterialButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    MaterialButton buttonPlus,buttonMult, buttonSub, buttonDiv,buttonEqual;
    MaterialButton buttonAC, buttonDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ResultDisplay=findViewById(R.id.result_display);
        SolutionDisplay=findViewById(R.id.sulution_display);
        assignID(buttonC,R.id.button_C);
        assignID(buttonBrackOpen,R.id.buttom_open_bracket);
        assignID(buttonBrackClose,R.id.buttom_close_bracket);
        assignID(button0,R.id.buttom_Zero);
        assignID(button1,R.id.buttom_1);
        assignID(button2,R.id.buttom_2);
        assignID(button3,R.id.button_3);
        assignID(button4,R.id.button_4);
        assignID(button5,R.id.buttom_5);
        assignID(button6,R.id.buttom_6);
        assignID(button7,R.id.button_7);
        assignID(button8,R.id.buttom_8);
        assignID(button9,R.id.buttom_9);

        assignID(buttonPlus,R.id.buttom_add);
        assignID(buttonMult,R.id.buttom_mult);
        assignID(buttonSub,R.id.buttom_min);
        assignID(buttonDiv,R.id.buttom_div);
        assignID(buttonEqual,R.id.buttom_equal);

        assignID(buttonAC,R.id.button_delate);
        assignID(buttonDot,R.id.buttom_dot);

    }

    /**
     * @param btn
     * @param id
     */
    void assignID (MaterialButton btn, int id){
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }
    public void onClick(View view){
        MaterialButton button = (MaterialButton) view;
        String buttonText =button.getText().toString();
        String dataToCalculate = SolutionDisplay.getText().toString();

        if (buttonText.equals("AC")){
            SolutionDisplay.setText(" ");
            ResultDisplay.setText("0");
            return;
        }
        if (buttonText.equals("=")){
            SolutionDisplay.setText(ResultDisplay.getText());
            return;
        }
        if (buttonText.equals("C")){
            dataToCalculate =dataToCalculate.substring(0,dataToCalculate.length()-1);
        }
        else{
            dataToCalculate= dataToCalculate+buttonText;
        }
        SolutionDisplay.setText(dataToCalculate);

        String finalResult = getresults(dataToCalculate);

        if (!finalResult.equals("Err")){
            ResultDisplay.setText(finalResult);
        }
    }
    String getresults(String data){
        try{
            Context contex=Context.enter();
            contex.setOptimizationLevel(-1);
            Scriptable scriptable =contex.initStandardObjects();
            return contex.evaluateString(scriptable,data, "Javascript", 1, null).toString();
        }
        catch (Exception e){
            return "Err";
        }
    }
}