package vinnik.networkgen;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class FileManagerTest {
    private final Path workingDir = Paths.get("src/test/resources");
    private final static String IN_1_TXT = "/in1.txt";
    private final static String IN_2_TXT = "/in2.txt";
    private final static String IN_3_TXT = "/in3.txt";
    private final static String IN_4_TXT = "/in4.txt";


    @Test(expected = FileNotFoundException.class)
    public void fileManager1() throws FileNotFoundException {
        FileManager manager = new FileManager("/abc/cde/in.txt");
    }

    @Test(expected = FileNotFoundException.class)
    public void fileManager2() throws FileNotFoundException {
        FileManager manager = new FileManager("abc.txt");
    }

    @Test(expected = InvalidPathException.class)
    public void fileManager3() throws FileNotFoundException {
        FileManager manager = new FileManager("//\\//\\//\\");
    }

    @Test
    public void getIpAddress1() throws IOException {
        FileManager manager = new FileManager(workingDir.toAbsolutePath() + IN_1_TXT);
        String ip = manager.getIpAddress();
        String expectedIp = "234.251.196.141";
        assertEquals(expectedIp, ip);
    }

    @Test
    public void getNumberOfNetworks1() throws IOException {
        FileManager manager = new FileManager(workingDir.toAbsolutePath() + IN_1_TXT);
        int number = manager.getNumberOfNetworks();
        int expectedNumber = 12;
        assertEquals(number, expectedNumber);
    }

    @Test
    public void getIpAddress2() throws IOException {
        FileManager manager = new FileManager(workingDir.toAbsolutePath() + IN_2_TXT);
        String ip = manager.getIpAddress();
        String expectedIp = "153.211.14.157";
        assertEquals(ip, expectedIp);
    }

    @Test
    public void getNumberOfNetworks2() throws IOException {
        FileManager manager = new FileManager(workingDir.toAbsolutePath() + IN_2_TXT);
        int number = manager.getNumberOfNetworks();
        int expectedNumber = 25;
        assertEquals(number, expectedNumber);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getNumberOfNetworks3() throws IOException {
        FileManager manager = new FileManager(workingDir.toAbsolutePath() + IN_3_TXT);
        int number = manager.getNumberOfNetworks();
    }

    @Test(expected = IllegalArgumentException.class)
    public void getNumberOfNetworks4() throws IOException {
        FileManager manager = new FileManager(workingDir.toAbsolutePath() + IN_4_TXT);
        manager.getIpAddress();
    }
}