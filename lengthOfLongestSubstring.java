import java.util.HashMap;
import java.util.Map;

class Solution {
  public static void main(String[] args) {
    Solution solution = new Solution();
    String testStr = "abcabcbb";
    int lengthOfLongestSubstring = solution.lengthOfLongestSubstring(testStr);
    System.out.println(lengthOfLongestSubstring);
  }

  public int lengthOfLongestSubstring(String str) {
    int result = -1;
    Map<String, Integer> map = new HashMap<String, Integer>();
    char[] chars = str.toCharArray();

    for (int i = 0; i < chars.length; i++) {
      char c = chars[i];
      // map.put(c, 1);
      while (true) {

      }
    }
    return result;
  }
}