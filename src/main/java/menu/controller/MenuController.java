package menu.controller;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Supplier;
import menu.model.Categories;
import menu.model.Coaches;
import menu.model.Menus;
import menu.view.InputView;
import menu.view.OutputView;
import menu.view.error.ErrorException;
import menu.view.error.InputErrorType;

public class MenuController {
/*
    private List<String> JAPANESE_FOOD = List.of("규동, 우동, 미소시루, 스시, 가츠동, 오니기리, 하이라이스, 라멘, 오코노미야끼");
    private List<String> KOREAN_FOOD = List.of("김밥, 김치찌개, 쌈밥, 된장찌개, 비빔밥, 칼국수, 불고기, 떡볶이, 제육볶음");
    private List<String> CHINESE_FOOD = List.of("깐풍기, 볶음면, 동파육, 짜장면, 짬뽕, 마파두부, 탕수육, 토마토 달걀볶음, 고추잡채");
    private List<String> ASIAN_FOOD = List.of("팟타이, 카오 팟, 나시고렝, 파인애플 볶음밥, 쌀국수, 똠얌꿍, 반미, 월남쌈, 분짜");
    private List<String> WESTERN_FOOD = List.of("라자냐, 그라탱, 뇨끼, 끼슈, 프렌치 토스트, 바게트, 스파게티, 피자, 파니니");


 */
    private InputView inputView;
    private OutputView outputView;

    public MenuController (InputView inputView, OutputView outputView
                           ) {
        this.inputView = inputView;
        this.outputView = outputView;
    }


    public void run(){
        outputView.displayStartMessage();
        List<String> coaches = getValidCoaches(inputView::enteredCoaches);
        Map<String, List<String>> personalOptions = new HashMap<>();
        for(String coach: coaches){
            List<String> menuToExclude = getValidMenuToExclude(coach);
            personalOptions.put(coach, menuToExclude);
        }

        List<String> menuOfTheCoach = new LinkedList<>();
        List<String> categories = new LinkedList<>();
        List<String> menuOfAllCoaches = new LinkedList<>();

        for(int day =1; day<=5; day++){
            getValidCategory(categories);
        }


        for(Entry<String, List<String>> personalOption : personalOptions.entrySet()){
            for(int day =1; day<=5; day++){
                String menu = getValidMenu(categories.get(day-1), personalOption, menuOfTheCoach);
                menuOfAllCoaches.add(menu);
            }
            menuOfTheCoach = new LinkedList<>();
        }

        outputView.displayMessage();
        String categoriesForDisplay = String.join(" | ", categories);
        outputView.displayCategories(categoriesForDisplay);
        displayResult(personalOptions, menuOfAllCoaches);
        outputView.displayEndMessage();
    }

    private void displayResult(Map<String, List<String>> personalOptions, List<String> menuOfAllCoaches) {
        for(Entry<String, List<String>> personalOption : personalOptions.entrySet()){
            String coachName = personalOption.getKey();
            outputView.displayCoachName(coachName);

            for(int index = 0; index< menuOfAllCoaches.size(); index+=5){
                List<String> menusOfTheCoach = menuOfAllCoaches.subList(index, index+5);
                String menus = String.join(" | ", menusOfTheCoach);
                outputView.displayMenus(menus);
            }
        }
    }

    private List<String> getValidCategory(List<String> categories) {
        while (true) {
            String category = Categories.get(Randoms.pickNumberInRange(1, 5));
            if(categories.size()==1){
                categories.add(category);
                return categories;
            }
            long hasSameCategory = categories.stream()
                    .filter(value -> value.equals(category))
                    .count();
            try {
                if(hasSameCategory >2){
                    throw new IllegalArgumentException("retry");
                }
                categories.add(category);
                return categories;

            } catch (ErrorException e) {
                if(!e.getMessage().equals("retry")){
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private String getValidMenu(String category, Entry<String, List<String>> personalOption, List<String> menuOfTheCoach) {
        while (true) {
            String menu = getMenuByCategory(category);
            boolean isMenuToExclude = personalOption.getValue().stream()
                    .anyMatch(value -> value.equals(menu));
            boolean isDuplicated = false;
            if(menuOfTheCoach != null){
                isDuplicated = menuOfTheCoach.stream()
                        .anyMatch(value -> value.equals(menu));
            }

            try {
                if(isMenuToExclude || isDuplicated){
                    throw new IllegalArgumentException("retry");
                }
                menuOfTheCoach.add(menu);
                return menu;

            } catch (IllegalArgumentException e) {
                if(!e.getMessage().equals("retry")){
                    System.out.println(e.getMessage());
                }
            }
        }
    }
    private List<String> getValidCoaches(Supplier<String> inputSupplier) {
        while (true) {
            String input = inputSupplier.get();
            try {
                Coaches coaches = new Coaches(input);
                return coaches.getNames();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private List<String> getValidMenuToExclude(String coach) {
        while (true) {
            String input = inputView.enteredMenuToExclude(coach);
            try {
                Menus menus = new Menus(input);
                return menus.getNames();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private String getMenuByCategory(String input) throws ErrorException {

        List<String> JAPANESE_FOOD = List.of("규동, 우동, 미소시루, 스시, 가츠동, 오니기리, 하이라이스, 라멘, 오코노미야끼");
        List<String> KOREAN_FOOD = List.of("김밥, 김치찌개, 쌈밥, 된장찌개, 비빔밥, 칼국수, 불고기, 떡볶이, 제육볶음");
        List<String> CHINESE_FOOD = List.of("깐풍기, 볶음면, 동파육, 짜장면, 짬뽕, 마파두부, 탕수육, 토마토 달걀볶음, 고추잡채");
        List<String> ASIAN_FOOD = List.of("팟타이, 카오 팟, 나시고렝, 파인애플 볶음밥, 쌀국수, 똠얌꿍, 반미, 월남쌈, 분짜");
        List<String> WESTERN_FOOD = List.of("라자냐, 그라탱, 뇨끼, 끼슈, 프렌치 토스트, 바게트, 스파게티, 피자, 파니니");

        if(input.equals("일식")){
            return Randoms.shuffle(JAPANESE_FOOD).get(0);
        }
        if(input.equals("한식")){
            return Randoms.shuffle(KOREAN_FOOD).get(0);
        }
        if(input.equals("중식")){
            return Randoms.shuffle(CHINESE_FOOD).get(0);
        }
        if(input.equals("아시안")){
            return Randoms.shuffle(ASIAN_FOOD).get(0);
        }
        if(input.equals("양식")){
            return Randoms.shuffle(WESTERN_FOOD).get(0);
        }
        throw new ErrorException(InputErrorType.NEED_AVAILABLE_INPUT);
    }
}
