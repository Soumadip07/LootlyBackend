package LootlyBackend.exceptions;

public class ProductNotFoundException extends RuntimeException {
    
    public ProductNotFoundException(String productSlug) {
        super("Product not found with slug: " + productSlug);
    }
}
