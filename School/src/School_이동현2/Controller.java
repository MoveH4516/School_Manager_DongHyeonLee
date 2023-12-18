package School_이동현2;

public class Controller {
	StudentDAO stuDAO;
	SubjectDAO subDAO;
	Util u;
	
	Controller() {
		stuDAO = new StudentDAO();
		subDAO = new SubjectDAO();
		Util.loadFile(stuDAO, subDAO);
	}
	
	void menu() {
		System.out.println("[1]학생추가"); // 학번(1001) 자동증가 : 학생id 중복 불가  
		System.out.println("[2]학생삭제"); // 학생 id 입력후 삭제 과목도 같이 삭제 
		System.out.println("[3]과목추가"); //학번 입력후 점수 랜덤 50-100 : 과목이름 중복 저장불가능
		System.out.println("[4]과목삭제"); // 학번 입력후 과목삭제 
		System.out.println("[5]전체학생목록"); // 점수로 출력
		System.out.println("[6]과목별학생목록"); // 과목이름 입력받아서 해당 과목 학생이름과 과목점수 출력 (오름차순 이름순) 
		System.out.println("[7]파일저장");
		System.out.println("[8]파일로드");
		System.out.println("[0] 종료");
	}
	
	void run() {
		while (true) {
			menu();
			int sel = Util.getIntVal("메뉴선택", 0, 8);
			if (sel == 0) {
				break;
			} else if (sel == 1) {
				stuDAO.addStudent();
			} else if (sel == 2) {
				stuDAO.removeStu(subDAO);
			} else if (sel == 3) {
				subDAO.addSubject(stuDAO);
			} else if (sel == 4) {
				subDAO.removeSub(stuDAO);
			} else if (sel == 5) {
				stuDAO.printAllStu(subDAO);
			} else if (sel == 6) {
				stuDAO.printDataforSub(subDAO);
			} else if (sel == 7) {
				Util.saveFile(stuDAO, subDAO);
			} else if (sel == 8) {
				Util.loadFile(stuDAO, subDAO);
			}
		}
	}
}
