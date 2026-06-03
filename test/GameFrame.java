import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;
import javax.swing.ImageIcon; 
import java.awt.Image;

// GameFrame 繼承 JFrame
public class GameFrame extends JFrame {
    
     // 宣告 CardLayout 與一個主容器，用來管理並抽換不同的子畫面
    private CardLayout cardLayout;
    private JPanel mainContainer;
    public Friend userCharacter;  // 儲存玩家的角色

    public GameFrame() {
        // 基本設定
        setTitle("第19組 - 決鬥吧！魔王大人");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
      
        cardLayout = new CardLayout();
        mainContainer = new JPanel(cardLayout);

        SelectPanel selectPanel = new SelectPanel(this); 
        mainContainer.add(selectPanel, "SELECT_SCREEN");

        add(mainContainer);
    }

    // 子畫面（例如選角畫面的確定按鈕）可以要求主視窗切換畫面
    public void changeScreen(String screenName) {
        cardLayout.show(mainContainer, screenName);
    }

    // 戰鬥開始
    // 請在 GameFrame.java 中覆蓋此方法
    public void startBattle(Friend selectedUser, Boss selectedBoss) {
        this.userCharacter = selectedUser;

    // 以前這裡可能固定讀取 bossList[0]，現在改用選角畫面傳進來的 selectedBoss！
        BattlePanel battlePanel = new BattlePanel(this, userCharacter, selectedBoss);

        mainContainer.add(battlePanel, "BATTLE_SCREEN");
        cardLayout.show(mainContainer, "BATTLE_SCREEN");
    }
    
    // 【新增】自動讀取並縮放圖片的小工具
    public ImageIcon loadAndScaleImage(String path, int width, int height) {
        java.io.File file = new java.io.File(path);
        if (!file.exists()) {
            System.out.println("找不到圖片：" + path); // 防呆機制，如果忘記放圖片，終端機會提醒你
            return null;
        }
        
        // 1. 讀取原始圖片
        ImageIcon originalIcon = new ImageIcon(path);
        Image img = originalIcon.getImage();
        
        // 2. 將圖片平滑縮放到指定的寬度與高度 (Image.SCALE_SMOOTH 代表高畫質縮放)
        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        
        // 3. 把縮放好的圖片包裝回去並回傳
        return new ImageIcon(scaledImg);
    }
}

