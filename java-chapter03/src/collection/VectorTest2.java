package collection;

import java.util.Enumeration;
import java.util.Vector;

// java 1.4 이전 버전 - old Vector
public class VectorTest2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Vector<String> v = new Vector<String>();
		
		v.addElement("둘리");
		v.addElement("마이콜");
		v.addElement("또치");
		
		// iterator1
		int count = v.size();
		
		for(int i=0;i<count;i++) {
			System.out.println(v.elementAt(i));
		}
		
		v.removeElementAt(1); // v.removeElement()도 가능
		
		// iterator2 - enum
		Enumeration<String> e = v.elements();
		
		while(e.hasMoreElements()) {
			System.out.println(e.nextElement());
		}
	}

}
