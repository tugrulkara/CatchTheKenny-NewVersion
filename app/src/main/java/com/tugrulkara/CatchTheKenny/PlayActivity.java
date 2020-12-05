package com.tugrulkara.CatchTheKenny;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class PlayActivity extends AppCompatActivity {

    ImageView imageView,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9,like1,like2,like3;

    TextView textView,textView2,baslangicText,seviyeText;

    Handler handler;
    Runnable runnable;

    SharedPreferences sharedPreferences;

    int score=0;
    int oncekiScore=0;
    int highScore=0;
    int can=3;

    Bitmap cartman,kyle,stoch,tucker,kenny,bomba,bitmap1,bitmap2;

    MediaPlayer mp,mp2,truee,falsee,finishh,bomb;

    CountDownTimer countDownTimer;

    ArrayList<Bitmap> charecters=new ArrayList<>();
    ArrayList<ImageView> imageViews=new ArrayList<>(9);

    String info;

    ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        Intent intent =getIntent();
        info=intent.getStringExtra("info");

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
        like1=findViewById(R.id.like1);
        like2=findViewById(R.id.like2);
        like3=findViewById(R.id.like3);

        textView=findViewById(R.id.score);
        textView2=findViewById(R.id.hightScore);
        baslangicText=findViewById(R.id.baslangic);
        seviyeText=findViewById(R.id.seviyeText);

        constraintLayout=findViewById(R.id.constraintLayout);



        sharedPreferences= getSharedPreferences("com.tugrulkara.storingdata",MODE_PRIVATE);

        if (info.matches("low")){
            constraintLayout.setBackgroundColor(Color.GREEN);
            seviyeText.setText("Kolay");
            oncekiScore += sharedPreferences.getInt("LowOncekiScore",0);
            if (oncekiScore>highScore){
                sharedPreferences.edit().putInt("HighScoreLow", oncekiScore).apply();
            }
            highScore+= sharedPreferences.getInt("HighScoreLow",0);
        }
        if (info.matches("medium")){
            constraintLayout.setBackgroundColor(Color.YELLOW);
            seviyeText.setText("Orta");
            oncekiScore += sharedPreferences.getInt("MediumOncekiScore",0);
            if (oncekiScore>highScore){
                sharedPreferences.edit().putInt("HighScoreMedium", oncekiScore).apply();
            }
            highScore+= sharedPreferences.getInt("HighScoreMedium",0);
        }
        if (info.matches("hard")){
            constraintLayout.setBackgroundColor(Color.RED);
            seviyeText.setText("Zor");
            oncekiScore += sharedPreferences.getInt("HardOncekiScore",0);
            if (oncekiScore>highScore){
                sharedPreferences.edit().putInt("HighScoreHard", oncekiScore).apply();
            }
            highScore+= sharedPreferences.getInt("HighScoreHard",0);
        }

        textView2.setText("En Yüksek Skor:"+highScore);


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
        charecters.add(tucker);
        charecters.add(kenny);
        charecters.add(bomba);
        charecters.add(kenny);
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
        countDownTimer=new CountDownTimer(6000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                baslangicText.setText(""+millisUntilFinished/1000);

            }

            @Override
            public void onFinish() {

                baslangicText.setVisibility(View.INVISIBLE);
                mp2=MediaPlayer.create(PlayActivity.this,R.raw.runn);
                mp.stop();
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

                        if (info.matches("low")){
                            handler.postDelayed(runnable,800);
                        }
                        if (info.matches("medium")){
                            handler.postDelayed(runnable,700);
                        }
                        if (info.matches("hard")){
                            handler.postDelayed(runnable,600);
                        }

                    }
                };
                handler.post(runnable);

            }
        }.start();


    }


    public void randomChar(){

        Random random=new Random();
        int i=random.nextInt(10);

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
            truee=MediaPlayer.create(PlayActivity.this,R.raw.truee);
            truee.start();
            score++;
            textView.setText("Skor: " + score);

            if (info.matches("low")){
                if (score > oncekiScore) {
                    sharedPreferences.edit().putInt("LowOncekiScore", score).apply();
                }
            }
            if (info.matches("medium")){
                if (score > oncekiScore) {
                    sharedPreferences.edit().putInt("MediumOncekiScore", score).apply();
                }
            }
            if (info.matches("hard")){
                if (score > oncekiScore) {
                    sharedPreferences.edit().putInt("HardOncekiScore", score).apply();
                }
            }



        }else {
            if (bitmap2==bomba) {
                if (info.matches("low")){
                    sharedPreferences.edit().putInt("LowOncekiScore", 0).apply();
                }
                if (info.matches("medium")){
                    sharedPreferences.edit().putInt("MediumOncekiScore", 0).apply();
                }
                if (info.matches("hard")){
                    sharedPreferences.edit().putInt("HardOncekiScore", 0).apply();
                }
                score=0;
                bomb=MediaPlayer.create(PlayActivity.this,R.raw.bomb);
                bomb.start();
                textView.setText("Skor: " + score);
            }else {
                falsee=MediaPlayer.create(PlayActivity.this,R.raw.falsee);
                falsee.start();
                can--;

                if (can ==2){

                    like3.setVisibility(View.INVISIBLE);
                }
                if (can ==1){

                    like2.setVisibility(View.INVISIBLE);

                }
                if (can <= 0){
                    handler.removeCallbacks(runnable);
                    like1.setVisibility(View.INVISIBLE);
                    mp2.stop();
                    finishh=MediaPlayer.create(PlayActivity.this,R.raw.finishh);
                    finishh.start();
                    Invisible();

                    AlertDialog.Builder alert =new AlertDialog.Builder(PlayActivity.this);
                    alert.setTitle("Oyun Bitti");
                    alert.setMessage("Toplam Skorunuz: "+score+"\n"+"Tekrar Oynamak İstermisiniz?");
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
                            Intent intent=new Intent(PlayActivity.this,MainActivity.class);
                            startActivity(intent);
                            Toast.makeText(PlayActivity.this,"Game Over",Toast.LENGTH_LONG).show();
                        }
                    });

                    alert.show();

                }
            }


        }


    }


    public void Invisible(){

        for (ImageView image : imageViews){

            image.setVisibility(View.INVISIBLE);

        }

    }
    public  void MediaPlayer(){

        mp = MediaPlayer.create(PlayActivity.this, R.raw.runstart);
        mp.start();

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Çıkış");
        builder.setMessage("Çıkış yapmak istiyormusunuz?");
        builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                handler.removeCallbacks(runnable);
                finish();
                mp.stop();
                mp2.stop();
                Intent intent = new Intent(PlayActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();
            }
        });
        builder.show();

    }
}