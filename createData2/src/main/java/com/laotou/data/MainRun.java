package com.laotou.data;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MainRun {
    public static void main(String[] args){
        Log log = LogFactory.getLog(MainRun.class);
            System.out.println(InitData.getLineData());
            log.info(InitData.getLineData());
        }
    }
