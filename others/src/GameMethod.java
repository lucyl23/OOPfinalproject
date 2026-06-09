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
