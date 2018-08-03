package com.example.gunjansrivastava.codingchallenge.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.widget.Toast;

import com.example.gunjansrivastava.codingchallenge.client.RestServiceAddNoteProtocol;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Retrofit;

public class AddNoteViewModel extends BaseObservable {
    private String description;
    private Context context;
    private Retrofit retrofit;
    private Callback<List<NoteListViewModel>> callback;

    public AddNoteViewModel(Context context , Retrofit retrofit, Callback<List<NoteListViewModel>> callback){
        this.context = context;
        this.retrofit = retrofit;
        this.callback = callback;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        if(description.length() >= 10){
            Toast.makeText(context,"Maximum Length Reached" , Toast.LENGTH_SHORT).show();
        }
    }

    public void submitNode(){
        NoteListViewModel note = new NoteListViewModel();
        note.setId("3");
        note.setDescription(description);

        RestServiceAddNoteProtocol restServiceProtocol = retrofit.create(RestServiceAddNoteProtocol.class);
        restServiceProtocol.addAndGetList(note).enqueue(callback);
    }
}
