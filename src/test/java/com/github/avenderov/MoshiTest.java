package com.github.avenderov;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class MoshiTest {

    private final Moshi moshi =
            new Moshi.Builder()
                    .build();

    @Test
    void shouldDeserializePersonFromJson() throws Exception {
        final String json = TestUtils.toString("person.json");

        final JsonAdapter<Person> adapter = moshi.adapter(Person.class);
        final Person person = adapter.fromJson(json);

        assertAll(() -> {
            assertEquals("John", person.getFirstName());
            assertEquals("Doe", person.getLastName());
            assertEquals(21, person.getAge());

            // Magic is happening here
            assertFalse(person.isConstructorCalled());
        });
    }

}
