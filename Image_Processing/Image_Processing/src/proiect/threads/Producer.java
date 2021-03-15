package proiect.threads;


import proiect.buffer.Buffer;

public class Producer extends Thread {                                             //thread ul care trimite pixelii catre procesare
    private Buffer buffer;                                                         //buffer ul  care contine imaginea    
    int part;                                                                      //contorizeaza al catelea sfert din imagine a fost procesat
    
    public Producer (Buffer buffer) {
        this.buffer = buffer;
        this.part = 0;
    }

    @Override
    public void run() {
        for (int i = 0; i < buffer.getHeight(); i++) {
            for (int j = 0; j < buffer.getWidth(); j++) {
            	if ((i+1)*(j+1) > part * (buffer.getWidth() * buffer.getHeight())/4){    //verifica daca produsul indecsilor atinge sau depaseste cate un sfert din imagine
            		try{
            			part++;
            			System.out.println("Producer puts " + part + "/4.");
            			sleep(1000);                                                //se asteapta o secunda dupa trimiterea unui sfert pentru a arata comunicarea dintre thread uri
            		}catch(InterruptedException e){                                 //se trateaza exceptia daca este caul
            			e.printStackTrace();
            		}
            	}
                buffer.put(buffer.getImage().getRGB(j, i));                         //trimite un pixel thread ului Consumer
            }
        }
        
        System.out.println("Producer finished.");
        
    }
}
