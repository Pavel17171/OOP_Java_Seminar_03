package org.example.BullsAndCows;

import java.util.ArrayList;
import java.util.List;

public class NumberGame extends AbstractGame {
    /**
     * Генерация набора цифр
     *
     * @return возвращает массив цифр (0-9)
     */
    @Override
    List<String> generateCharList() {
        List<String> charList = new ArrayList<String>();
        charList.add("&num");
        for (int i = 0; i < 10; ++i) {
            charList.add(String.valueOf(i));
        }
        return charList;
    }
}