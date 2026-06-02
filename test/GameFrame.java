import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;

// GameFrame 繼承 JFrame
public class GameFrame extends JFrame {
    
     // 宣告 CardLayout 與一個主容器，用來管理並抽換不同的子畫面
    private CardLayout cardLayout;
    private JPanel mainContainer;

    public GameFrame() {
        // 基本設定
        setTitle("第19組 - 決鬥吧！魔王大人");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
      
        cardLayout = new CardLayout();
        mainContainer = new JPanel(cardLayout);

        add(mainContainer);
    }

    // 子畫面（例如選角畫面的確定按鈕）可以要求主視窗切換畫面
    public void changeScreen(String screenName) {
        cardLayout.show(mainContainer, screenName);
}
