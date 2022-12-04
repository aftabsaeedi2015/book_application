package com.example.book_application;

import android.content.Context;
import android.content.Intent;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class adapter extends RecyclerView.Adapter<adapter.viewholder> {
    ArrayList<book_info> book_info_array = new ArrayList<>();
    Context context;
    String activity;

    public adapter(Context context, String activity) {
        this.context = context;
        this.activity= activity;
    };

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);
        return new viewholder(view);
    }

    @Override
//    binding data to the views created
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.name.setText(book_info_array.get(holder.getAdapterPosition()).TITLE);
        holder.discription.setText(book_info_array.get(holder.getAdapterPosition()).DESCRIPTION);
        Glide.with(context).asBitmap().load(book_info_array.get(holder.getAdapterPosition()).IMG).into(holder.img);

        //        we handle the deletbtn here
        if(book_info_array.get(holder.getAdapterPosition()).collapsed){
            TransitionManager.beginDelayedTransition(holder.content_container);
            if (activity.equals("favorite_books")){
                holder.deletbtn.setVisibility(View.VISIBLE);
                holder.deletbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String book_id = book_info_array.get(holder.getAbsoluteAdapterPosition()).ID;
                        utils.getInstance().delet_by_id(book_id);
                        notifyDataSetChanged();

                    }
                });

            holder.discription.setVisibility(View.VISIBLE);
            holder.arrow_down.setVisibility(View.GONE);
            holder.arrow_up.setVisibility(View.VISIBLE);


            }
            else
                holder.deletbtn.setVisibility(View.GONE);

        }
        else{
            TransitionManager.beginDelayedTransition(holder.content_container);
            holder.discription.setVisibility(View.GONE);
            holder.arrow_up.setVisibility(View.GONE);
            holder.arrow_down.setVisibility(View.VISIBLE);
        }
//        here we handle when the user
//        clicks on the book
        holder.content_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,bookactivity.class);
                intent.putExtra("book", String.valueOf(book_info_array.get(holder.getAdapterPosition())));
                intent.putExtra("book_id",String.valueOf(book_info_array.get(holder.getAdapterPosition()).ID));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.e("anything",String.valueOf(book_info_array.size()));
        return book_info_array.size();
    }



//    adding books to the adapter
    public void setBooks(ArrayList<book_info> books){
        book_info_array= books;
        notifyDataSetChanged();
    }




//    viewholder class
    public class viewholder extends RecyclerView.ViewHolder{
        ImageView img,arrow_down,arrow_up;
        Button deletbtn;
        TextView name,discription;
        RelativeLayout content_container;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            arrow_down = itemView.findViewById(R.id.arrow_down);
            arrow_up = itemView.findViewById(R.id.arrow_up);
            deletbtn = itemView.findViewById(R.id.deletbtn);
            name = itemView.findViewById(R.id.name);
            discription = itemView.findViewById(R.id.discription);
            content_container = itemView.findViewById(R.id.content_container);
            arrow_down.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    book_info current_book = book_info_array.get(getPosition());
                   current_book.setcollapsed(!current_book.collapsed);
                   notifyItemChanged(getAdapterPosition());
                   arrow_down.setVisibility(View.GONE);
                }

            });
            arrow_up.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    book_info current_book = book_info_array.get(getAdapterPosition());
                    current_book.setcollapsed(!current_book.collapsed);
                    notifyItemChanged(getAdapterPosition());
                    arrow_down.setVisibility(View.VISIBLE);
                }
            });

        }
    }
}
