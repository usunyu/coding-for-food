/*
Given a digit string, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below.


Input:Digit string "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
*/

import java.util.*;

class Solution {
    public ArrayList<Character> getLetters(char digit) {
        ArrayList<Character> letters = new ArrayList<Character>();
        switch(digit) {
            case '2':
                letters.add('a'); letters.add('b'); letters.add('c');
                break;
            case '3':
                letters.add('d'); letters.add('e'); letters.add('f');
                break;
            case '4':
                letters.add('g'); letters.add('h'); letters.add('i');
                break;
            case '5':
                letters.add('j'); letters.add('k'); letters.add('l');
                break;
            case '6':
                letters.add('m'); letters.add('n'); letters.add('o');
                break;
            case '7':
                letters.add('p'); letters.add('q'); letters.add('r'); letters.add('s');
                break;
            case '8':
                letters.add('t'); letters.add('u'); letters.add('v');
                break;
            case '9':
                letters.add('w'); letters.add('x'); letters.add('y'); letters.add('z');
                break;
            default:
                break;
        }
        return letters;
    }
    
    public void letterCombinations(String digits, String letters, int index, ArrayList<String> letterList) {
        if(index == digits.length()) {
            letterList.add(letters);
            return;
        }
        char digit = digits.charAt(index);
        ArrayList<Character> nextLetterList = getLetters(digit);
        for(char nextLetter : nextLetterList) {
            String nextLetters = letters + nextLetter;
            letterCombinations(digits, nextLetters, index + 1, letterList);
        }
    }
    
    public ArrayList<String> letterCombinations(String digits) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        ArrayList<String> letterList = new ArrayList<String>();
        if(digits != null) {
            letterCombinations(digits, "", 0, letterList);
        }
        return letterList;
    }

    /*
        Second Round
    */
    public ArrayList<String> letterCombinations2(String digits) {
        ArrayList<String> result = new ArrayList<String>();
        if(digits == null) return result;
        LinkedList<StringBuilder> queue = new LinkedList<StringBuilder>();
        // initial
        queue.add(new StringBuilder());
        // bfs
        for(int i = 0; i < digits.length(); i++) {
            LinkedList<StringBuilder> tmp = new LinkedList<StringBuilder>();
            // for every path so far
            for(StringBuilder sb : queue) {
                // for every possibile letters
                for(char letter : getLetters(digits.charAt(i))) {
                    StringBuilder nsb = new StringBuilder(sb);
                    nsb.append(letter);
                    tmp.add(nsb);
                }
            }
            queue = tmp;
        }
        // result
        for(StringBuilder sb : queue) {
            result.add(sb.toString());
        }
        return result;
    }
}

class Main {
    public static void print(ArrayList<String> letterList) {
        for(String letters : letterList) {
            System.out.println(letters);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        print(solution.letterCombinations2(""));
    }
}
