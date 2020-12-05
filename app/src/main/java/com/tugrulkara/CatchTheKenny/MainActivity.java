package com.tugrulkara.CatchTheKenny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button start;
    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    RadioButton low;
    RadioButton medium;
    RadioButton hard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start=findViewById(R.id.start);
        imageView=findViewById(R.id.imagefirst);
        imageView2=findViewById(R.id.southimage);
        imageView3=findViewById(R.id.imagesecond);
        low=findViewById(R.id.low);
        medium=findViewById(R.id.medium);
        hard=findViewById(R.id.hard);

    }
    public void start(View view){

        if (low.isChecked()){
            Intent intent=new Intent(MainActivity.this, PlayActivity.class);
            intent.putExtra("info","low");
            startActivity(intent);
        }
        else if (medium.isChecked()){

            Intent intent=new Intent(MainActivity.this, PlayActivity.class);
            intent.putExtra("info","medium");
            startActivity(intent);

        }
        else if (hard.isChecked()){

            Intent intent=new Intent(MainActivity.this, PlayActivity.class);
            intent.putExtra("info","hard");
            startActivity(intent);

        }
        else {

            Toast.makeText(MainActivity.this,"Lütfen bir seviye seçiniz",Toast.LENGTH_LONG).show();

        }

    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }


}