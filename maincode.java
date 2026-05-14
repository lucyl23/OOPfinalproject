import java.util.Scanner;
class Character{
    private String name;
    private int HP;
    private int ATK;
    private int UltATK;

    Character(String name, int HP, int ATK, int UltATK){
        this.name = name;
        this.HP = HP;
        this.ATK = ATK;
        this.UltATK = UltATK;
    }

    void setHP(int hp){
        this.HP = hp;
    }

    void beAttack(int ATK){
    }

    void beAttack(String ult, int ultATK){
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
    
    int getUltATK(){
        return UltATK;
    }
}

class Friend extends Character{
    private int healTimes = 2;
    private int ultCounter;
    private final int fullUltCounter;
    Friend(String name, int HP, int ATK, int UltATK, int healTimes, int ultCounter){
        super(name, HP, ATK, UltATK);
        this.healTimes = healTimes;
        this.ultCounter = ultCounter;
        this.fullUltCounter = ultCounter;
    }
    
    @Override
    void beAttack(int ATK){
        this.setHP(this.getHP() - ATK);
    }

    @Override
    void beAttack(String ult, int ultATK){
        this.setHP(this.getHP() - ultATK);
    }

    int getHealTimes(){
        return this.healTimes;
    }

    void heal(){
        this.setHP(this.getHP() + 70);
        this.healTimes -= 1;
    }

    int getUltCounter(){
        return this.ultCounter;
    }

    void cutUltCounter(){
        this.ultCounter -= 1;
    }

    void resetUltCounter(){
        this.ultCounter = fullUltCounter;
    }
}

class Boss extends Character{
    private int fullyRecoveredTimes = 1;

    Boss(String name, int HP, int ATK, int UltATK, int fullyRecoveredTimes){
        super(name, HP, ATK, UltATK);
        this.fullyRecoveredTimes = fullyRecoveredTimes;
    }

    @Override
    void beAttack(int ATK){
        this.setHP(this.getHP() - ATK);
    }

    @Override
    void beAttack(String ult, int ultATK){
        this.setHP(this.getHP() - ultATK);
    }

    void fullyRecovered(){
        fullyRecoveredTimes -= 1;
        this.setHP(500);
    }
}

public class FIGHT {
    public static String fighting(Boss badGuy, Friend user, Scanner scn){
    //玩家回合開始
    //使用者輸入
    System.out.println("輪到你的回合！決定你的美妙舞姿吧！\n 1 攻擊 2 回血 \n 請輸入行動編號進行動作呀呼");
    String move = scn.next();

    //判斷行動跟多載
    if(move.equals("1")){
        //決定這次攻擊是不是大招
        String useUlt = "Yes";
        if (user.getUltCounter() == 0){ //放大招
            badGuy.beAttack(useUlt, user.getUltATK());
            user.resetUltCounter();

            System.out.println("釋放大招！！！！！！！\n");       // 每個角色自己的大招台詞
			switch (user.getName()){
				case "爆豪勝己":
					System.out.println("打爆你 Howitzer Impact\n");
					break;
				case "成步堂龍一":
					System.out.println("異議！！(異議阿哩！)\n");
					break;
				case "宮野真守":
					System.out.println("沒錯，我就是kira。\n");
					break;
			}

            System.out.println("我方攻擊" + user.getUltATK() + "點傷害");
        }
        else{
            badGuy.beAttack(user.getAttack());
            user.cutUltCounter();
            System.out.println("我方攻擊" + user.getAttack() + "點傷害");
            System.out.println("再" + user.getUltCounter() + "次攻擊後會釋放大招");
        }
    }
    if(move.equals("2")){
        if(user.getHealTimes()>0){
            user.heal();
            System.out.println("我方回復70點血量");
        }
        else{
            System.out.println("你的靈魂瓶已用完，你怎麼忘記了，下去。");
        }
    }

    //badGuy回合開始
    user.beAttack(badGuy.getAttack());
    System.out.println("艾連：塔塔開欸");
    System.out.println("敵方攻擊" + badGuy.getAttack() + "點傷害");
    
    return move; //roundEnd要move
}

public static void roundEnd(int round, Boss badGuy, Friend user, String move) {
	System.out.println("第" + round + "回合戰況");
	System.out.println("我方剩餘血量" + user.getHP() + "管\n敵方剩餘血量" + badGuy.getHP() + "管");
	System.out.println("------------------------------"); // 30個斜線
}
    public static void main(String[] args){
        
        //建立物件
        Friend[] friendList;
        friendList = new Friend[3];
        friendList[0] = new Friend("爆豪勝己", 130, 30,80, 5, 4);
        friendList[1] = new Friend("成步堂龍一", 150, 40,60, 4, 3);
        friendList[2] = new Friend("宮野真守", 200, 20, 60, 2, 2);

        Boss[] bossList;
        bossList = new Boss[3];
        bossList[0] = new Boss("艾連葉卡", 500, 40, 0, 1);
        bossList[1] = new Boss("艾連葉卡", 500, 40, 0, 1);
        bossList[2] = new Boss("艾連葉卡", 500, 40, 0, 1);

        //使用者選擇角色與對戰對象
        Scanner scn = new Scanner(System.in);
        System.out.println("角色列表 \n 1 爆豪勝己 大・爆・殺・神 Dynamight / 2 成步堂龍一 百戰百勝 / 3 宮野真守 殘念王子系之心中神 \n 請輸入編號選擇夥伴！");
        int chooseFriend = scn.nextInt();
        Friend user = friendList[chooseFriend-1];

        System.out.println("角色列表 \n 1 艾連葉卡 / 2 艾連葉卡 / 3 艾連葉卡 \n 請輸入編號選擇攻略魔王！");
        int chooseBoss = scn.nextInt();
        Boss badGuy = bossList[chooseBoss-1];

        //戰鬥開始
        System.out.println("玩家：\n你就是" + badGuy.getName() + "嗎？！我來找你打架了！納命來！！！\n");
        System.out.println("艾連葉卡：\n敬告所有尤米爾的子民，\n我的名字是艾連葉卡，正透過始祖巨人的力量與所有尤米爾的子民對話。\n帕拉迪島上所有用以打造高牆的硬質化已解除，埋藏其中的所有巨人已經開始行動。\n我的目的是保護我成長的帕拉迪島上的人，但世界不僅希望消滅帕拉迪島上的人，更渴望將所有尤米爾子民趕盡殺絕。\n我拒絕接受他們的期望，城牆裡的巨人將會踏遍這座島以外的大地，直到將所有生命都從這世上驅除殆盡。\n");
        System.out.println("：開始戰鬥吧！");
        System.out.println("==============================");

        //回合中
        //魔王有一次回滿血那邊還沒用到的樣子
        int round = 1;  // 回合計次，初始為第一回合
        while(true){
            if(user.getHP()>0 && badGuy.getHP()>0){
                String move = fighting(badGuy, user, scn);
                roundEnd(round, badGuy, user, move);
            }
            else if(user.getHP()>0 && badGuy.getHP()<=0){
                System.out.println("你贏啦太強啦");
                break;
            }
            else if(user.getHP()<=0 && badGuy.getHP()>0){
                System.out.println("YOU DIED");
                break;
            }

        round += 1;
        }

        //戰鬥結束
        //這邊感覺是說點話就好
        scn.close();
    }
}
