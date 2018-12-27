import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

class Solution {
  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    Solution solution = new Solution();

    // // 求字符串不重复的最长值
    // String testStr = " ";
    // int lengthOfLongestSubstring = solution.lengthOfLongestSubstring(testStr);
    // System.out.println(lengthOfLongestSubstring);

    // // 求两个有序数组的中位数
    // int[] nums1 = new int[] { 1, 2 };
    // int[] nums2 = new int[] { -1, 3 };
    // System.out.println(solution.findMedianSortedArrays(nums1, nums2));

    // 求
    // int[] nums = new int[] { -1, 0, 1, 2, -1, -4 };
    // solution.threeSum(nums);

    solution.letterCombinations("");
    System.out.println("耗时：" + (System.currentTimeMillis() - start) + "ms");
  }

  public List<String> letterCombinations(String digits) {
    List<String> result = new ArrayList<>();
    if (digits != null && digits.length() > 0) {
      Map<Character, String> map = new HashMap<>();
      map.put('2', "abc");
      map.put('3', "def");
      map.put('4', "ghi");
      map.put('5', "jkl");
      map.put('6', "mno");
      map.put('7', "pqrs");
      map.put('8', "tuv");
      map.put('9', "wxyz");

      List<String> mapList = new ArrayList<>();
      for (int i = 0; i < digits.length(); i++) {
        char tmp = digits.charAt(i);
        String ch = map.get(tmp);
        if (ch != null) {
          mapList.add(ch);
        } else {
          mapList.add(tmp + "");
        }
      }
      StringBuffer sb = new StringBuffer();
      build(result, mapList, 0, sb);
    }
    return result;
  }

  private void build(List<String> result, List<String> mapList, int index, StringBuffer sb) {
    String mapString = mapList.get(index);
    for (int i = 0; i < mapString.length(); i++) {
      sb.append(mapString.charAt(i));
      if (sb.length() == mapList.size()) {
        // System.out.println(sb.toString());
        result.add(sb.toString());
        sb.deleteCharAt(sb.length() - 1);
      } else {
        build(result, mapList, index + 1, sb);
        sb.deleteCharAt(index);
      }
    }
  }

  public List<List<Integer>> threeSum(int[] nums) {
    // nums = new int[] { -4, -2, 1, -5, -4, -4, 4, -2, 0, 4, 0, -2, 3, 1, -5, 0 };
    // nums = new int[] { 1, 1, 2, -2, -1 };
    Arrays.sort(nums);
    List<List<Integer>> result = new ArrayList<>();
    Set<String> resultCount = new HashSet<>();

    Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    List<Integer> keys = new ArrayList<>();
    for (int i = 0; i < nums.length; i++) {
      int num = nums[i];
      if (map.containsKey(num)) {
        Integer list = map.get(num);
        list++;
        map.put(num, list);
      } else {
        map.put(num, 1);
        keys.add(num);
      }
    }

    for (int i = 0; i < keys.size(); i++) {
      Integer key = keys.get(i);
      Integer value = map.get(key);
      if (value >= 3 && key == 0) {
        String tmp = "0_0_0";
        if (!resultCount.contains(tmp)) {
          List<Integer> needIndexs = new ArrayList<>();
          needIndexs.add(0);
          needIndexs.add(0);
          needIndexs.add(0);
          result.add(needIndexs);
          resultCount.add(tmp);
        }
      }
      for (int j = i + 1; j < keys.size(); j++) {
        Integer subkey = keys.get(j);
        Integer subvalue = map.get(subkey);

        int needInt = -(key + subkey);
        if (map.containsKey(needInt)) {
          if ((key != needInt && subkey != needInt) || (needInt == key && value >= 2)
              || (needInt == subkey && subvalue >= 2)) {
            List<Integer> needIndexs = new ArrayList<>();
            needIndexs.add(key);
            needIndexs.add(subkey);
            needIndexs.add(needInt);
            Collections.sort(needIndexs, new Comparator<Integer>() {
              @Override
              public int compare(Integer lhs, Integer rhs) {
                return lhs > rhs ? -1 : (lhs < rhs) ? 1 : 0;
              }
            });
            String tmp = needIndexs.toString();
            if (!resultCount.contains(tmp)) {
              result.add(needIndexs);
              resultCount.add(tmp);
            }
          }
        }
      }
    }
    return result;
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

  /**
   * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2 请你找出这两个有序数组的中位数。 并且要求算法的时间复杂度为 O(log(m+n))。
   * 你可以假设 nums1 和 nums2 不会同时为空。
   */
  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    int totalLength = nums1.length + nums2.length, centerIndex = 0;
    boolean isTwoNum = false;
    if (totalLength % 2 == 0) {
      isTwoNum = true;
      centerIndex = (totalLength / 2) - 1;
    } else {
      centerIndex = (totalLength + 1) / 2 - 1;
    }
    if (nums1.length == 0) {
      if (isTwoNum) {
        if (nums2.length == 0) {
          return 0.0;
        } else {
          return (nums2[centerIndex] + nums2[centerIndex + 1]) / 2.0;
        }
      } else {
        return nums2[centerIndex];
      }
    } else if (nums2.length == 0) {
      if (isTwoNum) {
        if (nums1.length == 0) {
          return 0.0;
        } else {
          return (nums1[centerIndex] + nums1[centerIndex + 1]) / 2.0;
        }
      } else {
        return nums1[centerIndex];
      }
    } else {

      int index1 = 0, index2 = 0;
      while ((index1 + index2) <= centerIndex) {
        System.out.println(index1 + "|" + index2);
        if (index1 < nums1.length && index2 < nums2.length) {
          if (nums1[index1] <= nums2[index2]) {
            index1++;
          } else {
            index2++;
          }
        } else if (index1 < nums1.length) {
          index1++;
        } else if (index2 < nums2.length) {
          index2++;
        }
      }

      index1--;
      index2--;
      System.out.println(index1 + "|" + index2);

      if (isTwoNum) {
        int next = 0;
        if ((index1 + 1) < nums1.length && (index2 + 1) < nums2.length) {
          next = Math.min(nums1[index1 + 1], nums2[index2 + 1]);
        } else if ((index1 + 1) < nums1.length) {
          next = nums1[index1 + 1];
        } else {
          next = nums2[index2];
        }
        System.out.println(index1 + "|" + index2 + "|" + next);
        if (nums1[index1] > nums2[index2]) {
          return (nums2[index2] + next) / 2.0;
        } else {
          return (nums1[index1] + next) / 2.0;
        }
      } else {
        if (nums1[index1] > nums2[index2]) {
          return nums2[index2];
        } else {
          return nums1[index1];
        }
      }
    }
  }
}