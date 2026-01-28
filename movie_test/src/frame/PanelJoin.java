package frame;

import java.awt.*;

import javax.sql.rowset.JoinRowSet;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import db.MemberDAO;
import vo.MemberDTO;

public class PanelJoin extends JPanel{
    public PanelJoin() {
        // 패널 기본 설정
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(600, 800));
        setBackground(Color.WHITE);

        MemberDAO memberDAO = new MemberDAO();

        // ===== 상단 제목 패널 =====
        JPanel titlePanel = new JPanel(null);
        titlePanel.setPreferredSize(new Dimension(600, 150));
        titlePanel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("회원가입");
        titleLabel.setBounds(200, 50, 200, 50);
        titleLabel.setFont(new Font("나눔고딕코딩", Font.BOLD, 40));
        titlePanel.add(titleLabel);

        add(titlePanel, BorderLayout.NORTH); // 상단에 제목 배치

        // ===== 중앙 입력 패널 =====
        JPanel inputPanel = new JPanel(null); // null 레이아웃으로 절대 위치 지정
        inputPanel.setPreferredSize(new Dimension(600, 550));
        inputPanel.setBackground(Color.WHITE);

        // ID 라벨
        JLabel iblId = new JLabel("ID:");
        iblId.setBounds(80, 80, 100, 30);
        iblId.setFont(new Font("나눔고딕코딩", Font.BOLD, 18));
        inputPanel.add(iblId);

        // ID 입력 필드
        JTextField idField = new JTextField();
        idField.setBounds(180, 80, 200, 30);
        idField.setFont(new Font("나눔고딕코딩", Font.PLAIN, 16));
        inputPanel.add(idField);

        // ID 중복확인 버튼
        JButton btnCheckId = new JButton("중복확인");
        btnCheckId.setBounds(390, 80, 100, 30);
        btnCheckId.setFont(new Font("나눔고딕코딩", Font.BOLD, 14));
        inputPanel.add(btnCheckId);

        // 이름 라벨
        JLabel nameLabel = new JLabel("이름:");
        nameLabel.setBounds(80, 140, 100, 30);
        nameLabel.setFont(new Font("나눔고딕코딩", Font.BOLD, 18));
        inputPanel.add(nameLabel);

        // 이름 입력 필드
        JTextField nameField = new JTextField();
        nameField.setBounds(180, 140, 310, 30);
        nameField.setFont(new Font("나눔고딕코딩", Font.PLAIN, 16));
        inputPanel.add(nameField);

        // 비밀번호 라벨
        JLabel passLabel = new JLabel("비밀번호:");
        passLabel.setBounds(80, 200, 100, 30);
        passLabel.setFont(new Font("나눔고딕코딩", Font.BOLD, 18));
        inputPanel.add(passLabel);

        // 비밀번호 입력 필드 (암호화 표시)
        JPasswordField passField = new JPasswordField();
        passField.setBounds(180, 200, 310, 30);
        passField.setFont(new Font("나눔고딕코딩", Font.PLAIN, 16));
        inputPanel.add(passField);

        // 비밀번호 확인 라벨
        JLabel passConfirmLabel = new JLabel("비밀번호확인:");
        passConfirmLabel.setBounds(80, 260, 120, 30);
        passConfirmLabel.setFont(new Font("나눔고딕코딩", Font.BOLD, 18));
        inputPanel.add(passConfirmLabel);

        // 비밀번호 확인 입력 필드
        JPasswordField passConfirmField = new JPasswordField();
        passConfirmField.setBounds(200, 260, 290, 30);
        passConfirmField.setFont(new Font("나눔고딕코딩", Font.PLAIN, 16));
        inputPanel.add(passConfirmField);

        add(inputPanel, BorderLayout.CENTER); // 중앙에 입력 패널 배치

