package com.ioutils.translator.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Stack;

@Service
public class TranslatorService {
    private String original = "src/main/resources/files/original.txt";
    private String estrofasEnOrdenInverso = "src/main/resources/files/estrofasEnOrdenInverso.txt";
    private String statistics = "src/main/resources/files/statistics.txt";
    private String finaloutput= "src/main/resources/files/finaloutput.txt";

    private String getString() throws IOException {
        InputStream in = new FileInputStream(original);
        return StreamUtils.copyToString(in, StandardCharsets.UTF_8);
    }

    private File copyFile(String text, String nameFile) throws IOException {
        File outputFile = new File(nameFile);
        OutputStream out = new FileOutputStream(outputFile);
        StreamUtils.copy(text,StandardCharsets.UTF_8,out);
        return outputFile;
    }

    public File invertOrderTheLyrics() throws IOException {
        String inverse = "";
        Stack<String> strophes = new Stack<>();
        String[] inverts = getString().split("\r\n");
        for (String line : inverts) {
            strophes.push(line);
        }
        while(!strophes.empty()){
            inverse += strophes.pop()+"\n";
        }
        return copyFile(inverse, estrofasEnOrdenInverso);
    }

    public int countStrophes() throws IOException {
        String lyrics = getString();
        int count = 1;
        String[] words = lyrics.split("\r\n");
        for(String word: words){
            if(word.isBlank()){
                count++;
            }
        }
        return count;
    }

    private HashMap<String, Integer> countWords(String lyrics){
        lyrics = lyrics.replaceAll("[,'?]", "");
        lyrics = lyrics.replaceAll("\\R", " ");
        lyrics = lyrics.toLowerCase();
        String[] words = lyrics.split(" ");
        HashMap<String, Integer> stringIntegerHashMap = new HashMap<>();
        for (String word : words) {
            if (stringIntegerHashMap.containsKey(word)) {
                stringIntegerHashMap.put(word, stringIntegerHashMap.get(word) + 1);
            } else {
                stringIntegerHashMap.put(word, 1);
            }
        }
        return stringIntegerHashMap;
    }

    public File getWordsQuantity () throws IOException {
        String counts="";
        HashMap<String, Integer> map = countWords(getString());
        for (HashMap.Entry<String, Integer> entry : map.entrySet()) {
            counts += entry.getKey()+"---->"+entry.getValue()+"\n";
        }
        return copyFile(counts, statistics);
    }

    private String getTheMostRepeatedWord(HashMap<String, Integer> map){
        String highestVal = "";
        int maxValueInMap = Collections.max(map.values());
        for (HashMap.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == maxValueInMap) {
                highestVal = entry.getKey();
            }
        }
        return highestVal;
    }

    public File replaceTheMostRepeatedWord() throws IOException {
        HashMap<String, Integer> map = countWords(getString());
        String word = getTheMostRepeatedWord(map);
        String replaced = getString().replaceAll(word, "you");
        return copyFile(replaced, finaloutput);
    }
}
