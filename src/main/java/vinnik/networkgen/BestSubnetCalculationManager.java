package vinnik.networkgen;

import org.apache.commons.net.util.SubnetUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BestSubnetCalculationManager {
    public void manageProcessOfCalculatingBestSubnetForGivenIp(String inputPath) {
        try {
            FileManager fileManager = new FileManager(inputPath);

            try {
                int numberOfSubnets = fileManager.getNumberOfNetworks();
                String ip = fileManager.getIpAddress();

                SubnetGenerator generator = new SubnetGenerator();
                Set<SubnetUtils.SubnetInfo> subnets = generator.generateSubnets(numberOfSubnets);

                SubnetRefiner refiner = new SubnetRefiner();
                SubnetUtils.SubnetInfo bestSubnet = refiner.refineSubnet(ip, subnets);
                if (bestSubnet != null) {
                    System.out.println("best subnet is: " + bestSubnet.getCidrSignature());
                    fileManager.writeOutputFile(bestSubnet.getCidrSignature());
                    fileManager.writeGenFile(subnets
                            .stream()
                            .map(SubnetUtils.SubnetInfo::getCidrSignature)
                            .collect(Collectors.toList()));
                }
            } catch (IOException e) {
                System.out.println("Error occurred during working with file.");
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File was not found in specified folder.");
        } catch (InvalidPathException e) {
            System.out.println("Invalid file path was specified.");
        }
    }
}
