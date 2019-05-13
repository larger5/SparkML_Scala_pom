//package com.cun.MyAI;
//
//import java.io.Serializable;
//import java.util.List;
//
//import com.google.common.collect.Lists;
//import org.apache.spark.api.java.JavaSparkContext;
//import org.apache.spark.ml.Pipeline;
//import org.apache.spark.ml.PipelineModel;
//import org.apache.spark.ml.PipelineStage;
//import org.apache.spark.ml.classification.LogisticRegression;
//import org.apache.spark.ml.feature.HashingTF;
//import org.apache.spark.ml.feature.Tokenizer;
//import org.apache.spark.rdd.RDD;
//import org.apache.spark.sql.Dataset;
//import org.apache.spark.sql.SQLContext;
//import org.apache.spark.sql.api.java.JavaSQLContext;
//import org.apache.spark.sql.api.java.JavaSchemaRDD;
//import org.apache.spark.SparkConf;
//import org.apache.spark.sql.api.java.Row;
//import org.apache.spark.sql.types.*;
//
//public class PipelineTest {
//
//    public static void main(String[] args) {
//// Set up contexts.
//        SparkConf conf = new SparkConf().setAppName("sick_training").setMaster("local");
//        JavaSparkContext jsc = new JavaSparkContext(conf);
//        jsc.setLogLevel("WARN");
//        JavaSQLContext jsql = new JavaSQLContext(jsc);
//
//// Prepare training documents, which are labeled.
//        List<LabeledDocument> localTraining = Lists.newArrayList(
//                new LabeledDocument(0L, "a b c d e spark", 1.0),
//                new LabeledDocument(1L, "b d", 0.0),
//                new LabeledDocument(2L, "spark f g h", 1.0),
//                new LabeledDocument(3L, "hadoop mapreduce", 0.0));
//        JavaSchemaRDD training =
//                jsql.applySchema(jsc.parallelize(localTraining), LabeledDocument.class);
//
//        StructType schema = new StructType(new StructField[]{
//                new StructField("id", DataTypes.LongType, false, Metadata.empty()),
//                new StructField("text", DataTypes.StringType, false, Metadata.empty()),
//                new StructField("label", DataTypes.DoubleType, false, Metadata.empty()),
//        });
//
//// Configure an ML pipeline, which consists of three stages: tokenizer, hashingTF, and lr.
//        Tokenizer tokenizer = new Tokenizer()
//                .setInputCol("text")
//                .setOutputCol("words");
//        HashingTF hashingTF = new HashingTF()
//                .setNumFeatures(1000)
//                .setInputCol(tokenizer.getOutputCol())
//                .setOutputCol("features");
//        LogisticRegression lr = new LogisticRegression()
//                .setMaxIter(10)
//                .setRegParam(0.01);
//        Pipeline pipeline = new Pipeline()
//                .setStages(new PipelineStage[]{tokenizer, hashingTF, lr});
//
//// Fit the pipeline to training documents.
//        PipelineModel model = pipeline.fit(training);
//
//// Prepare test documents, which are unlabeled.
//        List<Document> localTest = Lists.newArrayList(
//                new Document(4L, "spark i j k"),
//                new Document(5L, "l m n"),
//                new Document(6L, "mapreduce spark"),
//                new Document(7L, "apache hadoop"));
//        JavaSchemaRDD test =
//                jsql.applySchema(jsc.parallelize(localTest), Document.class);
//
//// Make predictions on test documents.
//        model.transform(test).registerAsTable("prediction");
//        JavaSchemaRDD predictions = jsql.sql("SELECT id, text, score, prediction FROM prediction");
//        for (Row r : predictions.collect()) {
//            System.out.println("(" + r.get(0) + ", " + r.get(1) + ") --> score=" + r.get(2)
//                    + ", prediction=" + r.get(3));
//        }
//    }
//}
