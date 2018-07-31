package com.guide.travel.io.respay.threemilan;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button [][]button=new Button[3][3];
    public static final Random RANDOM = new Random();
    ImageView coin;
    public  int select=0;
    public int move =0;
    public Button previousButton;
    public int [][]maxValueForAImove=new int [3][3];
    public int minI=0;
    public int minJ=0;
    public int maxI=0;
    public int maxJ=0;
    public int swapMin =10;
    public int swapMax =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button[0][0]=(Button)findViewById(R.id.row1button1);
        button[0][1]=(Button)findViewById(R.id.row1button2);
        button[0][2]=(Button)findViewById(R.id.row1button3);
        button[1][0]=(Button)findViewById(R.id.row2button1);
        button[1][1]=(Button)findViewById(R.id.row2button2);
        button[1][2]=(Button)findViewById(R.id.row2button3);
        button[2][0]=(Button)findViewById(R.id.row3button1);
        button[2][1]=(Button)findViewById(R.id.row3button2);
        button[2][2]=(Button)findViewById(R.id.row3button3);
        Button takeToss =(Button)findViewById(R.id.flip);
        coin = (ImageView)findViewById(R.id.coin);

        takeToss.setOnClickListener(new View.OnClickListener() {
            int randomvalue=RANDOM.nextInt(2)+1;
            @Override
            public void onClick(View view) {
                flipCoin(randomvalue);
                setBoard();
            }
        });
        button[0][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(select==0){
                    selectButtonForMove(button[0][0]);
                }
                else{
                    userAction(button[0][0]);
                }
            }
        });
        button[0][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(select==0){
                    selectButtonForMove(button[0][1]);
                }
                else{
                    userAction(button[0][1]);
                }
            }
        });
        button[0][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(select==0){
                    selectButtonForMove(button[0][2]);
                }
                else{
                    userAction(button[0][2]);
                }
            }
        });
        button[1][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(select==0){
                    selectButtonForMove(button[1][0]);
                }
                else{
                    userAction(button[1][0]);
                }
            }
        });
        button[1][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(select==0){
                    selectButtonForMove(button[1][1]);
                }
                else{
                    userAction(button[1][1]);
                }
            }
        });
        button[1][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(select==0){
                    selectButtonForMove(button[1][2]);
                }
                else{
                    userAction(button[1][2]);
                }
            }
        });
        button[2][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(select==0){
                    selectButtonForMove(button[2][0]);
                }
                else{
                    userAction(button[2][0]);
                }
            }
        });
        button[2][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(select==0){
                    selectButtonForMove(button[2][1]);
                }
                else{
                    userAction(button[2][1]);
                }
            }
        });
        button[2][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(select==0){
                    selectButtonForMove(button[2][2]);
                }
                else{
                    userAction(button[2][2]);
                }
            }
        });
    }

    private void selectButtonForMove(Button selectedButton) {
        String selectedButtonString=selectedButton.getText().toString();
        if(selectedButtonString.length()!=0 && selectedButtonString!="Y"){
            select=1;
            previousButton=selectedButton;
            selectedButton.setBackgroundResource(R.drawable.selectedbutton);
        }
        else{
            toastMessage("You can't select this button");
        }
    }
    private void toastMessage(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void userAction(Button selectedButton){
        if(selectedButton.getText().toString().length()==0){
            previousButton.setBackgroundResource(R.drawable.button);
            previousButton.setText("");
            selectedButton.setText("X");
            selectedButton.setTextColor(Color.BLUE);
            select=0;
            artificialIntelligenceAction();
            findMinAndMax();
            moveArtificialIntelligence();
        }
        else if(selectedButton.getText().toString()=="Y"){
            toastMessage("You can't move here");
        }
        else{
            previousButton.setBackgroundResource(R.drawable.button);
            select=0;
            selectButtonForMove(selectedButton);
        }
    }

    private void moveArtificialIntelligence() {
        /*if(minI==0&&minJ==0){
            if(button[minI][minJ+1].getText().toString().length()==0 && maxValueForAImove[minI][minJ+1]==maxValueForAImove[maxI][maxJ]){
                button[minI][minJ+1].setText("Y");
                button[minI][minJ].setText("");
            }
            else if(button[minI+1][minJ+1].getText().toString().length()==0 && maxValueForAImove[minI+1][minJ+1]==maxValueForAImove[maxI][maxJ]){
                button[minI+1][minJ+1].setText("Y");
                button[minI][minJ].setText("");
            }
            else if(button[minI+1][minJ].getText().toString().length()==0 && maxValueForAImove[minI+1][minJ]==maxValueForAImove[maxI][maxJ]){
                button[minI+1][minJ].setText("Y");
                button[minI][minJ].setText("");
            }
        }*/
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                Log.d("","This is min and max value "+maxValueForAImove[i][j]+" of "+i+" "+j);
                maxValueForAImove[i][j]=0;
            }
        }


    }


    private void findMinAndMax() {
        minI=0;
        minJ=0;
        maxJ=0;
        maxI=0;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(button[i][j].getText().toString()=="Y"){
                    if(swapMin>maxValueForAImove[i][j]){
                        minI=i;
                        minJ=j;
                        swapMin=maxValueForAImove[i][j];
                        Log.d("Min value",""+swapMin+minI+minJ);
                    }
                }
                else if(button[i][j].getText().toString().length()==0){
                    if(swapMax<=maxValueForAImove[i][j]){
                        maxI=i;
                        maxJ=j;
                        swapMax=maxValueForAImove[i][j];
                        Log.d("Max value",""+swapMax+maxI+maxJ);
                    }

                }
                else {
                    maxValueForAImove[i][j]=0;
                }
            }
        }
    }


    private void artificialIntelligenceAction() {
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                boolean []check=new boolean[4];
                check[0]= (i==0 && j==1);
                check[1]= (i==1 && j==0);
                check[2]= (i==2 && j==1);
                check[3]= (i==1 && j==2);
                if(check[0]||check[1]||check[2]||check[3]) {
                    for (int k = j + 1; k < 3; k++) {
                        if (button[i][k].getText().toString() == "X") {
                            maxValueForAImove[i][k]=0;
                            maxValueForAImove[i][j]++;
                        }
                    }
                    for (int k = j - 1; k >= 0; k--) {
                        if (button[i][k].getText().toString() == "X") {
                            maxValueForAImove[i][k]=0;
                            maxValueForAImove[i][j]++;
                        }
                    }
                    for (int k = i + 1; k < 3; k++) {
                        if (button[k][j].getText().toString() == "X") {
                            maxValueForAImove[k][j]=0;
                            maxValueForAImove[i][j]++;
                        }
                    }
                    for (int k = i - 1; k >= 0; k--) {
                        if (button[k][j].getText().toString() == "X") {
                            maxValueForAImove[k][j]=0;
                            maxValueForAImove[i][j]++;
                        }
                    }
                }
                else {
                    for (int k = j + 1; k < 3; k++) {
                        if (button[i][k].getText().toString() == "X") {
                            maxValueForAImove[i][k]=0;
                            maxValueForAImove[i][j]++;
                        }
                    }
                    for (int k = j - 1; k >= 0; k--) {
                        if (button[i][k].getText().toString() == "X") {
                            maxValueForAImove[i][k]=0;
                            maxValueForAImove[i][j]++;
                        }
                    }
                    for (int k = i + 1; k < 3; k++) {
                        if (button[k][j].getText().toString() == "X") {
                            maxValueForAImove[k][j]=0;
                            maxValueForAImove[i][j]++;
                        }
                    }
                    for (int k = i - 1; k >= 0; k--) {
                        if (button[k][j].getText().toString() == "X") {
                            maxValueForAImove[k][j]=0;
                            maxValueForAImove[i][j]++;
                        }
                    }
                    for (int k = i + 1; k < 3; k++) {

                        if (button[k][k].getText().toString() == "X" && j!=2) {
                            maxValueForAImove[k][k]=0;
                            maxValueForAImove[i][j]++;
                        }
                    }
                    for (int k = i - 1; k >= 0; k--) {
                        if (button[k][k].getText().toString() == "X" && j!=0) {
                            maxValueForAImove[k][k]=0;
                            maxValueForAImove[i][j]++;
                        }
                    }
                    int l=j;
                    for (int k = i - 1; k >= 0; k--) {
                        l++;
                        if(l>2)
                            break;
                        else if (button[k][l].getText().toString() == "X") {
                            maxValueForAImove[k][l]=0;
                            maxValueForAImove[i][j]++;
                        }
                    }
                    l=j;
                    for (int k = i + 1; k < 3; k++) {
                        l--;
                        if(l<0)
                            break;
                        else if (button[k][l].getText().toString() == "X") {
                            maxValueForAImove[k][l]=0;
                            maxValueForAImove[i][j]++;
                        }
                    }
                }
            }
        }
    }

    private void setBoard() {
        for(int i=0;i<3;i+=2){
            for(int j=0;j<3;j++){
                if(i==0){
                    button[i][j].setText("X");
                    button[i][j].setTextColor(Color.BLUE);
                }
                else {
                    button[i][j].setText("Y");
                    button[i][j].setTextColor(Color.BLACK);
                }
            }
        }
    }

    private void flipCoin(final int randomvalue) {

        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setDuration(1000);
        fadeOut.setFillAfter(true);
        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                coin.setImageResource(randomvalue== 1 ? R.drawable.tail : R.drawable.head);
                Animation fadeIn = new AlphaAnimation(0, 1);
                fadeIn.setInterpolator(new DecelerateInterpolator());
                fadeIn.setDuration(3000);
                fadeIn.setFillAfter(true);
                coin.startAnimation(fadeIn);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        coin.startAnimation(fadeOut);
    }
}
