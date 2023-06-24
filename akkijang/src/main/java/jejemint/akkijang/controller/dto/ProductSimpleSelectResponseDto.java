package jejemint.akkijang.controller.dto;

import java.time.LocalDateTime;
import jejemint.akkijang.domain.Product;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductSimpleSelectResponseDto {
    private final Long id;
    private final String title;
    private final int price;
    private final String imageUrl;
    private final String regionCode;
    private final LocalDateTime createdAt;

    public static ProductSimpleSelectResponseDto from(Product product) {
        return new ProductSimpleSelectResponseDto(
                product.getId(),
                product.getTitle(),
                product.getPrice(),
                product.getImageUrl(),
                product.getRegion().getCode(),
                product.getCreatedAt());
    }
}
