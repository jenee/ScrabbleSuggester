import java.lang.String;
import java.util.*;
import java.io.*;

public class ScrabbleIndexerTester {
   

   /*tests to see whether a letter exists in a string or not*/
   public static boolean testIsLetterInString() {
      boolean cummRetVal = true;
      boolean retVal = true;
      boolean expected = true;
      ScrabbleIndexer indexer = new ScrabbleIndexer();
      String str1 = "hello";
      String str2 = "What a wonderful day!";
      char ch1 = 'h';
      char ch2 = 'w';
      char ch3 = '*';
      
      
      System.err.println("------ test for " +ch1+" in "+str1);
      expected = true;
      retVal = indexer.isLetterInString(ch1, str1);
      cummRetVal &= (expected == retVal);
      System.err.println("------ expected: "+expected+";\tactual: "+retVal);
      if( !cummRetVal ) {
         return cummRetVal;
      }
      
      System.err.println("------ test for " +ch2+" in "+str1);
      expected = false;
      retVal = indexer.isLetterInString(ch2, str1);
      cummRetVal &= (expected == retVal);
      System.err.println("------ expected: "+expected+";\tactual: "+retVal);
      if( !cummRetVal ) {
         return cummRetVal;
      }
      
      System.err.println("------ test for " +ch3+" in "+str1);
      expected = false;
      retVal = indexer.isLetterInString(ch3, str1);
      cummRetVal &= (expected == retVal);
      System.err.println("------ expected: "+expected+";\tactual: "+retVal);
      if( !cummRetVal ) {
         return cummRetVal;
      }
      
      System.err.println("------ test for " +ch1+" in "+str2);
      expected = true;
      retVal = indexer.isLetterInString(ch1, str2);
      cummRetVal &= (expected == retVal);
      System.err.println("------ expected: "+expected+";\tactual: "+retVal);
      if( !cummRetVal ) {
         return cummRetVal;
      }
      
      System.err.println("------ test for " +ch2+" in "+str2);
      expected = true;
      retVal = indexer.isLetterInString(ch2, str2);
      cummRetVal &= (expected == retVal);
      System.err.println("------ expected: "+expected+";\tactual: "+retVal);
      if( !cummRetVal ) {
         return cummRetVal;
      }
      
      System.err.println("------ test for " +ch3+" in "+str2);
      expected = false;
      retVal = indexer.isLetterInString(ch3, str2);
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
      ScrabbleIndexer indexer = new ScrabbleIndexer();
      String str1 = "hello";
      String str2 = "What a label day!";
      String toFind1 = "ha";
      String toFind2 = "el";
      String toFind3 = "hel";
      
      
      System.err.println("------ test for " +toFind1+" in "+str1);
      expected = false;
      retVal = indexer.isStringInString(toFind1, str1);
      cummRetVal &= (expected == retVal);
      System.err.println("------ expected: "+expected+";\tactual: "+retVal);
      if( !cummRetVal ) {
         return cummRetVal;
      }
      
      System.err.println("------ test for " +toFind2+" in "+str1);
      expected = true;
      retVal = indexer.isStringInString(toFind2, str1);
      cummRetVal &= (expected == retVal);
      System.err.println("------ expected: "+expected+";\tactual: "+retVal);
      if( !cummRetVal ) {
         return cummRetVal;
      }
      
      System.err.println("------ test for " +toFind3+" in "+str1);
      expected = true;
      retVal = indexer.isStringInString(toFind3, str1);
      cummRetVal &= (expected == retVal);
      System.err.println("------ expected: "+expected+";\tactual: "+retVal);
      if( !cummRetVal ) {
         return cummRetVal;
      }
      
      System.err.println("------ test for " +toFind1+" in "+str2);
      expected = true;
      retVal = indexer.isStringInString(toFind1, str2);
      cummRetVal &= (expected == retVal);
      System.err.println("------ expected: "+expected+";\tactual: "+retVal);
      if( !cummRetVal ) {
         return cummRetVal;
      }
      
      System.err.println("------ test for " +toFind2+" in "+str2);
      expected = true;
      retVal = indexer.isStringInString(toFind2, str2);
      cummRetVal &= (expected == retVal);
      System.err.println("------ expected: "+expected+";\tactual: "+retVal);
      if( !cummRetVal ) {
         return cummRetVal;
      }
      
      System.err.println("------ test for " +toFind3+" in "+str2);
      expected = false;
      retVal = indexer.isStringInString(toFind3, str2);
      cummRetVal &= (expected == retVal);
      System.err.println("------ expected: "+expected+";\tactual: "+retVal);
      if( !cummRetVal ) {
         return cummRetVal;
      }
      
      return cummRetVal;
   }
   
   
   public static boolean testComputeScrabbleScore() {
      boolean cummRetVal = true;
      ScrabbleIndexer indexer = new ScrabbleIndexer();
      int expected = -1;
      String testStr = "";
      int actual = -1;
      int numTests = 7;
      
      for( int i = 0; i < numTests; i++ ) {
         switch(i) { 
            case 0: 
               //Test case one: "a", expected: 1
               expected = 1;
               testStr = "a";
               break;
            case 1:
               //Test case two: "age", expected: 4
               expected = 4;
               testStr = "age";
               break;
            case 2:
               //test case three: "realize", expected: 16
               expected = 16;
               testStr = "realize";
               break;
            case 3:
               //test case four: "abcdefghijklmnopqrstuvwxyz", expected: 87
               expected = 87;
               testStr = "abcdefghijklmnopqrstuvwxyz";
               break;
            case 4:
               //test case five: "", expected: 0
               expected = 0;
               testStr = "";
               break;
            case 5:
               //test case six: "-", expected 0
               expected = 0;
               testStr = "-";
               break;
            case 6:
               //test case seven: "hi-res", expected: 8
               expected = 8;
               testStr = "hi-res";
               break;
            default:
               System.out.println("No such computeScrabbleScore test as test #"+i);
               break;
         }
         
         actual = indexer.computeScrabbleScore( testStr );
         
         if( actual != expected ) {
            cummRetVal &= false;
            System.out.print("FAILED Test "+(i+1)+", expected "+expected);
            System.out.println(" for \""+testStr+"\", got "+actual); 
            
         } else {
            cummRetVal &= true;
            System.out.println("test "+(i+1)+" for \""+testStr+"\" succeeded");
         }
      }
      return cummRetVal;
   }
   
