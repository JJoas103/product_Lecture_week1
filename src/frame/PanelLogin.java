package frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.JobAttributes;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import db.MemberDAO;
import vo.MemberDTO;

public class PanelLogin extends JPanel{
    
    public PanelLogin() {

        MemberDAO memberDAO = new MemberDAO();
        //패널기본설정
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(600, 800));

        //==============상단이미지 패널==============================
        ImageIcon img = new ImageIcon("img/sul.jpg");
        Image scaledImg = img.getImage().getScaledInstance(600, 600, Image.SCALE_SMOOTH);//이미지 크기 조정
        JLabel imgLabel = new JLabel(new ImageIcon(scaledImg));
        imgLabel.setPreferredSize(new Dimension(600, 600));
        JPanel imgPanel = new JPanel(new BorderLayout());
        imgPanel.add(imgLabel, BorderLayout.CENTER);

        add(imgPanel, BorderLayout.NORTH);

        //=============중단 입력 패널==============================
        JPanel inputPanel = new JPanel(null);
        inputPanel.setPreferredSize(new Dimension(600, 100));
        inputPanel.setBackground(Color.WHITE);

        // 아이디 라벨과 아이디 입력 필드
        JLabel iLabel = new JLabel("아이디 : ");
        iLabel.setBounds(120, 10, 80, 30);
        iLabel.setFont(new Font("나눔고딕코딩", Font.BOLD, 18));
        JTextField iField = new JTextField();
        iField.setBounds(230, 10, 250, 30);
        iField.setFont(new Font("나눔고딕코딩", Font.BOLD, 18));

        // 비밀번호 라벨과 비밀번호 입력 필드
        JLabel pLabel = new JLabel("비밀번호: ");
        pLabel.setBounds(120, 50, 100, 30);
        pLabel.setFont(new Font("나눔고딕코딩", Font.BOLD, 18));
        JPasswordField pField = new JPasswordField();
        pField.setBounds(230, 50, 250, 30);
        pField.setFont(new Font("나눔고딕코딩", Font.BOLD, 18));

        inputPanel.add(iLabel);
        inputPanel.add(iField);
        inputPanel.add(pLabel);
        inputPanel.add(pField);

        add(inputPanel, BorderLayout.CENTER);

        //=================하단 버튼 패널====================
        JPanel btnPanel = new JPanel(null);
        btnPanel.setPreferredSize(new Dimension(600, 100));
        btnPanel.setBackground(Color.WHITE);

        // 로그인 버튼
        JButton btnLogin = new JButton("로그인");
        btnLogin.setBounds(50, 5, 140, 90);
        btnLogin.setFont(new Font("나눔고딕코딩", Font.BOLD, 20));
        
        // 회원가입 버튼
        JButton btnJoin = new JButton("회원가입");
        btnJoin.setBounds(200, 5, 140, 90);
        btnJoin.setFont(new Font("나눔고딕코딩", Font.BOLD, 20));
        
        // 종료 버튼
        JButton btnExit = new JButton("종료");
        btnExit.setBounds(350, 5, 140, 90);
        btnExit.setFont(new Font("나눔고딕코딩", Font.BOLD, 20));

        btnPanel.add(btnLogin);
        btnPanel.add(btnJoin);
        btnPanel.add(btnExit);

        add(btnPanel, BorderLayout.SOUTH);

        //=======================버튼 이벤트========================
        //btnJoin.addActionListener((e) -> new FrameBase(new PanelJoin()));// 회원가입 버튼 클릭 시 패널출력
        btnJoin.addActionListener((e) -> FrameBase.getInstance(new PanelJoin()));

        btnLogin.addActionListener(e -> {
            String inputId = iField.getText();
            String inputPass = new String(pField.getPassword());


            MemberDTO loginMember = memberDAO.loginMember(inputId, inputPass);

            if(loginMember == null) {
                JOptionPane.showMessageDialog(this, "존재하지 않는 회원이거나 비밀번호가 일치하지 않습니다!!", "로그인 실패", JOptionPane.WARNING_MESSAGE);
            }

            System.out.println("로그인 성공");
            System.out.println(loginMember.getMemberName());
            JOptionPane.showMessageDialog(this, loginMember.getMemberName() + "님 환영합니다!!", "로그인 성공", JOptionPane.INFORMATION_MESSAGE);
            FrameBase.getInstance(new PanelMain());//메인 패널로 이동
        });//로그인 버튼 이벤트: 로그인 성공 시 로그인한 회원을 가져오기, 실패 시 경고 메시지 출력

        //종료 버튼 이벤트
        btnExit.addActionListener(e -> {
            System.exit(0);//프로그램 종료
        });
    }
}
