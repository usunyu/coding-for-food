import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;

class Solution {
    private boolean isNext(String str1, String str2) {
        if(str1.equals(str2)) return false;
        int count = 0;
        for(int i = 0; i < str1.length(); i++) {
            if(str1.charAt(i) != str2.charAt(i)) count++;
            if(count >= 2) return false;
        }
        return true;
    }
    
    private void findLadders(String current, String end, ArrayList<String> path, ArrayList<ArrayList<String>> ladders, HashSet<String> available, HashMap<String, ArrayList<String>> wordNexts, HashMap<String, Integer> cache) {
        // skip longer path
        if(cache.containsKey(current) && cache.get(current) < (path.size() + 1)) return;
        if(!ladders.isEmpty() && ladders.get(0).size() < path.size() + 1) return;
        cache.put(current, path.size() + 1);
        // find end case
        if(current.equals(end)) {
            // no path has been found
            if(ladders.isEmpty()) {
                ArrayList<String> pathNew = new ArrayList<String>(path);
                pathNew.add(current);
                ladders.add(pathNew);
            }
            else {
                // current found path is shorter
                if(ladders.get(0).size() > (path.size() + 1)) {
                    ladders.clear();
                    ArrayList<String> pathNew = new ArrayList<String>(path);
                    pathNew.add(current);
                    ladders.add(pathNew);
                } // current found path is equals to previous
                else if(ladders.get(0).size() == (path.size() + 1)) {
                    ArrayList<String> pathNew = new ArrayList<String>(path);
                    pathNew.add(current);
                    ladders.add(pathNew);
                }
            }
            return;
        }
        // no available case
        if(available.isEmpty()) return;
        // continue search case
        path.add(current);
        for(String str : wordNexts.get(current)) {
            // word is available
            if(available.contains(str)) {
                available.remove(str);
                findLadders(str, end, path, ladders, available, wordNexts, cache);
                available.add(str);
            }
        }
        path.remove(path.size() - 1);
    }
    
    // Time Limit Exceeded
    public ArrayList<ArrayList<String>> findLadders(String start, String end, HashSet<String> dict) {
        // prepare process
        HashMap<String, ArrayList<String>> wordNexts = new HashMap<String, ArrayList<String>>();
        dict.add(start); dict.add(end);
        // O(n^2 * m)
        for(String str1 : dict) {
            ArrayList<String> list = new ArrayList<String>();
            for(String str2 : dict)
                if(isNext(str1, str2)) list.add(str2);
            wordNexts.put(str1, list);
        }
        // TODO: BFS to find minimum length
        
        ArrayList<ArrayList<String>> ladders = new ArrayList<ArrayList<String>>();
        // O(n^2)
        dict.remove(start);
        // DFS to find paths
        findLadders(start, end, new ArrayList<String>(), ladders, dict, wordNexts, new HashMap<String, Integer>());
        return ladders;
    }
}

class Main {
    public static void print(ArrayList<ArrayList<String>> ladders) {
        for(ArrayList<String> list : ladders) {
            System.out.println(list);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] strs = {"kid","tag","pup","ail","tun","woo","erg","luz","brr","gay","sip","kay","per","val","mes","ohs","now","boa","cet","pal","bar","die","war","hay","eco","pub","lob","rue","fry","lit","rex","jan","cot","bid","ali","pay","col","gum","ger","row","won","dan","rum","fad","tut","sag","yip","sui","ark","has","zip","fez","own","ump","dis","ads","max","jaw","out","btu","ana","gap","cry","led","abe","box","ore","pig","fie","toy","fat","cal","lie","noh","sew","ono","tam","flu","mgm","ply","awe","pry","tit","tie","yet","too","tax","jim","san","pan","map","ski","ova","wed","non","wac","nut","why","bye","lye","oct","old","fin","feb","chi","sap","owl","log","tod","dot","bow","fob","for","joe","ivy","fan","age","fax","hip","jib","mel","hus","sob","ifs","tab","ara","dab","jag","jar","arm","lot","tom","sax","tex","yum","pei","wen","wry","ire","irk","far","mew","wit","doe","gas","rte","ian","pot","ask","wag","hag","amy","nag","ron","soy","gin","don","tug","fay","vic","boo","nam","ave","buy","sop","but","orb","fen","paw","his","sub","bob","yea","oft","inn","rod","yam","pew","web","hod","hun","gyp","wei","wis","rob","gad","pie","mon","dog","bib","rub","ere","dig","era","cat","fox","bee","mod","day","apr","vie","nev","jam","pam","new","aye","ani","and","ibm","yap","can","pyx","tar","kin","fog","hum","pip","cup","dye","lyx","jog","nun","par","wan","fey","bus","oak","bad","ats","set","qom","vat","eat","pus","rev","axe","ion","six","ila","lao","mom","mas","pro","few","opt","poe","art","ash","oar","cap","lop","may","shy","rid","bat","sum","rim","fee","bmw","sky","maj","hue","thy","ava","rap","den","fla","auk","cox","ibo","hey","saw","vim","sec","ltd","you","its","tat","dew","eva","tog","ram","let","see","zit","maw","nix","ate","gig","rep","owe","ind","hog","eve","sam","zoo","any","dow","cod","bed","vet","ham","sis","hex","via","fir","nod","mao","aug","mum","hoe","bah","hal","keg","hew","zed","tow","gog","ass","dem","who","bet","gos","son","ear","spy","kit","boy","due","sen","oaf","mix","hep","fur","ada","bin","nil","mia","ewe","hit","fix","sad","rib","eye","hop","haw","wax","mid","tad","ken","wad","rye","pap","bog","gut","ito","woe","our","ado","sin","mad","ray","hon","roy","dip","hen","iva","lug","asp","hui","yak","bay","poi","yep","bun","try","lad","elm","nat","wyo","gym","dug","toe","dee","wig","sly","rip","geo","cog","pas","zen","odd","nan","lay","pod","fit","hem","joy","bum","rio","yon","dec","leg","put","sue","dim","pet","yaw","nub","bit","bur","sid","sun","oil","red","doc","moe","caw","eel","dix","cub","end","gem","off","yew","hug","pop","tub","sgt","lid","pun","ton","sol","din","yup","jab","pea","bug","gag","mil","jig","hub","low","did","tin","get","gte","sox","lei","mig","fig","lon","use","ban","flo","nov","jut","bag","mir","sty","lap","two","ins","con","ant","net","tux","ode","stu","mug","cad","nap","gun","fop","tot","sow","sal","sic","ted","wot","del","imp","cob","way","ann","tan","mci","job","wet","ism","err","him","all","pad","hah","hie","aim","ike","jed","ego","mac","baa","min","com","ill","was","cab","ago","ina","big","ilk","gal","tap","duh","ola","ran","lab","top","gob","hot","ora","tia","kip","han","met","hut","she","sac","fed","goo","tee","ell","not","act","gil","rut","ala","ape","rig","cid","god","duo","lin","aid","gel","awl","lag","elf","liz","ref","aha","fib","oho","tho","her","nor","ace","adz","fun","ned","coo","win","tao","coy","van","man","pit","guy","foe","hid","mai","sup","jay","hob","mow","jot","are","pol","arc","lax","aft","alb","len","air","pug","pox","vow","got","meg","zoe","amp","ale","bud","gee","pin","dun","pat","ten","mob"};
        HashSet<String> dict = new HashSet<String>();
        for(String str : strs) dict.add(str);
        print(solution.findLadders("cet", "ism", dict));
    }
}



