package com.github.avenderov;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JacksonTest {

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    void shouldDeserializePersonFromJson() throws Exception {
        final String json = TestUtils.toString("person.json");

        final Person person = mapper.readValue(json, Person.class);

        assertAll(() -> {
            assertEquals("John", person.getFirstName());
            assertEquals("Doe", person.getLastName());
            assertEquals(21, person.getAge());

            assertTrue(person.isConstructorCalled());
        });
    }

}
