package menu.model;


import java.util.LinkedList;
import java.util.List;
import menu.view.error.ErrorException;
import menu.view.error.InputErrorType;

public class Coaches {

    private final String COMMA = "\\s*,\\s*";

    private final List<Coach> coaches;

    public Coaches(String input) throws ErrorException {
        String[] splitInput = extractValidInput(input);
        this.coaches = getValidCoaches(splitInput);
    }

    public List<Coach> getCoaches(){
        return coaches;
    }


    private List<Coach> getValidCoaches(String[] splitInput) throws ErrorException {
        List<Coach> coaches = new LinkedList<>();
        for (String coachesName: splitInput) {
            checkNameLength(coachesName);
            coaches.add(new Coach(coachesName));
        }
        return coaches;
    }

    private void checkNameLength(String input) throws ErrorException {
        if (input.length() < 2) {
            throw new ErrorException(InputErrorType.NEED_AT_LEAST_TWO_CHARACTER);
        }
        if (input.length() > 4) {
            throw new ErrorException(InputErrorType.MAXIMUM_CHARACTER_IS_FOUR);
        }
    }

    private String[] extractValidInput(String input) throws ErrorException {
        checkIsEmpty(input);
        String[] splitInput = input.split(COMMA);
        checkCoachesCount(splitInput);
        return splitInput;
    }

    private void checkCoachesCount(String[] splitInput) throws ErrorException {
        if (splitInput.length < 2) {
            throw new ErrorException(InputErrorType.NEED_AT_LEAST_TWO_COACH);
        }
        if (splitInput.length > 5) {
            throw new ErrorException(InputErrorType.MAXIMUM_COACH_IS_FIVE);
        }
    }

    private void checkIsEmpty(String input) throws ErrorException {
        if (input.isEmpty()) {
            throw new ErrorException(InputErrorType.NEED_AVAILABLE_INPUT);
        }
    }


    public void recommendMenus(List<String> categories){
        for (int day = 0; day < 5; day++) {
            for (Coach coach: coaches) {
                coach.recommendMenu(categories.get(day));
            }
        }
    }
}
