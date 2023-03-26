package org.example.BullsAndCows;

import java.util.List;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {

        System.out.println("1-цифры");
        System.out.println("2-буквы EN");
        System.out.println("3-буквы RU");
        Scanner in = new Scanner(System.in);
        System.out.println("Выберите игру");
        int num = in.nextInt();
        Game game = null;
        int countGames = 3; // Количество вариантов игры

        switch (num) {
            case 1:
                game = new NumberGame();
                break;
            case 2:
                game = new WordGameEnglish();
                break;
            case 3:
                game = new WordGameRussian();
                break;
            default:
                System.out.println("такой игры еще не существует");
        }
        if (num > 0 && num < countGames + 1) {
            System.out.println("Введите количество символов:");
            int size = in.nextInt();
            System.out.println("Введите количество попыток:");
            int maxTry = in.nextInt();
            game.start(game.setSizeWord(size), game.setMaxTry(maxTry));
            System.out.printf("Загаданное слово/число \nсостоит из %d символов\n",
                    game.getSizeWord());

            int count = 0;
            while (game.getGameStatus().equals(GameStatus.START)) {
                System.out.println("-----------------------------------");
                System.out.printf("Осталось ходов: %d . \nВаш ход № %d: ",
                        (game.getMaxTry() - count), ++count);
                String answer = in.next();
                while (answer.length() != game.getSizeWord()) {
                    System.out.printf("Символов должно быть %d: ", game.getSizeWord());
                    answer = in.next();
                }
                Answer answerGame = game.inputAnswer(answer);

                // Запись в log
                game.setLog(String.format("Ход №%d: %s (%d коров, %d быков)",
                        count, answer, answerGame.getCows(), answerGame.getBulls()));

                System.out.printf("Найдено %d коров и %d быков%n",
                        answerGame.getCows(), answerGame.getBulls());
            }
            System.out.println("-----------------------------------");
            System.out.println("Загаданное слово/число: " + game.getWord());
            System.out.println(game.getGameStatus());

            System.out.println("Вывести лог Ваших ответов?" +
                    " \nЕсли да, нажмите Y или Д" +
                    " \n(нет - любая другая клавиша)");

            String logAnswer = in.next().toLowerCase();
            if (logAnswer.equals("y") || logAnswer.equals("д")) {
                List<String> newLog = game.getLog();
                System.out.println("Ваши ходы");
                for (String line : newLog) {
                    System.out.println(line);
                }
            }
        }
    }
}
