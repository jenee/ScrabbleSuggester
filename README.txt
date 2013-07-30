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
* * Prevent duplicates by ensuring each word written isn't the same as the previous written word: O(1)
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

To test how long it actually took for the programs to run, I put together a few
shell scripts which each ran my test case 11 times, using the command line
utility "time". Eleven, because I like that number. 



time scrabble-indexer <word-list-file>

RUN 1

real	0m3.145s
user	0m4.176s
sys	0m0.406s
RUN 2

real	0m3.853s
user	0m4.311s
sys	0m0.410s
RUN 3

real	0m2.785s
user	0m4.295s
sys	0m0.418s
RUN 4

real	0m2.402s
user	0m4.237s
sys	0m0.412s
RUN 5

real	0m2.441s
user	0m4.305s
sys	0m0.421s
RUN 6

real	0m2.518s
user	0m4.437s
sys	0m0.425s
RUN 7

real	0m2.443s
user	0m4.300s
sys	0m0.422s
RUN 8

real	0m2.624s
user	0m4.354s
sys	0m0.409s
RUN 9

real	0m2.407s
user	0m4.266s
sys	0m0.409s
RUN 10

real	0m2.681s
user	0m4.474s
sys	0m0.425s
RUN 11

real	0m3.204s
user	0m4.154s
sys	0m0.411s



##################################################
time scrabble-suggester <QUERY> <K>


QUERY = "" K = 5 (base: just prints USAGE flag)

real	0m0.840s
user	0m1.407s
sys	0m0.096s

real	0m0.832s
user	0m1.406s
sys	0m0.092s

real	0m0.841s
user	0m1.395s
sys	0m0.093s

real	0m0.868s
user	0m1.440s
sys	0m0.092s

real	0m0.838s
user	0m1.465s
sys	0m0.093s

real	0m0.836s
user	0m1.406s
sys	0m0.093s

real	0m0.906s
user	0m1.531s
sys	0m0.096s

real	0m0.861s
user	0m1.393s
sys	0m0.091s

real	0m0.857s
user	0m1.416s
sys	0m0.098s

real	0m0.864s
user	0m1.443s
sys	0m0.097s

real	0m0.837s
user	0m1.402s
sys	0m0.095s

QUERY = "et" K = 10
real	0m0.881s
user	0m1.486s
sys	0m0.094s

real	0m0.854s
user	0m1.463s
sys	0m0.098s

real	0m0.866s
user	0m1.459s
sys	0m0.095s

real	0m0.868s
user	0m1.476s
sys	0m0.101s

real	0m0.866s
user	0m1.462s
sys	0m0.097s

real	0m0.863s
user	0m1.444s
sys	0m0.093s

real	0m0.862s
user	0m1.468s
sys	0m0.096s

real	0m0.862s
user	0m1.458s
sys	0m0.095s

real	0m0.844s
user	0m1.431s
sys	0m0.098s

real	0m0.847s
user	0m1.449s
sys	0m0.095s

real	0m0.848s
user	0m1.412s
sys	0m0.094s


QUERY = "embezzlements" K = 10

real	0m0.914s
user	0m1.512s
sys	0m0.103s

real	0m0.957s
user	0m1.578s
sys	0m0.114s

real	0m0.935s
user	0m1.552s
sys	0m0.103s

real	0m0.921s
user	0m1.530s
sys	0m0.101s

real	0m0.917s
user	0m1.517s
sys	0m0.101s

real	0m0.925s
user	0m1.536s
sys	0m0.107s

real	0m0.904s
user	0m1.523s
sys	0m0.101s

real	0m0.945s
user	0m1.557s
sys	0m0.101s

real	0m0.931s
user	0m1.531s
sys	0m0.102s

real	0m0.927s
user	0m1.575s
sys	0m0.101s

real	0m0.915s
user	0m1.513s
sys	0m0.097s

QUERY = "jen" K = 15

real	0m0.957s
user	0m1.647s
sys	0m0.108s

real	0m0.965s
user	0m1.660s
sys	0m0.110s

real	0m1.044s
user	0m1.773s
sys	0m0.116s

real	0m1.052s
user	0m1.790s
sys	0m0.114s

real	0m0.905s
user	0m1.553s
sys	0m0.096s

real	0m0.913s
user	0m1.574s
sys	0m0.099s

real	0m0.902s
user	0m1.529s
sys	0m0.100s

real	0m0.874s
user	0m1.501s
sys	0m0.095s

real	0m0.928s
user	0m1.562s
sys	0m0.100s

real	0m1.069s
user	0m1.561s
sys	0m0.104s

real	0m0.980s
user	0m1.501s
sys	0m0.102s


QUERY= "an" K = 300

real	0m15.070s
user	0m1.680s
sys	0m0.264s

