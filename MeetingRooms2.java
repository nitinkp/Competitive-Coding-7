import java.util.Arrays;
import java.util.PriorityQueue;

public class MeetingRooms2 {
    public static int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, (a,b) -> a[0] - b[0]); //sort based on start times, O(nlogn) T.C
        PriorityQueue<Integer> pq = new PriorityQueue<>(); //O(nlogn) T.C w.c for adding and polling

        for(int[] interval : intervals) { //add end times to the min-heap, O(n) S.C w.c
            if(!pq.isEmpty() &&
                    pq.peek() <= interval[0]){ //if the min end time is completing before the incoming start time
                pq.poll(); //we can poll min end time value, as its room is now being occupied by the incoming meeting
            }
            pq.offer(interval[1]); //either way, add all incoming intervals to the pq
        }
        return pq.size(); //whatever end times still remain in the min-heap, is the total count of rooms required
    }

    public static void main(String[] args) {
        int[][] intervals = {
                {10,30},
                {5,15},
                {20,35},
                {30,40}
        };

        System.out.println("The number of meeting rooms required to accommodate meetings: " +
                Arrays.deepToString(intervals) + " is: " + minMeetingRooms(intervals));
    }
}