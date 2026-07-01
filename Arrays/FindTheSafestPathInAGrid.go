// ──────────────────────────────────────────────────────────────────────
// LeetCode #2914 · Find the Safest Path in a Grid
// Difficulty : Medium
// Topics     : Array, Binary Search, Breadth-First Search, Union-Find, Heap (Priority Queue), Matrix
// URL        : https://leetcode.com/problems/find-the-safest-path-in-a-grid/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   The solution uses a multi-source BFS to compute the minimum Manhattan
//   distance from each cell to the nearest thief. Then, we sort all cells
//   by this distance in descending order and use a Union-Find data
//   structure to connect cells that have a distance at least as large as
//   the current cell's distance. We process cells from highest distance to
//   lowest, and as soon as the start (0,0) and end (n-1,n-1) become
//   connected, we return the current distance, which is the maximum
//   safeness factor. If the start or end cell itself is a thief, we return
//   0 immediately.
// 
// Complexity
//   Time  : O(n^2 log n)
//   Space : O(n^2)
// 
// Runtime  : 
// Memory   : 
// 
// Examples
//   Example 1:
//     Input  : grid = [[1,0,0],[0,0,0],[0,0,1]]
//     Output : 0
//     Explanation: All paths from (0, 0) to (n - 1, n - 1) go through the thieves in cells (0, 0) and (n - 1, n - 1).
//   Example 2:
//     Input  : grid = [[0,0,1],[0,0,0],[0,0,0]]
//     Output : 2
//     Explanation: The path depicted in the picture above has a safeness factor of 2 since:
//   Example 3:
//     Input  : grid = [[0,0,0,1],[0,0,0,0],[0,0,0,0],[1,0,0,0]]
//     Output : 2
//     Explanation: The path depicted in the picture above has a safeness factor of 2 since:
// 
// Constraints
//   · 1 <= grid.length == n <= 400
//   · grid[i].length == n
//   · grid[i][j] is either 0 or 1.
//   · There is at least one thief in the grid.
// ──────────────────────────────────────────────────────────────────────

func maximumSafenessFactor(grid [][]int) int {
    n := len(grid)
    if grid[0][0] == 1 || grid[n-1][n-1] == 1 {
        return 0
    }
    
    // Multi-source BFS to compute distance to nearest thief
    dist := make([][]int, n)
    for i := range dist {
        dist[i] = make([]int, n)
        for j := range dist[i] {
            dist[i][j] = 1 << 30
        }
    }
    q := make([][2]int, 0)
    for i := 0; i < n; i++ {
        for j := 0; j < n; j++ {
            if grid[i][j] == 1 {
                dist[i][j] = 0
                q = append(q, [2]int{i, j})
            }
        }
    }
    
    dirs := [][2]int{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}
    for len(q) > 0 {
        p := q[0]
        q = q[1:]
        i, j := p[0], p[1]
        for _, d := range dirs {
            x, y := i+d[0], j+d[1]
            if x >= 0 && x < n && y >= 0 && y < n && dist[x][y] == 1<<30 {
                dist[x][y] = dist[i][j] + 1
                q = append(q, [2]int{x, y})
            }
        }
    }
    
    // Sort cells by distance descending
    type cell struct {
        d, i, j int
    }
    cells := make([]cell, 0, n*n)
    for i := 0; i < n; i++ {
        for j := 0; j < n; j++ {
            cells = append(cells, cell{dist[i][j], i, j})
        }
    }
    sort.Slice(cells, func(a, b int) bool {
        return cells[a].d > cells[b].d
    })
    
    // Union-Find
    parent := make([]int, n*n)
    for i := range parent {
        parent[i] = i
    }
    var find func(int) int
    find = func(x int) int {
        if parent[x] != x {
            parent[x] = find(parent[x])
        }
        return parent[x]
    }
    union := func(a, b int) {
        ra, rb := find(a), find(b)
        if ra != rb {
            parent[ra] = rb
        }
    }
    
    for _, c := range cells {
        d, i, j := c.d, c.i, c.j
        for _, dir := range dirs {
            x, y := i+dir[0], j+dir[1]
            if x >= 0 && x < n && y >= 0 && y < n && dist[x][y] >= d {
                union(i*n+j, x*n+y)
            }
        }
        if find(0) == find(n*n-1) {
            return d
        }
    }
    return 0
}
