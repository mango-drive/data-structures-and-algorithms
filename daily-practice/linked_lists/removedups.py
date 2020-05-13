
# First solve
def remove_dups(head):
    values = {}

    prev = None
    curr = head

    while curr is not None:
        if curr.val in values.keys():
            if curr is head:
                head = curr.next
            else:
                prev.next = curr.next

        else:
            values[curr.val] = 1

        pred = curr
        curr = curr.next

    return head

