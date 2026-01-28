package frame;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.*;

public class PanelMain extends JPanel{
    
    public PanelMain() {
          // 패널 기본 설정
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(600, 800));

        JPanel moviPanel = new JPanel(new GridLayout(2, 2, 0, 0));
        moviPanel.setPreferredSize(new Dimension(600, 700));
        moviPanel.setBackground(Color.BLACK);

        

        // 상단 이미지 패널
        ImageIcon rawImg = new ImageIcon("img/MSG.jpg");
        Image scaledImg = rawImg.getImage().getScaledInstance(600, 900, Image.SCALE_SMOOTH);// 크기 조정
        JLabel imgLabel = new JLabel(new ImageIcon(scaledImg));// 이미지 라벨 생성
        imgLabel.setPreferredSize(new Dimension(600, 900));// 라벨 크기 설정
        add(imgLabel, BorderLayout.CENTER);// 중앙에 이미지 배치

        // 하단 버튼 패널
        JPanel btnPanel = new JPanel(null);
        btnPanel.setPreferredSize(new Dimension(600, 100));

        JButton btnInfo = new JButton("영화 예매");
        btnInfo.setBounds(50, 5, 140, 90);
        btnInfo.setFont(new Font("나눔고딕코딩", Font.BOLD, 20));

        JButton btnPost = new JButton("영화 리뷰");
        btnPost.setBounds(200, 5, 140, 90);
        btnPost.setFont(new Font("나눔고딕코딩", Font.BOLD, 20));

        JButton btnLogout = new JButton("로그아웃");
        btnLogout.setBounds(350, 5, 140, 90);
        btnLogout.setFont(new Font("나눔고딕코딩", Font.BOLD, 20));

        btnPanel.add(btnInfo);
        btnPanel.add(btnPost);
        btnPanel.add(btnLogout);

        add(btnPanel, BorderLayout.SOUTH);

        //버튼이벤트
        btnInfo.addActionListener(e -> {
            FrameBase.getInstance(new PanelSelect(1));//영화 선택 페이지로 이동
        });
    }
    
}
