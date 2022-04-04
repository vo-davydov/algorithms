package general.sort.domain;

public enum LaptopBrand {
    LENUVO("Lenuvo"),
    ASOSU("Asos"),
    MAC_NOTE("MacNote"),
    ESER("Eser"),
    XAMIOU("Xamiou");

    private String name;

    LaptopBrand(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
