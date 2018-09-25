package com.laotou;

import org.apache.storm.LocalCluster;
import org.apache.storm.kafka.*;
import org.apache.storm.spout.SchemeAsMultiScheme;
import org.apache.storm.topology.TopologyBuilder;
import java.util.HashMap;

public class DataTopolgy {
    public static void main(String[] args){
        //zookeeper
        String zks = "master:2181,slave01:2181,slave02:2181";
        //主题
        String topic = "homework";
        BrokerHosts brokerHosts = new ZkHosts(zks);
        // ZooKeeper 的注册 node 名称（注意：需要加“/”，否则 ZooKeeper 会无法识别）
        String zkRoot = "/fkastorm";
        // 配置 Spout
        String spoutId = "myKafka";
        SpoutConfig spoutConfig = new SpoutConfig(brokerHosts,topic,zkRoot,spoutId);
        // 配置 Scheme（可选）
        spoutConfig.scheme = new SchemeAsMultiScheme(new StringScheme());
        KafkaSpout kafkaSpout = new KafkaSpout(spoutConfig);
        //调用主的api,构建拓扑对象
        TopologyBuilder builder = new TopologyBuilder();
        //builder关联spout和bolt
        builder.setSpout("mySpout",kafkaSpout);
        //计算ip
        builder.setBolt("IpBolt",new IpBolt()).shuffleGrouping("mySpout");
        builder.setBolt("IpPrintBolt",new IpPrintBolt()).shuffleGrouping("IpBolt");
        //计算浏览器
        builder.setBolt("BrowserBolt",new BrowserBolt()).shuffleGrouping("mySpout");
        builder.setBolt("BrowserPrintBolt",new BrowserPrintBolt()).shuffleGrouping("BrowserBolt");
        //本地模式发布，可以右键直接运行--用于开发时
        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("HelloStormData",new HashMap(),builder.createTopology());
    }
}
