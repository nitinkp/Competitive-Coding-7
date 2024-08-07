import java.util.Arrays;
import java.util.PriorityQueue;

public class KthSmallestValueInMatrix {
    public static int kthSmallestMinHeap(int[][] matrix, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(); //min-heap
        int n = matrix.length;
        int totalElements = n*n;
        int result = Integer.MIN_VALUE;

        for(int i=0; i<n; i++) { //O(n*n) T.C
            for(int j=0; j<n; j++) {
                pq.offer(matrix[i][j]); //O((n-k)log(n-k)) S.C
                if(pq.size() > (totalElements-k)) {
                    result = Math.max(result, pq.poll());
                }
            }
        }
        return result;
    }

    public int kthSmallestBinarySearch(int[][] matrix, int k) { //O(n*(log(max)) T.C
        int n = matrix.length;
        int low = matrix[0][0]; //we start by taking low and high as
        int high = matrix[n-1][n-1]; //literal values instead of indices

        while(low < high) { //O(log(max)) T.C where max is the max value in matrix, O(1) S.C
            int mid = low + (high - low) / 2;

            if(countLesserThanOrEqual(matrix, mid) < k) {
                //if count of lesser than/equal elements to mid in the matrix is lesser than k
                low = mid+1; //we need to increase space to calculate lesser i.e., move low to mid+1.
            } else { //if more than k elements are found to be lesser than k for current mid
                high = mid; //reduce the count space i.e., move high to mid
            }
        }
        //We can observe that as we have taken literal values instead of indices to form low and mid, and for subsequent
        //calculations, we are just incrementing low = mid+1 instead of the mid+1's index, where there is a possibility
        //that this new low might not be in the matrix itself. But the countLesserThanOrEqual is written such a way that
        //it returns the count of values which are lesser than current mid by searching values in the given matrix only.
        //So, whenever we try to increment the mid-value, the first point where it reaches actual count and low and high
        //become same, is always a value found to be in the matrix.
        return low;
    }

    public int countLesserThanOrEqual(int[][] matrix, int target) { //O(n) T.C
        int n = matrix.length;
        int count = 0;

        int row = n - 1; // Start from the last row
        int col = 0;     // Start from the first column

        while (row >= 0 && col < n) {
            if (matrix[row][col] <= target) {
                // All elements in the current row above this one are less than target
                count += (row + 1);
                col++; // Move right
            } else {
                row--; // Move up
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1,13,16},
                {22,25,27},
                {28,100,110}
        };

        int k = 6;

        System.out.println("kth smallest value in the matrix: " + Arrays.deepToString(matrix) +
                " where k = " + k + " is: " + kthSmallestMinHeap(matrix, k));
    }
}
