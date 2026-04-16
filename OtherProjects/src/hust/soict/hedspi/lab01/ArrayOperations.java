package hust.soict.hedspi.lab01;

import java.util.Arrays;
import java.util.Scanner;

public class ArrayOperations {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the size of array: ");
        int n = sc.nextInt();
        int[] my_array = new int[n];
        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < n; i++) {
            my_array[i] = sc.nextInt();
        }
        System.out.println("Original array: " + Arrays.toString(my_array));
        Arrays.sort(my_array);
        System.out.println("Sorted array:   " + Arrays.toString(my_array));
        long sum = 0;
        for (int num : my_array) {
            sum += num;
        }
        double average = (double) sum / my_array.length;
        System.out.println("Sum of array elements: " + sum);
        System.out.println("Average value of array elements: " + average);
        sc.close();
    }
}