
def longestPalindromeSubstring(s):
    if s is None: return 0

    start = end = 0

    for i, char in enumerate(s):
        l1 = palindromeLengthFrom(s, i, i)
        l2 = palindromeLengthFrom(s, i, i+1)
        length = max(l1, l2)

        if length > end - start - 1:
            end = i + length // 2
            start = i - (length - 1) // 2
    
    return s[start:end+1]

def palindromeLengthFrom(s, left, right):
    if s is None or left > right: return 

    while left >= 0 and right < len(s) and s[left] == s[right]:
        left -= 1
        right += 1

    return right - left - 1

if __name__ == '__main__':
    s = "babad"
    print(longestPalindromeSubstring(s))
    s = "bab"
    print(longestPalindromeSubstring(s))