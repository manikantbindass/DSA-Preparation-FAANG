// LeetCode Problem 1352: Product of the Last K Numbers
// Difficulty: Medium
// Link: https://leetcode.com/problems/product-of-the-last-k-numbers/
// Approach: Track prefix products since the last zero and divide to get a suffix product.
// Time Complexity: O(1) amortized per add, O(1) per query
// Space Complexity: O(n)

package productofthelastknumbers

type ProductOfNumbers struct {
	prefixes []int
}

func Constructor() ProductOfNumbers {
	return ProductOfNumbers{prefixes: []int{1}}
}

func (product *ProductOfNumbers) Add(num int) {
	if num == 0 {
		product.prefixes = product.prefixes[:1]
		return
	}
	last := product.prefixes[len(product.prefixes)-1]
	product.prefixes = append(product.prefixes, last*num)
}

func (product *ProductOfNumbers) GetProduct(k int) int {
	n := len(product.prefixes)
	if k >= n {
		return 0
	}
	return product.prefixes[n-1] / product.prefixes[n-1-k]
}
