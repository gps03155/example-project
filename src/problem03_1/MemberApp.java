package problem03_1;

public class MemberApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Member member = new Member();
		
		member.setId("psh");
		member.setName("Park SungHye");
		member.setPoint(10000);
		
		System.out.println("id : " + member.getId());
		System.out.println("name : " + member.getName());
		System.out.println("point : " + member.getPoint());
	}

}
