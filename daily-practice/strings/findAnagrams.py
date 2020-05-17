findAnagrams(self, s, p):
    """
    :type s: str
    :type p: str
    :rtype: List[int]
    """
    if len(s) < len(p): return []
    
    pf = {}
    for char in p:
        self.insertOrDefault(pf, char)

    sf = {}
    for char in s[:len(p)-1]:
        self.insertOrDefault(sf, char)
        
    a = []
    for i, char in enumerate(s[:-len(p)+1]):
        self.insertOrDefault(sf, s[i+len(p)-1])
        
        if self.areAnagrams(pf, sf):
            a.append(i)
            
        sf[char] -= 1            
        
    return a     
        
def areAnagrams(self, a, b):
    for char, count in a.items():        
        if not(char in b and b[char] == count): 
            return False
    return True
        
def insertOrDefault(self, d, char, default=1):
    if char not in d: 
        d[char] = default
    else: 
        d[char] += 1
    
# BETTER
def findAnagrams2(self, s, p):
        cnt_p = Counter(p); n = len(s); m = len(p); res = []
        for i in range(n-m+1):
            cnt_s = Counter(s[i:i+m])
            if cnt_s == cnt_p: res.append(i)
        return res 