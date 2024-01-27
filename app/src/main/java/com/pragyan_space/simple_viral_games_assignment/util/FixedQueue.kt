package com.pragyan_space.simple_viral_games_assignment.util

import java.util.LinkedList

class FixedQueue<String> {
    private val queue: LinkedList<String> = LinkedList()

    fun enqueue(item: String) {
        if (queue.size == 20) {
            queue.removeFirst()
        }
        queue.addLast(item)
    }

    fun toList():List<String>
    {
        return queue.toList()
    }

    fun size():Int
    {
        return queue.size
    }
}
