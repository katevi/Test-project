package vinnik.networkgen;

import org.apache.commons.net.util.Charsets;
import org.apache.commons.net.util.SubnetUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Set;


public class SubnetRefiner {

    public SubnetUtils.SubnetInfo refineSubnet(String ipAddress,
                                               Set<SubnetUtils.SubnetInfo> subnets) {
        /*SubnetUtils.SubnetInfo one = (new SubnetUtils("192.0.0.0/6")).getInfo();
        SubnetUtils.SubnetInfo two = (new SubnetUtils("192.0.0.0/4")).getInfo();
        System.out.println(one.getNetmask() + " " + two.getNetmask());
        System.out.println(one.getNetmask().compareTo(two.getNetmask()));
        return null;*/

        /*SubnetUtils.SubnetInfo bestSubnet = subnets
                .stream()
                .filter(t -> t.isInRange(ipAddress))
                .max(Comparator.comparing(SubnetUtils.SubnetInfo::getNetmask))
                .orElse(null);
        return bestSubnet;*/
        subnets
                .stream()
                .filter(t -> t.isInRange(ipAddress))
                .forEach(t -> System.out.println(t.getCidrSignature() + " " + t.getNetmask()));
        SubnetUtils.SubnetInfo posSub = subnets
                .stream()
                .filter(t -> t.isInRange(ipAddress))
                .max(Comparator.comparing(SubnetUtils.SubnetInfo::getNetmask))
                .orElse(null);
        return posSub;
    }
}
