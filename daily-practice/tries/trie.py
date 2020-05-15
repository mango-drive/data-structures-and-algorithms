class Trie(object):

    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.root={"*": "*"}

    def insert(self, word):
        """
        Inserts a word into the trie.
        :type word: str
        :rtype: None
        """
        curn = self.root
        for lett in word:
            if lett not in curn:
                curn[lett] = {}
            curn=curn[lett]
        curn['*'] = '*'
        

    def search(self, word):
        """
        Returns if the word is in the trie.
        :type word: str
        :rtype: bool
        """
        curn = self.root
        for lett in word:
            if lett not in curn:
                return False
            else:
                curn = curn[lett]
        return '*' in curn
    

    def startsWith(self, prefix):
        """
        Returns if there is any word in the trie that starts with the given prefix.
        :type prefix: str
        :rtype: bool
        """
        curn = self.root
        for lett in prefix:
            if lett not in curn:
                return False
            curn = curn[lett]
        return True