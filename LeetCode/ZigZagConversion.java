class Solution {
    public String convert(String s, int nRows) {
        if(nRows == 1) return s;
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < nRows; i++) {
            for(int j = i; j < s.length(); j+= (2 * nRows - 2)) {
                builder.append(s.charAt(j));
                if(i != 0 && i != nRows - 1) {
                    int block = j / (2 * nRows - 2) + 1;
                    int k = block * (2 * nRows - 2) - i;
                    if(k < s.length())
                        builder.append(s.charAt(k));
                }
            }
        }
        return builder.toString();
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.convert("PAYPALISHIRING", 4));
    }
}
