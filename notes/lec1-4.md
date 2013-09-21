# Lectures 1.1-4

Martin's proclamation of "starting from first principles" and "making a clean break" are quite true. In particular, it's hard not to think of call-by-name things as functions and call-by-value things as values, which I guess isn't actually a problem. 

You were initially confused that call-by-name = functions, since in JS everything's dynamic so it's totally cool to just return an int, bool, or function, whenver and his model of expressions + values has every expression/function being called at *some* point. but it makes sense if you're avoiding side effects, since you'd have no reason to leave a function/expression unevaluated. cool.
