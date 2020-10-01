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
import android.widget.Toast;

import java.util.Random;

public class KolayActivity extends AppCompatActivity {

    TextView sureKolayText;
    TextView puanKolayText;
    TextView highScoreKolay;

    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView [] imageArray;

    Handler handler;
    Runnable runnable;

    Button tekrar;
    Button menu;

    SharedPreferences sharedPreferences;

    int puan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kolay);

        sureKolayText = findViewById(R.id.sureKolayText);
        puanKolayText = findViewById(R.id.puanKolayText);
        highScoreKolay = findViewById(R.id.highScoreKolayText);

        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
        imageView3= findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        imageView9 = findViewById(R.id.imageView9);
        tekrar = findViewById(R.id.tekrarButon);
        menu = findViewById(R.id.menuButon);

        imageArray = new ImageView[] {imageView,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9};

        sharedPreferences = KolayActivity.this.getSharedPreferences("com.example.oyun", Context.MODE_PRIVATE);
        final int HighScore = sharedPreferences.getInt("highScore",0);
        highScoreKolay.setText("HighScore: "+HighScore);

        puan = 0;
        kuruGoster();

        new CountDownTimer(60000,1000){
            @Override
            public void onTick(long l) {
                sureKolayText.setText("Süre: "+l/1000);
            }

            @Override
            public void onFinish() {
                sureKolayText.setText("Süre Bitti");
                handler.removeCallbacks(runnable);
                for (ImageView image : imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                if (puan > HighScore ){
                    sharedPreferences.edit().putInt("highScore",puan).apply();
                }

                gameOver(HighScore,puan);

            }
        }.start();
    }

    public void  puanArttir(View view) {
        puan++;
        puanKolayText.setText("Puan :" + puan);
    }

    public void kuruGoster() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for (ImageView image : imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                Random random = new Random();
                int i = random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this,500);
            }
        };
        handler.post(runnable);
    }
    public void gameOver(int HighScore,int puan){
        LayoutInflater inflater = getLayoutInflater();
        View game = inflater.inflate(R.layout.gameoverlayout,null);
        final Button menu = game.findViewById(R.id.menuButon);
        final Button tekrar = game.findViewById(R.id.tekrarButon);
        final TextView gamehighscore = game.findViewById(R.id.gamehighScore);
        final TextView gamepuan = game.findViewById(R.id.gamePuan);

        AlertDialog.Builder alert = new AlertDialog.Builder(KolayActivity.this);
        alert.setTitle("Game Over");
        alert.setView(game);
        alert.setCancelable(false);
        final AlertDialog dialog = alert.create();
        gamehighscore.setText("HighScore: "+ HighScore);
        gamepuan.setText("Puan:"+puan);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KolayActivity.this,MainActivity.class);
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