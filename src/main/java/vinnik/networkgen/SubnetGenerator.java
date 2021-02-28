package vinnik.networkgen;

import org.apache.commons.net.util.SubnetUtils;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class SubnetGenerator {
    public List<SubnetUtils.SubnetInfo> generateSubnets(int numberOfSubnets) {
        LinkedList<SubnetUtils.SubnetInfo> subnets = new LinkedList<>();
        for (int i = 0; i < numberOfSubnets; i++) {
            System.out.println(i + " = " + generateSubnet());
            subnets.add(new SubnetUtils(generateSubnet()).getInfo());
        }
        return subnets;
    }


    private String generateSubnet() {
        Random r = new Random();
        int size = 32;
        int mask = 1 + r.nextInt(size);
        int numberOfBitsInOctet = 8;
        LinkedList<Integer> bits = new LinkedList<>();
        for (int i = 1; i <= mask; i++) {
            bits.add(r.nextInt(2));
        }
        for (int i = mask + 1; i < size; i++) {
            bits.add(0);
        }
        final AtomicInteger counter = new AtomicInteger(0);
        Collection<List<Integer>> octetsDigits = bits
                .stream()
                .collect(Collectors.groupingBy(it -> counter.getAndIncrement() / numberOfBitsInOctet))
                .values();
        List<String> octets = octetsDigits.stream().map(t -> t.stream()
                .map(Object::toString)
                .collect(Collectors.joining()))
                .map(s -> Integer.parseInt(s, 2))
                .map(Object::toString)
                .collect(Collectors.toList());
        String subnet = octets.stream().collect(Collectors.joining(".","",""));
        return subnet + "/" + mask;
    }
}
