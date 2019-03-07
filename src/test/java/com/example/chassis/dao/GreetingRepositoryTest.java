package com.example.chassis.dao;

import com.example.chassis.entity.GreetingEntity;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.google.common.collect.ImmutableMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@DataJpaTest
public class GreetingRepositoryTest extends AbstractDaoTest<GreetingRepository> {

    @Test
    public void emptyDbShouldReturnNothingByNonExistingId() {
        Optional<GreetingEntity> entity = dao.findById(847234234L);
        assertFalse(entity.isPresent());
    }

    @Test
    public void shouldBeAbleToAddBook() {
        long id = addRecordToDatabase("Greetings", ImmutableMap.of("message", "Hey!"));
        Optional<GreetingEntity> entity = dao.findById(id);
        assertTrue(entity.isPresent());
        assertEquals(entity.get().getMessage(), "Hey!");
    }

    @Test
    @Sql("/db/sample-greetings.sql")
    public void shouldBeAllRetrieveRecordsByMultipleIds() {
        List<GreetingEntity> greetings = dao.findAllById(Arrays.asList(123L, 456L));

        assertEquals(greetings.size(), 2);
        assertThat(greetings.get(0).getId(), is(123L));
        assertThat(greetings.get(0).getMessage(), is("Hello"));
        assertThat(greetings.get(1).getId(), is(456L));
        assertThat(greetings.get(1).getMessage(), is("Hi"));
    }

    @Test
    @DataSet("/db/before-mutation.xml")
    @ExpectedDataSet("/db/after-mutation.xml")
    @Commit
    public void shouldBeAbleToUpdateRecord() {
        updateRecordToDatabaseWithId("Greetings", 555L, ImmutableMap.of("message", "Hey"));
        addRecordToDatabaseWithId("Greetings", 777L, ImmutableMap.of("message", "Welcome"));
    }
}
