package jejemint.akkijang.domain;

import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Region {
    SEOUL("0"),
    GYEONGGI("1"),
    INCHEON("2"),
    GANGWON("3"),
    JEJU("4"),
    DAEJEON("5"),
    CHUNGBUK("6"),
    CHUNGNAM("7"),
    SEJONG("8"),
    BUSAN("9"),
    ULSAN("10"),
    GYEONGNAM("11"),
    DAEGU("12"),
    GYEONGBUK("13"),
    GWANGJU("14"),
    JEONNAM("15"),
    JEONJU("16"),
    JEONBUK("17");

    private final String code;

    public static Region fromCode(String code) {
        return Arrays.stream(values())
                .filter(region -> region.equalsCode(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("코드에 맞는 지역이 존재하지 않습니다."));
    }

    private boolean equalsCode(String other) {
        return code.equals(other);
    }
}
