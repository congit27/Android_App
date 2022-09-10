package com.android.cuocduakythu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView txtMark;
    CheckBox cb1, cb2, cb3;
    SeekBar sb1, sb2, sb3;
    ImageButton playBtn;
    int mark = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        
        mapping();
        sb1.setEnabled(false);
        sb2.setEnabled(false);
        sb3.setEnabled(false);

        txtMark.setText(mark + "");

        CountDownTimer countDownTimer = new CountDownTimer(60000, 300) {
            @Override
            public void onTick(long millisUntilFinished) {
                int number = 5;
                Random random = new Random();
                int one = random.nextInt(number);
                int two = random.nextInt(number);
                int three = random.nextInt(number);

                // check winner
                if(sb1.getProgress() >= sb1.getMax()) {
                    this.cancel();
                    playBtn.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "One Win", Toast.LENGTH_SHORT).show();

                    if(cb1.isChecked()) {
                        mark += 10;
                        Toast.makeText(MainActivity.this, "Bạn đã đoán chính xác!", Toast.LENGTH_SHORT).show();
                    } else {
                        mark -= 10;
                        Toast.makeText(MainActivity.this, "Bạn đoán trật lất!", Toast.LENGTH_SHORT).show();
                    }
                    txtMark.setText(mark + "");
                    enableCheckbox();
                }
                if(sb2.getProgress() >= sb2.getMax()) {
                    this.cancel();
                    playBtn.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "Two Win", Toast.LENGTH_SHORT).show();

                    if(cb2.isChecked()) {
                        mark += 10;
                        Toast.makeText(MainActivity.this, "Bạn đã đoán chính xác!", Toast.LENGTH_SHORT).show();
                    } else {
                        mark -= 10;
                        Toast.makeText(MainActivity.this, "Bạn đoán trật lất!", Toast.LENGTH_SHORT).show();
                    }
                    txtMark.setText(mark + "");
                    enableCheckbox();
                }
                if(sb3.getProgress() >= sb3.getMax()) {
                    this.cancel();
                    playBtn.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "Three Win", Toast.LENGTH_SHORT).show();

                    if(cb3.isChecked()) {
                        mark += 10;
                        Toast.makeText(MainActivity.this, "Bạn đã đoán chính xác!", Toast.LENGTH_SHORT).show();
                    } else {
                        mark -= 10;
                        Toast.makeText(MainActivity.this, "Bạn đoán trật lất!", Toast.LENGTH_SHORT).show();
                    }
                    txtMark.setText(mark + "");
                    enableCheckbox();
                }

                sb1.setProgress(sb1.getProgress() +  one);
                sb2.setProgress(sb2.getProgress() +  two);
                sb3.setProgress(sb3.getProgress() +  three);
            }

            @Override
            public void onFinish() {

            }
        };

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cb1.isChecked() || cb2.isChecked() || cb3.isChecked()) {
                    sb1.setProgress(0);
                    sb2.setProgress(0);
                    sb3.setProgress(0);
                    playBtn.setVisibility(View.INVISIBLE);
                    countDownTimer.start();

                    disableCheckbox();
                } else {
                    Toast.makeText(MainActivity.this, "Vui lòng chọn thú!", Toast.LENGTH_SHORT).show();

                }

            }
        });

        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    cb2.setChecked(false);
                    cb3.setChecked(false);
                }
            }
        });

        cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    cb1.setChecked(false);
                    cb3.setChecked(false);
                }
            }
        });

        cb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    cb1.setChecked(false);
                    cb2.setChecked(false);
                }
            }
        });
    }

    private void enableCheckbox() {
        cb1.setEnabled(true);
        cb2.setEnabled(true);
        cb3.setEnabled(true);
    }

    private void disableCheckbox() {
        cb1.setEnabled(false);
        cb2.setEnabled(false);
        cb3.setEnabled(false);
    }

    private void mapping() {
        txtMark = (TextView) findViewById(R.id.txtMark);
        playBtn = (ImageButton) findViewById(R.id.playBtn);
        cb1     = (CheckBox) findViewById(R.id.checkbox1);
        cb2     = (CheckBox) findViewById(R.id.checkbox2);
        cb3     = (CheckBox) findViewById(R.id.checkbox3);
        sb1     = (SeekBar) findViewById(R.id.seekbar1);
        sb2     = (SeekBar) findViewById(R.id.seekbar2);
        sb3     = (SeekBar) findViewById(R.id.seekbar3);
    }
}