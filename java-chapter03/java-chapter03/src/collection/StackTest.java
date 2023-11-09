package collection;

import java.util.Stack;

public class StackTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stack<String> stack = new Stack<String>();
		
		stack.push("둘리");
		stack.push("또치");
		stack.push("희동이");
		
		System.out.println(stack.pop());
		System.out.println(stack.peek()); // peek() : stack의 맨 위에 어떤 데이터가 들어있는지 확인 (빼내지는 않음)
		
		while(stack.isEmpty() == false) {
			String value = stack.pop();
			System.out.println(value); // LIFO : Last In First Out
		}
		
		// stack이 비어있는지 확인 후 pop() 할 것
		// stack.pop(); // 비어있는 경우 예외 발생
	}

}
