import java.util.*;

// Link to prompt:

/**
 * @author William Wittig
 * @version 1.0
 * <a href="https://www.hackerrank.com/contests/projecteuler/challenges/euler254/problem?isFullScreen=true">...</a>
 * This class was developed to solve a HackerRank problem from the link above
 */
public class Main {
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        // Start timer

        // To calculate how much memory is being used
        Runtime run = Runtime.getRuntime();
        double sumOfMemoryUsage = 0;
        double sumOfExecutionTimes = 0;

        // To manipulate amount of executions in one place (1,000,000) or (1)
        int runCount = 1000000;
        double beforeUsedMemory = run.totalMemory() - run.freeMemory();
        // int runCount = 1;
        for (int iteration = 1; iteration <= runCount; iteration++) {
            // Start time in nanoseconds
            long startTime = System.nanoTime();

            Scanner scan = new Scanner(System.in);
            // Number of queries
            int q = 2;
            // Getting series of sg(i)
            for (int i = 0; i < q; i++) {
                // Digit to getFactorial()ed
                int n = 3;
                // Digit to be modded with at end
                int m = 1000000;

                // Output for tests
                System.out.println(calculateSGISum(n) % m);
            }
            scan.close();

            // Final timer in nanoseconds
            long endTime = System.nanoTime();
            // Getting memory after application running
            double afterUsedMemory = run.totalMemory() - run.freeMemory();
            // Getting total time
            sumOfExecutionTimes+=(endTime - startTime);
            sumOfMemoryUsage+=(afterUsedMemory - beforeUsedMemory);
            // System.out.println("iteration: " + iteration + ", That took " + (endTime - startTime) + " nanoseconds");
        }

        // Total avg time / total times (10,000,000) converted from milliseconds to seconds diving by 1,000,000,000)
        // (HackerRank 4 second max for Java)
        System.out.println("Average time: " + ((sumOfExecutionTimes/runCount)/1000000000) + " seconds.");
        //System.out.println("Average time: " + (avgTime) + " nanoseconds.");

        // Printing memory used during running of application converted from byte to mb dividing by 1,000,000
        // (HackerRank 512mb max for Java)
        System.out.println("Memory increased: " + ((sumOfMemoryUsage)/1000000) + " mb.");
    }

    private static int calculateSGISum(int n) {
        if (n <= 1) { return 1; }
        return getSG(n) + calculateSGISum(n-1);
    }

    // Returns the sum of digits of g(i)
    private static int getSG(int i) {
        int[] intArray = toIntArray(getG(i, 1));
        // Returning sg(i)
        return getArraySum(intArray, intArray.length, 0);
    }

    // Returns the smallest positive integer n such that sf(n) = i
    private static int getG(int i, int n) {
        if (getSF(n) == i) { return n; }
        return getG(i, n+1);
    }

    // Returns sum of digits in Integer
    private static int getSF(int n) {
        // Returning f(n)
        int[] intArray = toIntArray(getFN(n));
        return getArraySum(intArray, intArray.length, 0);
    }

    // Returns sum getFactorial()s f(n)
    private static int getFN(int n) {
        // Returning f(n)
        int[] factorialArray = getFactorialArray(toIntArray(n));
        return getArraySum(factorialArray, factorialArray.length, 0);
    }

    private static int[] getFactorialArray(int[] array) {
        // getFactorial()s
        return getFactorialArray(array, array.length, 0);
    }

    private static int[] getFactorialArray(int[] array, int arrayLength, int i) {
        if (i >= arrayLength) { return array; }
        // Setting index to factorial
        array[i] = getFactorial(array[i]);
        return getFactorialArray(array, arrayLength, i+1);
    }

    // Return getFactorial() of n, f(n) = n! - Optimized
    private static int getFactorial(int n) {
        // Return recursive getFactorial()
        return getFactorial(n, 1);
    }

    // Optimized
    private static int getFactorial(int n, int i) {
        // If i is greater than n, no more numbers to multiply
        if (i > n) { return 1; }
        // Return i * rest getFactorial()
        return i * getFactorial(n, i+1);
    }

    private static int getArraySum(int[] array, int arrayLength, int index) {
        if (index >= arrayLength) { return 0; }
        return array[index] + getArraySum(array, arrayLength, index+1);
    }

    // Optimized
    private static int[] toIntArray(int num) {
        // Getting the length of the array
        int arrayLength = String.valueOf(num).length();
        // Turning into int array for calculations
        return convertCharsToInts(Integer.toString(num).toCharArray(), new int[arrayLength], 0, arrayLength);
    }

    // Optimized
    private static int[] convertCharsToInts(char[] charArray, int[] intArray, int i, int arrayLength) {
        if (i == arrayLength) { return intArray; }
        // Converting to int
        intArray[i] = Character.getNumericValue(charArray[i]);
        return convertCharsToInts(charArray, intArray, i+1, arrayLength);
    }
}
