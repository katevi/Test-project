package vinnik.networkgen;

import org.apache.commons.net.util.SubnetUtils;

import java.util.Comparator;
import java.util.List;

public class SubnetRefiner {

    public String refineSubnet(String ipAddress,
                               List<SubnetUtils.SubnetInfo> subnets) {
        SubnetUtils.SubnetInfo bestSubnet = subnets
                .stream()
                .filter(t -> t.isInRange(ipAddress))
                .max(Comparator.comparing(t -> Integer.parseInt(t.getNetmask())))
                .orElse(null);

        if (bestSubnet == null) {

        } else {

        }
        return "";
    }
}
