import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

class Probe {

    private int[] values = new int[100];
    final int MAX_TRY = 50;
    private int totalWinsAlg = 0;
    private int totalWinsRand = 0;

    Probe() {
        generateValues();

        long start = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            shuffleArray(values);
            runGameByAlgorithm();
            runGameByRandomSelection();
        }
        long end = System.nanoTime();
        long totalTime = (end - start) / 1000000;

        System.out.println("Total wins by algorithm: " + totalWinsAlg);
        System.out.println("Total wins by random selection: " + totalWinsRand);
        System.out.println("Total time: " + totalTime + " milliseconds");

    }

    private void generateValues() {
        for (int i = 0; i < 100; i++) {
            values[i] = i;
        }
    }

    private void shuffleArray(int[] ar) {
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);

            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

    private boolean attemptFindAlg(int c) {
        int currentPosition = c;
        for (int i = 0; i < MAX_TRY; i++) {
            if (values[currentPosition] == c) {
                return true;
            }
            currentPosition = values[currentPosition];
        }
        return false;
    }

    private boolean attemptFindRand(int c) {
        int currentPosition = c;
        Random rand = new Random();
        for (int i = 0; i < MAX_TRY; i++) {
            if (values[currentPosition] == c) {
                return true;
            }
            currentPosition = rand.nextInt(100);
        }
        return false;
    }

    private void runGameByAlgorithm() {
        int successes = 0;

        for (int i = 0; i < 100; i++) {
            if (attemptFindAlg(i)) {
                successes++;
            }
        }

        if (successes == 100) {
            totalWinsAlg++;
        }

    }

    private void runGameByRandomSelection() {
        int successes = 0;

        for (int i = 0; i < 100; i++) {
            if (attemptFindRand(i)) {
                successes++;
            }
        }

        if (successes == 100) {
            totalWinsRand++;
        }
    }

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
}