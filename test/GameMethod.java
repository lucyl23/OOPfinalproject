import java.util.Scanner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class GameMethod {
    public static Friend[] getFriendCharacterInfo(String friendListFile){
        List<Friend> friendList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(friendListFile, StandardCharsets.UTF_8))) {
        br.readLine();
        br.readLine(); //跳過前兩行

        String line;
        
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");

            String name = parts[0];
            String introduceWord = parts[1];
            int HP = Integer.parseInt(parts[2]);
            int ATK = Integer.parseInt(parts[3]);
            int ultATK = Integer.parseInt(parts[4]);
            int luck = Integer.parseInt(parts[5]);
            int healTimes = Integer.parseInt(parts[6]);
            int ultCounter = Integer.parseInt(parts[7]);
            int powerPoints = Integer.parseInt(parts[8]);

            friendList.add(new Friend(name, introduceWord, HP, ATK, ultATK, luck, healTimes, ultCounter, powerPoints));
        }
    }
        catch (IOException e) {
            System.out.println(e.getMessage());
    }
        return friendList.toArray(new Friend[0]);
    }

    public static Boss[] getBossCharacterInfo(String bossListFile){
        List<Boss> bossList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(bossListFile, StandardCharsets.UTF_8))) {
        br.readLine();
        br.readLine(); //跳過前兩行

        String line;
        
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");

            String name = parts[0];
            String introduceWord = parts[1];
            int HP = Integer.parseInt(parts[2]);
            int ATK = Integer.parseInt(parts[3]);
            int ultATK = Integer.parseInt(parts[4]);
            int fullyRecoveredTimes = Integer.parseInt(parts[5]);
            bossList.add(new Boss(name, introduceWord, HP, ATK, ultATK, fullyRecoveredTimes));
        }
    }
        catch (IOException e) {
            System.out.println(e.getMessage());
    }
        return bossList.toArray(new Boss[0]);
    }

    public static Item[] getItemCharacterInfo(String itemListFile){
        List<Item> itemList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(itemListFile, StandardCharsets.UTF_8))) {
        br.readLine();
        br.readLine(); //跳過前兩行

        String line;
        
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");

            String name = parts[0];
            //itemList.add(new Item(name)); //抽象類別是不是不能這樣寫
        }
    }
        catch (IOException e) {
            System.out.println(e.getMessage());
    }
        return itemList.toArray(new Item[0]);
    }

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

    public static void gameCharacterDialogueFriendUlt(Friend user){
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
        System.out.println("分配你的實打實攻擊力和幸運值（關乎爆擊與閃避）\n你有" + user.getPowerPoints() + "點可以分配，輸入你要分配多少給攻擊力，剩下就是幸運值");
        //這邊可以加上下限限制的if-else
        int plusATK = scn.nextInt();
        user.setLuck(user.getPowerPoints() - plusATK);
        user.setATK(user.getAttack() + plusATK);
    }

    public static void fighting(Boss badGuy, Friend user, Scanner scn){

    //玩家回合開始
    System.out.println("輪到你的回合！決定你的美妙舞姿吧！\n1 攻擊 2 回血 \n請輸入行動編號進行動作呀呼");
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


	System.out.println("我方剩餘血量" + printUserHP + "管　　　敵方剩餘血量" + printBossHP + "管");
	System.out.println("------------------------------"); //30個斜線
}
