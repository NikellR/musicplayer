package com.example.musicplayer;

/**
 * Queue ADT
 * based on https://people.cs.vt.edu/shaffer/Book/JAVA3elatest.pdf
 * Page 125
 */
public interface Queue<E> {

    /**
     * Place an element at the rear of the queue.
     * @param it The element being enqueued.
     * @throws IllegalStateException if the element cannot be added at this time due to capacity restrictions
     * @return
     */
    public void enqueue(E it) throws IllegalStateException;

    /**
     * Remove and return element at the front of the queue.
     * @return The element at the front of the queue.
     * @return null if the queue is empty
     */
    public E dequeue();

    /**
     * @return The front element.
     * @return null if the queue is empty
     */
    public E frontValue();

    /**
     * @return The number of elements in the queue.
     */

    public boolean isEmpty();

    public int length();
}
