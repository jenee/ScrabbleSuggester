import java.util.*;
import java.io.*;
import java.lang.String;
import java.util.Collections;

public class ScrabbleSuggester {
   public ArrayList<ScoreWordPair> wordList;
   public int maxSize;
   public File inputFile;
   public Scanner fileScanner;
   public String filepath;
   
   public ScrabbleSuggester() {
      this.maxSize = 25; //113810; // 25 for testing purposes
      this.wordList = new ArrayList<ScoreWordPair>(maxSize);
      this.filepath = "./word_list_moby_crossword-flat/word_list_moby_crossword.flat.txt";
   }
   
   public ScrabbleSuggester( String path ) {
      this.maxSize = 113810;
      this.wordList = new ArrayList<ScoreWordPair>(maxSize);
      this.filepath = path;
   }
   
   public void cleanup() {
      if( this.fileScanner != null ) {
         fileScanner.close();
      }
   }
   
   public boolean openFileScanner() {
      boolean fileFound = false;
      try {
         inputFile = new File(this.filepath);
         if( inputFile.exists() && inputFile.isFile() ) {
            fileScanner = new Scanner( inputFile );
            fileFound = true;
         }
      } catch (FileNotFoundException e ) {
         System.err.println("File not found at "+this.filepath);
         return fileFound;
      }
      return fileFound;
   }
   
   public boolean scoreAndStoreWordsFromFile( ) {
      boolean retVal = true;
      if( this.fileScanner == null ) {
         retVal &= this.openFileScanner();
      }
      int i = 0;
      int testMax= maxSize;
      String line = "";
      int score = -1;
      while ( fileScanner.hasNextLine() && retVal == true && i++ <= testMax ) {
         line = fileScanner.nextLine();
         score = computeScrabbleScore(line);
         ScoreWordPair temp = new ScoreWordPair(score, line);
         System.err.print("\t"+i+": ");
         retVal &= wordList.add(temp);
         System.err.println( wordList.get(i-1) );
      } 
   
      return retVal;
   }
   
   public void sortWordList() {
      Collections.sort(wordList);
      System.err.println("\tAfter sort:");
      for(int i = 0; i < wordList.size(); i++) {
         System.err.println("\t"+i+": "+wordList.get(i) );
      }
   } 
   
   public static boolean isStringInString( String toFind, String str) {
      System.err.println("Find "+toFind+" in "+str);
      return str.contains(toFind);
   }
   
   public static boolean isLetterInString( char letter, String str) {
      String toFind = String.valueOf(letter);
      System.err.println("letter to find as char: \'"+letter+"\' as str:"+toFind);
      System.err.println("Find "+toFind+" in "+str);
      return str.contains(toFind);
   }
   
   public static int computeScrabbleScore(String word) {
      int sum = 0;
      
      for ( char c : word.toUpperCase().toCharArray() ) {
         int letterVal = 0;
         switch( c ) {
            case 'A':
            case 'E':
            case 'I':
            case 'L':
            case 'N':
            case 'O':
            case 'R':
            case 'S':
            case 'T':
            case 'U':
               letterVal = 1;
               break;
            case 'D':
            case 'G':
               letterVal = 2;
               break;
            case 'B':
            case 'C':
            case 'M':
            case 'P':
               letterVal = 3;
               break;
            case 'F':
            case 'H':
            case 'V':
            case 'W':
            case 'Y':
               letterVal = 4;
               break;
            case 'K':
               letterVal = 5;
               break;
            case 'J':
            case 'X':
               letterVal = 8;
               break;
            case 'Q':
            case 'Z':
               letterVal = 10;
               break;
            default:
               System.err.println("non-standard scrabble char \'"+c+"\'; skipping");
               break;
         }
         sum += letterVal;
      }
      return sum;
   }
   
   public static boolean readLinesFromFile(String path) { 
      boolean fileFound = false;
      try {
         File inputFile = new File(path);
         
         Scanner fileScanner = new Scanner( inputFile );
         
         int i = 0;
         int testMax= 25;
         String line = "";
         
         while (i++ <= testMax && fileScanner.hasNextLine() ) {
            line = fileScanner.nextLine();
            System.out.println("line read: "+line);
         }
         fileFound = true;
      } catch (FileNotFoundException e ) {
         System.err.println("File not found at "+path);
         return fileFound;
      }
      return fileFound;
   }
   
   public static boolean printScrabbleScoreWithEachLineInFile(String path) { 
      boolean fileFound = false;
      try {
         File inputFile = new File(path);
         
         Scanner fileScanner = new Scanner( inputFile );
         int score = -1;
         int i = 0;
         int testMax= 25; 
         String line = "";
         
         System.out.println("score,word");
         while (i++ <= testMax && fileScanner.hasNextLine() ) {
            line = fileScanner.nextLine();
            score = computeScrabbleScore(line);
            System.out.println(score+","+line);
         }
         fileFound = true;
      } catch (FileNotFoundException e ) {
         System.err.println("File not found at "+path);
         return fileFound;
      }
      return fileFound;
   }
   
  
   
   /*public static void main(String[] args) throws IOException {

   }*/
   
   
}
