package com.metapack.assignments.impl;

import com.metapack.assignments.Accumulator;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
@NoArgsConstructor
public class AccumulatorImpl implements Accumulator {

    private int total = 0;

    @Override
    public int accumulate(int... values) {
        int passedValuesSum = Arrays.stream(values).sum();
        this.total = this.total + passedValuesSum;
        return passedValuesSum;
    }

    @Override
    public int getTotal() {
        return this.total;
    }

    @Override
    public void reset() {
        this.total = 0;
    }
}
