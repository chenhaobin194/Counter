package com.example.administrator.counter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class conut extends AppCompatActivity{
    int havepoint =0;//检测是否已经有点
    boolean flag = true;
    double num1=0,num2=0;//运算的第一和第二个数
    int option=0;//运算符状态
    double sum=0;//得数
    double sumtype=0;//得数是小数与否：0为整数，非0为小数
    boolean admitcount=false;
    int adjustnum2=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conut);

        //功能按键
        Button btnAdd = (Button)findViewById(R.id.jia);
        Button btnSub = (Button)findViewById(R.id.jian);
        Button btnMul= (Button)findViewById(R.id.chengyi);
        Button btndiv = (Button)findViewById(R.id.chuyi);
        Button btnEqual = (Button)findViewById(R.id.dengyu);
        Button btnClear = (Button)findViewById(R.id.ac);
        Button btnC = (Button)findViewById(R.id.aad);
        
        //数字及小数点按钮
        Button btn1 = (Button)findViewById(R.id.one);
        Button btn2 = (Button)findViewById(R.id.two);
        Button btn3 = (Button)findViewById(R.id.three);
        Button btn4 = (Button)findViewById(R.id.four);
        Button btn5 = (Button)findViewById(R.id.five);
        Button btn6 = (Button)findViewById(R.id.six);
        Button btn7 = (Button)findViewById(R.id.seven);
        Button btn8 = (Button)findViewById(R.id.eight);
        Button btn9 = (Button)findViewById(R.id.neight);
        Button btn0 = (Button)findViewById(R.id.zero);
        Button btn_point = (Button)findViewById(R.id.point);

        //监听器
        btn0.setOnClickListener(listenter);
        btn1.setOnClickListener(listenter);
        btn2.setOnClickListener(listenter);
        btn3.setOnClickListener(listenter);
        btn4.setOnClickListener(listenter);
        btn5.setOnClickListener(listenter);
        btn6.setOnClickListener(listenter);
        btn7.setOnClickListener(listenter);
        btn8.setOnClickListener(listenter);
        btn9.setOnClickListener(listenter);
        btnAdd.setOnClickListener(listenter);
        btnSub.setOnClickListener(listenter);
        btnMul.setOnClickListener(listenter);
        btndiv.setOnClickListener(listenter);
        btnEqual.setOnClickListener(listenter);
        btnClear.setOnClickListener(listenter);
        btn_point.setOnClickListener(listenter);
        btnC.setOnClickListener(listenter);

    }
    OnClickListener listenter = new OnClickListener() {
        @Override
        public void onClick(View v) {
            EditText showText = (EditText)findViewById(R.id.textView);
            String t = showText.getText().toString();
            Button btn = (Button)v;
            String b = btn.getText().toString();
            //输入数字或小数点
            if (btn.getId()==R.id.one||btn.getId()==R.id.two||btn.getId()==R.id.three||btn.getId()==R.id.four||btn.getId()==R.id.five||
                    btn.getId()==R.id.six||btn.getId()==R.id.seven||btn.getId()==R.id.eight||btn.getId()==R.id.neight||btn.getId()==R.id.zero||
                    (btn.getId()==R.id.point&&havepoint==0))
            {
                    if(adjustnum2==1||adjustnum2==2)
                    {
                        showText.setText("");
                        adjustnum2 = 0;
                        t = new String();
                    }

                    if (btn.getId() == R.id.point) {
                        if (null == t || t.equals("")) {
                            t += "0" + btn.getText();
                            showText.setText(t);
                        } else {
                            t += btn.getText();
                        }
                        havepoint = 1;

                    } else {
                        t += btn.getText();
                    }
                    showText.setText(t);


            }
            //运算
            if(btn.getId()==R.id.jia||btn.getId()==R.id.jian||btn.getId()==R.id.chengyi||btn.getId()==R.id.chuyi)
            {
                admitcount=true;
                adjustnum2=1;

                havepoint=0;
                if (t==null||t.equals(""))
                {
                    t="0";
                    showText.setText(t);
                }
                if(option!=0)
                {
                        num2 = Double.valueOf(t);
                        switch (option) {
                            case 1:
                                sum = num1 + num2;
                                break;
                            case 2:
                                sum = num1 - num2;
                                break;
                            case 3:
                                sum = num1 * num2;
                                break;
                            case 4:
                                if (num2 == 0) {
                                    showText.setText("Error");
                                    break;
                                }
                                sum = num1 / num2;
                                break;
                            default:
                                break;
                        }
                        num1 = sum;
                }
                if(option==0){
                    num1 = Double.valueOf(t);
                }
                switch(btn.getId()){
                    case R.id.jia:
                        option = 1;
                        break;
                    case R.id.jian:
                        option = 2;
                        break;
                    case R.id.chengyi:
                        option = 3;
                        break;
                    case R.id.chuyi:
                        option = 4;
                        break;
                    default:
                        break;
                }

            }
            // =按钮
            if(admitcount)
            if (btn.getId() == R.id.dengyu) {
                adjustnum2=2;
                if(admitcount) {
                    if (t == null || t.equals("")) {
                        t = "0";
                    }
                    num2 = Double.valueOf(t);
                    switch (option) {
                        case 1:
                            sum = num1 + num2;
                            break;
                        case 2:
                            sum = num1 - num2;
                            break;
                        case 3:
                            sum = num1 * num2;
                            break;
                        case 4:
                            if (num2 == 0) {
                                showText.setText("Error");
                                flag = false;
                                break;
                            }
                            sum = num1 / num2;
                            break;
                        default:
                            break;
                    }
                    sumtype = sum % 1;
                    if (sumtype > 0) {
                        havepoint = 1;
                    }
                    t = "" + sum;
                    if (sumtype == 0) {   //如果得数是整数，取第一位到小数点前以为间的字符，转换为数字
                        int end = (t.toString()).lastIndexOf(".");
                        String str = (t.toString()).substring(0, end);
                        t = "" + Integer.parseInt(str);
                        havepoint = 0;
                    }
                    if (flag) {
                        showText.setText(t);
                    }
                    num1 = Double.valueOf(t);
                    num2 = 0;//-----------
                    option = 0;
                    flag = true;
                }
            }
            //清除
            if(btn.getId()==R.id.ac){
                showText.setText("");
                havepoint=0;
                option=0;
                flag=true;
                adjustnum2=0;
                num1=0;
                num2=0;
                sum=0;
            }
        }

    };

}
