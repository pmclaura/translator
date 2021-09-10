package com.ioutils.translator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StreamUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.testng.Assert.*;

@SpringBootTest
public class TranslatorServiceTest {
    @Autowired
    private TranslatorService translatorService;

    @Test
    public void givenTheLyrics_WhenRevertTheLyrics_ThenTheNewLyricsShouldBeInTheNewFile() throws IOException {
        String inputFileName = "src/test/resources/original.txt";
        String outputFileName = "src/test/resources/estrofasEnOrdenInverso.txt";
        File outputFile = new File(outputFileName);
        InputStream in = new FileInputStream(inputFileName);
        OutputStream out = new FileOutputStream(outputFile);
        String lyrics = StreamUtils.copyToString(in, StandardCharsets.UTF_8);
        String reverse = translatorService.reverseOrderTheLyrics(lyrics);
        StreamUtils.copy(reverse,StandardCharsets.UTF_8,out);
        System.out.println(reverse);
        assertTrue(outputFile.exists());
    }

    @Test
    public void givenTheLyrics_WhenCountStrophes_ThenShouldBeNumberOfTheLyrics() throws IOException {
        String inputFileName = "src/test/resources/original.txt";
        InputStream in = new FileInputStream(inputFileName);
        String lyricsReverted = StreamUtils.copyToString(in, StandardCharsets.UTF_8);
        int strophes = translatorService.countStrophes(lyricsReverted);
        assertEquals(17,strophes);
    }

    @Test
    public void givenTheLyrics_WhenCountTheMostRepeatedWord_ThenShouldBeTheInfoInAFile() throws IOException {
        String inputFileName = "src/test/resources/original.txt";
        String outputFileName = "src/test/resources/statistics.txt";
        File outputFile = new File(outputFileName);
        InputStream in = new FileInputStream(inputFileName);
        OutputStream out = new FileOutputStream(outputFile);
        String lyrics = StreamUtils.copyToString(in, StandardCharsets.UTF_8);
        String mostRepeated = translatorService.countTheMostRepeatedWord(lyrics);
        StreamUtils.copy(mostRepeated,StandardCharsets.UTF_8,out);
        assertTrue(outputFile.exists());
    }

    @Test
    public void givenTheLyrics_WhenReplaceTheMostRepeatedWord_ThenShouldBeTheInfoInAFile() throws IOException {
        String inputFileName = "src/test/resources/original.txt";
        String outputFileName = "src/test/resources/finaloutput.txt";
        File outputFile = new File(outputFileName);
        InputStream in = new FileInputStream(inputFileName);
        OutputStream out = new FileOutputStream(outputFile);
        String lyrics = StreamUtils.copyToString(in, StandardCharsets.UTF_8);
        String replacement = translatorService.replaceTheMostRepeatedWord(lyrics);
        StreamUtils.copy(replacement,StandardCharsets.UTF_8,out);
        assertTrue(outputFile.exists());
    }
}