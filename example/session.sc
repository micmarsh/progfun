import scala.annotation.tailrec

object session {
  def abs(x: Double) = if (x < 0) -x else x       //> abs: (x: Double)Double
  abs(4)                                          //> res0: Double = 5.0
  abs(-8)                                         //> res1: Double = 8.0
  
  def sqrt(x: Double):Double = {
    val GOOD_ENOUGH = 0.000001
	  
	  @tailrec
		def sqrtIter(guess: Double): Double =
	    if (isGoodEnough(guess)) guess
	    else sqrtIter(improve(guess))
	    
	  def isGoodEnough(guess:Double) =
	  	abs(1 - (guess * guess) / x) < GOOD_ENOUGH
	  	
	  def improve(guess:Double) =
	  	(guess + x /guess) / 2
  	
  	return sqrtIter(1.0)
  }                                               //> sqrt: (x: Double)Double
 	
 	sqrt(2)                                   //> res2: Double = 1.4142135623746899
 	sqrt(4)                                   //> res3: Double = 2.0000000929222947
 	sqrt(1e-6)                                //> res4: Double = 0.0010000001533016628
	sqrt(1e60)                                //> res5: Double = 1.0000000031080746E30
	sqrt(1e50)                                //> res6: Double = 1.0000003807575104E25
	sqrt(1e-30)                               //> res7: Double = 1.0000000300245242E-15
	
	def fact (n: Double): Double = {
		@tailrec
		def factHelper(n:Double, soFar: Double):Double =
			if (n == 0) return soFar
			else factHelper(n-1, n * soFar)
		
		return factHelper(n, 1)
		
	}                                         //> fact: (n: Double)Double
  fact(5)                                         //> res8: Double = 120.0
  fact(3)                                         //> res9: Double = 6.0

  
}