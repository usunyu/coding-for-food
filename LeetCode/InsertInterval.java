/*
Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

Example 1:
Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].

Example 2:
Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].

This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
*/

import java.util.*;
import LCLibrary.*;

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

    public ArrayList<Interval> insert2(ArrayList<Interval> intervals, Interval newInterval) {
        ArrayList<Interval> result = new ArrayList<Interval>();
        if(intervals == null || intervals.size() == 0) {
            result.add(newInterval);
            return result;
        }
        int i = 0;
        Interval current;
        // insert before
        while(i < intervals.size() && newInterval.start > (current = intervals.get(i)).end) {
            result.add(current);
            i++;
        }
        // overlap
        while(i < intervals.size() && newInterval.end >= (current = intervals.get(i)).start) {
            newInterval.start = Math.min(newInterval.start, current.start);
            newInterval.end = Math.max(newInterval.end, current.end);
            i++;
        }
        result.add(newInterval);
        // insert end
        while(i < intervals.size()) {
            current = intervals.get(i);
            result.add(current);
            i++;
        }
        return result;
    }

    /*
        Second Round
    */
    public boolean isOverlap(Interval i1, Interval i2) {
        if(i1.start >= i2.start && i1.start <= i2.end
            || i1.end >= i2.start && i1.end <= i2.end
            || i1.start <= i2.start && i1.end >= i2.end
            || i1.start >= i2.start && i1.end <= i2.end)
            return true;
        else
            return false;
    }
    
    public ArrayList<Interval> insert3(ArrayList<Interval> intervals, Interval newInterval) {
        ArrayList<Interval> result = new ArrayList<Interval>();
        boolean inserted = false;
        for(int i = 0; i < intervals.size(); i++) {
            Interval current = intervals.get(i);
            // check if current is overlap
            if(isOverlap(current, newInterval)) {
                // merge to new
                newInterval.start = Math.min(newInterval.start, current.start);
                newInterval.end = Math.max(newInterval.end, current.end);
            }
            else {
                if(newInterval.end < current.start && !inserted) {
                    result.add(newInterval);
                    inserted = true;
                }
                result.add(current);
            }
        }
        if(!inserted) result.add(newInterval);
        return result;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        ArrayList<Interval> intervals = Input.buildExampleIntervals();
        Output.printIntervals(intervals);
        Interval newInterval = new Interval(4, 9);
        System.out.println("Insert: " + newInterval);
        intervals = solution.insert3(intervals, newInterval);
        Output.printIntervals(intervals);
    }
}


