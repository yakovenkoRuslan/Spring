package com.nixsolution.util;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.net.ssl.HttpsURLConnection;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class VerifyUtils {

    public static final String SITE_KEY = "6Lc5Kz4dAAAAACARQxPn4Qk0BBu42VHdCaFkYWpM";

    public static final String SECRET_KEY = "6Lc5Kz4dAAAAAHexRcojq6HpSBN02UFZiyJAlw02";

    public static final String SITE_VERIFY_URL = //
            "https://www.google.com/recaptcha/api/siteverify";

    public static boolean verify(String gRecaptchaResponse) {
        if (gRecaptchaResponse == null || gRecaptchaResponse.length() == 0) {
            return false;
        }
        try {
            URL verifyUrl = new URL(SITE_VERIFY_URL);
            HttpsURLConnection conn = (HttpsURLConnection) verifyUrl.openConnection();
            conn.setRequestMethod("POST");
            String postParams =
                    "secret=" + SECRET_KEY + "&response=" + gRecaptchaResponse;
            conn.setDoOutput(true);
            OutputStream outStream = conn.getOutputStream();
            outStream.write(postParams.getBytes());
            outStream.flush();
            outStream.close();
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode=" + responseCode);
            InputStream is = conn.getInputStream();
            JsonReader jsonReader = Json.createReader(is);
            JsonObject jsonObject = jsonReader.readObject();
            jsonReader.close();
            boolean success = jsonObject.getBoolean("success");
            return success;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
