import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StatDialog extends JDialog {

    private int allocatedAtk = 0;
    private int allocatedLuck;

    public StatDialog(GameFrame parent, Friend user, Boss boss) {
        super(parent, "戰前準備：能力點數分配", true); // true 代表是強制互動視窗
        setSize(400, 250);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        // 預設幸運值為全部點數
        allocatedLuck = user.getPowerPoints();

        // --- 上方提示文字 ---
        JLabel titleLabel = new JLabel("你有 " + user.getPowerPoints() + " 點可以分配", SwingConstants.CENTER);
        titleLabel.setFont(new Font("微軟正黑體", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        // --- 中間滑桿與數值顯示 ---
        JPanel centerPanel = new JPanel(new GridLayout(3, 1));
        JLabel atkLabel = new JLabel("增加攻擊力: 0", SwingConstants.CENTER);
        JLabel luckLabel = new JLabel("幸運值 (爆擊率): " + allocatedLuck, SwingConstants.CENTER);
        atkLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
        luckLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 16));

        // 建立滑桿：最小值 0，最大值為角色的 powerPoints
        JSlider slider = new JSlider(0, user.getPowerPoints(), 0);
        slider.setMajorTickSpacing(10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        // 裝上馬達：滑桿移動時即時更新文字
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                allocatedAtk = slider.getValue();
                allocatedLuck = user.getPowerPoints() - allocatedAtk;
                atkLabel.setText("增加攻擊力: " + allocatedAtk);
                luckLabel.setText("幸運值 (爆擊率): " + allocatedLuck);
            }
        });

        centerPanel.add(slider);
        centerPanel.add(atkLabel);
        centerPanel.add(luckLabel);
        add(centerPanel, BorderLayout.CENTER);

        // --- 下方確定按鈕 ---
        JButton confirmBtn = new JButton("確認分配");
        confirmBtn.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        confirmBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 將數值真正寫入角色的變數中
                user.setATK(user.getAttack() + allocatedAtk);
                user.setLuck(allocatedLuck);
                
                System.out.println("分配完畢！當前攻擊力：" + user.getAttack() + "，幸運值：" + user.getLuck());
                
                dispose(); // 關閉此分配視窗
                
                // 【關鍵】開啟下一個抽道具視窗
                new GachaDialog(parent, user, boss).setVisible(true);
            }
        });
        add(confirmBtn, BorderLayout.SOUTH);
    }
}