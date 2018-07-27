package com.example.gunjansrivastava.codingchallenge;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.gunjansrivastava.codingchallenge.adapter.NoteListAdapter;
import com.example.gunjansrivastava.codingchallenge.client.RestServiceProtocol;
import com.example.gunjansrivastava.codingchallenge.components.DaggerNoteComponent;
import com.example.gunjansrivastava.codingchallenge.modules.NoteListModules;
import com.example.gunjansrivastava.codingchallenge.modules.RetrofitClientModule;
import com.example.gunjansrivastava.codingchallenge.response.Notes;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NotesListActivity extends AppCompatActivity implements Callback<Notes> {
    private final String TAG = NotesListActivity.class.getSimpleName();

    @BindView(R.id.viewNoteList)
    RecyclerView recyclerView;

    @Inject
    Retrofit retrofitClient;
    @Inject
    NoteListAdapter noteListAdapter;

    private NotesStorage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        storage = NotesStorage.getInstance(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(noteListAdapter);

        DaggerNoteComponent.builder()
                .retrofitClientModule(new RetrofitClientModule("http://www.google.vom"))
                .noteListModules(new NoteListModules())
                .build().inject(this);

        RestServiceProtocol restServiceProtocol = retrofitClient.create(RestServiceProtocol.class);

        restServiceProtocol.getList().enqueue(this);
    }

    @OnClick(R.id.addNoteButton)
    public void onClick(){
        Intent intent = new Intent(this, AddNoteActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(storage.getNotes() != null){
            noteListAdapter.setUpdatedList(storage.getNotes().getNotesList());
            noteListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onResponse(Call<Notes> call, Response<Notes> response) {
        if(response != null){
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(noteListAdapter);
            noteListAdapter.setUpdatedList(response.body().getNotesList());
            noteListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onFailure(Call<Notes> call, Throwable t) {
        Log.d(TAG, "Request failed: " + t.getMessage());
    }
}
