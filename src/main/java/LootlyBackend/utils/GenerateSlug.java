package LootlyBackend.utils;

public class GenerateSlug {
	public static String generateSlug(String input) {
	    return input.toLowerCase()
	                .replaceAll("[^a-z0-9\\s]", "") // Remove special characters
	                .replaceAll("\\s+", "-");      // Replace spaces with hyphens
	}

}
