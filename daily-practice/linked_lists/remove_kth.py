
def remove_kth(head, k):
    to_rm = head
    p = head

    for i in range(k+1):
        if p is None: return None
        else: p = p.next

    while p.next is not None:
        p = p.next
        to_rm = to_rm.next

    rv = to_rm.next
    to_rm.next = to_rm.next.next

    return rv

