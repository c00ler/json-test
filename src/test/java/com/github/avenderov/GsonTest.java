package com.github.avenderov;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GsonTest {

    private final Gson gson =
            new GsonBuilder()
                    .setPrettyPrinting()
                    .create();

    @Test
    void shouldSerializePersonIntoJson() {
        final Person person = new Person("John", "Doe", 21);

        final String json = gson.toJson(person);

        final DocumentContext parsed = JsonPath.parse(json);
        assertAll(() -> {
            assertEquals("John", parsed.read("$.firstName"));
            assertEquals("Doe", parsed.read("$.lastName"));
            assertEquals(Integer.valueOf(21), parsed.read("$.age", Integer.class));

            assertTrue(person.isConstructorCalled());
        });
    }

    @Test
    void shouldDeserializePersonFromJson() {
        final String json = TestUtils.toString("person.json");

        final Person person = gson.fromJson(json, Person.class);

        assertAll(() -> {
            assertEquals("John", person.getFirstName());
            assertEquals("Doe", person.getLastName());
            assertEquals(21, person.getAge());

            // Magic is happening here
            assertFalse(person.isConstructorCalled());
        });
    }

}
