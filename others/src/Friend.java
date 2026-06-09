public class Friend extends Character{
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