
### Depth 


*Depth* of a node p is the number of ancestors of p, excluding p itself. The depth of the root is 0.
Depth can be recursively defined:

* If p is the root, then the depth of p is 0
* Otherwise, the depth of p is 1+depth of parent of p

```python
def depth(self):
    if self.is_root(p):
        return 0
    else:
        return 1 + self.depth(self.parent(p))
```
**Running time**: $O(dp + 1) = O(n)$ where dp is depth of tree. 
**Rationale**: Constant time recursive step for each ancestor of p.

### Height

* TODO: Goodrich 310 determine running time of height


*Height* of a node is the number of child levels of p, excluding p itself. The height of a leaf is 0.
Height can be recursively defined:

* If p is a leaf, then the height of p is 0
* Otherwise, the height of p is 1+ max(height(p.children))

**Proposition**: 

*Height and Depth are related*: 
The height of a nonempty tree == maximum of the depths of its leafs

```python
# works, but O(n^2)
def _height1(self):
    return max(self.depth(p) for p in self.positions() if self.is_leaf(p))
```

```python
def _height(self, p):
    if self.is_leaf(p):
        return 0
    else:
        return 1 + max(self.height(c) for c in self.children(p))

def height(self, p=None):
    if p is None:
        p = self.root()
    return self._height(p)

```

## Leafs

```python
def is_leaf(self, p):
    return self.num_children(p) == 0
```