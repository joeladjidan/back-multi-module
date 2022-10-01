package com.joeladjidan.gestionfichier.file;

import org.springframework.stereotype.Service;

import java.io.*;
import java.util.stream.Stream;

@Service
public class FileReaderService {

    public int fileReaders(String fileName) throws IOException {

        Reader reader = new FileReader(fileName);

        BufferedReader br = new BufferedReader(reader, 16384);

        Stream<String> stream = br.lines();
        stream
                .filter(line -> {
                    return !line.trim().startsWith("#") && line.endsWith("B");
                })
                .forEach(System.out::println);

        br.close();
        return 0;
    }

}
