package prob2;

import java.util.ArrayList;
import java.util.Scanner;

public class GoodsApp {
	private static final int COUNT_GOODS = 3;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ArrayList<String> name_arr = new ArrayList<String>();
		ArrayList<Integer> price_arr = new ArrayList<Integer>();
		ArrayList<Integer> num_arr = new ArrayList<Integer>();
		
		Scanner sc = new Scanner(System.in);
		Goods[] goods;
		int count = 0;
		
		String null_chk;
		String in_name;
		String in_price;
		String in_num;
		
		null_chk = sc.nextLine();
		
		while(!null_chk.equals("")) {	
			in_name = null_chk.split(" ")[0];
			in_price = null_chk.split(" ")[1];
			in_num = null_chk.split(" ")[2];
			
			name_arr.add(in_name);		
			price_arr.add(Integer.parseInt(in_price));
			num_arr.add(Integer.parseInt(in_num));
			
			count++;
			
			null_chk = sc.nextLine();
		}
		
		goods = new Goods[count];
		
		for(int i=0;i<count;i++) {
			goods[i] = new Goods();
			
			goods[i].setName(name_arr.get(i));
			goods[i].setPrice(price_arr.get(i));
			goods[i].setNum(num_arr.get(i));
			
			System.out.println(goods[i].getName() + "(가격 : " + goods[i].getPrice() + "원)이 " + goods[i].getNum() + "개 입고 되었습니다.");
		}
	}

}
