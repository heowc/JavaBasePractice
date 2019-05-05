package com.example;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.atomic.AtomicInteger;

public class JMHSample {

    @State(Scope.Benchmark)
    public static class BenchmarkState {
        volatile AtomicInteger atomicInteger = new AtomicInteger(0);
    }

    @State(Scope.Thread)
    public static class ThreadState {
        volatile AtomicInteger atomicInteger = new AtomicInteger(0);
    }

    @Benchmark
    public void wellHelloThere(BenchmarkState state) {
        state.atomicInteger.incrementAndGet();

    }

    @Benchmark
    public void wellHelloThere2(ThreadState state) {
        state.atomicInteger.incrementAndGet();

    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JMHSample.class.getSimpleName())
                .forks(1)
                .threads(4)
                .warmupIterations(0)
                .measurementIterations(10)
                .build();

        new Runner(opt).run();
    }

}