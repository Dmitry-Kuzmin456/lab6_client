package util;

import java.io.Serializable;

public record Request(String name, byte[] data) implements Serializable {}
