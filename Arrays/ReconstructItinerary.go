/*
 * LeetCode Problem 332: Reconstruct Itinerary
 * Problem Number: 332
 * Difficulty: Hard
 * Link: https://leetcode.com/problems/reconstruct-itinerary/
 *
 * You are given a list of airline tickets where tickets[i] = [fromi, toi]
 * represent the departure and the arrival airports of one flight. Reconstruct the
 * itinerary in order and return it.
 *
 * All of the tickets belong to a man who departs from "JFK", thus, the itinerary
 * must begin with "JFK". If there are multiple valid itineraries, you should
 * return the itinerary that has the smallest lexical order when read as a single
 * string.
 *
 * 	For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than
 * ["JFK", "LGB"].
 *
 * You may assume all tickets form at least one valid itinerary. You must use all
 * the tickets once and only once.
 *
 *
 *
 * Example 1:
 *
 * Input: tickets = [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]
 * Output: ["JFK","MUC","LHR","SFO","SJC"]
 *
 * Example 2:
 *
 * Input: tickets =
 * [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
 * Explanation: Another possible reconstruction is
 * ["JFK","SFO","ATL","JFK","ATL","SFO"] but it is larger in lexical order.
 *
 *
 *
 * Constraints:
 *
 * 	1 <= tickets.length <= 300
 * 	tickets[i].length == 2
 * 	fromi.length == 3
 * 	toi.length == 3
 * 	fromi and toi consist of uppercase English letters.
 * 	fromi != toi
 *
 * Example 1:
 * Input: tickets = [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]
 * Output: ["JFK","MUC","LHR","SFO","SJC"]
 *
 * Example 2:
 * Input: tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
 * Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"] but it is larger in lexical order.
 *
 * Constraints:
 * - 1 <= tickets.length <= 300
 * - tickets[i].length == 2
 * - fromi.length == 3
 * - toi.length == 3
 * - fromi and toi consist of uppercase English letters.
 * - fromi != toi
 *
 * Topics: Array, String, Depth-First Search, Graph Theory, Sorting, Heap (Priority Queue), Eulerian Circuit, Eulerian Path, Semi-Eulerian Graph
 * Time Complexity: O(n log n)
 * Space Complexity: O(1) to O(n)
 */

/*
 * Original Solution (java) — translate to Go:
 *
 * class Solution {
 *     private Map<String, List<String>> g=new HashMap<>();
 *     private List<String> ans =new ArrayList<>();
 *     public List<String> findItinerary(List<List<String>> tickets) {
 *         Collections.sort(tickets, (a,b)-> b.get(1).compareTo(a.get(1)));
 *         for (List<String> ticket : tickets) {
 *             g.computeIfAbsent(ticket.get(0), k -> new ArrayList<>()).add(ticket.get(1));
 *         }
 *         dfs("JFK");
 *         Collections.reverse(ans);
 *         return ans;
 *     }
 *     private void dfs(String f) {
 *         while (g.containsKey(f) && !g.get(f).isEmpty()) {
 *             String t = g.get(f).remove(g.get(f).size()- 1);
 *             dfs(t);
 *         }
 *         ans.add(f);
 *     }
 * }
 */

package reconstructitinerary

func solve() interface{} {
	// TODO: Implement Go solution
	return nil
}
