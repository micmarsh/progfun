import annotation.tailrec
object data {
 class Rational(x: Int, y: Int) {
  //require throws illegal arg exception
  require( y != 0, "denominator must be nonzero")

	def this(x: Int) = this(x,1)
	
 	def numer = x
 	def denom = y
 	
 	@tailrec
 	private def gcd(a:Int, b:Int): Int =
 		if (b == 0) a else gcd(b, a % b)
 		
 	private def arithmetic(op: (Int,Int) => Int)(that: Rational) =
 	 		new Rational(op(numer * that.denom,that.numer * denom),
 			denom * that.denom)
 	
 	def + (that:Rational) = arithmetic(_ + _)(that)
  def - (that:Rational) = arithmetic(_ - _)(that)
 	def < (that:Rational) = numer * that.denom < that.numer * denom
 	def max (that: Rational) = if (this < that) that else this
 	override def toString = {
 		val g = gcd(x,y)
 		//exposing arithmetic overflows by putting this in here
	 	if (denom == 1) numer.toString
	 	else numer / g + "/" + denom / g
	 }
 }
 val g = new Rational(4)                          //> g  : data.Rational = 4
 
 val r = new Rational(1,3)                        //> r  : data.Rational = 1/3
 val r1 = new Rational(3,6)                       //> r1  : data.Rational = 1/2
 r + r1                                           //> res0: data.Rational = 5/6
 
 val x = new Rational(1,3)                        //> x  : data.Rational = 1/3
 val y = new Rational(5,7)                        //> y  : data.Rational = 5/7
 val z = new Rational(3,2)                        //> z  : data.Rational = 3/2
 x - y -z                                         //> res1: data.Rational = -79/42
 
 x < y                                            //> res2: Boolean = true
}