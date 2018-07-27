package com.example.gunjansrivastava.codingchallenge.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gunjansrivastava.codingchallenge.R;
import com.example.gunjansrivastava.codingchallenge.response.Note;

import java.util.List;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.MyViewHolder> {

    private List<Note> notes;

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView listItem;
        public MyViewHolder(View itemView) {
            super(itemView);
            listItem = itemView.findViewById(R.id.listItem);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.listItem.setText(notes.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setUpdatedList(List<Note> notes){
        this.notes = notes;
    }
}
