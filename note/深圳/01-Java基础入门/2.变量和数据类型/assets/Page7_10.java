package two;

public class Page7_10 {
	public static void main(String[] args){
		// ������������
		
/*
byte,1�ֽ�,-128~127
int,4�ֽ�,-2^31~2^31-1  -2147483648~2147483647
short,2�ֽ�,-2^15~2^15-1  -32767~32767
long,8�ֽ�,-2^63~2^63-1
float,4�ֽ�,1.4E-48~3.4E38
double,8�ֽ�,4.9E-324~1.7E308
char
boolean
String
 */
		String name = "����";
		char sex = '��';
		int age = 100;
		double score = 99.99;
		
		System.out.println("������" + name);
		System.out.println("�Ա�" + sex);
		System.out.println("���䣺" + age);
		System.out.println("������" + score);
		
		int money;
		money = 2147483647;
		System.out.println("Ǯ��" + money);
		
		//long�������ֽ�βҪ��L
		long myMoney = 2147483648L;
		System.out.println("Ǯ��" + myMoney);
		//float�������ֽ�βҪ��f
		float moneyCoin = 0.5f;
		System.out.println("Ӳ�ң�" + moneyCoin);

		byte testByte = 5;
		System.out.println("�ֽڣ�" + testByte);
		
		boolean result = true;
		System.out.println("�����" + result);

		
		
	}
	
}
