/*
 * LeetCode Problem 3635: Smallest Divisible Digit Product II
 * Problem Number: 3635
 * Difficulty: Hard
 * Link: https://leetcode.com/problems/smallest-divisible-digit-product-ii/
 *
 * You are given a string num which represents a positive integer, and an integer
 * t.
 *
 * A number is called zero-free if none of its digits are 0.
 *
 * Return a string representing the smallest zero-free number greater than or equal
 * to num such that the product of its digits is divisible by t. If no such number
 * exists, return "-1".
 *
 *
 *
 * Example 1:
 *
 * Input: num = "1234", t = 256
 *
 * Output: "1488"
 *
 * Explanation:
 *
 * The smallest zero-free number that is greater than 1234 and has the product of
 * its digits divisible by 256 is 1488, with the product of its digits equal to
 * 256.
 *
 * Example 2:
 *
 * Input: num = "12355", t = 50
 *
 * Output: "12355"
 *
 * Explanation:
 *
 * 12355 is already zero-free and has the product of its digits divisible by 50,
 * with the product of its digits equal to 150.
 *
 * Example 3:
 *
 * Input: num = "11111", t = 26
 *
 * Output: "-1"
 *
 * Explanation:
 *
 * No number greater than 11111 has the product of its digits divisible by 26.
 *
 *
 *
 * Constraints:
 *
 * 	2 <= num.length <= 2 * 105
 * 	num consists only of digits in the range ['0', '9'].
 * 	num does not contain leading zeros.
 * 	1 <= t <= 1014
 *
 * Example 1:
 * Input: num = "1234", t = 256
 * Output: "1488"
 *
 * Example 2:
 * Input: num = "12355", t = 50
 * Output: "12355"
 *
 * Example 3:
 * Input: num = "11111", t = 26
 * Output: "-1"
 *
 * Constraints:
 * - 2 <= num.length <= 2 * 105
 * - num consists only of digits in the range ['0', '9'].
 * - num does not contain leading zeros.
 * - 1 <= t <= 1014
 *
 * Topics: Math, String, Backtracking, Greedy, Number Theory
 * Time Complexity: See solution
 * Space Complexity: O(1) to O(n)
 */

