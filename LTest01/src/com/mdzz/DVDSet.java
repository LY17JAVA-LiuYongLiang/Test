package com.mdzz;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DVDSet {

	public DVDSet() {
		DVD d1 = new DVD("周筠菠sb", 0, "2017-11-16");
		dvd.add(d1);

		DVD d2 = new DVD("sb周筠菠", 0, "2017-11-16");
		dvd.add(d2);

		DVD d3 = new DVD("SB周筠菠", 1, "");
		dvd.add(d3);
	}

	ArrayList<DVD> dvd = new ArrayList<DVD>();
	Scanner sr = new Scanner(System.in);

	public void lnit() {

		// 开始菜单

		System.out.println("欢迎使用爱吃屎的周筠菠");
		System.out.println("--------------------------");
		System.out.println("1.新增");
		System.out.println("2.查看");
		System.out.println("3.删除");
		System.out.println("4.借出");
		System.out.println("5.归还");
		System.out.println("6.退出");
		System.out.println("0.排行");
		System.out.println("--------------------------");
		System.out.println("请输入：");
		int mdzz = sr.nextInt();
		switch (mdzz) {
		case 1:
			System.out.println("--->新增");
			add();
			break;

		case 2:
			System.out.println("--->列表");
			list();
			break;

		case 3:
			System.out.println("--->删除");
			delete();
			break;

		case 4:
			System.out.println("--->借出");
			lend();
			break;

		case 5:
			System.out.println("--->归还");
			returnDVD();
			break;

		case 6:
			System.out.println("感谢使用爱吃屎的周筠菠！");
			break;

		case 0:
			System.out.println("--->排行");
			returnMenu();
			break;

		}

	}

	public void delete() {

		// 删除dvd

		System.out.print("请输入想删除的名字：");
		String name = sr.next();
		boolean flag = false;
		for (int i = 0; i < dvd.size(); i++) {
			DVD dv = dvd.get(i);
			if (dv.mz.equals(name) && dv.sl == 1) {
				dvd.remove(i);
				System.out.println("删除《" + name + "》成功！");
				System.out.println("********************************");
				flag = true;
				break;
			} else if (dv.mz.equals(name) && dv.sl == 0) {
				System.out.println("《" + name + "》为使用状态，不能删除！");
				flag = true;
				break;
			}

		}
		if (!flag) {
			System.out.println("没有找到匹配的！");
			System.out.println("********************************");

		}
		returnMenu();

	}

	public void list() {

		// 查看dvd

		System.out.println("名称：" + "\t\t数量：" + "\t\t借出日期");
		for (int i = 0; i < dvd.size(); i++) {
			DVD dv = dvd.get(i);
			System.out.println(dv.mz + "\t\t" + dv.sl + "\t\t" + dv.rq);
		}
		System.out.println("********************************");
		returnMenu();

	}

	public void returnMenu() {

		// 返回

		System.out.print("输入0返回：");
		if (sr.nextInt() == 0) {
			lnit();

		} else {
			System.out.println("输入异常！");
			returnMenu();
		}

	}

	public void add() {

		// 增加

		System.out.print("请输入想增加的名字：");
		String name = sr.next();
		DVD dv = new DVD(name, 1, "");
		dvd.add(dv);
		int num = dvd.size();
		if (dvd.size() == num) {
			System.out.println("新增成功！");
			System.out.println("********************************");
			returnMenu();
		} else {
			System.out.println("新增失败！");
			System.out.println("********************************");
			returnMenu();
		}

	}

	public void returnDVD() {

		// 归还

		System.out.print("请输入想要归还的名称：");
		String name = sr.next();
		boolean flag = false;
		Scanner scanner = new Scanner(System.in);
		for (int i = 0; i < dvd.size(); i++) {
			DVD dv = dvd.get(i);
			if (dv.mz.equals(name) && dv.sl == 0) {
				dv.sl = 1;
				System.out.print("请输入归还日期(yyyy-mm-dd)：");
				String string = scanner.next();
				long money = money(string, dv);
				System.out.println("归还《" + name + "》成功！");
				System.out.println("应付" + money + "元");
				System.out.println("********************************");
				flag = true;
				break;
			} else if (dv.mz.equals(name) && dv.sl == 1) {
				System.out.println("《" + name + "》没有借出不能归还！");
				System.out.println("********************************");
				flag = true;
				break;
			}

		}
		if (!flag) {
			System.out.println("没有找到匹配的信息！");
			System.out.println("********************************");

		}
		returnMenu();

	}

	public long money(String string, DVD dv) {

		// 计算价格

		long charge = 0;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
		try {
			Date date1 = dateFormat.parse(dv.rq);
			Date date2 = dateFormat.parse(string);
			charge = (date2.getTime() - date1.getTime()) / (24 * 60 * 60 * 1000);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		dv.rq = "";
		return charge;

	}

	public void lend() {

		// 借出

		System.out.print("请输入想借出的名字：");
		String name = sr.next();
		boolean flag = false;
		for (int i = 0; i < dvd.size(); i++) {
			DVD dv = dvd.get(i);
			if (dv.mz.equals(name) && dv.sl == 1) {
				System.out.print("请输入借出日期（yyyy-mm-dd）：");
				String date = sr.next();
				dv.rq = date;
				dv.sl = 0;
				System.out.println("您的" + dv.mz + "已成功借出");
				System.out.println("********************************");
				flag = true;
				break;

			} else if (dv.mz.equals(name) && dv.sl == 0) {
				System.out.println("您输入的物品暂时没有了！");
				System.out.println("********************************");
				flag = true;
				break;

			}

		}
		if (!flag) {
			System.out.println("没有找到匹配的信息！");
			System.out.println("********************************");

		}
		returnMenu();
	}
	
	public void xianShiPaiHangBang()
	{
		
		int length = dvd.size();
		DVD[] dvdArray = new DVD[length];
	}

}
