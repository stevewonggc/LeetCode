package MergekSortedLists023;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Steve Wang.
 * @since 03 Dec, 2018 5:46 PM
 */
public class Solution {
  public static void main(String[] args) {
    ListNode a = new ListNode(1, 4, 5);
    ListNode b = new ListNode(1, 3, 4);
    ListNode c = new ListNode(2, 6);

    Solution solution = new Solution();
    solution.mergeKLists((ListNode[]) Arrays.asList(a, b, c).toArray()).print();
  }

  public ListNode mergeKLists(ListNode[] lists) {
    lists = Stream.of(lists).filter(list -> list!=null).collect(Collectors.toList()).toArray(new ListNode[0]);
    ListNode[] newList = twoTwoMerge(lists);
    while(newList.length >= 2) {
      newList = twoTwoMerge(newList);
    }
    return newList[0];
  }

  private ListNode[] twoTwoMerge(ListNode[] lists) {
    ListNode[] newList;
    if(lists.length % 2 == 0) {
      newList = new ListNode[lists.length / 2];
    } else {
      newList = new ListNode[lists.length / 2 + 1];
      newList[newList.length - 1] = lists[lists.length-1];
    }
    for (int i = 0; i + 1 < lists.length; i+=2) {
      newList[i/2] = mergeTwoLists(lists[i], lists[i+1]);
    }
    return newList;
  }

  private ListNode mergeTwoLists(ListNode nodes1, ListNode nodes2) {
    ListNode result = nodes1.val <= nodes2.val ? nodes1: nodes2;
    ListNode cutPoint = nodes1.val <= nodes2.val ? nodes2: nodes1;
    ListNode ptr = nodes1.val <= nodes2.val ? nodes1: nodes2;

    while(cutPoint != null) {
      if(ptr.next == null) {
        ptr.next = cutPoint;
        break;
      }
      if(ptr.val <= cutPoint.val && ptr.next.val > cutPoint.val) {
        ListNode tmp = ptr.next;
        ptr.next = cutPoint;
        cutPoint = tmp;
      } else {
        ptr = ptr.next;
      }
    }

    return result;
  }


  static class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
      val = x;
    }
    ListNode(int... xs) {
      if(xs.length == 1) {
        val = xs[0];
      } else {
        val = xs[0];
        next = new ListNode(Arrays.copyOfRange(xs, 1, xs.length));
      }
    }
    void print() {
      if(this.next == null) {
        System.out.println(val);
      } else {
        System.out.print(val + " => ");
        this.next.print();
      }
    }
  }

}