   public static boolean testReadLinesFromFile() {
      boolean retVal = true;    
      ScrabbleIndexer indexer = new ScrabbleIndexer();
      String path = "/Users/admin/Documents/Code/InterviewCode/Etsy/word_list_moby_crossword-flat/word_list_moby_crossword.flat.txt";
      
      retVal = indexer.readLinesFromFile( path );
      
      return retVal;
   }
   
   public static boolean testPrintScrabbleScoreWithEachLineInFile() {
      boolean retVal = true;    
      ScrabbleIndexer indexer = new ScrabbleIndexer();
      String path = "/Users/admin/Documents/Code/InterviewCode/Etsy/word_list_moby_crossword-flat/word_list_moby_crossword.flat.txt";
      
      retVal = indexer.printScrabbleScoreWithEachLineInFile( path );
      
      return retVal;
   }
   
   //Note: this doesn't actually test whether the word is being added, just
   // that the file is growing
   public static boolean testAddWordToFile() { 
      boolean retVal = true;
      String path = "./TestOutput/testAddWordToFile.txt";
      String wordA = "have";
      String wordB = "you";
      String wordC = "ever";
      String wordD = "been";
      String wordE = "mellow";
      
      try {
         File target = new File(path);
         //System.err.println("opened File target");
         boolean targetDoesNotExist = target.createNewFile();
         //System.err.println("File target attempted creation");

         if( targetDoesNotExist == false ){
            //System.err.println("File target already exists");
            target.delete();
            //System.err.println("File target deleted");
            target.createNewFile();
            //System.err.println("File target re-created");
         }
      

         long origSize = target.length(); //gets the size of the file.
         //target.close();
         long curSize = origSize;
         
         System.err.println("\tTest adding "+wordA+" to "+path);
         ScrabbleIndexer.addWordToFile( wordA, path );     
         //target = new File(path);
         curSize = target.length();
         //target.close();
         if( ( curSize > origSize ) == false ) { 
            System.err.print("\t^^^^FAILED^^^^, origSize= "+origSize+" > curSize");
            System.err.println("= "+curSize);
            retVal &= false;
         }
         
         System.err.println("\tTest adding "+wordA+" to "+path);
         origSize = curSize;
         ScrabbleIndexer.addWordToFile( wordA, path );     
         //target = new File(path);
         curSize = target.length();
         //target.close();
         if( ( curSize == origSize ) == false ) { 
            System.err.print("\t^^^^FAILED^^^^, origSize= "+origSize+"== curSize");
            System.err.println("= "+curSize);
            retVal &= false;
         }
         
         System.err.println("\tTest adding "+wordB+" to "+path);
         origSize = curSize;
         ScrabbleIndexer.addWordToFile( wordB, path );     
         //target = new File(path);
         curSize = target.length();
         //target.close();
         if( ( curSize > origSize ) == false ) { 
            System.err.print("\t^^^^FAILED^^^^, origSize= "+origSize+"> curSize");
            System.err.println("= "+curSize);
            retVal &= false;
         }
         
         System.err.println("\tTest adding "+wordC+" to "+path);
         origSize = curSize;
         ScrabbleIndexer.addWordToFile( wordC, path );     
         //target = new File(path);
         curSize = target.length();
         //target.close();
         if( ( curSize > origSize ) == false ) { 
            System.err.print("\t^^^^FAILED^^^^, origSize= "+origSize+"> curSize");
            System.err.println("= "+curSize);
            retVal &= false;
         }
         
         System.err.println("\tTest adding "+wordD+" to "+path);
         origSize = curSize;
         ScrabbleIndexer.addWordToFile( wordD, path );     
         //target = new File(path);
         curSize = target.length();
         //target.close();
         if( ( curSize > origSize ) == false ) { 
            System.err.print("\t^^^^FAILED^^^^, origSize= "+origSize+"> curSize");
            System.err.println("= "+curSize);
            retVal &= false;
         }
         
         System.err.println("\tTest adding "+wordE+" to "+path);
         origSize = curSize;
         ScrabbleIndexer.addWordToFile( wordE, path );     
         //target = new File(path);
         curSize = target.length();
         //target.close();
         if( ( curSize > origSize ) == false ) { 
            System.err.print("\t^^^^FAILED^^^^, origSize= "+origSize+"> curSize");
            System.err.println("= "+curSize);
            retVal &= false;
         }
         
         System.err.println("\tTest adding "+wordC+" to "+path);
         origSize = curSize;
         ScrabbleIndexer.addWordToFile( wordC, path );     
         //target = new File(path);
         curSize = target.length();
         //target.close();
         if( ( curSize == origSize ) == false ) { 
            System.err.print("\t^^^^FAILED^^^^, origSize= "+origSize+"== curSize");
            System.err.println("= "+curSize);
            retVal &= false;
         }
      } catch (Exception e) {
         System.err.println("Error: " + e.getMessage());
         retVal = false;
         return retVal;
      }    
      

      return retVal;
   }
   
