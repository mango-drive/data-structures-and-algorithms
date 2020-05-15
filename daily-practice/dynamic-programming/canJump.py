
def canJump(nums):
    N = len(nums)
    if N == 1: return True

    lastGood = N - 1
    for i, num in enumerate(reversed(nums[:-1])):
        pos = N - i - 2
        if pos + num >= lastGood:
            lastGood = pos

    return lastGood == 0


print(canJump([2, 0, 0]) == True)
print(canJump([2,3,1,1,4]) == True)
print(canJump([3,2,1,0,4]) == False)