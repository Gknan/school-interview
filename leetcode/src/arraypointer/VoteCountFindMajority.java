package arraypointer;

/*
Boyer-Moore 投票算法
如果我们把众数记为 +1 ，把其他数记为 -1 ，将它们全部加起来，显然和大于 0 ，从结果本身我们可以看出众数比其他数多。
本质上， Boyer-Moore 算法就是找 nums 的一个后缀 suf ，其中 suf[0] 就是后缀中的众数。我们维护一个计数器，
如果遇到一个我们目前的候选众数，就将计数器加一，否则减一。只要计数器等于 0 ，我们就将 nums 中之前访问的数字全部 忘记 ，并把下一个数字当做候选的众数。
此时，我们的候选者并不是真正的众数，但是我们在 遗忘 前面的数字的时候，要去掉相同数目的众数和非众数（如果遗忘更多的非众数，会导致计数器变成负数）。
因此，上面的过程说明了我们可以放心地遗忘前面的数字，并继续求解剩下数字中的众数。最后，总有一个后缀满足计数器是大于 0 的，此时这个后缀
的众数就是整个数组的众数。


 */
public class VoteCountFindMajority {

    public static int majorityElement(int[] nums) {

        int prob = 0; // 候选众数
        int cnt = 0; // 候选众数的计数

        for (int i = 0; i < nums.length; i ++) {
            if (cnt == 0) { // 若当前的候选众数得票 为 0，忘掉之前的候选众数，选择当前作为候选众数
                prob = nums[i];
                cnt = 1;
                continue;
            }

            if (nums[i] == prob) { // 若相等，得票 + 1 不等 得票 - 1
                cnt ++;
            } else {
                cnt --;
            }
        }

        return prob;
    }

}
