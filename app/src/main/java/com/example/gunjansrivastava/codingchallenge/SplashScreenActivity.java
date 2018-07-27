package com.example.gunjansrivastava.codingchallenge;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.gunjansrivastava.codingchallenge.client.RestServiceProtocol;
import com.example.gunjansrivastava.codingchallenge.components.DaggerRetrofitComponents;
import com.example.gunjansrivastava.codingchallenge.modules.RetrofitClientModule;
import com.example.gunjansrivastava.codingchallenge.response.Notes;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SplashScreenActivity extends AppCompatActivity implements Callback<Notes>{

    private final String TAG = SplashScreenActivity.class.getSimpleName();
    private NotesStorage storage ;

    @Inject
    Retrofit retrofitClient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        storage = NotesStorage.getInstance(this);
        if (storage.getNotes() == null) {
            DaggerRetrofitComponents.builder()
                    .retrofitClientModule(new RetrofitClientModule("http://www.google.vom"))
                    .build().inject(this);
            RestServiceProtocol restServiceProtocol = retrofitClient.create(RestServiceProtocol.class);

            restServiceProtocol.getList().enqueue(this);
        } else {
            Intent intent = new Intent(SplashScreenActivity.this , NotesListActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onResponse(Call<Notes> call, Response<Notes> response) {
        storage.saveNotes(response.body());
        SplashScreenActivity.this.finish();
    }

    @Override
    public void onFailure(Call<Notes> call, Throwable t) {
        Log.d(TAG, "Request failed: " + t.getMessage());
    }
}
