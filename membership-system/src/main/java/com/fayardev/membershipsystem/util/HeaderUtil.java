package com.fayardev.membershipsystem.util;

import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;

public class HeaderUtil {

    public static String getTokenPayloadID(HttpServletRequest request) throws JSONException {
        String token = request.getHeader("Authorization");

        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getDecoder();

        String payload = new String(decoder.decode(chunks[1]));
        JSONObject jsonObject = new JSONObject(payload);

        return jsonObject.get("id").toString();
    }
}