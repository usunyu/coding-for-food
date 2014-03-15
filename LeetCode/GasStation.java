/*
There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). 
You begin the journey with an empty tank at one of the gas stations.

Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.

Note:
The solution is guaranteed to be unique.
*/

class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int length = 0, total = 0, start = 0;
        while(start < gas.length) {
            while(total >= 0 && length <= gas.length) {
                int index = start + length;
                if(index >= gas.length) {
                    index -= gas.length;
                }
                total += (gas[index] - cost[index]);
                length++;
            }
            // travel around the circuit once
            if(length == gas.length + 1) return start;
            // shift
            total -= (gas[start] - cost[start]);
            length--;
            start++;
        }
        // cannot travel around the circuit
        return -1;
    }
    /*
        Second Round
    */
    public int canCompleteCircuit2(int[] gas, int[] cost) {
        int start = 0, total = 0, length = 0;
        while(start < gas.length) {
            int i = start + length;
            while(total >= 0 && length <= gas.length) {
                // circular
                i = (i >= gas.length ? i - gas.length : i);
                length++;
                total += (gas[i] - cost[i]);
                i++;
            }
            if(length == gas.length + 1) return start;
            // back
            total += (cost[start] - gas[start]);
            length--;
            // try next one
            start++;
        }
        return -1;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] gas = {5, 10, 3};
        int[] cost = {6, 5, 1};
        System.out.println(solution.canCompleteCircuit2(gas, cost));
    }
}