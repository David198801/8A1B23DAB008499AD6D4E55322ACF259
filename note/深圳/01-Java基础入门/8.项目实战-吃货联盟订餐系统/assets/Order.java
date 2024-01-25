package orderSystem;

import java.util.Scanner;

public class Order {
	public static void main(String[] args) {
		// 餐袋大小
		int orderSize = 5;
		// 订餐人,同时作为有无订单判断依据
		String[] orderNames = new String[orderSize];
		// 订餐种类
		String[] orderFoods = new String[orderSize];
		// 订餐数量
		int[] orderNums = new int[orderSize];
		// 订单号,从1开始
		int[] orderCodes = new int[orderSize];
		// 订单号赋值
		for (int i = 0; i < orderCodes.length; i++) {
			orderCodes[i] = i + 1;
		}
		// 送餐时间
		int[] orderTimes = new int[orderSize];
		// 送餐地址
		String[] orderAddrs = new String[orderSize];
		// 送餐状态，true完成，false未完成
		Boolean[] orderStus = new Boolean[orderSize];
		//订单价格
		double[] orderPrices = new double[orderSize];

		// 菜品列表
		String[] foodNames = new String[] { "红烧鱼", "黄焖鸡", "猪脚饭", "烤鸭" };
		//菜品价格
		double[] foodPrices = new double[] { 30.0, 25.0, 35.0, 50.0 };
		// 菜品点赞数
		int[] foodLikes = new int[foodNames.length];

		// 初始状态，两个订单
		orderNames[0] = "张三";
		orderFoods[0] = foodNames[0];
		orderStus[0] = false;
		orderNums[0] = 1;
		orderPrices[0] = foodPrices[0]*orderNums[0];
		orderTimes[0] = 12;
		orderAddrs[0] = "人民路1号";
		orderNames[1] = "李四";
		orderFoods[1] = foodNames[1];
		orderStus[1] = true;
		orderNums[1] = 1;
		orderPrices[1] = foodPrices[1]*orderNums[1];
		orderTimes[1] = 13;
		orderAddrs[1] = "解放路2号";

		// 返回用变量
		String enterKeyStr;

		// 主菜单循环
		do {

			// 输出主菜单内容
			System.out.println("**************");
			System.out.println("吃货联盟订单系统主菜单");
			System.out.println("1.我要订餐");
			System.out.println("2.查看餐袋");
			System.out.println("3.签收订单");
			System.out.println("4.删除订单");
			System.out.println("5.我要点赞");
			System.out.println("6.退出系统");
			System.out.println("**************");
			System.out.println("请输入菜单对应数字：");

			// 创建输入对象
			Scanner input = new Scanner(System.in);

			// 获取主菜单输入选项
			String mainMenuOption = input.next();

			// 根据输入选项进行对应操作
			switch (mainMenuOption) {
			case "1":
				System.out.println(">>1.我要订餐");

				// 订餐循环
				do {

					// 订餐位置下标
					int placeOrderSub = -1;
					// 检测是否有空位，有则下标赋给placeOrderSub，placeFlag=true，跳出循环
					for (int i = 0; i < orderNames.length; i++) {
						if (orderNames[i]==null) {
							placeOrderSub = i;
							break;
						}
					}
					// 若无订单则输出，并跳出循环
					if (placeOrderSub == -1) {
						System.out.println("------------------------");
						System.out.println("餐袋已满，无法订餐，返回主菜单");
						System.out.println("------------------------");
						break;
					}

					// 开始订餐
					// 获取订餐人姓名
					System.out.println("开始订餐，订单号" + (placeOrderSub + 1));
					System.out.println("请输入订餐人姓名：");
					String placeName = input.next();

					// 输出菜品列表
					System.out.println("**************");
					System.out.println("菜品\t价格\t点赞数");
					for (int i = 0; i < foodNames.length; i++) {
						System.out.println(
								(i + 1) + "." + foodNames[i] + "\t"
								+ foodPrices[i] 
								+ "\t"+ foodLikes[i]);
					}
					System.out.println("**************");

					// 获取订餐菜品编号并检查范围，超出则重新订餐
					int placeFoodOption;
					do {
						System.out.println("请输入菜品编号：");
						placeFoodOption = input.nextInt();
						if (placeFoodOption > foodNames.length) {
							System.out.println("------------");
							System.out.println("输入错误,请重新输入");
							System.out.println("------------");
							continue;
						}else{
							break;
						}
					} while (true);

					// 获取菜品份数，输入错误则输出提示并循环
					int placeNum = 0;
					do {
						System.out.println("请输入菜品份数：");
						placeNum = input.nextInt();
						if (placeNum < 0) {
							System.out.println("------------------");
							System.out.println("菜品份数应大于0,请重新输入");
							System.out.println("------------------");
						}else{
							break;
						}
					} while (true);

					// 获取送餐时间，输入错误则输出提示并循环
					int placeTime = 0;
					do {
						System.out.println("营业时间为10点~20点");
						System.out.println("请输入送餐时间：");
						placeTime = input.nextInt();
						if (placeTime < 10 || placeTime > 20) {
							System.out.println("------------------");
							System.out.println("非营业时间,请重新输入");
							System.out.println("------------------");
						}else{
							break;
						}
					} while (true);

					// 获取送餐地址
					System.out.println("请输入送餐地址：");
					String placeAddress = input.next();

					// 保存订单信息
					orderNames[placeOrderSub] = placeName;
					orderFoods[placeOrderSub] = foodNames[placeFoodOption - 1];
					orderNums[placeOrderSub] = placeNum;
					orderPrices[placeOrderSub] = orderNums[placeOrderSub]*foodPrices[placeFoodOption - 1];
					orderTimes[placeOrderSub] = placeTime;
					orderAddrs[placeOrderSub] = placeAddress;
					orderStus[placeOrderSub] = false;

					// 输出订餐成功和订餐信息
					System.out.println("订餐成功！");
					System.out.println("您的订单：");
					System.out.println("**************");
					System.out.println("订单号\t订餐人\t菜品\t份数\t价格\t送餐状态\t送餐时间\t送餐地址");
					String orderStusChinese = (orderStus[placeOrderSub]) ? ("已完成")
							: ("未完成");
					System.out.println(
							orderCodes[placeOrderSub] + "\t"
							+ orderNames[placeOrderSub]+ "\t" 
							+ orderFoods[placeOrderSub] + "\t" 
							+ orderNums[placeOrderSub] + "\t" 
							+ orderPrices[placeOrderSub] + "\t" 
							+ orderStusChinese + "\t"
							+ orderTimes[placeOrderSub] + "点" + "\t" 
							+ orderAddrs[placeOrderSub]);
					System.out.println("**************");
					System.out.println("0.返回主菜单");
					System.out.println("1.继续订餐");
					enterKeyStr = input.next();
					if (enterKeyStr.equals("0")) {
						break;
					} else if (enterKeyStr.equals("1")) {
						continue;
					}

				} while (true);

				break;

			case "2":
				System.out.println(">>2.查看餐袋");
				
				System.out.println("**************");
				System.out.println("当前餐袋内订单有：");
				System.out.println("订单号\t订餐人\t菜品\t份数\t价格\t送餐状态\t送餐时间\t送餐地址");
				for (int i = 0; i < orderNames.length; i++) {
					if (orderNames[i]!=null) {
						// 将true/false转为完成/未完成
						String orderStusChinese = (orderStus[i]) ? ("已完成")
								: ("未完成");
						System.out.println(
								orderCodes[i] + "\t"
								+ orderNames[i]+ "\t" 
								+ orderFoods[i] + "\t" 
								+ orderNums[i] + "\t" 
								+ orderPrices[i] + "\t" 
								+ orderStusChinese + "\t"
								+ orderTimes[i] + "点" + "\t" 
								+ orderAddrs[i]);

					}
				}
				System.out.println("**************");
				// 结束返回主菜单
				System.out.println("输入0返回主菜单：");
				enterKeyStr = input.next();

				break;

			case "3":
				System.out.println(">>3.签收订单");
				

				// 签收订单循环
				do {
					
					boolean orderFlag=false;

					// 检查餐袋内是否有订单
					for (int i = 0; i < orderNames.length; i++) {
						if (orderNames[i]!=null) {
							orderFlag=true;
							break;
						}
					}

					// 若无订单则输出，并跳出循环
					if (!orderFlag) {
						System.out.println("------------------------");
						System.out.println("餐袋内无订单，无法签收，返回主菜单");
						System.out.println("------------------------");
						break;
					}

					// 查看餐袋
					System.out.println("**************");
					System.out.println("当前餐袋内订单有：");
					System.out.println("订单号\t订餐人\t菜品\t份数\t价格\t送餐状态\t送餐时间\t送餐地址");
					for (int i = 0; i < orderNames.length; i++) {
						if (orderNames[i]!=null) {
							// 将true/false转为完成/未完成
							String orderStusChinese = (orderStus[i]) ? ("已完成")
									: ("未完成");
							System.out.println(
									orderCodes[i] + "\t"
									+ orderNames[i]+ "\t" 
									+ orderFoods[i] + "\t" 
									+ orderNums[i] + "\t" 
									+ orderPrices[i] + "\t" 
									+ orderStusChinese + "\t"
									+ orderTimes[i] + "点" + "\t" 
									+ orderAddrs[i]);

						}
					}
					System.out.println("**************");

					// 获取要签收的订单号
					System.out.println("请输入要签收的订单号(输入0返回主菜单)：");
					int signCode = input.nextInt();
					if (signCode == 0) {
						break;
					}

					// 检查输入范围，超出则提示订单号不存在
					if (signCode > orderNames.length || signCode < 1
							|| (orderNames[signCode - 1]==null)) {
						System.out.println("------------------------");
						System.out.println("订单号不存在,无法签收,请重新输入");
						System.out.println("------------------------");
						continue;
					}

					// 若订单已完成，则提示订单已完成，无需再签收
					if (orderStus[signCode - 1]) {
						System.out.println("------------------------");
						System.out.println("订单号已完成,无需再签收,请重新输入");
						System.out.println("------------------------");
						continue;
					}

					// 签收订单
					orderStus[signCode - 1] = true;

					// 输出签收成功
					System.out.println("签收成功");
					System.out.println("0.返回主菜单");
					System.out.println("1.继续签收");
					enterKeyStr = input.next();
					if (enterKeyStr.equals("0")) {
						break;
					} else if (enterKeyStr.equals("1")) {
						continue;
					}

				} while (true);

				break;

			case "4":
				System.out.println(">>4.删除订单");
				

				// 删除订单的循环
				do {
					boolean orderFlag = false;

					// 检查餐袋内是否有订单
					for (int i = 0; i < orderNames.length; i++) {
						if (orderNames[i]!=null) {
							orderFlag = true;
							break;
						}
					}

					// 若无订单则输出，并跳出循环
					if (!orderFlag) {
						System.out.println("------------------------");
						System.out.println("餐袋内无订单，无法删除，返回主菜单");
						System.out.println("------------------------");
						break;
					}

					// 查看餐袋
					System.out.println("**************");
					System.out.println("当前餐袋内订单有：");
					System.out.println("订单号\t订餐人\t菜品\t份数\t价格\t送餐状态\t送餐时间\t送餐地址");
					for (int i = 0; i < orderNames.length; i++) {
						if (orderNames[i]!=null) {
							// 将true/false转为完成/未完成
							String orderStusChinese = (orderStus[i]) ? ("已完成")
									: ("未完成");
							System.out.println(
									orderCodes[i] + "\t"
									+ orderNames[i]+ "\t" 
									+ orderFoods[i] + "\t" 
									+ orderNums[i] + "\t" 
									+ orderPrices[i] + "\t" 
									+ orderStusChinese + "\t"
									+ orderTimes[i] + "点" + "\t" 
									+ orderAddrs[i]);

						}
					}
					System.out.println("**************");

					// 获取要删除的订单号
					System.out.println("请输入要删除的订单号(输入0返回主菜单)：");
					int deleteCode = input.nextInt();
					if (deleteCode == 0) {
						break;
					}

					// 检查输入范围，超出则提示订单号不存在
					if (deleteCode > orderNames.length || deleteCode < 1
							|| (orderNames[deleteCode - 1]==null)) {
						System.out.println("------------------------");
						System.out.println("订单号不存在,无法删除,请重新输入");
						System.out.println("------------------------");
						continue;
					}

					// 若订单未完成，则提示订单未完成，无法删除
					if (!orderStus[deleteCode - 1]) {
						System.out.println("------------------------");
						System.out.println("订单号未完成,无法删除,请重新输入");
						System.out.println("------------------------");
						continue;
					}

					// 删除订单内容
					int deleteSub = deleteCode - 1;		
					int lastSub = orderNames.length-1;
					//从删除位置开始前移
					for (int j = deleteSub; j < lastSub; j++) {
						orderNames[j] = orderNames[j+1];
						orderFoods[j] = orderFoods[j+1];
						orderStus[j] = orderStus[j+1];
						orderNums[j] = orderNums[j+1];
						orderTimes[j] = orderTimes[j+1];
						orderAddrs[j] = orderAddrs[j+1];
						orderPrices[j] = orderPrices[j+1];
					}
					//最后一位清空
					orderNames[lastSub] = null;
					orderFoods[lastSub] = null;
					orderStus[lastSub] = false;
					orderNums[lastSub] = 0;
					orderTimes[lastSub] = 0;
					orderAddrs[lastSub] = null;
					orderPrices[lastSub] = 0.0;
					
					

					// 输出删除成功
					System.out.println("删除成功");
					System.out.println("0.返回主菜单");
					System.out.println("1.继续删除");
					enterKeyStr = input.next();
					if (enterKeyStr.equals("0")) {
						break;
					} else if (enterKeyStr.equals("1")) {
						continue;
					}

				} while (true);

				break;

			case "5":
				System.out.println(">>5.我要点赞");

				// 输出菜品列表
				System.out.println("**************");
				System.out.println("菜品\t价格\t点赞数");
				for (int i = 0; i < foodNames.length; i++) {
					System.out.println(
							(i + 1) + "." + foodNames[i] + "\t"
							+ foodPrices[i] + "\t"
							+ foodLikes[i]);
				}
				System.out.println("**************");

				// 获取点赞菜品序号，从1开始，操作需减1
				System.out.println("请输入点赞的对应数字：");
				int foodLikesOption = input.nextInt();
				// 检查输入范围，超出则返回主菜单
				if (foodLikesOption > foodNames.length) {
					System.out.println("------------");
					System.out.println("输入错误,请重新选择");
					System.out.println("------------");
					continue;
				}
				// 点赞成功则foodLikes[foodLikesOption-1]自增
				System.out.println("------------");
				System.out.println("点赞成功，" + foodNames[foodLikesOption - 1]
						+ "赞数+1！");
				System.out.println("------------");
				foodLikes[foodLikesOption - 1] += 1;
				// 结束返回主菜单
				System.out.println("输入0返回主菜单：");
				enterKeyStr = input.next();

				break;

			case "6":
				System.out.println(">>6.退出系统");
				
				System.out.println("谢谢使用，程序退出");
				System.exit(0);
				break;

			default:
				// 输入不在范围内则返回主菜单
				System.out.println("------------");
				System.out.println("输入错误,请重新选择");
				System.out.println("------------");
				continue;
			}
		} while (true);

	}

}
