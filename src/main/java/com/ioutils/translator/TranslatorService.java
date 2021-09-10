package com.ioutils.translator;

import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class TranslatorService {
    public String reverseOrderTheLyrics(String song) {
        StringBuilder reverse = new StringBuilder();
        Scanner sc = new Scanner(song);
        while(sc.hasNextLine()){
            String space = sc.nextLine();
            if(space == ""){

            }
        }
        return "";
    }

    public int countStrophes(String lyricsReverted) {
        int count = 1;
        Scanner sc = new Scanner(lyricsReverted);
        while(sc.hasNextLine()){
            String space = sc.nextLine();
            if(space.equals("")){
                count++;
            }
        }
        return count;
    }

    public String countTheMostRepeatedWord(String lyrics) {
        return "";
    }

    public String replaceTheMostRepeatedWord(String lyrics) {
        return lyrics.replaceAll("your", "flower");
    }
}
