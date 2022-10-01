package com.joeladjidan.gestionfichier;

import com.joeladjidan.gestionfichier.file.FileReaderService;
import com.joeladjidan.gestionfichier.utils.Constants;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import static org.junit.Assert.assertEquals;

@SpringBootTest
public class ApiGestionFichierApplicationTests {

    @Autowired
    FileReaderService fileReaderService;

    @BeforeClass
    public static void setUpBeforeClass()  {
        System.out.println( "@BeforeClass");
    }

    @DisplayName("Test loading a properties file")
    @Test
    public void test1() throws IOException {
        int result = fileReaderService.fileReaders(Constants.FILENAME);
        // On vérifie les résultats
        assertEquals( 0 /* Valeur attendue */, result /* Valeur constatée */);

    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        System.out.println( "@AfterClass" );
    }

}
