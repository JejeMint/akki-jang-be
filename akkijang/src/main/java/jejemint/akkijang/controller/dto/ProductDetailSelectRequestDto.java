package jejemint.akkijang.controller.dto;

import java.time.LocalDateTime;
import jejemint.akkijang.domain.Product;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductDetailSelectRequestDto {
    private final Long id;
    private final String title;
    private final String content;
    private final int price;
    private final String imageUrl;
    private final String categoryCode;
    private final String regionCode;
    private final LocalDateTime createdAt;

    public static ProductDetailSelectRequestDto from(Product product) {
        return new ProductDetailSelectRequestDto(
                product.getId(),
                product.getTitle(),
                product.getContent(),
                product.getPrice(),
                product.getImageUrl(),
                product.getCategory().getCode(),
                product.getRegion().getCode(),
                product.getCreatedAt());
    }
}
