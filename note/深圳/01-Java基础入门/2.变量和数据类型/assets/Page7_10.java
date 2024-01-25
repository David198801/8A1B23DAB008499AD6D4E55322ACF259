package two;

public class Page7_10 {
	public static void main(String[] args){
		// 常用数据类型
		
/*
byte,1字节,-128~127
int,4字节,-2^31~2^31-1  -2147483648~2147483647
short,2字节,-2^15~2^15-1  -32767~32767
long,8字节,-2^63~2^63-1
float,4字节,1.4E-48~3.4E38
double,8字节,4.9E-324~1.7E308
char
boolean
String
 */
		String name = "张三";
		char sex = '男';
		int age = 100;
		double score = 99.99;
		
		System.out.println("姓名：" + name);
		System.out.println("性别：" + sex);
		System.out.println("年龄：" + age);
		System.out.println("分数：" + score);
		
		int money;
		money = 2147483647;
		System.out.println("钱：" + money);
		
		//long类型数字结尾要加L
		long myMoney = 2147483648L;
		System.out.println("钱：" + myMoney);
		//float类型数字结尾要加f
		float moneyCoin = 0.5f;
		System.out.println("硬币：" + moneyCoin);

		byte testByte = 5;
		System.out.println("字节：" + testByte);
		
		boolean result = true;
		System.out.println("结果：" + result);

		
		
	}
	
}
