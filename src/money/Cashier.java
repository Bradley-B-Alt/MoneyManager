package money;

public class Cashier {
	static CashRegister[] cashie = {new CashRegister(), new CashRegister(), new CashRegister(), new CashRegister(), new CashRegister()};
	
	public static void main(String[] args) {	
		int[] changeArray = new int[5];	
		int[] cash = {0, 0, 0, 0, 0};
		storeChange(cashie[0], cash); //put some change in the register in preparation for bake sale
		System.out.println("Cash register starts operations with " + cashie[0].getStoredCashValue() + " dollars\n");
	
		double cost = 0.90;
		int[] paidWith = {1, 0, 0, 0, 0};
		
		storeChange(cashie[0], paidWith);
		System.out.println("Customer paid " + returnChange(paidWith) + ", cost is " + cost + "\n");
		double owedToCustomer = Double.valueOf(String.format("%.3g", returnChange(paidWith)-cost));
		changeArray = returnChange(owedToCustomer);
		System.out.println("Owed to customer: " + owedToCustomer);
		printMoneyArray(changeArray);
		System.out.println("");
		takeChange(cashie[0], changeArray, paidWith);
		
		System.out.println("Cash register finishes operations with " + String.format("%.3g", cashie[0].getStoredCashValue()) + " dollars");
		
	}
	
	public static void takeChange(CashRegister cashie, int[] changeArray, int[] paidWith) { //subtract coins from register, undo the operation if unsuccessful
		try { 
			takeRawChange(cashie, changeArray);
			System.out.println("Operation successfull!");
		} catch (Exception e) {
			
			try {takeRawChange(cashie, paidWith);} catch (Exception e2) {e.printStackTrace();}
			
			String errored = e.getMessage().substring(11);
			System.out.print("Not enough " + errored + " in register to make change!");
			switch (errored) {
				case "pennies" : cashie.storeNickels(changeArray[3]);
				case "nickels" : cashie.storeDimes(changeArray[2]);
				case "dimes" : cashie.storeQuarters(changeArray[1]);
				case "quarters" : cashie.storeDollars(changeArray[0]);
				case "dollars" :
					break;
				default:
					break;
			}
			System.out.println(" Coins refunded.");
		}
	}
	
	public static void takeRawChange(CashRegister cashie, int[] changeArray) throws Exception {
		cashie.takeDollars(changeArray[0]);
		cashie.takeQuarters(changeArray[1]);
		cashie.takeDimes(changeArray[2]);
		cashie.takeNickels(changeArray[3]);
		cashie.takePennies(changeArray[4]);
	}
	
	public static void storeChange(CashRegister cashie, int[] changeArray) {
		cashie.storeDollars(changeArray[0]);
		cashie.storeQuarters(changeArray[1]);
		cashie.storeDimes(changeArray[2]);
		cashie.storeNickels(changeArray[3]);
		cashie.storePennies(changeArray[4]);
	}
	
	public static int[] returnChange(double amountPaid) {
		amountPaid *= 100;
		int[] finalCount = new int[5]; //0 is dollars, 1 is quarters, etc.
		double afterFactoredDollars = 0;
		double afterFactoredQuarters = 0;
		double afterFactoredDimes = 0;
		double afterFactoredNickels = 0;
		@SuppressWarnings("unused") double afterFactoredPennies = 0;
		
		for(int i=0;i<100;i++) { //remove whole dollars from amount 
			if(((amountPaid-i)%100)==0) {
				finalCount[0] = (int) ((amountPaid-i)/100);
				afterFactoredDollars = i;
			}
		}
		for(int i=0;i<25;i++) {//remove whole quarters from amount
			if(((afterFactoredDollars-i)%25)==0) {
				finalCount[1] = (int) ((afterFactoredDollars-i)/25);
				afterFactoredQuarters = i;
			}
		}	
		for(int i=0;i<10;i++) {//remove whole dimes from amount
			if(((afterFactoredQuarters-i)%10)==0) {
				finalCount[2] = (int) (afterFactoredQuarters-i)/10;
				afterFactoredDimes = i;
			}
		}
		for(int i=0;i<5;i++) {//remove whole nickels from amount
			if(((afterFactoredDimes-i)%5)==0) {
				finalCount[3] = (int) (afterFactoredDimes-i);
				afterFactoredNickels = i;
			}
		}
		for(int i=0;i<1;i++) {//remove whole pennies from amount
			if(((afterFactoredNickels-i)%1)==0) {
				finalCount[4] = (int) (afterFactoredNickels-i);
				afterFactoredPennies = i;
			}
		}
		
		//System.out.println(finalCount[0] + " " + finalCount[1] + " " + finalCount[2] + " " + finalCount[3] + " " + finalCount[4]);
		return finalCount;
	}
	
	public static double returnChange(int[] changeArray) {
		return ((changeArray[0]*1) + (changeArray[1]*.25) + (changeArray[2]*.10) + (changeArray[3]*.05) + (changeArray[4]*.01));
	}
	
	public static void printMoneyArray(int[] arr) {
		String[] texts = {" Dollars"," Quarters"," Dimes"," Nickels"," Pennies"};	
		
		for(int i=0;i<arr.length;i++) {
			System.out.println(arr[i] + texts[i]);
		}
	}
}
