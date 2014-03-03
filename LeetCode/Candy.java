class Solution {
    public int candy(int[] ratings) {
        if(ratings.length == 0) return 0;
        // find min rating
        int min = 0;
        for(int i = 1; i < ratings.length; i++) {
            if(ratings[i] < ratings[min]) min = i;
        }
        // store candy info
        int[] candy = new int[ratings.length];
        // set min candy to 1
        candy[min] = 1;
        // expand from min to right
        for(int i = min + 1; i < ratings.length; i++) {
            if(ratings[i] > ratings[i - 1]) {
                candy[i] = candy[i - 1] + 1;
            }
            else {
                candy[i] = 1;
                if(candy[i - 1] == 1) {  // raise up left
                    int left = i;
                    while(left > 0 && candy[left] == candy[left - 1] && ratings[left] < ratings[left - 1]) {
                        candy[--left]++;
                    }
                }
            }
        }
        // expand from min to left
        for(int i = min - 1; i >= 0; i--) {
            if(ratings[i] > ratings[i + 1]) {
                candy[i] = candy[i + 1] + 1;
            }
            else {
                candy[i] = 1;
                if(candy[i + 1] == 1) {  // raise up right
                    int right = i;
                    while(right < ratings.length - 1 && candy[right] == candy[right + 1] && ratings[right] < ratings[right + 1]) {
                        candy[++right]++;
                    }
                }
            }
        }
        // count candy
        int count = 0;
        for(int c : candy) {
            count += c;
        }
        return count;
    }

    /*
        Second Round
    */
    public int candy2(int[] ratings) {
        if(ratings.length == 0) return 0;
        // find the min rating
        int minIndex = 0;
        for(int i = 1; i < ratings.length; i++) {
            if(ratings[i] < ratings[minIndex]) {
                minIndex = i;
            }
        }
        // set min index
        int[] candies = new int[ratings.length];
        candies[minIndex] = 1;
        // scan from min to right
        for(int i = minIndex + 1; i < candies.length; i++) {
            if(ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
            else {
                candies[i] = 1;
                // raise left
                int back = i;
                while(back > 0 && ratings[back] < ratings[back - 1] && candies[back] == candies[back - 1]) {
                    candies[--back]++;
                }
            }
        }
        // scan from min to left
        for(int i = minIndex - 1; i >= 0; i--) {
            if(ratings[i] > ratings[i + 1]) {
                candies[i] = candies[i + 1] + 1;
            }
            else {
                candies[i] = 1;
                // raise right
                int back = i;
                while(back < candies.length - 1 && ratings[back] < ratings[back + 1] && candies[back] == candies[back + 1]) {
                    candies[++back]++;
                }
            }
        }
        // count candies
        int count = 0;
        for(int candy : candies) count += candy;
        return count;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] ratings = {1,2,4,4,3};
        System.out.println(solution.candy2(ratings));
    }
}
