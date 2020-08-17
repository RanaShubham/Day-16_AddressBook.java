package AddressBook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CreateFile {
	
	static boolean flag = true;
	static Scanner s = new Scanner(System.in);
	static String info = "";

	//Creates a cache file to store data before saving it in real file
	 static void createCache () throws IOException
	{
		File f = new File (".cache");
		
		if ( f.exists() == false )
		{
			f.createNewFile();
		}
		else
		{
			FileWriter fw = new FileWriter(f);
			fw.write("");
			fw.flush();
			fw.close();
		}
	}
	 
	 //Creates a new user file and transfers data of cache file in the user file
	  void createNewFile () throws Exception
	 {
		createCache();		
		PrintWriter pw = new PrintWriter (".cache");
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
				String add=s.next();
				if (i == 7)
				{
					matchPN(add);
				}
				if (i == 6)
				{
					matchPIN(add);
				}
				info=info+" "+add;
			}
			
			pw.println(info);
			
			System.out.println("Do you want add one more record ? Enter 1 if yes, else enter 0");
			int doYou = s.nextInt();
			
			if (doYou == 0)
			{
				pw.flush();
				pw.close();
				saveFile();
				AddressBook.mainMenu();
				break;
			}
			else
			{
				info="";
				continue;
			}
		}
		pw.flush();
		pw.close();
	 } 
	 
	  void matchPN(String phoneNumToBeMatched) throws IOException
	 {
		 Pattern p = Pattern.compile("(0|91)?[7-9][0-9]{9,9}");
		 Matcher m = p.matcher(phoneNumToBeMatched);
		 
		 if (m.find() == false)
		 {
			 System.out.println("You have entered invalid phone number. Start from the begining");
			 System.exit(0);
		 }
		 createCache();
	 }
	 
	  void matchPIN(String pinToBeMatched) throws IOException
	 {
		 Pattern pPIN = Pattern.compile("[0-9]{6,6}");
		 Matcher mPIN = pPIN.matcher(pinToBeMatched) ;
		 
		 if (mPIN.find() == false)
		 {
			 System.out.println("You have entered invalid pin number. Start from the begining");
			 System.exit(0);
		 }
		 createCache();
	 }
	 
	  	void saveFile () throws IOException
	 {
		 System.out.println("Do you want to save file ? Enter 1 if yes, else enter 0");
		 int flag=s.nextInt();
		 if (flag == 1)
		 {
			 System.out.println("Enter name of the file along with extension"); 
			 String fileName=s.next();
			//System.out.println("Enter the path where you want save the file.");
			//String filePath=s.next();
			 
			 FileManager fManager= new FileManager();
			 fManager.saveCached(fileName);
		 }
		 else
		 {
			 System.out.println("Your file was not saved");
		 }
	 }
	 
}
