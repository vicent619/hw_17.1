package app;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
                new Product("Laptop", "Electronics", 1200.0),
                new Product("Coffee Maker", "Appliances", 80.0),
                new Product("Headphones", "Electronics", 150.0),
                new Product("Blender", "Appliances", 50.0)
        );

        Map<String, List<Product>> productsByCategory = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory));

        System.out.println("\nГрупування товарів:");
        productsByCategory.forEach((category, list) ->
                System.out.println(category + ": " + list)
        );

        Map<String, Double> avgPriceByCategory = productsByCategory.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().stream()
                                .mapToDouble(Product::getPrice)
                                .average()
                                .orElse(0)
                ));

        System.out.println("\nСередня ціна за категоріями: ");
        avgPriceByCategory.forEach((category, avgPrice) ->
                System.out.println(category + ": " + avgPrice)
        );

        avgPriceByCategory.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .ifPresent(theMostExpensiveCategory ->
                        System.out.printf("\nКатегорія з найвищою середньою ціною: %s (%.1f)%n",
                                theMostExpensiveCategory.getKey(),
                                theMostExpensiveCategory.getValue())
                );

    }
}
