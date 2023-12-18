package School_이동현;

import java.util.ArrayList;

public class StudentDAO {
	public ArrayList<Student> stuList = new ArrayList<Student>();
	public int maxNum;
	
	public void init(String data) {
		String[] temp = data.split("\n");
		for (int i = 0; i < temp.length; i++) {
			String[] temp2 = temp[i].split("/");
			Student stu = new Student();
			stu.init(Integer.parseInt(temp2[0]), temp2[1], temp2[2]);
			stuList.add(stu);
		}
	}
	
	public String saveData() {
		if (stuList.size() == 0) return "";
		String data = "";
		for (Student s : stuList) {
			data += s.saveData();
		}
		return data;
	}
	
	private String checkId() {
		String id = Util.getStrVal("id");
		for (int i = 0; i < stuList.size(); i++) {
			if (stuList.get(i).stuid.equals(id)) {
				return null;
			}
		}
		return id;
	}
	
	public void addStudent() {
		String id = checkId();
		if (id == null) {
			System.out.println("아이디 중복");
			return;
		}
		String name = Util.getStrVal("이름 : ");
		Student s = new Student();
		s.init(++maxNum, id, name);
		stuList.add(s);
		System.out.println("학생 추가 완료");
	}
	
	public void updateMaxNum() {
		int max = 0;
		for (int i = 0; i < stuList.size(); i++) {
			if (max < stuList.get(i).stuNo) {
				max = stuList.get(i).stuNo;
			}
		}
		maxNum = max;
	}
	
	public void removeStu(SubjectDAO subDAO) {
		if (stuList.size() == 0) {
			System.out.println("[No Data]");
			return;
		}
		String id = Util.getStrVal("아이디");
		int idx = -1;
		for (int i = 0; i < stuList.size(); i++) {
			if (stuList.get(i).stuid.equals(id)) {
				idx = i;
				break;
			}
		}
		if (idx == -1) {
			System.out.println("일치하는 아이디가 없습니다.");
			return;
		}
		for (int i = 0; i < subDAO.subList.size(); i++) {
			if (subDAO.subList.get(i).stuNo == stuList.get(idx).stuNo) {
				subDAO.subList.remove(i);
				i--;
			}
		}
		stuList.remove(idx);
		System.out.println("학생 삭제 완료");
	}
	
	public void printAllStu(SubjectDAO subDAO) {
		ArrayList<Student> temp = new ArrayList<Student>();
		for (int i = 0; i < stuList.size(); i++) {
			temp.add(stuList.get(i));
		}
		double[] average = new double[stuList.size()];
		for (int i = 0; i < temp.size(); i++) {
			int sum = 0;
			int cnt = 0;
			for (int j = 0; j < subDAO.subList.size(); j++) {
				if (subDAO.subList.get(j).stuNo == temp.get(i).stuNo) {
					sum += subDAO.subList.get(j).Score;
					cnt++;
				}
			}
			double avg = 1.0 * sum / cnt;
			if (cnt != 0) average[i] = avg;
			else average[i] = -1;
		}
		for (int i = 0; i < average.length; i++) {
			for (int j = i + 1; j < average.length; j++) {
				if (average[i] < average[j]) {
					double copy = average[i];
					average[i] = average[j];
					average[j] = copy;
					
					Student copy2 = temp.get(i);
					temp.set(i, temp.get(j));
					temp.set(j, copy2);
				}
			}
		}
		for (int i = 0; i < temp.size(); i++) {
			System.out.println(temp.get(i));
			for (int j = 0; j < subDAO.subList.size(); j++) {
				if (subDAO.subList.get(j).stuNo == temp.get(i).stuNo) {
					System.out.println(subDAO.subList.get(j));
				}
			}
			if (average[i] != -1) System.out.printf("평균 : %.2f점\n", average[i]);
			System.out.println("-------------------");
		}
	}
	
	
	public void printDataforSub(SubjectDAO subDAO) {
		String name = subDAO.checkName();
		int idx = -1;
		for (int i = 0; i < subDAO.subList.size(); i++) {
			if (subDAO.subList.get(i).subName.equals(name)) {
				idx = i;
				break;
			}
		}
		if (idx == -1) {
			System.out.println("일치하는 과목이 없습니다.");
			return;
		}
		System.out.println(name + "과목");
		ArrayList<Student> temp = new ArrayList<Student>();
		for (int i = 0; i < stuList.size(); i++) {
			temp.add(stuList.get(i));
		}
		for (int i = 0; i < temp.size(); i++) {
			for (int j = i + 1; j < temp.size(); j++) {
				if (temp.get(i).stuName.compareTo(temp.get(j).stuName) > 0) {
					Student copy = temp.get(i);
					temp.set(i, temp.get(j));
					temp.set(j, copy);
				}
			}
		}
		for (int j = 0; j < temp.size(); j++) {
			for (int i = 0; i < subDAO.subList.size(); i++) {
				if (temp.get(j).stuNo == subDAO.subList.get(i).stuNo && subDAO.subList.get(i).subName.equals(name)) {
					System.out.println(temp.get(j) + " " + subDAO.subList.get(i).Score + "점");
				}
			}
		}
	}
	
}
