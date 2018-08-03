package com.example.gunjansrivastava.codingchallenge;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.gunjansrivastava.codingchallenge.viewmodel.NoteListViewModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class NotesStorage {

    private final String STORAGE_ID = "notes_storage";
    private final String NOTES_LIST_KEY = "notes_list_key";
    private final Gson gson = new Gson();
    private static NotesStorage instance;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    private NotesStorage(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(STORAGE_ID , Context.MODE_PRIVATE);
    }

    public static NotesStorage getInstance(final Context context){
        if(instance == null){
            instance = new NotesStorage(context.getApplicationContext());
        }
        return instance;
    }

    public void saveNotes(List<NoteListViewModel> notes){
        String json = gson.toJson(notes);
        editor = sharedPreferences.edit();
        editor.putString(NOTES_LIST_KEY , json);
        editor.commit();
    }

    public List<NoteListViewModel> getNotes(){
        String json = sharedPreferences.getString(NOTES_LIST_KEY , null);
        List<NoteListViewModel> notes = gson.fromJson(json , new TypeToken<List<NoteListViewModel>>(){}.getType());
        return notes;
    }
}
