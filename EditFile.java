package AddressBook;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

class EditFile {

	FileManager fm = new FileManager();
	CreateFile cf = new CreateFile();
	
	public static void fileToEdit() throws Exception 
	{
		Scanner f = new Scanner (System.in);
		System.out.println("\nEnter name of the file whose records you want to edit");
		String editThis=f.next();
		
		EditFile ef = new EditFile();
		
		System.out.println("\nSelect the editing operation that you want to do for file "+editThis
				+ "\nEnter 1 to add a record"
				+ "\nEnter 2 to delete a record"
				+ "\nEnter 3 to go back to main menu");
		int flipSwitch = f.nextInt();
		switch (flipSwitch)
		{
			case 1: ef.addRecord(editThis);
					AddressBook.mainMenu();
					break;
			case 2: ef.deleteRecord(editThis);
					break;
			case 3: AddressBook.mainMenu();
					break;
		}
		
	}

	private void deleteRecord(String fileName) throws Exception 
	{
		try {
			fm.show(fileName);
		} catch (IOException e) {
			System.out.println("File you want to edit was not found");
		}
		Scanner deleteThis = new Scanner (System.in);
		System.out.println("Enter the first name of person whose record you want to delete.");
		String firstName=deleteThis.next();
		System.out.println("Enter last name of person whose record you want to delete");
		String lastName=deleteThis.next();
		
		FileReader fr1 = new FileReader (fileName);
		BufferedReader br1 = new BufferedReader(fr1);
		String line = br1.readLine();
		String deletableRecord=null;
		String [] feilds;
		while (line != null)
		{
			feilds= line.split(" ");
			if (feilds[1].equals(firstName) && feilds[2].equals(lastName))
				{
					System.out.println("DELETING RECORD: "+line);
					deletableRecord = line;
					br1.close();
					break;
				}
			
			line = br1.readLine();
		}
		if (deletableRecord == null)
		{
			br1.close();
			System.out.println("No such record found in file: ");
		}
		 
		 CreateFile.createCache();
		 PrintWriter pn1 = new PrintWriter (".cache");
		 BufferedReader br2 = new BufferedReader(new FileReader (fileName));
		 String line1 = br2.readLine();
		 
		 while (line1 != null)
		 {
			 if(line1.equals(deletableRecord))
			 {
				 line1=br2.readLine();
				 continue;
			 }
			 else
			 {
			 pn1.println(line1);
			 line1=br2.readLine();
			 }
		 }
		 pn1.flush();
		 br2.close();
		 pn1.close();
		 
		 System.out.println("\nDo you want to save file with edits you made? "
		 		+ "\n Enter 1 to save"
		 		+ "\n Enter 2 to save as"
		 		+ "\n Enter 3 to discard");
		 
		 Scanner in1 = new Scanner (System.in);
		 int input = in1.nextInt();		 
		 switch (input)
		 {
		 case 1: fm.saveCached(fileName);
		 break;
		 case 2: cf.saveFile();
		 break;
		 case 3: AddressBook.mainMenu();
		 }
	}

	private void addRecord(String fileName) throws IOException 

	{
		try {
			fm.show(fileName);
		} catch (IOException e) {
			System.out.println("File you want to edit was not found");
		}
		
		boolean flag = true;
		cf.createCache();
		Scanner s2 = new Scanner (System.in);
		String info ="";
		PrintWriter pw3 = new PrintWriter (".cache");
		while (flag)
		{
			System.out.println("Enter details of one user in following order"
					+ "\n1. First Name"
					+ "\n2. Last Name"
					+ "\n3. Address"
					+ "\n4. City"
					+ "\n5. State"
					+ "\n6. Pincode"
					+ "\n7. Phone number");
			
			for (int i=1; i<=7; i++)
			{
				String add=s2.next();
				if (i == 7)
				{
					cf.matchPN(add);
				}
				if (i == 6)
				{
					cf.matchPIN(add);
				}
				info=info+" "+add;
			}
			
			pw3.println(info);
			
			System.out.println("Do you want to add one more record ? Enter 1 if yes, else enter 0");
			int doYou = s2.nextInt();
			
			if (doYou == 0)
			{
				pw3.flush();
				pw3.close();
				break;
			}
			else
			{
				info="";
				continue;
			}
		}
		pw3.flush();
		pw3.close();
		fm.saveCached(fileName);
	}

}
