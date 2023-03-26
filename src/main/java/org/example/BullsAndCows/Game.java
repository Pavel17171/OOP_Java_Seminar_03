package org.example.BullsAndCows;

import java.util.List;

public interface Game {
    void start(Integer sizeWord, Integer maxTry);

    Answer inputAnswer(String value);

    String getWord();

    GameStatus getGameStatus();

    Integer getSizeWord();

    Integer setSizeWord(int size);

    Integer getMaxTry();

    Integer setMaxTry(int size);

    List<String> getLog();

    void setLog(String answer);
}
