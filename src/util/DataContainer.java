package util;

import java.io.Serializable;

public class DataContainer implements Serializable {
    private byte[] data;

    public DataContainer(byte[] data) {
        this.data = data;
    }

    public byte[] getData() {
        return data;
    }
}
