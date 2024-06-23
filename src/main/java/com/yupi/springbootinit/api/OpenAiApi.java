package com.yupi.springbootinit.api;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;

@Service
public class OpenAiApi {

    public static void main(String[] args) {

        String url = "http://XXX";
        Map<String,Object> hashMap = new HashMap<>();
        hashMap.put("message", "用户的消息，请帮我分析");
        String json = JSONUtil.toJsonStr(hashMap);
//        System.out.println(json);
        String result = HttpRequest.post(url)
                .header("Authorization", "Bearer")
                .body(json)
                .execute()
                .body();
        return ;
    }

}
