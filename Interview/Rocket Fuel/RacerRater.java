/*
Suppose you are a fan of auto-racing and want to figure out which drivers are likely to perform well in an upcoming race.
Luckily you have access to a log of the times that each racer started and finished their test race the day before.
The particular rating algorithm you have chosen is to assign each racer R a score that equals the number of other racers who both started after 
R started and also finished before R finished.
Note that a lower score generally suggests that the racer is faster, and this rating algorithm keeps from penalizing fast racers who have slow times 
simply because they are stuck behind a crash or slow racer.
Additionally, this rating algorithm does not reward fast racers who pass tons of slow racers in comparison to fast racers who race when there are 
not many slow racers on the track to pass 
(compare this with rating a racer based on the net number of passes).

More formally, you want to write a program that will read the test race log from standard input. 
The first line of the log contains a single integer n from 0 to 70,000 that represents the number of racers in the log. 
The next n lines of the test race log have the following format:

racerId startTime endTime

where racerId is an integer in the range [0,10^9] and startTime and endTime are both integers such that 0 <= startTime < endTime <= 10^18. 
Each racerId will be distinct. Also, the collection of all start and end times will not contain any duplicate elements.

Given such an input, you should print output in the following format:

racerId score

where score is the score as defined above for racer racerId. The output lines should be sorted in ascending order ofscore with ties broken by 
sorting by racerId, also in ascending order. This can be accomplished with a simple sort at the end.

Directions:
Please code this problem in Java, C, or C++. Your solution should run in less than O(N^2) on all inputs.
Hint: The naive brute force solution is too slow to run within the time limit. You will need to think of a faster solution. 
Specifically, we are looking for a solution that is guaranteed to be less than O(N^2) on all inputs. One possible way to accomplish this 
(there are several other acceptable methods) is to use a data structure with K buckets (e.g., K = 300), 
each of which is initially empty and is defined by two times. Each bucket  will eventually contain racers whose start time falls between the two times. 
The bucket boundaries should be chosen such that they ultimately will contain the same number of racers. Then iterate through the racers in end time 
order and, as you iterate over each racer, build up this bucketed data structure in such a way that you can use it to quickly count the number of 
racers that finished before him but started after him.

What We Are Looking For:
For this problem, we simply want to see that you can implement the algorithm correctly, without particular regard to principles of object 
orientation or modularity.  Do give us at least minimal documentation to help us understand what you are trying to accomplish in certain key 
places of the algorithm.

Sample Testcases 
input:
5
2 100 200
3 110 190
4 105 145
1 90 150
5 102 198
output:
3 0
4 0
1 1
5 2
2 3

Note in the above example that racer 3 has a score of 0 because no one starts after racer 3 
(a drawback to this scoring system is the last racer always has a score of 0). Racer 4 also has a score of 0 because the only racer 
who starts after racer 4's start time (racer 3) has a later finish time. Racer 3 is listed ahead of racer 4 despite having a slower 
time because racer 3's id is lower. At the other end, racer 2 has a score of 3 because racers 3, 4, and 5 start after racer 2 and 
finish before racer 2 finishes.
*/

/* Enter your code here. Read input from STDIN. Print output to STDOUT */
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

class Racer implements Comparable<Racer> {
    int racerId;
    long startTime;
    long endTime;
    int score;

    public Racer(int racerId, long startTime, long endTime) {
        this.racerId = racerId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public int compareTo(Racer r) {
        if(score == r.score) {
            return racerId - r.racerId;
        }
        else {
            return score - r.score;
        }
    }
}

class Bucket {
    long start;
    long end;
    LinkedList<Racer> racers;
    
    public Bucket(long start, long end) {
        this.start = start;
        this.end = end;
        racers = new LinkedList<Racer>();
    }
}

public class Solution {
    static int BUCKET_NUMBER = 100;
    static int BUCKET_IS_NEED = 2000;
    
    private static void insert(ArrayList<Racer> racerList, Racer racer) {
        if(racerList.size() == 0) {
            racerList.add(racer);
        }
        else {
            int left = 0, right = racerList.size() - 1;
            while(left < right) {
                int mid = left + (right - left) / 2;
                long startTime = racerList.get(mid).startTime;
                if(racer.startTime < startTime) {
                    right = mid - 1;
                }
                else if(racer.startTime > startTime) {
                    left = mid + 1;
                }
            }
            // check valid index
            if(right < 0) right = 0;
            if(right > racerList.size() - 1) right = racerList.size() - 1;
            int index;
            if(racerList.get(right).startTime >= racer.startTime) index = right;
            else index = right + 1;
            racerList.add(index, racer);
        }
    }
    
