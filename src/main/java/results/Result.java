package results;

public class Result {
    private Double x;
    private String value;

    public Result(Double x, String value) {
        this.x = x;
        this.value = value;
    }


    @Override
    public String toString() {
        return "Result{" +
                "x=" + x +
                ", value='" + value + '\'' +
                '}';
    }
}
