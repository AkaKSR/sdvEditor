package stringRes;

public class StringMain {
	
	public static void mainMenu() {
		
		System.out.println("Stardew Valley Save Editor - sdvEditor");
		System.out.println();
		System.out.println("----------Menu----------");
		System.out.println("1. Edit Status");
		System.out.println("2. Check Status");
		System.out.println("3. Exit");
		System.out.println("------------------------");
		System.out.println();
		System.out.println("Please enter the Menu Number");
		System.out.print("Menu Num : ");
		
	}
	
	public static void skillMenu() {
		System.out.println(" [Farming]");
		System.out.println(" (0) - Rancher		(1) - Tiller");
		System.out.println(" (2) - Coopmaster	(3) - Shepard");
		System.out.println(" (4) - Artisan		(5) - Agricultist");
		System.out.println();
		System.out.println(" [Fishing]");
		System.out.println(" (6) - Fisher		(7) - Trapper");
		System.out.println(" (8) - Angler		(9) - Pirate");
		System.out.println(" (10) - Mariner		(11) - Luremaster");
		System.out.println();
		System.out.println(" [Foraging]");
		System.out.println(" (12) - Forester	(13) - Forager");
		System.out.println(" (14) - Lumberjack	(15) - Tapper");
		System.out.println(" (16) - Botanist	(17) - Tracker");
		System.out.println();
		System.out.println(" [Mining]");
		System.out.println(" (18) - Miner		(19) - Geologist");
		System.out.println(" (20) - Blacksmith	(21) - Prospector");
		System.out.println(" (22) - Excavator	(23) - Gemologist");
		System.out.println();
		System.out.println(" [Combat]");
		System.out.println(" (24) - Fighter 	(25) - Scout");
		System.out.println(" (26) - Brute		(27) - Defender");
		System.out.println(" (28) - Acrobat		(29) - Desperado");
		
	}
	
	public static void infoSave() {
		System.out.println("-----------------------");
		System.out.println("This Program is main save only");
		System.out.println("Main save file Exam : User_uniqueID");
		System.out.println("Please check file");
	}
	
	public static void titleName() {
		System.out.println("Stardew Valley Save Editor - sdvEditor");
	}
	
	public static void inputSave() {
		System.out.print("Please type your savefile name: ");
	}
	
	public static void errorFile() {
		System.out.println("This savefile is not Stardew Valley savefile.");
		System.out.println("Please check file");
	}
	
	public static void statEdit() {
		System.out.println("Please enter the function you want to change.");
		System.out.println("(name, skill, farmName, favoriteThing, money, health, maxHealth, stamina, maxStamina, MaxItems, farmingLevel, miningLevel, combatLevel, foragingLevel, fishingLevel)");
		System.out.print("command: ");
	}
	
	public static void statEdit1() {
		System.out.println("Please enter the function you want to change.");
		System.out.println("(name, farmName, favoriteThing, money, health, maxHealth, stamina, maxStamina, MaxItems, farmingLevel, miningLevel, combatLevel, foragingLevel, fishingLevel)");
		System.out.print("command: ");
	}
	
	public static void skillEdit() {
		System.out.println("-------Skill Edit-------");
		System.out.println("This function is applied after initializing the skill.");
		System.out.print("Do you agree? (Y/N): ");
	}
	
}
