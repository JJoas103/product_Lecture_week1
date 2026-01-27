package main;

import java.util.List;
import java.util.Scanner;

import db.ClassDAO;
import db.ProfDAO;
import db.StudentDAO;
import vo.ClassDTO;
import vo.ProfDTO;
import vo.StudentDTO;

public class Test_2 {
    
    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        boolean run = true;

        StudentDAO studentDAO = new StudentDAO();
        ClassDAO classDAO = new ClassDAO();
        ProfDAO profDAO = new ProfDAO();

        StudentDTO loginStudent = null;
        ProfDTO loginProf = null;

        while(run) {

            System.out.println("==============================");
            System.out.println("1.학생 2.교수 3.종료");
            System.out.println("==============================");
            System.out.println("선택 >> ");
            int select = Integer.parseInt(scan.nextLine());

            if(select == 1) {
                
                System.out.println("======================================================================================");
                System.out.println("1.학생등록 2.로그인 3.강의정보 4.강의등록 5.강의취소 6.메인으로");
                System.out.println("======================================================================================");
                System.out.println("메뉴 선택 >> ");
                int num = Integer.parseInt(scan.nextLine());

                switch (num) {
                    case 1: {
                        //학생등록
                        System.out.print("학생 ID >> ");
                        String id = scan.nextLine();

                        //중복확인
                        boolean checkId = studentDAO.confirmId(id);
                        if(checkId == true) {//중복아이디가 존재
                            System.out.println("이미 존재하는 ID입니다.");
                            break;
                        }

                        System.out.print("비밀번호 >> ");
                        String pass = scan.nextLine();
                        System.out.println("학생이름 >> ");
                        String name = scan.nextLine();

                        StudentDTO joinStudent = new StudentDTO(id, pass, name);
                        studentDAO.insertStudent(joinStudent);
                        break;
                    }
                    case 2: {
                        //로그인
                        System.out.println("학생 ID >>>");
                        String id = scan.nextLine();
                        System.out.println("비밀번호 >>");
                        String pass = scan.nextLine();
                        loginStudent = studentDAO.loginStudent(id, pass);

                        if(loginStudent == null) {
                            System.out.println("로그인 실패! 아이디 또는 비밀번호를 확인하세요.");
                            continue;
                        }
                        System.out.println(loginStudent.getStd_name() + "님 환영합니다.");
                        break;
                    }
                    case 3:
                        //강의정보
                        if(loginStudent == null) {
                            System.out.println("로그인후 접근 가능합니다.");
                            continue;
                        }
                        List<ClassDTO> allClass = classDAO.getAllClasses();
                        System.out.println("====================================================");
                        allClass.stream().forEach(classD -> System.out.println(
                            "강의명: " + classD.getClass_name() + " | 시간: " + classD.getClass_time()
                            + " | 정원: " + classD.getClass_count() + " | 교수: "
                        ));
                        System.out.println("====================================================");
                        break;
                    case 4:
                        //강의등록
                        if(loginStudent == null) {
                            System.out.println("로그인후 접근 가능합니다.");
                            continue;
                        }
                        List<ClassDTO> classList = classDAO.getAllClasses();
                        System.out.println("================================================");
                        System.out.println("수강 가능한 강의 목록: ");
                        System.out.println(classDAO.getAllClasses());
                        System.out.println("================================================");

                        System.out.println("등록할 강의를 입력하세요 >> ");
                        String choiceClass = scan.nextLine();
                        ClassDTO classD = classList.stream()
                                .filter(i -> i.getClass_name().equals(choiceClass))
                                .findFirst()
                                .orElse(null);

                        if(classD == null) {
                            System.out.println("등록할 강의가 없습니다!!");
                        }
                        studentDAO.enrollLecture(loginStudent, classD);
                        System.out.println("수강신청이 완료되었습니다.");
                        break;
                    case 5:
                        //강의취소
                        if(loginStudent == null) {
                            System.out.println("로그인후 접근 가능합니다.");
                            continue;
                        }
                        System.out.println("====================================================");
                        System.out.println("강의 취소");
                        System.out.println("====================================================");
                        System.out.println("취소할 강의명을 입력하세요: ");
                        String lectName = scan.nextLine();
                        classDAO.deleteLecture(lectName);
                        break;
                    case 6:
                        //메인으로
                        continue;
                    default:
                        break;
                }

            }//if
            else if(select == 2) {
                //교수
                System.out.println("===================================================");
                System.out.println("1.교수등록 2.로그인 3.강의개설 4.강의정보조회 5.메인으로");
                System.out.println("===================================================");
                System.out.println("메뉴 선택 >> ");
                int num = Integer.parseInt(scan.nextLine());
                switch (num) {
                    case 1: {
                        //교수등록
                        System.out.print("교수 ID >> ");
                        String id = scan.nextLine();
                        
                        //중복확인
                        boolean checkId = profDAO.confirmId(id);
                        if(checkId == true) {//중복아이디가 존재
                            System.out.println("이미 존재하는 ID입니다.");
                            break;
                        }
                        System.out.print("비밀번호 >> ");
                        String pass = scan.nextLine();
                        System.out.println("교수이름 >> ");
                        String name = scan.nextLine();

                        ProfDTO joinProfessor = new ProfDTO(id, pass, name);
                        profDAO.insertProf(joinProfessor);
                        break;
                    }
                    case 2: {
                        //로그인
                        System.out.println("교수 ID >>>");
                        String id = scan.nextLine();
                        System.out.println("비밀번호 >>");
                        String pass = scan.nextLine();
                        loginProf = profDAO.loginProf(id, pass);

                        if(loginProf == null) {
                            System.out.println("로그인 실패! 아이디 또는 비밀번호를 확인하세요.");
                            continue;
                        }
                        System.out.println(loginProf.getProf_name() + "님 환영합니다.");
                        break;
                    }
                    case 3:
                        //강의개설
                        if(loginProf == null) {
                            System.out.println("로그인후 접근 가능합니다.");
                            continue;
                        }
                        System.out.println("강의명: ");
                        String lectName = scan.nextLine();
                        System.out.println("강의시간 선택: ");
                        System.out.println("1. 9:00-10:30\r\n" + //
                                            "2. 11:00-12:30\r\n" + //
                                            "3. 14:00-15:30\r\n" + //
                                            "4. 16:00-17:30");
                        System.out.println("선택>> ");
                        int lectTime = Integer.parseInt(scan.nextLine());
                        String classTime = "";
                        if(lectTime == 1) {
                            classTime = "9:00-10:30";
                        }
                        else if(lectTime == 2) {
                            classTime = "11:00-12:30";
                        }
                        else if(lectTime == 3) {
                            classTime = "14:00-15:30";
                        }
                        else if(lectTime == 4){
                            classTime = "16:00-17:30";
                        }else{
                            System.out.println("잘못된 선택입니다!!");
                            break;
                        }
                        System.out.println("강의 정원 : ");
                        int lectCount = Integer.parseInt(scan.nextLine());

                        //중복 시간 체크
                        boolean checkTime = classDAO.checkTime(loginProf.getProf_idx(), classTime);//특정 교수가 특정 시간대에 강좌를 개설했는지 확인
                        if(checkTime){
                            System.out.println(classTime + "에 이미 개설된 강좌가 있습니다.");
                            break;
                        }
                        //선택 시간대에 강좌가 없음
                        ClassDTO createLecture = new ClassDTO(lectName, classTime, lectCount, loginProf.getProf_idx());
                        classDAO.insertLecture(createLecture);
                        
                        break;
                    case 4:
                        //강의정보조회
                        if(loginProf == null) {
                            System.out.println("로그인후 접근 가능합니다.");
                            continue;
                        }
                        List<ClassDTO> allClass = classDAO.getProfClass(loginProf.getProf_idx());
                        System.out.println("===================================================");
                        System.out.println("내가 개설한 강의 목록: ");
                        System.out.println("===================================================");
                        allClass.stream().forEach(classD -> System.out.println(
                            "강의명: " + classD.getClass_name() + " | 시간: " + classD.getClass_time()
                            + " | 정원: " + classD.getClass_count() + " | 교수: "
                        ));
                        break;
                    case 5:
                        //메인으로
                        continue;
                    default:
                        break;
                }
            }//if
            else if(select == 3) {
                System.out.println("프로그램을 종료합니다.");
                run = false;
                break;
            }
        }// while
    }
}
