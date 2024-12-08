package menu.model;

import java.util.Arrays;
import java.util.Objects;
import menu.view.error.ErrorException;
import menu.view.error.InputErrorType;

public enum Categories {

    JAPANESE("일식", 1),
    KOREAN("한식", 2),
    CHINESE("중식", 3),
    ASIAN("아시안", 4),
    WESTERN("양식", 5);

    final String category;
    final int value;

    Categories(String category, int value) {
        this.category = category;
        this.value = value;
    }

    public static String get(int input) {
        return Arrays.stream(Categories.values())
                .filter(dayOfWeek -> Objects.equals(dayOfWeek.value, input))
                .map(dayOfWeek -> dayOfWeek.category)
                .findAny()
                .orElseThrow(() -> new ErrorException(InputErrorType.NEED_AVAILABLE_INPUT));
    }
}
