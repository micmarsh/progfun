package patmat

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import patmat.Huffman._

@RunWith(classOf[JUnitRunner])
class HuffmanSuite extends FunSuite {
  trait TestTrees {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
    val l1 = Leaf('b', 3)
    val frenchBits = List(1, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1)

  }

  
  test("weight of a larger tree") {
    new TestTrees {
      assert(weight(t1) === 5)
    }
  }

  test("chars of a larger tree") {
    new TestTrees {
      assert(chars(t2) === List('a','b','d'))
    }
  }
  
  test("operations on leaves") {
    new TestTrees {
       assert(chars(l1) === List('b'))
       assert(weight(l1) === 3)
    }
  }

  test("string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }

  test("counts things up") {
    val timesList = times("aeaacbcb".toList)
    val shortList = times("a".toList)
    val longList = times("zzzzgbcbcczcczc".toList)
    assert(timesList.to[Set]  === Set(('a',3),('e',1),('b',2),('c',2)))
    assert(shortList.to[Set] === Set(('a',1)))
    assert(longList.to[Set] === Set(('z',6), ('g',1), ('b',2), ('c',6)))
  }
  
  test("makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }

  test("combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }
  

  test("'a' should not encode to '000' in french, but 's' should") {
    assert(encode(frenchCode)(List('a')) != List(0,0,0))
    assert(encode(frenchCode)(List('s')) === List(0,0,0))
  }
  
  test("decode and encode a very short text should be identity") {
      val t1 = frenchCode
      for (chars <- List("ab","b","a"))
    	  assert(decode(t1, encode(t1)(chars.toList)) === chars.toList)
    
  }
  
  
  
  test("encode-decode on some long list of bits is identity") {
        			 //	 List(0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 1, 1, 1, 1, 0, 0, 1, 0, 1, 1, 1, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 1, 1, 1, 1)
    new TestTrees {
          assert(encode(frenchCode)(decode(frenchCode, frenchBits)) === frenchBits)
    }    
  }
  
  
  test("quick encode functions basically the same as normal encode") {
	  new TestTrees {
	    assert(quickEncode(frenchCode)(decode(frenchCode, frenchBits)) === frenchBits)
	    val t = frenchCode
	    for (chars <- List("ab","b","a"))
    	  assert(decode(t, quickEncode(t)(chars.toList)) === chars.toList)
    	assert(quickEncode(frenchCode)(List('a')) != List(0,0,0))
    	assert(quickEncode(frenchCode)(List('s')) === List(0,0,0))
	    
	  }
  }
  
}
