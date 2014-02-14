
class Solution {
    // time complexity : O(N)
    // space complexity : O(N)
    public String addBinary(String a, String b) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        if((a == null || a.length() == 0) && (b == null || b.length() == 0)) {
            return null;
        }
        if(a == null || a.length() == 0) {
            return b;
        }
        if(b == null || b.length() == 0) {
            return a;
        }

        StringBuffer strBuff = new StringBuffer();
        int aItr = a.length() - 1;
        int bItr = b.length() - 1;
        int carry = 0;
        while(aItr >= 0 && bItr >= 0) {
            int aValue = a.charAt(aItr) - '0';
            int bValue = b.charAt(bItr) - '0';
            int add = aValue + bValue + carry;
            carry = add / 2;
            add = add % 2;
            strBuff.append(add);
            aItr--;
            bItr--;
        }
        
        while(aItr >= 0) {
            int aValue = a.charAt(aItr) - '0';
            int add = aValue + carry;
            carry = add / 2;
            add = add % 2;
            strBuff.append(add);
            aItr--;
        }
        
        while(bItr >= 0) {
            int bValue = b.charAt(bItr) - '0';
            int add = bValue + carry;
            carry = add / 2;
            add = add % 2;
            strBuff.append(add);
            bItr--;
        }
        
        if(carry > 0) {
            strBuff.append(carry);
        }
        
        strBuff.reverse();
        
        return strBuff.toString();
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String a = "1";
        String b = "1";
        System.out.println(solution.addBinary(a, b));
    }
}