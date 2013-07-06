import java.lang.String;
import java.util.*;
import java.io.*;

public class ScrabbleSuggesterTester {

   /*tests to see whether a letter exists in a string or not*/
   public static boolean testIsLetterInString() {
      boolean cummRetVal = true;
      boolean retVal = true;
      boolean expected = true;
      ScrabbleSuggester suggester = new ScrabbleSuggester();
      String str1 = "hello";
      String str2 = "What a wonderful day!";
      char ch1 = 'h';
      char ch2 = 'w';
      char ch3 = '*';
      
      
      System.err.println("------ test for " +ch1+" in "+str1);
      expected = true;
      retVal = suggester.isLetterInString(ch1, str1);
      cummRetVal &= (expected == retVal);
      System.err.println("------ expected: "+expected+";\tactual: "+retVal);
      if( !cummRetVal ) {
         return cummRetVal;
      }
      
      System.err.println("------ test for " +ch2+" in "+str1);
      expected = false;
      retVal = suggester.isLetterInString(ch2, str1);
      cummRetVal &= (expected == retVal);
      System.err.println("------ expected: "+expected+";\tactual: "+retVal);
      if( !cummRetVal ) {
         return cummRetVal;
      }
      
      System.err.println("------ test for " +ch3+" in "+str1);
      expected = false;
      retVal = suggester.isLetterInString(ch3, str1);
      cummRetVal &= (expected == retVal);
      System.err.println("------ expected: "+expected+";\tactual: "+retVal);
      if( !cummRetVal ) {
         return cummRetVal;
      }
      
      System.err.println("------ test for " +ch1+" in "+str2);
      expected = true;
      retVal = suggester.isLetterInString(ch1, str2);
      cummRetVal &= (expected == retVal);
      System.err.println("------ expected: "+expected+";\tactual: "+retVal);
      if( !cummRetVal ) {
         return cummRetVal;
      }
      
      System.err.println("------ test for " +ch2+" in "+str2);
      expected = true;
      retVal = suggester.isLetterInString(ch2, str2);
      cummRetVal &= (expected == retVal);
      System.err.println("------ expected: "+expected+";\tactual: "+retVal);
      if( !cummRetVal ) {
         return cummRetVal;
      }
      
      System.err.println("------ test for " +ch3+" in "+str2);
      expected = false;
      retVal = suggester.isLetterInString(ch3, str2);
      cummRetVal &= (expected == retVal);
      System.err.println("------ expected: "+expected+";\tactual: "+retVal);
      if( !cummRetVal ) {
         return cummRetVal;
      }
      
      return cummRetVal;
   }
   
   
   /*tests to see whether a string exists in a string or not*/
   public static boolean testIsStringInString() {
      boolean cummRetVal = true;
      boolean retVal = true;
      boolean expected = true;
      ScrabbleSuggester suggester = new ScrabbleSuggester();
      String str1 = "hello";
      String str2 = "What a label day!";
      String toFind1 = "ha";
      String toFind2 = "el";
      String toFind3 = "hel";
      
      
      System.err.println("------ test for " +toFind1+" in "+str1);
      expected = false;
      retVal = suggester.isStringInString(toFind1, str1);
      cummRetVal &= (expected == retVal);
      System.err.println("------ expected: "+expected+";\tactual: "+retVal);
      if( !cummRetVal ) {
         return cummRetVal;
      }
      
      System.err.println("------ test for " +toFind2+" in "+str1);
      expected = true;
      retVal = suggester.isStringInString(toFind2, str1);
      cummRetVal &= (expected == retVal);
      System.err.println("------ expected: "+expected+";\tactual: "+retVal);
      if( !cummRetVal ) {
         return cummRetVal;
      }
      
      System.err.println("------ test for " +toFind3+" in "+str1);
      expected = true;
      retVal = suggester.isStringInString(toFind3, str1);
      cummRetVal &= (expected == retVal);
      System.err.println("------ expected: "+expected+";\tactual: "+retVal);
      if( !cummRetVal ) {
         return cummRetVal;
      }
      
      System.err.println("------ test for " +toFind1+" in "+str2);
      expected = true;
      retVal = suggester.isStringInString(toFind1, str2);
      cummRetVal &= (expected == retVal);
      System.err.println("------ expected: "+expected+";\tactual: "+retVal);
      if( !cummRetVal ) {
         return cummRetVal;
      }
      
      System.err.println("------ test for " +toFind2+" in "+str2);
      expected = true;
      retVal = suggester.isStringInString(toFind2, str2);
      cummRetVal &= (expected == retVal);
      System.err.println("------ expected: "+expected+";\tactual: "+retVal);
      if( !cummRetVal ) {
         return cummRetVal;
      }
      
      System.err.println("------ test for " +toFind3+" in "+str2);
      expected = false;
      retVal = suggester.isStringInString(toFind3, str2);
      cummRetVal &= (expected == retVal);
      System.err.println("------ expected: "+expected+";\tactual: "+retVal);
      if( !cummRetVal ) {
         return cummRetVal;
      }
      
      return cummRetVal;
   }
   
   
   public static boolean runTests() {
      boolean cummResult = true;
      boolean result;
      
      System.err.println("---Testing isLetterInString----");
      result = testIsLetterInString();
      if( testIsLetterInString() ) {
         System.err.println("PASSED");
      } else {
         System.err.println("FAILED");
      }
      cummResult &= result;
      
      System.err.println("---Testing isStringInString----");
      result = testIsStringInString();
      if( testIsStringInString() ) {
         System.err.println("PASSED");
      } else {
         System.err.println("FAILED");
      }
      cummResult &= result;
      
      return cummResult;
   }
   
   public static void main(String[] args) throws IOException {
      ScrabbleSuggesterTester.runTests();
   }
}
