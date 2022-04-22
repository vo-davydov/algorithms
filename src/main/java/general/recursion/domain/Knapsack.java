package general.recursion.domain;

import java.util.Arrays;

public class Knapsack {
    private final int size;
    private Item[] items;

    public Knapsack(int size) {
        this.size = size;
    }

    public Knapsack(int size, Item[] items) {
        this.size = size;
        this.items = items;
    }

    public int getSize() {
        return size;
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        validateItems();
        this.items = items;
    }

    private void validateItems() {
        if (items != null && items.length > 0) {
            if (getWeightSum() > size) {
                throw new RuntimeException("Items are too heavy! Maximum weight size is " + this.size);
            }
        }
    }

    public double getPriceSum() {
        double sum = 0.0;
        for (Item i : items) {
            sum += i.getPrice();
        }

        return sum;
    }

    public int getWeightSum() {
        int countSize = 0;
        for (Item i : items) {
            countSize += i.getWeight();
        }
        return countSize;
    }

    @Override
    public String toString() {
        return "Knapsack{" +
                "size=" + size +
                ", items=" + Arrays.toString(items) +
                '}';
    }
}
