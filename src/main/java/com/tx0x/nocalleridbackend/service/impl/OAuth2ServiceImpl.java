package com.tx0x.nocalleridbackend.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tx0x.nocalleridbackend.service.OAuth2Service;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class OAuth2ServiceImpl implements OAuth2Service {

    private static final String key = "4208e05e30e184317b9d4af6781878e4";
    private static final String oauthTokenUrl = "https://kauth.kakao.com/oauth/token";
    private static final String userMeUrl = "https://kapi.kakao.com/v2/user/me";

    @Override
    public JsonNode getToken(String code, String path) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("grant_type", "authorization_code"));
        params.add(new BasicNameValuePair("client_id", key));
        params.add(new BasicNameValuePair("redirect_uri",
                "http://localhost:8082/oauth2/" + path + "/kakao"));
        params.add(new BasicNameValuePair("code", code));

        final HttpClient client = HttpClientBuilder.create().build();
        final HttpPost post = new HttpPost(oauthTokenUrl);

        JsonNode jsonNode = null;

        try {
            post.setEntity(new UrlEncodedFormEntity(params));
            final HttpResponse response = client.execute(post);
            ObjectMapper mapper = new ObjectMapper();
            jsonNode = mapper.readTree(response.getEntity().getContent());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("[getToken] INFO: " + Objects.requireNonNull(jsonNode).toPrettyString());
        }
        return jsonNode;
    }

    @Override
    public JsonNode getUser(String token) {
        final HttpClient client = HttpClientBuilder.create().build();
        final HttpPost post = new HttpPost(userMeUrl);

        post.addHeader("Authorization", "Bearer " + token);

        JsonNode jsonNode = null;

        try {
            final HttpResponse response = client.execute(post);
            ObjectMapper mapper = new ObjectMapper();
            jsonNode = mapper.readTree(response.getEntity().getContent());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("[getUser] INFO: " + jsonNode.toPrettyString());
        }
        return jsonNode;
    }
}
