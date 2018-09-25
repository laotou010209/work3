package com.laotou.data;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MainRun {
    public static void main(String[] args) {
        Log log = LogFactory.getLog(MainRun.class);
        for (int i=0 ;i<100;i++) {
            System.out.println(InitData.getLineData());
            log.info(InitData.getLineData());
        }
    }
}
