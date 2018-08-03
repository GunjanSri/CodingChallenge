package com.example.gunjansrivastava.codingchallenge;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.gunjansrivastava.codingchallenge.adapter.NoteListAdapter;
import com.example.gunjansrivastava.codingchallenge.client.RestServiceProtocol;
import com.example.gunjansrivastava.codingchallenge.components.DaggerNoteComponent;
import com.example.gunjansrivastava.codingchallenge.databinding.ActivityMainBinding;
import com.example.gunjansrivastava.codingchallenge.modules.NoteListModules;
import com.example.gunjansrivastava.codingchallenge.modules.RetrofitClientModule;
import com.example.gunjansrivastava.codingchallenge.viewmodel.FetchNoteViewModel;
import com.example.gunjansrivastava.codingchallenge.viewmodel.NoteListViewModel;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NotesListActivity extends AppCompatActivity implements Callback<List<NoteListViewModel>> {
    private final String TAG = NotesListActivity.class.getSimpleName();

    @Inject
    Retrofit retrofitClient;
    @Inject
    NoteListAdapter noteListAdapter;

    private NotesStorage storage;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        storage = NotesStorage.getInstance(this);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        FetchNoteViewModel model = new FetchNoteViewModel();
        binding.setFetchNoteViewModel(model);

        recyclerView = findViewById(R.id.viewNoteList);

        DaggerNoteComponent.builder()
                .retrofitClientModule(new RetrofitClientModule("http://www.google.vom"))
                .noteListModules(new NoteListModules())
                .build().inject(this);

        RestServiceProtocol restServiceProtocol = retrofitClient.create(RestServiceProtocol.class);

        restServiceProtocol.getList().enqueue(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(storage.getNotes() != null){
            noteListAdapter.setUpdatedList(storage.getNotes());
            noteListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onResponse(Call<List<NoteListViewModel>> call, Response<List<NoteListViewModel>> response) {
        if(response != null){
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(noteListAdapter);
            noteListAdapter.setUpdatedList(response.body());
            noteListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onFailure(Call<List<NoteListViewModel>> call, Throwable t) {
        Log.d(TAG, "Request failed: " + t.getMessage());
    }
}
