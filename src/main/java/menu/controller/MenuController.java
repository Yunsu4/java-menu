package menu.controller;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import menu.model.Categories;
import menu.model.Coach;
import menu.model.Coaches;
import menu.model.Menus;
import menu.model.RecommendedCategories;
import menu.view.InputView;
import menu.view.OutputView;
import menu.view.error.ErrorException;

public class MenuController {


    private InputView inputView;
    private OutputView outputView;

    public MenuController(InputView inputView, OutputView outputView
    ) {
        this.inputView = inputView;
        this.outputView = outputView;
    }


    public void run() {
        outputView.displayStartMessage();
        Coaches coaches = getValidInput(inputView::enteredCoaches, Coaches::new);

        for (Coach coach: coaches.getCoaches()) {
            Menus menuToExclude = getValidInput(inputView::enteredMenuToExclude, coach.getName(), Menus::new);
            coach.setMenuToExclude(menuToExclude.getNames());
        }

        RecommendedCategories recommendedCategories = new RecommendedCategories();
        List<String> categories = recommendedCategories.get();
        coaches.recommendMenus(categories);

        displayRecommendMenus(categories, coaches);
    }

    private void displayResult(Coaches coaches) {
        for (Coach coach: coaches.getCoaches()) {
            outputView.displayCoachName(coach.getName());

            String menus = String.join(" | ", coach.getRecommendedMenus());
            outputView.displayMenus(menus);
        }
    }

    private void displayRecommendMenus(List<String> categories, Coaches coaches) {
        outputView.displayMessage();
        String categoriesForDisplay = String.join(" | ", categories);
        outputView.displayCategories(categoriesForDisplay);
        displayResult(coaches);
        outputView.displayEndMessage();
    }




    private <T> T getValidInput(Supplier<String> inputSupplier, Function<String, T> converter) {
        while (true) {
            String input = inputSupplier.get();
            try {
                return converter.apply(input);
            } catch (ErrorException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private <T, U> T getValidInput(Function<U, String> inputFunction, U inputArg, Function<String, T> converter) {
        while (true) {
            String input = inputFunction.apply(inputArg); // 매개변수로 입력 받기
            try {
                return converter.apply(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }


}
