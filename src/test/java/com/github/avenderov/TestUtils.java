package com.github.avenderov;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class TestUtils {

    private static final Class<?> CLAZZ = TestUtils.class;

    private TestUtils() {
    }

    public static String toString(final String classpathResource) {
        try {
            return new String(
                    Files.readAllBytes(Paths.get(CLAZZ.getClassLoader().getResource(classpathResource).toURI())),
                    StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

}
