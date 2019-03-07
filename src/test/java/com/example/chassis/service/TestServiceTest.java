package com.example.chassis.service;

import com.example.chassis.dao.GreetingRepository;
import com.example.chassis.entity.GreetingEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestServiceTest {
    @Mock
    private GreetingRepository repo;

    private TestService testService;

    @Before
    public void setUp() {
        testService = new TestService(repo);
    }

    @Test
    public void testServiceShouldSayOhHi() {
        GreetingEntity ohHi = new GreetingEntity();
        ohHi.setId(1);
        ohHi.setMessage("Oh, hi!");
        when(repo.findById(1L)).thenReturn(Optional.of(ohHi));

        assertEquals("Oh, hi!", testService.greeting());
    }
}
