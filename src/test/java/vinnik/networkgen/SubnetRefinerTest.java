package vinnik.networkgen;

import org.apache.commons.net.util.SubnetUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class SubnetRefinerTest {
    private final SubnetRefiner refiner = new SubnetRefiner();

    @Test
    public void refineSubnet1() {
        String ip = "231.230.2.4";
        Set<SubnetUtils.SubnetInfo> subnets = Arrays.asList("64.0.0.0/4",
                "74.50.128.0/17",
                "18.16.0.0/12",
                "128.0.0.0/1",
                "192.0.0.0/2",
                "84.73.195.56/28",
                "136.0.0.0/5",
                "128.0.0.0/3",
                "128.0.0.0/1").stream()
                .map(t -> new SubnetUtils(t).getInfo())
                .collect(Collectors.toSet());
        String bestSubnet = "192.0.0.0/2";
        String actualBestSubnet = refiner.refineSubnet(ip, subnets).getCidrSignature();
        Assert.assertEquals(actualBestSubnet, bestSubnet);
    }

    @Test
    public void refineSubnet2() {
        String ip = "192.175.6.12";
        Set<SubnetUtils.SubnetInfo> subnets = Arrays.asList("64.0.0.0/4",
                "242.48.0.0/14",
                "119.37.228.40/28",
                "192.0.0.0/2",
                "84.73.195.56/28",
                "136.0.0.0/5",
                "192.140.0.0/5",
                "192.140.0.0/4").stream()
                .map(t -> new SubnetUtils(t).getInfo())
                .collect(Collectors.toSet());
        String bestSubnet = "192.140.0.0/5";
        String actualBestSubnet = refiner.refineSubnet(ip, subnets).getCidrSignature();
        Assert.assertEquals(actualBestSubnet, bestSubnet);
    }

    @Test
    public void refineSubnet3() {
        String ip = "1.2.3.4";
        Set<SubnetUtils.SubnetInfo> subnets = Arrays.asList("1.2.2.0/23",
                "1.2.3.0/14",
                "1.2.3.0/24",
                "192.0.0.0/2",
                "84.73.195.56/28",
                "193.154.157.64/27",
                "224.174.126.96/26").stream()
                .map(t -> new SubnetUtils(t).getInfo())
                .collect(Collectors.toSet());
        String bestSubnet = "1.2.3.0/24";
        String actualBestSubnet = refiner.refineSubnet(ip, subnets).getCidrSignature();
        Assert.assertEquals(actualBestSubnet, bestSubnet);
    }

    @Test
    public void refineSubnet4() {
        String ip = "34.2.3.4";
        Set<SubnetUtils.SubnetInfo> subnets = new HashSet<>();
        SubnetUtils.SubnetInfo actualBestSubnet = refiner.refineSubnet(ip, subnets);
        Assert.assertEquals(null, actualBestSubnet);
    }

    @Test
    public void refineSubnet5() {
        String ip = "224.185.33.28";
        Set<SubnetUtils.SubnetInfo> subnets = Stream.of(
                "224.184.0.0/15",
                "224.185.37.0/19",
                "84.73.195.56/28",
                "224.185.37.0/21",
                "193.50.250.64/27",
                "224.27.126.96/13")
                .map(t -> new SubnetUtils(t).getInfo())
                .collect(Collectors.toSet());
        String bestSubnet = "224.185.37.0/21";
        SubnetUtils.SubnetInfo actualBestSubnet = refiner.refineSubnet(ip, subnets);
        Assert.assertEquals(bestSubnet, actualBestSubnet.getCidrSignature());
    }

}
