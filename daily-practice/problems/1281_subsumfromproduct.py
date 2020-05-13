
# Modulo soluiton
def subtractSumFromProduct(n):
    p = 1
    s = 0

    while(n):
        rem = n % 10
        n = n // 10
        s += rem
        p *= rem
    
    return p - s

# Another solution involves converting to string
# then for each char performing the product and sum operations

print(subtractSumFromProduct(234))