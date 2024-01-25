package two;

import java.util.*;


public class Page13 {

	public static void main(String[] args) {
		// Scanner，获取输入
		Scanner input = new Scanner(System.in);
		
		System.out.print("请输入数字：");
		int number = input.nextInt();
		
		System.out.print("请输入浮点数：");
		double myDouble = input.nextDouble();
		
		System.out.print("请输入字符串：");
		String myString = input.next();
		
		System.out.println("数字为：" + number);
		System.out.println("浮点数为：" + myDouble);
		System.out.println("字符串为：" + myString);

		


	}

}
