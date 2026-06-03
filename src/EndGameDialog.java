import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndGameDialog extends JDialog {

    public EndGameDialog(GameFrame parent, boolean isWin, Boss boss) {
        super(parent, "", true); // true 代表是強制互動視窗
        
        // 【關鍵】設定為無邊框視窗
        setUndecorated(true);
        setSize(550, 400);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());
        
        // 將背景設為黑色，增添遊戲結束的電影感
        getContentPane().setBackground(Color.BLACK); 

        // ==========================================
        // 1. 上方圖片區 (YOU WIN / GAME OVER)
        // ==========================================
        JLabel imageLabel = new JLabel("", SwingConstants.CENTER);
        
        // 根據勝負決定要讀取哪張圖片 (請記得在 images 資料夾中放入這兩張圖)
        String imageName = isWin ? "/images/YOU WIN.jpg" : "/images/GAME OVER.jpg";
        ImageIcon icon = parent.loadAndScaleImage(imageName, 550, 250);
        
        if (icon != null) {
            imageLabel.setIcon(icon);
        } else {
            // 防呆：如果忘記放圖片，用文字代替
            imageLabel.setText(isWin ? "YOU WIN" : "GAME OVER");
            imageLabel.setForeground(isWin ? Color.YELLOW : Color.RED);
            imageLabel.setFont(new Font("Arial", Font.BOLD, 48));
        }
        add(imageLabel, BorderLayout.NORTH);

        // ==========================================
        // 2. 下方文字區 (魔王遺言與系統訊息)
        // ==========================================
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.WHITE);
        textArea.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
        textArea.setLineWrap(true);      // 自動換行
        textArea.setWrapStyleWord(true);
        textArea.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        // 根據勝負填入對應的劇情台詞
        if (isWin) {
            // 呼叫你在 Boss 類別中寫好的死亡台詞
            textArea.setText(boss.getName() + "：「" + boss.getgameCharacterDialogueDie() + "」\n系統：你是真的太強了。\n\n\n系統：戰鬥結束\n「啊～又是這個夢。」");
        } else {
            textArea.setText("YOU DIED\n系統：勝敗乃兵家常事，大俠請重新來過。\n\n\n系統：戰鬥結束\n「啊～又是這個夢。」");
        }
        add(textArea, BorderLayout.CENTER);

        // ==========================================
        // 3. 底部結束按鈕
        // ==========================================
        JButton closeBtn = new JButton(isWin ? "滿載而歸 (關閉遊戲)" : "從惡夢中醒來 (關閉遊戲)");
        closeBtn.setFont(new Font("微軟正黑體", Font.BOLD, 18));
        closeBtn.setBackground(Color.DARK_GRAY);
        closeBtn.setForeground(Color.WHITE);
        closeBtn.setFocusPainted(false); // 取消按鈕點擊時的虛線框
        
        closeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // 點擊後直接結束整個 Java 程式
            }
        });
        add(closeBtn, BorderLayout.SOUTH);
    }
}