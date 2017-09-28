package com.cindy.rxandroid.applist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cindy.rxandroid.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.subjects.PublishSubject;

/**
 * Created by jiyoung on 2017. 9. 28..
 */

public class AppListAdapter extends RecyclerView.Adapter<AppViewHolder> {

    private List<ItemApp> mItems = new ArrayList<>();
    private PublishSubject<ItemApp> mPublishSubject;

    public AppListAdapter() {
        super();
        //구독자가 subscribe() 함수를 호출하면 값을 발행하는 평범한 Subject 클래스
        //클릭 이벤트 발생시 값을 발행
        this.mPublishSubject = PublishSubject.create();
    }

    @Override
    public AppViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_app_info, parent, false);
        return new AppViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AppViewHolder holder, int position) {
        final ItemApp item = mItems.get(position);
        holder.mImage.setImageDrawable(item.image);
        holder.mTitle.setText(item.title);
        holder.getClickObserver(item).subscribe(mPublishSubject);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void updateItems(List<ItemApp> items) {
        mItems.addAll(items);
    }

    public void updateItem(ItemApp item) {
        mItems.add(item);
    }

    public PublishSubject<ItemApp> getItemPublishSubject() {
        return mPublishSubject;
    }
}
