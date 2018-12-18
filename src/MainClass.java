import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class MainClass
{
	private final static int strahdHPMax = 120;
	private final static int richtenHPMax = 90;
	private static Random rand = new Random();
	private static int strahdHP;
	private static int richtenHP;
	private static int order; // strix 1/paultin 2

	public static void main(String[] args)
	{
		start();
	}
	public static void fightStrix()
	{
		int turnNumber = 0;
		System.out.println("Paultin challenges you with Strahd Von Chairovich!");
		sleep(1);
		System.out.println("Go Chair Richten!");
		sleep(1);
		System.out.println("Rolling for initiative...");
		int initiative1 = rand.nextInt(20) + 1;
		int initiative2 = rand.nextInt(20) + 1;
		System.out.println("You got " + initiative1 + " while Paultin got " + initiative2);
		sleep(1);
		if (initiative1 >= initiative2)
		{
			order = 1;
		} else
		{
			order = 2;
		}
		System.out.println("Time for battle!");
		Scanner in = new Scanner(System.in);
		while (strahdHP >= 1 && richtenHP >= 1)
		{
			int selection = 3;
			turnNumber++;
			System.out.println("Strahd Von Charovich: " + strahdHP + "/" + strahdHPMax + "\nChair Richten: "
					+ richtenHP + "/" + richtenHPMax);
			System.out.println("What will Strix do?" + "\n 1:Chair Kick! Power 20, Accuracy 90"
					+ "\n 2:Chair Punch! Power 40 Accuracy 70 " + "\n 3:Panic! Strix gives up a turn due to panicking");
			System.out.print("Selection Number: ");
			try 
			{
				 selection = in.nextInt();
			}
			catch(InputMismatchException ex)
			{
				System.out.println("Incorrect Input\n");
				in.nextLine();
			}
			switch (selection)
			{
			case 1:
				if (order == 1)
				{
					strixKickAttack();
					aiAttack("Paultin");

				} else
				{
					aiAttack("Paultin");
					strixKickAttack();
				}
				break;
			case 2:
				if (order == 1)
				{
					strixPunchAttack();
					aiAttack("Paultin");
				} else
				{
					aiAttack("Paultin");
					strixPunchAttack();
				}
				break;
			case 3:
				if(order == 1)
				{
					strixPanic();
					aiAttack("Paultin");
				}
				else
				{
					aiAttack("Paultin");
					strixPanic();
				}
				break;
			default:
				System.out.println("Incorrect Option");
				aiAttack("Paultin");
				break;
			}
			turnSayings(turnNumber);
		}
		if (strahdHP < 0)
		{
			System.out.println("Strahd Von Chairovich falls to the ground in pieces \n Paultin:Nooo, my chair!");
		} else
		{
			System.out.println(
					"Chair Richten falls to the ground in pieces \n Strix: Nooo! We were going to go to the big leagues!");
		}
		in.close();
	}

	public static void fightPaultin()
	{
		int turnNumber = 0;
		int selection = 3;
		System.out.println("Strix challenges you with Chair Richten!");
		sleep(1);
		System.out.println("Go Strahd Von Chairovich!");
		sleep(1);
		System.out.println("Rolling for initiative...");
		int initiative1 = rand.nextInt(20) + 1;
		int initiative2 = rand.nextInt(20) + 1;
		System.out.println("You got " + initiative1 + " while Strix got " + initiative2);
		if (initiative1 >= initiative2)
		{
			order = 2;
		} else
		{
			order = 1;
		}
		sleep(1);
		System.out.println("Time for battle!");
		Scanner in = new Scanner(System.in);
		while (strahdHP >= 0 && richtenHP >= 0)
		{
			turnNumber++;
			System.out.println(" Strahd Von Charovich: " + strahdHP + "/" + strahdHPMax + "\nChair Richten: "
					+ richtenHP + "/" + richtenHPMax);
			System.out.println("What will Paultin do?" + "\n 1:Chair Punch! Power 20, Accuracy 80"
					+ "\n 2:Chair Slam! Power 40 Accuracy 60 "
					+ "\n 3:I need a drink. Paultin leaves and gets himself a drink for a turn.");
			System.out.print("Selection Number: ");
			try 
			{
				 selection = in.nextInt();
			}
			catch(InputMismatchException ex)
			{
				System.out.println("Incorrect Input\n");
				in.nextLine();
			}
			switch (selection)
			{
			case 1:
				if (order == 2)
				{
					paultinPunch();
					aiAttack("Strix");

				} else
				{
					aiAttack("Strix");
					paultinPunch();
				}
				break;
			case 2:
				if (order == 2)
				{
					paultinSlam();
					aiAttack("Strix");
				} else
				{
					aiAttack("Strix");
					paultinSlam();
				}
				break;
			case 3:
				if(order == 2)
				{
					paultinDrink();
					aiAttack("Strix");
				}
				else
				{
					aiAttack("Strix");
					paultinDrink();
				}
				break;
			default:
				System.out.println("Incorrect Option");
				aiAttack("Strix");
				break;
			}
			turnSayings(turnNumber);
		}
		winner();

		in.close();
	}

	public static void aiAttack(String person)
	{
		int aiSelection = rand.nextInt(5)+1;
		if (aiSelection == 1 || aiSelection == 2)
		{
			if (person.equals("Strix"))
			{
				strixKickAttack();
			} else
			{
				paultinPunch();
			}
		} else if (aiSelection == 3 || aiSelection == 4)
		{
			if (person.equals("Strix"))
			{
				strixPunchAttack();
			} else
			{
				paultinSlam();
			}
		} else
		{
			if (person.equals("Strix"))
			{
				strixPanic();
			} else
			{
				paultinDrink();
			}
		}
	}

	static void sleep(int seconds)
	{
		try
		{
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	static void strixKickAttack()
	{
		System.out.println("Chair Richten uses Chair Kick!");
		sleep(1);
		if (rand.nextInt(100) + 1 <= 90)
		{
			strahdHP -= 20;
			System.out.println("Chair Richten's leg smashes into its opponent for 20 damage!");
		} else
		{
			System.out.println("Chair Richten's leg smashes into nothing...");
		}
		sleep(1);
	}

	static void strixPunchAttack()
	{
		System.out.println("Chair Richten uses Chair Punch!");
		sleep(1);
		if (rand.nextInt(100) + 1 <= 70)
		{
			strahdHP -= 40;
			System.out.println("Chair Richten punches it's opponent for 40 damage!");
		} else
		{
			System.out.println("Chair Richten punches nothing...");
		}
		sleep(1);
	}

	static void strixPanic()
	{
		System.out.println("Strix uses Panic! \n Strix lays down and starts screaming for a turn");
		sleep(1);
	}

	static void paultinPunch()
	{
		System.out.println("Strahd Von Chairovich uses Chair Punch!");
		sleep(1);
		if (rand.nextInt(100) + 1 <= 80)
		{
			richtenHP -= 20;
			System.out.println("Strahd Von Chairovich punches his opponent for 20 damage!");
		} else
		{
			System.out.println("Strahd Von Chairovich punches nothing...");
		}
		sleep(1);
	}

	static void paultinSlam()
	{
		System.out.println("Strahd Von Chairovich uses Chair Slam!");
		sleep(1);
		if (rand.nextInt(100) + 1 <= 60)
		{
			richtenHP -= 40;
			System.out.println("Strahd Von Chairovich slams into its opponent for 40 damage!");
		} else
		{
			System.out.println("Strahd Von Chairovich slams into nothing...");
		}
		sleep(1);
	}

	static void paultinDrink()
	{
		System.out.println("Paultin uses I need a drink. \n Paultin leaves and gets a new drink for a turn");
		sleep(1);
	}

	static void turnSayings(int turnNumber)
	{
		if (turnNumber == 2)
		{
			System.out.println(" Paultin: It's okay, we scared them with the first round, Strahd von Chairovich! "
					+ "\n  Chair Richten, you bastard! After this next attack you won't have a leg to stand on!");
		} else if (turnNumber == 4)
		{
			System.out.println(" Paultin: It appears your chair has not trained hard enough.");
		} else if (turnNumber == 6)
		{
			System.out.println(" Strix: Don't give up Chair Richten! We can still do this!");
		}
		sleep(1);
	}

	static void winner()
	{
		if (strahdHP < 0)
		{
			System.out.println("Strahd Von Chairovich falls to the ground in pieces \n Paultin:Nooo, my beautiful chair!");
		} else
		{
			System.out.println(
					"Chair Richten falls to the ground in pieces \n Strix: Nooo, We were going to go to the big leagues!");
		}
		
		
	}
	static void start()
	{
		System.out.println("Choose Your Fighter! \n Strix with Chair Richten: smaller target, but less HP \n "
				+ "Paultin with Strahd Von Chairovich: bigger target, but more HP");
		System.out.print("Fighter Name: ");
		strahdHP = strahdHPMax;
		richtenHP = richtenHPMax;
		Scanner start = new Scanner(System.in);
		String person = start.nextLine();
		if (person.equalsIgnoreCase("Strix"))
		{
			fightStrix();
		} else if (person.equalsIgnoreCase("Paultin"))
		{
			fightPaultin();
		}
		start.close();
	}

}
