package School_이동현;

public class Subject {
	public int stuNo;
	public String subName;
	public int Score;
	
	public void init(int no, String name, int score) {
		this.stuNo = no;
		this.subName = name;
		this.Score = score;
	}
	
	@Override
	public String toString() {
		return subName + " " + Score + "점 ";
	}

	public String saveData() {
		return "%d/%s/%d\n".formatted(stuNo, subName, Score);
	}
	
}
