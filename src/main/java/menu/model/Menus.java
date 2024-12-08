package menu.model;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.LinkedList;
import java.util.List;
import menu.view.error.ErrorException;
import menu.view.error.InputErrorType;

public class Menus {

    public static final String NONE = "none";
    private final String COMMA = "\\s*,\\s*";
    private List<String> names;

    public Menus(String input) throws ErrorException {
        String[] splitInput = extractValidInput(input);
        this.names = getValidCoaches(splitInput);
    }

    public List<String> getNames(){
        return names;
    }

    private List<String> getValidCoaches(String[] splitInput) throws ErrorException {
        for (String input : splitInput) {
            checkNameLength(input);
        }
        return new LinkedList<>(List.of(splitInput));
    }

    private void checkNameLength(String input) throws ErrorException {
        if (input.length() > 10) {
            throw new ErrorException(InputErrorType.MAXIMUM_MENU_NAME_LENGTH_IS_TEN);
        }
    }

    private String[] extractValidInput(String input) throws ErrorException {
        if(input.isEmpty()){
            return new String[]{NONE};
        }
        String[] splitInput = input.split(COMMA);
        checkMenusCount(splitInput);
        return splitInput;
    }

    private void checkMenusCount(String[] splitInput) throws ErrorException {
        if (splitInput.length > 2) {
            throw new ErrorException(InputErrorType.MAXIMUM_MENU_IS_TWO);
        }
    }


}
