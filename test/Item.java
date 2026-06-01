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

	HealItem(String name, String introduceWord, int addHealTimes, int addHP, boolean fullyRecovered) {
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

	DamageItem(String name, String introduceWord, int ATKTimes, int addUltATK, int addCurse) {
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
