/*
Given an absolute path for a file (Unix-style), simplify it.

For example,
path = "/home/", => "/home"
path = "/a/./b/../../c/", => "/c"
click to show corner cases.

Corner Cases:
Did you consider the case where path = "/../"?
In this case, you should return "/".
Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
In this case, you should ignore redundant slashes and return "/home/foo".
*/

import java.util.*;

class Solution {
    public String simplifyPath(String path) {
        LinkedList<String> list = new LinkedList<String>();
        int i = 0;
        while(i < path.length()) {
            StringBuilder builder = new StringBuilder();
            while(i < path.length() && path.charAt(i) != '/') {
                builder.append(path.charAt(i));
                i++;
            }
            if(builder.length() > 0) {
                String str = builder.toString();
                if(str.equals("..") || str.equals(".")) {
                    if(!list.isEmpty()) list.removeLast();
                    if(str.equals("..") && !list.isEmpty()) list.removeLast();
                }
                else {
                    list.add(str);
                }
            }
            if(list.isEmpty() || !list.peekLast().equals("/")) {
                list.add("/");   
            }
            i++;
        }
        if(list.size() > 1) list.removeLast();
        StringBuilder spath = new StringBuilder();
        for(String str : list) {
            spath.append(str);
        }
        return spath.toString();
    }
}

/*
    Second Round
*/
class Solution2 {
    public String simplifyPath(String path) {
        LinkedList<Character> simple = new LinkedList<Character>();
        int i = 0;
        while(i < path.length()) {
            char ch = path.charAt(i);
            if(ch == '/') {
                if(simple.size() == 0 || simple.peekLast() != '/')
                    simple.add(ch);
            }
            else if(ch == '.') {
                int count = 0;
                while(i < path.length() && path.charAt(i) == '.') {
                    i++;
                    count++;
                }
                if(count == 1 && (i == path.length() || path.charAt(i) == '/')) {
                    if(simple.size() > 0 && simple.peekLast() == '/') {
                        simple.removeLast();
                    }
                }
                else if(count == 2 && (i == path.length() || path.charAt(i) == '/')) {
                    if(simple.peekLast() == '/') {
                        simple.removeLast();
                        while(simple.size() > 0 && simple.peekLast() != '/') {
                            simple.removeLast();
                        }
                    }
                }
                else {
                    while(count > 0) {
                        simple.add('.');
                        count--;
                    }
                }
                i--;
            }
            else simple.add(ch);
            i++;
        }
        if(simple.size() > 0 && simple.peekLast() == '/')
            simple.removeLast();
        if(simple.size() == 0)
            simple.add('/');
        StringBuilder ret = new StringBuilder();
        for(char c : simple) ret.append(c);
        return ret.toString();
    }
}

class Main {
    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        System.out.println(solution.simplifyPath("/a/./b/../../c/"));
    }
}
