package menu.view;

public class OutputView {

    public void displayStartMessage() {
        System.out.println("점심 메뉴 추천을 시작합니다.");
    }


    private static final String LINE_SEPARATOR = System.lineSeparator();

    public void displayMessage() {
        System.out.println("메뉴 추천 결과입니다." + LINE_SEPARATOR +
                "[ 구분 | 월요일 | 화요일 | 수요일 | 목요일 | 금요일 ]");
    }

    public void displayCategories(String input){
        System.out.println("[ 카테고리 | " + input + " ]");

    }

    public void displayCoachName(String coachName) {
        System.out.print("[ " + coachName + " | ");
    }

    public void displayMenus(String menus) {
        System.out.println(menus + " ]");
    }

    public void displayEndMessage() {
        System.out.println(LINE_SEPARATOR + "추천을 완료했습니다.");
    }
}
