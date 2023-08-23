package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.Gson;
import okhttp3.*;

public class OpenAIIntegration {
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();

    String post(String url, String json) throws IOException {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(60, TimeUnit.SECONDS);
        builder.readTimeout(60, TimeUnit.SECONDS);
        builder.writeTimeout(60, TimeUnit.SECONDS);
        client = builder.build();
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("Authorization", "Bearer paste YOUR_OPENAI_TOKEN here")
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public String generateResponse(String input, int maxTokens) throws IOException {
        String url = "https://api.openai.com/v1/engines/code-davinci-002/completions";
        String json = "{"
                + "\"prompt\":\"" + input + "\","
                + "\"temperature\":0.9,"
                + "\"max_tokens\":" + maxTokens
                + "}";
        String response = post(url, json);
        return response;
    }

    public String saveResponseToFile(String response) throws IOException {
        Gson gson = new Gson();
        JsonObject json = gson.fromJson(response, JsonObject.class);
        JsonArray choices = json.getAsJsonArray("choices");
        JsonObject choice = choices.get(0).getAsJsonObject();
        String generatedText = choice.get("text").getAsString();
        try (FileWriter file = new FileWriter("response.json")) {
            file.write(json.toString());
            System.out.println("Successfully Copied JSON Object to File...");
            System.out.println("\nGenerated Text: " + generatedText);
        }
        return generatedText;
    }
}
