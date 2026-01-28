package frame;

import db.MovieDAO;
import vo.MovieDTO;

import java.awt.*;
import java.util.List;

import javax.swing.*;

public class PanelSelect extends JPanel {
    
    public PanelSelect(int page) {

        MovieDAO movieDAO = new MovieDAO();

        // 패널 기본 설정
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(600, 800));

        JPanel moviePanel = new JPanel(new GridLayout(2, 2, 0, 0));
        moviePanel.setPreferredSize(new Dimension(600, 700));
        moviePanel.setBackground(Color.BLACK);

        //페이지당 4개의 영화 출력
        List<MovieDTO> listMovie = movieDAO.getMovieListByPage(page);
        
        //영화 포스터 버튼 
        listMovie.stream().forEach(movie -> {
            ImageIcon poster = new ImageIcon("img/" + movie.getMovie_img());
            JButton btn = new JButton(poster);
            btn.setName(movie.getMovie_title());
            btn.setBorderPainted(false);
            btn.setContentAreaFilled(false);
            btn.setFocusPainted(false);
            moviePanel.add(btn);

            btn.addActionListener(e -> {
                FrameBase.getInstance(new PanelInfo(movie));
            });


        });
        add(moviePanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(null);
        bottomPanel.setPreferredSize(new Dimension(600, 100));

        JButton btnBack = new JButton("처음으로");
        btnBack.setBounds(50, 5, 140, 90);
        btnBack.setFont(new Font("나눔고딕코딩", Font.BOLD, 20));

        JButton btnPrev = new JButton("이전");
        btnPrev.setBounds(200, 5, 140, 90);
        btnPrev.setFont(new Font("나눔고딕코딩", Font.BOLD, 20));

        JButton btnNext = new JButton("다음");
        btnNext.setBounds(350, 5, 140, 90);
        btnNext.setFont(new Font("나눔고딕코딩", Font.BOLD, 20));

        bottomPanel.add(btnBack);
        bottomPanel.add(btnPrev);
        bottomPanel.add(btnNext);

        add(bottomPanel, BorderLayout.SOUTH);
        
        //-================버튼이벤트===================
        //처음으로 버튼 이벤트(메인 페이지로 이동)
        btnBack.addActionListener(e -> FrameBase.getInstance(new PanelMain()));
        
        int totalPage = movieDAO.getTotalMoviePage();
        if(page >= totalPage) {
            btnNext.setEnabled(false);
        }else {
            //다음 버튼 이벤트(다음 페이지로 이동)
            btnNext.addActionListener(e -> FrameBase.getInstance(new PanelSelect(page+1)));
        }

        //이전 버튼 이벤트(이전 페이지로 이동)
        if(page <= 1) {
            btnPrev.setEnabled(false);//1페이지면 이전 버튼 비활성화
        }else {
            btnPrev.addActionListener(e -> FrameBase.getInstance(new PanelSelect(page-1)));
        }
    }
}
