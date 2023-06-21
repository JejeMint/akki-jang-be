package jejemint.akkijang.controller.dto;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@RequiredArgsConstructor
public class ProductCreateRequestDto {

    @NotNull(message = "제목이 입력되지 않았습니다.")
    private final String title;
    @NotNull(message = "내용이 입력되지 않았습니다.")
    private final String content;
    @NotNull(message = "가격이 입력되지 않았습니다.")
    private final int price;
    @NotNull(message = "이미지가 입력되지 않았습니다.")
    private final MultipartFile image;
    @NotNull(message = "카테고리가 입력되지 않았습니다.")
    private final String categoryCode;
    @NotNull(message = "지역이 입력되지 않았습니다.")
    private final String regionCode;

    @Override
    public String toString() {
        return "ProductCreateRequestDto{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", price=" + price +
                ", image=" + image.getOriginalFilename() +
                ", categoryCode='" + categoryCode + '\'' +
                ", regionCode='" + regionCode + '\'' +
                '}';
    }
}
