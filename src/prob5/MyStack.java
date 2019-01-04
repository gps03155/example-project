package prob5;

public class MyStack {
	private int top;
	private String[] buffer;

	@SuppressWarnings("unchecked")
	public MyStack(int capacity) {
		top = -1;
		buffer = new String[capacity];
	}

	public void push(String s) {
		top++;
		
		if(top == buffer.length) {
			String[] temp = new String[buffer.length + 1];
			
			for(int i=0;i<buffer.length;i++) {
				temp[i] = buffer[i];
			}
			
			buffer = temp;
		}
		
		buffer[top] = s;

		// System.out.println("push : " + top);
	}

	public String pop() throws MyStackException {
		if(top == -1) {
			throw new MyStackException("stack is empty");
		}
		
		String str = buffer[top];
		top--;
		
		// System.out.println("pop : " + top);
		
		return str;
	}

	public boolean isEmpty() {
		return top == -1;
	}
}