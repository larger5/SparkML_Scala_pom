/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cun.aiTry;

// $example on$

import java.util.Arrays;

import org.apache.spark.ml.regression.GeneralizedLinearRegression;
import org.apache.spark.ml.regression.GeneralizedLinearRegressionModel;
import org.apache.spark.ml.regression.GeneralizedLinearRegressionTrainingSummary;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
// $example off$
import org.apache.spark.sql.SparkSession;

/**
 * An example demonstrating generalized linear regression.
 * Run with
 * <pre>
 * bin/run-example ml.JavaGeneralizedLinearRegressionExample
 * </pre>
 */

public class JavaGeneralizedLinearRegressionExample {

    public static void main(String[] args) {
        SparkSession spark = SparkSession
                .builder()
                .master("local")
                .appName("JavaGeneralizedLinearRegressionExample")
                .getOrCreate();

        // $example on$
        // Load training data
        Dataset<Row> dataset = spark.read().format("libsvm").load("C:\\Users\\linhongcun\\Desktop\\sick_training.txt");
        RDD<Row> rdd = dataset.rdd();

        Dataset<Row> dataFrame = spark.createDataFrame(rdd, Medical.class);

        dataFrame.show();

        GeneralizedLinearRegression glr = new GeneralizedLinearRegression()
                .setFamily("gaussian")
                .setLink("identity")
                .setMaxIter(10)
                .setRegParam(0.3);

        // Fit the model
        GeneralizedLinearRegressionModel model = glr.fit(dataFrame);

        spark.stop();
    }
}