package chapter02;

public class GoodsApp {

	public static void main(String[] args) {
		Goods goods = new Goods();
		goods.setName("nikon");
		goods.setPrice(44000);
		goods.setCountSold(100);
		goods.setCountStock(50);
		
		goods.showInfo();
		System.out.println(goods.calcDiscountPrice(0.3f));
		
		Goods goods2 = new Goods();
		Goods goods3 = new Goods();
		
		System.out.println(Goods.countOfGoods);
	}

}
