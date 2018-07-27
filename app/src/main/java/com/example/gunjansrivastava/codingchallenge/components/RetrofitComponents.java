package com.example.gunjansrivastava.codingchallenge.components;

import com.example.gunjansrivastava.codingchallenge.AddNoteActivity;
import com.example.gunjansrivastava.codingchallenge.modules.RetrofitClientModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {RetrofitClientModule.class})
public interface RetrofitComponents {
    void inject(AddNoteActivity addNoteActivity);
}
