// AI版僅供參考

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// GUI 繼承 JFrame 負責畫面，並實作 ActionListener 負責監聽按鈕
public class GUI extends JFrame implements ActionListener {
    
    // 1. 宣告視覺元件
    private JTextArea battleLogArea; // 戰鬥文字紀錄區
    private JLabel userHpLabel;      // 玩家血量顯示
    private JLabel bossHpLabel;      // 魔王血量顯示
    private JButton btnAttack, btnHeal, btnUlt; // 動作按鈕

    // 2. 宣告遊戲狀態與資料 (直接使用你定義好的類別)
    private Friend user;
    private Boss badGuy;
    private int round = 1;

    public GUI() {
        // --- 遊戲初始化 (選角) ---
        gameSetup();

        // --- 介面排版 (使用你講義學到的 BorderLayout) ---
        setTitle("鵝勢力傳說 - 終極決戰");
        setSize(600, 400);
        setLayout(new BorderLayout(10, 10));

        // 北方 (North)：顯示雙方血量的面板 (使用 GridLayout 左右平分)
        JPanel hpPanel = new JPanel(new GridLayout(1, 2));
        userHpLabel = new JLabel("玩家 HP: " + user.getHP(), SwingConstants.CENTER);
        bossHpLabel = new JLabel("魔王 HP: " + badGuy.getHP(), SwingConstants.CENTER);
        userHpLabel.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        bossHpLabel.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        bossHpLabel.setForeground(Color.RED);
        hpPanel.add(userHpLabel);
        hpPanel.add(bossHpLabel);
        add(hpPanel, BorderLayout.NORTH);

        // 中央 (Center)：顯示戰鬥紀錄的文字區與捲軸 (JTextArea + JScrollPane)
        battleLogArea = new JTextArea();
        battleLogArea.setEditable(false); // 設定不可編輯
        battleLogArea.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(battleLogArea, 
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane, BorderLayout.CENTER);

        // 南方 (South)：動作按鈕面板 (使用 FlowLayout)
        JPanel actionPanel = new JPanel(new FlowLayout());
        btnAttack = new JButton("一般攻擊");
        btnHeal = new JButton("使用靈魂瓶 (" + user.getHealTimes() + ")");
        btnUlt = new JButton("發動大招");
        
        // 向按鈕註冊傾聽者 (這就是觸發事件的關鍵)
        btnAttack.addActionListener(this);
        btnHeal.addActionListener(this);
        btnUlt.addActionListener(this);

        actionPanel.add(btnAttack);
        actionPanel.add(btnHeal);
        actionPanel.add(btnUlt);
        add(actionPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        // 開場白印在文字區
        appendLog("【戰鬥開始】");
        appendLog("你選擇了：" + user.getName());
        appendLog("遭遇魔王：" + badGuy.getName() + "\n");
        checkUltStatus();
    }

    // 遊戲開始前的資料載入與選角
    private void gameSetup() {
        // 直接呼叫你 FIGHT0528 寫好的讀檔方法！
        Friend[] friendList = FIGHT0528.getFriendCharacterInfo("friendListFile.txt");
        Boss[] bossList = FIGHT0528.getBossCharacterInfo("bossListFile.txt");

        // 為了不寫複雜的下拉式選單，這裡用 JOptionPane 跳窗讓玩家輸入數字
        String userChoiceStr = JOptionPane.showInputDialog(null, 
            "請輸入編號選擇角色 (1.爆豪 2.成步堂 3.宮野):", "選擇角色", JOptionPane.QUESTION_MESSAGE);
        int userIndex = (userChoiceStr != null && !userChoiceStr.isEmpty()) ? Integer.parseInt(userChoiceStr) - 1 : 0;
        user = friendList[userIndex];

        String bossChoiceStr = JOptionPane.showInputDialog(null, 
            "請輸入編號選擇魔王 (1.AFO 2.艾連 3.宇智波班):", "選擇魔王", JOptionPane.QUESTION_MESSAGE);
        int bossIndex = (bossChoiceStr != null && !bossChoiceStr.isEmpty()) ? Integer.parseInt(bossChoiceStr) - 1 : 0;
        badGuy = bossList[bossIndex];
    }

    // 將文字寫入戰鬥紀錄區的輔助工具
    private void appendLog(String text) {
        battleLogArea.append(text + "\n");
        // 自動捲動到最底下
        battleLogArea.setCaretPosition(battleLogArea.getDocument().getLength());
    }

    // 更新介面上的血量與按鈕狀態
    private void updateUI() {
        userHpLabel.setText(user.getName() + " HP: " + Math.max(0, user.getHP()));
        bossHpLabel.setText(badGuy.getName() + " HP: " + Math.max(0, badGuy.getHP()));
        btnHeal.setText("使用靈魂瓶 (" + user.getHealTimes() + ")");
        checkUltStatus();
    }

    // 檢查大招是否可用
    private void checkUltStatus() {
        if (user.getUltCounter() <= 0) {
            btnUlt.setEnabled(true);
            btnUlt.setText("發動大招 (就緒!)");
        } else {
            btnUlt.setEnabled(false);
            btnUlt.setText("大招冷卻中 (" + user.getUltCounter() + ")");
        }
    }

    // ==========================================
    // 事件處理者：當按鈕被按下時，會執行這裡的邏輯
    // ==========================================
    @Override
    public void actionPerformed(ActionEvent e) {
        appendLog("--- 第 " + round + " 回合 ---");

        // 1. 判斷玩家按了哪個按鈕
        if (e.getSource() == btnAttack) {
            // 爆擊機率計算 (移植自你的 FIGHT 邏輯)
            double critChance = user.getLuck() / 1000.0;
            if (Math.random() < critChance) {
                long critATK = Math.round(user.getAttack() * 2.5);
                badGuy.beAttack((int)critATK);
                appendLog("💥 爆擊！！造成 " + critATK + " 點傷害！");
            } else {
                badGuy.beAttack(user.getAttack());
                appendLog("🗡️ 一般攻擊，造成 " + user.getAttack() + " 點傷害！");
            }
            user.cutUltCounter();

        } else if (e.getSource() == btnUlt) {
            badGuy.beAttack("Yes", user.getUltATK());
            user.resetUltCounter();
            appendLog("✨ 發動大招！造成 " + user.getUltATK() + " 點毀滅傷害！");

        } else if (e.getSource() == btnHeal) {
            if (user.getHealTimes() > 0) {
                user.heal(); // 呼叫你寫好的補血方法
                if (user.getHP() > user.getFullHP()) user.setHP(user.getFullHP());
                appendLog("💖 使用靈魂瓶，恢復了血量！");
            } else {
                appendLog("❌ 你的靈魂瓶已用完，你怎麼忘記了，下去。(浪費一回合)");
            }
        }

        // 2. 魔王存活則反擊
        if (badGuy.getHP() > 0) {
            user.beAttack(badGuy.getAttack());
            appendLog("☠️ " + badGuy.getName() + " 反擊！對你造成 " + badGuy.getAttack() + " 點傷害。");
        } else {
            // 魔王二階機制 (移植)
            if (badGuy.getFullyRecoveredTimes() > 0) {
                appendLog("⚠️ 系統：你以為大魔王沒有二階嗎？太嫩了！");
                badGuy.fullyRecovered();
                badGuy.cutFullyRecoveredTimes(); // 記得消耗次數
            }
        }

        // 3. 更新畫面數值
        updateUI();
        round++;

        // 4. 勝負判定
        if (user.getHP() <= 0 || badGuy.getHP() <= 0) {
            btnAttack.setEnabled(false);
            btnHeal.setEnabled(false);
            btnUlt.setEnabled(false);
            
            if (user.getHP() > 0) {
                appendLog("\n🎉 戰鬥結束，你贏了！");
            } else if (badGuy.getHP() > 0) {
                appendLog("\n💀 YOU DIED...");
            } else {
                appendLog("\n🤝 兩敗俱傷...");
            }
        }
    }

    // 從這裡啟動遊戲介面！
    public static void main(String[] args) {
        new GUI();
    }
}
