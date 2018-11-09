//package com.premom.www.premom3;
//
//import android.graphics.Color;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//
//public class SeatAdapter extends RecyclerView.Adapter<SeatAdapter.ViewHolder>{
//    private ArrayList<SeatItem> my_items;
//
//    int this_position;
//    int is_seat;
//    int seat_fast;
//    int car;
//    int name;
//    int sub_car;
//
//    public SeatAdapter(ArrayList<SeatItem> items){
//        my_items = items;
//    }
//
//    // 필수
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
//        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_seat, null);
//        ViewHolder holder = new ViewHolder(itemLayoutView);
//        return holder;
//    }
//
//
//    //필수
//    @Override
//    public void onBindViewHolder(ViewHolder viewHolder, int position){
//        this_position = position;
//        car = my_items.get(position).getCar();
//        is_seat = my_items.get(position).getIs_seat();
//        seat_fast = my_items.get(position).getFast_transfer();
//        name =  my_items.get(position).getIdx();
//
//        viewHolder.seat_name.setText(name);
//        //viewHolder.seat_is_seat.setText("남은좌석 (" + Integer.toString(is_seat) + "/1)");
//        if(is_seat == 0) {
//            if(name == 1)
//                viewHolder.seat_heart0.setImageResource(R.drawable.heart_gray);
//            else if(name ==2)
//                viewHolder.seat_heart1.setImageResource(R.drawable.heart_gray);
//            else if(name ==3)
//                viewHolder.seat_heart2.setImageResource(R.drawable.heart_gray);
//            else
//                viewHolder.seat_heart3.setImageResource(R.drawable.heart_gray);
//
//        }
//
//        if(seat_fast == 1) {
//            viewHolder.seat_fast.setTextColor(Color.parseColor("#666666"));
//        }
//    }
//
//    // 필수
//    @Override
//    public int getItemCount(){
//        return my_items.size();
//    }
//
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//        public TextView seat_name;
//        //public TextView seat_is_seat;
//        public TextView seat_fast;
//        public ImageView seat_heart0;
//        public ImageView seat_heart1;
//        public ImageView seat_heart2;
//        public ImageView seat_heart3;
//        public RelativeLayout seat_view;
//        public ViewHolder(View itemLayoutView) {
//            super(itemLayoutView);
//            seat_name = (TextView) itemLayoutView.findViewById(R.id.seat_name);
//           /* seat_is_seat = (TextView) itemLayoutView.findViewById(R.id.seat_is_seat);*/
//            seat_fast = (TextView) itemLayoutView.findViewById(R.id.seat_fast);
//            seat_heart0 = (ImageView) itemLayoutView.findViewById(R.id.seat_heart0);
//            seat_heart1 = (ImageView) itemLayoutView.findViewById(R.id.seat_heart1);
//            seat_heart2 = (ImageView) itemLayoutView.findViewById(R.id.seat_heart2);
//            seat_heart3 = (ImageView) itemLayoutView.findViewById(R.id.seat_heart3);
//            seat_view = (RelativeLayout) itemLayoutView.findViewById(R.id.seat_view);
//
//        }
//
//    }
//
//}
