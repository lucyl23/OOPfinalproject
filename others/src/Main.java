import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // 1. 實例化（new）你剛剛寫好的視窗物件
                GameFrame gameWindow = new GameFrame();
                
                // 2. 預設的視窗是隱藏的，必須手動設定為 true，畫面才會真正跳出來
                gameWindow.setVisible(true);
            }
        });
    }
}


