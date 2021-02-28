package vinnik.networkgen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
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
    private final static String OUT_PATH_NAME = "out.txt";
    private final static String GEN_PATH_NAME = "autogen.txt";

    public FileManager(String inputPath) throws FileNotFoundException {
        Path path = Paths.get(inputPath);
        if (Files.exists(path)) {
            this.inputPath = path;
        } else {
            throw new FileNotFoundException();
        }
    }

    public int getNumberOfNetworks() throws IllegalArgumentException, IOException {
        List<String> lines = null;
        try {
            lines = Files.lines(inputPath).collect(Collectors.toList());
        } catch (IOException e) {
            throw new IOException("Something went wrong during reading input file.");
        }
        checkNumberOfArgs(lines);
        String stringNumber = lines.get(INDEX_OF_NUMBER_OF_NETWORKS_ARG);
        try {
            return Integer.parseInt(stringNumber);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid format number of networks parameter.");
        }
    }

    public String getIpAddress() throws IOException {
        List<String> lines = null;
        try {
            lines = Files.lines(inputPath).collect(Collectors.toList());
        } catch (IOException e) {
            throw new IOException("Something went wrong during reading input file.");
        }
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

    public void writeOutputFile(String bestSubnet) throws IOException {
        String path = inputPath.toFile().getAbsoluteFile().getParentFile().getPath() + "//" + OUT_PATH_NAME;
        File file = new File(path);
        try {
            // no difference if file exists, or not, since it is still will be rewritten
            file.createNewFile();
            FileWriter writer = new FileWriter(file.getAbsolutePath());
            writer.write(bestSubnet);
            writer.close();
        } catch (IOException e) {
           throw new IOException("Something went wrong during printing results to output file.");
        }
    }

    public void writeGenFile(List<String> subnets) throws IOException {
        String path = inputPath.toFile().getAbsoluteFile().getParentFile().getPath() + "//" + GEN_PATH_NAME;
        File file = new File(path);
        try {
            // no difference if file exists, or not, since it is still will be rewritten
            file.createNewFile();
            FileWriter writer = new FileWriter(file.getAbsolutePath());
            for (String subnet : subnets) {
                writer.write(subnet);
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new IOException("Something went wrong during printing results to autogen file.");
        }
    }
}
