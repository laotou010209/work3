package com.laotou.consumerUtils;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 消费者
 */
public class SendConsumer {
    public static void main(String[] args) throws IOException {
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(PropertiesUtils.getProperties("consumer"));
        //拿数据，设置从哪些主题下拿去数据
        consumer.subscribe(Arrays.asList("testtopic"));
        while (true) {
            //读取数据的超时间
            ConsumerRecords<String, String> records = consumer.poll(100);
            //遍历拿到的数据
            List<String> list=new ArrayList();
            for (ConsumerRecord<String, String> record : records)
                System.out.println(record.value());
        }
    }
}
