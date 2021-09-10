package com.ioutils.translator;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.*;

@Service
public class TranslatorService {

    public String getStringFromInputStream(InputStream input) throws IOException {
        StringWriter writer = new StringWriter();
        IOUtils;
        return writer.toString();
        StreamUtils.;
    }

    public static String stringWriter(String fileName) throws IOException {

        char[] buff = new char[1024];
        StringWriter stringWriter = new StringWriter();
        FileInputStream fStream = null;
        Reader bReader = null;

        try {

            fStream = new FileInputStream(fileName);
            bReader = new BufferedReader(new InputStreamReader(fStream, "UTF-8"));
            int n;
            while ((n = bReader.read(buff)) != -1) {
                stringWriter.write(buff, 0, n);
            }
        } finally {
            bReader.close();
            stringWriter.close();
            fStream.close();
        }
        return stringWriter.toString();
    }
}
