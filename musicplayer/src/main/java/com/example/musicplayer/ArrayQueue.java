package com.example.musicplayer;

import java.util.EmptyStackException;

public class ArrayQueue<E> implements Queue<E> {
    private final int DEFAULT_CAPACITY = 100;
    private E[] queue;
    private int count;
    private int front, back;

    public ArrayQueue() {
        queue = (E[]) new Object[DEFAULT_CAPACITY];
        count = 0;
    }

    public ArrayQueue(int size) {
        queue = (E[]) new Object[size];
        count = 0;
    }

    @Override
    public void enqueue(E it) throws IllegalStateException {

        if (count < queue.length) {
            queue[back++ % queue.length] = it;
            count++;

        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public E dequeue() {
        //option 1 is O(1)
        E returnData = queue[front % queue.length];
        queue[front % queue.length] = null;
        front++;
        count--;
        return returnData;
    }

    @Override
    public E frontValue() throws EmptyStackException {
        if (isEmpty()) { // End music player when no more songs in queue
            System.out.println("Playlist is Over! No More Songs To Play!");
            System.exit(0);
        }

        return queue[front];
    }

    @Override
    public int length() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        if (count == 0) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String o = "";
        for (int i = 0; i < count; i++) {
            int index = (front + i) % queue.length;
            o += queue[index] + ", ";
        }
        return o;
    }
}
