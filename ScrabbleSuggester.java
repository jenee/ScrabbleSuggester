import java.util.*;
import java.io.*;
import java.lang.String;

public class ScrabbleSuggester {
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
   
   public static boolean printScrabbleScoreForEachLineInFile(String path) { 
      boolean fileFound = false;
      try {
         File inputFile = new File(path);
         
         Scanner fileScanner = new Scanner( inputFile );
         int score = -1;
         int i = 0;
         int testMax= 113810; 
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
   
   public class ScoreWordPair implements Comparable<ScoreWordPair> {
      public final Integer score;
      public final String word;
      
      public ScoreWordPair() {
         score = -1;
         word = "";
         System.err.println("Default constructing a ScoreWordPair :(");
      }
      
      public ScoreWordPair(Integer score, String word) {
         this.score = score;
         this.word = word;
      }
      

      
      /**
       * This -returns 0 if scores AND words are equal,
       *      -returns -1 if this.score < o.score 
       *              OR if scores are equal, and this.word is BEFORE o.word in
       *                    the alphabet
       *      -returns 1 if this.score > o.score 
       *              OR if scores are equal, and this.word is AFTER o.word in
       *                    the alphabet
       * It should never be the case that words are the same and scores are
       *    unequal, so for the sake of simplicity, we're not checking for that
       */
      @Override
      public int compareTo( ScoreWordPair o ) {
         int retVal;
         int scoreCompare = this.getScore().compareTo( o.getScore() );
         if( scoreCompare == 0 ) {
            int wordCompare = this.getWord().compareTo( o.getWord() );
            retVal = wordCompare;
         } else {
            retVal = scoreCompare;
         }
         return retVal;
      }
      
      public Integer getScore() {
         return score;
      }
      
      public String getWord() {
         return word;
      }
   }
   
}
