package Net.socket.FileUpload;

import java.io.IOException;
import java.util.Scanner;

public class Auto_Close {

	public static void main(String[] args) throws IOException {
		/**
		 * 1����ʾ�û�����ӭʹ��ĳĳ �ػ�����
		 * 2����ʾ�û������뵹��ʱ�ػ�������
		 * 3�������û�����������������Զ��ػ�
		 * 4����ʼ�Զ��ػ�ʱ����ʾ�û�xxx���ػ�
		 * */

		System.out.println("��ӭʹ���Զ��ػ�����");
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.println("��");
		String input = scanner.nextLine();
		//�Զ��ػ�
		Runtime.getRuntime().exec("shutdown -s -t "+input);
		System.out.println("�������Զ��ػ�ϵͳ��ϵͳ����"+input+"���ػ�");
	}

}
