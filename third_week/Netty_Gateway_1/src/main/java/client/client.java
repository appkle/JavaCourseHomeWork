package client;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class client {
    public static OkHttpClient okHttpClient = new OkHttpClient();

    // GET 调用
    public static String getAsString(String url) throws IOException {
        Request request = new Request.Builder()
                .header("token", "fszf")
                .url(url)
                .build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public static void main(String[] args) throws Exception {

        String url = "http://127.0.0.1:8808/test";
        String text = client.getAsString(url);
        System.out.println("url: " + url + " ; response: " + text);

        // 清理资源
        client.okHttpClient = null;
    }
}
