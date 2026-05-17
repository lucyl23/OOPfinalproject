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

    void setATK(int ATK){
        this.ATK = ATK;
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
    private int luck;
    private int onceheal = 150;
    private int healTimes = 2;
    private int ultCounter;
    private final int fullUltCounter;
    private final int fullHP;
    private int powerPoints;
    Friend(String name, int HP, int ATK, int UltATK, int luck, int healTimes, int ultCounter, int powerPoints){
        super(name, HP, ATK, UltATK);
        this.healTimes = healTimes;
        this.ultCounter = ultCounter;
        this.fullUltCounter = ultCounter;
        this.fullHP = HP;
        this.powerPoints = powerPoints;
    }
    
    @Override
    void beAttack(int ATK){
        this.setHP(this.getHP() - ATK);
    }

    @Override
    void beAttack(String ult, int ultATK){
        this.setHP(this.getHP() - ultATK);
    }

    int getFullHP(){
        return this.fullHP;
    }

    int getHealTimes(){
        return this.healTimes;
    }

    int getOnceHeal(){
        return this.onceheal;
    }

    void heal(){
        this.setHP(this.getHP() + this.onceheal);
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

    void setLuck(int luck){
        this.luck = luck;
    }

    int getLuck(){
        return this.luck;
    }

    int getPowerPoints(){
        return this.powerPoints;
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

    int getFullyRecoveredTimes(){
        return this.fullyRecoveredTimes;
    }

    int cutFullyRecoveredTimes(){
        return this.fullyRecoveredTimes-1;
    }
}

public class FIGHT {
    public static void gameCharacterDialogueFriendBeChosen(Friend user){
    //選角確定台詞
        switch (user.getName()){
				case "爆豪勝己":
					System.out.println("爆豪勝己：蛤！不要命令我！\n");
					break;
				case "成步堂龍一":
					System.out.println("成步堂龍一：辯方已準備完畢。\n");
					break;
				case "宮野真守":
					System.out.println("宮野真守：從今以後，你就要跟你的同伴一起拯救佐賀。我就是那個要把你變成偶像的人！\n");
					break;
			}
    }

    public static void gameCharacterDialogueBossBeChosen(Boss badGuy){
        switch (badGuy.getName()){
				case "All For One":
					System.out.println("All For One：我要得到One For All\n");
					break;
				case "艾連葉卡":
					System.out.println("艾連葉卡：我要把巨人從這個世上一隻不剩地驅逐出去！");
					break;
				case "宇智波班":
                    System.out.println("宇智波班：\n");
					break;
		}
    }

    public static void gameCharacterDialogueBossDie(Boss badGuy){
    switch (badGuy.getName()){
		        case "All For One":
					System.out.println("All For One：One For All是我的啊啊啊啊啊");
					break;
				case "艾連葉卡":
					System.out.println("艾連葉卡：吶...只要把海的另一端的敵人全部殺光...我們就能獲得自由了嗎？");
					break;
				case "宇智波班":
					System.out.println("宇智波班：我不過是想在這虛幻的世界上，畫上一個真實的句點。");
					break;
        }
    }

    public static void gameCharacterDialogueBossAttack(Boss badGuy){
        switch (badGuy.getName()){
				case "All For One":
					System.out.println("All For One：我要奪走你的個性。");
					break;
				case "艾連葉卡":
					System.out.println("艾連葉卡：\n敬告所有尤米爾的子民，\n我的名字是艾連葉卡，正透過始祖巨人的力量與所有尤米爾的子民對話。\n帕拉迪島上所有用以打造高牆的硬質化已解除，埋藏其中的所有巨人已經開始行動。\n我的目的是保護我成長的帕拉迪島上的人，但世界不僅希望消滅帕拉迪島上的人，更渴望將所有尤米爾子民趕盡殺絕。\n我拒絕接受他們的期望，城牆裡的巨人將會踏遍這座島以外的大地，直到將所有生命都從這世上驅除殆盡。");
					break;
				case "宇智波班":
                    if(Math.random() <= 0.5){
                        System.out.println("宇智波班：天礙震星");
                    }
                    else{
                        System.out.println("宇智波班：完全體須佐能乎");
                    }
					break;
			}
    }

    public static void showCharacterInfo(Friend[] friendList, Scanner scn){
        System.out.println("請輸入角色編號選擇要看誰的能力值！");
        int characterNumber = scn.nextInt();
        System.out.println(friendList[characterNumber-1].getName() + "　血量：" + friendList[characterNumber-1].getHP() + "　普攻攻擊力：" + friendList[characterNumber-1].getAttack() + "　大招攻擊力：" + friendList[characterNumber-1].getUltATK() + "　加血次數" + friendList[characterNumber-1].getHealTimes());
    }

    public static Friend userChooseCharacter(Friend[] friendList, Scanner scn){
        System.out.println("請輸入編號選擇夥伴！");
        int chooseFriend = scn.nextInt();
        Friend user = friendList[chooseFriend-1];
        return user;
    }

    public static void setCharacterValues(Friend user, Scanner scn){
        //設定角色數值
        System.out.println("分配你的實打實攻擊力和幸運值（關乎爆擊與閃避），你有" + user.getPowerPoints() + "點可以分配，輸入你要分配多少給攻擊力，剩下就是幸運值");
        //這邊可以加上下限限制的if-else
        int plusATK = scn.nextInt();
        user.setLuck(user.getPowerPoints() - plusATK);
        user.setATK(user.getAttack() + plusATK);
    }

    public static int fighting(Boss badGuy, Friend user, Scanner scn){
    int round = 0;  // 回合計次
    round += 1;

    //玩家回合開始
    System.out.println("輪到你的回合！決定你的美妙舞姿吧！\n1 攻擊 2 回血 \n請輸入行動編號進行動作呀呼");
    String move = scn.next();

    //判斷行動跟多載
    if(move.equals("1")){
        String useUlt = "Yes";

        if (user.getUltCounter() == 0){ //這次攻擊是大招
            badGuy.beAttack(useUlt, user.getUltATK());
            user.resetUltCounter();

            System.out.println("釋放大招！！！！！！！");       // 每個角色自己的大招台詞
			switch (user.getName()){
				case "爆豪勝己":
					System.out.println("爆豪勝己：打爆你 Howitzer Impact");
					break;
				case "成步堂龍一":
					System.out.println("成步堂龍一：異議！！(異議阿哩！)");
					break;
				case "宮野真守":
                    if(Math.random() <= 0.5){
                        System.out.println("宮野真守：沒錯，我就是kira。");
                    }
                    else{
                        System.out.println("宮野真守：居合手刀。");
                    }
					break;
			}
            System.out.println("我方攻擊" + user.getUltATK() + "點傷害");
        }
        else{
            double critChance = user.getLuck()/1000; //這邊除1000讓下面*10讓數字比較好看
            if(Math.random() < critChance){ //有爆擊
                System.out.println("爆擊！！");
                long critATK = Math.round(user.getAttack()*2.5); //round的回傳型態是long
                badGuy.beAttack((int)critATK*10); //強制轉型
            }
            badGuy.beAttack(user.getAttack());
            user.cutUltCounter();
            System.out.println("我方攻擊" + user.getAttack() + "點傷害");
            System.out.println("再" + user.getUltCounter() + "次攻擊後會釋放大招\n");
        }
    }
    if(move.equals("2")){ //回血
        if(user.getHealTimes()>0){
            user.heal();
            if(user.getHP() > user.getFullHP()){ //血量上限
                System.out.println("我方回復" + (user.getOnceHeal() - (user.getHP() - user.getFullHP())) + "點血量");
                user.setHP(user.getFullHP());
            }
            System.out.println("我方回復" + user.getOnceHeal() + "點血量");
            System.out.println("還剩下" + user.getHealTimes() + "瓶靈魂瓶");
        }
        else{
            System.out.println("你的靈魂瓶已用完，你怎麼忘記了，下去。");
        }
    }

    //badGuy回合開始
    System.out.println("\n魔王的回合，小心點！");
    user.beAttack(badGuy.getAttack());
    gameCharacterDialogueBossAttack(badGuy);
    System.out.println("敵方攻擊" + badGuy.getAttack() + "點傷害");

    return round;
}

public static void roundEnd(int round, Boss badGuy, Friend user) {
	System.out.println("第" + round + "回合戰況");

    //讓魔王的血量不會是負的
    int printBossHP = badGuy.getHP();
    if(badGuy.getHP() < 0){
        printBossHP = 0;
    }

	System.out.println("我方剩餘血量" + user.getHP() + "管　　　敵方剩餘血量" + printBossHP + "管");
	System.out.println("------------------------------"); //30個斜線
}

    public static void main(String[] args){
        //建立物件之夥伴
        Friend[] friendList;
        friendList = new Friend[3];
        friendList[0] = new Friend("爆豪勝己", 200, 30, 350, 0, 5, 4, 100);
        friendList[1] = new Friend("成步堂龍一", 350, 50, 300, 0, 4, 3, 100);
        friendList[2] = new Friend("宮野真守", 400, 70, 200, 0, 2, 2, 100);    

        //建立物件之魔王
        Boss[] bossList;
        bossList = new Boss[3];
        bossList[0] = new Boss("All For One", 400, 60, 0, 1);
        bossList[1] = new Boss("艾連葉卡", 500, 40, 0, 1);
        bossList[2] = new Boss("宇智波班", 700, 80, 0, 0);

        Scanner scn = new Scanner(System.in);

        System.out.println("角色列表 \n1 爆豪勝己 大・爆・殺・神 Dynamight / 2 成步堂龍一 百戰百勝 / 3 宮野真守 殘念王子系之心中神");
        System.out.println("要看角色數值嗎？　1 要 / 2 不要");
        int whetherShowInfo = scn.nextInt();

        int watchTimes = 0; //我不想讓它不會停下來所以做了計次
        while(whetherShowInfo == 1){
            showCharacterInfo(friendList, scn); //友方顯示數值
            watchTimes += 1;
            if(watchTimes == friendList.length){
                System.out.println("好了看完了");
                break;
            }
            System.out.println("要繼續看嗎？　1 要 / 2 不要");
            whetherShowInfo = scn.nextInt();
        }
        
        Friend user = userChooseCharacter(friendList, scn); //使用者選擇角色
        gameCharacterDialogueFriendBeChosen(user); //氣氛組之角色被選臺詞
        setCharacterValues(user, scn); //設定角色數值

        //使用者選擇魔王
        System.out.println("角色列表 \n1 All For One / 2 艾連葉卡 / 3 宇智波班");
        System.out.println("請輸入編號選擇攻略魔王！");
        int chooseBoss = scn.nextInt();
        Boss badGuy = bossList[chooseBoss-1];

        //戰鬥開始
        System.out.println("玩家：你就是" + badGuy.getName() + "嗎？！我來找你打架了！納命來！！！");
        gameCharacterDialogueBossBeChosen(badGuy); //氣氛組之魔王被選臺詞
        System.out.println("：開始戰鬥吧！");
        System.out.println("==============================");

        //回合中
        while(true){
            if(user.getHP()>0 && badGuy.getHP()>0){
                int round = fighting(badGuy, user, scn);
                roundEnd(round, badGuy, user);
            }
            else if(user.getHP()>0 && badGuy.getHP()<=0){
                if (badGuy.getFullyRecoveredTimes()>0){
                    System.out.println("系統：你以為這種小遊戲大魔王不會有二階嗎？哈哈哈你還是太嫩了！");
                    badGuy.fullyRecovered();
                    System.out.println("魔王血量：" + badGuy.getHP());
                    badGuy.cutFullyRecoveredTimes();
                }
                else{
                    gameCharacterDialogueBossDie(badGuy); //氣氛組之魔王死掉臺詞
                    System.out.println("系統：你是真的太強了。");
                    break;
                }
            }
            else if(user.getHP()<=0 && badGuy.getHP()>0){
                System.out.println("YOU DIED");
                break;
            }
            else if(user.getHP()<=0 && badGuy.getHP()<0){
                System.out.println("兩敗俱傷");
                break;
            }
        }

        //戰鬥結束
        System.out.println("\n系統臺詞：戰鬥結束");
        System.out.println("啊～又是這個夢。");
        scn.close();
    }
}
