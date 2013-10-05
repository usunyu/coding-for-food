import java.util.*;

class Interval {
    int start;
    int end;
    Interval() { start = 0; end = 0; }
    Interval(int s, int e) { start = s; end = e; }
}

class Solution {
    public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        ArrayList<Interval> result = new ArrayList<Interval>();
        if(intervals == null || intervals.size() == 0) {
            result.add(newInterval);
            return result;
        }
        int newStart = newInterval.start;
        int newEnd = newInterval.end;
        int i = 0;
        boolean overlap = false;
        boolean hasInsert = false;
        while(i < intervals.size()) {
            Interval current = intervals.get(i);
            if(!overlap && (current.start >= newInterval.start && current.start <= newInterval.end) ||
            (newInterval.start >= current.start && newInterval.start <= current.end)) { // overlap begin
                if(current.start < newStart) {
                    newStart = current.start;
                }
                overlap = true;
            }
            if(!overlap) {
                if(!hasInsert && newEnd < current.start) {   // hasn't been insert
                    hasInsert = true;
                    result.add(newInterval);
                }
                result.add(current);
                if(!hasInsert && newStart > current.end && i == intervals.size() - 1) {   // hasn't been insert
                    hasInsert = true;
                    result.add(newInterval);
                }
            }
            if(overlap && newEnd < current.start) { // overlap end
                Interval insert = new Interval(newStart, newEnd);
                result.add(insert);
                hasInsert = true;
                result.add(current);    // also need add current, or we will miss it
                overlap = false;
            }
            if(overlap && newEnd < current.end && newEnd >= current.start) { // overlap end
                newEnd = current.end;
                Interval insert = new Interval(newStart, newEnd);
                result.add(insert);
                hasInsert = true;
                overlap = false;
            }
            if(overlap) {   // start count new end
                if(current.end > newEnd) {
                    newEnd = current.end;
                }
            }
            i++;
        }
        if(overlap && !hasInsert) {
            Interval insert = new Interval(newStart, newEnd);
            result.add(insert);
            hasInsert = true;
            overlap = false;
        }
        return result;
    }
}

class Main {
    public static void print(ArrayList<Interval> intervals) {
        for(Interval interval : intervals) {
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        }
        System.out.println();
    }

    public static void print(Interval interval) {
        System.out.println("Insert: [" + interval.start + "," + interval.end + "] ");
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        ArrayList<Interval> intervals = new ArrayList<Interval>();
        intervals.add(new Interval(0, 2));
        intervals.add(new Interval(3, 5));
        intervals.add(new Interval(6, 7));
        intervals.add(new Interval(8, 10));
        intervals.add(new Interval(12, 16));
        // intervals.add(new Interval(1, 5));
        // intervals.add(new Interval(6, 8));
        print(intervals);
        Interval newInterval = new Interval(4, 6);
        print(newInterval);
        intervals = solution.insert(intervals, newInterval);
        print(intervals);
    }
}


