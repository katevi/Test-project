package vinnik.networkgen;

import org.apache.commons.net.util.SubnetUtils;

import java.util.Comparator;
import java.util.Set;


public class SubnetRefiner {

    public SubnetUtils.SubnetInfo refineSubnet(String ipAddress,
                                               Set<SubnetUtils.SubnetInfo> subnets) {
        SubnetUtils.SubnetInfo posSub = subnets
                .stream()
                .filter(t -> t.isInRange(ipAddress))
                .max(Comparator.comparing(SubnetUtils.SubnetInfo::getNetmask))
                .orElse(null);
        return posSub;
    }
}
