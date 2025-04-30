
class PetrolPump {
    int petrol;
    int distance;

    PetrolPump(int petrol, int distance) {
        this.petrol = petrol;
        this.distance = distance;
    }
}

public class CircularTour {
    public static int findStartingPump(PetrolPump[] pumps) {
        int start = 0;
        int totalSurplus = 0;
        int currentSurplus = 0;

        for (int i = 0; i < pumps.length; i++) {
            int gain = pumps[i].petrol - pumps[i].distance;
            totalSurplus += gain;
            currentSurplus += gain;

            // If current surplus is negative, start from next pump
            if (currentSurplus < 0) {
                start = i + 1;
                currentSurplus = 0;
            }
        }

        return totalSurplus >= 0 ? start : -1; // -1 if no solution
    }

    public static void main(String[] args) {
        PetrolPump[] pumps = {
            new PetrolPump(6, 4),
            new PetrolPump(3, 6),
            new PetrolPump(7, 3)
        };

        int start = findStartingPump(pumps);
        if (start != -1)
            System.out.println("Start at pump: " + start);
        else
            System.out.println("No feasible starting pump.");
    }
}

