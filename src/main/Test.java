package main;

import java.util.Scanner;

import db.TestDAO;
import vo.MemberDTO;

public class Test {
    
    public static void main(String[] args) {
        
        TestDAO testDAO = new TestDAO();
        MemberDTO memberDTO = new MemberDTO();
        Scanner scan = new Scanner(System.in);
        boolean run = true;
        boolean sign = false; // 로그인 상태
        while(run) {
            System.out.println("================================================================================================");
            System.out.println("1. 회원가입 | 2.로그인 | 3.충전하기 | 4.로그아웃 | 5.프로그램 종료 | 6.아이템구매 | 7.구매한 아이템 조회 |");
            System.out.println("================================================================================================");
            System.out.print("번호 선택 >>");
            int select = Integer.parseInt(scan.nextLine());

            
            switch (select) {
                case 1:
                    // 회원가입
                    
                    System.out.print("ID >> ");
                    String setId = scan.nextLine();
                    System.out.print("비밀번호 >> ");
                    String setPass = scan.nextLine();
                    System.out.print("주소 >> ");
                    String setAddress = scan.nextLine();
                    System.out.print("전화번호 >> ");
                    String setPhone = scan.nextLine();
                    System.out.print("초기 금액 >> ");
                    int setMoney = Integer.parseInt(scan.nextLine());

                    memberDTO.setMember_id(setId);
                    memberDTO.setMember_pass(setPass);
                    memberDTO.setMember_address(setAddress);
                    memberDTO.setMember_phone(setPhone);
                    memberDTO.setMember_money(setMoney);
                    testDAO.regiserMember(memberDTO);
                    break;
                case 2: 
                /*로그인 상태는 sign, 확인하려면 db에서 값을 가져와야함. 가져오려면 조회해서 getMember */
                    // 로그인
                    System.out.print("ID >>");
                    String signID = scan.nextLine();
                    System.out.print("비밀번호 >>");
                    String signPass = scan.nextLine();
                    MemberDTO member = testDAO.getMember(signID, signPass);
                    if(signID.equals(member.getMember_id()) && signPass.equals(member.getMember_pass())) {
                        System.out.println(member.getMember_id() + "님 환영합니다!");
                        sign = true;
                    }
                    else {
                        System.out.println("로그인에 실패했습니다!");
                        sign = false;
                    }
                    //loginMember();
                    break;
                case 3: 
                    // 충전하기
                    if(sign == false) {
                        System.out.println("로그인을 먼저 해주세요");
                        break;
                    }
                    else {
                        System.out.print("잔액을 확인할 ID와 비밀번호를 입력해주세요 : ");
                        String updatingMoneyName = scan.nextLine();
                        System.out.print("충전할 금액을 입력하세요 >> ");
                        int updateChange = Integer.parseInt(scan.nextLine());
                        System.out.println("현재 " + member1.getMember_id() + "님의 잔액은 " + memberDTO.getMember_money());
                        testDAO.updateMoney(updatingMoneyName, updateChange);
                    }
                    //chargeBalance();
                    /*
                    선택 >> 3
현재 hong123님의 잔액은 10000입니다
충전할 금액을 입력하세요 >> 5000
DB 연결 성공!
충전이 완료되었습니다!! 현재 금액은 15000입니다!!
                    */
                    break;
                case 4: 
                    // 로그아웃
                    if(sign == false) {
                        System.out.println("로그인을 먼저 해주세요");
                    }
                    else {
                        System.out.println("로그아웃되셨습니다");
                        sign = false;
                    }
                    //logoutMember();
                    break;
                case 5: 
                    // 프로그램 종료
                    System.out.println("프로그램을 종료합니다!!");
                    run = false;
                    break;
                case 6: 
                    // 아이템구매
                    //purchaseItem();
                    break;
                case 7: 
                    // 구매한 아이템 조회
                    //getAllItem();
                    break;
                
                default:
                    break;
            }
        }
        
    }
    // 회원가입 메서드
    
}

/*
ID >> hong123
비밀번호 >> 12345
주소 >> 서울시 어딘가
전화번호 >> 010-1111-2222
초기 금액 >> 10000
DB 연결 성공!
회원가입이 완료되었습니다!! */