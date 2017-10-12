For question 1, the first solution:
The heuristic used was beam search.

The reward/heuristic for each state is being calculated as follow:
1) For every person across the bridge: reward = reward + 10

2) Person moved who has the shortest time will have a higher reward, the algorithm is as follow: reward = highestTimeTaken - currentPersonTimeTaken.	
We substract the person moving from the person who have the highest time taken to cross the bridge. This will result in higher reward for people who takes lesser time to cross the bridge.

The number of states considered is the least compared to the rest of the search. Its solution quality obtained for the test case is accepted and is equal to 28 which is consider a success for this solution.
