package AddressBook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

class FileManager {
	
	//public static String cachePath = "";
	static Scanner fmx = new Scanner (System.in);
	static FileReader fr = null ;

	
	 void show (String fileName) throws IOException
	{
		try {
			fr = new FileReader(fileName);
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		
		BufferedReader br = new BufferedReader (fr);
		String line = br.readLine();
		System.out.println("_______________________________________________________________________________________");
		System.out.println("--------------------------------THIS IS YOUR FILE--------------------------------------\n");
		 while (line != null)
		 {
			 System.out.println(line);
			 line = br.readLine();
		 }
			System.out.println("_______________________________________________________________________________________");
			System.out.println("--------------------------------THIS IS YOUR FILE--------------------------------------");
			br.close();
		// AddressBook.mainMenu();
		
	}

	public void fileMenu() throws IOException {
		System.out.println("Enter name along with extension of the file that you want to open.");
		String openPath=fmx.next();
		FileManager fmanager = new FileManager();
		fmanager.show(openPath);
	}
	
	//Returns 0 if cache file was appened to another file and 1 if cache file was added to new file
	  int saveCached(String fileName) throws IOException
	 {
		 File f = new File (fileName);
		 
				if (f.exists())
				{
					System.out.println("ATTENTION!!! File " +fileName+ " already exists. Adding new records to it.");
					FileWriter fw = new FileWriter (f, true);
					PrintWriter pwx = new PrintWriter (fw);
					BufferedReader brx = new BufferedReader(new FileReader (".cache"));
					 
					 String lineX = brx.readLine();
					 
					 while (lineX != null)
					 {
						 pwx.println(lineX);
						 lineX=brx.readLine();
					 }
					 brx.close();
					 pwx.flush();
					 pwx.close();
					 
					 return 0;
				}
				else
				{
					f.createNewFile();
				}
		 
		 
		 PrintWriter pn = new PrintWriter (fileName);
		 BufferedReader br = new BufferedReader(new FileReader (".cache"));
		 
		 String line = br.readLine();
		 
		 while (line != null)
		 {
			 pn.println(line);
			 line=br.readLine();
		 }
		 pn.flush();
		 br.close();
		 pn.close();
		 System.out.println("Your file was saved as follows");
		 FileManager fm = new FileManager();
		 fm.show(fileName);
		return 1;
	 }
	  
	  void overrideWithCache(String fileToOverride) throws IOException
	  {
		  	 PrintWriter pn = new PrintWriter (fileToOverride);
			 BufferedReader br = new BufferedReader(new FileReader (".cache"));
			 
			 String line = br.readLine();
			 
			 while (line != null)
			 {
				 pn.println(line);
				 line=br.readLine();
			 }
			 pn.flush();
			 br.close();
			 pn.close();
			 System.out.println("Your file was saved as follows");
			 FileManager fm = new FileManager();
			 fm.show(fileToOverride);
	  }
	  
	 int noOfRecords(String fileName) throws IOException
	  {
		  int noOfRecords=0;
		  BufferedReader brLine = new BufferedReader(new FileReader(fileName));
		  while (brLine.readLine() != null)
		  {
			  ++noOfRecords;
		  }
		  brLine.close();

		  return noOfRecords;
	  }
}
