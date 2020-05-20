
# O(N) time for inorder traversal, O(N) space in recursion stack 
def kthSmallest(root, k):
    def inorderList(root):
        return inorderList(root.left) + [root.val] + inorderList(root.right)
    return inorderList(root)[k-1]

# Iterative approach allows easy early return
def kthSmallestIterative(root, k):
    stack = []
        
        while True:
            while root:
                stack.append(root)
                root = root.left
            root = stack.pop()
            k -= 1
            if not k:
                return root.val
            root = root.right)


