package com.cindy.rxandroid.applist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cindy.rxandroid.R;

import io.reactivex.Observable;

/**
 * Created by jiyoung on 2017. 9. 28..
 */

public class AppViewHolder extends RecyclerView.ViewHolder {
    ImageView mImage;
    TextView mTitle;

    public AppViewHolder(View itemView) {
        super(itemView);
        mImage = (ImageView) itemView.findViewById(R.id.item_image);
        mTitle = (TextView) itemView.findViewById(R.id.item_title);
    }

    Observable<ItemApp> getClickObserver(ItemApp item) {
        return Observable.create(e -> itemView.setOnClickListener(
                view -> e.onNext(item)
        ));
    }
}
