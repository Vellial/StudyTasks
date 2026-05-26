import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

public class DataAggregator {
    public ProductInfo aggregateProductInfo(String productName) throws RuntimeException {
        CompletableFuture<Double> priceFuture = CompletableFuture.supplyAsync(() -> {
            Double price;
            try {
                price = fetchPrice();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return price;
        }).exceptionally(ex -> {
            System.out.println("Ошибка получения цены: " + ex.getMessage());
            return 0.0;
        });

        CompletableFuture<String> descriptionFuture = CompletableFuture.supplyAsync(() -> {
            String descr;
            try {
                descr = fetchDescription();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            return descr;
        }).exceptionally(ex -> {
            System.out.println("Ошибка получения описания: " + ex.getMessage());
            return "Нет данных";
        });

        CompletableFuture<Double> ratingFuture = CompletableFuture.supplyAsync(() -> {
            Double rating;
            try {
                rating = fetchRating();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return rating;
        }).exceptionally(ex -> {
            System.out.println("Ошибка получения рейтинга: " + ex.getMessage());
            return 0.0;
        });

        CompletableFuture.allOf(priceFuture, descriptionFuture, ratingFuture).join();

        Double price = priceFuture.join();
        String description = descriptionFuture.join();
        Double rating = ratingFuture.join();

        return new ProductInfo(productName, price, description, rating);
    }

    Double fetchPrice() throws InterruptedException {
        Thread.sleep(1000);
        if (ThreadLocalRandom.current().nextDouble() < 0.2) {
            throw new RuntimeException();
        }
        return 2.0;
    }

    String fetchDescription() throws InterruptedException {
        Thread.sleep(2000);
        if (ThreadLocalRandom.current().nextDouble() < 0.2) {
            throw new RuntimeException();
        }
        return "Description ";
    }

    Double fetchRating() throws InterruptedException {
        Thread.sleep(3000);
        if (ThreadLocalRandom.current().nextDouble() < 0.2) {
            throw new RuntimeException();
        }
        return 1.0;
    }

}
