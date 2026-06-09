public class Boss extends Character{
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