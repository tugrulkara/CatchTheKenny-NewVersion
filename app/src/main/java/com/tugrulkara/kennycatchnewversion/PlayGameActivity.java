package com.tugrulkara.kennycatchnewversion;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class PlayGameActivity extends AppCompatActivity {

    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    TextView time;
    TextView textView;
    TextView textView2;
    TextView canText;
    TextView baslangic;
    //Button button;
    Handler handler;
    Runnable runnable;
    SharedPreferences sharedPreferences;
    int Score=0;
    int oncekiScore=0;
    int can=3;

    Bitmap cartman;
    Bitmap kyle;
    Bitmap stoch;
    Bitmap tucker;
    Bitmap kenny;
    Bitmap bomba;
    Bitmap bitmap1;
    Bitmap bitmap2;
    MediaPlayer mp;
    MediaPlayer mp2;
    MediaPlayer truee;
    MediaPlayer falsee;
    MediaPlayer finishh;
    MediaPlayer bomb;

    ArrayList<Bitmap> charecters=new ArrayList<>();
    ArrayList<ImageView> imageViews=new ArrayList<>(9);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);

        MediaPlayer();
        imageView=findViewById(R.id.imageView);
        imageView2=findViewById(R.id.imageView2);
        imageView3=findViewById(R.id.imageView3);
        imageView4=findViewById(R.id.imageView4);
        imageView5=findViewById(R.id.imageView5);
        imageView6=findViewById(R.id.imageView6);
        imageView7=findViewById(R.id.imageView7);
        imageView8=findViewById(R.id.imageView8);
        imageView9=findViewById(R.id.imageView9);

        time=findViewById(R.id.time);
        textView=findViewById(R.id.score);
        textView2=findViewById(R.id.hightScore);
        canText=findViewById(R.id.canText);
        //button=findViewById(R.id.restart);
        canText.setText("Can: "+ can);
        baslangic=findViewById(R.id.baslangic);

        sharedPreferences= getSharedPreferences("com.tugrulkara.storingdata",MODE_PRIVATE);
        oncekiScore += sharedPreferences.getInt("oncekiScore",0);
        textView2.setText("En Yüksek Skor:"+oncekiScore);


        cartman= BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.cartman);
        kyle= BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.kyle);
        stoch= BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.stoch);
        tucker= BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.tucker);
        kenny= BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.kenny);
        bomba=BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.bomba);

        charecters.add(cartman);
        charecters.add(kenny);
        charecters.add(kyle);
        charecters.add(stoch);
        charecters.add(kenny);
        charecters.add(bomba);
        charecters.add(tucker);
        charecters.add(kenny);
        charecters.add(kyle);
        charecters.add(stoch);
        charecters.add(kenny);
        charecters.add(tucker);
        charecters.add(bomba);
        charecters.add(kenny);

        randomChar();

        imageViews.add(imageView);
        imageViews.add(imageView2);
        imageViews.add(imageView3);
        imageViews.add(imageView4);
        imageViews.add(imageView5);
        imageViews.add(imageView6);
        imageViews.add(imageView7);
        imageViews.add(imageView8);
        imageViews.add(imageView9);

        Invisible();

        //button.setEnabled(false);
        CountDownTimer countDownTimer=new CountDownTimer(6000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                baslangic.setText(""+millisUntilFinished/1000);


            }

            @Override
            public void onFinish() {

                baslangic.setVisibility(View.INVISIBLE);
                mp2=MediaPlayer.create(PlayGameActivity.this,R.raw.runn);
                mp2.start();
                mp2.setLooping(true);
                handler=new Handler();
                runnable=new Runnable() {
                    @Override
                    public void run() {

                        Invisible();
                        Random random=new Random();
                        int is=random.nextInt(9);

                        randomChar();

                        imageViews.get(is).setVisibility(View.VISIBLE);

                        bitmap1 =((BitmapDrawable)imageViews.get(is).getDrawable()).getBitmap();
                        bitmap2 =((BitmapDrawable)imageViews.get(is).getDrawable()).getBitmap();

                        handler.postDelayed(runnable,700);

                    }
                };
                handler.post(runnable);

            }
        }.start();
        /*new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                button.setEnabled(false);

                time.setText("Time: "+millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {

                Invisible();
                handler.removeCallbacks(runnable);
                button.setEnabled(true);
                time.setText("Süre Bitti");

                AlertDialog.Builder alert =new AlertDialog.Builder(MainActivity3.this);
                alert.setTitle("Oyun Bitti");
                alert.setMessage("Toplam Skorunuz: "+Score+"\n"+"Tekrar Oynamak İstermisiniz?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent=getIntent();
                        finish();
                        startActivity(intent);

                    }

                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(MainActivity3.this,"Game Over",Toast.LENGTH_LONG).show();
                    }
                });

                alert.show();

            }
        }.start();*/



    }

    public void randomChar(){

        Random random=new Random();
        int i=random.nextInt(13);

        imageView.setImageBitmap(charecters.get(i));
        imageView2.setImageBitmap(charecters.get(i));
        imageView3.setImageBitmap(charecters.get(i));
        imageView4.setImageBitmap(charecters.get(i));
        imageView5.setImageBitmap(charecters.get(i));
        imageView6.setImageBitmap(charecters.get(i));
        imageView7.setImageBitmap(charecters.get(i));
        imageView8.setImageBitmap(charecters.get(i));
        imageView9.setImageBitmap(charecters.get(i));

    }

    public void Kenny(View view) {


        if (bitmap1==kenny) {
            truee=MediaPlayer.create(PlayGameActivity.this,R.raw.truee);
            truee.start();
            Score++;
            textView.setText("Skor: " + Score);
            if (Score > oncekiScore) {
                sharedPreferences.edit().putInt("oncekiScore", Score).apply();
            }
        }else {
            if (bitmap2==bomba) {
                bomb=MediaPlayer.create(PlayGameActivity.this,R.raw.bomb);
                bomb.start();
                Score=0;
                textView.setText("Skor: " + Score);
            }else {
                falsee=MediaPlayer.create(PlayGameActivity.this,R.raw.falsee);
                falsee.start();
                can--;
                canText.setText("Can: " + can);

                if (can <= 0){
                    mp2.stop();
                    finishh=MediaPlayer.create(PlayGameActivity.this,R.raw.finishh);
                    finishh.start();
                    handler.removeCallbacks(runnable);
                    Invisible();
                    //button.setEnabled(true);

                    AlertDialog.Builder alert =new AlertDialog.Builder(PlayGameActivity.this);
                    alert.setTitle("Oyun Bitti");
                    alert.setMessage("Toplam Skorunuz: "+Score+"\n"+"Tekrar Oynamak İstermisiniz?");
                    alert.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {


                            Intent intent=getIntent();
                            finish();
                            startActivity(intent);

                        }

                    });
                    alert.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent=new Intent(PlayGameActivity.this,MainActivity.class);
                            startActivity(intent);
                            Toast.makeText(PlayGameActivity.this,"Game Over",Toast.LENGTH_LONG).show();
                        }
                    });

                    alert.show();

                }
            }


        }


    }

    /*public void Restart(View view){

        Intent intent=getIntent();
        finish();
        startActivity(intent);

    }*/

    public void Invisible(){

        for (ImageView image : imageViews){

            image.setVisibility(View.INVISIBLE);

        }

    }
    public  void MediaPlayer(){

        mp = MediaPlayer.create(PlayGameActivity.this, R.raw.runstart);
        mp.start();


    }
}