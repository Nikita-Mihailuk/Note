package com.example.note.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.note.Models.Notes;
import com.example.note.NotesClickListener;
import com.example.note.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NotesListAdapter extends RecyclerView.Adapter <NotesViewHolder>{

    Context context;
    List<Notes> List;

    NotesClickListener listener;

    public NotesListAdapter(Context context, java.util.List<Notes> list, NotesClickListener listener) {
        this.context = context;
        List = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotesViewHolder(LayoutInflater.from(context).inflate(R.layout.notes_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {

        holder.textView_title.setText(List.get(position).getTitle());
        holder.textView_title.setSelected(true);

        holder.textView_notes.setText(List.get(position).getNotes());


        holder.textView_date.setText(List.get(position).getData());
        holder.textView_date.setSelected(true);

        if (List.get(position).isPinned()){
            holder.imageView_pin.setImageResource(R.drawable.pin_icon);
        }else {
            holder.imageView_pin.setImageResource(0);
        }

        int color_code = getRandomColor();
        holder.notes_container.setCardBackgroundColor(holder.itemView.getResources().getColor(color_code,null));

        holder.notes_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(List.get(holder.getAdapterPosition()));
            }
        });

        holder.notes_container.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener.onLongClick(List.get(holder.getAdapterPosition()), holder.notes_container);
                return true;
            }
        });
    }

    private int getRandomColor(){
        List <Integer> colorCode = new ArrayList<>();

        colorCode.add(R.color.color1);
        colorCode.add(R.color.color2);
        colorCode.add(R.color.color3);
        colorCode.add(R.color.color4);
        colorCode.add(R.color.color5);

        Random random = new Random();
        int random_color = random.nextInt(colorCode.size());
        return colorCode.get(random_color);
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    public void filterList (List<Notes> filteredList){
        List = filteredList;
        notifyDataSetChanged();
    }

}

class NotesViewHolder extends RecyclerView.ViewHolder {

    CardView notes_container;
    TextView textView_title;
    ImageView imageView_pin;
    TextView textView_notes;
    TextView textView_date;
    public NotesViewHolder(@NonNull View itemView) {
        super(itemView);

        notes_container = itemView.findViewById(R.id.notes_container);
        textView_title = itemView.findViewById(R.id.textView_title);
        imageView_pin = itemView.findViewById(R.id.imageView_pin);
        textView_notes = itemView.findViewById(R.id.textView_notes);
        textView_date = itemView.findViewById(R.id.textView_date);
    }
}