real	0m0.995s
user	0m1.532s
sys	0m0.113s

real	0m0.904s
user	0m1.526s
sys	0m0.099s

real	0m1.081s
user	0m1.640s
sys	0m0.115s

real	0m1.063s
user	0m1.566s
sys	0m0.117s

real	0m1.170s
user	0m1.519s
sys	0m0.100s

real	0m1.820s
user	0m1.553s
sys	0m0.108s

real	0m1.401s
user	0m1.649s
sys	0m0.126s

real	0m1.518s
user	0m1.557s
sys	0m0.121s

real	0m1.146s
user	0m1.589s
sys	0m0.125s

real	0m4.414s
user	0m1.576s
sys	0m0.144s

-

real	0m6.811s
user	0m1.518s
sys	0m0.202s

real	0m0.996s
user	0m1.591s
sys	0m0.103s

real	0m1.057s
user	0m1.540s
sys	0m0.119s

real	0m1.114s
user	0m1.605s
sys	0m0.125s

real	0m1.084s
user	0m1.538s
sys	0m0.123s

real	0m0.930s
user	0m1.557s
sys	0m0.114s

real	0m1.190s
user	0m1.537s
sys	0m0.112s

real	0m1.879s
user	0m1.623s
sys	0m0.122s

real	0m1.569s
user	0m1.547s
sys	0m0.114s

real	0m0.934s
user	0m1.528s
sys	0m0.109s

real	0m1.160s
user	0m1.473s
sys	0m0.110s

-

real	0m1.292s
user	0m1.604s
sys	0m0.118s

real	0m1.009s
user	0m1.582s
sys	0m0.110s

real	0m0.891s
user	0m1.533s
sys	0m0.101s

real	0m0.912s
user	0m1.550s
sys	0m0.099s

real	0m0.923s
user	0m1.578s
sys	0m0.103s

real	0m0.924s
user	0m1.532s
sys	0m0.103s

real	0m1.184s
user	0m1.561s
sys	0m0.107s

real	0m1.048s
user	0m1.608s
sys	0m0.108s

real	0m0.975s
user	0m1.575s
sys	0m0.104s

real	0m1.077s
user	0m1.642s
sys	0m0.112s

real	0m1.431s
user	0m1.474s
sys	0m0.111s

-

real	0m4.253s
user	0m1.509s
sys	0m0.189s

real	0m0.920s
user	0m1.540s
sys	0m0.099s

real	0m0.889s
user	0m1.517s
sys	0m0.099s

real	0m0.912s
user	0m1.502s
sys	0m0.100s

real	0m0.887s
user	0m1.523s
sys	0m0.097s

real	0m0.898s
user	0m1.536s
sys	0m0.097s

real	0m0.922s
user	0m1.576s
sys	0m0.102s

real	0m0.930s
user	0m1.569s
sys	0m0.103s

real	0m0.898s
user	0m1.560s
sys	0m0.102s

real	0m0.906s
user	0m1.546s
sys	0m0.101s

real	0m0.907s
user	0m1.549s
sys	0m0.101s


QUERY = "zygl" K= 5 (query not found at all)
real	0m1.489s
user	0m1.603s
sys	0m0.126s

real	0m0.937s
user	0m1.592s
sys	0m0.107s

real	0m0.966s
user	0m1.677s
sys	0m0.105s

real	0m0.923s
user	0m1.572s
sys	0m0.104s

real	0m0.919s
user	0m1.511s
sys	0m0.102s

real	0m0.956s
user	0m1.645s
sys	0m0.108s

real	0m0.926s
user	0m1.549s
sys	0m0.102s

real	0m0.922s
user	0m1.561s
sys	0m0.105s

real	0m0.927s
user	0m1.560s
sys	0m0.103s

real	0m0.938s
user	0m1.588s
sys	0m0.104s

real	0m0.997s
user	0m1.729s
sys	0m0.124s




QUERY = "e" K=76170 (WORST CASE: most common letter, read all lines)

real	0m7.240s
user	0m2.671s
sys	0m0.499s

real	0m1.755s
user	0m2.703s
sys	0m0.394s

real	0m1.681s
user	0m2.636s
sys	0m0.375s

real	0m1.605s
user	0m2.592s
sys	0m0.375s

real	0m1.716s
user	0m2.638s
sys	0m0.392s

real	0m1.643s
user	0m2.625s
sys	0m0.374s

real	0m2.190s
user	0m2.661s
sys	0m0.389s

real	0m2.506s
user	0m2.694s
sys	0m0.418s

real	0m2.821s
user	0m2.614s
sys	0m0.387s

real	0m1.682s
user	0m2.603s
sys	0m0.373s

real	0m1.838s
user	0m2.584s
sys	0m0.424s