   public static boolean runStaticScrabbleIndexerTests() {
      boolean cummResult = true;
      boolean result;
      
      System.err.println("---Testing isLetterInString----");
      result = testIsLetterInString();
      if( result ) {
         System.err.println("passed");
      } else {
         System.err.println("FAILED");
      }
      cummResult &= result;
      System.err.println("**********************************************");

      System.err.println("---Testing isStringInString----");
      result = testIsStringInString();
      if( result ) {
         System.err.println("passed");
      } else {
         System.err.println("FAILED");
      }
      cummResult &= result;
      System.err.println("**********************************************");

      System.err.println("---Testing computeScrabbleScore----");
      result = testComputeScrabbleScore();
      if( result ) {
         System.err.println("passed");
      } else {
         System.err.println("FAILED");
      }
      cummResult &= result;
      System.err.println("**********************************************");
      
      //ADD MORE FULLY AUTOMATED TESTS ABOVE HERE
      

      System.err.println("----------------------------------------------");
      System.err.println("---BEGIN PARTIALLY-MANUALLY-EVALUATED TESTS---");
      System.err.println("----------------------------------------------");
      System.err.println("----------------------------------------------");
     
      System.err.println("-----Testing readLinesFromFile----------------");

      System.err.println("-----START Output from readLinesFromFile -----");

      result = testReadLinesFromFile();
      
      System.err.println("-----END Output from readLinesFromFile -------");
      if( result ) {
         System.err.println("passed");
      } else {
         System.err.println("FAILED");
      }
      cummResult &= result;
      System.err.println("---------------------------------------------------");

      System.err.println("--Testing testPrintScrabbleScoreWithEachLineInFile--");

      System.err.println("-----START Output from testPrintScrabbleScoreWithEachLineInFile -----");

      result = testPrintScrabbleScoreWithEachLineInFile();
      
      System.err.println("-----END Output from testPrintScrabbleScoreWithEachLineInFile -------");
      if( result ) {
         System.err.println("passed");
      } else {
         System.err.println("FAILED");
      }
      cummResult &= result;
      
      
      System.err.println("---------------------------------------------------");

      System.err.println("----------Testing testAddWordToFile----------");
      result = testAddWordToFile();
      if( result ) {
         System.err.println("passed");
      } else {
         System.err.println("FAILED");
      }
      cummResult &= result;

      //Wrap-up message
      System.err.println("**********************************************");   
      if( cummResult ) {
         System.err.println("All automated tests passed");
      } else  {
         System.err.println("FAILURE of one or more automated tests!");
      } 
      System.err.println("**********************************************");
      System.err.println("**********************************************");
      return cummResult;
   }
   
