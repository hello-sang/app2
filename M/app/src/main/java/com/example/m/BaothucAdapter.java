package com.example.m;

import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class BaothucAdapter extends RecyclerView.Adapter<BaothucAdapter.MyviewHolder> {

    private List<Baothuc> data;
    private OnItemClickedListener onItemClickedListener;
    public boolean isClicked =false;


    public  interface OnItemClickedListener{
        void onItemclick(Baothuc baothuc,int position);
    }

    public void setOnItemClickedListener(OnItemClickedListener onItemClickedListener) {
        this.onItemClickedListener = onItemClickedListener;
    }
    public  BaothucAdapter(List<Baothuc> data){
        this.data=data;


    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.item_in_main,parent,false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyviewHolder holder, final int position) {
        final Baothuc baothuc =data.get(position);
        SimpleDateFormat sdfTime = new SimpleDateFormat("hh:mm", Locale.getDefault());
        Date date = new Date(baothuc.getTime());

        holder.tvGio.setText(sdfTime.format(date));
//        holder.tvTrangthai.setText(baothuc.getLaplai()+"|"+baothuc.getThoigianconlai());
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isClicked){
                    onItemClickedListener.onItemclick(baothuc,position);
                }
            }
        });
        holder.switchCompat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.switchCompat.isChecked()){
                    holder.tvTrangthai.setText(baothuc.getLaplai()+"|"+baothuc.getThoigianconlai());


                }
                else{
                    holder.tvTrangthai.setText("tat");
                }

            }


        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public  static  class MyviewHolder extends RecyclerView.ViewHolder{
        TextView tvGio,tvTrangthai;
        ConstraintLayout constraintLayout;
        SwitchCompat switchCompat;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            tvGio=itemView.findViewById(R.id.textView_Time);
            tvTrangthai=itemView.findViewById(R.id.textView_Trangthai);
            constraintLayout=itemView.findViewById(R.id.constraintLayout);
            switchCompat=itemView.findViewById(R.id.scOnoff);
        }
    }
}
