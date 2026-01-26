package main;

import java.util.List;
import java.util.Scanner;

import db.ItemDAO;
import db.MemberDAO;
import vo.ItemDTO;
import vo.MemberDTO;

public class Test2 {
    
    public static void main(String[] args) {
        
        
        Scanner scan = new Scanner(System.in);
        boolean run = true;

        MemberDAO memberDAO = new MemberDAO();
        ItemDAO itemDAO = new ItemDAO();

        MemberDTO loginMember = null;
        
        while(run) {
            System.out.println("================================================================================================");
            System.out.println("1. 회원가입 | 2.로그인 | 3.충전하기 | 4.로그아웃 | 5.프로그램 종료 | 6.아이템구매 | 7.구매한 아이템 조회 |");
            System.out.println("================================================================================================");
            System.out.print("번호 선택 >>");
            int select = Integer.parseInt(scan.nextLine());

            
            switch (select) {
                case 1: {
                    // 회원가입
                    
                    System.out.print("ID >> ");
                    String id = scan.nextLine();
                    System.out.print("비밀번호 >> ");
                    String pass = scan.nextLine();
                    System.out.print("주소 >> ");
                    String address = scan.nextLine();
                    System.out.print("전화번호 >> ");
                    String phone = scan.nextLine();
                    System.out.print("초기 금액 >> ");
                    int money = Integer.parseInt(scan.nextLine());

                    MemberDTO joinMember = new MemberDTO(id, pass, address, phone, money);
                    memberDAO.insertMember(joinMember);
                    break;
                }
                case 2: {
                    System.out.println("ID >>>");
                    String id = scan.nextLine();
                    System.out.println("password >>");
                    String pass = scan.nextLine();
                    loginMember = memberDAO.getMember(id, pass);

                    if(loginMember == null) {
                        System.out.println("존재하지 않는 회원이거나 비밀번호가 틀렸습니다");
                        continue;
                    }
                    System.out.println(loginMember.getMember_id() + "님 환영합니다!");
                    break;
                }
                case 3: 
                    if(loginMember == null) {
                        System.out.println("로그인을 먼저 해주세요!");
                        continue;
                    }

                    System.out.println("현재 " + loginMember.getMember_id() + "님의 잔액은 " + 
                                        loginMember.getMember_money() + "원입니다.");
                    System.out.println("충전할 금액을 입력하세요 >>> ");
                    int money = Integer.parseInt(scan.nextLine());
                    memberDAO.updateMoney(money, loginMember);// 소지금 충전

                    break;
                case 4: {
                        if(loginMember == null) {
                            System.out.println("로그인을 먼저 해주세요!");
                        continue;
                    }
                    System.out.println(loginMember.getMember_id() + "님 로그아웃 되었습니다");
                    loginMember = null;
                    break;
                }
                    
                case 5: 
                    // 프로그램 종료
                    run = false;
                    System.out.println("프로그램을 종료합니다.");
                    break;
                case 6: {
                    // 아이템구매
                    if(loginMember == null) {
                        System.out.println("로그인을 먼저 해주세요!");
                        continue;
                    }
                    List<ItemDTO> itemList = itemDAO.getItems(loginMember.getMember_money());
                    itemList.stream().forEach(item -> System.out.println(item.getItemName() + ":" + item.getItemPrice()));

                    System.out.println("구매할 상품을 입력하세요 >> ");
                    String choiceItem = scan.nextLine();

                    ItemDTO item = itemList.stream()
                            .filter(i -> i.getItemName().equals(choiceItem))
                            .findFirst()
                            .orElse(null);

                        if(item == null) {
                            System.out.println("해당 상품이 없습니다");
                            continue;
                        }
                        memberDAO.buyItem(loginMember, item);
                    break;
                }
                case 7: 
                    // 구매한 아이템 조회
                    if(loginMember == null) {
                        System.out.println("로그인을 먼저 해주세여!!");
                        continue;
                    }
                    List<ItemDTO> purchasedItemList = itemDAO.getPurchasedItemList(loginMember.getMember_idx());
                    purchasedItemList.stream().forEach(item -> System.out.println(
                        item.getMemberId() + "(" + item.getOrderDate() + ")" + ": " + item.getItemName() + "(" + item.getItemPrice() + ")"
                    ));
                    break;
                
                default:
                    System.out.println("잘못된 번호입니다");
                    break;
            }
        }
        
    }
}
