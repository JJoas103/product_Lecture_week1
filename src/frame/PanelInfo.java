package frame;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.*;
import vo.MovieDTO;

public class PanelInfo extends JPanel{
    
    public PanelInfo(MovieDTO movieDTO) {
         setLayout(new BorderLayout());
        setPreferredSize(new Dimension(600, 800));
        setBackground(new Color(250, 244, 192));

        // ---------- 상단 포스터 + 정보 패널 ----------
        JPanel infoPanel = new JPanel(null);
        infoPanel.setPreferredSize(new Dimension(600, 700));
        infoPanel.setBackground(new Color(250, 244, 192));

        //영화 포스터
        ImageIcon img = new ImageIcon("img/" + movieDTO.getMovie_img());
        JLabel label = new JLabel(img);
        label.setBounds(50, 13, 285, 350);
        infoPanel.add(label);

        //영화 정보
        TextArea story = new TextArea(
            "\n@영화제목" + "\n" + " " + movieDTO.getMovie_title() +
            "\n\n@평점" + "\n" + " " + "5" + 
            "\n\n@스토리" + "\n" + " " + movieDTO.getMovie_story(), 
            0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);

        story.setEditable(false);
        story.setSize(580, 370);
        story.setLocation(0, 370);
        story.setBackground(new Color(0xffd700));
        story.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        infoPanel.add(story);

        // 리뷰 버튼
        JButton btnReview = new JButton("리뷰 보기");
        btnReview.setBackground(new Color(229, 216, 92));
        btnReview.setBounds(400, 325, 150, 40);
        btnReview.setFont(new Font("나눔고딕코딩", Font.BOLD, 18));
        infoPanel.add(btnReview);

        // ---------- 하단 버튼 패널 ----------
        JPanel bottomPanel = new JPanel(null);
        bottomPanel.setPreferredSize(new Dimension(600, 100));
        bottomPanel.setBackground(new Color(250, 244, 192));

        JButton btnBack = new JButton("처음으로");
        btnBack.setBounds(100, 5, 180, 90);
        btnBack.setFont(new Font("나눔고딕코딩", Font.BOLD, 22));

        JButton btnReserve = new JButton("예매하기");
        btnReserve.setBounds(320, 5, 180, 90);
        btnReserve.setFont(new Font("나눔고딕코딩", Font.BOLD, 22));

        bottomPanel.add(btnBack);
        bottomPanel.add(btnReserve);

        // ---------- 패널 통합 ----------
        add(infoPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        //버튼이벤트
        btnBack.addActionListener(e -> FrameBase.getInstance(new PanelSelect(1)));
        //처음으로 버튼 클릭 시, 영화선택 페이지(1)로 이동

    }
}
