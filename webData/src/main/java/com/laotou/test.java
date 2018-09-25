package com.laotou;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {
    public static void main(String[] args) {
        Result result = new Result();
        Map<String, Long> browerMap = new HashMap<>();
        //建立redis连接
        Jedis jedis = new Jedis("192.168.200.200", 6379);
        //查询redis中的网页数据
        //报错，当redis处于保护模式是，可以在客户端执行：config set protected-mode "no"
        Map<String, Long> bMap = (Map<String, Long>) SerializeUtil.unserialize(jedis.get("resultMapBrowserweb".getBytes()));
        if (bMap != null && bMap.size() > 0) {
            for (String key : bMap.keySet()) {
                String[] ss = key.split("]");
                Pattern p = Pattern.compile("\\s*|\t|\r|\n");
                Matcher m = p.matcher(ss[0]);
                String dest = m.replaceAll("");
                dest = dest.trim();
                if (dest != "" && dest != null && dest.length() > 1) {
                    browerMap.put(dest, bMap.get(key));
                    System.out.println(browerMap);
                }
            }
        }
    }
}
