package org.youngmonkeys.code_smell;

import lombok.Setter;
import org.youngmonkeys.code_smell.service.SettingService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Setter
public class HttpClient {

    private int connectTimeout;
    private int readTimeout;

    public void call(
        String method,
        String url
    ) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setConnectTimeout(connectTimeout);
        con.setReadTimeout(readTimeout);
        con.setRequestMethod(method);
        int responseCode = con.getResponseCode();
        System.out.println("Response Code: " + responseCode);
        BufferedReader in = new BufferedReader(
            new InputStreamReader(
                con.getInputStream(),
                StandardCharsets.UTF_8
            )
        );
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine).append("\n");
        }
        in.close();
        System.out.println("Response:\n" + response);
    }

    public static void main(String[] args) throws Exception {
        SettingService settingService = new SettingService();
        HttpClient client = new HttpClient();
        client.setConnectTimeout(settingService.getDefaultConnectTimeout());
        client.setReadTimeout(settingService.getDefaultReadTimeout());
        client.call(
            "GET",
            "https://ezyplatform.com"
        );
    }
}