        // ===== 하단 버튼 패널 =====
        JPanel btnPanel = new JPanel(null); // null 레이아웃
        btnPanel.setPreferredSize(new Dimension(600, 100));
        btnPanel.setBackground(Color.WHITE); // 흰색 배경

        // 회원가입 버튼
        JButton btnJoin = new JButton("가입하기");
        btnJoin.setBounds(100, 5, 180, 90);
        btnJoin.setFont(new Font("나눔고딕코딩", Font.BOLD, 22));
        btnJoin.setEnabled(false); // 초기에는 비활성화

        // 취소 버튼
        JButton btnCancel = new JButton("취소");
        btnCancel.setBounds(320, 5, 180, 90);
        btnCancel.setFont(new Font("나눔고딕코딩", Font.BOLD, 22));

        btnPanel.add(btnJoin);
        btnPanel.add(btnCancel);

        add(btnPanel, BorderLayout.SOUTH); // 하단에 버튼 패널 배치

        //ID필드 상태에 따른 회원가입 버튼 상태
        idField.getDocument().addDocumentListener(new DocumentListener() {
            //ID 필드 변경 감지 - 값이 변경되면 회원가입 버튼 비활성화
            @Override
            public void insertUpdate(DocumentEvent e) {
                btnJoin.setEnabled(false);
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                btnJoin.setEnabled(false);
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                btnJoin.setEnabled(false);
            }
        });

        //===========버튼 이벤트 ============================
        btnCancel.addActionListener(e -> FrameBase.getInstance(new PanelLogin()));// 취소버튼 이벤트 : 로그인 화면으로 가기

        //중보확인 버튼 이벤트
        btnCheckId.addActionListener(e -> {
            String inputId = idField.getText();//아이디 입력필드에 입력한 문자열 가져오기

            if(inputId.isEmpty()){
                JOptionPane.showMessageDialog(this, "ID를 입력하세요!!", "입력오류", JOptionPane.WARNING_MESSAGE);
                return;
            }//입력창에 아무 값도 없으면 경고 메시지 출력


            boolean check = memberDAO.checkId(inputId);//중복확인

            if(check) {//중복된 ID면 경고 메시지 출력
                JOptionPane.showConfirmDialog(this, "이미 사용중인 ID입니다!!", "중복ID", JOptionPane.WARNING_MESSAGE);
                return;
            }else{
                JOptionPane.showConfirmDialog(this, "사용 가능한 ID입니다!!", "중복ID", JOptionPane.INFORMATION_MESSAGE);
                btnJoin.setEnabled(true);
            }

        });

        //회원가입 버튼 이벤트
        btnJoin.addActionListener(e -> {
            String inputId = idField.getText();//입력한 아이디 
            String inputName = nameField.getText();//입력한 이름
            String inputPass = new String(passField.getPassword());//입력한 비밀번호
            String inputPass2 = new String(passConfirmField.getPassword());//입력한 비밀번호확인 값

            // 입력란 공란 이슈
            if(inputId.isEmpty() || inputName.isEmpty() || inputPass.isEmpty() || inputPass2.isEmpty()) {
                JOptionPane.showMessageDialog(this, "모든 항목을 입력하세요!!", "입력 오류", JOptionPane.WARNING_MESSAGE);
                return;
            }
            // 비밀번호 불일치
            if(!inputPass.equals(inputPass2)) {
                JOptionPane.showMessageDialog(this, "비밀번호가 일치하지 않습니다!!", "입력 오류", JOptionPane.WARNING_MESSAGE);
            }

            // 회원가입
            JOptionPane.showMessageDialog(this, "회원가입이 완료되었습니다.", "회원가입", JOptionPane.INFORMATION_MESSAGE);
            MemberDTO joinMember = new MemberDTO(inputId, inputPass, inputName);
            memberDAO.insertMember(joinMember);//DB에 회원 저장
            FrameBase.getInstance(new PanelLogin());//회원가입 완료후 로그인 페이지로 이동
        });

    }
}
