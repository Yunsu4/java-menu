package menu.view.error;

public enum InputErrorType {

    ERROR_MESSAGE("[ERROR] "),
    NEED_AVAILABLE_FORMAT("올바르지 않은 형식으로 입력했습니다."),
    NEED_AT_LEAST_TWO_COACH("코치는 최소 2명 이상 입력해야 합니다."),
    MAXIMUM_COACH_IS_FIVE("코치는 최대 5명만 입력해야 합니다."),
    NEED_AT_LEAST_TWO_CHARACTER("코치 이름은 최소 2글자 입니다."),
    MAXIMUM_CHARACTER_IS_FOUR("코치 이름은 최대 4글자 입니다."),
    MAXIMUM_MENU_IS_TWO("메뉴는 최대 2개까지 입력 가능합니다."),
    MAXIMUM_MENU_NAME_LENGTH_IS_TEN("메뉴 이름은 최대 10글자 입니다."),
    NEED_AVAILABLE_INPUT("잘못된 입력입니다."),
    NEED_REENTER_INPUT(" 다시 입력해 주세요.");

    final String message;

    InputErrorType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
