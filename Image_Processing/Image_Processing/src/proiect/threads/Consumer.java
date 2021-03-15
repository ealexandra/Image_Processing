package proiect.threads;

import proiect.buffer.Buffer;

public class Consumer extends Thread{                                         //thread ul care preia pixelii si construieste matricea imaginii
    private Buffer buffer;                                                    //buffer care contine imaginea
    int part;                                                                 //contorizeaza al catelea sfert din imagine a fost procesat

    public Consumer (Buffer buffer) {
        this.buffer = buffer;
        this.part = 0;
    }

    @Override
    public void run() {
        for (int i = 0; i < buffer.getHeight(); i++) {
            for (int j = 0; j < buffer.getWidth(); j++) {
            	if((i+1) * (j+1) > part * (buffer.getWidth() * buffer.getHeight())/4){      //verifica daca produsul indecsilor atinge sau depaseste cate un sfert din imagine
            		try{
            			part++;
            			System.out.println("Consumer gets " + part + "/4");
            			sleep(1000);                                           //se asteapta o secunda dupa procesarea unui sfert pentru a arata comunicarea dintre thread uri
            		}catch(InterruptedException e){                            //trateaza exceptia daca este cazul
            			e.printStackTrace();
            		}
            	}
                buffer.putPixel(buffer.get(), j, i);                           //pune pixelul in matrice
            }
        }
        System.out.println("Consumer finished.");
        buffer.blurImage();                                                    //apeleaza metoda care se ocupa de efectul de smoothing
    }
}
