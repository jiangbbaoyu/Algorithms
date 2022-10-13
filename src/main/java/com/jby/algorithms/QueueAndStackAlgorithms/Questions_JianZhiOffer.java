package com.jby.algorithms.QueueAndStackAlgorithms;
/**
 * 剑指 Offer 09. 用两个栈实现队列
 * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。
 * 若队列中没有元素，deleteHead 操作返回 -1
 * 思路：
 *       appendTail 向stackToAdd中添加元素
 *       deleteHead 从stackDeleteFrom中删除；
 *       stackDeleteFrom中如果没有元素，则将stackToAdd中的全部元素pop,然后push到stackToRemove中，
 *       此时stackToRemove中的元素是与push顺序相反的，，因此从stackToRemove中pop元素时满足队列元素出队的特点
 *
 * 拓展：
 * 用两个队列实现一个栈
 *  思路：
 *  插入元素时，将元素写入到一个已经存在元素的队列中   （任意时刻，两个队列至少有一个是空的）
 *  删除元素时，将有元素的队列中的前n-1个元素重新放入到另一个队列中，然后将最后一个元素返回。
 */


/**
 * 剑指 Offer 30. 包含min函数的栈
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，
 * 调用 min、push 及 pop 的时间复杂度都是 O(1)
 * 思路 : 使用一个存放min值的辅助栈
 */


/**
 * 剑指 Offer 59 - I. 滑动窗口的最大值
 * 给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
 *
 * 示例:
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 *   滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *
 *  思路 ： 使用一个 size为k的队列 来维护可能成为滑动窗口最大值的数组元素的 `下标`，
 *        队列的最后一个元素始终是当前 滑动窗口最大值元素的数组下标， 队列中前面的下标值对应的数组元素虽然较小，但有可能是后面滑动窗口的最大值
 *
 */

/**
 * 剑指 Offer 59 - II. 队列的最大值
 * 请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。
 * 若队列为空，pop_front 和 max_value 需要返回 -1
 *
 * 思路：与 带有getMin函数的栈问题类似，  使用一个辅助队列来存放最大值， 在push_bak, pop_front时同时更新该辅助队列
 */








public class Questions_JianZhiOffer {
}
