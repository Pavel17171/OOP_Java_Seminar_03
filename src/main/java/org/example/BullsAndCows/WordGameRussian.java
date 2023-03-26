package org.example.BullsAndCows;

import java.util.List;

public class WordGameRussian extends AbstractGame {
    /**
     * Путь к файлу русского словаря
     */
    String nameFile = "src\\main\\java\\org\\" +
            "example\\BullsAndCows\\Dictionary\\" +
            "DictionaryRU.txt";

    /**
     * Создание массива русских слов
     *
     * @return возвращает массив слов
     */
    @Override
    List<String> generateCharList() {
        return getWordList(nameFile);
    }
}