import java.util.*;
import java.io.*;
import java.lang.String;
import java.util.Collections;

public class ScrabbleSuggester {
   public int numMatchesDesired;
   public int curNumMatches;
   public String queryString;
   
   
   public ScrabbleSuggester(String queryStr, int k) {
      this.queryString = queryStr;
      this.numMatchesDesired = k;
      this.curNumMatches = 0;
   }
   
   public ScrabbleSuggester(String queryStr) {
      this.queryString = queryStr;
      this.numMatchesDesired = 10;
      this.curNumMatches = 0;
   }
   
   public ScrabbleSuggester() {
      this.queryString = "";
      this.numMatchesDesired = 10;
      this.curNumMatches = 0;
   }
}