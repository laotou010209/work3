package com.laotou;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by  on 2018/4/18
 */
@Controller
@RequestMapping("show")
public class ShowData{

    @RequestMapping("showData")
    public String mainIndex(){
        return "showData";
    }

    @RequestMapping("browerCount")
    @ResponseBody
    public Result fnameCount(){
        Result result=new Result();
        Map<String,Long> browerMap=new HashMap<>();
        //建立redis连接
        Jedis jedis = new Jedis("192.168.200.200",6379);
        //查询redis中的网页数据
        Map<String,Long> bMap = (Map<String,Long>) SerializeUtil.unserialize(jedis.get("resultMapBrowserweb".getBytes()));
        if(bMap!=null && bMap.size()>0){
            for(String key : bMap.keySet()){
                String[] ss=key.split("]");
                Pattern p = Pattern.compile("\\s*|\t|\r|\n");
                Matcher m = p.matcher(ss[0]);
                String dest = m.replaceAll("");
                dest=dest.trim();
                if(dest!="" && dest!=null &&dest.length()>1 ){
                    browerMap.put(dest,bMap.get(key));
                }
            }
            result.setState(true);
            result.setData(browerMap);
            return result;
        }
        result.setState(false);
        result.setMessage("没有数据！");

        return result;
    }
    @RequestMapping("addressCount")
    @ResponseBody
    public Result faddrCount() {
        Result result = new Result();
        //建立redis连接
        Jedis jedis = new Jedis("192.168.200.200", 6379);
        Map<String, Long> provinceMap = (Map<String, Long>) SerializeUtil.unserialize(jedis.get("resultMapAddress".getBytes()));
        Map<String,Long> address = new HashMap<String, Long>();
        for (String key : provinceMap.keySet()) {
            if(key!=null){
                address.put(key,provinceMap.get(key));
            }
        }

        if (address != null && address.size() > 0) {
            result.setState(true);
            result.setData(address);
        }
        return result;
    }
}
