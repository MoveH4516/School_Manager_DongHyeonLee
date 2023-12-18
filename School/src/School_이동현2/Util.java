package School_이동현2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Util {
	private static Scanner sc = new Scanner(System.in);
	private static final String CUR_PATH = System.getProperty("user.dir") + "\\src\\School_이동현2\\";
	
	public static int getIntVal(String msg, int start, int end) {
		int num = 0;
		while (true) {
			System.out.println(msg);
			try {
				num = sc.nextInt();
				if (num < start || num > end) {
					System.out.printf("%d ~ %d 사이의 숫자 입력\n", start, end);
					continue;
				}
				return num;
			} catch (Exception e) {
				System.out.println("숫자 입력");
				sc.nextLine();
			}
		}
		
	}
	
	public static String getStrVal(String msg) {
		System.out.println(msg);
		String data = sc.next();
		return data;
	}
	
	private static String loadFile(String filename) {
		String data = "";
		try (FileReader fr = new FileReader(CUR_PATH + filename);
				BufferedReader br = new BufferedReader(fr)) {
			while (true) {
				String s = br.readLine();
				if (s == null) break;
				data += s + "\n";
			}
			data = data.substring(0, data.length() - 1);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	public static void loadFile (StudentDAO stuDAO, SubjectDAO subDAO) {
		String stuData = loadFile("student.txt");
		String subData = loadFile("subject.txt");
		
		stuDAO.init(stuData);
		subDAO.init(subData);
		stuDAO.updateMaxNum();
	}
	
	public static void saveFile(StudentDAO stuDAO, SubjectDAO subDAO) {
		getData("student.txt", stuDAO.saveData());
		getData("subject.txt", subDAO.saveData());
	}
	
	private static void getData(String filename, String data) {
		try (FileWriter fw = new FileWriter(CUR_PATH + filename)) {
			fw.write(data);
			System.out.println("데이터 저장");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
