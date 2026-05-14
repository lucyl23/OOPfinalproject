// show_info (角色資訊)
// 選角色的同時可以查看角色資訊(選項4)，需要設定使用者輸入非1~4數字時的「輸入錯誤」
// 需要設定加血不可以加超過原本血量，提示語/禁止加血/浪費一回合
// 修改畫面呈現效果或新增ch13內容
// 檔案名稱要改成FIGHT顆顆

// 期望新增：幸運值、使用者自行配點，道具class，魔王重生與暴擊


int round = 1;  // 回合計次，初始為第一回合

public static void roundEnd(int round, Boss badGuy, Friend user, String move) {
	System.out.println("第" + round + "回合戰況");
	if (move.equals("1")){
		if (user.getUltCounter() == 0){
			System.out.println("釋放大招！！！！！！！\n");       // 每個角色自己的大招台詞
			switch (user.getName()){
				case "爆豪勝己":
					System.out.println("\n");
					break;
				case "成步堂龍一":
					System.out.println("異議！！(異議阿哩！)\n");
					break;
				case "宮野真守":
					System.out.println("\n");
					break;
			}
		}
		else{
			System.out.println("我方攻擊"+user.getAttack()+"點傷害\n");
			ultCounter -= 1 ;
			System.out.println("再"+ultCounter+"次攻擊後會釋放大招");
		}
}	
	if (move=="2"){
		System.out.println("我方回復"+user.getHP()+"點血量\n");
}
	System.out.println("敵方攻擊"+badGuy.getAttack()+"點傷害\n_______\n");
	System.out.println("我方剩餘血量"+user.getHP+"管\n敵方剩餘血量"+badGuy.getHP);
	System.out.println("------------------------------"); // 30個斜線
}




