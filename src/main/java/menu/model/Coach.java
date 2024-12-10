package menu.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Coach {

    private final String name;
    private List<String> menuToExclude = new ArrayList<>();
    private final List<String> recommendedMenus = new LinkedList<>();


    public Coach(String input){
        this.name = input;
    }

    public String getName(){
        return name;
    }

    public List<String> getRecommendedMenus(){
        return recommendedMenus;
    }

    public void setMenuToExclude(List<String> input){
        this.menuToExclude = input;
    }

    public void recommendMenu(String category) {
        String recommendedMenu = Categories.getRecommendedMenu(category);
        boolean isMenuToExclude = menuToExclude.stream()
                .anyMatch(value -> value.equals(recommendedMenu));
        boolean isDuplicated =  recommendedMenus.stream()
                .anyMatch(value -> value.equals(recommendedMenu));

        if(isMenuToExclude || isDuplicated){
            recommendMenu(category);
            return;
        }

        recommendedMenus.add(recommendedMenu);
    }
}
