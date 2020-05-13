from random import randint
from timeit import repeat


def bubble_sort2(array):
    n = len(array)
    for i in range(n):
        # flag to terminate early if there's nothing left to sort
        already_sorted = True

        # compare each with adjacent value. 
        # shrink the unsorted array by one
        for j in range(n-i-1):
            if array[j] > array[j + 1]:
                array[j], array[j+1] = array[j+1], array[j]
                # we had to swap, prevent early termination
                already_sorted = False
        
        if already_sorted: break
    return array

# Description: 
# With every new pass, the largest element in the list bubbles up towards its correct
# position
def bubble_sort(array):
    n = len(array)
    # repeat until the entire array is sorted
    for i in range(n):
        # initialise early termination flag (optimisation)
        already_sorted = True
        # for each element in the unsorted part
        for j in range ( n - i - 1 ): # not 100% on why -1 part...
            # if current item is larger than the one ahead of it, swap them
            if array[j] > array[j+1]:
                array[j], array[j+1] = array[j+1], array[j]
                # the subarray was not sorted, so set the early term flag to false
                already_sorted = False
        if already_sorted: break
    return array

# Description
# How you would sort a deck of cards
# key and shift ahead implementation
def insertion_sort(array):
    # loop from second element until the last
    for i in range(1, len(array)):

        # store the element as a key (it will be overwritten by others)
        key = array[i]

        # initialize an index just behind the element
        j = i - 1

        # shift the elements until you find the correct position for the key
        while j >= 0 and array[j] > key:
            array[j+1] = array[j]
            j -= 1

        # When finished shifting, position the key at the correct position
        array[j+1] = key
    return arr

def merge(left, right):
    N = len(left)
    M = len(right)
    # if left is empty, nothing needs to be merged 
    # so return right
    if N == 0: return right
    if M == 0: return left

    # initialise a result array, and indices for merging
    result = []
    i = j = 0

    # Merge the two arrays by deciding whether to get the
    # next element from the left or right array
    while i < N and j < M:
        if left[i] <= right[j]: 
            result.append(left[i])
            i += 1
        else:
            result.append(right[j])
            j += 1
        
    # if you've reached the end of either array,
    # add the remaining elements from the other to the
    # result and break the loop
    if i == N: result.extend(right[j:])
    if j == M: result.extend(left[i:])

    return result

def merge_sort(array):
    # if len(array) is 0 or 1, just return it
    if len(array) < 2:
        return array
    
    # calculate the midpoint
    midpoint = len(array) // 2

    # recursively split the input into two equal halves
    # sorting each half and merging them together into the final
    # result

    return merge(left=merge_sort(array[:midpoint]),
                 right=merge_sort(array[midpoint:]))

def quick_sort(array):
    if len(array) < 2: return array

    low, same, high = [], [], []

    pivot = array[randint(0, len(array) - 1)]

    for item in array:
        if item < pivot: low.append(item)
        elif item == pivot: same.append(item)
        elif item > pivot: high.append(item)
    
    return quick_sort(low) + same + quick_sort(high)





def run_sorting_algorithm(algorithm, array):
    setup_code = f"from __main__ import {algorithm}" if algorithm != "sorted" else ""

    stmt = f"{algorithm}({array})"

    times = repeat(setup=setup_code, stmt=stmt, repeat=3, number=10)

    print(f"Algorithm: {algorithm}. Minimum execution time: {min(times)}")


ARRAY_LENGTH = 2000
TEST_LENGTH = 10

if __name__ == "__main__":
    array = [randint(0, 1000) for i in range(ARRAY_LENGTH)]
    test_array = [randint(0, 1000) for i in range(TEST_LENGTH)]

    # print(bubble_sort(test_array))
    # run_sorting_algorithm(algorithm="bubble_sort", array=array)

    a = [1,2,3,4,5]
    b = [1,3,5,7,8,9]
    print(merge(a,b))
