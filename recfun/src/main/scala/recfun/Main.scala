package recfun
import common._
import scala.annotation.tailrec

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

 /*
1
1 1
1 2 1
1 3 3 1
1 4 6 4 1
1 5 10 10 5 1
1 6 15 20 15 6 1
 */

  
  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int) = {
    def recur(c: Int, r: Int): Int = 
	    if (c == 0 || c == r) 1
	    else recur(c, r -1) + recur(c-1, r - 1)
	if(c > r) 0
	else recur(c, r)
  }
   //good, prolly need to throw some exception if c > r

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]) = {
    
    @tailrec
    def recur(counter: Int, chars: List[Char]): Boolean = {
      if (counter < 0) false
      else if (chars.isEmpty) counter == 0
      else {
        val head::tail = chars
	    val newCount = if(head == '(') counter + 1 
		        else if (head == ')') counter - 1
		        else counter
		recur(newCount, tail)
      }
	       
    }
    recur(0, chars)
  }

  def balance(chars: String):Boolean = balance(chars.toList)
  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = 
    if(money == 0) 1
    else if(money < 0 || coins.isEmpty) 0
    else {
      val head::tail = coins
      countChange(money, tail) + countChange(money-head, coins)
    }
}
