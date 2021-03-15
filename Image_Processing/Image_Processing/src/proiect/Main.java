package proiect;

import proiect.buffer.Buffer;
import proiect.image.Image;
import proiect.threads.Consumer;
import proiect.threads.Producer;

public class Main {                                                              //executia programului

    public static void main(String[] args) {
        Image imageBuffer = new Buffer();                                        //instantiere buffer pentru citirea si scrierea imaginii
        imageBuffer.readImage();                                                 //citirea imaginii
        Producer producer = new Producer((Buffer)imageBuffer);                   //se instantieaza thread ul Producer
        Consumer consumer = new Consumer((Buffer)imageBuffer);                   //se instantieaza thread ul Consumer
        producer.start();                                                        //se da start la cele doua thread uri
        consumer.start();
    }
    
}
