import java.util.Scanner;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

class Character{
    private String name;
    private int HP;
    private int ATK;
    private int UltATK;
    private String introduceWord;
    private String CharacterDialogueBeChosen;
    private String CharacterDialogueAttack1;
    private String CharacterDialogueAttack2;

    Character(String name, String introduceWord, int HP, int ATK, int UltATK, String CharacterDialogueBeChosen, String CharacterDialogueAttack1, String CharacterDialogueAttack2){
        this.name = name;
        this.introduceWord = introduceWord;
        this.HP = HP;
        this.ATK = ATK;
        this.UltATK = UltATK;
        this.CharacterDialogueBeChosen = CharacterDialogueBeChosen;
        this.CharacterDialogueAttack1 = CharacterDialogueAttack1;
        this.CharacterDialogueAttack2 = CharacterDialogueAttack2;
    }

    void setHP(int hp){
        this.HP = hp;
    }

    void setATK(int ATK){
        this.ATK = ATK;
    }
    
    void setUltATK(int UltATK){
        this.UltATK = UltATK;
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
    String getIntroduceWord(){
        return this.introduceWord;
    }
    String getgameCharacterDialogueBeChosen(){
        return this.CharacterDialogueBeChosen;
    }
    String getgameCharacterDialogueAttack1(){
        return this.CharacterDialogueAttack1;
    }
    String getgameCharacterDialogueAttack2(){
        return this.CharacterDialogueAttack2;
    }
}

class Friend extends Character{
    private int luck;
    private int onceheal = 150;
    private int healTimes = 2;
    private int ultCounter;
    private final int fullUltCounter;
    private int fullHP;
    private int powerPoints;
    private Item respawnItem;
    Friend(String name, String introduceWord, int HP, int ATK, int UltATK, int luck, int healTimes, int ultCounter, int powerPoints, String CharacterDialogueBeChosen, String CharacterDialogueAttack1, String CharacterDialogueAttack2){
        super(name, introduceWord, HP, ATK, UltATK, CharacterDialogueBeChosen, CharacterDialogueAttack1, CharacterDialogueAttack2);
        this.luck = luck;
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
	void setHealTimes(int healTimes){
        this.healTimes = healTimes;
    }
	void addHealTimes(int addHealTimes) {
		this.setHealTimes(this.getHealTimes() + addHealTimes);
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
    void setRespawnItem(Item pulledItem){
        this.respawnItem = pulledItem;
    }
    Item getRespawnItem(){
        return this.respawnItem;
    }
    boolean haveRespawnItem(){
        return this.respawnItem != null;
    }
}

class Boss extends Character{
    private int fullyRecoveredTimes = 1;
    private String CharacterDialogueDie;

    Boss(String name, String introduceWord, int HP, int ATK, int UltATK, int fullyRecoveredTimes, String CharacterDialogueBeChosen, String CharacterDialogueAttack1, String CharacterDialogueAttack2, String CharacterDialogueDie){
        super(name, introduceWord, HP, ATK, UltATK, CharacterDialogueBeChosen, CharacterDialogueAttack1, CharacterDialogueAttack2);
        this.fullyRecoveredTimes = fullyRecoveredTimes;
        this.CharacterDialogueDie = CharacterDialogueDie;
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
    String getgameCharacterDialogueDie(){
        return this.CharacterDialogueDie;
    }
}

abstract class Item{   // 抽象類別
    private String name;
	private String introduceWord;
    
    Item(String name, String introduceWord){
        this.name = name;
		this.introduceWord = introduceWord;
    }
    
    String getName(){
        return this.name;
    }

	String getIntroduceWord(){
        return this.introduceWord;
    }
	
	void showInfo(){     // 前言: 獲得了道具！
		System.out.println("道具名稱：" + this.getName() + "\n道具效果：" + this.getIntroduceWord());
	}  
	
    abstract void useItem(Friend user, Boss badGuy);  // 抽象方法，使用道具時呼叫
	
}
// 子類別：回血、傷害
class HealItem extends Item {
	private int addHealTimes = 0;    
	private int addHP = 0;
	private boolean fullyRecovered = false;

	HealItem(String name, String introduceWord, int addHealTimes, int addHP, boolean fullyRecovered){
		super(name, introduceWord);
		this.addHealTimes = addHealTimes;
		this.addHP = addHP;
		this.fullyRecovered = fullyRecovered;
	}

	int getAddHealTimes(){
        return this.addHealTimes;
    }

	int getAddHP(){
        return this.addHP;
    }

	boolean getFullyRecovered(){
		return this.fullyRecovered;
	}

	@Override
	void useItem(Friend user, Boss badGuy) {     // 出現於抽到道具後 & 血量<0(如果可復活)時，前言: 使用了道具！
		if (this.getFullyRecovered()) {
            user.setHP(user.getFullHP()); 
            System.out.println(user.getName() + " 滿血復活！可以再大戰三百回合啦！");
        } 
		
		if (this.addHP > 0) {
            user.setHP(user.getHP() + this.getAddHP());
            System.out.println("血量最大值增加了 " + this.getAddHP() + " 管！(現為 " + user.getHP() + " 管血量)");
        }
		
        if (this.addHealTimes > 0) {
            user.addHealTimes(this.getAddHealTimes());
            System.out.println("靈魂瓶增加了 " + this.addHealTimes + " 瓶！(現為 " + user.getHealTimes() + " 瓶靈魂瓶");
        }
	}
}

class DamageItem extends Item {
	private int ATKTimes = 0;
	private int addUltATK = 0;
	private int addCurse = 0;

	DamageItem(String name, String introduceWord, int ATKTimes, int addUltATK, int addCurse){
		super(name, introduceWord);
		this.ATKTimes = ATKTimes;
		this.addUltATK = addUltATK;
		this.addCurse = addCurse;
	}

	int getATKTimes() {
		return this.ATKTimes;
	}

	int getAddUltATK() {
		return this.addUltATK;
	}

	int getAddCurse() {
		return this.addCurse;
	}

	@Override
	void useItem(Friend user, Boss badGuy){     // 出現於抽到道具後 & 使用者每次攻擊後(二次攻擊/詛咒)，每回合顯示的攻擊要記得變動(用if?)
		if (this.getATKTimes() > 0) {
			badGuy.beAttack(user.getAttack());
			System.out.println("使用道具 " + this.getName() + " 進行第二次攻擊！");   
		}

		if (this.getAddUltATK() > 0) {
			user.setUltATK(user.getUltATK() + this. getAddUltATK());
			System.out.println("大招攻擊增加了 " + this.getAddUltATK() + " 點！(現為" + user.getUltATK() + "點攻擊)");
		}

		if (this.getAddCurse() > 0) {
			badGuy.beAttack(this.getAddCurse());
			System.out.println(badGuy.getName() + "被詛咒了！(失去 " + this.getAddCurse() + " 管血量)");
		}
	}
}

public class FIGHTFinal {
    public static Friend[] getFriendCharacterInfo(String friendListFile){
        List<Friend> friendList = new ArrayList<>();
        try{
            List<String> lines = Files.readAllLines(Paths.get(friendListFile), StandardCharsets.UTF_8);
            for (int i = 2; i < lines.size(); i++) {
                String[] parts = lines.get(i).split(",");

                String name = parts[0];
                String introduceWord = parts[1];
                int HP = Integer.parseInt(parts[2]);
                int ATK = Integer.parseInt(parts[3]);
                int ultATK = Integer.parseInt(parts[4]);
                int luck = Integer.parseInt(parts[5]);
                int healTimes = Integer.parseInt(parts[6]);
                int ultCounter = Integer.parseInt(parts[7]);
                int powerPoints = Integer.parseInt(parts[8]);
                String CharacterDialogueBeChosen = parts[9];
                String CharacterDialogueAttack1 = parts[10];
                String CharacterDialogueAttack2 = parts[11];

                friendList.add(new Friend(name, introduceWord, HP, ATK, ultATK, luck, healTimes, ultCounter, powerPoints, CharacterDialogueBeChosen, CharacterDialogueAttack1, CharacterDialogueAttack2));
        }
    }
        catch (IOException e) {
            System.out.println(e.getMessage());
    }
        return friendList.toArray(new Friend[0]);
    }
    public static Boss[] getBossCharacterInfo(String bossListFile){
        List<Boss> bossList = new ArrayList<>();
        try{
            List<String> lines = Files.readAllLines(Paths.get(bossListFile), StandardCharsets.UTF_8);
            for (int i = 2; i < lines.size(); i++) {
                String[] parts = lines.get(i).split(",");

                String name = parts[0];
                String introduceWord = parts[1];
                int HP = Integer.parseInt(parts[2]);
                int ATK = Integer.parseInt(parts[3]);
                int ultATK = Integer.parseInt(parts[4]);
                int fullyRecoveredTimes = Integer.parseInt(parts[5]);
                String CharacterDialogueBeChosen = parts[6];
                String CharacterDialogueAttack1 = parts[7];
                String CharacterDialogueAttack2 = parts[8];
                String CharacterDialogueDie = parts[9];
                bossList.add(new Boss(name, introduceWord, HP, ATK, ultATK, fullyRecoveredTimes, CharacterDialogueBeChosen, CharacterDialogueAttack1, CharacterDialogueAttack2, CharacterDialogueDie));
        }
    }
        catch (IOException e) {
            System.out.println(e.getMessage());
    }
        return bossList.toArray(new Boss[0]);
    }
    public static HealItem[] getHealItemCharacterInfo(String healItemListFile){
        List<Item> healItemList = new ArrayList<>();
        try{
            List<String> lines = Files.readAllLines(Paths.get(healItemListFile), StandardCharsets.UTF_8);
            for (int i = 2; i < lines.size(); i++) {
                String[] parts = lines.get(i).split(",");

                String name = parts[0];
                String introduceWord = parts[1];
                int addHealTimes = Integer.parseInt(parts[2]);
                int addHP = Integer.parseInt(parts[3]);
                boolean fullyRecovered = Boolean.parseBoolean(parts[4]);
                healItemList.add(new HealItem(name, introduceWord, addHealTimes, addHP, fullyRecovered));
        }
    }
        catch (IOException e) {
            System.out.println(e.getMessage());
    }
        return healItemList.toArray(new HealItem[0]);
    }
    public static DamageItem[] getDamageItemCharacterInfo(String damageItemListFile){
        List<Item> damageItemList = new ArrayList<>();

        try{
            List<String> lines = Files.readAllLines(Paths.get(damageItemListFile), StandardCharsets.UTF_8);
            for (int i = 2; i < lines.size(); i++) {
                String[] parts = lines.get(i).split(",");
                String name = parts[0];
                String introduceWord = parts[1];
                int ATKTimes = Integer.parseInt(parts[2]);
                int addUltATK = Integer.parseInt(parts[3]);
                int addCurse = Integer.parseInt(parts[4]);
                damageItemList.add(new DamageItem(name, introduceWord, ATKTimes, addUltATK, addCurse));
        }
    }
        catch (IOException e) {
            System.out.println(e.getMessage());
    }
        return damageItemList.toArray(new DamageItem[0]);
    }

    public static void gameCharacterDialogueFriendBeChosen(Friend user){
		System.out.println(user.getName() + "：" + user.getgameCharacterDialogueBeChosen());
    }
    public static void gameCharacterDialogueFriendUlt(Friend user){
        System.out.println("釋放大招！！！！！！！");       // 每個角色自己的大招台詞
        if(Math.random() <= 0.5){
            System.out.println(user.getName() + "：" + user.getgameCharacterDialogueAttack1());
        }
        else{
            System.out.println(user.getName() + "：" + user.getgameCharacterDialogueAttack2());
		}
    }
    public static void gameCharacterDialogueBossBeChosen(Boss badGuy){
        System.out.println(badGuy.getName() + "：" + badGuy.getgameCharacterDialogueBeChosen());
    }
    public static void gameCharacterDialogueBossDie(Boss badGuy){
        System.out.println(badGuy.getName() + "：" + badGuy.getgameCharacterDialogueDie());
    }
    public static void gameCharacterDialogueBossAttack(Boss badGuy){
        if(Math.random() <= 0.5){
            System.out.println(badGuy.getName() + "：" + badGuy.getgameCharacterDialogueAttack1());
        }
        else{
            System.out.println(badGuy.getName() + "：" + badGuy.getgameCharacterDialogueAttack2());
		}
    }

    public static void showCharacterInfo(Friend[] friendList, Scanner scn){
        int characterNumber;
        while(true){
            System.out.println("請輸入角色編號選擇要看誰的能力值！");
            characterNumber = scn.nextInt();
            if(characterNumber > friendList.length || characterNumber <= 0){
                System.out.println("啊你很叛逆 已經過18了成熟點 快點再一次");
            }
            else{
                break;
            }
        }
        System.out.println(friendList[characterNumber-1].getName() + "　血量：" + friendList[characterNumber-1].getHP() + "　普攻攻擊力：" + friendList[characterNumber-1].getAttack() + "　大招攻擊力：" + friendList[characterNumber-1].getUltATK() + " (" + friendList[characterNumber-1].getUltCounter() + "次攻擊後)" + "　加血次數" + friendList[characterNumber-1].getHealTimes());
    }
    public static Friend userChooseCharacter(Friend[] friendList, Scanner scn){
        int chooseFriend;
        while(true){
            System.out.println("請輸入編號選擇夥伴！");
            chooseFriend = scn.nextInt();
            if(chooseFriend > friendList.length){
                System.out.println("讓他們等太久他們會不開心 你捨得嗎（水汪汪眼睛）快去接他們");
            }
            else{
                break;
            }
        }
        Friend user = friendList[chooseFriend-1];
        return user;
    }
    public static void setCharacterValues(Friend user, Scanner scn){
        //設定角色數值
        System.out.println("分配你的實打實攻擊力和幸運值（關乎爆擊）\n你有" + user.getPowerPoints() + "點可以分配，輸入你要分配多少給攻擊力，剩下就是幸運值");
        int plusATK = scn.nextInt();
        if(plusATK > user.getPowerPoints() || plusATK < 0){
            System.out.println("啊你很鬧 好啦給你全點幸運不客氣");
            user.setLuck(user.getPowerPoints());
        }
        else{
            user.setLuck(user.getPowerPoints() - plusATK);
            user.setATK(user.getAttack() + plusATK);
        }
    }

    public static Item gachaItem(Item[] itemList){
        double dropRate = 1.0/itemList.length;
        double roll = Math.random();
        for(int i = 0; i < itemList.length; i++){
            if(roll <= dropRate*(i+1)){
                return itemList[i];
            }
        }
        return itemList[itemList.length - 1];
    }

    public static void fighting(Boss badGuy, Friend user, Scanner scn){
    //玩家回合開始
    System.out.println("輪到你的回合！決定你的美妙舞姿吧！\n1 攻擊 2 回血\n請輸入行動編號進行動作呀呼");
    String move = scn.next();

    //判斷行動跟多載
    if(move.equals("1")){
        String useUlt = "Yes";

        if (user.getUltCounter() == 0){ //這次攻擊是大招
            badGuy.beAttack(useUlt, user.getUltATK());
            user.resetUltCounter();
            gameCharacterDialogueFriendUlt(user);
            System.out.println("我方攻擊" + user.getUltATK() + "點傷害");
        }
        else{
            double critChance = user.getLuck()/100.0;
            if(Math.random() < critChance*0.8){ //有爆擊
                System.out.println("爆擊！！");
                long critATK = Math.round(user.getAttack()*4); //round的回傳型態是long
                badGuy.beAttack((int)critATK); //強制轉型
                System.out.println("我方攻擊" + critATK + "點傷害！！");
            }
            else{
                badGuy.beAttack(user.getAttack());
                System.out.println("我方攻擊" + user.getAttack() + "點傷害");
            }
            user.cutUltCounter();
            System.out.println("再" + user.getUltCounter() + "次攻擊後會釋放大招\n");
        }
    }
    if(move.equals("2")){ //回血
        if(user.getHealTimes()>0){
            user.heal();
            if(user.getHP() > user.getFullHP()){ //血量上限
                System.out.println("我方回復" + (user.getOnceHeal() - (user.getHP() - user.getFullHP())) + "點血量 (滿血為" + user.getFullHP() + ")");
                user.setHP(user.getFullHP());
            }
            else{
                System.out.println("我方回復" + user.getOnceHeal() + "點血量");
            }
            System.out.println("還剩下" + user.getHealTimes() + "瓶靈魂瓶");
        }
        else{
            System.out.println("你的靈魂瓶已用完，你怎麼忘記了，下去。");
        }
    }
    else{
        System.out.println("哎呀揮棒落空 下個打席會更好 東買～(Don't mind~)");
    }

    //badGuy回合開始
    System.out.println("\n魔王的回合，小心點！");
    user.beAttack(badGuy.getAttack());
    gameCharacterDialogueBossAttack(badGuy);
    System.out.println("敵方攻擊" + badGuy.getAttack() + "點傷害");
}

    public static void roundEnd(int round, Boss badGuy, Friend user) {
	    System.out.println("\n第" + round + "回合戰況");

        //讓魔王的血量不會是負的
        int printBossHP = badGuy.getHP();
            if(badGuy.getHP() < 0){
        printBossHP = 0;
        }
	    //讓使用者的血量不會是負的
        int printUserHP = user.getHP();
        if(badGuy.getHP() < 0){
            printUserHP = 0;
        }


	    System.out.println("我方剩餘血量" + printUserHP + "管　　　敵方剩餘血量" + printBossHP + "管　　　我方剩餘" + user.getHealTimes() + "瓶靈魂瓶");
	    System.out.println("------------------------------"); //30個斜線
    }

    public static void main(String[] args){
        //建立物件之夥伴
        Friend[] friendList;
        friendList = new Friend[3];
        friendList = getFriendCharacterInfo("friendListFile.txt");

        //建立物件之魔王
        Boss[] bossList;
        bossList = new Boss[3];
        bossList = getBossCharacterInfo("bossListFile.txt");

        //建立物件之道具
        HealItem[] healItemList;
        DamageItem[] damageItemList;
        healItemList = new HealItem[3];
        damageItemList = new DamageItem[3];
        healItemList = getHealItemCharacterInfo("healItemListFile.txt");
        damageItemList = getDamageItemCharacterInfo("damageItemListFile.txt");

        Scanner scn = new Scanner(System.in);
        System.out.println("角色列表 \n1 " + friendList[0].getName() + " " + friendList[0].getIntroduceWord() + " / 2 " + friendList[1].getName() + " " + friendList[1].getIntroduceWord() + " / 3 " + friendList[2].getName() + " " + friendList[2].getIntroduceWord());
        int whetherShowInfo;
        while(true){
            System.out.println("要看角色數值嗎？　1 要 / 2 不要");
            whetherShowInfo = scn.nextInt();
            if(whetherShowInfo != 1 && whetherShowInfo != 2){
                System.out.println("問你要或不要就兩個選項欸哥 這還可以亂選一通那很帥啦");
            }
            else{
                break;
            }
        }
        
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
            while(whetherShowInfo != 1 && whetherShowInfo != 2){
                System.out.println("好好選（瞪）（鄧不利多）（我是哈利波特顆顆顆）");
                whetherShowInfo = scn.nextInt();
            }
            if(whetherShowInfo == 2){
                break; 
            }
        }
        
        Friend user = userChooseCharacter(friendList, scn); //使用者選擇角色
        gameCharacterDialogueFriendBeChosen(user); //氣氛組之角色被選臺詞
        System.out.println();
        setCharacterValues(user, scn); //設定角色數值

        //使用者選擇魔王
        System.out.println("");
        System.out.println("角色列表 \n1 " + bossList[0].getName() + " " + bossList[0].getIntroduceWord() + " / 2 " + bossList[1].getName() + " " + bossList[1].getIntroduceWord() + " / 3 " + bossList[2].getName() + " " + bossList[2].getIntroduceWord());
        System.out.println("請輸入編號選擇攻略魔王！");
        Boss badGuy = null;
        int chooseBoss = scn.nextInt();
        if(chooseBoss > bossList.length){
            System.out.println("你怕了吧抓 好啦我直接幫你抽一個 不要怕你是最強的啾咪");
            int randomIndex = (int)(Math.random() * bossList.length);
            badGuy = bossList[randomIndex];
            System.out.println("我幫你抽到的是" + badGuy.getName() + "　他有點強　好好加油吧～");
        }
        else{
            badGuy = bossList[chooseBoss-1];
            System.out.println("你選擇的是" + badGuy.getName());
        }
        System.out.println(badGuy.getName() + "：" + badGuy.getgameCharacterDialogueBeChosen() + "\n");

        //道具
        System.out.println("抽夥伴力量環節～～～（給我興奮點）\n1 好嗨好嗨啊哈哈啊哈哈 2 威威孟孟旅行三百天～");
        int temp = scn.nextInt();
        if(temp == 1){
            System.out.println("好聽話你真棒啾咪<3 給你一個大拇指印章跟抽夥伴力量的資格 太挺了");
        }
        else if(temp == 2){
            System.out.println("厲害崴孟讓旅途順暢無阻～");
        }
        else{
            System.out.println("啊你很鬧");
        }
        Item pulledItem;
        if (Math.random() <= 0.5){
            pulledItem = gachaItem(healItemList);
        }
        else{
            pulledItem = gachaItem(damageItemList);
        }
        System.out.println("好啦 你抽到的是" + pulledItem.getName() + "　下面是他的詳細資料！");
        pulledItem.showInfo();

        boolean isReviveItem = false;
        if (pulledItem instanceof HealItem) {
            HealItem tempItem = (HealItem) pulledItem; //tempItem temporary暫時的
            if (tempItem.getFullyRecovered() == true) {
                isReviveItem = true;
            }
        }

        if(isReviveItem){
            user.setRespawnItem(pulledItem);
            System.out.println("這道具等你死掉會自動發動");
        }
        else{
            pulledItem.useItem(user, badGuy);
        }

        //戰鬥開始
        System.out.println("\n前置作業結束！戰鬥開始！");
        System.out.println("玩家：你就是" + badGuy.getName() + "嗎？！我來找你打架了！納命來！！！");
        gameCharacterDialogueBossBeChosen(badGuy); //氣氛組之魔王被選臺詞
        System.out.println("：開始戰鬥吧！");
        System.out.println("==============================");
        int round = 0;  // 回合初設
        //回合中
        while(true){
            if(user.getHP()>0 && badGuy.getHP()>0){
                round += 1; // 回合計次
                fighting(badGuy, user, scn);
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

                if (user.haveRespawnItem()) {
                    System.out.println("在Uber Eats上點得到 眼罩 眼影 眼線筆 但點不到一秒落淚的演技\nUber Eats（應該）都點得到");
                    user.getRespawnItem().useItem(user, badGuy);
                    user.setRespawnItem(null); // 消耗掉道具，避免無限復活
                    continue; // 回到迴圈開頭，繼續戰鬥！
        }
                System.out.println("YOU DIED");
                break;
            }
            else if(user.getHP()<=0 && badGuy.getHP()<0){
                if (user.haveRespawnItem()) {
                    System.out.println("在Uber Eats上點得到 眼罩 眼影 眼線筆 但點不到一秒落淚的演技\nUber Eats（應該）都點得到");
                    user.getRespawnItem().useItem(user, badGuy);
                    user.setRespawnItem(null);
                }
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
                System.out.println("兩敗俱傷");
                break;
            }
        }
        //戰鬥結束
        System.out.println("\n系統：戰鬥結束。");
        System.out.println("：啊～又是這個夢。");
        scn.close();
    }
}