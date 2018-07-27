package com.example.gunjansrivastava.codingchallenge;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.gunjansrivastava.codingchallenge.adapter.NoteListAdapter;
import com.example.gunjansrivastava.codingchallenge.components.DaggerNoteListComponent;
import com.example.gunjansrivastava.codingchallenge.modules.NoteListModules;
import com.example.gunjansrivastava.codingchallenge.response.Notes;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NotesListActivity extends AppCompatActivity {

    @BindView(R.id.viewNoteList)
    RecyclerView recyclerView;

    @Inject
    NoteListAdapter noteListAdapter;
    NotesStorage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        storage = NotesStorage.getInstance(this);
        Notes list = storage.getNotes();

        DaggerNoteListComponent.builder()
                .noteListModules(new NoteListModules(list.getNotesList()))
                .build().inject(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(noteListAdapter);
    }

    @OnClick(R.id.addNoteButton)
    public void onClick(){
        Intent intent = new Intent(this, AddNoteActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        noteListAdapter.setUpdatedList(storage.getNotes().getNotesList());
        noteListAdapter.notifyDataSetChanged();
    }
}
