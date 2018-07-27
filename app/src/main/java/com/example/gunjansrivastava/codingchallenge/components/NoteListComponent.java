package com.example.gunjansrivastava.codingchallenge.components;

import com.example.gunjansrivastava.codingchallenge.NotesListActivity;
import com.example.gunjansrivastava.codingchallenge.modules.NoteListModules;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = NoteListModules.class )
public interface NoteListComponent {
    void inject(NotesListActivity mainActivity);
}
