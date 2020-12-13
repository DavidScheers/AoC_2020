/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package AoC;

import AoC.day1.Day1;
import AoC.day2.Day2;
import AoC.day4.Day4;
import io.reactivex.rxjava3.core.Flowable;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.BaseStream;

public class App {
    public String getGreeting() {
        return "Hello world.";
    }

    public static void main(String[] args) {
        runDay1();
        runDay2();
        runDay4();
    }

    private static void runDay1() {
        final var allInts = readInput(Path.of("src/main/resources/input.txt"))
                .map(Integer::parseInt);

        final var day1 = new Day1();
        final var firstResult = day1.run1(allInts).subscribe(System.out::println);
        final var secondResult = day1.run2(allInts).subscribe(System.out::println);
    }

    private static void runDay2() {
        final var allInputs = readInput(Path.of("src/main/resources/input2.txt"));
        final var day2 = new Day2();
        final var firstResult = day2.run1(allInputs).subscribe(System.out::println);
        final var secondResult = day2.run2(allInputs).subscribe(System.out::println);
    }

    private static void runDay4() {
        final var allLines = readInput(Path.of("src/main/resources/input4.txt"));
        final var day4 = new Day4();
        final var firstResult = day4.run1(allLines).subscribe(System.out::println);
    }

    private static Flowable<String> readInput(Path path) {
        return Flowable.using(
                () -> Files.lines(path),
                Flowable::fromStream,
                BaseStream::close);
    }


}
