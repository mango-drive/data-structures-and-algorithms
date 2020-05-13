class LinkedBinaryTree:
    
    class Node:
        def __init__(self, element, parent=None, left=None, right=None):
            self._element = element
            self._parent = parent
            self._left = left
            self._right = right
        
    class Position:

        def element(self):

