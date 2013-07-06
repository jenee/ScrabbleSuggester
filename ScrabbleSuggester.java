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
   
   
   
   /*public static void main(String[] args) throws IOException {

   }*/
   
}
