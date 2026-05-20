# Optimized O(n) solution
class Solution:
    def findThePrefixCommonArray(self, A: List[int], B: List[int]) -> List[int]:
        n = len(A)
        ans = [0] * n
        freq = [0] * (n + 1)
        common = 0
        
        for i in range(n):
            freq[A[i]] += 1
            if freq[A[i]] == 2:
                common += 1
            freq[B[i]] += 1
            if freq[B[i]] == 2:
                common += 1
            ans[i] = common
        
        return ans
