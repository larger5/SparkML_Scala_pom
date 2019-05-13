package com.cun.utils;

import com.huaban.analysis.jieba.JiebaSegmenter;

import java.util.List;

public class JieBaUtils {

    public static String getSentence(String sentence) {
        JiebaSegmenter jiebaSegmenter = new JiebaSegmenter();

        List<String> strings = jiebaSegmenter.sentenceProcess(sentence);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strings.size(); i++) {
            if (strings.get(i).equals("，") ||
                    strings.get(i).equals("。") ||
                    strings.get(i).equals("、") ||
                    strings.get(i).equals("：") ||
                    strings.get(i).equals("？")) {
                continue;
            }
            sb.append(strings.get(i)).append(" ");
        }
        return sb.substring(0, sb.length() - 1);
    }

    public static void main(String[] args) {
        String sentence = JieBaUtils.getSentence("搜索引擎模式，在精确模式的基础上，对长词再次切分，提高召回率，适合用于搜索引擎分词。");
        //搜索引擎 模式 在 精确 模式 的 基础 上 ， 对长 词 再次 切分 ， 提高 召回 率 ， 适合 用于 搜索引擎 分词
        System.out.println(sentence);
    }

}
