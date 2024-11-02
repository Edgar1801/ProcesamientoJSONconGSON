import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

class CarSale {
    String car;
    String price;
}

public class CarSalesReportGson {
    public static <Gson> void main(String[] args) throws JsonIOException, IOException {
        Gson gson;
        Type listType = new TypeToken<List<Carsale>>() {}.getClass();
        List<Carsale> carSales;
        carSales = gson.fromJson(new FileReader("car_sales.json"), listType);
        Map<String, List<Double>> pricesByBrand = new HashMap<>();
        for (Carsale sale : carSales) {
            String carBrand = sale.car;
            String priceText = sale.price.replace("$", "").replace(",", "");
            double price = Double.parseDouble(priceText);
            pricesByBrand.computeIfAbsent(carBrand, k -> new ArrayList<>()).add(price);
        }
        Map<String, Double> averagePrices = pricesByBrand.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().stream().mapToDouble(Double::doubleValue).average().orElse(0.0)
                ));
        System.out.println("Reporte de Precio Promedio por Marca:");
        averagePrices.forEach((brand, avgPrice) ->
                System.out.printf("%s: $%.2f%n", brand, avgPrice));

    }
    private static class TypeToken<T> {
    }
    private static class JsonIOException extends Exception {
    }
}