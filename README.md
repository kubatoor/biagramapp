**Description**

This is an app which identifies the count of biagrams from the input text file.
A bigram is any two adjacent words in the text disregarding case. A histogram is the count of
how many times that particular bigram occurred in the text.

**Example**

Given the text: “The quick brown fox and the quick blue hare.” The bigrams with their counts
would be.

● “the quick” 2

● “quick brown” 1

● “brown fox” 1

● “fox and” 1

● “and the” 1

● “quick blue” 1

● “blue hare” 1

**Assumption**

The input to the application will be a text file containing the words separated by space.

**How to run**

(1) Clone the repository to your local machine

(2) Run gradle clean build

(3) Navigate to $PROJECT_HOME/build/libs directory

(4) Run the command java -jar biagramapp-1.0-SNAPSHOT.jar <path to input text file containing biagrams>

The above should print the histogram of the biagrams
