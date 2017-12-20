package com.mdzz;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DVDSet {

	public DVDSet() {
		DVD d1 = new DVD("���޲�sb", 0, "2017-11-16");
		dvd.add(d1);

		DVD d2 = new DVD("sb���޲�", 0, "2017-11-16");
		dvd.add(d2);

		DVD d3 = new DVD("SB���޲�", 1, "");
		dvd.add(d3);
	}

	ArrayList<DVD> dvd = new ArrayList<DVD>();
	Scanner sr = new Scanner(System.in);

	public void lnit() {

		// ��ʼ�˵�

		System.out.println("��ӭʹ�ð���ʺ�����޲�");
		System.out.println("--------------------------");
		System.out.println("1.����");
		System.out.println("2.�鿴");
		System.out.println("3.ɾ��");
		System.out.println("4.���");
		System.out.println("5.�黹");
		System.out.println("6.�˳�");
		System.out.println("0.����");
		System.out.println("--------------------------");
		System.out.println("�����룺");
		int mdzz = sr.nextInt();
		switch (mdzz) {
		case 1:
			System.out.println("--->����");
			add();
			break;

		case 2:
			System.out.println("--->�б�");
			list();
			break;

		case 3:
			System.out.println("--->ɾ��");
			delete();
			break;

		case 4:
			System.out.println("--->���");
			lend();
			break;

		case 5:
			System.out.println("--->�黹");
			returnDVD();
			break;

		case 6:
			System.out.println("��лʹ�ð���ʺ�����޲���");
			break;

		case 0:
			System.out.println("--->����");
			returnMenu();
			break;

		}

	}

	public void delete() {

		// ɾ��dvd

		System.out.print("��������ɾ�������֣�");
		String name = sr.next();
		boolean flag = false;
		for (int i = 0; i < dvd.size(); i++) {
			DVD dv = dvd.get(i);
			if (dv.mz.equals(name) && dv.sl == 1) {
				dvd.remove(i);
				System.out.println("ɾ����" + name + "���ɹ���");
				System.out.println("********************************");
				flag = true;
				break;
			} else if (dv.mz.equals(name) && dv.sl == 0) {
				System.out.println("��" + name + "��Ϊʹ��״̬������ɾ����");
				flag = true;
				break;
			}

		}
		if (!flag) {
			System.out.println("û���ҵ�ƥ��ģ�");
			System.out.println("********************************");

		}
		returnMenu();

	}

	public void list() {

		// �鿴dvd

		System.out.println("���ƣ�" + "\t\t������" + "\t\t�������");
		for (int i = 0; i < dvd.size(); i++) {
			DVD dv = dvd.get(i);
			System.out.println(dv.mz + "\t\t" + dv.sl + "\t\t" + dv.rq);
		}
		System.out.println("********************************");
		returnMenu();

	}

	public void returnMenu() {

		// ����

		System.out.print("����0���أ�");
		if (sr.nextInt() == 0) {
			lnit();

		} else {
			System.out.println("�����쳣��");
			returnMenu();
		}

	}

	public void add() {

		// ����

		System.out.print("�����������ӵ����֣�");
		String name = sr.next();
		DVD dv = new DVD(name, 1, "");
		dvd.add(dv);
		int num = dvd.size();
		if (dvd.size() == num) {
			System.out.println("�����ɹ���");
			System.out.println("********************************");
			returnMenu();
		} else {
			System.out.println("����ʧ�ܣ�");
			System.out.println("********************************");
			returnMenu();
		}

	}

	public void returnDVD() {

		// �黹

		System.out.print("��������Ҫ�黹�����ƣ�");
		String name = sr.next();
		boolean flag = false;
		Scanner scanner = new Scanner(System.in);
		for (int i = 0; i < dvd.size(); i++) {
			DVD dv = dvd.get(i);
			if (dv.mz.equals(name) && dv.sl == 0) {
				dv.sl = 1;
				System.out.print("������黹����(yyyy-mm-dd)��");
				String string = scanner.next();
				long money = money(string, dv);
				System.out.println("�黹��" + name + "���ɹ���");
				System.out.println("Ӧ��" + money + "Ԫ");
				System.out.println("********************************");
				flag = true;
				break;
			} else if (dv.mz.equals(name) && dv.sl == 1) {
				System.out.println("��" + name + "��û�н�����ܹ黹��");
				System.out.println("********************************");
				flag = true;
				break;
			}

		}
		if (!flag) {
			System.out.println("û���ҵ�ƥ�����Ϣ��");
			System.out.println("********************************");

		}
		returnMenu();

	}

	public long money(String string, DVD dv) {

		// ����۸�

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

		// ���

		System.out.print("���������������֣�");
		String name = sr.next();
		boolean flag = false;
		for (int i = 0; i < dvd.size(); i++) {
			DVD dv = dvd.get(i);
			if (dv.mz.equals(name) && dv.sl == 1) {
				System.out.print("�����������ڣ�yyyy-mm-dd����");
				String date = sr.next();
				dv.rq = date;
				dv.sl = 0;
				System.out.println("����" + dv.mz + "�ѳɹ����");
				System.out.println("********************************");
				flag = true;
				break;

			} else if (dv.mz.equals(name) && dv.sl == 0) {
				System.out.println("���������Ʒ��ʱû���ˣ�");
				System.out.println("********************************");
				flag = true;
				break;

			}

		}
		if (!flag) {
			System.out.println("û���ҵ�ƥ�����Ϣ��");
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
