package vinnik.networkgen;

import org.apache.commons.net.util.SubnetUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class SubnetGeneratorTest {
    private final SubnetGenerator generator = new SubnetGenerator();

    @Test
    public void generateSubnets1() {
        int number = 10;
        Set<SubnetUtils.SubnetInfo> subnets = generator.generateSubnets(number);
        assertTrue(isSet(subnets));
        assertEquals(number, subnets.size());

    }

    @Test
    public void generateSubnets2() {
        int number = -20;
        int actualNumber = 0;
        Set<SubnetUtils.SubnetInfo> subnets = generator.generateSubnets(number);
        assertTrue(isSet(subnets));
        assertEquals(actualNumber, subnets.size());
    }

    @Test
    public void generateSubnets3() {
        int number = 100;
        int actualNumber = 100;
        Set<SubnetUtils.SubnetInfo> subnets = generator.generateSubnets(number);
        assertTrue(isSet(subnets));
        assertEquals(actualNumber, subnets.size());
    }

    private boolean isSet(Set<SubnetUtils.SubnetInfo> subnets) {
        return subnets
                .stream()
                .allMatch(t -> (subnets
                        .stream()
                        .filter(s -> s.getCidrSignature().equals(t.getCidrSignature()))
                        .collect(Collectors.toList()).size()) == 1);
    }

}