public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}


// O(n) solution since only 2 passes and constant space not including answer as memory used to solve

// concept: given an index, the proudct at the index will be the product of all index to L * index to R
// first loop we assign each index as product of all preciding indexes
// second loop combines two steps: updating the answer index of L product * R product, then updating the value of
// the R temp product variable to avoid a third array to store values on R to L pass

// example nums array : [4, 5, 1,  8,  2,   10,  6]
//     L to R ans :     [1, 4, 20, 20, 160, 320, 3200]
//     R updates        4800 960 960 120 60   6    1
// multipy the L indexes by R temp variable  -->  4800, 3840, 1920, 2400, 9600, 1920, 3200

class Solution {
    public int[] productExceptSelf(int[] nums) {
        int length = nums.length;
        int[] answer = new int[length];


        answer[0] = 1; // 1 since no elements to the left of index 0

        // left side product, increments
        for (int i = 1; i < length; i++) {
            answer[i] = nums[i - 1] * answer[i - 1];
        }


        int R = 1; // no values to R of last index so 1 again like L

        // right side product, decrements
        for (int i = length - 1; i >= 0; i--) {
            answer[i] = answer[i] * R; // suppose ans for index 6 is 3200. that means it mulitiplied all numbers to left
            // so nums starts at 1 which is logical since all indexes excpt index 6 alreeadyu multiplied. we decreement
            // to index 5 and say that R now is the product of 1*6 which is R side while L side product gets smaller sincee
            // now less indexes to L.
            R *= nums[i];
        }

        return answer;
    }
}