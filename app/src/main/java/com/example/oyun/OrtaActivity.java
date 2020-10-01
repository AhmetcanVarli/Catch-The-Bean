package com.example.oyun;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.ref.PhantomReference;
import java.util.Random;

public class OrtaActivity extends AppCompatActivity {
    TextView sureOrtaText;
    TextView puanOrtaText;
    TextView highScoreOrta;

    ImageView imageView10;
    ImageView imageView11;
    ImageView imageView12;
    ImageView imageView13;
    ImageView imageView14;
    ImageView imageView15;
    ImageView imageView16;
    ImageView imageView17;
    ImageView imageView18;
    ImageView imageView19;
    ImageView imageView20;
    ImageView imageView21;
    ImageView imageView22;
    ImageView imageView23;
    ImageView imageView24;
    ImageView imageView25;
    ImageView imageView26;
    ImageView imageView27;
    ImageView imageView28;

    ImageView [] imageArray;

    Handler handler;
    Runnable runnable;

    SharedPreferences sharedPreferencesOrta;

    int puan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orta);

        sureOrtaText = findViewById(R.id.sureOrtaText);
        puanOrtaText = findViewById(R.id.puanOrtaText);
        highScoreOrta = findViewById(R.id.highScoreOrtaText);

        imageView10 = findViewById(R.id.imageView10);
        imageView11 = findViewById(R.id.imageView11);
        imageView12 = findViewById(R.id.imageView12);
        imageView13 = findViewById(R.id.imageView13);
        imageView15 = findViewById(R.id.imageView15);
        imageView16 = findViewById(R.id.imageView16);
        imageView17 = findViewById(R.id.imageView17);
        imageView18 = findViewById(R.id.imageView18);
        imageView20 = findViewById(R.id.imageView20);
        imageView21 = findViewById(R.id.imageView21);
        imageView22 = findViewById(R.id.imageView22);
        imageView23 = findViewById(R.id.imageView23);
        imageView25 = findViewById(R.id.imageView25);
        imageView26 = findViewById(R.id.imageView26);
        imageView27 = findViewById(R.id.imageView27);
        imageView28 = findViewById(R.id.imageView28);
        imageArray = new ImageView[] {imageView10,imageView11,imageView12,imageView13,imageView15,imageView16,imageView17,imageView18,imageView20,
                                      imageView21,imageView22, imageView23,imageView25,imageView26,imageView27,imageView28};

        sharedPreferencesOrta = OrtaActivity.this.getSharedPreferences(" com.example.oyun",Context.MODE_PRIVATE);
        final int highScore = sharedPreferencesOrta.getInt("highScoreOrta",0);
        highScoreOrta.setText("HighScore: "+highScore);

        puan = 0 ;
        kuruGosterOrta();

        new CountDownTimer(45000,1000){
            @Override
            public void onTick(long l) {
                sureOrtaText.setText("Süre :"+l/1000);
            }

            @Override
            public void onFinish() {
                sureOrtaText.setText("Süre Bitti");
                handler.removeCallbacks(runnable);
                for (ImageView image : imageArray) {
                    image.setVisibility(View.INVISIBLE);
                }
                if (puan > highScore){
                    sharedPreferencesOrta.edit().putInt("highScoreOrta",puan).apply();
                }
                gameOver(highScore,puan);
            }
        }.start();
    }
    public void puanArttirOrta(View view){
        puan++;
        puanOrtaText.setText("Puan :"+puan);
    }
    public void kuruGosterOrta(){
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for (ImageView image : imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                Random random = new Random();
                int i = random.nextInt(16);
                imageArray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this,350);
            }
        };
        handler.post(runnable);
    }
    public void gameOver(int highScore,int puan){
        LayoutInflater inflater = getLayoutInflater();
        View game = inflater.inflate(R.layout.gameoverlayout,null);
        final Button menu = game.findViewById(R.id.menuButon);
        final Button tekrar = game.findViewById(R.id.tekrarButon);
        final TextView gamehighscore = game.findViewById(R.id.gamehighScore);
        final TextView gamepuan = game.findViewById(R.id.gamePuan);

        AlertDialog.Builder alert = new AlertDialog.Builder(OrtaActivity.this);
        alert.setTitle("Game Over");
        alert.setView(game);
        alert.setCancelable(false);
        final  AlertDialog dialog = alert.create();
        gamehighscore.setText("HighScore: "+highScore);
        gamepuan.setText("Puan: "+puan);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrtaActivity.this,MainActivity.class);
                startActivity(intent);
                dialog.cancel();
            }
        });
        tekrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
                dialog.cancel();
            }
        });
            dialog.show();
    }
}