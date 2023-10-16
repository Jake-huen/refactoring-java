package com.example.study;

import com.example.study.domain.Statement;
import com.example.study.dto.Invoice;
import com.example.study.dto.Play;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Currency;
import java.util.Locale;

@SpringBootApplication
public class StudyApplication {

    public static <T> T readJson(String filepath, Class<T> valueType) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.readValue(new File(filepath), valueType);
        } catch (IOException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        Play[] plays = readJson("/Users/kimtaeheon/Documents/GitHub/study/src/main/java/com/example/study/resource/plays.json", Play[].class);
        Invoice[] invoices = readJson("/Users/kimtaeheon/Documents/GitHub/study/src/main/java/com/example/study/resource/invoices.json", Invoice[].class);

        Statement statement = new Statement(invoices[0], plays);
        System.out.println(statement.readPlainText());
    }

}
