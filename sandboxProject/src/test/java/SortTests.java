import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class SortTests {

    // алгоритм бинарного поиска
    public Integer binarySearch(List<Integer> arr, int elem) {
        int low = 0;
        int high = arr.size() - 1;
        int med;
        while (low <= high) {
            med = (low + high) / 2;
            int guess = arr.get(med);
            if (elem == guess) {
                return med;
            }
            if (guess > elem) {
                high = med - 1;
            } else {
                low = med + 1;
            }
        }
        return null;
    }

    @Test
    void binarySearchTests() {
        List<Integer> array = List.of(-9, -5, 0, 1, 3, 8, 99, 101);
        System.out.println(binarySearch(array, 101));
        System.out.println(binarySearch(array, 0));
        System.out.println(binarySearch(array, 3));
    }

    // алогритм сортировки выбором:
    // находим наименьший жлемент в массиве
    // копируем его в новый массив
    // из старого элемент удаляем
    // повторяем действия, пока не переберем все элементы в исходном массиве
    // Алгоритм долгий, сложность O(n^2)

    // делаем метод поиска наименьшего значения
    private int findSmallestIndex(List<Integer> arr) {
        int smallest = arr.get(0);
        int smallestIndex = 0;
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) < smallest) {
                smallest = arr.get(i);
                smallestIndex = i;
            }
        }
        return smallestIndex;
    }

    // сама функция сортировки выбором
    private List<Integer> selectionSort(List<Integer> arr) {
        List<Integer> sortedList = new ArrayList<>();
        int index; // = findSmallestIndex(arr);
        var size = arr.size();
        for (int i = 0; i < size; i++) {
            index = findSmallestIndex(arr);
            sortedList.add(arr.get(index));
            arr.remove(index);
        }
        return sortedList;
    }

    @Test
    void selectionSortTest() {
        List<Integer> unsortedArray = new ArrayList<>(List.of(3, 6, 1, -1, -9, 9, -9, 0, 0));
        System.out.println(selectionSort(unsortedArray));
    }

    // метод получения суммы элементов массива через рекурсию

    private int arrElementSum(List<Integer> arr) {
        if (arr.isEmpty()) {
            return 0; // если массив пустой - то сумма = 0
        }
        // рекурсивно: берем первый элемент и прибавляем к нему ркурсивный вызов метода от массива без первого элемента
        return arr.get(0) + arrElementSum(arr.subList(1, arr.size()));
    }

    @Test
    void arrElementSumTest() {
        List<Integer> arr = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9));
        System.out.println("size = " + arr.size());
        System.out.println("sum = " + arrElementSum(arr));
    }

    // расчет факториала
    private int factorial(int x) {
        if (x == 0 || x == 1) {

            return 1;
        }
        return x * factorial(x - 1);
    }

    @Test
    void factorialTests(){
        System.out.println(factorial(4));
        System.out.println(factorial(5));
        System.out.println(factorial(10));
    }

}
