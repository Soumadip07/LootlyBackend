package LootlyBackend.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import LootlyBackend.entities.Category;
import LootlyBackend.entities.Review;
import LootlyBackend.entities.User;
import LootlyBackend.utils.DiscountType;
import LootlyBackend.utils.Quantity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductDto {

    private Integer productId;

    private String title;

    private String content;

    private String productSlug;

    private float base_price;

    private Quantity quantity;

    private Integer stock;

    private float discount;

    private DiscountType discount_type;

    private String imageName;

    private Date addedDate;

    private CategoryDto category;

    private UserDto user;

    private Set<ReviewDto> reviews = new HashSet<>();
}
