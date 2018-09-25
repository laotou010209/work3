package com.laotou;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import java.util.HashMap;
import java.util.Map;

public class IpBolt extends BaseRichBolt {
    Map<String,Long> resultMap;
    OutputCollector collector;
    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        resultMap=new HashMap<String, Long>();
        collector=outputCollector;
    }
    @Override
    public void execute(Tuple tuple) {
        //如果拿到自己想要的值
        String str = tuple.getValues().toString();
        String[] split = str.split("\t");
        if (split.length >= 3) {
            //拿到ip
            String ip = split[1];
            //对ip 进行解析
            String address = "";
            try {
                address = GetArea.getAddress("ip=" + ip, "utf-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
            //将拿到的结果放到map中
            //放进去之前需要先判断map中是否已经有该key
            if (resultMap.get(address) != null) {
                resultMap.put(address, resultMap.get(address) + 1L);
            } else {
                resultMap.put(address, 1L);
            }
            //发射
            collector.emit(new Values(resultMap));
        }
    }
    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("resultMapAddress"));
    }
}
