package general.recursion;

import general.recursion.domain.Item;
import general.recursion.domain.Knapsack;

public class Recursion {
    public static double doExponentiation(double number, double exponentiation) {

        if (exponentiation == 0) {
            return 1;
        }

        if (exponentiation == 1) {
            return number * exponentiation;
        }

        if (exponentiation == -1) {
            return 1 / number * exponentiation;
        }

        if (exponentiation > 0) {
            return number * (doExponentiation(number, exponentiation - 1));
        }

        return 1 / (number * (doExponentiation(number, -1 * (exponentiation + 1))));
    }


    public static double findBestForKnapsack(final Item[] items, Knapsack knapsack) {
        final double[] priceItems = new double[items.length];
        final int[] weightItems = new int[items.length];


        for (int i = 0; i < items.length; i++) {
            priceItems[i] = items[i].getPrice();
            weightItems[i] = items[i].getWeight();
        }

        return knapsackRec(weightItems, priceItems, items.length, knapsack.getSize());
    }

    public static double knapsackRec(int[] weights, double[] prices, int numberItems, int weightMax) {
        if (numberItems <= 0) {
            return 0.0;
        } else if (weights[numberItems - 1] > weightMax) {
            return knapsackRec(weights, prices, numberItems - 1, weightMax);
        } else {
            return Math.max(knapsackRec(weights, prices, numberItems - 1, weightMax), prices[numberItems - 1]
                    + knapsackRec(weights, prices, numberItems - 1, weightMax - weights[numberItems - 1]));
        }
    }

}
