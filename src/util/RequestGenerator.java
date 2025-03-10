package util;

import model.City;
import records.Request;
import serialize.SerializeCity;

import java.io.*;

public class RequestGenerator {

    public static byte[] generate(String name, byte[] args) throws IOException {
        Request request = new Request(name, args);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(request);
        objectOutputStream.flush();

        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] generateWithString(String name, String value) throws Exception {
        return generate(name, value.getBytes());
    }

    public static byte[] generateWithCity(String name, City city) throws Exception {
        byte[] citySerialized = SerializeCity.serialize(city);
        return generate(name, citySerialized);
    }
}
