import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;
import javax.swing.ImageIcon; 

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
    public void startBattle(Friend selectedUser, Boss selectedBoss, Item pulledItem) {
        this.userCharacter = selectedUser;

    // 將道具傳入戰鬥面板
        BattlePanel battlePanel = new BattlePanel(this, userCharacter, selectedBoss, pulledItem);

        mainContainer.add(battlePanel, "BATTLE_SCREEN");
        cardLayout.show(mainContainer, "BATTLE_SCREEN");
    }
    
    // 【更新版】使用 getResource 讀取圖片，確保跨電腦與打包後都能正常顯示
    public ImageIcon loadAndScaleImage(String path, int maxWidth, int maxHeight) {
        // 使用 getClass().getResource 來抓取與程式碼綁定在一起的資源
        java.net.URL imgURL = getClass().getResource(path);
        
        if (imgURL == null) {
            System.out.println("找不到圖片，請檢查路徑與檔名：" + path); // 防呆提示
            return null;
        }
        
        // 1. 讀取原始圖片
        ImageIcon originalIcon = new ImageIcon(imgURL);
        java.awt.Image img = originalIcon.getImage();
        
        // 2. 取得圖片的原始寬高
        int origWidth = originalIcon.getIconWidth();
        int origHeight = originalIcon.getIconHeight();
        
        // 如果讀不到寬高 (通常是檔案損毀或還沒載入完)，就直接回傳原圖防呆
        if (origWidth <= 0 || origHeight <= 0) {
            return originalIcon; 
        }

        // 3. 計算縮放比例 (取寬度比例和高度比例中，比較小的那個)
        double widthRatio = (double) maxWidth / origWidth;
        double heightRatio = (double) maxHeight / origHeight;
        double scaleRatio = Math.min(widthRatio, heightRatio);

        // 5. 計算出等比例縮放後的新寬高
        int newWidth = (int) (origWidth * scaleRatio);
        int newHeight = (int) (origHeight * scaleRatio);
        
        // 6. 執行縮放
        java.awt.Image scaledImg = img.getScaledInstance(newWidth, newHeight, java.awt.Image.SCALE_SMOOTH);

        // 3. 把縮放好的圖片包裝回去並回傳
        return new ImageIcon(scaledImg);
    }
}

