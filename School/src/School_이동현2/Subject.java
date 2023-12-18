package School_이동현2;

public class Subject {
	int stuNo;
	String subName;
	int Score;
	
	void init(int no, String name, int score) {
		this.stuNo = no;
		this.subName = name;
		this.Score = score;
	}
	
	@Override
	public String toString() {
		return subName + " " + Score + "점 ";
	}

	String saveData() {
		return "%d/%s/%d\n".formatted(stuNo, subName, Score);
	}
	
}
