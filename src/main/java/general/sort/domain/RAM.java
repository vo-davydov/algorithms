package general.sort.domain;

public enum RAM {
    TWO(2),
    FOUR(4),
    EIGHT(8),
    SIXTEEN(16),
    TWENTY_FOUR(24);

    private int ram;

    public int getRam() {
        return this.ram;
    }

    RAM(int ram) {
        this.ram = ram;
    }
}
