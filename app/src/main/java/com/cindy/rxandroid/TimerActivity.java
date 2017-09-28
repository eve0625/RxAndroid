package com.cindy.rxandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class TimerActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        textView = (TextView) findViewById(R.id.textview);

        /*
        Observable<String> ob = Observable.interval(3, TimeUnit.SECONDS)
                .flatMap(o -> Observable.just("polling #1 " + o));

        ob.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> textView.append(s + "\n"));
        */

        Observable<String> ob2 = Observable.just("polling #2")
                .repeatWhen(o -> o.delay(3, TimeUnit.SECONDS));

        ob2.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> textView.append(s + "\n"));
    }


}
