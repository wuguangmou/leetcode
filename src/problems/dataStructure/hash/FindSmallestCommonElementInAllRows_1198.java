package problems.dataStructure.hash;

import java.util.*;

/**
 * @User: 吴广谋
 * @Date: 2020/6/22
 * @Description: 给你一个矩阵mat，其中每一行的元素都已经按递增顺序排好了。请你帮忙找出在所有这些行中最小的公共元素。如果矩阵中没
 * 有这样的公共元素，就请返回-1。
 * 示例：输入：mat = [[1,2,3,4,5],[2,4,5,8,10],[3,5,7,9,11],[1,3,5,7,9]]     输出：5
 * Tips：1.mat[i]已按递增顺序排列
 *       2.1 <= mat[i][j] <= 10000
 *       3.1 <= mat.length, mat[i].length <= 500
 */
public class FindSmallestCommonElementInAllRows_1198 {

    /**
     * 由于矩阵的每一行单调递增，所以每行中不会存在重复的元素，因此，我们可以统计出出现次数等于行数的数字，再取其中最小的数字
     * 时间复杂度：O(n^2)     空间复杂度：O(n)(最好)  O(n^2)(最坏)
     */
    public static int solution1(int[][] mat){
        Map<Integer, Integer> hashMap = new HashMap<>();
        for (int[] row : mat) {
            for (int col : row) {
                //将二维矩阵的每一个元素依次放入hashMap中，key为二维数组的元素，value为该元素出现的频率
                if (hashMap.containsKey(col)) {
                    int count = hashMap.get(col);
                    hashMap.put(col, count + 1);
                    continue;
                }
                hashMap.put(col, 1);
            }
        }
        Set<Integer> keySet = hashMap.keySet();
        int min = Integer.MAX_VALUE;
        for (Integer key : keySet) {
            //若当前key出现的频率等于矩阵的行数，则取出最小的数字
            if (hashMap.get(key) == mat.length){
                min = min > key ? key : min;
            }
        }
        //当没有满足的元素时，返回-1
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    /**
     * 由于知道了数组中的元素的范围，我们可以使用数组来代替hashMap，去统计数字出现的频率
     */
    public static int solution2(int[][] mat){
        int[] feq = new int[10001];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                feq[mat[i][j]] ++;
            }
        }
        for (int i = 1; i < feq.length; i++) {
            if (feq[i] == mat.length){
                return i;
            }
        }
        return -1;
    }

    /**
     * 上面两种统计频率的方法都是逐行统计，其实我们也可以逐列统计，这样，一旦某个元素出现的频率等于行数，就可以提前返回，提高了
     * 算法效率。
     */
    public static int solution3(int[][] mat){
        int[] feq = new int[10001];
        for (int j = 0; j < mat[0].length; j++) {
            for (int i = 0; i < mat.length; i++) {
                //逐列统计元素，发现符合条件的直接提前返回
                if (++feq[mat[i][j]] == mat.length){
                    return mat[i][j];
                }
            }
        }
        return -1;
    }

    /**
     * 由于这个元素一定在每一行都存在，且每一行的元素都单调递增，因此，我们可以遍历第一行的每一个元素，然后用二分查找法去剩下的
     * 每一行查找当前遍历的元素是否存在。
     * 时间复杂度：O(mnlogm) 解释：行数m*列数n*二分查找的开销logm    空间复杂度：O(1)
     */
    public static int solution4(int[][] mat){
        for (int j = 0; j < mat[0].length; j++) {
            boolean exist = true;
            for (int i = 1; i < mat.length && exist; i++) {
                exist = Arrays.binarySearch(mat[i], mat[0][j]) >= 0;
            }
            if (exist){
                return mat[0][j];
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] mat = {{1,2,3,4,5},{2,4,5,8,10},{3,5,7,9,11},{1,3,5,7,9}};
        System.out.println(solution4(mat));
    }
}
