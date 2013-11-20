import java.util.*;

class IPAddress {
    int[] part;
    int currentRestore;
    int currentIndex;
    
    public IPAddress() {
        part = new int[4];
        currentRestore = 0;
        currentIndex = 0;
    }
    
    public IPAddress(IPAddress ipa) {
        part = new int[4];
        for(int i = 0; i <4; i++) {
            part[i] = ipa.part[i];
        }
        this.currentRestore = ipa.currentRestore;
        this.currentIndex = ipa.currentIndex;
    }
}

class Solution {
    public ArrayList<String> restoreIpAddresses(String s) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        ArrayList<String> ipList = new ArrayList<String>();
        if(s == null || s.length() < 4) {
            return ipList;
        }
        IPAddress ipa = new IPAddress();
        Stack<IPAddress> ipStack = new Stack<IPAddress>();
        ipStack.push(ipa);
        while(!ipStack.isEmpty()) {
            ipa = ipStack.pop();
            if(ipa.currentRestore == 4) {
                String ip = ipa.part[0] + "." + ipa.part[1] + "." + ipa.part[2] + "." + ipa.part[3];
                ipList.add(ip);
                continue;
            }
            else if(ipa.currentRestore == 3) {
                if(s.length() - ipa.currentIndex > 3) { // rest is too long
                    continue;
                }
                String sub = s.substring(ipa.currentIndex);
                int part = Integer.parseInt(sub);
                if(part <= 255) {
                    if(sub.charAt(0) == '0' && sub.length() > 1) {
                        continue;
                    }
                    ipa.part[ipa.currentRestore] = part;
                    ipa.currentRestore += 1;
                    ipStack.push(ipa);
                }
            }
            else {
                for(int i = 1; i <= 3 && (ipa.currentIndex + i) < s.length(); i++) {
                    String sub = s.substring(ipa.currentIndex, ipa.currentIndex + i);
                    int part = Integer.parseInt(sub);
                    if(part <= 255) {   // valid
                        if(sub.charAt(0) == '0' && sub.length() > 1) {
                            continue;
                        }
                        IPAddress ipn = new IPAddress(ipa);
                        ipn.currentIndex = ipa.currentIndex + i;
                        ipn.part[ipa.currentRestore] = part;
                        ipn.currentRestore = ipa.currentRestore + 1;
                        ipStack.push(ipn);
                    }
                }
            }
        }
        return ipList;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        ArrayList<String> ipList = solution.restoreIpAddresses("010010");
        for(String ip : ipList) {
            System.out.println(ip);
        }
    }
}
