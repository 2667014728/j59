package org.java.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * @Author: 马果
 * @Date: 2019/7/3 11:47
 * @Description:
 */
public class JsonUtil {

    private static ObjectMapper objectMapper =new ObjectMapper();

    public static JsonNode  stringToJsonNode(String json){
        try {
            JsonNode node = objectMapper.readTree(json);
            return node;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
