package org.example.BullsAndCows;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractGame implements Game {
    Integer sizeWord;
    String word;
    Integer maxTry;
    GameStatus gameStatus = GameStatus.INIT;
    List<String> log = new ArrayList<>();
    static int countTry;

    /**
     * Добавляет ответ игрока в лог игры
     *
     * @param answer ответ игрока (String)
     */
    public void setLog(String answer) {
        this.log.add(answer);
    }

    /**
     * Возврат ходов игрока
     *
     * @return Возвращает массив ходов игрока
     */
    public List<String> getLog() {
        return this.log;
    }

    /**
     * Размер загаданого слова
     *
     * @param size размер слова/числа (int)
     * @return возвращает размер слова (int)
     */
    public Integer setSizeWord(int size) {
        this.sizeWord = size;
        return sizeWord;
    }

    /**
     * Количество попыток
     *
     * @param size количество попыток (int)
     * @return возвращает количество попыток (int)
     */
    public Integer setMaxTry(int size) {
        this.maxTry = size;
        return maxTry;
    }

    /**
     * Генерирует загаданное слово/число
     *
     * @return взвращает загаданное слово/число (String)
     */
    private String generateWord() {
        List<String> charList = generateCharList();
        SecureRandom random = new SecureRandom();
        String res = "";
        if (charList.get(0) == "&num") {
            for (int i = 0; i < sizeWord; i++) {
                int randomIndex = random.nextInt(1, charList.size());
                res += charList.get(randomIndex);
                charList.remove(randomIndex);
            }
        } else {
            res = charList.get(AbstractGame.randomNum(1, charList.size()));
            while (res.length() != sizeWord) {
                res = charList.get(AbstractGame.randomNum(1, charList.size()));
            }
        }
        System.out.println("ДЛЯ ТЕСТА: ***|" + res + "|***"); // Для тестирования. При игре отключить!
        return res;
    }

    /**
     * Создание массива слов для выбора
     *
     * @param nameFile путь в файлу словаря ".txt"
     * @return возвращает массив всех слов в словаре
     */
    protected static List<String> getWordList(String nameFile) {
        List<String> wordsList = new ArrayList<>();
        wordsList.add("&word");
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(nameFile)));
            String read;
            while ((read = br.readLine()) != null) {
                wordsList.add(read);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("I/O error.");
        }
        return wordsList;
    }

    /**
     * Размер загаданного слова
     *
     * @return возвращает (int) размер слова
     */
    public Integer getSizeWord() {
        return sizeWord;
    }

    /**
     * Максимально количество попыток
     *
     * @return возвращает (int) максимальное количество попыток
     */
    public Integer getMaxTry() {
        return maxTry;
    }

    /**
     * Возврат загаданного слова
     *
     * @return возвращает загаданное слово/число (String)
     */
    public String getWord() {
        return this.word;
    }

    /**
     * Запуск игры
     *
     * @param sizeWord длина загаданного слова/числа
     * @param maxTry   количество попыток отгадать
     */
    @Override
    public void start(Integer sizeWord, Integer maxTry) {
        this.maxTry = maxTry;
        this.sizeWord = sizeWord;
        this.word = generateWord();
        this.gameStatus = GameStatus.START;
    }

    @Override
    public Answer inputAnswer(String value) {
        int bull = 0;
        int cow = 0;
        for (int i = 0; i < value.length(); i++) {
            if (word.contains(Character.toString(value.charAt(i)))) {
                cow++;
            }
            if (word.charAt(i) == value.charAt(i)) {
                bull++;
            }
        }
        countTry++;
        Answer answer = new Answer(cow, bull, value);
        gameStatus = checkState(value);
        return answer;
    }

    /**
     * Возвращает статус игры WIN/LOSE/START
     *
     * @param value ответ игрока (String)
     * @return меняет статус игры
     */
    private GameStatus checkState(String value) {
        if (value.equals(word)) {
            return gameStatus = GameStatus.WIN;
        } else {
            if (countTry >= maxTry) {
                return gameStatus = GameStatus.LOSE;
            } else {
                return gameStatus = GameStatus.START;
            }
        }
    }

    /**
     * Рандом числа "int" от min до max включительно
     *
     * @param min минимальное значение (включительно)
     * @param max максимальное значение (включительно)
     * @return возвращает число "int"
     */
    public static int randomNum(int min, int max) {
        Random r = new Random();
        return r.nextInt(max + 1 - min) + min;
    }

    /**
     * Возвращает массив элементов для рандомного выбора слова/числа
     *
     * @return возврат массива слов/цифр
     */
    abstract List<String> generateCharList();
}

