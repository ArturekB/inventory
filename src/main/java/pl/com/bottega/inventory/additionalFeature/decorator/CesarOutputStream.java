package pl.com.bottega.inventory.additionalFeature.decorator;

import java.io.IOException;
import java.io.OutputStream;

public class CesarOutputStream extends OutputStream {

    private OutputStream stream;
    private Integer key;

    CesarOutputStream(OutputStream stream, Integer key) {
        this.stream = stream;
        this.key = key;
    }

    @Override
    public void write(int b) throws IOException { {
            stream.write((b + key) % 256);
        }
    }
}
