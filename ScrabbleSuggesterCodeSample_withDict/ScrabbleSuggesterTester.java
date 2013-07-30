public class ScrabbleSuggesterTester {

   public static boolean testConstruction() {
      boolean retVal = true;
      
      ScrabbleSuggester s1 = new ScrabbleSuggester();
      if(s1.queryString == "z" && s1.numMatchesDesired == 10) {
         retVal &= true;
      } else {
         System.err.print("Default construction of S1 failed:");
         System.err.println( "str=\""+s1.queryString+"\", k= "+s1.numMatchesDesired);
         retVal &= false;
      }   
         
      ScrabbleSuggester s2 = new ScrabbleSuggester("et");
      if(s2.queryString == "et" && s2.numMatchesDesired == 10) {
         retVal &= true;
      } else {
         System.err.print("Default construction of S2 failed:");
         System.err.println( "str=\""+s2.queryString+"\", k= "+s2.numMatchesDesired);
         retVal &= false;
      }
         
      ScrabbleSuggester s3 = new ScrabbleSuggester("air", 20);
      if(s3.queryString == "air" && s3.numMatchesDesired == 20) {
         retVal &= true;
      } else {
         System.err.print("Default construction of S3 failed:");
         System.err.println( "str=\""+s3.queryString+"\", k= "+s3.numMatchesDesired);
         retVal &= false;
      }
      return retVal;
   }
   
   public static boolean testGetPathOfSmallestLetterContainsFile() {
      boolean retVal = true ;
      
      ScrabbleSuggester sgst = new ScrabbleSuggester("get",10);
      String smallestFile = sgst.getPathOfSmallestLetterContainsFile();
      
      if(smallestFile.equals("./TestOutput/wordsContaining_g.txt") ) {
         retVal &= true;
      } else {
         retVal &= false;
         System.err.println("Smallest file method *may* be in error, wasn't g");
      }
      
      return retVal;
   }
   
   public static void testRunSuggester() {
      
      ScrabbleSuggester sgst = new ScrabbleSuggester("get",10);
      sgst.runSuggester();
      
   }
   
   
   public static boolean runAllStaticScrabbleSuggesterTests() {
      int totalTests = 0;
      int testsPassed = 0;
      
      totalTests++;
      if( testConstruction() ) {
         testsPassed++;
         System.err.println("** passed testConstruction ");
      } else {
         System.err.println("** FAILED testConstruction ");
      }
      
      totalTests++;
      if( testGetPathOfSmallestLetterContainsFile() ) {
         testsPassed++;
         System.err.println("** passed testGetPathOfSmallestLetterContainsFile ");
      } else {
         System.err.println("** FAILED testGetPathOfSmallestLetterContainsFile ");
      }
      
      
      System.err.println("PASSED "+testsPassed+" OF "+totalTests+" TESTS");
      return (totalTests == testsPassed);
   }

   public static void main(String[] args) {
      //runAllStaticScrabbleSuggesterTests();
      testRunSuggester();
   }

}