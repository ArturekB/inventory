package pl.com.bottega.inventory.additionalFeature.decorator;

import java.io.IOException;
import java.io.InputStream;


public class CesarInputStream extends InputStream {

    private InputStream stream;
    private Integer key;

    CesarInputStream(InputStream stream, Integer key) {
        this.stream = stream;
        this.key = key;
    }

    @Override
    public int read() throws IOException {
        int result=stream.read();
        if (result == -1)
            return -1;
        int uncoded = (result - key) % 256;
        return (uncoded > 0 ? uncoded : uncoded + 256);
    }
}
