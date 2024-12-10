package menu.model;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import menu.view.error.ErrorException;
import menu.view.error.InputErrorType;

public enum Categories {

    JAPANESE("일식", 1, List.of("규동, 우동, 미소시루, 스시, 가츠동, 오니기리, 하이라이스, 라멘, 오코노미야끼")),
    KOREAN("한식", 2, List.of("김밥, 김치찌개, 쌈밥, 된장찌개, 비빔밥, 칼국수, 불고기, 떡볶이, 제육볶음")),
    CHINESE("중식", 3, List.of("깐풍기, 볶음면, 동파육, 짜장면, 짬뽕, 마파두부, 탕수육, 토마토 달걀볶음, 고추잡채")),
    ASIAN("아시안", 4, List.of("팟타이, 카오 팟, 나시고렝, 파인애플 볶음밥, 쌀국수, 똠얌꿍, 반미, 월남쌈, 분짜")),
    WESTERN("양식", 5, List.of("라자냐, 그라탱, 뇨끼, 끼슈, 프렌치 토스트, 바게트, 스파게티, 피자, 파니니"));

    final String category;
    final int value;
    final List<String> menus;

    Categories(String category, int value, List<String> menus) {
        this.category = category;
        this.value = value;
        this.menus = menus;
    }

    public static String get(int input) {
        return Arrays.stream(Categories.values())
                .filter(categories -> Objects.equals(categories.value, input))
                .map(categories -> categories.category)
                .findAny()
                .orElseThrow(() -> new ErrorException(InputErrorType.NEED_AVAILABLE_INPUT));
    }

    public static String getRecommendedMenu(String input) {
        return Arrays.stream(Categories.values())
                .filter(categories -> categories.category.equals(input))
                .findAny()
                .map(categories -> Randoms.shuffle(categories.menus).get(0))
                .orElseThrow(() -> new ErrorException(InputErrorType.NEED_AVAILABLE_INPUT));
    }

}
