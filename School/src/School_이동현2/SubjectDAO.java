package School_이동현2;

import java.util.ArrayList;
import java.util.Random;

public class SubjectDAO {
	ArrayList<Subject> subList = new ArrayList<Subject>();
	Random rd = new Random();
	
	void init(String data) {
		String[] temp = data.split("\n");
		for (int i = 0; i < temp.length; i++) {
			String[] temp2 = temp[i].split("/");
			Subject sub = new Subject();
			sub.init(Integer.parseInt(temp2[0]), temp2[1], Integer.parseInt(temp2[2]));
			subList.add(sub);
		}
	}
	
	int checkNum(StudentDAO stuDAO) {
		int num = Util.getIntVal("학번입력", 1001, stuDAO.maxNum);
		for (int i = 0; i < stuDAO.stuList.size(); i++) {
			if (stuDAO.stuList.get(i).stuNo == num) return num;
		}
		return -1;
	}
	
	String checkName() {
		String name = Util.getStrVal("과목 이름");
		return name;
	}
	
	void addSubject(StudentDAO stuDAO) {
		int num = checkNum(stuDAO);
		if (num == -1) {
			System.out.println("일치하는 학번이 없습니다.");
			return;
		}
		String name = checkName();
		for (int i = 0; i < subList.size(); i++) {
			if (num == subList.get(i).stuNo && subList.get(i).subName.equals(name)) {
				System.out.println("과목 중복");
				name = null;
				break;
			}
		}
		if (name == null) return;
		Subject sub = new Subject();
		sub.init(num, name, rd.nextInt(51) + 50);
		subList.add(sub);
		System.out.println("과목 추가완료");
	}
	
	String saveData() {
		if (subList.size() == 0) return "";
		String data = "";
		for (Subject s : subList) {
			data += s.saveData();
		}
		return data;
	}
	
	void removeSub(StudentDAO stuDAO) {
		int num = checkNum(stuDAO);
		if (num == -1) {
			System.out.println("일치하는 학번이 없습니다.");
			return;
		}
		int a = -1;
		for (int i = 0; i < subList.size(); i++) {
			if (subList.get(i).stuNo == num) {
				a = i;
				break;
			}
		}
		if (a == -1) {
			System.out.println("[No Data]");
			return;
		}
		String name = checkName();
		int idx = -1;
		for (int i = 0; i < subList.size(); i++) {
			if (num == subList.get(i).stuNo && subList.get(i).subName.equals(name)) {
				idx = i;
				break;
			}
		}
		if (idx == -1) {
			System.out.println("해당 과목이 존재하지 않습니다.");
			return;
		}
		subList.remove(idx);
		System.out.println("과목 삭제 완료");
	}

	
}
