package com.premom.www.premom3;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Retrofit;


public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.ViewHolder>{
    Date mDate;

    SimpleDateFormat mFormat = new SimpleDateFormat("dd");
    TextView mTextView;
    SimpleDateFormat mFormat2 = new SimpleDateFormat("EEE");
    TextView mTextView2;

    private ArrayList<MyItem2> my_items;
    int this_position;
    int is_pic;


    public MyAdapter2(ArrayList<MyItem2> items){
        my_items = items;
    }

    // 필수
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_pic, null);
        ViewHolder holder = new ViewHolder(itemLayoutView);

        mTextView = (TextView) itemLayoutView.findViewById(R.id.pic_is_pic);
        mTextView.setText(getTime());
        mTextView2 = (TextView) itemLayoutView.findViewById(R.id.pic_is_pic2);
        mTextView2.setText(getTime2());
        return holder;
    }
    private String getTime2(){
        mDate = new Date();
        String day = "";
        if(mFormat2.format(mDate)=="월")
            day="Mon";
        else if(mFormat2.format(mDate)=="화")
            day="Tue";
        else if(mFormat2.format(mDate)=="수")
            day="Wed";
        else if(mFormat2.format(mDate)=="목")
            day="Thr";
        else if(mFormat2.format(mDate)=="금")
            day="Fri";
        else if(mFormat2.format(mDate)=="토")
            day="Sat";
        else
            day="Sun";

        return (day);
    }
    private String getTime(){
        mDate = new Date();
        return mFormat.format(mDate);
    }
    //필
    public void onBindViewHolder(ViewHolder viewHolder, int position){
        this_position = position;
        is_pic = my_items.get(position).getIs_pic();

        if(is_pic == 0) {
            viewHolder.pic_cho.setImageResource(R.drawable.choumpa);
        }
    }
    // 필수
    @Override
    public int getItemCount(){
        return my_items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView pic_cho;
        public TextView pic_is_pic;
        public RelativeLayout pic_view;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);

            pic_is_pic = (TextView) itemLayoutView.findViewById(R.id.pic_is_pic);
            pic_cho = (ImageView) itemLayoutView.findViewById(R.id.pic_cho);
            pic_view = (RelativeLayout) itemLayoutView.findViewById(R.id.pic_view);

        }


    }
}

