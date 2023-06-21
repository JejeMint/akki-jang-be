package jejemint.akkijang.domain;

import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {

    ELECTRIC_GUITAR("0"),
    ACOUSTIC_GUITAR("1"),
    BASS_GUITAR("2"),
    KEYBOARD("3"),
    DRUM("4"),
    CLASSIC("5"),
    SOUND_EQUIPMENT("6");

    private final String code;

    public static Category fromCode(String code) {
        return Arrays.stream(values())
                .filter(category -> category.equalsCode(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("코드에 맞는 카테고리가 존재하지 않습니다."));
    }

    private boolean equalsCode(String other) {
        return code.equals(other);
    }
}
