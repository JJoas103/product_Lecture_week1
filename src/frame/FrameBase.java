package frame;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Toolkit;

public class FrameBase extends JFrame{

    private static FrameBase instance;//정적영역 변수


    private FrameBase() {//메인 프레임
        setTitle("영화 예매 프로그램");
        Toolkit tk = Toolkit.getDefaultToolkit();
        int x = (int)(tk.getScreenSize().getWidth()) / 2 - 300;
        int y = (int)(tk.getScreenSize().getHeight()) / 2 - 475;
    
        setBounds(x, y, 600, 950);
        setVisible(true);
    
    }
    public static FrameBase getInstance(JPanel panel) {//정적메서드는 객체 생성 없이 호출 가능
        if(instance == null) {
            instance = new FrameBase();// getInstance 메서드 호출 시, 정적변수 instance 참조변수에 객체 주소값 할당
        }
        instance.getContentPane().removeAll();//프레임에 있는 기존 요소 들 제거
        instance.getContentPane().add(panel);//새로운 화면 추가
        instance.revalidate();//레이아웃 갱신
        instance.setVisible(true);//프레임 보이기
        return instance;
    } 
}
