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

public class BrowserBolt extends BaseRichBolt{
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
            //拿到浏览器
            String browser = split[2];
            //将拿到的结果放到map中
            //放进去之前需要先判断map中是否已经有该key
            if (resultMap.get(browser) != null) {
                resultMap.put(browser, resultMap.get(browser) + 1L);
            } else {
                resultMap.put(browser, 1L);
            }
            //发射
            collector.emit(new Values(resultMap));
        }
    }
    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("resultMapBrowserweb"));
    }
}
