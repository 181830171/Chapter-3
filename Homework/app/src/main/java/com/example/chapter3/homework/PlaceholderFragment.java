package com.example.chapter3.homework;


import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;

public class PlaceholderFragment extends Fragment {

    private LottieAnimationView animationView;
    private ListView listView;
    private ArrayAdapter<Item> adapterItems;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<Item> items = Item.getItems();
        adapterItems = new ArrayAdapter<Item>(getActivity(),
                android.R.layout.simple_list_item_activated_1, items);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO ex3-3: 修改 fragment_placeholder，添加 loading 控件和列表视图控件;
        View view=inflater.inflate(R.layout.fragment_placeholder, container, false);
        animationView=view.findViewById(R.id.animation_view1);
       // animationView.playAnimation();
        listView=view.findViewById(R.id.lvItems);
        listView.setAdapter(adapterItems);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getView().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 这里会在 5s 后执行
                // TODO ex3-4：实现动画，将 lottie 控件淡出，列表数据淡入
                ObjectAnimator animator1=ObjectAnimator.ofFloat(animationView,"alpha",1f,0f);
                animator1.setDuration(500);
                animator1.start();
                listView.setVisibility(View.VISIBLE);
                ObjectAnimator animator2=ObjectAnimator.ofFloat(listView,"alpha",0f,1f);
                animator2.setDuration(1000);
                animator2.start();

            }
        }, 5000);
    }
}
