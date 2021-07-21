package Assignment3;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;


public class SentimentAnalysis {

	public static void main(String[] args) {
		
//Read in a new file
		try {
			//Debugging line
			//Scanner in = new Scanner(new File("/Users/Saira/Desktop/Eclipse Workspace 2021/340 Data Structures and Algorithms/src/Assignment3/sentiments.txt"));
			Scanner in1 = new Scanner(System.in);
			System.out.println("Enter the path to a sentiment file:");
			String dictionaryFile = in1.nextLine();
			Scanner in = new Scanner(new File(dictionaryFile));
	
	HashTable<String, Integer> Sents = new HashTable<String, Integer>(50);
	
	
//Split file into Keys and Values		
		while(in.hasNextLine()) {
			String line = in.nextLine();
			String[] parts = line.split(",");
			String lineKey = parts[0];
			int lineVal = Integer.parseInt(parts[1]);

//Hashtable is resized from via the insert() method
			Sents.insert(lineKey, lineVal);
		}
		
	//Error checking	(Tests HashTable)
//System.out.println(Sents.lookup("abusive"));	
//System.out.println("Entries = "+Sents.getEntries());
//System.out.println("maxSize = "+Sents.getMaxSize()); 
//Sents.print();
	//Error checking
		
String[] allInput = new String[200];		
int emptySpot = 0;		
int words = 0;
int sentiment = 0;		
			
//Take in user input		
		Scanner scan = new Scanner(System.in);
		String text = null; 
	System.out.println("Enter text to be analyzed. ");
	System.out.println("Enter 'end' when finished:");
	while (text != "end") {
		
//Ignore punctuation and extra white spaces			
			text = scan.nextLine().toLowerCase().replaceAll("\\p{Punct}", "");
			text = text.replaceAll("\s+", " ");
			
//Stop if "end" is entered			
		if (text.equals("end") ) {
			break;
	}
		String[] inputParts = text.split(" ");
		
//add to allInput		
		for (int i=0; i<inputParts.length; i++) {
//insert at the end of the array
			String insert = inputParts[i];	
			allInput[emptySpot]= insert;
			emptySpot++;		
			words++;
		}
		}
	//error checking	(prints  total user input)
//	System.out.println("Successfully ended.");
//for (int i=0; i<allInput.length; i++) {
//	if(allInput[i]!= null) {
//		System.out.println(allInput[i]);
//	}}
	//error checking	
		

//calculating sentiment	
		String prev = null;
	for (int i=0; i<words; i++) {
		String current = (allInput[i]);

		String curPrev = (prev+" "+current);

//checks one word		
		if(Sents.lookup(current) != null) {
			sentiment= sentiment+Sents.lookup(current);
			prev = current;

//check for two-word phrases
		} else if (Sents.lookup(curPrev) != null) {
			sentiment = sentiment + Sents.lookup(prev+" "+current);
			prev = current;
		}
		prev = current;
	}

double overall = ((double)sentiment/(double)words);		

	System.out.println("Words: "+ words);
	System.out.println("Sentiment: "+ sentiment);
	System.out.println("Overall: "+ new DecimalFormat("#.##").format(overall));
	
				}catch (IOException e) {
					e.printStackTrace();
					System.out.println("File not found.");
				}
	
			
			}
	
}
		
		

	


