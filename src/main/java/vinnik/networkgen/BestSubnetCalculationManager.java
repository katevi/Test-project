package vinnik.networkgen;

import org.apache.commons.net.util.SubnetUtils;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

public class BestSubnetCalculationManager {
    public void manage(String inputPath) throws FileNotFoundException {
        FileManager fileManager = new FileManager(inputPath);
        String ip = fileManager.getIpAddress();
        int numberOfSubnets = fileManager.getNumberOfNetworks();

        SubnetGenerator generator = new SubnetGenerator();
        List<SubnetUtils.SubnetInfo> subnets = generator.generateSubnets(numberOfSubnets);

        SubnetRefiner refiner = new SubnetRefiner();
        String bestSubnet = refiner.refineSubnet(ip, subnets);

        fileManager.writeOutputFile(bestSubnet);
        fileManager.writeGenFile(subnets.stream().map(t -> t.getCidrSignature()).collect(Collectors.toList()));
    }
}
