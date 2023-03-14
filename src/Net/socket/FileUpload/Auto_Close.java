package Net.socket.FileUpload;

import java.io.IOException;
import java.util.Scanner;

public class Auto_Close {

	public static void main(String[] args) throws IOException {
		/**
		 * 1、提示用户，欢迎使用某某 关机程序
		 * 2、提示用户，输入倒计时关机的秒数
		 * 3、根据用户输入的秒数，进行自动关机
		 * 4、开始自动关机时，提示用户xxx秒后关机
		 * */

		System.out.println("欢迎使用自动关机程序");
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.println("：");
		String input = scanner.nextLine();
		//自动关机
		Runtime.getRuntime().exec("shutdown -s -t "+input);
		System.out.println("已启动自动关机系统，系统将在"+input+"秒后关机");
	}

}
