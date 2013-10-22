	package forcomp

import common._

object Anagrams {

  /** A word is simply a `String`. */
  type Word = String

  /** A sentence is a `List` of words. */
  type Sentence = List[Word]

  /** `Occurrences` is a `List` of pairs of characters and positive integers saying
   *  how often the character appears.
   *  This list is sorted alphabetically w.r.t. to the character in each pair.
   *  All characters in the occurrence list are lowercase.
   *  
   *  Any list of pairs of lowercase characters and their frequency which is not sorted
   *  is **not** an occurrence list.
   *  
   *  Note: If the frequency of some character is zero, then that character should not be
   *  in the list.
   */
  type Occurrences = List[(Char, Int)]

  /** The dictionary is simply a sequence of words.
   *  It is predefined and obtained as a sequence using the utility method `loadDictionary`.
   */
  val dictionary: List[Word] = loadDictionary

  /** Converts the word into its character occurence list.
   *  
   *  Note: the uppercase and lowercase version of the character are treated as the
   *  same character, and are represented as a lowercase character in the occurrence list.
   */
  def wordOccurrences(w: Word): Occurrences = w.toLowerCase.sorted.groupBy(c => c).toList.map{
    case (c, str) => (c, str.length)
  }.sortBy{case (c, i) => c }
  

  /** Converts a sentence into its character occurrence list. */
  def sentenceOccurrences(s: Sentence): Occurrences = 
    ( s flatMap (wordOccurrences(_)) ).sortBy{case (c, i) => c }

  /** The `dictionaryByOccurrences` is a `Map` from different occurrences to a sequence of all
   *  the words that have that occurrence count.
   *  This map serves as an easy way to obtain all the anagrams of a word given its occurrence list.
   *  
   *  For example, the word "eat" has the following character occurrence list:
   *
   *     `List(('a', 1), ('e', 1), ('t', 1))`
   *
   *  Incidentally, so do the words "ate" and "tea".
   *
   *  This means that the `dictionaryByOccurrences` map will contain an entry:
   *
   *    List(('a', 1), ('e', 1), ('t', 1)) -> Seq("ate", "eat", "tea")
   *
   */
  type DictByOccur = Map[Occurrences, List[Word]]
  lazy val dictionaryByOccurrences: DictByOccur = {
    def buildMap (acc: DictByOccur, wordsLeft: List[String]): DictByOccur = wordsLeft match {
      case Nil => acc
      case word::tail => { 
        val occurrences = wordOccurrences(word)
        acc get occurrences match {
          case None => buildMap(acc + (occurrences -> List(word)), tail)
          case Some(list) => 
            buildMap(acc + (occurrences -> (word::list)), tail) 
        }
      }
    }
   
    buildMap(Map(), loadDictionary)
  }

  /** Returns all the anagrams of a given word. */	
  def wordAnagrams(word: Word): List[Word] = 
    dictionaryByOccurrences get wordOccurrences(word) match {
	    case None => List()
	    case Some(occurrences) => occurrences
  	}

