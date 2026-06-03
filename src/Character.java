public class Character{
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

