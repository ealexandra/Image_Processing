package proiect.buffer;

import proiect.image.Image;
import proiect.image.ImageInterface;

public class Buffer extends Image {                            //se ocupa de manipularea pixelilor dintre cele doua thread uri
    private int number = 0;
    private boolean available = false;

    public Buffer() {

    }

    @Override
    public synchronized int get () {                          //sincronizare thread uri
        while (!available) {
            try {
                wait ();
            } catch (InterruptedException e) {                //trateaza exceptia
                e.printStackTrace ();
            }
        }
        available = false;
        notifyAll();
        return number;
    }

    @Override
    public synchronized void put (int number) {               //sincronizare thread uri
        while (available) {
            try {
                wait ();
            } catch ( InterruptedException e) {              //trateaza exceptia
                e.printStackTrace ();
            }
        }
        available = true;
        this.number = number;
        notifyAll();
    }
    public Buffer getBuffer() {
        return this;
    }

}
