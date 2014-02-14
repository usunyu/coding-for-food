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



