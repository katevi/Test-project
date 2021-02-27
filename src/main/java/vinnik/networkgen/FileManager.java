package vinnik.networkgen;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileManager {
    private String inputPath;

    public FileManager(String inputPath) throws FileNotFoundException {
        Path path = Paths.get(inputPath);
        if (Files.exists(path)) {
            this.inputPath = inputPath;
        } else {
            throw new FileNotFoundException("File was not found.");
        }
    }

    public int getNumberOfNetworks() {
        return 0;
    }

    public String getIpAddress() {
        return "";
    }

    public void writeOutputFile(String bestSubnet) {

    }

    public void writeGenFile(List<String> subnets) {

    }
}
