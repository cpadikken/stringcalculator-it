package nl.oose.stringcalculator.webservice;

public class AddResult {
    private final String numbers;
    private final int sum;

    public AddResult(String numbers, int sum) {
        this.numbers = numbers;
        this.sum = sum;
    }

    public String getNumbers() {
        return numbers;
    }

    public int getSum() {
        return sum;
    }
}
