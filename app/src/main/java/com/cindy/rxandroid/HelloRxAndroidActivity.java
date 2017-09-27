package com.cindy.rxandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

public class HelloRxAndroidActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_rx_android);

        textView = (TextView) findViewById(R.id.textview);

        useObservableWithLambda2();
    }

    private void useObservable() {

        //발행한 데이터를 구독하는 Observer
        Observer<String> observer = new DisposableObserver<String>() {
            @Override
            public void onNext(@NonNull String s) {
                textView.setText(s);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        //데이터를 발행하는 Observable 생성 및 구독자 설정
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                e.onNext("Hello RxAndroid!!");
                e.onComplete();
            }
        }).subscribe(observer);

    }

    private void useObservableWithLambda() {

        //람다표현식으로 변경
        Observable.<String>create(s -> {
            s.onNext("Hello RxAndroid With Lambda");
            s.onComplete();;
        }).subscribe(o -> textView.setText(o));
    }

    private void useObservableWithLambda2() {

        //람다표현식으로 변경
        Observable.just("Hello RxAndroid With Lambda2")
                .subscribe(textView::setText); //메소드 레퍼런스 사용
    }
}
