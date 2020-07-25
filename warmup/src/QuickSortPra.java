public class QuickSortPra {

    // 联系快排
    public void sort(int[] nums) {

        sortHelp(nums, 0, nums.length - 1);
    }

    private void sortHelp(int[] nums, int l, int r) {

        if (l >= r) return;

        int[] parts = partition(nums, l, r);
        sortHelp(nums, l, parts[0] - 1);
        sortHelp(nums, parts[1] + 1, r);
    }

    public int[] partition(int[] nums, int left, int right) {
        int pivot = nums[right];

        int p = left;// 小于pivot 的区域的下一个
        int q = right; // 大于 pivot 的区域的第一个
        int r = left; // 遍历的游标

        while (r < q) {
            if (nums[r] < pivot) {
                // left region
                swap(nums, r ++, q ++);
            } else if (nums[r] > pivot) {
                swap(nums, r, -- q);
            } else {
                r ++;
            }
        }

        //
        swap(nums, r, right);

        return new int[]{p, r - 1};
    }

    private void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

}
