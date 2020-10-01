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

import java.util.Random;

public class ZorActivity extends AppCompatActivity {
    TextView sureZorText;
    TextView puanZorText;
    TextView highScoreZor;

    ImageView imageView30;
    ImageView imageView31;
    ImageView imageView32;
    ImageView imageView33;
    ImageView imageView34;
    ImageView imageView35;
    ImageView imageView36;
    ImageView imageView37;
    ImageView imageView38;
    ImageView imageView39;
    ImageView imageView40;
    ImageView imageView41;
    ImageView imageView42;
    ImageView imageView43;
    ImageView imageView44;
    ImageView imageView45;
    ImageView imageView46;
    ImageView imageView47;
    ImageView imageView48;
    ImageView imageView49;
    ImageView imageView50;
    ImageView imageView51;
    ImageView imageView52;
    ImageView imageView53;
    ImageView imageView54;
    ImageView imageView55;
    ImageView imageView56;
    ImageView imageView57;
    ImageView imageView58;
    ImageView imageView59;
    ImageView imageView60;
    ImageView imageView61;
    ImageView imageView62;
    ImageView imageView63;
    ImageView imageView64;
    ImageView imageView65;
    ImageView imageView66;
    ImageView imageView67;
    ImageView imageView68;
    ImageView imageView69;
    ImageView imageView70;
    ImageView imageView71;
    ImageView imageView72;
    ImageView imageView73;
    ImageView imageView74;
    ImageView imageView75;
    ImageView imageView76;
    ImageView imageView77;
    ImageView imageView78;
    ImageView [] imageArray;
    SharedPreferences sharedPreferencesZor;

    Handler handler;
    Runnable runnable;

    int puan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zor);

        puanZorText = findViewById(R.id.puanZorText);
        sureZorText = findViewById(R.id.sureZorText);
        highScoreZor = findViewById(R.id.highScoreZorText);

        imageView30 = findViewById(R.id.imageView30);
        imageView31 = findViewById(R.id.imageView31);
        imageView32 = findViewById(R.id.imageView32);
        imageView33 = findViewById(R.id.imageView33);
        imageView34 = findViewById(R.id.imageView34);
        imageView35 = findViewById(R.id.imageView35);
        imageView37 = findViewById(R.id.imageView37);
        imageView38 = findViewById(R.id.imageView38);
        imageView39 = findViewById(R.id.imageView39);
        imageView40 = findViewById(R.id.imageView40);
        imageView41 = findViewById(R.id.imageView41);
        imageView42 = findViewById(R.id.imageView42);
        imageView44 = findViewById(R.id.imageView44);
        imageView45 = findViewById(R.id.imageView45);
        imageView46 = findViewById(R.id.imageView46);
        imageView47 = findViewById(R.id.imageView47);
        imageView48 = findViewById(R.id.imageView48);
        imageView49 = findViewById(R.id.imageView49);
        imageView51 = findViewById(R.id.imageView51);
        imageView52 = findViewById(R.id.imageView52);
        imageView53 = findViewById(R.id.imageView53);
        imageView54 = findViewById(R.id.imageView54);
        imageView55 = findViewById(R.id.imageView55);
        imageView56 = findViewById(R.id.imageView56);
        imageView58 = findViewById(R.id.imageView58);
        imageView59 = findViewById(R.id.imageView59);
        imageView60 = findViewById(R.id.imageView60);
        imageView61 = findViewById(R.id.imageView61);
        imageView62 = findViewById(R.id.imageView62);
        imageView63 = findViewById(R.id.imageView63);
        imageView65 = findViewById(R.id.imageView65);
        imageView66 = findViewById(R.id.imageView66);
        imageView67 = findViewById(R.id.imageView67);
        imageView68 = findViewById(R.id.imageView68);
        imageView69 = findViewById(R.id.imageView69);
        imageView70 = findViewById(R.id.imageView70);
        imageView72 = findViewById(R.id.imageView72);
        imageView73 = findViewById(R.id.imageView73);
        imageView74 = findViewById(R.id.imageView74);
        imageView75 = findViewById(R.id.imageView75);
        imageView76 = findViewById(R.id.imageView76);
        imageView77 = findViewById(R.id.imageView77);
        imageArray = new ImageView[] {imageView30,imageView31,imageView32,imageView33,imageView34,imageView35,imageView37,imageView38,imageView39,imageView40,imageView41,imageView42,imageView44,imageView45,imageView46,imageView47,imageView48,imageView49,imageView51,imageView52,imageView53,imageView54,
                                      imageView55,imageView56,imageView58,imageView59,imageView60,imageView61,imageView62,imageView63,imageView65,imageView66,imageView67,imageView68,imageView69,imageView70,imageView72,imageView73,imageView74,imageView75,imageView76,imageView77};
        sharedPreferencesZor = ZorActivity.this.getSharedPreferences("com.example.oyun", Context.MODE_PRIVATE);
        final int highScore = sharedPreferencesZor.getInt("HighScoreZor",0);
        highScoreZor.setText("HighScore: "+highScore);
        puan = 0;

        kuruGosterZor();
        new CountDownTimer(25000,1000){


            @Override
            public void onTick(long millisUntilFinished) {
                sureZorText.setText("Süre :"+millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                sureZorText.setText("Süre Bitti");
                handler.removeCallbacks(runnable);
                for (ImageView image : imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                if (puan > highScore){
                    sharedPreferencesZor.edit().putInt("HighScoreZor",puan).apply();
                }
                gameOver(highScore,puan);

            }
        }.start();

    }
    public void puanArttirZor(View view) {
        puan++;
        puanZorText.setText("Puan :"+puan);
    }
    public void kuruGosterZor(){
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for (ImageView image : imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                Random random = new Random();
                int i = random.nextInt(41);
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

        AlertDialog.Builder alert = new AlertDialog.Builder(ZorActivity.this);
        alert.setTitle("Game Over");
        alert.setView(game);
        alert.setCancelable(false);
        final AlertDialog dialog = alert.create();
        gamehighscore.setText("HighScore:"+highScore);
        gamepuan.setText("Puan: "+puan);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ZorActivity.this,MainActivity.class);
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