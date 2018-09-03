package com.chapter15;

import com.jsoniter.JsonIterator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Http Get request of the Advice Slip JSON API
 */
public class AdviceSlipRequest {
    final private static String SLIP_ADVICE_API_PATH = "http://api.adviceslip.com/advice";
    final private static String SLIP_SEARCH_API_PATH = "http://api.adviceslip.com/advice/search";
    final private static String HTTP_GET_METHOD = "GET";

    /**
     *
     * @return
     */
    public static String GetSlipAdvice() {
       return GetRequest(SLIP_ADVICE_API_PATH);
    }

    /**
     *
     * @param slipId
     * @return
     */
    public static String GetSlipAdvice(int slipId) {
        return GetRequest(SLIP_ADVICE_API_PATH + "/" + slipId);
    }

    private static String GetRequest(String url){
        String response = null;
        try {
            URL slipAdviceApi = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) slipAdviceApi.openConnection();
            connection.setRequestMethod(HTTP_GET_METHOD);
            connection.connect();
            int responseCode = connection.getResponseCode();

            if (responseCode != 200) {
                throw new RuntimeException("Http Response Code: " + responseCode);
            } else {
                response = read(slipAdviceApi.openStream());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return response;
    }

    public static String read(InputStream input) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input))) {
            return bufferedReader.lines().collect(Collectors.joining(""));
        }
    }
    /**
     *
     * @param jsonResponse
     * @return
     */
    public static AdviceSlip parseSlipResponse(String jsonResponse) {
        AdviceSlip adviceSlip = JsonIterator.deserialize(jsonResponse, AdviceSlip.class);
        return adviceSlip;
    }
}
