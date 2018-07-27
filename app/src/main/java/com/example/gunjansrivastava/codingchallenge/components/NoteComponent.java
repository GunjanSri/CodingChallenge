package com.example.gunjansrivastava.codingchallenge.components;

import com.example.gunjansrivastava.codingchallenge.NotesListActivity;
import com.example.gunjansrivastava.codingchallenge.modules.NoteListModules;
import com.example.gunjansrivastava.codingchallenge.modules.RetrofitClientModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NoteListModules.class , RetrofitClientModule.class} )
public interface NoteComponent {
    void inject(NotesListActivity notesListActivity);
}
