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

 Now, to decide on the on-disc storage method.
    I could:
    (1) write the score/word tuples into a file, then sort that file by number
    (2) write a file for each score, and put words with that score into those files
    (3) Use a (serializable) hash table, where the score is the key, and the
       value is a list of words with that score.
    (4) throw pre-computing scores out all together, and make files for words that
       contain each letter. Then, when I get a sequence of letters, Find all the
       lines that the "letter" files have in common, and only search those words.
       This could take up a lot more space, because of storing duplicate words,
       but could potentially be faster. This method could also be
       combined with other methods that optimize for searching highest-scoring
       words first. I'd have to account for duplicate letters somehow, when
       creating and when using the files created.
    


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




-----------------------------
Performance Measurements
-----------------------------
~~~Theoretical Run-time Measurement ~~~
   
Scrabble Indexer: 
KEY:  n == total # of words
      m == # of letters in each word   
* Reading all file lines into program and storing each in ArrayList: O(n)
* * For each line, compute score for each word by going through letter: O(m)
* Sort the words by their scores, descending: O(nlogn)
* For each letter in each word, write the word into the corresponding letter's index-file: O(n*m)
~~~OVERALL: O(nlogn) + O(n*m) + (cost of writing to disk)

Scrabble Suggester:
KEY:  m == # of letters in QUERY
      n == # of words in the smallest letter-index-file 
      b == # of letters in each word in the smallest letter-index-file
* For each letter in the QUERY word, get index file size: O(m)
* For each word in smallest indexfile O(n)
* * store it if it contains the QUERY str: O(b)
* * (Short circuit if you've found K words that contain QUERY)
~~~OVERALL: O(m) + O(n*b) 

~~~Practical Run-time Measurement ~~~

Over (######) trial runs!

time scrabble-indexer <word-list-file>
Virgin environment:
(1)
real	0m1.824s
user	0m3.597s
sys	0m0.190s
(2)
real	0m1.841s
user	0m3.640s
sys	0m0.191s
(3)
real	0m1.834s
user	0m3.622s
sys	0m0.188s
(4)
real	0m1.809s
user	0m3.612s
sys	0m0.190s
(5)
real	0m1.828s
user	0m3.628s
sys	0m0.193s
(6)
real	0m1.838s
user	0m3.609s
sys	0m0.201s
(7)
real	0m1.827s
user	0m3.599s
sys	0m0.184s
(8)
real	0m1.830s
user	0m3.629s
sys	0m0.190s
(9)
real	0m1.841s
user	0m3.642s
sys	0m0.194s
(10)
real	0m1.847s
user	0m3.651s
sys	0m0.194s

Indexer Already run
(1)
real	0m1.797s
user	0m3.550s
sys	0m0.183s
(2)
real	0m1.830s
user	0m3.641s
sys	0m0.191s
(3)
real	0m1.836s
user	0m3.645s
sys	0m0.188s
(4)
real	0m1.813s
user	0m3.634s
sys	0m0.183s


time scrabble-suggester <QUERY> <K>


