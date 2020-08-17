package AddressBook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

class SortFile {
	
	static FileManager fm = new FileManager();
	static SortFile sofi = new SortFile();

	public static void fileToSort() throws Exception 
	{
		System.out.println("\nEnter name of the file whose contents you want to sort\n");
		Scanner sc = new Scanner (System.in);
		String fileToSort=sc.next();
		
		fm.show(fileToSort);
		
		System.out.println("Enter 1 if you want to sort it by last Name in records"
				+ "\nEnter 2 if you want to sort it by PIN number");
		int sortWith=sc.nextInt();
		
		switch (sortWith)
		{
			case 1: sofi.sortByLastName(fileToSort);
			AddressBook.mainMenu();
			break;
			case 2: sofi.sortByPIN(fileToSort);
			AddressBook.mainMenu();
			break;
		}
		sc.close();
	}
	
	
	private void sortByPIN(String fileToSort) throws Exception
	{
		CreateFile.createCache();
		//fields Stores all fields in String array. its size is 7 because number of fields is fixed
		String [] fields= new String [7];
		//arr stores all number of elements belonging to a field. This is of length equal to number of records in a file
		String [] arr = new String [fm.noOfRecords(fileToSort)];
		
		BufferedReader brsP = new BufferedReader (new FileReader(fileToSort));
		PrintWriter pwsP = new PrintWriter(".cache");
		String line = brsP.readLine();
		int elementsToSort = 0;
		while ( line != null )
		{
				 fields = line.split(" ");
				 arr[elementsToSort]=fields[6];
				 elementsToSort++;
				 line=brsP.readLine();
		}
		pwsP.flush();
		pwsP.close();
		brsP.close();
		
		Arrays.sort(arr);
		
		BufferedReader brsP1 = new BufferedReader (new FileReader(fileToSort));
		PrintWriter pwsP1 = new PrintWriter(new FileWriter (new File(".cache"),true));

		for (int i=0 ; i<fm.noOfRecords(fileToSort); i++)
		{
			brsP1.mark(10000);
			line=brsP1.readLine();
			while ( line != null)
			{
				fields = line.split(" ");
				
				if (fields[6].equals(arr[i]))
				{
					pwsP1.println(line);
				}
					line=brsP1.readLine();
			}
			brsP1.reset();
		}
		brsP1.close();
		pwsP1.flush();
		pwsP1.close();
		fm.overrideWithCache(fileToSort);
	}

	
	
	private void sortByLastName(String fileToSort) throws IOException
	{

		CreateFile.createCache();
		//fields Stores all fields in String array. its size is 7 because number of fields is fixed
		String [] fields= new String [7];
		//arr stores all number of elements belonging to a field. This is of length equal to number of records in a file
		String [] arr = new String [fm.noOfRecords(fileToSort)];
		
		BufferedReader brsN = new BufferedReader (new FileReader(fileToSort));
		PrintWriter pwsN = new PrintWriter(".cache");
		String line = brsN.readLine();
		int elementsToSort = 0;
		while ( line != null )
		{
				 fields = line.split(" ");
				 arr[elementsToSort]=fields[2];
				 elementsToSort++;
				 line=brsN.readLine();
		}
		pwsN.flush();
		pwsN.close();
		brsN.close();
		
		for (int i=0; i< arr.length; i++)
		{
			System.out.println(arr[i]);
		}
		Arrays.sort(arr);
		for (int i=0; i< arr.length; i++)
		{
			System.out.println(arr[i]);
		}
		
		BufferedReader brsP2 = new BufferedReader (new FileReader(fileToSort));
		PrintWriter pwsP2 = new PrintWriter(new FileWriter (new File(".cache"),true));

		for (int i=0 ; i<fm.noOfRecords(fileToSort); i++)
		{
			brsP2.mark(10000);
			line=brsP2.readLine();
			while ( line != null)
			{
				fields = line.split(" ");
				
				if (fields[2].equals(arr[i]))
				{
					pwsP2.println(line);
				}
					line=brsP2.readLine();
			}
			brsP2.reset();
		}
		brsP2.close();
		pwsP2.flush();
		pwsP2.close();
		fm.overrideWithCache(fileToSort);
	
	}

	
}
//Call override with cache to save sorted cache file