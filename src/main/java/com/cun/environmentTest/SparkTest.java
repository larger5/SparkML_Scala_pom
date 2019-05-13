package com.cun.environmentTest;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

public class SparkTest {
    public static void main(String[] args) {

        SparkConf conf = new SparkConf().setAppName("Testing").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);
        sc.setLogLevel("WARN");
        System.out.println(sc.appName());

    }
}