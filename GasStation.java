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
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] gas = {5, 10, 3};
        int[] cost = {6, 5, 1};
        System.out.println(solution.canCompleteCircuit(gas, cost));
    }
}