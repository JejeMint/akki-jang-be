package jejemint.akkijang.domain;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Product {

    @Id
    @GeneratedValue
    @Column(name = "product_id")
    private Long id;

    private String title;
    private String content;
    private int price;
    private String imageUrl;

    @Convert(converter = CategoryPersistConverter.class)
    @Column(name = "category_code")
    private Category category;
    @Convert(converter = RegionPersistConverter.class)
    @Column(name = "region_code")
    private Region region;

    private Product(final Long id, final String title, final String content, final int price, final String imageUrl,
                   final Category category, final Region region) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.price = price;
        this.imageUrl = imageUrl;
        this.category = category;
        this.region = region;
    }

    public static Product create(String title,
                                 String content,
                                 int price,
                                 String imageUrl,
                                 Category category,
                                 Region region) {
        return new Product(null, title, content, price, imageUrl, category, region);
    }

}
