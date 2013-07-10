/*
   Code borrowed and modified from original code at:
   http://stackoverflow.com/a/677248/1572766
   
   Modifications: 
      -removed setters 
      -made A and B final, to make the Pair immutable
      -fixed some irregular bracketing on toString
      
*/

public class Pair<A, B> {
    private final A first;
    private final B second;

    public Pair(A first, B second) {
    	super();
    	this.first = first;
    	this.second = second;
    }

    public int hashCode() {
    	int hashFirst = first != null ? first.hashCode() : 0;
    	int hashSecond = second != null ? second.hashCode() : 0;

    	return (hashFirst + hashSecond) * hashSecond + hashFirst;
    }

    public boolean equals(Object other) {
    	if (other instanceof Pair) {
    		Pair otherPair = (Pair) other;
    		return 
    		((  this.first == otherPair.first ||
    			( this.first != null && otherPair.first != null &&
    			  this.first.equals(otherPair.first))) &&
    		 (	this.second == otherPair.second ||
    			( this.second != null && otherPair.second != null &&
    			  this.second.equals(otherPair.second))) );
    	}

    	return false;
    }

    public String toString(){ 
           return "(" + first + ", " + second + ")"; 
    }

    public A getFirst() {
    	return first;
    }

    public B getSecond() {
    	return second;
    }

}