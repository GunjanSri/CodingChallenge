package com.example.gunjansrivastava.codingchallenge.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import android.view.ViewGroup;

import com.example.gunjansrivastava.codingchallenge.BR;
import com.example.gunjansrivastava.codingchallenge.R;
import com.example.gunjansrivastava.codingchallenge.databinding.ListItemBinding;
import com.example.gunjansrivastava.codingchallenge.viewmodel.NoteListViewModel;

import java.util.List;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.MyViewHolder> {

    private List<NoteListViewModel> notes;

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private final ListItemBinding binding;

        public MyViewHolder(ListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(NoteListViewModel item) {
            binding.setVariable(BR.viewmodel,item);
            binding.executePendingBindings();
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ListItemBinding binding = DataBindingUtil.inflate(inflater,R.layout.list_item,parent,false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(notes.get(position));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setUpdatedList(List<NoteListViewModel> notes){
        this.notes = notes;
    }
}
