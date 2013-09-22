import scala.annotation.tailrec

object sum {
  
  def iter(operation:(Int, Int) => Int, unit:Int)(f: Int => Int)(a:Int, b:Int):Int = {
      List.range(a,b + 1) reduce( (acc:Int, item:Int) =>
      	operation(acc, f(item)) )
  }                                               //> iter: (operation: (Int, Int) => Int, unit: Int)(f: Int => Int)(a: Int, b: In
                                                  //| t)Int
  
  def sum(f: Int => Int)(a:Int, b:Int) = iter(_ + _, 0)(f)(a,b)
                                                  //> sum: (f: Int => Int)(a: Int, b: Int)Int
  def product(f:Int => Int)(a: Int, b: Int) = iter(_ * _, 1)(f)(a,b)
                                                  //> product: (f: Int => Int)(a: Int, b: Int)Int
 def fact(b:Int) = product(x => x)(1, b)          //> fact: (b: Int)Int
 
 fact(5)                                          //> res0: Int = 120
}