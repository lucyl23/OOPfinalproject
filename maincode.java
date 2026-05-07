import java.util.Scanner;
class Character{
    protected String name;
    protected int HP;
    protected int ATK;
    protected int UltATK;

    Character(String name, int HP, int ATK, int UltATK){
        this.name = name;
        this.HP = HP;
        this.ATK = ATK;
        this.UltATK = UltATK;
    }

    void beAttack(int ATK){
    }

    void beAttack(String ult){
    }

    String getName(){
        return this.name;
    }
    int getHP(){
        return this.HP;
    }
    int getAttack(){
        return this.ATK;
    }
    
}

class Friend extends Character{
    int healTimes = 2;
    int ultCounter;

    Friend(String name, int HP, int ATK, int UltATK, int healTimes, int ultCounter){
        super(name, HP, ATK, UltATK);
        this.healTimes = healTimes;
        this.ultCounter = ultCounter;
    }

    @Override
    void beAttack(int ATK){
        this.HP -= ATK;
    }

    @Override
    void beAttack(String ult){
        this.HP -= 50;
    }

    void heal(){
        this.HP += 40;
        this.healTimes -= 1;
    }

}

class Boss extends Character{
    int fullyRecoveredTimes = 1;

    Boss(String name, int HP, int ATK, int UltATK, int fullyRecoveredTimes){
        super(name, HP, ATK, UltATK);
        this.fullyRecoveredTimes = fullyRecoveredTimes;
    }

    @Override
    void beAttack(int ATK){
        this.HP -= ATK;
    }

    @Override
    void beAttack(String ult){
        this.HP -= 50;
    }

    void fullyRecovered(){
        fullyRecoveredTimes -= 1;
        this.HP = 500;
    }
}

public void fighting(){
    //玩家回合開始
    System.out.println("輪到你的回合！決定你的美妙舞姿吧！\n 請輸入行動編號進行動作呀呼");
    Scanner scn = new Scanner(System.in);
    String move = scn.next();

    //badGuy回合開始
    System.out.println("戦え");

}

public class FIGHT {
    public static void main(String[] args){
        //使用者選擇角色
        System.out.println("角色列表 \n A 爆豪勝己 / B 成步堂龍一 百戰百勝 / C 宮野真守 殘念王子系之心中神 \n 請輸入編號選擇夥伴！");
        Scanner scn = new Scanner(System.in);
        String chooceCharacter = scn.next();

        //建立物件
        Boss badGuy = new Boss("艾連葉卡", 500, 40, 0, 1);
        
        Character user = null;
        switch (chooceCharacter){
            case "A":
                user = new Friend("爆豪勝己", 130, 30,80, 5, 4);
                break;

            case "B":
                user = new Friend("成步堂龍一", 150, 40,60, 4, 3);
                break;

            case "C":
                user = new Friend("宮野真守", 200, 20, 60, 2, 2);
                break;
        }

        //戰鬥開始
        System.out.println("　你就是" + badGuy.getName() + "嗎？！我來找你打架了！納命來！！！");
        System.out.println("艾連葉卡：敬告所有尤米爾的子民，\n我的名字是艾連葉卡，正透過始祖巨人的力量與所有尤米爾的子民對話。\n帕拉迪島上所有用以打造高牆的硬質化已解除，埋藏其中的所有巨人已經開始行動。\n我的目的是保護我成長的帕拉迪島上的人，但世界不僅希望消滅帕拉迪島上的人，更渴望將所有尤米爾子民趕盡殺絕。\n我拒絕接受他們的期望，城牆裡的巨人將會踏遍這座島以外的大地，直到將所有生命都從這世上驅除殆盡");

        roundEnd();
        
    }
}