    private static void insert2(ArrayList<Racer> racerList, Racer racer) {
        if(racerList.size() == 0) {
            racerList.add(racer);
        }
        else {
            int left = 0, right = racerList.size() - 1;
            while(left < right) {
                int mid = left + (right - left) / 2;
                long endTime = racerList.get(mid).endTime;
                if(racer.endTime < endTime) {
                    right = mid - 1;
                }
                else if(racer.endTime > endTime) {
                    left = mid + 1;
                }
            }
            // check valid index
            if(right < 0) right = 0;
            if(right > racerList.size() - 1) right = racerList.size() - 1;
            int index;
            if(racerList.get(right).endTime >= racer.endTime) index = right;
            else index = right + 1;
            racerList.add(index, racer);
        }
    }
    
    public static void main(String[] args) throws IOException {
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //String line = br.readLine();
        //int n = Integer.parseInt(line);
        //ArrayList<Racer> racerList = new ArrayList<Racer>(n);
        // input, runtime: O(n)
        //while ((line = br.readLine()) != null) {
        //  String[] nodes = line.split(" ");
        //  Racer racer = new Racer(Integer.parseInt(nodes[0]), Integer.parseInt(nodes[1]), Integer.parseInt(nodes[2]));
        //  racerList.add(racer);
        //}
        File file = new File("input004.txt");
        Scanner sc = new Scanner(file);
        int n = sc.nextInt();
        String line = sc.nextLine();
        // use racerList to hold racer data, and order by startTime
        ArrayList<Racer> racerList = new ArrayList<Racer>(n);
        // order by endTime
        ArrayList<Racer> racerList2 = new ArrayList<Racer>(n);
        while(sc.hasNext()) {
            line = sc.nextLine();
            String[] nodes = line.split(" ");
            Racer racer = new Racer(Integer.parseInt(nodes[0]), Long.parseLong(nodes[1]), Long.parseLong(nodes[2]));
            // using binary search to insert
            insert(racerList, racer);
            if(n >= BUCKET_IS_NEED) {
                insert2(racerList2, racer);
            }
        }
        sc.close();
        if(n >= BUCKET_IS_NEED) {
            // build bucket
            ArrayList<Bucket> bucketList = new ArrayList<Bucket>(BUCKET_NUMBER);
            int bucketSize = n / BUCKET_NUMBER;
            for(int i = 0; i < n; i += bucketSize) {
                int j = i + bucketSize;
                if(j > n) j = n;
                Bucket bucket = new Bucket(racerList.get(i).startTime, racerList.get(j - 1).startTime);
                bucketList.add(bucket);
            }
            for(int i = 0; i < n; i++) {
                Racer racer = racerList2.get(i);
                // find right place to insert
                int score = 0;
                for(int j = 0; j < BUCKET_NUMBER; j++) {
                    Bucket bucket = bucketList.get(j);
                    if(racer.startTime >= bucket.start) {
                        // count score
                        for(Racer r : bucket.racers) {
                            if(r.startTime > racer.startTime) {
                                score++;
                            }
                        }
                        bucket.racers.add(racer);
                        break;
                    }
                    else {
                        score += bucket.racers.size();
                    }
                }
                racer.score = score;
            }
        }
        else {
            // calculate score, runtime O(n^2)
            for(int i = 0; i < n; i++) {
                int count = 0;
                Racer racer = racerList.get(i);
                for(int j = i + 1; j < n; j++) {
                    if(racerList.get(j).endTime < racer.endTime) {
                        count++;
                    }
                    if(racerList.get(j).startTime > racer.endTime) {
                        break;
                    }
                }
                racer.score = count;
            }
        }
        // sort the result, runtime O(nlogn)
        Collections.sort(racerList);
        // output, runtime O(n)
        PrintWriter writer = new PrintWriter("my_output.txt", "UTF-8");
        for(Racer r : racerList) {
            //System.out.println(r.racerId + " " + r.score);
            writer.println(r.racerId + " " + r.score);
        }
        writer.close();
    }
}



