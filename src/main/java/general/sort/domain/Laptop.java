package general.sort.domain;

public class Laptop {
    private Double price;
    private String name;
    private int RAM;

    public Laptop(Double price, String name, int RAM) {
        this.price = price;
        this.name = name;
        this.RAM = RAM;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRAM() {
        return RAM;
    }

    public void setRAM(int RAM) {
        this.RAM = RAM;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "price=" + price +
                ", name='" + name + '\'' +
                ", RAM=" + RAM +
                '}';
    }
}