class Solution {
    static Map<Integer, Map<Integer, Integer>> kFactorCounts = new HashMap<>();
    static {
        kFactorCounts.put(0, mapOf());
        kFactorCounts.put(1, mapOf());
        kFactorCounts.put(2, mapOf(2, 1));
        kFactorCounts.put(3, mapOf(3, 1));
        kFactorCounts.put(4, mapOf(2, 2));
        kFactorCounts.put(5, mapOf(5, 1));
        kFactorCounts.put(6, mapOf(2, 1, 3, 1));
        kFactorCounts.put(7, mapOf(7, 1));
        kFactorCounts.put(8, mapOf(2, 3));
        kFactorCounts.put(9, mapOf(3, 2));
    }
    static Map<Integer, Integer> mapOf(int... kv) {
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < kv.length; i += 2) m.put(kv[i], kv[i + 1]);
        return m;
    }
    public String smallestNumber(String num, long t) {
        Object[] pc = getPrimeCount(t);
        Map<Integer, Integer> primeCount = (Map<Integer, Integer>) pc[0];
        boolean isDivisible = (Boolean) pc[1];
        if (!isDivisible) return "-1";
        Map<Integer, Integer> factorCount = getFactorCount(primeCount);
        if (sumValues(factorCount) > num.length()) {
            return construct(factorCount);
        }
        Map<Integer, Integer> primeCountPrefix = getPrimeCountFromString(num);
        int firstZeroIndex = num.indexOf('0');
        if (firstZeroIndex == -1) {
            firstZeroIndex = num.length();
            if (isSubset(primeCount, primeCountPrefix)) {
                return num;
            }
        }
        for (int i = num.length() - 1; i >= 0; i--) {
            int d = num.charAt(i) - '0';
            primeCountPrefix = subtract(primeCountPrefix, kFactorCounts.get(d));
            int spaceAfterThisDigit = num.length() - 1 - i;
            if (i > firstZeroIndex) continue;

            for (int biggerDigit = d + 1; biggerDigit < 10; biggerDigit++) {
                Map<Integer, Integer> factorsAfterReplacement = getFactorCount(
                        subtract(subtract(primeCount, primeCountPrefix), kFactorCounts.get(biggerDigit))
                );
                if (sumValues(factorsAfterReplacement) <= spaceAfterThisDigit) {
                    int fillOnes = spaceAfterThisDigit - sumValues(factorsAfterReplacement);
                    StringBuilder sb = new StringBuilder();
                    sb.append(num, 0, i);
                    sb.append(biggerDigit);
                    for (int k = 0; k < fillOnes; k++) sb.append('1');
                    sb.append(construct(factorsAfterReplacement));
                    return sb.toString();
                }
            }
        }
        Map<Integer, Integer> factorsAfterExtension = getFactorCount(primeCount);
        int onesCount = num.length() + 1 - sumValues(factorsAfterExtension);
        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < onesCount; k++) sb.append('1');
        sb.append(construct(factorsAfterExtension));
        return sb.toString();
    }
    static Object[] getPrimeCount(long t) {
        Map<Integer, Integer> count = new HashMap<>();
        count.put(2, 0); count.put(3, 0); count.put(5, 0); count.put(7, 0);
        int[] primes = {2, 3, 5, 7};
        for (int prime : primes) {
            while (t % prime == 0) {
                t /= prime;
                count.put(prime, count.get(prime) + 1);
            }
        }
        return new Object[]{count, t == 1};
    }
    static Map<Integer, Integer> getPrimeCountFromString(String num) {
        Map<Integer, Integer> count = new HashMap<>();
        count.put(2, 0); count.put(3, 0); count.put(5, 0); count.put(7, 0);
        for (char c : num.toCharArray()) {
            int d = c - '0';
            Map<Integer, Integer> fc = kFactorCounts.get(d);
            for (Map.Entry<Integer, Integer> e : fc.entrySet()) {
                count.put(e.getKey(), count.getOrDefault(e.getKey(), 0) + e.getValue());
            }
        }
        return count;
    }
    static Map<Integer, Integer> getFactorCount(Map<Integer, Integer> count) {
        Map<Integer, Integer> res = new HashMap<>();
        int c2 = count.getOrDefault(2, 0);
        int c3 = count.getOrDefault(3, 0);
        int c5 = count.getOrDefault(5, 0);
        int c7 = count.getOrDefault(7, 0);
        int count8 = c2 / 3;
        int remaining2 = c2 % 3;
        int count9 = c3 / 2;
        int count3 = c3 % 2;
        int count4 = remaining2 / 2;
        int count2 = remaining2 % 2;
        int count6 = 0;
        if (count2 == 1 && count3 == 1) {
            count2 = 0; count3 = 0;
            count6 = 1;
        }
        if (count3 == 1 && count4 == 1) {
            count2 = 1;
            count6 = 1;
            count3 = 0; count4 = 0;
        }
        res.put(2, count2);
        res.put(3, count3);
        res.put(4, count4);
        res.put(5, c5);
        res.put(6, count6);
        res.put(7, c7);
        res.put(8, count8);
        res.put(9, count9);
        return res;
    }
    static String construct(Map<Integer, Integer> factors) {
        StringBuilder sb = new StringBuilder();
        for (int digit = 2; digit < 10; digit++) {
            int cnt = factors.getOrDefault(digit, 0);
            for (int k = 0; k < cnt; k++) sb.append((char) ('0' + digit));
        }
        return sb.toString();
    }
    static boolean isSubset(Map<Integer, Integer> a, Map<Integer, Integer> b) {
        for (Map.Entry<Integer, Integer> e : a.entrySet()) {
            if (b.getOrDefault(e.getKey(), 0) < e.getValue()) return false;
        }
        return true;
    }
    static Map<Integer, Integer> subtract(Map<Integer, Integer> a, Map<Integer, Integer> b) {
        Map<Integer, Integer> res = new HashMap<>(a);
        for (Map.Entry<Integer, Integer> e : b.entrySet()) {
            int k=e.getKey();
            int v =e.getValue();
            res.put(k, Math.max(0, res.getOrDefault(k, 0) - v));
        }
        return res;
    }
    static int sumValues(Map<Integer, Integer> count) {
        int sum =0;
        for (int v:count.values()) sum += v;
        return sum;
    }
}
