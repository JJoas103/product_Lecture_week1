package main;

import frame.FrameBase;
import frame.PanelLogin;

public class MovieMain {

    public static void main(String[] args) {
        
        /*
        # 프로젝트 기능
        1. 회원가입
        2. 로그인 -> 기능수행
        3. 영화소개(로그인선행)
        4. 영화예매(로그인선행)
        5. 영화리뷰쓰기(로그인선행)

        # DB 설계
        1. 회원테이블 - ID, PWD, 이름
        2. 영화테이블 - 영화제목, 영화이미지 이름, 영화스토리, 영화예매가격
        3. 리뷰테이블 - 회원(기본키), 영화(기본키), 평점, 리뷰내용
        4. 예매테이블 - 회원(기본키), 영화(기본키), 예매시간, 좌석
        */
       //FrameBase frame = new FrameBase(new PanelLogin());
       FrameBase.getInstance(new PanelLogin());
    }
}