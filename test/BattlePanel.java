import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BattlePanel extends JPanel {
    
    private GameFrame parentFrame;
    private Friend user;
    private Boss boss;

    // 角色 UI 元件
    private JProgressBar userHpBar;
    private JProgressBar bossHpBar;
    private JLabel userHpLabel;
    private JLabel bossHpLabel;
    private JLabel userImageLabel;
    private JLabel bossImageLabel;
    
    // 【新增】紀錄角色原始背景色，方便閃紅後恢復
    private Color userDefaultColor = new Color(173, 216, 230);
    private Color bossDefaultColor = new Color(255, 182, 193);

    // 操作按鈕與文字介面
    private JButton attackBtn;
    private JButton ultBtn;
    private JButton healBtn;
    private JTextArea battleLogArea; // 【新增】介面上的對戰紀錄區

    public BattlePanel(GameFrame parentFrame, Friend user, Boss boss) {
        this.parentFrame = parentFrame;
        this.user = user;
        this.boss = boss;

        setLayout(new BorderLayout());

        // ==========================================
        // 1. 中間區域：左右角色排版
        // ==========================================
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        JPanel leftPanel = createCharacterPanel(user.getName(), user.getHP(), user.getFullHP(), true);
        JPanel rightPanel = createCharacterPanel(boss.getName(), boss.getHP(), boss.getHP(), false);

        centerPanel.add(leftPanel);
        centerPanel.add(rightPanel);
        add(centerPanel, BorderLayout.CENTER);

        // ==========================================
        // 2. 底部區域：對戰紀錄區 + 操作按鈕
        // ==========================================
        JPanel bottomContainer = new JPanel(new BorderLayout());
        bottomContainer.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));

        // 【滿足需求 2】對戰紀錄文字區
        battleLogArea = new JTextArea(6, 50);
        battleLogArea.setEditable(false);
        battleLogArea.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(battleLogArea);
        bottomContainer.add(scrollPane, BorderLayout.CENTER);
        
        appendLog("戰鬥開始！" + user.getName() + " VS " + boss.getName());

        // 操作按鈕區
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        attackBtn = new JButton();
        ultBtn = new JButton();
        healBtn = new JButton();

        Font btnFont = new Font("微軟正黑體", Font.BOLD, 20);
        attackBtn.setFont(btnFont);
        ultBtn.setFont(btnFont);
        healBtn.setFont(btnFont);
        updateButtonsState();

        // --- 裝上馬達 1：普通攻擊 ---
        attackBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 1. 扣血與紀錄
                boss.beAttack(user.getAttack());
                user.cutUltCounter();
                updateHpUI();
                appendLog("我方攻擊！對 " + boss.getName() + " 造成 " + user.getAttack() + " 點傷害。 ");
                
                // 【滿足需求 1】觸發敵方受傷變紅特效
                flashImage(bossImageLabel, bossDefaultColor);

                // 2. 結算與反擊
                checkGameStateAndBossTurn();
            }
        });

        // --- 裝上馬達 2：大招按鈕 ---
        ultBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 【滿足需求 3】先展示大招動畫，動畫結束後才執行傷害判定
                showUltimateAnimation();
            }
        });

        // --- 裝上馬達 3：回血按鈕 ---
        healBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(user.getHealTimes() > 0) {
                    user.heal();
                    if(user.getHP() > user.getFullHP()){
                        user.setHP(user.getFullHP());
                    }
                    updateHpUI();
                    
                    appendLog("我方使用靈魂瓶回血！目前剩餘 " + user.getHealTimes() + " 瓶。");
                    // 回血時可以閃綠光
                    flashImage(userImageLabel, userDefaultColor, Color.GREEN); 
                    
                    checkGameStateAndBossTurn();
                }
            }
        });

        buttonPanel.add(attackBtn);
        buttonPanel.add(ultBtn);
        buttonPanel.add(healBtn);
        bottomContainer.add(buttonPanel, BorderLayout.SOUTH);
        
        add(bottomContainer, BorderLayout.SOUTH);
    }

    // ==========================================
    // 小工具：【需求 3】釋放大招動畫 (使用 JDialog)
    // ==========================================
    private void showUltimateAnimation() {
        // 建立一個無邊框的對話框覆蓋在主畫面上
        JDialog ultDialog = new JDialog(parentFrame, "", true); // true 代表是強制互動視窗
        ultDialog.setUndecorated(true);
        ultDialog.setSize(600, 400);
        ultDialog.setLocationRelativeTo(parentFrame); // 讓對話框在畫面正中央
        ultDialog.setLayout(new BorderLayout());

        // 滿版大招圖片與文字設定
        JLabel ultImageLabel = new JLabel("【" + user.getName() + " 大招滿版圖片】", SwingConstants.CENTER);
        ultImageLabel.setOpaque(true);
        ultImageLabel.setBackground(Color.BLACK);
        ultImageLabel.setForeground(Color.YELLOW);
        ultImageLabel.setFont(new Font("微軟正黑體", Font.BOLD, 36));
        
        JLabel ultTextLabel = new JLabel("「釋放大招！！！！」", SwingConstants.CENTER);
        ultTextLabel.setFont(new Font("微軟正黑體", Font.BOLD, 24));
        ultTextLabel.setOpaque(true);
        ultTextLabel.setBackground(Color.BLACK);
        ultTextLabel.setForeground(Color.WHITE);

        ultDialog.add(ultImageLabel, BorderLayout.CENTER);
        ultDialog.add(ultTextLabel, BorderLayout.SOUTH);

        // 設定 Timer，2秒 (2000毫秒) 後自動關閉對話框
        Timer closeTimer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ultDialog.dispose(); // 關閉視窗
                
                // --- 視窗關閉後，真正執行大招傷害判定 ---
                boss.beAttack("Ult", user.getUltATK());
                user.resetUltCounter(); 
                updateHpUI();
                appendLog("★ 大招爆發！對 " + boss.getName() + " 造成高達 " + user.getUltATK() + " 點傷害！");
                flashImage(bossImageLabel, bossDefaultColor);
                
                checkGameStateAndBossTurn(); // 結算回合
            }
        });
        closeTimer.setRepeats(false);
        closeTimer.start();
        
        // 顯示視窗 (程式會停在這裡等它被 dispose)
        ultDialog.setVisible(true); 
    }

    // ==========================================
    // 小工具：【需求 1】受傷閃紅 Timer 特效
    // ==========================================
    private void flashImage(JLabel label, Color defaultColor) {
        flashImage(label, defaultColor, Color.RED); // 預設閃紅色
    }

    private void flashImage(JLabel label, Color defaultColor, Color flashColor) {
        // 先記住原本身上的圖片是什麼
        Icon originalIcon = label.getIcon(); 
        
        label.setIcon(null); // 暫時把圖片拿掉
        label.setBackground(flashColor); // 顯示受傷顏色
        label.setText(flashColor == Color.RED ? "受到攻擊！" : "回復！"); // 顯示提示字

        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setBackground(defaultColor); // 1秒後恢復原色
                label.setText("");                 // 清空文字
                label.setIcon(originalIcon);       // 【關鍵】把原本的圖片裝回去！
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    // ==========================================
    // 小工具：將文字加入對戰紀錄，並自動滾動到底部
    // ==========================================
    private void appendLog(String text) {
        battleLogArea.append(text + "\n");
        // 自動將滾動條拉到最下面
        battleLogArea.setCaretPosition(battleLogArea.getDocument().getLength());
    }

    // ==========================================
    // 結算與敵方反擊邏輯
    // ==========================================
    private void checkGameStateAndBossTurn() {
        if (boss.getHP() <= 0) {
            updateHpUI();
            appendLog("戰鬥結束！你成功擊敗了 " + boss.getName() + "！");
            disableAllButtons();
            return;
        }

        // 敵方反擊
        user.beAttack(boss.getAttack());
        updateHpUI();
        appendLog("敵方反擊！" + boss.getName() + " 對你造成 " + boss.getAttack() + " 點傷害。");
        
        // 【滿足需求 1】我方受到反擊時閃紅
        flashImage(userImageLabel, userDefaultColor);

        if (user.getHP() <= 0) {
            updateHpUI();
            appendLog("💀 YOU DIED... 戰鬥失敗。");
            disableAllButtons();
            return;
        }

        updateButtonsState();
    }

    // ==========================================
    // UI 更新小工具
    // ==========================================
    private void disableAllButtons() {
        attackBtn.setEnabled(false);
        ultBtn.setEnabled(false);
        healBtn.setEnabled(false);
    }

    private void updateButtonsState() {
        if (user.getUltCounter() <= 0) {
            attackBtn.setEnabled(false);
            attackBtn.setText("大招已就緒！");
            ultBtn.setEnabled(true);
            ultBtn.setText("釋放大招！！！");
        } else {
            attackBtn.setEnabled(true);
            attackBtn.setText("攻擊 (大招倒數: " + user.getUltCounter() + ")");
            ultBtn.setEnabled(false);
            ultBtn.setText("大招 (未就緒)");
        }
        
        healBtn.setText("回血 (剩餘: " + user.getHealTimes() + " 瓶)");
        if (user.getHealTimes() <= 0) {
            healBtn.setEnabled(false);
        }
    }

    private void updateHpUI() {
        int currentBossHp = Math.max(0, boss.getHP());
        bossHpBar.setValue(currentBossHp);
        bossHpLabel.setText(currentBossHp + " / " + bossHpBar.getMaximum());

        int currentUserHp = Math.max(0, user.getHP());
        userHpBar.setValue(currentUserHp);
        userHpLabel.setText(currentUserHp + " / " + user.getFullHP());
    }

    // ==========================================
    // 快速生成角色面板
    // ==========================================
    private JPanel createCharacterPanel(String name, int currentHp, int maxHp, boolean isUser) {
        JPanel panel = new JPanel(new BorderLayout(0, 10));

        JLabel nameLabel = new JLabel(name, SwingConstants.CENTER);
        nameLabel.setFont(new Font("微軟正黑體", Font.BOLD, 24));
        panel.add(nameLabel, BorderLayout.NORTH);

        JLabel imageLabel = new JLabel("", SwingConstants.CENTER); // 把文字清空
        imageLabel.setOpaque(true);
        imageLabel.setBackground(isUser ? userDefaultColor : bossDefaultColor);
        imageLabel.setPreferredSize(new Dimension(200, 300));
        imageLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        
        // 【新增】載入戰鬥圖片 (設定為 200x300 大小)
        ImageIcon icon = parentFrame.loadAndScaleImage("images/" + name + ".jpg", 200, 300);
        imageLabel.setIcon(icon);

        panel.add(imageLabel, BorderLayout.CENTER);

        JPanel hpPanel = new JPanel(new BorderLayout());
        JProgressBar hpBar = new JProgressBar(0, maxHp);
        hpBar.setValue(currentHp);
        hpBar.setForeground(isUser ? Color.GREEN : Color.RED);
        hpBar.setPreferredSize(new Dimension(200, 30));
        
        JLabel hpText = new JLabel(currentHp + " / " + maxHp, SwingConstants.CENTER);
        hpText.setFont(new Font("微軟正黑體", Font.BOLD, 16));

        hpPanel.add(hpBar, BorderLayout.CENTER);
        hpPanel.add(hpText, BorderLayout.SOUTH);
        panel.add(hpPanel, BorderLayout.SOUTH);

        if (isUser) {
            userHpBar = hpBar;
            userHpLabel = hpText;
            userImageLabel = imageLabel;
        } else {
            bossHpBar = hpBar;
            bossHpLabel = hpText;
            bossImageLabel = imageLabel;
        }

        return panel;
    }
}