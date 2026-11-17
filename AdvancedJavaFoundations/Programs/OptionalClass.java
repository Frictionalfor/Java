import java.util.Optional;

public class OptionalClass {
    public static void main(String[] args) {
        // Empty Optional
        Optional<String> emptyOpt = Optional.empty();
        System.out.println("emptyOpt.isPresent(): " + emptyOpt.isPresent());
        System.out.println("emptyOpt.isEmpty(): " + emptyOpt.isEmpty());
        System.out.println("emptyOpt.orElse(\"Empty\"): " + emptyOpt.orElse("Empty"));

        // Optional with value
        Optional<String> nameOpt = Optional.of("Alice");

        // Optional with nullable value
        Optional<String> nullableOpt = Optional.ofNullable(null);

        // Check presence
        if (nameOpt.isPresent()) {
            System.out.println("Name: " + nameOpt.get());
        }

        // orElse
        String value = nullableOpt.orElse("Default Name");
        System.out.println("Value: " + value);

        // ifPresent
        nameOpt.ifPresent(name -> System.out.println("Hello, " + name));
    }
}
