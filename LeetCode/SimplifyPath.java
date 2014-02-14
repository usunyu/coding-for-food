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

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.simplifyPath("/a/./b/../../c/"));
    }
}
