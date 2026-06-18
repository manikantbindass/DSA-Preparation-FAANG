/*
 * LeetCode Problem 1344: Angle Between Hands of a Clock
 * Problem Number: 1344
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/angle-between-hands-of-a-clock/
 * 
 * Given two numbers, hour and minutes, return the smaller angle (in degrees)
 * formed between the hour and the minute hand.
 * 
 * Example 1:
 * Input: hour = 12, minutes = 30
 * Output: 165
 * 
 * Example 2:
 * Input: hour = 3, minutes = 30
 * Output: 75
 * 
 * Example 3:
 * Input: hour = 3, minutes = 15
 * Output: 7.5
 * 
 * Constraints:
 * - 1 <= hour <= 12
 * - 0 <= minutes <= 59
 * 
 * Topics: Math
 * Time Complexity: O(1) - constant time calculation
 * Space Complexity: O(1) - only using constant extra space
 */

class Solution {
    public double angleClock(int hour, int minutes) {
        // Calculate the angle of the hour hand from 12 o'clock
        // Each hour is 30 degrees (360/12)
        // Each minute adds 0.5 degrees (30/60)
        double hourAngle = 30 * hour + 0.5 * minutes;
        
        // Calculate the angle of the minute hand from 12 o'clock
        // Each minute is 6 degrees (360/60)
        double minuteAngle = 6 * minutes;
        
        // Calculate the absolute difference
        double diff = Math.abs(hourAngle - minuteAngle);
        
        // Return the smaller angle (min of diff and 360-diff)
        return Math.min(diff, 360 - diff);
    }
}
