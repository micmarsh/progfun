# Lectures 1.1-4

## What's the Deal with CBN + CBV?

One of the first things Martin talks about is making starting from first principles and making a "clean break" with prior programming experience. He does this by keeping it theoretical by talking about on values, expressions, call by name and call by value, as opposed to the more familiar terminology of contstants, return statements, and higher-order functions (can't think of a good analogy for call by value, since it's such an assumed default in most of programming)

For example, to introduce higher order functions, he never uses the words higher order or function, and rather explains "you can force this expression to be call by name by adding '=>' to the method signature" (paraphrase). This helps to keep it abstract, and although slightly confusing at first, lay exactly the foundation he wants to (presumably)

Refering to higher-order functions as "call by name" is confusing at first coming from js, because the substitution model of evaluation implies that each higher order function will be evaluated sometime, which is not necessarily true in js. In the land of static typing it makes perfect sense though, since you already know what the function is supposed to be returning, and Scala making "loop" and "loop()" is a clever touch, since the compiler can tell by the return type if you're returning a value, and call the method accordingly. 
