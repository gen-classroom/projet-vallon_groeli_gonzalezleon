package ch.heigvd.VallonGroeliGonzalezLeon.statique.benchmark;

import ch.heigvd.VallonGroeliGonzalezLeon.statique.Statique;
import org.openjdk.jmh.annotations.*;
import picocli.CommandLine;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class BenchmarksStatique {
    @Setup
    public void setup()
    {
        new CommandLine(new Statique()).execute("init", "/benchmark");
    }

    @Benchmark
    public void benchmarkBuild()
    {
        new CommandLine(new Statique()).execute("build", "-p=/benchmark");
    }
}
