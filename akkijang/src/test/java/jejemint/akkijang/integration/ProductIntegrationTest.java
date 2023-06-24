package jejemint.akkijang.integration;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import jejemint.akkijang.controller.dto.ProductDetailSelectRequestDto;
import jejemint.akkijang.controller.dto.ProductSimpleSelectResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ProductIntegrationTest {

    @LocalServerPort
    private int port;

    private File mockFile;

    @BeforeEach
    void setUp() {
        RestAssured.port = this.port;
        String filePath = "src/test/resources/static/image/test.png";
        mockFile = new File(filePath);
    }

    @Test
    void 상품을_등록한다() {
        // when
        final ExtractableResponse<Response> response = 상품_생성_요청(mockFile, "테스트 제목", "테스트 내용", 10_000, "1", "1");

        // then
        assertThat(response.header("Location")).contains("/api/products/");
    }

    @Test
    void 상품을_전체_조회한다() {
        // given
        상품_생성_요청(mockFile, "테스트 제목1", "테스트 내용1", 10_000, "1", "1");
        상품_생성_요청(mockFile, "테스트 제목2", "테스트 내용2", 20_000, "2", "2");

        // when
        final ExtractableResponse<Response> response = 상품_전체_조회_요청();
        final List<ProductSimpleSelectResponseDto> selectResponse = Arrays.asList(
                response.as(ProductSimpleSelectResponseDto[].class));

        // then
        assertThat(selectResponse).hasSize(2);
    }

    @Test
    void 상품을_단건_조회한다() {
        // given
        상품_생성_요청(mockFile, "테스트 제목1", "테스트 내용1", 10_000, "1", "1");
        final ExtractableResponse<Response> createResponse = 상품_생성_요청(mockFile, "테스트 제목2", "테스트 내용2", 20_000, "2", "2");
        final Long productId = Long.parseLong(createResponse.header("Location").split("/")[3]);

        // when
        final ExtractableResponse<Response> response = 상품_단건_조회_요청(productId);
        final ProductDetailSelectRequestDto selectResponse = response.as(ProductDetailSelectRequestDto.class);

        // then
        assertThat(selectResponse.getId()).isEqualTo(productId);
    }

    private ExtractableResponse<Response> 상품_생성_요청(final File file,
                                                   final String title,
                                                   final String content,
                                                   final int price,
                                                   final String categoryCode,
                                                   final String regionCode) {
        return given().log().all()
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                .multiPart("image", file)
                .multiPart("title", title)
                .multiPart("content", content)
                .multiPart("price", price)
                .multiPart("categoryCode", categoryCode)
                .multiPart("regionCode", regionCode)
                .when().post("/api/products")
                .then().log().all()
                .statusCode(HttpStatus.CREATED.value())
                .extract();
    }

    private ExtractableResponse<Response> 상품_전체_조회_요청() {
        return given().log().all()
                .when().get("/api/products")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .extract();
    }

    private ExtractableResponse<Response> 상품_단건_조회_요청(final Long productId) {
        return given().log().all()
                .when().get("/api/products/{productId}", productId)
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .extract();
    }
}
