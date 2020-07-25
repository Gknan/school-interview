import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Dengchashulie {

    public ArrayList<Integer> getAns(int[][] arr) {
        ArrayList<Integer> ret = new ArrayList<>();

        // 遍历所有行，记录等差值
        int[] map = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            map[i] = -1;
        }

        for (int i = 0; i < arr.length; i++) {
            HashMap<Integer, Integer> valid = new HashMap<>();
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] != 0) {
                    if (valid.size() == 1) {
                        int lastIdx = 0;
                        int lastVal = 0;
                        for(Integer key: valid.keySet()) {
                            lastIdx = key;
                            lastVal = valid.get(lastIdx);
                        }
                        // 计算并保存 当前行等差值
                        map[i] = (arr[i][j] - lastVal) / (j - lastIdx);
                        break;
                    }
//                    valid.put(j, arr[i][j]);
//                    if (valid.si)
                    valid.put(j, arr[i][j]);
                }
            }
        }
        // 根据等差填写 矩阵


        return null;

    }
}
