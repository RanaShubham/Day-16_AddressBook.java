package AddressBook;

import java.io.IOException;
import java.util.Scanner;

public class AddressBook {
	
	static FileManager fm = new FileManager();
	static CreateFile cf = new CreateFile ();
	public static void main(String[] args) throws Exception 
	{
		mainMenu();
	}

	public static void mainMenu() throws Exception 
	{
		System.out.println("\n----------------Welcome to Adderss book program. Choose an option.----------------"
				+ "\n Press 1 to create new file."
				+ "\n Press 2 to open a file."
				+ "\n Press 3 to for more options."
				+ "\n Press 4 to quit");
		
		Scanner in = new Scanner (System.in);
		int options=in.nextInt();
		
		switch (options)
		{
			case 1: cf.createNewFile();
					break;
			case 2: fm.fileMenu();
					AddressBook.mainMenu();
					break;
			case 4: System.exit(0);
			case 3: moreOptions();
					break;
		}
	}

	private static void moreOptions() throws Exception 
	{
		System.out.println("\nPress 1 to edit a file"
				+ "\nPress 2 to sort the entries in a file"
				+ "\nPress 3 to go back to main menu.");
		
		Scanner in = new Scanner (System.in);
		int moreOptions = in.nextInt();
		
		switch (moreOptions)
		{
			case 1: EditFile.fileToEdit();
			break;
			case 2: SortFile.fileToSort();
			break;
			case 3: mainMenu();
			break;
		}
	}

	
}
