import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class GameMethod{
    public static Friend[] getFriendCharacterInfo(String friendListFile){
        List<Friend> friendList = new ArrayList<>();
        try{
            // 1. 建立一個空的 List，準備用來裝每一行的文字
            List<String> lines = new ArrayList<>();
    
            // 2. 使用 getResourceAsStream 從 JAR 內部讀取檔案 (記得檔名前面加上 "/")
            java.io.InputStream is = GameMethod.class.getResourceAsStream("/" + friendListFile);
    
            // 3. 確保檔案有順利找到
            if (is != null) {
                // 這裡就是我之前提到的 BufferedReader，我們用它把 InputStream 轉成文字一行行讀出來
                java.io.BufferedReader br = new java.io.BufferedReader(
                    new java.io.InputStreamReader(is, StandardCharsets.UTF_8)
                );
                String line;
                while ((line = br.readLine()) != null) {
                    lines.add(line);
                }
                br.close();
            } else {
                System.out.println("系統找不到檔案：" + friendListFile);
            }

            // ==========================================
            // 底下的程式碼完全不用動！它會無縫接軌繼續執行
            // ==========================================
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
            // 1. 建立一個空的 List，準備用來裝每一行的文字
            List<String> lines = new ArrayList<>();
    
            // 2. 使用 getResourceAsStream 從 JAR 內部讀取檔案 (記得檔名前面加上 "/")
            java.io.InputStream is = GameMethod.class.getResourceAsStream("/" + bossListFile);
    
            // 3. 確保檔案有順利找到
            if (is != null) {
                // 這裡就是我之前提到的 BufferedReader，我們用它把 InputStream 轉成文字一行行讀出來
                java.io.BufferedReader br = new java.io.BufferedReader(
                    new java.io.InputStreamReader(is, StandardCharsets.UTF_8)
                );
                String line;
                while ((line = br.readLine()) != null) {
                    lines.add(line);
                }
                br.close();
            } else {
                System.out.println("系統找不到檔案：" + bossListFile);
            }

            // ==========================================
            // 底下的程式碼完全不用動！它會無縫接軌繼續執行
            // ==========================================
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
            // 1. 建立一個空的 List，準備用來裝每一行的文字
            List<String> lines = new ArrayList<>();
    
            // 2. 使用 getResourceAsStream 從 JAR 內部讀取檔案 (記得檔名前面加上 "/")
            java.io.InputStream is = GameMethod.class.getResourceAsStream("/" + healItemListFile);
    
            // 3. 確保檔案有順利找到
            if (is != null) {
                // 這裡就是我之前提到的 BufferedReader，我們用它把 InputStream 轉成文字一行行讀出來
                java.io.BufferedReader br = new java.io.BufferedReader(
                    new java.io.InputStreamReader(is, StandardCharsets.UTF_8)
                );
                String line;
                while ((line = br.readLine()) != null) {
                    lines.add(line);
                }
                br.close();
            } else {
                System.out.println("系統找不到檔案：" + healItemListFile);
            }

            // ==========================================
            // 底下的程式碼完全不用動！它會無縫接軌繼續執行
            // ==========================================
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
            // 1. 建立一個空的 List，準備用來裝每一行的文字
            List<String> lines = new ArrayList<>();
    
            // 2. 使用 getResourceAsStream 從 JAR 內部讀取檔案 (記得檔名前面加上 "/")
            java.io.InputStream is = GameMethod.class.getResourceAsStream("/" + damageItemListFile);
    
            // 3. 確保檔案有順利找到
            if (is != null) {
                // 這裡就是我之前提到的 BufferedReader，我們用它把 InputStream 轉成文字一行行讀出來
                java.io.BufferedReader br = new java.io.BufferedReader(
                    new java.io.InputStreamReader(is, StandardCharsets.UTF_8)
                );
                String line;
                while ((line = br.readLine()) != null) {
                    lines.add(line);
                }
                br.close();
            } else {
                System.out.println("系統找不到檔案：" + damageItemListFile);
            }

            // ==========================================
            // 底下的程式碼完全不用動！它會無縫接軌繼續執行
            // ==========================================
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

}
