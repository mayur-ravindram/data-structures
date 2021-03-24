import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * HourglassSum column | V row -> 1 0 1 0 9 2 4 6 2 exclude a[1][0] and a[1][2]
 * for sum
 * 
 */
public class HourglassSum {

    static Map<Integer, List<Integer>> calculateHourglassSum(int[][] subArray) {
        int sum = 0, element = 0;
        Map<Integer, List<Integer>> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < subArray.length; i++) {
            for (int j = 0; j < subArray.length; j++) {
                sum += subArray[i][j];
                element = subArray[i][j];
                list.add(element);
            }
        }
        map.put(sum, list);
        return map;
    }

    static void printArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                System.out.print(array[i][j] + "   ");
            }
            System.out.println();
        }
    }

    static int[][] printSelectedMatrix(int[][] matrix, int rowIndex, int colIndex, int size) {
        int[][] a = new int[size][size];

        for (int i = rowIndex, indexRow = 0; i < rowIndex + size; i++, indexRow++) {
            for (int j = colIndex, indexCol = 0; j < colIndex + size; j++, indexCol++) {
                if (i < matrix.length && j < matrix.length && includeHourglassElements(indexRow, indexCol)) {
                    a[indexRow][indexCol] = matrix[i][j];
                }
            }
        }
        return a;
    }

    private static boolean includeHourglassElements(int indexRow, int indexCol) {
        return (indexRow == 1 && indexCol != 0) || (indexRow != 1 && indexCol != 2);
    }

    public static void main(String[] args) {
        int[][] array = new int[][] { { 1, 1, 1, -2, -2, -2 }, { -2, 1, -2, -2, -2, -2 }, { 1, 1, 1, -2, -2, -2 },
                { -2, -2, 2, 4, 4, -2 }, { -2, -2, -2, 2, -2, -2 }, { -2, -2, 1, 2, 4, 9 } };

        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int row = 0; row < array.length; row++) {
            for (int col = 0; col < array.length; col++) {
                map.putAll(calculateHourglassSum(printSelectedMatrix(array, row, col, 3)));
            }
        }
        Optional<Integer> minSum = map.keySet().stream().min((a, b) -> a - b);
        System.out.println("minimum hourglass sum is :" + minSum.get());
        Optional<Integer> maxSum = map.keySet().stream().max((a, b) -> a - b);
        System.out.println( "maximum hourglass sum is :" + maxSum.get());
    }
}