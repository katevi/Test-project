package vinnik.networkgen;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FileManager {
    private Path inputPath;
    private final static int NUMBER_OF_ARGS = 2;
    private final static int INDEX_OF_NUMBER_OF_NETWORKS_ARG = 0;
    private final static int INDEX_OF_IP_ARG = 1;

    public FileManager(String inputPath) throws FileNotFoundException {
        Path path = Paths.get(inputPath);
        if (Files.exists(path)) {
            this.inputPath = path;
        } else {
            throw new FileNotFoundException();
        }
    }

    public int getNumberOfNetworks() throws IOException, IllegalArgumentException {
        List<String> lines = Files.lines(inputPath).collect(Collectors.toList());
        checkNumberOfArgs(lines);
        String stringNumber = lines.get(INDEX_OF_NUMBER_OF_NETWORKS_ARG);
        try {
            return Integer.parseInt(stringNumber);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid format number of networks parameter.");
        }
    }

    public String getIpAddress() throws IOException {
        List<String> lines = Files.lines(inputPath).collect(Collectors.toList());
        checkNumberOfArgs(lines);
        return lines.get(INDEX_OF_IP_ARG);
    }

    private void checkNumberOfArgs(List<String> lines) {
        if (lines.size() != NUMBER_OF_ARGS) {
            StringBuilder msg = new StringBuilder("Wrong number of arguments was found in file, expected 2, found ");
            msg.append(lines.size());
            msg.append(".");
            throw new IllegalArgumentException(msg.toString());
        }
    }

    public void writeOutputFile(String bestSubnet) {

    }

    public void writeGenFile(List<String> subnets) {

    }
}
