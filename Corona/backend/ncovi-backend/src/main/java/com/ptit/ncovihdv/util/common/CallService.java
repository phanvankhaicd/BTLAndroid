package com.ptit.ncovihdv.util.common;

import com.google.gson.Gson;
import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 03-Jun-2020
 */
public class CallService {
    public static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");

    public static String callPost(String url, Object object) {
        try {
            String requestBody = new Gson().toJson(object);
            OkHttpClient client = new OkHttpClient.Builder().readTimeout(5, TimeUnit.MINUTES).writeTimeout(5, TimeUnit.MINUTES).build();

            RequestBody body = RequestBody.create(requestBody, MEDIA_TYPE);
            Request requestVHT = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();

            Response executeVht = client.newCall(requestVHT).execute();
            String response = executeVht.body().string();
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String callPut(String url, Object object) {
        try {
            String requestBody = new Gson().toJson(object);
            OkHttpClient client = new OkHttpClient.Builder().readTimeout(5, TimeUnit.MINUTES).writeTimeout(5, TimeUnit.MINUTES).build();

            RequestBody body = RequestBody.create(requestBody, MEDIA_TYPE);
            Request requestVHT = new Request.Builder()
                    .url(url)
                    .put(body)
                    .build();

            Response executeVht = client.newCall(requestVHT).execute();

            System.out.println("Request: " + requestBody);
            String response = executeVht.body().string();
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String callGet(String url) {
        try {
            OkHttpClient client = new OkHttpClient.Builder().readTimeout(5, TimeUnit.MINUTES).writeTimeout(5, TimeUnit.MINUTES).build();
            Request requestVHT = new Request.Builder()
                    .url(url)
                    .get()
                    .build();

            Response executeVht = client.newCall(requestVHT).execute();
            String response = executeVht.body().string();
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getErrorCode(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.has("result")) {
                JSONObject resultObject = jsonObject.getJSONObject("result");
                if (resultObject.has("errorCode")) {
                    return (int) resultObject.get("errorCode");
                }
            } else if (jsonObject.has("errorCode")) {
                return (int) jsonObject.get("errorCode");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    public static String getWsResponse(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.has("result")) {
                JSONObject resultObject = jsonObject.getJSONObject("result");
                if (resultObject.has("wsResponse")) {
                    return String.valueOf(resultObject.get("wsResponse"));
                }
            } else if (jsonObject.has("wsResponse")) {
                return String.valueOf(jsonObject.get("wsResponse"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }
}
