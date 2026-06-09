import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GachaDialog extends JDialog {

    private HealItem[] healItemList;
    private DamageItem[] damageItemList;

    // 【新增】宣告一個變數用來儲存剛剛抽到的道具
    private Item currentItem;
    
    public GachaDialog(GameFrame parent, Friend user, Boss boss) {
        super(parent, "命運的時刻：抽取道具", true);
        setSize(450, 400);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        // 讀取道具資料 
        healItemList = GameMethod.getHealItemCharacterInfo("healItemListFile.txt");
        damageItemList = GameMethod.getDamageItemCharacterInfo("damageItemListFile.txt");

        // --- 中間區域 (預先建立好，但先藏起來) ---
        JPanel resultPanel = new JPanel(new BorderLayout(5, 5));
        JLabel imageLabel = new JLabel("", SwingConstants.CENTER); // 裝道具圖片
        JTextArea descArea = new JTextArea(4, 20); // 裝道具文字
        descArea.setEditable(false);
        descArea.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
        
        resultPanel.add(imageLabel, BorderLayout.CENTER);
        resultPanel.add(new JScrollPane(descArea), BorderLayout.SOUTH);
        resultPanel.setVisible(false); // 一開始先隱藏
        add(resultPanel, BorderLayout.CENTER);

        // --- 底部進入戰鬥按鈕 (先隱藏) ---
        JButton enterBattleBtn = new JButton("進入戰鬥！");
        enterBattleBtn.setFont(new Font("微軟正黑體", Font.BOLD, 18));
        enterBattleBtn.setVisible(false);
        add(enterBattleBtn, BorderLayout.SOUTH);

        // --- 大大的抽卡按鈕 (放在最上方，抽完後會消失) ---
        JButton drawBtn = new JButton("點我抽取道具！");
        drawBtn.setFont(new Font("微軟正黑體", Font.BOLD, 24));
        drawBtn.setPreferredSize(new Dimension(0, 100));
        add(drawBtn, BorderLayout.NORTH);

        // 裝上馬達 1：抽卡邏輯
        drawBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 1. 抽卡演算法 (移植你原本在 main 的邏輯)
                Item pulledItem;
                if (Math.random() <= 0.5) {
                    pulledItem = GameMethod.gachaItem(healItemList);
                } else {
                    pulledItem = GameMethod.gachaItem(damageItemList);
                }

                currentItem = pulledItem;

                // 2. 更新介面文字與圖片
                descArea.setText("【" + pulledItem.getName() + "】\n" + pulledItem.getIntroduceWord());
                
                // 載入道具圖片 (記得在 images 資料夾放入與道具同名的圖片，例如 "蘋果.jpg")
                ImageIcon icon = parent.loadAndScaleImage("/images/" + pulledItem.getName() + ".jpg", 400, 200);
                if (icon != null) {
                    imageLabel.setIcon(icon);
                } else {
                    imageLabel.setText("(找不到圖片: " + pulledItem.getName() + ".jpg)");
                }

                // 3. 立即使用道具效果
                boolean isReviveItem = false;
                if (pulledItem instanceof HealItem) {
                    HealItem tempItem = (HealItem) pulledItem;
                    if (tempItem.getFullyRecovered()) {
                        isReviveItem = true;
                    }
                }

                if (isReviveItem) {
                    user.setRespawnItem(pulledItem);
                    descArea.append("\n\n此道具已裝備，將在你倒下時自動發動！");
                } else {
                    pulledItem.useItem(user, boss);
                    descArea.append("\n\n道具效果已發動！");
                }

                // 4. 切換介面顯示狀態
                drawBtn.setVisible(false);       // 隱藏抽卡按鈕
                resultPanel.setVisible(true);    // 顯示道具結果
                enterBattleBtn.setVisible(true); // 顯示進入戰鬥按鈕
            }
        });

        // 裝上馬達 2：進入戰鬥邏輯
        enterBattleBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // 關閉抽卡視窗
                // 真正呼叫主視窗切換到戰鬥畫面！
                parent.startBattle(user, boss, currentItem); 
            }
        });
    }
}