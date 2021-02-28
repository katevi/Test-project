package vinnik.networkgen;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Please, enter correct number of arguments.");
        } else {
            BestSubnetCalculationManager manager = new BestSubnetCalculationManager();
            manager.manageProcessOfCalculatingBestSubnetForGivenIp(args[0]);
        }
    }
}
