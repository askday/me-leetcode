import java.util.HashSet;
import java.util.Set;

class Solution {
  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    Solution solution = new Solution();
    String testStr = " ";
    int lengthOfLongestSubstring = solution.lengthOfLongestSubstring(testStr);
    System.out.println(lengthOfLongestSubstring);
    System.out.println(System.currentTimeMillis() - start);
  }

  public int lengthOfLongestSubstring(String str) {
    int result = -1;
    Set<Character> set = new HashSet<Character>();
    for (int i = 0; i < str.length(); i++) {
      set.add(str.charAt(i));
      for (int j = i + 1; j < str.length(); j++) {
        if (set.contains(str.charAt(j))) {
          result = Math.max(result, set.size());
          set.clear();
          break;
        }
        set.add(str.charAt(j));
      }
    }
    return Math.max(result, set.size());
  }
}