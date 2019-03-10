package com.franklin.deck.utils;

import java.util.List;
import java.util.Random;

public class Shuffle {

    private static Random rand = new Random();

    public <T> void shuffleList(List<T> list) {
        int length = list.size();
        for (int i = 0; i < length; i++) {
            int positionToSwap = rand.nextInt(length);

            float probability = rand.nextFloat();

            T temp = list.get(positionToSwap);
            list.set(positionToSwap, list.get(i));
            list.set(i, temp);
        }
    }
}
