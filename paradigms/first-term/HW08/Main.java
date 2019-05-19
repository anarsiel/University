import queue.ArrayQueueADT;
import queue.ArrayQueueModule;
import queue.ArrayQueue;

public class Main {

    public static void main(String[] args) {
        System.out.println("----------ArrayQueueModule----------");
        ArrayQueueModule.enqueue("world");
        ArrayQueueModule.enqueue(1);
        ArrayQueueModule.enqueue("4");
        ArrayQueueModule.enqueue("3");
        ArrayQueueModule.enqueue(239);
//        ArrayQueueModule.push("hello");
        System.out.println(ArrayQueueModule.element());
        System.out.println(ArrayQueueModule.size());
//        System.out.println(ArrayQueueModule.remove());
        System.out.println(ArrayQueueModule.size());
        System.out.println(ArrayQueueModule.remove());
        System.out.println(ArrayQueueModule.remove());
        System.out.println(ArrayQueueModule.remove());
        System.out.println(ArrayQueueModule.remove());
        System.out.println(ArrayQueueModule.remove());
        System.out.println(ArrayQueueModule.isEmpty());
        System.out.println("beg end sz: " + ArrayQueueModule.beginning + ArrayQueueModule.ending + ArrayQueueModule.size);
        ArrayQueueModule.push(2);
        System.out.println("beg end sz: " + ArrayQueueModule.beginning + ArrayQueueModule.ending + ArrayQueueModule.size);
        System.out.println(ArrayQueueModule.element());
        System.out.println(ArrayQueueModule.peek());
        System.out.println("------------------------------------");

        System.out.println("-----------ArrayQueueADT------------");
        ArrayQueueADT queueADT = new ArrayQueueADT();
        ArrayQueueADT.enqueue(queueADT, "hello");
        ArrayQueueADT.enqueue(queueADT, 1);
        ArrayQueueADT.enqueue(queueADT, "4");
        ArrayQueueADT.enqueue(queueADT, "3");
        ArrayQueueADT.enqueue(queueADT, 239);
        System.out.println(ArrayQueueADT.size(queueADT));
        System.out.println(ArrayQueueADT.element(queueADT));
        System.out.println(ArrayQueueADT.dequeue(queueADT));
        System.out.println(ArrayQueueADT.size(queueADT));
        System.out.println(ArrayQueueADT.element(queueADT));
        ArrayQueueADT.dequeue(queueADT);
        ArrayQueueADT.dequeue(queueADT);
        ArrayQueueADT.dequeue(queueADT);
        System.out.println(ArrayQueueADT.dequeue(queueADT) + " one love");
        System.out.println(ArrayQueueADT.size(queueADT));
        ArrayQueueADT.enqueue(queueADT, 5);
        System.out.println(ArrayQueueADT.size(queueADT));
        System.out.println(ArrayQueueADT.element(queueADT));
        System.out.println("------------------------------------");

        System.out.println("-------------ArrayQueue-------------");
        ArrayQueue queue = new ArrayQueue();
        queue.enqueue("hello");
        queue.enqueue(1);
        queue.enqueue("4");
        queue.enqueue("3");
        queue.enqueue(239);
        System.out.println(queue.size());
        System.out.println(queue.element());
        System.out.println(queue.dequeue());
        System.out.println(queue.size());
        System.out.println(queue.element());
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        System.out.println(queue.dequeue() + " one love");
        System.out.println(queue.size());
        queue.enqueue(5);
        System.out.println(queue.size());
        System.out.println(queue.element());
        System.out.println("------------------------------------");
    }
}
