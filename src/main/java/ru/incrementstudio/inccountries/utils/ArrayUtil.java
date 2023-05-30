package ru.incrementstudio.inccountries.utils;

import java.util.ArrayList;
import java.util.List;

public class ArrayUtil {
    public static Object[] subarray(Object[] array, int startIndex, int endIndex) {
        List<Object> result = new ArrayList<>();
        for (int i = startIndex; i < endIndex && i < array.length; i++) {
            result.add(array[i]);
        }
        return result.toArray();
    }

    public static List<Object> sublist(List<Object> list, int startIndex, int endIndex) {
        List<Object> result = new ArrayList<>();
        for (int i = startIndex; i < endIndex && i < list.size(); i++) {
            result.add(list.get(i));
        }
        return result;
    }


    public static List<String> toStringList(String[] array) {
        List<String> result = new ArrayList<>();
        for (String element : array) result.add(element);
        return result;
    }
}