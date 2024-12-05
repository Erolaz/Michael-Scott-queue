package org.example

import java.util.concurrent.atomic.AtomicReference

class MichaelScottQueue<T> {
    private class Node<T>(val value: T?, var next: AtomicReference<Node<T>?> = AtomicReference(null))

    private val head = AtomicReference(Node<T>(null))
    private val tail = AtomicReference(Node<T>(null))

    fun enqueue(value: T) {
        val newNode = Node(value)
        while (true) {
            val currentTail = tail.get()
            val nextNode = currentTail.next.get()
            if (nextNode != null) { // not at the end
                tail.compareAndSet(currentTail, nextNode)
            } else { // at the end
                if (currentTail.next.compareAndSet(null, newNode)) {
                    tail.compareAndSet(currentTail, newNode)
                    return
                }
            }
        }
    }

    fun dequeue(): T? {
        while (true) {
            val currentHead = head.get()
            val currentTail = tail.get()
            val nextNode = currentHead.next.get()
            if (currentHead == currentTail) { // avoid retrieving from empty queue
                tail.compareAndSet(currentTail, nextNode)
            } else { //retrieve value
                val value = nextNode?.value
                if (head.compareAndSet(currentHead, nextNode)) {
                    return value
                }
            }
        }
    }
}