  /** Returns the list of all subsets of the occurrence list.
   *  This includes the occurrence itself, i.e. `List(('k', 1), ('o', 1))`
   *  is a subset of `List(('k', 1), ('o', 1))`.
   *  It also include the empty subset `List()`.
   * 
   *  Example: the subsets of the occurrence list `List(('a', 2), ('b', 2))` are:
   *
   *    List(
   *      List(),
   *      List(('a', 1)),
   *      List(('a', 2)),
   *      List(('b', 1)),
   *      List(('a', 1), ('b', 1)),
   *      List(('a', 2), ('b', 1)),
   *      List(('b', 2)),
   *      List(('a', 1), ('b', 2)),
   *      List(('a', 2), ('b', 2))
   *    )
   *
   *  Note that the order of the occurrence list subsets does not matter -- the subsets
   *  in the example above could have been displayed in some other order.
   */
  def combinations(occurrences: Occurrences): List[Occurrences] = {
    val length = occurrences.length
    val initialCombos = for {
    	index <- (0 to length)
    	combo <- occurrences.combinations(index)
    } yield combo
    println(initialCombos.toList)
    List()
    
    /*
     * You Know what you want "loop" to do, or rather what you should do with 
     * the above in general: For each item List(('a', 3)...),
     * 
     * need to generate
     * a new seq of lists Seq(List(('a', 1)...), List(('a', 2)...),...), that 
     * then be all flattened together into the final list of lists
     * 
     * Easy for the case of length one, but what's the "recursive step" (may
     * not actually be recursive)
     * 
     * so do:
     * 
     *
     * 
     * def countCombos(List[(Char, Int)]): Seq(List[(Char, Int)])
     * 		match list {
     *   	case Nil => Nil
     *      case (char, int)::tail =>  
     *    the problem is this ->
     *    		for (i <- (1 to int))
     *             yield (char, i)::countCombos(tail)
     *         this damn pattern keeps coming up, it's so close!
     *   }
     *   
     *or {
     * 		for {(char, int) <- list
     *  		  i <- (1 to int)} yield (char, i)
     *      
     *       no! b/c it will just expand all the shit out, maybe
     *       
     *     for { (char, int) <- list } yield	 		
     * 	
     * }
     */
    
    //TODO: this shit be failing the test case. Check it out tomorrow
    //shit, the second one is correct, you ain't got no short lists homie!
  }
  

  /** Subtracts occurrence list `y` from occurrence list `x`.
   * 
   *  The precondition is that the occurrence list `y` is a subset of
   *  the occurrence list `x` -- any character appearing in `y` must
   *  appear in `x`, and its frequency in `y` must be smaller or equal
   *  than its frequency in `x`.
   *
   *  Note: the resulting value is an occurrence - meaning it is sorted
   *  and has no zero-entries.
   */
  def subtract(x: Occurrences, y: Occurrences): Occurrences = {
    val toSub = y.toMap.withDefault((c:Char) => 0)
    def loop(acc: Occurrences, x:Occurrences): Occurrences = 
      if (x.isEmpty) acc.reverse
      else {
        val (char, count)::tail = x
        loop((char, count - toSub(char))::acc, tail)
      }
      
    loop(Nil, x).filter{case (char, int) => int > 0}
  }

  /** Returns a list of all anagram sentences of the given sentence.
   *  
   *  An anagram of a sentence is formed by taking the occurrences of all the characters of
   *  all the words in the sentence, and producing all possible combinations of words with those characters,
   *  such that the words have to be from the dictionary.
   *
   *  The number of words in the sentence and its anagrams does not have to correspond.
   *  For example, the sentence `List("I", "love", "you")` is an anagram of the sentence `List("You", "olive")`.
   *
   *  Also, two sentences with the same words but in a different order are considered two different anagrams.
   *  For example, sentences `List("You", "olive")` and `List("olive", "you")` are different anagrams of
   *  `List("I", "love", "you")`.
   *  
   *  Here is a full example of a sentence `List("Yes", "man")` and its anagrams for our dictionary:
   *
   *    List(
   *      List(en, as, my),
   *      List(en, my, as),
   *      List(man, yes),
   *      List(men, say),
   *      List(as, en, my),
   *      List(as, my, en),
   *      List(sane, my),
   *      List(Sean, my),
   *      List(my, en, as),
   *      List(my, as, en),
   *      List(my, sane),
   *      List(my, Sean),
   *      List(say, men),
   *      List(yes, man)
   *    )
   *
   *  The different sentences do not have to be output in the order shown above - any order is fine as long as
   *  all the anagrams are there. Every returned word has to exist in the dictionary.
   *  
   *  Note: in case that the words of the sentence are in the dictionary, then the sentence is the anagram of itself,
   *  so it has to be returned in this list.
   *
   *  Note: There is only one anagram of an empty sentence.
   */
  def sentenceAnagrams(sentence: Sentence): List[Sentence] = ???

}