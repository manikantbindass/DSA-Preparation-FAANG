// Optimized O(n) solution
func findThePrefixCommonArrayOptimized(A []int, B []int) []int {
    n := len(A)
    ans := make([]int, n)
    freq := make([]int, n+1)
    common := 0
    
    for i := 0; i < n; i++ {
        freq[A[i]]++
        if freq[A[i]] == 2 {
            common++
        }
        freq[B[i]]++
        if freq[B[i]] == 2 {
            common++
        }
        ans[i] = common
    }
    
    return ans
}
