package com.example.recyclerviewdemo;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewdemo.Adapter.RecycleAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    RecyclerView recycle;
    LinearLayoutManager manager;
    RecycleAdapter adapter;
    int currentitems,totalitems,scrolloutitems;
    ArrayList<String> list;
    Boolean scroll=false;
    ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycle=(RecyclerView)findViewById(R.id.recycle);
        progressbar=(ProgressBar) findViewById(R.id.progress);
        manager=new LinearLayoutManager(this);

        String[] a={"Data 1","Data 2","Data 3","Data 4","Data 5"};
        list=new ArrayList(Arrays.asList(a));

        recycle.setLayoutManager(manager);
        adapter=new RecycleAdapter(this,list);
        recycle.setAdapter(adapter);

        recycle.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState== AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){

                    scroll=true;

                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentitems=manager.getChildCount();
                totalitems=manager.getItemCount();
                scrolloutitems=manager.findFirstCompletelyVisibleItemPosition();

                if(scroll && (currentitems+scrolloutitems==totalitems)){

                      scroll=false;
                      data();

                }
            }
        });
    }

    private void data(){

        progressbar.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<5;i++){
                    list.add(Math.round(Math.random())+1+"");
                    adapter.notifyDataSetChanged();
                    progressbar.setVisibility(View.GONE);

                }
            }
        },5000);


    }

}
