package com.example.gunjansrivastava.codingchallenge;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.gunjansrivastava.codingchallenge.response.Notes;
import com.google.gson.Gson;

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

    public void saveNotes(Notes notes){
        String json = gson.toJson(notes);
        editor = sharedPreferences.edit();
        editor.putString(NOTES_LIST_KEY , json);
        editor.commit();
    }

    public Notes getNotes(){
        String json = sharedPreferences.getString(NOTES_LIST_KEY , null);
        Notes notes = gson.fromJson(json , Notes.class);
        return notes;
    }
}
