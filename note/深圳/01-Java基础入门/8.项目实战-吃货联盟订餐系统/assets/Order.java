package orderSystem;

import java.util.Scanner;

public class Order {
	public static void main(String[] args) {
		// �ʹ���С
		int orderSize = 5;
		// ������,ͬʱ��Ϊ���޶����ж�����
		String[] orderNames = new String[orderSize];
		// ��������
		String[] orderFoods = new String[orderSize];
		// ��������
		int[] orderNums = new int[orderSize];
		// ������,��1��ʼ
		int[] orderCodes = new int[orderSize];
		// �����Ÿ�ֵ
		for (int i = 0; i < orderCodes.length; i++) {
			orderCodes[i] = i + 1;
		}
		// �Ͳ�ʱ��
		int[] orderTimes = new int[orderSize];
		// �Ͳ͵�ַ
		String[] orderAddrs = new String[orderSize];
		// �Ͳ�״̬��true��ɣ�falseδ���
		Boolean[] orderStus = new Boolean[orderSize];
		//�����۸�
		double[] orderPrices = new double[orderSize];

		// ��Ʒ�б�
		String[] foodNames = new String[] { "������", "���˼�", "��ŷ�", "��Ѽ" };
		//��Ʒ�۸�
		double[] foodPrices = new double[] { 30.0, 25.0, 35.0, 50.0 };
		// ��Ʒ������
		int[] foodLikes = new int[foodNames.length];

		// ��ʼ״̬����������
		orderNames[0] = "����";
		orderFoods[0] = foodNames[0];
		orderStus[0] = false;
		orderNums[0] = 1;
		orderPrices[0] = foodPrices[0]*orderNums[0];
		orderTimes[0] = 12;
		orderAddrs[0] = "����·1��";
		orderNames[1] = "����";
		orderFoods[1] = foodNames[1];
		orderStus[1] = true;
		orderNums[1] = 1;
		orderPrices[1] = foodPrices[1]*orderNums[1];
		orderTimes[1] = 13;
		orderAddrs[1] = "���·2��";

		// �����ñ���
		String enterKeyStr;

		// ���˵�ѭ��
		do {

			// ������˵�����
			System.out.println("**************");
			System.out.println("�Ի����˶���ϵͳ���˵�");
			System.out.println("1.��Ҫ����");
			System.out.println("2.�鿴�ʹ�");
			System.out.println("3.ǩ�ն���");
			System.out.println("4.ɾ������");
			System.out.println("5.��Ҫ����");
			System.out.println("6.�˳�ϵͳ");
			System.out.println("**************");
			System.out.println("������˵���Ӧ���֣�");

			// �����������
			Scanner input = new Scanner(System.in);

			// ��ȡ���˵�����ѡ��
			String mainMenuOption = input.next();

			// ��������ѡ����ж�Ӧ����
			switch (mainMenuOption) {
			case "1":
				System.out.println(">>1.��Ҫ����");

				// ����ѭ��
				do {

					// ����λ���±�
					int placeOrderSub = -1;
					// ����Ƿ��п�λ�������±긳��placeOrderSub��placeFlag=true������ѭ��
					for (int i = 0; i < orderNames.length; i++) {
						if (orderNames[i]==null) {
							placeOrderSub = i;
							break;
						}
					}
					// ���޶����������������ѭ��
					if (placeOrderSub == -1) {
						System.out.println("------------------------");
						System.out.println("�ʹ��������޷����ͣ��������˵�");
						System.out.println("------------------------");
						break;
					}

					// ��ʼ����
					// ��ȡ����������
					System.out.println("��ʼ���ͣ�������" + (placeOrderSub + 1));
					System.out.println("�����붩����������");
					String placeName = input.next();

					// �����Ʒ�б�
					System.out.println("**************");
					System.out.println("��Ʒ\t�۸�\t������");
					for (int i = 0; i < foodNames.length; i++) {
						System.out.println(
								(i + 1) + "." + foodNames[i] + "\t"
								+ foodPrices[i] 
								+ "\t"+ foodLikes[i]);
					}
					System.out.println("**************");

					// ��ȡ���Ͳ�Ʒ��Ų���鷶Χ�����������¶���
					int placeFoodOption;
					do {
						System.out.println("�������Ʒ��ţ�");
						placeFoodOption = input.nextInt();
						if (placeFoodOption > foodNames.length) {
							System.out.println("------------");
							System.out.println("�������,����������");
							System.out.println("------------");
							continue;
						}else{
							break;
						}
					} while (true);

					// ��ȡ��Ʒ��������������������ʾ��ѭ��
					int placeNum = 0;
					do {
						System.out.println("�������Ʒ������");
						placeNum = input.nextInt();
						if (placeNum < 0) {
							System.out.println("------------------");
							System.out.println("��Ʒ����Ӧ����0,����������");
							System.out.println("------------------");
						}else{
							break;
						}
					} while (true);

					// ��ȡ�Ͳ�ʱ�䣬��������������ʾ��ѭ��
					int placeTime = 0;
					do {
						System.out.println("Ӫҵʱ��Ϊ10��~20��");
						System.out.println("�������Ͳ�ʱ�䣺");
						placeTime = input.nextInt();
						if (placeTime < 10 || placeTime > 20) {
							System.out.println("------------------");
							System.out.println("��Ӫҵʱ��,����������");
							System.out.println("------------------");
						}else{
							break;
						}
					} while (true);

					// ��ȡ�Ͳ͵�ַ
					System.out.println("�������Ͳ͵�ַ��");
					String placeAddress = input.next();

					// ���涩����Ϣ
					orderNames[placeOrderSub] = placeName;
					orderFoods[placeOrderSub] = foodNames[placeFoodOption - 1];
					orderNums[placeOrderSub] = placeNum;
					orderPrices[placeOrderSub] = orderNums[placeOrderSub]*foodPrices[placeFoodOption - 1];
					orderTimes[placeOrderSub] = placeTime;
					orderAddrs[placeOrderSub] = placeAddress;
					orderStus[placeOrderSub] = false;

					// ������ͳɹ��Ͷ�����Ϣ
					System.out.println("���ͳɹ���");
					System.out.println("���Ķ�����");
					System.out.println("**************");
					System.out.println("������\t������\t��Ʒ\t����\t�۸�\t�Ͳ�״̬\t�Ͳ�ʱ��\t�Ͳ͵�ַ");
					String orderStusChinese = (orderStus[placeOrderSub]) ? ("�����")
							: ("δ���");
					System.out.println(
							orderCodes[placeOrderSub] + "\t"
							+ orderNames[placeOrderSub]+ "\t" 
							+ orderFoods[placeOrderSub] + "\t" 
							+ orderNums[placeOrderSub] + "\t" 
							+ orderPrices[placeOrderSub] + "\t" 
							+ orderStusChinese + "\t"
							+ orderTimes[placeOrderSub] + "��" + "\t" 
							+ orderAddrs[placeOrderSub]);
					System.out.println("**************");
					System.out.println("0.�������˵�");
					System.out.println("1.��������");
					enterKeyStr = input.next();
					if (enterKeyStr.equals("0")) {
						break;
					} else if (enterKeyStr.equals("1")) {
						continue;
					}

				} while (true);

				break;

			case "2":
				System.out.println(">>2.�鿴�ʹ�");
				
				System.out.println("**************");
				System.out.println("��ǰ�ʹ��ڶ����У�");
				System.out.println("������\t������\t��Ʒ\t����\t�۸�\t�Ͳ�״̬\t�Ͳ�ʱ��\t�Ͳ͵�ַ");
				for (int i = 0; i < orderNames.length; i++) {
					if (orderNames[i]!=null) {
						// ��true/falseתΪ���/δ���
						String orderStusChinese = (orderStus[i]) ? ("�����")
								: ("δ���");
						System.out.println(
								orderCodes[i] + "\t"
								+ orderNames[i]+ "\t" 
								+ orderFoods[i] + "\t" 
								+ orderNums[i] + "\t" 
								+ orderPrices[i] + "\t" 
								+ orderStusChinese + "\t"
								+ orderTimes[i] + "��" + "\t" 
								+ orderAddrs[i]);

					}
				}
				System.out.println("**************");
				// �����������˵�
				System.out.println("����0�������˵���");
				enterKeyStr = input.next();

				break;

			case "3":
				System.out.println(">>3.ǩ�ն���");
				

				// ǩ�ն���ѭ��
				do {
					
					boolean orderFlag=false;

					// ���ʹ����Ƿ��ж���
					for (int i = 0; i < orderNames.length; i++) {
						if (orderNames[i]!=null) {
							orderFlag=true;
							break;
						}
					}

					// ���޶����������������ѭ��
					if (!orderFlag) {
						System.out.println("------------------------");
						System.out.println("�ʹ����޶������޷�ǩ�գ��������˵�");
						System.out.println("------------------------");
						break;
					}

					// �鿴�ʹ�
					System.out.println("**************");
					System.out.println("��ǰ�ʹ��ڶ����У�");
					System.out.println("������\t������\t��Ʒ\t����\t�۸�\t�Ͳ�״̬\t�Ͳ�ʱ��\t�Ͳ͵�ַ");
					for (int i = 0; i < orderNames.length; i++) {
						if (orderNames[i]!=null) {
							// ��true/falseתΪ���/δ���
							String orderStusChinese = (orderStus[i]) ? ("�����")
									: ("δ���");
							System.out.println(
									orderCodes[i] + "\t"
									+ orderNames[i]+ "\t" 
									+ orderFoods[i] + "\t" 
									+ orderNums[i] + "\t" 
									+ orderPrices[i] + "\t" 
									+ orderStusChinese + "\t"
									+ orderTimes[i] + "��" + "\t" 
									+ orderAddrs[i]);

						}
					}
					System.out.println("**************");

					// ��ȡҪǩ�յĶ�����
					System.out.println("������Ҫǩ�յĶ�����(����0�������˵�)��");
					int signCode = input.nextInt();
					if (signCode == 0) {
						break;
					}

					// ������뷶Χ����������ʾ�����Ų�����
					if (signCode > orderNames.length || signCode < 1
							|| (orderNames[signCode - 1]==null)) {
						System.out.println("------------------------");
						System.out.println("�����Ų�����,�޷�ǩ��,����������");
						System.out.println("------------------------");
						continue;
					}

					// ����������ɣ�����ʾ��������ɣ�������ǩ��
					if (orderStus[signCode - 1]) {
						System.out.println("------------------------");
						System.out.println("�����������,������ǩ��,����������");
						System.out.println("------------------------");
						continue;
					}

					// ǩ�ն���
					orderStus[signCode - 1] = true;

					// ���ǩ�ճɹ�
					System.out.println("ǩ�ճɹ�");
					System.out.println("0.�������˵�");
					System.out.println("1.����ǩ��");
					enterKeyStr = input.next();
					if (enterKeyStr.equals("0")) {
						break;
					} else if (enterKeyStr.equals("1")) {
						continue;
					}

				} while (true);

				break;

			case "4":
				System.out.println(">>4.ɾ������");
				

				// ɾ��������ѭ��
				do {
					boolean orderFlag = false;

					// ���ʹ����Ƿ��ж���
					for (int i = 0; i < orderNames.length; i++) {
						if (orderNames[i]!=null) {
							orderFlag = true;
							break;
						}
					}

					// ���޶����������������ѭ��
					if (!orderFlag) {
						System.out.println("------------------------");
						System.out.println("�ʹ����޶������޷�ɾ�����������˵�");
						System.out.println("------------------------");
						break;
					}

					// �鿴�ʹ�
					System.out.println("**************");
					System.out.println("��ǰ�ʹ��ڶ����У�");
					System.out.println("������\t������\t��Ʒ\t����\t�۸�\t�Ͳ�״̬\t�Ͳ�ʱ��\t�Ͳ͵�ַ");
					for (int i = 0; i < orderNames.length; i++) {
						if (orderNames[i]!=null) {
							// ��true/falseתΪ���/δ���
							String orderStusChinese = (orderStus[i]) ? ("�����")
									: ("δ���");
							System.out.println(
									orderCodes[i] + "\t"
									+ orderNames[i]+ "\t" 
									+ orderFoods[i] + "\t" 
									+ orderNums[i] + "\t" 
									+ orderPrices[i] + "\t" 
									+ orderStusChinese + "\t"
									+ orderTimes[i] + "��" + "\t" 
									+ orderAddrs[i]);

						}
					}
					System.out.println("**************");

					// ��ȡҪɾ���Ķ�����
					System.out.println("������Ҫɾ���Ķ�����(����0�������˵�)��");
					int deleteCode = input.nextInt();
					if (deleteCode == 0) {
						break;
					}

					// ������뷶Χ����������ʾ�����Ų�����
					if (deleteCode > orderNames.length || deleteCode < 1
							|| (orderNames[deleteCode - 1]==null)) {
						System.out.println("------------------------");
						System.out.println("�����Ų�����,�޷�ɾ��,����������");
						System.out.println("------------------------");
						continue;
					}

					// ������δ��ɣ�����ʾ����δ��ɣ��޷�ɾ��
					if (!orderStus[deleteCode - 1]) {
						System.out.println("------------------------");
						System.out.println("������δ���,�޷�ɾ��,����������");
						System.out.println("------------------------");
						continue;
					}

					// ɾ����������
					int deleteSub = deleteCode - 1;		
					int lastSub = orderNames.length-1;
					//��ɾ��λ�ÿ�ʼǰ��
					for (int j = deleteSub; j < lastSub; j++) {
						orderNames[j] = orderNames[j+1];
						orderFoods[j] = orderFoods[j+1];
						orderStus[j] = orderStus[j+1];
						orderNums[j] = orderNums[j+1];
						orderTimes[j] = orderTimes[j+1];
						orderAddrs[j] = orderAddrs[j+1];
						orderPrices[j] = orderPrices[j+1];
					}
					//���һλ���
					orderNames[lastSub] = null;
					orderFoods[lastSub] = null;
					orderStus[lastSub] = false;
					orderNums[lastSub] = 0;
					orderTimes[lastSub] = 0;
					orderAddrs[lastSub] = null;
					orderPrices[lastSub] = 0.0;
					
					

					// ���ɾ���ɹ�
					System.out.println("ɾ���ɹ�");
					System.out.println("0.�������˵�");
					System.out.println("1.����ɾ��");
					enterKeyStr = input.next();
					if (enterKeyStr.equals("0")) {
						break;
					} else if (enterKeyStr.equals("1")) {
						continue;
					}

				} while (true);

				break;

			case "5":
				System.out.println(">>5.��Ҫ����");

				// �����Ʒ�б�
				System.out.println("**************");
				System.out.println("��Ʒ\t�۸�\t������");
				for (int i = 0; i < foodNames.length; i++) {
					System.out.println(
							(i + 1) + "." + foodNames[i] + "\t"
							+ foodPrices[i] + "\t"
							+ foodLikes[i]);
				}
				System.out.println("**************");

				// ��ȡ���޲�Ʒ��ţ���1��ʼ���������1
				System.out.println("��������޵Ķ�Ӧ���֣�");
				int foodLikesOption = input.nextInt();
				// ������뷶Χ�������򷵻����˵�
				if (foodLikesOption > foodNames.length) {
					System.out.println("------------");
					System.out.println("�������,������ѡ��");
					System.out.println("------------");
					continue;
				}
				// ���޳ɹ���foodLikes[foodLikesOption-1]����
				System.out.println("------------");
				System.out.println("���޳ɹ���" + foodNames[foodLikesOption - 1]
						+ "����+1��");
				System.out.println("------------");
				foodLikes[foodLikesOption - 1] += 1;
				// �����������˵�
				System.out.println("����0�������˵���");
				enterKeyStr = input.next();

				break;

			case "6":
				System.out.println(">>6.�˳�ϵͳ");
				
				System.out.println("ллʹ�ã������˳�");
				System.exit(0);
				break;

			default:
				// ���벻�ڷ�Χ���򷵻����˵�
				System.out.println("------------");
				System.out.println("�������,������ѡ��");
				System.out.println("------------");
				continue;
			}
		} while (true);

	}

}
