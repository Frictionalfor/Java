public class Generics {
    public static void main(String[] args) {
        // Generic class
        Box<Integer> intBox = new Box<>(10);
        Box<String> strBox = new Box<>("Hello");

        System.out.println("Integer Box: " + intBox.getValue());
        System.out.println("String Box: " + strBox.getValue());

        // Bounded type parameter
        NumberBox<Double> doubleBox = new NumberBox<>(3.14);
        System.out.println("Number Box: " + doubleBox.getValue());
    }
}

class Box<T> {
    private T value;

    public Box(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}

class NumberBox<T extends Number> {
    private T value;

    public NumberBox(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public double square() {
        return value.doubleValue() * value.doubleValue();
    }
}
