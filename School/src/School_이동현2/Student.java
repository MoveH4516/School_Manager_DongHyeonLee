package School_이동현2;

public class Student {
	int stuNo;
	String stuName;
	String stuid;
	
	void init(int no, String id, String name) { 
		this.stuNo = no;
		this.stuName = name;
		this.stuid = id;
	}
	
	@Override
	public String toString() {
		String data = stuNo + " " + stuName + " " + stuid;
		return data;
	}

	String saveData() {
		return "%d/%s/%s\n".formatted(stuNo, stuName, stuid);
	}
	
}
