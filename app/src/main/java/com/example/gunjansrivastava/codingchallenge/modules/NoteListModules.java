package com.example.gunjansrivastava.codingchallenge.modules;

import com.example.gunjansrivastava.codingchallenge.adapter.NoteListAdapter;
import com.example.gunjansrivastava.codingchallenge.response.Note;

import java.util.List;

import dagger.Module;
import dagger.Provides;

@Module
public class NoteListModules {

    List<Note> notesList;
    public NoteListModules(List<Note> notesList){
        this.notesList = notesList;
    }

    @Provides
    public NoteListAdapter getNoteListAdapter(){
        return new NoteListAdapter(notesList);
    }
}
