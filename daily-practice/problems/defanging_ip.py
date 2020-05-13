
def defangIPaddr(address):
    # 20ms, faster than 33.18%
    # 12.8MB, 100%
    return address.replace(".", "[.]")

def defangIPaddr2(address):
    # doesn't work: ['1', '2', '7', '[.]', '1', '[.]', '0', '[.]', '0', '[.]', '1']
    return ["".join(char) if char != "." else "[.]" for char in address]
    
s = "127.1.0.0.1"
d = defangIPaddr2(s)
print(d)


