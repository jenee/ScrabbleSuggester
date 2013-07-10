import java.lang.*;

/*
   Code borrowed and modified from original code at:
   http://stackoverflow.com/a/677248/1572766
   
   Modifications: 
      -removed setters 
      -made Integer and String final, to make the Pair immutable
      -fixed some irregular bracketing on toString
      
*/

public class ScoreWordPair_outer<Integer, String> implements Comparable {
    private final Integer score;
    private final String word;

    public ScoreWordPair_outer(Integer score, String word) {
    	super();
    	this.score = score;
    	this.word = word;
    }

    public int hashCode() {
    	int hashFirst = score != null ? score.hashCode() : 0;
    	int hashSecond = word != null ? word.hashCode() : 0;

    	return (hashFirst + hashSecond) * hashSecond + hashFirst;
    }

    public boolean equals(Object other) {
    	if (other instanceof ScoreWordPair_outer) {
    		ScoreWordPair_outer otherPair = (ScoreWordPair_outer) other;
    		return 
    		((  this.score == otherPair.score ||
    			( this.score != null && otherPair.score != null &&
    			  this.score.equals(otherPair.score))) &&
    		 (	this.word == otherPair.word ||
    			( this.word != null && otherPair.word != null &&
    			  this.word.equals(otherPair.word))) );
    	}

    	return false;
    }

   /** Compares the SCORES (i.e. score, type Integer) . Ignores String. 
     * returns 0 if Integer scores are equal, 
     * returns -1 if this.score < otherPair.score
     * returns  1 if this.score > otherPair.score
     */
   public int compareTo(Object other) {
      int retVal = -600;
      if (other instanceof ScoreWordPair_outer) {
    		ScoreWordPair_outer otherPair = (ScoreWordPair_outer) other;
         retVal = this.score.compareTo(otherPair.score);
      }
      return retVal;
    } 


    public String toString(){ 
           return "(" + score + ", " + word + ")"; 
    }

    public Integer getScore() {
    	return score;
    }

    public String getWord() {
    	return word;
    }

}