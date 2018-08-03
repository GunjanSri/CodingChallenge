package com.example.gunjansrivastava.codingchallenge;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.gunjansrivastava.codingchallenge.components.DaggerRetrofitComponents;
import com.example.gunjansrivastava.codingchallenge.databinding.AddNoteBinding;
import com.example.gunjansrivastava.codingchallenge.modules.RetrofitClientModule;
import com.example.gunjansrivastava.codingchallenge.viewmodel.AddNoteViewModel;
import com.example.gunjansrivastava.codingchallenge.viewmodel.NoteListViewModel;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AddNoteActivity extends AppCompatActivity implements Callback<List<NoteListViewModel>>{
    private final String TAG = NotesListActivity.class.getSimpleName();
    private NotesStorage storage;

    @Inject
    Retrofit retrofit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerRetrofitComponents.builder()
                .retrofitClientModule(new RetrofitClientModule("http://www.google.vom"))
                .build().inject(this);
        storage = NotesStorage.getInstance(this);

        AddNoteBinding binding = DataBindingUtil.setContentView(this,R.layout.add_note);
        AddNoteViewModel addNoteViewModel = new AddNoteViewModel(this , retrofit , AddNoteActivity.this);
        binding.setViewModel(addNoteViewModel);

    }

    @Override
    public void onResponse(Call<List<NoteListViewModel>> call, Response<List<NoteListViewModel>> response) {
        Log.d("Response " , ""+ response.body().toString());

        storage.saveNotes(response.body());
        AddNoteActivity.this.finish();
    }

    @Override
    public void onFailure(Call<List<NoteListViewModel>> call, Throwable t) {
        Log.d(TAG, "Request failed: " + t.getMessage());
    }
}
