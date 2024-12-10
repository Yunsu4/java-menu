package menu.model;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.LinkedList;
import java.util.List;


public class RecommendedCategories {

    private final List<String> categories = new LinkedList<>();

    public RecommendedCategories(){
        generate();
    }

    public List<String> get(){
        return categories;
    }

    private void generate() {
        for (int day = 1; day <= 5; day++) {
            getValidCategory();
        }
    }

    private void getValidCategory() {
        String category = Categories.get(Randoms.pickNumberInRange(1, 5));

        if (isFirstCategory(categories, category)) {
            return;
        }
        if (twoOrMoreDuplicatedCategories(category)) {
            return;
        }

        categories.add(category);
    }

    private boolean isFirstCategory(List<String> categories, String category) {
        if (categories.size() == 1) {
            categories.add(category);
            return true;
        }
        return false;
    }

    private boolean twoOrMoreDuplicatedCategories(String category) {
        if (sameCategoryCount(category) > 2) {
            getValidCategory();
            return true;
        }
        return false;
    }

    private long sameCategoryCount(String category) {
        return categories.stream()
                .filter(value -> value.equals(category))
                .count();
    }
}
