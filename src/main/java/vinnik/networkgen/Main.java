package vinnik.networkgen;

public class Main {
    public static void main(String[] args) {
        BestSubnetCalculationManager manager = new BestSubnetCalculationManager();
        manager.manageProcessOfCalculatingBestSubnetForGivenIp(args[0]);
    }
}
