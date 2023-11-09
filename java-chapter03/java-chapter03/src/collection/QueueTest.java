package collection;

import java.util.LinkedList;
import java.util.Queue;

public class QueueTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Queue<String> q = new LinkedList<String>();
		
		q.offer("둘리");
		q.offer("마이콜");
		q.offer("또치");
		
		while(q.isEmpty() == false) {
			System.out.println(q.poll()); // FIFO : First In First Out
		}
		
		q.poll(); // 비어있는 상태에서 불러도 Exception 발생 안함
		System.out.println(q.poll()); // 비어있는 경우 null return
		
		q.offer("둘리");
		q.offer("마이콜");
		q.offer("또치");
		
		System.out.println(q.poll());
		System.out.println(q.peek()); // 다음 차례에 뽑아지는거 보기
		System.out.println(q.poll());
		System.out.println(q.poll());
	}

}
