// 1340. Jump Game V
// https://leetcode.com/problems/jump-game-v/
// Difficulty: Hard

package dp

func maxJumps(arr []int, d int) int {
    n := len(arr)
    f := make([]int, n)
    for i := range f {
        f[i] = -1
    }
    
    var dfs func(int) int
    dfs = func(i int) int {
        if f[i] != -1 {
            return f[i]
        }
        ans := 1
        // jump left
        for j := i - 1; j >= 0; j-- {
            if i-j > d || arr[j] >= arr[i] {
                break
            }
            if val := 1 + dfs(j); val > ans {
                ans = val
            }
        }
        // jump right
        for j := i + 1; j < n; j++ {
            if j-i > d || arr[j] >= arr[i] {
                break
            }
            if val := 1 + dfs(j); val > ans {
                ans = val
            }
        }
        f[i] = ans
        return ans
    }
    
    result := 1
    for i := 0; i < n; i++ {
        if val := dfs(i); val > result {
            result = val
        }
    }
    return result
}
