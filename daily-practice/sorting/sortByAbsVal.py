

def absSort(arr):
    for i in range(n):
        already_sorted = True

        for j in range(n - i -1):
            if abs(arr[j]) > abs(arr[j+1]):
                arr[j], arr[j+1] = arr[j+1], arr[j]
                already_sorted = False
            elif abs(arr[j] == abs(arr[j+1])):
                if arr[j] > arr[j+1]:
                    arr[j], arr[j+1] = arr[j+1], arr[j]
                    already_sorted = False
            
        if already_sorted: break
    return arr

import functools
def abs_cmp(a, b):
    if abs(a) - abs(b) == 0: return a - b
    else: return abs(a) - abs(b)

def absSort2(arr):
    return sorted(arr, key=functools.cmp_to_key(abs_cmp))

arr = [2, -7, -2, -2, 0]
print(absSort2(arr))


