package com.onrkrdmn.helper;

/**
 * @author Onur Karaduman
 * @since 09.04.17
 */
public class ArrayHelper {

    /**
     * Reverse int array
     *
     * @param arr
     */
    public static void reverseArray(int[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = temp;
        }
    }

    /**
     * Convert the string pattern to int array for representing as edge
     *
     * @param text
     * @return
     */
    public static int[] convertToEdgeArray(String text) {

        char[] charArray = text.toCharArray();
        int[] units = new int[charArray.length];
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == 'o') {
                units[i] = 1;
            } else {
                units[i] = 0;
            }
        }
        return units;
    }
}
