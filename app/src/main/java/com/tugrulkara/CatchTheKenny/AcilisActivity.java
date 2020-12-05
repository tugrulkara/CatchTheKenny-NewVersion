package com.tugrulkara.CatchTheKenny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;

public class AcilisActivity extends AppCompatActivity {

    Runnable runnable;
    Handler handler;
    int sure=0;
    ProgressBar progressBar;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acilis);

        textView=findViewById(R.id.progressText);
        progressBar=findViewById(R.id.progressBar);
        handler=new Handler();
        sure=0;
        runnable=new Runnable() {
            @Override
            public void run() {
                sure++;
                progressBar.setProgress(sure);
                textView.setText("%"+sure+"/"+progressBar.getMax());
                handler.postDelayed(runnable,30);


                if (sure==100){
                    handler.removeCallbacks(runnable);
                    Intent intent=new Intent(AcilisActivity.this,MainActivity.class);
                    startActivity(intent);
                }
            }

        };
        handler.post(runnable);


    }
}