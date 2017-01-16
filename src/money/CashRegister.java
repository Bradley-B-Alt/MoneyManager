package money;

public class CashRegister {
	int dollars = 0;
	int quarters = 0;
	int dimes = 0;
	int nickels = 0;
	int pennies = 0;
	
	public double getStoredCashValue() {
		return ((dollars*1) + (quarters*.25) + (dimes*.10) + (nickels*.05) + (pennies*.01));
	}
	
	public void storeDollars(int amount) {
		dollars+=amount;
	}
	
	public void storeQuarters(int amount) {
		quarters+=amount;
	}
	
	public void storeDimes(int amount) {
		dimes+=amount;
	}
	
	public void storeNickels(int amount) {
		nickels+=amount;
	}
	
	public void storePennies(int amount) {
		pennies+=amount;
	}
		
	public void takeDollars(int amount) throws Exception{
		if((dollars-=amount)<0) {
			dollars+=amount;
			throw new Exception("Not enough dollars");
		}
	}
	
	public void takeQuarters(int amount) throws Exception{
		if((quarters-=amount)<0) {
			quarters+=amount;
			throw new Exception("Not enough quarters");
		}
	}
	
	public void takeDimes(int amount) throws Exception {
		if((dimes-=amount)<0) {
			dimes+=amount;
			throw new Exception("Not enough dimes");
		}
	}
	
	public void takeNickels(int amount) throws Exception {
		if((nickels-=amount)<0) {
			nickels+=amount;
			throw new Exception("Not enough nickels");
		}
	}
	
	public void takePennies(int amount) throws Exception {
		if((pennies-=amount)<0) {
			pennies+=amount;
			throw new Exception("Not enough pennies");
		}
	}
	
	public int getDollars() {
		return dollars;
	}
	
	public int getQuarters() {
		return quarters;
	}
	
	public int getDimes() {
		return dimes;
	}
	
	public int getNickels() {
		return nickels;
	}
	
	public int getPennies() {
		return pennies;
	}
}
