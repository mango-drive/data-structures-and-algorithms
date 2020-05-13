
def numJewelsInStones(J, S):

    c = 0
    for s in S:
        if s in J: c += 1
    
    return c

J = "aA"
S = "aAAbbbb"
print(numJewelsInStones(J, S))

class Solution:
    def numJewelsInStones(self, J, S):
        return sum([True for i in S if i in J])


from collections import Counter
class Solution(object):
    def numJewelsInStones(self, J, S):
        """
        :type J: str
        :type S: str
        :rtype: int
        """
        
        s_dict = Counter(S)
        jewelStones = 0
        
        for key in s_dict:
            if key in J:
                jewelStones += s_dict[key]
                
        return jewelStones