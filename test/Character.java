class Character{
    private String name;
    private int HP;
    private int ATK;
    private int UltATK;
    private String introduceWord;

    Character(String name, String introduceWord, int HP, int ATK, int UltATK){
        this.name = name;
        this.introduceWord = introduceWord;
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
}

class Friend extends Character{
    private int luck;
    private int onceheal = 150;
    private int healTimes = 2;
    private int ultCounter;
    private final int fullUltCounter;
    private int fullHP;                 // 這邊的final刪除囉因為道具會修改
    private int powerPoints;
    Friend(String name, String introduceWord, int HP, int ATK, int UltATK, int luck, int healTimes, int ultCounter, int powerPoints){
        super(name, introduceWord, HP, ATK, UltATK);
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
}

class Boss extends Character{
    private int fullyRecoveredTimes = 1;

    Boss(String name, String introduceWord, int HP, int ATK, int UltATK, int fullyRecoveredTimes){
        super(name, introduceWord, HP, ATK, UltATK);
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
