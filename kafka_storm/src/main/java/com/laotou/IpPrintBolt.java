package com.laotou;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;
import redis.clients.jedis.Jedis;

import java.util.Map;

public class IpPrintBolt extends BaseRichBolt {
    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
    }
    @Override
    public void execute(Tuple tuple) {
        //拿到传递过来的值
        Map<String,Long> resultMap = (Map<String,Long>)tuple.getValueByField("resultMapAddress");
        //建立redis连接
        Jedis jedis = new Jedis("192.168.200.200",6379);
        //存入redis
        jedis.set("resultMapAddress".getBytes(), SerializeUtil.serialize(resultMap));
        //处理
        for(String key:resultMap.keySet()){
            System.out.println(key+"======"+resultMap.get(key));
        }
    }
    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }
}
