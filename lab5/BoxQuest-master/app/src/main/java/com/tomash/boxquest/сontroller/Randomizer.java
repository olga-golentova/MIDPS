package com.tomash.boxquest.сontroller;

import com.tomash.boxquest.model.Box;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Randomizer {
    private static Random mRandom = new Random();

    /**
     * Mix list by getting item from random index and inserting it in random position.
     *
     * @param list list to mix
     */
    public static <T> void mixList(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            //random index where to insert
            int randomInsertIndex = mRandom.nextInt(list.size());
            //random index where to get item
            int randomItemIndex = mRandom.nextInt(list.size());
            T item = list.get(randomItemIndex);
            //removing it
            list.remove(randomItemIndex);
            //adding to new position
            list.add(randomInsertIndex, item);
        }

    }

    /**
     * Create new list with boxes , already mixed.
     *
     * @return list of mixed boxes.
     */
    public static ArrayList<Box> getStartingBoxList() {
        ArrayList<Box> boxList = new ArrayList<>();
        boxList.add(new Box(true));
        for (int i = 0; i < 9; i++) {
            boxList.add(new Box(false));
            mixList(boxList);
        }
        return boxList;
    }


}
