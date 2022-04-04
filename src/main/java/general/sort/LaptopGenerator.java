package general.sort;

import general.sort.domain.Laptop;
import general.sort.domain.LaptopBrand;
import general.sort.domain.RAM;

import java.text.DecimalFormat;
import java.util.Random;

public class LaptopGenerator {

    private static Random random = new Random();
    private static Double maxPrice = 2000.00;
    private static Double minPrice = 500.00;
    private static int size = 50_000;
    private static int gap = 50;

    private LaptopGenerator() {

    }

    public static Laptop[] getRandomLaptopArray() {
        Laptop[] laptops = new Laptop[size];

        for (int i = 0; i < size; i++) {
            laptops[i] = new Laptop(getRandomPrice(), getRandomLaptopBrand(), getRandomRAM());
        }
        return laptops;
    }

    private static String getRandomLaptopBrand() {
        int randomIndex = random.nextInt(LaptopBrand.values().length);
        return LaptopBrand.values()[randomIndex].getName();
    }

    private static Double getRandomPrice() {
        DecimalFormat df = new DecimalFormat("#.##");
        Double[] prices = getPrices();
        int randomIndex = random.nextInt(prices.length);
        return Double.parseDouble(df.format(prices[randomIndex]));
    }

    private static int getRandomRAM() {
        int randomIndex = random.nextInt(RAM.values().length);
        return RAM.values()[randomIndex].getRam();
    }

    private static Double[] getPrices() {
        Double[] prices = new Double[30];
        double price = 500.0;
        for (int i = 0; i < prices.length; i++) {
            prices[i] = price;
            price += 50;
        }
        return prices;
    }

}
