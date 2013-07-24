Scrabble Suggester Documentation

----------------
Goal of program:
----------------
Given the string "et", we want to find the top K scoring scrabble words that contain "et" anywhere in the word.

-------------------------------------
Structure of Necessary Program Files:
-------------------------------------
<Source Code>
./ScoreWordPair.java
./ScrabbleIndexer.java
./ScrabbleIndexerTester.java
./ScrabbleSuggester.java
./ScrabbleSuggesterTester.java

<Shell Scripts>
./scrabble-indexer
./scrabble-suggester

<Folder>
./TestOutput


-------------------------------
High Level Program Structure:
-------------------------------
This program runs in two parts.

The first part, the scrabble indexer, takes a list of words and builds an on-disk index.

>>USAGE<<: ./scrabble-indexer <word-list-file>

The second part, the scrabble suggester, uses the on-disk index created during the first part.
   It takes two arguments: a string Q and an integer K.
   It then returns the top K words that contain the query string Q, sorted by their scores.
 
>>USAGE<<: ./scrabble-suggester <Q> <K>

The design is optimized to make the second part run as fast as possible, so most of the heavy-lifting happens in part 1.

The suggester will not run if the indexer has not been called first. 

The indexer only has to be called once, ever. Every subsequent suggester call uses the same index.


--------------------------
Scrabble Indexer Design
--------------------------
The

(1) read dict into program
(2) sort from highest to lowest score
(3) From sorted list of words, write into words-containing-<letter> files

    





--------------------------
Scrabble Suggester Design
--------------------------
After indexer is run, we have twenty-six files, one for each letter of the alphabet.
Each file contains ALL words from the original dictionary that contain that particular letter.
Each word that contains *all* the letters that compose the QUERY must also contain *each* of the letters that compose the QUERY.
Because of that, any word that contains ALL the letters in QUERY must necessarily be in the index-file for each of the letters in QUERY.
By using the index file of the letter in QUERY that is least common in the English language, we can narrow down the number of words we need to check.
Additionally, since the index-files are already sorted from highest to lowest score, we are guaranteed that the first K words that we find that contain the full QUERY are also the top-scoring K words that contain QUERY

 
(1) Get cmd line args, int numMatches, string QUERY
(2) get all contains-letter files for each letter in QUERY
(3) find smallest contains-letter file, make its words the BASE list
(4) For each line (word) in BASE:
   (a) see if it contains the given param str QUERY
   (b) if the word contains QUERY, add it to the list-to-return, and
                increment the num of words found.
   (c) If number of words found is >= numMatches, then return and
          be done with it!

