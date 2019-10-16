package com.bzk.wzzx.provider;

import com.alibaba.fastjson.JSON;
import com.bzk.wzzx.dto.AccessTokenDTO;
import com.bzk.wzzx.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GitHubProvider {

    public static final MediaType mediaType
            = MediaType.get("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();

    //Post to Server
    String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(mediaType, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
    //Get a URL
    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        try {
//            System.out.println(accessTokenDTO);
            String back = post("https://github.com/login/oauth/access_token", JSON.toJSONString(accessTokenDTO));
            return back;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public GithubUser getUser(String accessToken){
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            //将返回的json字符串解析成GithubUser对象
            GithubUser githubUser = com.alibaba.fastjson.JSON.parseObject(string, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
