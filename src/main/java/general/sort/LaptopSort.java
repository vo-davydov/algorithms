package general.sort;

import general.sort.domain.Laptop;

import java.time.LocalDateTime;

public class LaptopSort {

    private LaptopSort() {

    }


    public static void main(String[] args) {
        Laptop[] laptops = LaptopGenerator.getRandomLaptopArray();
        LocalDateTime start = LocalDateTime.now();
        sortLaptops(laptops);
        LocalDateTime end = LocalDateTime.now();
        for (Laptop l : laptops) {
            System.out.println(l);
        }
        System.out.println(end.getSecond() - start.getSecond());
    }

    public static void sortLaptops(Laptop[] laptops) {
        int length = laptops.length;

        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if (laptops[i].getPrice().equals(laptops[j].getPrice())) {
                    if (laptops[i].getRAM() == (laptops[j].getRAM())) {
                        if (isFirstBigger(laptops[i].getName(), laptops[j].getName())) {
                            Laptop l = laptops[i];
                            laptops[i] = laptops[j];
                            laptops[j] = l;
                        }
                        continue;
                    }

                    if (laptops[i].getRAM() > laptops[j].getRAM()) {
                        Laptop l = laptops[i];
                        laptops[i] = laptops[j];
                        laptops[j] = l;
                    }

                    continue;
                }

                if (laptops[i].getPrice() > laptops[j].getPrice()) {
                    Laptop l = laptops[i];
                    laptops[i] = laptops[j];
                    laptops[j] = l;
                }

            }
        }
    }

    private static boolean isFirstBigger(String first, String second) {
        return countCharArr(first) > countCharArr(second);
    }

    private static int countCharArr(String str) {
        char[] firstChars = str.toCharArray();
        int size = 0;

        for (char c : firstChars) {
            size += c;
        }

        return size;
    }

}