   public static boolean runNonStaticScrabbleIndexerTests() {
      int totalPassedTests = 0;
      int totalTests = 0;
      boolean retVal = true;
      ScrabbleIndexer ndxr = new ScrabbleIndexer();
      
      System.err.println("######### Testing openFileScanner #########");
      totalTests++;
      retVal &= ndxr.openFileScanner();
      if( retVal ) {
         totalPassedTests++;
         System.err.println("------- PASSED openFileScanner test -------");
      } else {
         System.err.println("XXXXXXX FAILED openFileScanner test XXXXXXX");
      }

      System.err.println("######### Testing scoreAndStoreWordsFromFile #########");
      totalTests++;
      retVal &= ndxr.scoreAndStoreWordsFromFile();
      if( retVal ) {
         totalPassedTests++;
         System.err.println("------- PASSED scoreAndStoreWordsFromFile test -------");
      } else {
         System.err.println("XXXXXXX FAILED scoreAndStoreWordsFromFile test XXXXXXX");
      }
      
      System.err.println("######### Test run of sortWordList #########");
      totalTests++;
      ndxr.sortWordList();
      /*manually checking that it's sorted*/
      retVal = true;
      ScoreWordPair prev = new ScoreWordPair(101, "dummyword!");
      for( ScoreWordPair elem :  ndxr.wordList ) {
         //System.err.print("\tcomparing prev("+prev+") to ");
         //System.err.println("elem("+ elem+") ");
         int prevScore = prev.getScore().intValue();
         int elemScore = elem.getScore().intValue();
         //System.err.println("prev is null? "+(prev != null) );
         //System.err.println(""+prevScore+" > "+elemScore+"? "+( prevScore < elemScore ) );
         if( prev != null && prevScore < elemScore ) {
            retVal &= false;
            break;
         }
         prev = elem;
      }
      if( retVal ) {
         totalPassedTests++;
         System.err.println("------- PASSED sortWordList test -------");
      } else {
         System.err.println("XXXXXXX FAILED sortWordList test XXXXXXX");
      } 
      
      System.err.println("######### Test run of writeSortedWordsToFile #########");
      totalTests++; 
      retVal &= ndxr.writeSortedWordsToFile();
      if( retVal ) {
         totalPassedTests++;
         System.err.println("------- PASSED writeSortedWordsToFile test -------");
      } else {
         System.err.println("XXXXXXX FAILED writeSortedWordsToFile test XXXXXXX");
      }
      
      
      System.err.println("######### Test run of populateFilesForEachLetter #########");
      totalTests++; 
      ndxr.populateFilesForEachLetter();
      if( retVal ) {
         totalPassedTests++;
         System.err.println("------- RAN populateFilesForEachLetter test -------");
      } else {
         System.err.println("XXXXXXX FAILED TO RUN populateFilesForEachLetter test XXXXXXX");
      }
      
      
      
      
      /*
      System.err.println("######### Test run of scoreAndStoreWordsFromFile #########");
      totalTests++; 
      TODO! Figure out test
      //retVal &= ndxr.isWordInFile();
      if( retVal ) {
         totalPassedTests++;
         System.err.println("------- PASSED scoreAndStoreWordsFromFile test -------");
      } else {
         System.err.println("XXXXXXX FAILED scoreAndStoreWordsFromFile test XXXXXXX");
      }
      */
      
      
      
      System.out.println("Passed "+totalPassedTests+" out of "+totalTests+" tests");
      ndxr.cleanup();
      return (totalPassedTests == totalTests ) ;
   }
   
   public static void runAllIndexerTests() {
      System.err.println("################################################");
      System.err.println("################################################");
      System.err.println("############### static tests ###################");
      System.err.println("################################################");
      System.err.println("################################################");

      ScrabbleIndexerTester.runStaticScrabbleIndexerTests();
      
      System.err.println("################################################");
      System.err.println("################################################");
      System.err.println("############ non-static tests ##################");
      System.err.println("################################################");
      System.err.println("################################################");


      ScrabbleIndexerTester.runNonStaticScrabbleIndexerTests();
   
   }
   
   public static void main(String[] args) throws IOException {
      //ScrabbleIndexerTester.runAllIndexerTests();
      
      String filename = String args[1];
      System.err.println("Cmd line param for filename: "+filename);
      ScrabbleIndexer ndxr = new ScrabbleIndexer(filename);
      nxdr.runIndexer();
   }
}
