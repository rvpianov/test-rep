import org.testng.annotations.Test;

import java.util.List;

public class SortTests {

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

}
