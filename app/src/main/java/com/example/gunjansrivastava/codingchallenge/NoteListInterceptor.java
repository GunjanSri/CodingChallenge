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

        String result = "[{\n" +
                "\t\t\"id\": \"1\",\n" +
                "\t\t\"description\": \"This is sample note to test.\"\n" +
                "\t},\n" +
                "\n" +
                "\t{\n" +
                "\t\t\"id\": \"2\",\n" +
                "\t\t\"description\": \"This is sample note to test.\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"id\": \"2\",\n" +
                "\t\t\"description\": \"This is sample note to test.\"\n" +
                "\t}\n" +
                "\n" +
                "]";

            ResponseBody body = getResponseBody(result);

            return getResponse(request,body);
    }

    private Response processGetRequest(){
        Request request = new Request.Builder()
                .url("http://www.codingchallege")
                .header("Accept", "application/json")
                .build();

        String result = "[{\n" +
                "\t\t\"id\": \"1\",\n" +
                "\t\t\"description\": \"This is sample note to test.\"\n" +
                "\t},\n" +
                "\n" +
                "\t{\n" +
                "\t\t\"id\": \"2\",\n" +
                "\t\t\"description\": \"This is sample note to test.\"\n" +
                "\t}\n" +
                "\n" +
                "]";

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
