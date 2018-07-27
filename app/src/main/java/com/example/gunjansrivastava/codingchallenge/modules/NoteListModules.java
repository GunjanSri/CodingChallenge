package com.example.gunjansrivastava.codingchallenge.modules;

import com.example.gunjansrivastava.codingchallenge.adapter.NoteListAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class NoteListModules {
    @Provides
    public NoteListAdapter getNoteListAdapter(){
        return new NoteListAdapter();
    }
}
