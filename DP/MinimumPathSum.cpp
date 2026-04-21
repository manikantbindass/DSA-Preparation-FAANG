// LeetCode 64 - Minimum Path Sum
// Time Complexity: O(mn) | Space Complexity: O(1)
#include <algorithm>
#include <climits>
#include <vector>
using namespace std;

class Solution {
public:
    int minPathSum(vector<vector<int>>& grid) {
        int rows = grid.size();
        int cols = grid[0].size();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (row == 0 && col == 0) {
                    continue;
                }

                int fromTop = row > 0 ? grid[row - 1][col] : INT_MAX;
                int fromLeft = col > 0 ? grid[row][col - 1] : INT_MAX;
                grid[row][col] += min(fromTop, fromLeft);
            }
        }

        return grid[rows - 1][cols - 1];
    }
};
