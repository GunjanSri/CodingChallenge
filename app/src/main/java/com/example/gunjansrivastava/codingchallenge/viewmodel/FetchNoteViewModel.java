package com.example.gunjansrivastava.codingchallenge.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;

import com.example.gunjansrivastava.codingchallenge.AddNoteActivity;

public class FetchNoteViewModel extends BaseObservable {

    public void addNote(Context context){
        Intent intent = new Intent(context, AddNoteActivity.class);
        context.startActivity(intent);
    }
}
