package com.example.raj.bookapicapstone;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;
public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyViewHoldder> {
    Context context;
    List<BookModel> bookModelList;
    public BookAdapter(BookActivity bookActivity, List<BookModel> list) {
        this.context =bookActivity ;
        this.bookModelList = list;
    }
    @NonNull
    @Override
    public BookAdapter.MyViewHoldder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.rowdesign,parent,false);
        return new MyViewHoldder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull BookAdapter.MyViewHoldder holder, int position) {
        BookModel model=bookModelList.get(position);
        Picasso.with(context).load(model.img).placeholder(R.mipmap.ic_launcher_round).into(holder.im);
    }
    @Override
    public int getItemCount() {
        return  bookModelList.size();
    }

    public class MyViewHoldder extends RecyclerView.ViewHolder {
        ImageView im;
        TextView tv;

        public MyViewHoldder(View itemView) {
            super(itemView);
            im=itemView.findViewById(R.id.bookiv);

        }
    }
}
