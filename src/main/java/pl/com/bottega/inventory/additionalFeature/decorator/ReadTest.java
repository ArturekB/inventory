package pl.com.bottega.inventory.additionalFeature.decorator;

import pl.com.bottega.inventory.additionalFeature.decorator.CesarInputStream;

import java.io.FileInputStream;
import java.io.IOException;

public class ReadTest {

    private static final String INPUT_FILE = "D:\\testFile.txt";

    public static void main(String[] args) throws IOException {

        CesarInputStream in = new CesarInputStream(new FileInputStream(INPUT_FILE), 3);
        StringBuilder builder = new StringBuilder();
        int b;
        while ((b = in.read()) != -1) {
            builder.append((char) b);
        }
        System.out.println(builder.toString());
    }
}
