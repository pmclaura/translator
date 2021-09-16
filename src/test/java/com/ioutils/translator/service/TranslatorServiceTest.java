package com.ioutils.translator.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;

import static org.testng.Assert.*;

@SpringBootTest
public class TranslatorServiceTest {
    @Autowired
    private TranslatorService translatorService;

    @Test
    public void givenTheLyrics_WhenCopyLyrics_ThenShouldBeInTheNewFile() throws IOException {
        assertTrue(translatorService.invertOrderTheLyrics().exists());
        assertEquals(17,translatorService.countStrophes());
        assertTrue(translatorService.getWordsQuantity().exists());
        assertTrue(translatorService.replaceTheMostRepeatedWord().exists());
    }
}