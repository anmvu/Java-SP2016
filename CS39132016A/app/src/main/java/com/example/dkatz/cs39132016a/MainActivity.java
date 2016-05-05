package com.example.dkatz.cs39132016a;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int i=0;
    int up=0;
    int down=0;
    ProgressBar pb;
    Button upButton;
    Button downButton;
    TextView iVal;
    TextView upVal;
    TextView downVal;
    TextView totalVal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        upButton = (Button) findViewById(R.id.upButton);
        downButton=(Button) findViewById(R.id.downButton);
        iVal = (TextView) findViewById(R.id.iVal);
        upVal = (TextView) findViewById(R.id.upVal);
        downVal = (TextView) findViewById(R.id.downVal);
        totalVal = (TextView) findViewById(R.id.totalVal);
        upButton.setOnClickListener(new Up());
        downButton.setOnClickListener(new Down());
        pb = (ProgressBar) findViewById(R.id.progressBar);
    }
    public class Up implements View.OnClickListener{
        public void onClick(View arg0) {
            i++;
            up++;
            iVal.setText("I=" + i);
            upVal.setText("Up=" + up);
            totalVal.setText("Total=" + (up+down));
            pb.setProgress(i);
        }
    }
    public class Down implements View.OnClickListener{
        public void onClick(View arg0) {
            Button b = (Button) arg0;
            if (b==downButton) {
                i--;
                down++;
                iVal.setText("I=" + i);
                downVal.setText("Down=" + down);
                totalVal.setText("Total=" + (up + down));
                pb.setProgress(i);
            }
        }
    }

}
