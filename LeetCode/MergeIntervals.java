import java.util.*;

class Interval {
    int start;
    int end;
    Interval() { start = 0; end = 0; }
    Interval(int s, int e) { start = s; end = e; }
}

class Solution {
    public void quickSort(ArrayList<Interval> intervals, int start, int end) {
        if(start >= end) {
            return;
        }
        int p = partition(intervals, start, end);
        quickSort(intervals, start, p - 1);
        quickSort(intervals, p, end);
    }
    
    public void swap(ArrayList<Interval> intervals, int n1, int n2) {
        Interval temp = intervals.get(n1);
        intervals.set(n1, intervals.get(n2));
        intervals.set(n2, temp);
    }
    
    public int partition(ArrayList<Interval> intervals, int start, int end) {
        int mid = start + (end - start) / 2;
        int pivot = intervals.get(mid).start;
        while(start <= end) {
            while(intervals.get(start).start < pivot) {
                start++;
            }
            while(intervals.get(end).start > pivot) {
                end--;
            }
            
            if(start <= end) {
            	swap(intervals, start, end);
                start++;
                end--;
            }
        }
        return start;
    }
    
    public void quickSort(ArrayList<Interval> intervals) {
        quickSort(intervals, 0, intervals.size() - 1);
    }

    public void quickSort2(ArrayList<Interval> intervals, int start, int end) {
        if(start >= end) {
            return;
        }
        int p = partition2(intervals, start, end);
        quickSort2(intervals, start, p - 1);
        quickSort2(intervals, p, end);
    }
    
    public int partition2(ArrayList<Interval> intervals, int start, int end) {
        int mid = start + (end - start) / 2;
        int pivot = intervals.get(mid).end;
        while(start <= end) {
            while(intervals.get(start).end < pivot) {
                start++;
            }
            while(intervals.get(end).end > pivot) {
                end--;
            }
            
            if(start <= end) {
            	swap(intervals, start, end);
                start++;
                end--;
            }
        }
        return start;
    }
    
    public void quickSort2(ArrayList<Interval> intervals) {
        quickSort2(intervals, 0, intervals.size() - 1);
    }

    public void mergeSort(ArrayList<Interval> workspce, ArrayList<Interval> intervals, int start, int end) {
    	if(start >= end) {
    		return;
    	}
    	int mid = start + (end - start) / 2;
    	mergeSort(workspce, intervals, start, mid);
    	mergeSort(workspce, intervals, mid + 1, end);
    	merge(workspce, intervals, start, mid, end);
    }

    public void mergeSort2(ArrayList<Interval> workspce, ArrayList<Interval> intervals, int start, int end) {
    	if(start >= end) {
    		return;
    	}
    	int mid = start + (end - start) / 2;
    	mergeSort(workspce, intervals, start, mid);
    	mergeSort(workspce, intervals, mid + 1, end);
    	merge2(workspce, intervals, start, mid, end);
    }

    public void merge(ArrayList<Interval> workspce, ArrayList<Interval> intervals, int start, int mid, int end) {
    	int p1 = start;
    	int p2 = mid + 1;
    	int index = start;
    	while(p1 <= mid && p2 <= end) {
    		if(intervals.get(p1).start <= intervals.get(p2).start) {
    			workspce.set(index, intervals.get(p1));
    			p1++;
    		}
    		else {
    			workspce.set(index, intervals.get(p2));
    			p2++;
    		}
    		index++;
    	}
    	while(p1 <= mid) {
    		workspce.set(index, intervals.get(p1));
    		p1++;
    		index++;
    	}
    	while(p2 <= mid) {
    		workspce.set(index, intervals.get(p2));
    		p2++;
    		index++;
    	}
    	// copy back
    	for(int i = start; i <= end; i++) {
    		intervals.set(i, workspce.get(i));
    	}
    }

    public void merge2(ArrayList<Interval> workspce, ArrayList<Interval> intervals, int start, int mid, int end) {
    	int p1 = start;
    	int p2 = mid + 1;
    	int index = start;
    	while(p1 <= mid && p2 <= end) {
    		if(intervals.get(p1).end <= intervals.get(p2).end) {
    			workspce.set(index, intervals.get(p1));
    			p1++;
    		}
    		else {
    			workspce.set(index, intervals.get(p2));
    			p2++;
    		}
    		index++;
    	}
    	while(p1 <= mid) {
    		workspce.set(index, intervals.get(p1));
    		p1++;
    		index++;
    	}
    	while(p2 <= mid) {
    		workspce.set(index, intervals.get(p2));
    		p2++;
    		index++;
    	}
    	// copy back
    	for(int i = start; i <= end; i++) {
    		intervals.set(i, workspce.get(i));
    	}
    }

    public void mergeSort(ArrayList<Interval> intervals) {
    	ArrayList<Interval> workspce = new ArrayList<Interval>(intervals);
    	mergeSort(workspce, intervals, 0, intervals.size() - 1);
    	intervals = workspce;
    	mergeSort2(workspce, intervals, 0, intervals.size() - 1);
    	intervals = workspce;
    }
    
    // time complexity : O(NlogN)
    // space complexity : O(N)
    public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        if(intervals == null) {
        	return null;
        }
        // quick sort is not stable
        // quickSort2(intervals);
        // quickSort(intervals);
        // we using stable sort
        mergeSort(intervals);

        ArrayList<Interval> mergeList = new ArrayList<Interval>();
        int p = 0;
        while(p < intervals.size()) {
            int start = intervals.get(p).start;
            int end = intervals.get(p).end;
            while(p < intervals.size() - 1 && end >= intervals.get(p + 1).start) {
                p++;
                if(intervals.get(p).end > end) {
                	end = intervals.get(p).end;
                }
            }
            Interval merge = new Interval(start, end);
            mergeList.add(merge);
            p++;
        }
        return mergeList;
    }
}

class Main {
	public static void print(ArrayList<Interval> intervals) {
		for(Interval interval : intervals) {
			System.out.print("[" + interval.start + "," + interval.end + "] ");
		}
		System.out.println();
	}

    public static void main(String[] args) {
        Solution solution = new Solution();
        ArrayList<Interval> intervals = new ArrayList<Interval>();
        // intervals.add(new Interval(2, 6));
        // intervals.add(new Interval(1, 3));
        // intervals.add(new Interval(8, 10));
        // intervals.add(new Interval(15, 18));
        intervals.add(new Interval(2, 3));
        intervals.add(new Interval(4, 5));
        intervals.add(new Interval(6, 7));
        intervals.add(new Interval(8, 9));
        intervals.add(new Interval(1, 10));
        // solution.mergeSort(intervals);
        print(intervals);
        ArrayList<Interval> mergeList = solution.merge(intervals);
        print(mergeList);
    }
}
