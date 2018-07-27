package com.example.gunjansrivastava.codingchallenge;

import android.support.annotation.NonNull;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class NoteListInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain){

        if(chain.request().method().equals("GET")){
            return processGetRequest();
        }else{
            return processPostRequest(chain);
        }
    }

    private Response processPostRequest(Chain chain){
        Request request = new Request.Builder()
                .url("http://www.codingchallege")
                .header("Accept", "application/json")
                .post(chain.request().body())
                .build();

        String result = "{\n" +
                "\t\"notesList\": [\n" +
                "\t\t{\n" +
                "\t\t\t\"id\": \"1\",\n" +
                "\t\t\t\"description\": \"Note 1 Description\"\n" +
                "\t\t},\n" +
                "\n" +
                "\t\t{\n" +
                "\t\t\t\"id\": \"2\",\n" +
                "\t\t\t\"description\": \"Note 2 Description\"\n" +
                "\t\t},\n" +
                "\n" +
                "\t\t{\n" +
                "\t\t\t\"id\": \"3\",\n" +
                "\t\t\t\"description\": \"Note 3 Description\"\n" +
                "\t\t}\n" +
                "\t]\n" +
                "}";

            ResponseBody body = getResponseBody(result);

            return getResponse(request,body);
    }

    private Response processGetRequest(){
        Request request = new Request.Builder()
                .url("http://www.codingchallege")
                .header("Accept", "application/json")
                .build();

        String result = "{\n" +
                "\t\"notesList\": [\n" +
                "\t\t{\n" +
                "\t\t\t\"id\": \"1\",\n" +
                "\t\t\t\"description\": \"This is sample note to test.\"\n" +
                "\t\t},\n" +
                "\n" +
                "\t\t{\n" +
                "\t\t\t\"id\": \"2\",\n" +
                "\t\t\t\"description\": \"This is sample note to test.\"\n" +
                "\t\t}\n" +
                "\t]\n" +
                "}";

            ResponseBody body = getResponseBody(result);
            return getResponse(request,body);
    }

    @NonNull
    private ResponseBody getResponseBody(String result){

        return ResponseBody.create(MediaType.parse("Application/Json"), result);
    }

    private Response getResponse(Request request , ResponseBody responseBody){
        Response response = new Response.Builder().code(200)
                .request(request)
                .protocol(Protocol.HTTP_1_0)
                .body(responseBody)
                .build();
        response.body().close();
        return response;
    }
}
