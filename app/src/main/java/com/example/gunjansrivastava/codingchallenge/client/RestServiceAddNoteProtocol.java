package com.example.gunjansrivastava.codingchallenge.client;

import com.example.gunjansrivastava.codingchallenge.response.Note;
import com.example.gunjansrivastava.codingchallenge.response.Notes;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RestServiceAddNoteProtocol {

    @POST("https://www.codingchallenge")
    public Call<Notes> addAndGetList(
            @Body Note note
    );
}
