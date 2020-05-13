## Basics

### Traversal

```python
def traversal(head):
    curr = head
    while(curr):
        # visit
        curr = curr.next
```

### Search

```python
# Necaise p 161
def unorderedSearch(head , target):
    currNode = head

    while currNode and currNode.data != target:
        currNode = currNode.next

    # if just looking for a boolean
    return currNode is not None
    
```

```python
def findNode(head):
    curr = head
    while(curr) and curr.data != target:
        currNode = currNode.next

    return currNode
```

### Removal



```python
def remove(head, target):
    predNode = None # to stitch ll back together
    # predNode obtained during search
    curNode = head
    while curNode is not None and curNode.data != target:
        predNode = curNode
        curNode = curNode.next

    if curNode is not None:
        if curNode is head:
            head = curNode.next
        else:
            predNode.next = curNode.next
```

Another method uses the node's next.next, so that you don't have to keep track of the pred

```python
def remove(head, target):
    n = head

    if n.data == target: 
        head = head.next
        return head

    while (n is not None):
        if (n.next.data == target):
            return n
        n = n.next

    return n
```

#### Removal in DLL


## Advanced Applications

### Polynomials
Necaise - 6.6
