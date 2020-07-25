package backtracking;

/*
给定一个二维网格和一个单词，找出该单词是否存在于网格中。
单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元
格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。

示例:

board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

给定 word = "ABCCED", 返回 true
给定 word = "SEE", 返回 true
给定 word = "ABCB", 返回 false
 

提示：

board 和 word 中只包含大写和小写英文字母。
1 <= board.length <= 200
1 <= board[i].length <= 200
1 <= word.length <= 10^3

分析：
先写回溯的算法
从 word 的第一个字符开始，找到相应的位置，开始遍历
若当前字符匹配成功，收集已经匹配的结果，标记该位置已经被匹配了，然后走一步（可以向四个方法走）
走到下一步继续比较；若 i 到终点，找到结果，返回true

优化：
可以剪枝吗？

// 经过分析，将剪枝的部分改到这里，在进入循环前就抛弃不可能的节点，比把交给 dfs 下一级去判断无效少很多递归栈的调用，效率更高
在进入栈之前，把无效的节点都抛弃掉，可以减少递归栈的深度
另外，对应四个方向移动的这种题目，定义 direction 数组的方式很常用，也很方便

 */
public class Exist_79 {

    // 可以使用一维数组替换二维数组 i * col + j  就是对应一维数组的位置
    boolean[][] visited;

    int[][] direction = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}}; // 四个方向的数组，这个技巧很常用，用于向四个方向移动

    public boolean exist(char[][] board, String word) {
        if (board.length == 0 || board[0].length == 0) return word.isEmpty();

        int m = board.length;
        int n = board[0].length;

        visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!dfs(board, i, j, word, 0)) continue;
                else return true;
            }
        }

        return false;
    }

    private boolean dfs(char[][] board, int row,
                     int col, String word, int idx) {

        if (idx == word.length() - 1) return board[row][col] == word.charAt(idx);

        if (board[row][col] == word.charAt(idx)) {
            visited[row][col] = true;

            // 经过分析，将剪枝的部分改到这里，在进入循环前就抛弃不可能的节点，比把交给 dfs 下一级去判断无效少很多递归栈的调用，效率更高
            for (int i = 0; i < direction.length; i++) {
                int nextRow = row + direction[i][0];
                int nextCol = col + direction[i][1];

                // 还有下个位置已经被访问过了
                if (nextRow < 0 || nextRow >= board.length || nextCol < 0
                        || nextCol >= board[0].length || visited[nextRow][nextCol]) {
                    // 其中一个方向越界了，尝试下一个方向
                    continue;
                }
                if (dfs(board, nextRow, nextCol, word, idx + 1)) return true;
            }


            visited[row][col] = false;
        }

//        if (idx == word.length()) return true;
//
//        // 越界，或者已经访问过，跳过
//        if (row < 0 || row >= board.length || col < 0
//                || col >= board[0].length || visited[row][col]
//                || board[row][col] != word.charAt(idx)) {
//            return false;
//        }
//
//        for (int i = 0; i < direction.length; i++) {
//            // 四个方向移动，移动前记录，移动后恢复
//            visited[row][col] = true;
//
//            if (dfs(board, row + direction[i][0], col + direction[i][1], word, idx + 1)) return true;
//
//            visited[row][col] = false;
//        }

        // 四个方向扩展

//        // 利用 | 运算的短路特性，可以减少部分计算
//        boolean ret = dfs(board, row - 1, col, word, idx + 1)
//
//                // 代码的问题，加入从这里不停的进去，发现一只可以匹配，那么，不停的修改了 visited 的状态，直到如果越界了，返回 false
//                // 这时候，下一个 dfs 看到的 visited 状态并不是上一个看到的状态，所以每一步都应该进行 选择 执行 和 恢复
//                | dfs(board, row + 1, col, word, idx + 1)
//                | dfs(board, row, col - 1, word, idx + 1)
//                | dfs(board, row, col + 1, word, idx + 1);


        return false;
    }

    public static void main(String[] args) {
        Exist_79 test = new Exist_79();

        char[][] board = {
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        };
        String word = "ABCCED";
        System.out.println(test.exist(board, word));
    }
}
