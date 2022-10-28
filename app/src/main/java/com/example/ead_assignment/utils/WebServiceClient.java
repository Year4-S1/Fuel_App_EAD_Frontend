package com.example.ead_assignment.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebServiceClient {

    private static WebServiceClient instance = null;
    private WebServiceInterface webService;

    private WebServiceClient() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(WebServiceInterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webService = retrofit.create(WebServiceInterface.class);
    }

    public static synchronized WebServiceClient getInstance() {
        if (instance == null) {
            instance = new WebServiceClient();
        }
        return instance;
    }

    public WebServiceInterface getWebService() {
        return webService;
    }

}
