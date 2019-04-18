package com.example.threeinarow;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "MainActivity";
    private Button button11;
    private Button button12;
    private Button button13;

    private Button button21;
    private Button button22;
    private Button button23;

    private Button button31;
    private Button button32;
    private Button button33;

    private Button replayButton;
    private Button quitButton;

    private TextView tipText;

    private List<Button> buttonList = new ArrayList<Button>();

    private int flag = 0;   //为0时，表明当前要下的棋子为O，为1时则下X

    private int times = 0;  //当下到第5步时才开始判断胜负

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button11 = initButton(R.id.button11);
        button12 = initButton(R.id.button12);
        button13 = initButton(R.id.button13);

        button21 = initButton(R.id.button21);
        button22 = initButton(R.id.button22);
        button23 = initButton(R.id.button23);

        button31 = initButton(R.id.button31);
        button32 = initButton(R.id.button32);
        button33 = initButton(R.id.button33);

        replayButton = findViewById(R.id.replayButton);
        replayButton.setOnClickListener(this);

        quitButton = findViewById(R.id.quitButton);
        quitButton.setOnClickListener(this);

        tipText = findViewById(R.id.tipText);
    }

    //获取按钮并初始化，然后把按钮添加进按钮列表
    public Button initButton(int id){
        Button b = findViewById(id);
        b.setOnClickListener(this);
        buttonList.add(b);
        return b;
    }

    //清空棋子
    public void clearAll(){
        for(Button one:buttonList){
            one.setText("");
        }
        flag = 0;
        times = 0;
        tipText.setText("O方先走");
    }

    public void success(String XOrO){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage(XOrO+"胜！");
        dialog.show();
        clearAll();
    }

    //将原来的一维按钮列表的字符串转化为二维字符串数组
    public String[][] getTwoD(){
        String[][] twoD = new String[3][3];
        int k = 0;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                twoD[i][j] = String.valueOf(buttonList.get(k).getText());
                k++;
            }
        }
        Log.d(TAG, "展示一下该二维数组：");
        for(int i = 0; i < 3; i++){
            System.out.println(twoD[i][0]+twoD[i][1]+twoD[i][2]);
        }
        return twoD;
    }

    //判断是否三连
    public void judge(){
        String[][] twoD = getTwoD();
        //判断主对角线
        if(!twoD[0][0].equals("")){
            if(twoD[0][0].equals(twoD[1][1])){
                if(twoD[0][0].equals(twoD[2][2])){
                    Log.d(TAG, "judge: 主对角线胜"+twoD[0][0]);
//                    Toast.makeText(this, twoD[0][0]+"胜！", Toast.LENGTH_SHORT).show();
                    success(twoD[0][0]);
                }
            }
        }
        //判断副对角线
        if(!twoD[0][2].equals("")) {
            if (twoD[0][2].equals(twoD[1][1])) {
                if (twoD[0][2].equals(twoD[2][0])) {
                    success(twoD[0][2]);
                }
            }
        }
        //判断横轴
        for(int i = 0; i < 3; i++) {
            if(!twoD[i][0].equals("")) {
                if (twoD[i][0].equals(twoD[i][1])) {
                    if (twoD[i][0].equals(twoD[i][2])) {
                        success(twoD[i][0]);
                    }
                }
            }
        }
        //判断纵轴
        for(int i = 0; i < 3; i++) {
            if(!twoD[0][i].equals("")) {
                if (twoD[0][i].equals(twoD[1][i])) {
                    if (twoD[0][i].equals(twoD[2][i])) {
                        success(twoD[0][i]);
                    }
                }
            }
        }
    }

    //下棋
    public void playChess(Button b){
        if(b.getText().equals("")){
            times++;
            if(flag == 0){
                b.setText("O");
                flag = 1;
                tipText.setText("现在X方走");
            }else{
                b.setText("X");
                flag = 0;
                tipText.setText("现在O方走");
            }
            if(times > 4){
                judge();
            }
        }else{
            Toast.makeText(this, "不能悔棋", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button11:
                Log.d(TAG, "button11被点击，为："+button11.getText());
                playChess(button11);
                break;
            case R.id.button12:
                Log.d(TAG, "button12被点击，为："+button12.getText());
                playChess(button12);
                break;
            case R.id.button13:
                Log.d(TAG, "button13被点击，为："+button13.getText());
                playChess(button13);
                break;
            case R.id.button21:
                Log.d(TAG, "button21被点击，为："+button21.getText());
                playChess(button21);
                break;
            case R.id.button22:
                Log.d(TAG, "button22被点击，为："+button22.getText());
                playChess(button22);
                break;
            case R.id.button23:
                Log.d(TAG, "button23被点击，为："+button23.getText());
                playChess(button23);
                break;
            case R.id.button31:
                Log.d(TAG, "button31被点击，为："+button31.getText());
                playChess(button31);
                break;
            case R.id.button32:
                Log.d(TAG, "button32被点击，为："+button32.getText());
                playChess(button32);
                break;
            case R.id.button33:
                Log.d(TAG, "button33被点击，为："+button33.getText());
                playChess(button33);
                break;
            case R.id.replayButton:
                Log.d(TAG, "replayButton被点击，为："+replayButton.getText());
                clearAll();
                break;
            case R.id.quitButton:
                Log.d(TAG, "quitButton被点击，为："+quitButton.getText());
                finish();
                super.onDestroy();
                break;
            default:
                break;
        }
    }
}

