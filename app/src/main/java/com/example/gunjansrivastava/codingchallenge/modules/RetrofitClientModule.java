package com.example.gunjansrivastava.codingchallenge.modules;

import com.example.gunjansrivastava.codingchallenge.NoteListInterceptor;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitClientModule {
    String baseUrl;
    public RetrofitClientModule(String baseUrl){
        this.baseUrl = baseUrl;
    }

    @Provides
    public Retrofit getRetrofitInstance(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new NoteListInterceptor())
                .build();

        return new Retrofit.Builder()
                .baseUrl("https://www.codingchallenge")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }
}
