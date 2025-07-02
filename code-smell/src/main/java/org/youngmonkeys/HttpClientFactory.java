package org.youngmonkeys;

import lombok.AllArgsConstructor;
import org.youngmonkeys.code_smell.HttpClient;
import org.youngmonkeys.code_smell.service.SettingService;

@AllArgsConstructor
public class HttpClientFactory {

    private final SettingService settingService;

    public HttpClient newHttpClient() {
        HttpClient client = new HttpClient();
        client.setConnectTimeout(settingService.getDefaultConnectTimeout());
        client.setReadTimeout(settingService.getDefaultReadTimeout());
        return client;
    }

    public static void main(String[] args) throws Exception {
        HttpClientFactory factory = new HttpClientFactory(
            new SettingService()
        );
        HttpClient client = factory.newHttpClient();
        client.call(
            "GET",
            "https://ezyplatform.com"
        );
    }
}
