import org.testng.annotations.Test;

import java.util.*;

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
    void factorialTests() {
        System.out.println(factorial(4));
        System.out.println(factorial(5));
        System.out.println(factorial(10));
    }

    // находим максимальный элемент в списке
    private int findMax(List<Integer> arr) {

        if (arr.size() == 1) {
            return arr.get(0);
        }
        int subMax = findMax(arr.subList(1, arr.size()));
        if (arr.get(0) > subMax) {
            return arr.get(0);
        } else
            return subMax;
    }

    @Test
    void findMaxValueTest() {
        System.out.println("max value  = " + findMax(new ArrayList<>(List.of(100, 2, 399, 400, 5, 6, 7, 8, 9099))));
        System.out.println("max value  = " + findMax(new ArrayList<>(List.of(109))));
        System.out.println("max value  = " + findMax(new ArrayList<>(List.of(100, 100, 100))));
        System.out.println("max value  = " + findMax(new ArrayList<>(List.of(0, -11100, 0))));
    }

    //  подсчитываем count
    private int countElements(List<Integer> arr) {

        if (arr.isEmpty()) {
            return 0;
        }
        return 1 + countElements(arr.subList(1, arr.size()));
    }

    @Test
    void countElementsInArrayTests() {
        System.out.println("count  = " + countElements(new ArrayList<>(List.of(100, 2, 399, 400, 5, 6, 7, 8, 9099))));
        System.out.println("count  = " + countElements(new ArrayList<>(List.of(109))));
        System.out.println("count  = " + countElements(new ArrayList<>(List.of(100, 100, 100))));
        System.out.println("count  = " + countElements(new ArrayList<>(List.of(0, -11100, 0))));
        System.out.println("count  = " + countElements(new ArrayList<>(List.of(10, 0))));
    }

    // метод быстрой сортировки. в качестве опорного элемента берется первый
    private List<Integer> quickSort(List<Integer> arr) {
        if (arr.size() < 2) {
            return arr;
        }
        int basis = arr.get(0); // за опорный элемент берем первый в массиве
        List<Integer> lessArray = new ArrayList<>();
        List<Integer> greaterArray = new ArrayList<>();

        for (int i = 1; i < arr.size(); i++) {
            if (basis > arr.get(i)) {
                lessArray.add(arr.get(i));
            } else {
                greaterArray.add(arr.get(i));
            }
        }
        List<Integer> result = new ArrayList<>();
        result.addAll(quickSort(lessArray));
        result.add(basis);
        result.addAll(quickSort(greaterArray));
        return result;
    }

    @Test
    void quickSortTest() {
        System.out.println(quickSort(new ArrayList<>(List.of(-9, -100, 12, 0, 99, 55, 3, -65))));
        System.out.println(quickSort(Arrays.asList(10, -10, 12, 0)));
        System.out.println(quickSort(List.of(10)));
        System.out.println(quickSort(Collections.emptyList()));
    }

    private List<Integer> quickSort2(List<Integer> arr) {
        if (arr.size() < 2) {
            return arr;
        }
        int basis = arr.get(arr.size() / 2); // за опорный элемент берем элемент из середины
        List<Integer> lessArray = new ArrayList<>();
        List<Integer> greaterArray = new ArrayList<>();

        for (Integer elem : arr) {
            if (elem == basis) {
                continue;
            }
            if (basis > elem) {
                lessArray.add(elem);
            } else {
                greaterArray.add(elem);
            }
        }
        List<Integer> result = new ArrayList<>();
        result.addAll(quickSort2(lessArray));
        result.add(basis);
        result.addAll(quickSort2(greaterArray));
        return result;
    }

    @Test
    void quickSort2Test() {
        System.out.println(quickSort2(new ArrayList<>(List.of(-9, -100, 12, 0, 99, 55, 3, -65))));
        System.out.println(quickSort2(Arrays.asList(10, -10, 12, 0)));
        System.out.println(quickSort2(List.of(10)));
        System.out.println(quickSort2(Collections.emptyList()));
    }

    // поиск в ширину
    public String breadthFirstSearch(Map<String, List<String>> graph) {
        Queue<String> queue = new LinkedList<>();
        queue.addAll(graph.get("you"));
        Set<String> searched = new HashSet<>();
        while (!queue.isEmpty()) {
            //System.out.println(queue.size());
            String person = queue.poll();
            if (!searched.contains(person)) {
                System.out.println(person);
                if (checkPersonIsSeller(person)) {
                    System.out.println("person " + person + " is seller!");
                    return person;
                } else {
                    queue.addAll(graph.get(person));
                    searched.add(person);
                }
            }
        }
        return null;
    }

    private boolean checkPersonIsSeller(String person) {
        return person.equals("tom");
    }

    @Test
    void checkPersonIsSellerTest(){
        Map<String, List<String>> graph = new HashMap<>();
        //graph.put("you", List.of("alice", "bob", "claire"));
        graph.put("you", List.of("claire", "alice", "bob"));
        graph.put("bob", List.of("anuj" , "peggy"));
        graph.put("alice", List.of( "peggy"));
        graph.put("claire", List.of( "tom", "jonny"));
        graph.put("anuj", Collections.emptyList());
        graph.put("peggy", Collections.emptyList());
        graph.put("tom", Collections.emptyList());
        graph.put("jonny", Collections.emptyList());

        System.out.println(breadthFirstSearch(graph));
    }




}
