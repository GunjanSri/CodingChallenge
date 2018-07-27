package com.example.gunjansrivastava.codingchallenge;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gunjansrivastava.codingchallenge.client.RestServiceAddNoteProtocol;
import com.example.gunjansrivastava.codingchallenge.components.DaggerRetrofitComponents;
import com.example.gunjansrivastava.codingchallenge.modules.RetrofitClientModule;
import com.example.gunjansrivastava.codingchallenge.response.Note;
import com.example.gunjansrivastava.codingchallenge.response.Notes;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AddNoteActivity extends AppCompatActivity implements Callback<Notes>{
    private final String TAG = NotesListActivity.class.getSimpleName();
    private NotesStorage storage;

    @BindView(R.id.addNoteWrapper)
    TextInputLayout textInputLayout;
    @BindView(R.id.addNoteEditText)
    EditText addNoteEdiText;

    @Inject
    Retrofit retrofit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_note);
        ButterKnife.bind(this);
        addNoteEdiText.addTextChangedListener(textWatcher);
        storage = NotesStorage.getInstance(this);
    }

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if(textInputLayout.getEditText().getText().toString().length() >= 10){
                Toast.makeText(getApplicationContext(),"Maximum Length Reached" , Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void afterTextChanged(Editable s) { }
    };

    @OnClick(R.id.submitNote)
    public void submitNote(){
        Note note = new Note();
        note.setId("3");
        note.setDescription(addNoteEdiText.getText().toString());

        DaggerRetrofitComponents.builder()
                .retrofitClientModule(new RetrofitClientModule("http://www.google.vom"))
                .build().inject(this);

        RestServiceAddNoteProtocol restServiceProtocol = retrofit.create(RestServiceAddNoteProtocol.class);
        restServiceProtocol.addAndGetList(note).enqueue(AddNoteActivity.this);
    }

    @Override
    public void onResponse(Call<Notes> call, Response<Notes> response) {
        Log.d("Response " , ""+ response.body().toString());

        storage.saveNotes(response.body());
        AddNoteActivity.this.finish();
    }

    @Override
    public void onFailure(Call<Notes> call, Throwable t) {
        Log.d(TAG, "Request failed: " + t.getMessage());
    }
}
