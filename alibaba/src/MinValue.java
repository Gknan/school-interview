public class MinValue {

    int min = Integer.MAX_VALUE;

    public int getMin(int[][] matrix) {

        process(matrix, 1, matrix[0][0], 0);
        process(matrix, 1, matrix[0][1], 0);
        process(matrix, 1, matrix[0][2], 0);
        return min;
    }

    private void process(int[][] matrix, int idx, int preValue, int preSum) {

        if (idx == matrix[0].length) {
            min = Math.min(min, preSum);
            return;
        }


        for (int i = 0; i < 3; i++) {
//            for (int j = idx; j < matrix[0].length; j++) {
                process(matrix, idx + 1, matrix[i][idx], preSum + Math.abs(matrix[i][idx] - preValue));
//            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {5, 9, 5, 4, 4},
                {4, 7, 4, 10, 3},
                {2, 10, 9, 2, 3},
        };

        System.out.println(new MinValue().getMin(matrix));
    }

    // 回溯
//    public int[] dp(int[][] matrix, int n) {

//        if (n == 1) {
//            int minIdx = 0;
//            int minValue = 0;
//            for (int i = 0; i < 3; i++) {
//                for (int j = 0; j < 3; j++) {
//                    if (Math.abs(matrix[i][0]))
//                }
//            }
//            return new int[]{, 0};
//        }
//
//        int[] last = dp(matrix, n - 1);
//        int lastValue = last[0];
//        int lastMin = last[1];
//
//        int curValue = matrix[0][n - 1];
//        int dif1 = Math.abs(matrix[0][n - 1] - lastValue);
//        int dif2 = Math.abs(matrix[1][n - 1] - lastValue);
//        int dif3 = Math.abs(matrix[2][n - 1] - lastValue);
//        int min = Math.min(dif1, Math.min(dif2, dif3));
//        if (min == dif2) curValue = matrix[1][n - 1];
//        else if (min == dif3) curValue = matrix[2][n - 1];
//
//
//        return new int[]{curValue, min + lastMin};
//    }
}
