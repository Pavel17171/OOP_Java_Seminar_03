package org.example.BullsAndCows;

import java.util.List;

public class WordGameEnglish extends AbstractGame {
    /**
     * Путь к файлу английского словаря
     */
    String nameFile = "src\\main\\java\\org\\" +
            "example\\BullsAndCows\\Dictionary\\" +
            "DictionaryEN.txt";

    /**
     * Создание массива английских слов
     *
     * @return возвращает массив слов
     */
    @Override
    List<String> generateCharList() {
        return getWordList(nameFile);
    }
}