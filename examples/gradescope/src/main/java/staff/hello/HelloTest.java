package staff.hello;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import com.github.tkutcher.jgrade.gradedtest.GradedTest;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import student.hello.Greeting;

import static org.junit.Assert.assertEquals;

public class HelloTest {

    // I use this so I can run locally and verify output
    static final boolean DEBUG = false;

    // I like to add this no matter what so if the submission loops,
    // Gradescope doesn't get stuck.
    @Rule
    public Timeout globalTimeout = Timeout.seconds(10);


    private static final String GREETING = "Hello";
    private Greeting unit;

    // Running with the DEBUG flag enabled tests the staff solution.
    @Before
    public void initUnit() {
        this.unit = DEBUG ? new Hello(GREETING) : new student.hello.Hello(GREETING);
    }

    @Test
    @GradedTest(name = "greet() works")
    public void defaultGreeting() {
        assertEquals(GREETING, unit.greet());
    }

    @Test
    @GradedTest(name = "greet(String who) works", points = 2.0)
    public void greetSomebody() {
        assertEquals(GREETING + ", World!", unit.greet("World"));
    }

    @Test
    @GradedTest(name = "prints greeting", points = 2.0)
    public void printGreeting() {
        PrintStream realStdout = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        unit.printGreeting();
        assertEquals(GREETING, baos.toString().trim());
        System.setOut(realStdout);
    }
}
