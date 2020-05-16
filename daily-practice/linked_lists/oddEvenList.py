def oddEvenList(head):
    """
    :type head: ListNode
    :rtype: ListNode
    """ 
    if head is None: return head
    
    odd, even = head, head.next
    
    even_head = even
    
    while even and even.next is not None:
        odd.next = odd.next.next
        even.next = even.next.next
        odd, even = odd.next, even.next
        
    odd.next = even_head
    
    return head