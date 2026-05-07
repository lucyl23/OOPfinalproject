import java.util.Scanner;
class Character{
    protected String name;
    protected int HP;
    protected int ATK;
    protected int Ult;

    Character(String name, int HP, int ATK, int Ult){
        this.name = name;
        this.HP = HP;
        this.ATK = ATK;
        this.Ult = Ult;
    }

    void beAttack(int ATK){
    }

    void beAttack(String ult){
    }
}

class Friend extends Character{
    int healTimes = 2;

    Friend(String name, int HP, int ATK, int Ult, int healTimes){
        super(name, HP, ATK, Ult);
        this.healTimes = healTimes;
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
        this.HP += 20;
        this.healTimes -= 1;
    }

}

class Boss extends Character{
    int fullyRecoveredTimes = 1;

    Boss(String name, int HP, int ATK, int Ult, int fullyRecoveredTimes){
        super(name, HP, ATK, Ult);
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
        this.HP = 100;
    }
}

// 檔案名稱：FIGHT
public class FIGHT {
    public static void main(String[] args){
        //使用者選擇角色
        System.out.println("角色列表 \n A 爆豪勝己 / B 成步堂龍一 百戰百勝 / C 宮野真守 殘念王子系之心中神 \n 請輸入代碼選擇角色");
        Scanner scn = new Scanner(System.in);
        String chooceCharacter = scn.next();

        //建立物件
        switch (chooceCharacter){
            case "A":
                Friend user = new Friend("爆豪勝己", 130, 30,0, 3);
                break;

            case "B":
                Friend user = new Friend("成步堂龍一", 150, 40,0, 2);
                break;

            case "C":
                Friend user = new Friend("宮野真守", 200, 20,0, 2);
                break;
}

