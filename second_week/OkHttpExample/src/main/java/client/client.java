package client;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class client {
    public static void main(String[] args){
        String url = "http://localhost:8801/";
        String message = read(url);
        System.out.println(message);
    }

    public static String read(String url){
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        String message = null;
        try (Response response = okHttpClient.newCall(request).execute()) {
            message = response.body().string();
        } catch (IOException e) {
            message = "获取信息失败";
            e.printStackTrace();
        }
        return message;
    }
}
