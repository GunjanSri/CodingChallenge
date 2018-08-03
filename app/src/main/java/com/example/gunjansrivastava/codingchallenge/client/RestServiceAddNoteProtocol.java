package com.example.gunjansrivastava.codingchallenge.client;

import com.example.gunjansrivastava.codingchallenge.viewmodel.NoteListViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RestServiceAddNoteProtocol {

    @POST("https://www.codingchallenge")
    public Call<List<NoteListViewModel>> addAndGetList(
            @Body NoteListViewModel note
    );
}
