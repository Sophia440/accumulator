package com.metapack.assignments.impl;

import com.metapack.assignments.Accumulator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class AccumulatorImplTest {
    private final Accumulator accumulator = new AccumulatorImpl();

    @AfterEach
    public void afterEach() {
        accumulator.reset();
    }

    @Test
    public void resetTest() {
        Accumulator accumulatorNotEmpty = new AccumulatorImpl(1);
        accumulatorNotEmpty.reset();
        assertEquals(0, accumulatorNotEmpty.getTotal());
    }

    @ParameterizedTest
    @MethodSource("accumulateTestParams")
    public void accumulateTestWhenEmpty(int expected, int... values) {
        assertEquals(0, accumulator.getTotal());
        int actualSum = accumulator.accumulate(values);
        assertEquals(expected, actualSum);
        assertEquals(expected, accumulator.getTotal());
    }

    static Stream<Arguments> accumulateTestParams() {
        return Stream.of(
                arguments(Integer.MAX_VALUE, new int[]{Integer.MAX_VALUE}),
                arguments(1, new int[]{1}),
                arguments(-1, new int[]{-1}),
                arguments(0, new int[]{0}),
                arguments(0, new int[]{1, -1}),
                arguments(10, new int[]{1, 2, 3, 4})
        );
    }

    @Test
    public void accumulateTestWhenNotEmpty() {
        assertEquals(0, accumulator.getTotal());
        accumulator.accumulate(1);
        assertEquals(1, accumulator.getTotal());
        accumulator.accumulate(2, 3, 4);
        assertEquals(10, accumulator.getTotal());
        accumulator.accumulate(-5, -5);
        assertEquals(0, accumulator.getTotal());
    }
}
