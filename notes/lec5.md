#Newtown's Method

## Why isn't it precise enough?
It terminates too soon: mutliplying small decimals together is going to get small real quick. 


## Why doesn't it terminate for big nums?

bigNum^2 - otherBigNum is probably not going to result in a small decimal for a while, can probably be mathematically verified as an infinite loop (or maybe not, since NP hard and stuff 

## Solution?

Maybe tighten up the good enough constant and use some other approximation arithmetic? Yeah! tightened up and multiplied/divided numbers together rather than subtracting. Didn't actually need to tighten up the precision according to the answer, but it couldn't hurt (except for all the extra cycles)
