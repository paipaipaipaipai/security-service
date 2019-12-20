package org.potholes.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class JsonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        // 忽略未知字段属性
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static String objectToJsonStr(Object o) {
        try {
            return objectMapper.writeValueAsString(o);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T jsonStrToObject(String json, Class<T> cls) {
        try {
            return objectMapper.readValue(json, cls);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> List<T> jsonStrToList(String json, Class<?> clazz) {
        try {
            TypeFactory t = TypeFactory.defaultInstance();
            return objectMapper.readValue(json, t.constructCollectionType(ArrayList.class, clazz));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> jsonStrToMap(String json) {
        try {
            return objectMapper.readValue(json, HashMap.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String jsonStringByKey(String json, String key) {
        try {
            JsonNode node = objectMapper.readTree(json);
            if (node.has(key)) {
                return node.get(key).toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // public static void main(String[] args) {
    // String json =
    // "{\"mac_algorithm\":\"hmac-sha-1\",\"mac_key\":\"4d48e69ca66c9\",\"token_type\":\"mac\",\"access_token\":\"2CQ0K3X5HUijL8b4\"}";
    // System.out.println(jsonStringByKey(json, "access_token"));
    // }

}
