package jejemint.akkijang.integration;

import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.io.File;
import java.io.IOException;
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

    @BeforeEach
    void setUp() {
        RestAssured.port = this.port;
    }

    @Test
    void 상품을_등록한다() throws IOException{
        // given
        String filePath = "src/test/resources/image/test.png";
        final File file = new File(filePath);

        // when
        final ExtractableResponse<Response> response = 상품_생성_요청(file, "테스트 제목", "테스트 내용", 10_000, "1", "1");

        // then
        assertThat(response.header("Location")).contains("/products/");
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
                .when().post("/products")
                .then().log().all()
                .statusCode(HttpStatus.CREATED.value())
                .extract();
    }
}
