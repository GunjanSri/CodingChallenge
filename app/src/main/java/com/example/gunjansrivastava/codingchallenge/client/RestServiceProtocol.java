package com.example.gunjansrivastava.codingchallenge.client;

import com.example.gunjansrivastava.codingchallenge.viewmodel.NoteListViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestServiceProtocol {
    @GET("https://www.codingchallenge")
    public Call<List<NoteListViewModel>> getList();
}
