# Wrong
class StockSpanner(object):

    def __init__(self):
        self.stack = []
        

    def next(self, price):
        """
        :type price: int
        :rtype: int
        """
        if len(self.stack) == 0 or price < self.stack[-1][0]:
            self.stack.append((price, 1))
        else:
            if price == self.stack[-1][0]:
                self.stack[-1][1] += 1
            else:
                while(price > self.stack[-1][0]):
                    p, s = self.stack.pop()
                    self.stack.append((price, s+1))
      
        return self.stack[-1][1]
            
# Review: too many self.stack[][] : 
#    - hard coded indices: BAD
#    - better to use a wrapper

# Better solution:
class StockSpanner:

    def __init__(self):
        self.stacks = []

    def next(self, price: int) -> int:
        n = 1
        while self.stacks:
            val, num = self.stacks[-1]
            if price < val:
                self.stacks.append((price, n))
                return n
            else:
                n += num
                self.stacks.pop()
        self.stacks.append((price, n))
        return n