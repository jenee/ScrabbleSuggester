import java.util.*;
import java.io.*;
import java.lang.String;
import java.util.Collections;

public class ScrabbleSuggester {
   public int numMatchesDesired;
   public int curNumMatches;
   public String queryString;
   public String pathOfSmallestFile;
   
   
   public ScrabbleSuggester(String queryStr, int k) {
      this.queryString = queryStr;
      this.numMatchesDesired = k;
      this.curNumMatches = 0;
      this.pathOfSmallestFile ="";
   }
   
   public ScrabbleSuggester(String queryStr) {
      this.queryString = queryStr;
      this.numMatchesDesired = 10;
      this.curNumMatches = 0;
      this.pathOfSmallestFile ="";
   }
   
   public ScrabbleSuggester() {
      this.queryString = "z";
      this.numMatchesDesired = 10;
      this.curNumMatches = 0;
      this.pathOfSmallestFile ="";
   }
   
   public String getPathOfSmallestLetterContainsFile() {
      String filePrefix = "./TestOutput/wordsContaining_";
      String fileSuffix = ".txt";
      long minSize = Long.MAX_VALUE;
      
      for( char ch : queryString.toCharArray() ) {
         String tempFilename = filePrefix + String.valueOf(ch) + fileSuffix;
         File targetFile = new File(tempFilename);
         if( targetFile.length() < minSize ) {
            minSize = targetFile.length();
            pathOfSmallestFile = tempFilename;
         }
      }
      
      return pathOfSmallestFile;
   }
   
}