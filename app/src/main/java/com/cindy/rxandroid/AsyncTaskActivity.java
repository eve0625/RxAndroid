package com.cindy.rxandroid;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import io.reactivex.MaybeObserver;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.operators.maybe.MaybeObserveOn;

public class AsyncTaskActivity extends AppCompatActivity {

    private TextView textView;

    private MyAsyncTask myAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        textView = (TextView) findViewById(R.id.textview);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Observable.just("Hello", "rx", "world")
                .reduce((x, y) -> x + " " + y)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver());

        //myAsyncTask = new MyAsyncTask();
        //myAsyncTask.execute("Hello", "async", "world");
    }

    @Override
    protected void onStop() {
        super.onStop();

        //myAsyncTask.cancel(true);
    }

    private MaybeObserver<String> getObserver() {
        return new MaybeObserver<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull String s) {
                textView.setText(s);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }

    public class MyAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            StringBuilder word = new StringBuilder();
            for (String s : strings) {
                word.append(s).append(" ");
            }
            return word.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            textView.setText(s);
        }
    }
}
