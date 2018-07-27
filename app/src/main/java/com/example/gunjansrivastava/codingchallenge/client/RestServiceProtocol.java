package com.example.gunjansrivastava.codingchallenge.client;

import com.example.gunjansrivastava.codingchallenge.response.Notes;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestServiceProtocol {
    @GET("https://www.codingchallenge")
    public Call<Notes> getList();
}
