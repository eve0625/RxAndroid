package com.cindy.rxandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;

import io.reactivex.Observable;

public class LoopActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnLoop1, btnLoop2;
    private TextView tvResult1, tvResult2;

    private final String APPLE = "apple";
    private final String NOT_FOUND = "Not found";
    private Iterable<String> samples = Arrays.asList("banana", "orange", "apple", "apple mango", "melon", "watermelon");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loop);

        btnLoop1 = (Button) findViewById(R.id.btn_loop1);
        btnLoop1.setOnClickListener(this);
        btnLoop2 = (Button) findViewById(R.id.btn_loop2);
        btnLoop2.setOnClickListener(this);

        tvResult1 = (TextView) findViewById(R.id.result1);
        tvResult2 = (TextView) findViewById(R.id.result2);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_loop1: {
                //Java 방식
                for (String s : samples) {
                    if (s.contains(APPLE)) {
                        tvResult1.setText(s);
                        return;
                    }
                }
                tvResult1.setText(NOT_FOUND);
                break;
            }
            case R.id.btn_loop2: {
                //rxJava 방식
                Observable.fromIterable(samples)
                        .filter(s -> s.contains(APPLE))
                        .first(NOT_FOUND) //첫번째 값만 반환 (없을 경우 default)
                        .subscribe(result -> tvResult2.setText(result));
                break;
            }
        }
    }
}
