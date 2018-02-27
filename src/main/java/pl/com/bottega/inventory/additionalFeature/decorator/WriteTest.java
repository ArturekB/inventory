package pl.com.bottega.inventory.additionalFeature.decorator;

import pl.com.bottega.inventory.additionalFeature.decorator.CesarInputStream;
import pl.com.bottega.inventory.additionalFeature.decorator.CesarOutputStream;

import java.io.*;

public class WriteTest {

    private static final String OUTPUT_FILE = "D:\\testFile.txt";

    public static void main(String[] args) throws IOException {

        String sample = "Taki sobie tekst";
        byte[] bytes = sample.getBytes();

        CesarOutputStream out = new CesarOutputStream(new FileOutputStream(OUTPUT_FILE), 3);
        out.write(bytes);

        CesarInputStream in = new CesarInputStream(System.in, 3);
    }
}

