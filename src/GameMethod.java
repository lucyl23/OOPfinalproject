import java.util.Scanner;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class GameMethod{
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
        System.out.println("請輸入角色編號選擇要看誰的能力值！");
        int characterNumber = scn.nextInt();
        System.out.println(friendList[characterNumber-1].getName() + "　血量：" + friendList[characterNumber-1].getHP() + "　普攻攻擊力：" + friendList[characterNumber-1].getAttack() + "　大招攻擊力：" + friendList[characterNumber-1].getUltATK() + " (" + friendList[characterNumber-1].getUltCounter() + "次攻擊後)" + "　加血次數" + friendList[characterNumber-1].getHealTimes());
    }
    public static Friend userChooseCharacter(Friend[] friendList, Scanner scn){
        System.out.println("請輸入編號選擇夥伴！");
        int chooseFriend = scn.nextInt();
        Friend user = friendList[chooseFriend-1];
        return user;
    }
    public static void setCharacterValues(Friend user, Scanner scn){
        //設定角色數值
        System.out.println("分配你的實打實攻擊力和幸運值（關乎爆擊）\n你有" + user.getPowerPoints() + "點可以分配，輸入你要分配多少給攻擊力，剩下就是幸運值");
        //這邊可以加上下限限制的if-else
        int plusATK = scn.nextInt();
        user.setLuck(user.getPowerPoints() - plusATK);
        user.setATK(user.getAttack() + plusATK);
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

    public static void fighting(Boss badGuy, Friend user, Item pulledItem, Scanner scn){
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
}
