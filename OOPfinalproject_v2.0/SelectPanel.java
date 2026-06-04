import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectPanel extends JPanel {
    
    private GameFrame parentFrame; 
    
    // 英雄（友方）UI 元件
    private JComboBox<String> heroComboBox; 
    private JLabel heroImageLabel;           // 英雄圖片呈現空間
    private JTextArea heroInfoArea;          // 英雄詳細數值
    private Friend[] friendList;                 

    // 魔王（敵方）UI 元件
    private JComboBox<String> bossComboBox; 
    private JLabel bossImageLabel;           // 魔王圖片呈現空間
    private JTextArea bossInfoArea;          // 魔王詳細數值
    private Boss[] bossList;                 

    public SelectPanel(GameFrame parentFrame) {
        this.parentFrame = parentFrame;

        // 1. 主面板採用 BorderLayout 進行上、中、下的排版
        setLayout(new BorderLayout(0, 10)); 

        // 2. 自動呼叫你原本寫好的讀檔方法，載入兩邊的角色資料
        friendList = GameMethod.getFriendCharacterInfo("friendListFile.txt");
        bossList = GameMethod.getBossCharacterInfo("bossListFile.txt");

        // 3. 【滿足需求 2】將主提示語直接放上介面頂部，不再透過終端機輸出
        JLabel titleLabel = new JLabel("決鬥吧！選擇你的角色與你想對戰的對象！", SwingConstants.CENTER);
        titleLabel.setFont(new Font("微軟正黑體", Font.BOLD, 22));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 5, 0));
        add(titleLabel, BorderLayout.NORTH);

        // 4. 中間區域：採用 GridLayout 將畫面一分為二 (左英雄、右魔王)
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 30, 0)); // 30 是左右欄位的間距
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // ==================================================
        // 【左半部：英雄選擇區】
        // ==================================================
        JPanel heroPanel = new JPanel(new BorderLayout(0, 10));
        
        // 介面提示語
        JLabel heroPromptLabel = new JLabel("【第一步】請選擇你的英雄夥伴：", SwingConstants.LEFT);
        heroPromptLabel.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        heroPromptLabel.setForeground(new Color(0, 102, 204)); // 設定藍色調
        heroPanel.add(heroPromptLabel, BorderLayout.NORTH);

        // 下拉選單資料設定
        String[] heroNames = new String[friendList.length];
        for (int i = 0; i < friendList.length; i++) {
            heroNames[i] = friendList[i].getName();
        }
        heroComboBox = new JComboBox<>(heroNames);
        heroComboBox.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
        
        // 【滿足需求 1】英雄圖片預留空間
        heroImageLabel = new JLabel("英雄圖片載入中...", SwingConstants.CENTER);
        heroImageLabel.setOpaque(true);
        heroImageLabel.setBackground(new Color(220, 240, 255)); // 淡藍色背景
        heroImageLabel.setPreferredSize(new Dimension(150, 180));
        heroImageLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        
        // 數值區
        heroInfoArea = new JTextArea(6, 20);
        heroInfoArea.setEditable(false); // 唯讀
        heroInfoArea.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
        
        // 組合英雄區的中間容器 (選單 + 圖片 + 數值)
        JPanel heroSubCenter = new JPanel(new BorderLayout(0, 10));
        heroSubCenter.add(heroComboBox, BorderLayout.NORTH);
        heroSubCenter.add(heroImageLabel, BorderLayout.CENTER);
        heroSubCenter.add(new JScrollPane(heroInfoArea), BorderLayout.SOUTH);
        heroPanel.add(heroSubCenter, BorderLayout.CENTER);

        // ==================================================
        // 【右半部：魔王選擇區】
        // ==================================================
        JPanel bossPanel = new JPanel(new BorderLayout(0, 10));
        
        // 介面提示語
        JLabel bossPromptLabel = new JLabel("【第二步】請選擇你要挑戰的魔王：", SwingConstants.LEFT);
        bossPromptLabel.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        bossPromptLabel.setForeground(new Color(204, 0, 0)); // 設定紅色調
        bossPanel.add(bossPromptLabel, BorderLayout.NORTH);

        // 下拉選單資料設定
        String[] bossNames = new String[bossList.length];
        for (int i = 0; i < bossList.length; i++) {
            bossNames[i] = bossList[i].getName();
        }
        bossComboBox = new JComboBox<>(bossNames);
        bossComboBox.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
        
        // 【滿足需求 1】魔王圖片預留空間
        bossImageLabel = new JLabel("魔王圖片載入中...", SwingConstants.CENTER);
        bossImageLabel.setOpaque(true);
        bossImageLabel.setBackground(new Color(255, 230, 230)); // 淡紅色背景
        bossImageLabel.setPreferredSize(new Dimension(150, 180));
        bossImageLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        
        // 數值區
        bossInfoArea = new JTextArea(6, 20);
        bossInfoArea.setEditable(false);
        bossInfoArea.setFont(new Font("微軟正黑體", Font.PLAIN, 14));

        // 組合魔王區的中間容器
        JPanel bossSubCenter = new JPanel(new BorderLayout(0, 10));
        bossSubCenter.add(bossComboBox, BorderLayout.NORTH);
        bossSubCenter.add(bossImageLabel, BorderLayout.CENTER);
        bossSubCenter.add(new JScrollPane(bossInfoArea), BorderLayout.SOUTH);
        bossPanel.add(bossSubCenter, BorderLayout.CENTER);

        // 將左英雄、右魔王面板合體放入主中央區
        centerPanel.add(heroPanel);
        centerPanel.add(bossPanel);
        add(centerPanel, BorderLayout.CENTER);

        // ==================================================
        // 【裝上馬達：監聽選單切換事件】
        // ==================================================
        heroComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateHeroInfo(heroComboBox.getSelectedIndex());
            }
        });

        bossComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateBossInfo(bossComboBox.getSelectedIndex());
            }
        });

        // 初始畫面的預設值 (第 0 個角色)
        updateHeroInfo(0);
        updateBossInfo(0);

        // ==================================================
        // 【底部：確定按鈕】
        // ==================================================
        JButton confirmBtn = new JButton("雙方準備就緒，全面開戰！");
        confirmBtn.setFont(new Font("微軟正黑體", Font.BOLD, 20));
        confirmBtn.setPreferredSize(new Dimension(0, 50)); // 設定按鈕高度
        
        confirmBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Friend selectedHero = friendList[heroComboBox.getSelectedIndex()];
                Boss selectedBoss = bossList[bossComboBox.getSelectedIndex()];
                
                // 【修改這裡】原本是直接呼叫 parentFrame.startBattle
                // 現在改為：彈出「點數分配視窗」，並將控制權交給它
                new StatDialog(parentFrame, selectedHero, selectedBoss).setVisible(true);
            }
        });
        add(confirmBtn, BorderLayout.SOUTH);
    }

    // 動態更新英雄資訊與圖片提示文字
    private void updateHeroInfo(int index) {
        if (index >= 0 && index < friendList.length) {
            Friend f = friendList[index];
            String info = "「" + f.getIntroduceWord() + "」\n"
                        + "血量 (HP)：" + f.getHP() + "\n"
                        + "普攻攻擊力：" + f.getAttack() + "\n"
                        + "大招攻擊力：" + f.getUltATK() + "\n"
                        + "靈魂瓶次數：" + f.getHealTimes() + "\n"
                        + "初始大招冷卻：" + f.getUltCounter();
            heroInfoArea.setText(info);
            // 【圖片修改重點】
            heroImageLabel.setText(""); // 清空原本的「圖片顯示區」文字
            // 呼叫我們剛寫好的共用工具，設定要縮放成 150x180 大小 (跟原本的佔位區塊一樣大)
            // 假設你的圖片都是 jpg 格式
            ImageIcon icon = parentFrame.loadAndScaleImage("/F" + index + "_select.jpg", 200, 300);
            heroImageLabel.setIcon(icon);
        }
    }

    // 動態更新魔王資訊與圖片提示文字
    private void updateBossInfo(int index) {
        if (index >= 0 && index < bossList.length) {
            Boss b = bossList[index];
            String info = "「" + b.getIntroduceWord() + "」\n"
                        + "血量 (HP)：" + b.getHP() + "\n"
                        + "普攻攻擊力：" + b.getAttack() + "\n"
                        + "大招攻擊力：" + b.getUltATK() + "\n"
                        + "復活（二階）次數：" + b.getFullyRecoveredTimes();
            bossInfoArea.setText(info);
            // 【圖片修改重點】
            bossImageLabel.setText("");
            ImageIcon icon = parentFrame.loadAndScaleImage("/B" + index + "_select.jpg", 200, 300);
            bossImageLabel.setIcon(icon);
        }
    }
}