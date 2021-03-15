package proiect.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Image implements ImageInterface {                                    //clasa care se ocupa cu procesarea imaginii
    private BufferedImage image;                                                  //imaginea primita ca input
    private int[][] pixels;                                                       //matrice pentru pixelii imaginii

    public Image() {
    }

    @Override
    public void readImage() {                                                     //metoda pentru citirea imaginii
        Scanner in = new Scanner(System.in);
        System.out.print("Please enter the name of the photo: ");
        String inFile = in.nextLine();
        try {
            this.image = ImageIO.read(new File("src/proiect/" +  inFile + ".bmp"));      //se construieste path-ul in functie de variabila primita ca input de la tastatura
        } catch (IOException e) {                                                 //daca citirea nu se realizeaza cu succes se trateaza exceptia
            e.printStackTrace();
        }
        this.pixels = new int[image.getWidth()][image.getHeight()];               //se aloca matricea unde vor fi stocati pixelii imaginii
    }

    @Override
    public int get() {                                                            //metoda este suprainscrisa
        return 0;
    }

    @Override
    public void put(int number) {                                                 //metoda este suprainscrisa

    }

    public int getWidth() {                                                       //returneaza lungimea imaginii
        return this.image.getWidth() - 1;
    }

    public int getHeight() {                                                      //returneaza latimea imaginii
        return this.image.getHeight() - 1;
    }

    public BufferedImage getImage() {                                             //referinta la imagine
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void putPixel(int pixel, int line, int col) {                          //populeaza matricea cu pixelii imaginii
        this.pixels[line][col] = pixel;
    }

    public void blurImage() {                                                     //algoritmul ce confera efectul de smoothing
        for (int i = 0; i < image.getHeight(); i++) {                             
            for (int j = 0; j < image.getWidth(); j++) {                          //cele 2 bucle for sunt folodite pentru a parcurge fiecare pixel din matricea imaginii
                     
                int indexX1 = i - 3/2;
        		int indexY1 = j - 3/2;
         
        		if (indexX1 < 0) {                                                //verifica daca pixelul este prea aproape de marginea imaginii astfel incat 
        			continue;                                                     //sa nu se poate aplica masca de convolutie
        		}
        		if (indexY1 < 0) {                                                //acest lucru este verificat pentru fiecare latura a imaginii
        			continue;                                                     //pentru ca nucleul sa nu fie extins in afara imaginii
        		}
        		if (j + 3 >= image.getWidth()) {
        			continue;
        		}
        		if (i + 3  >= image.getHeight()) {
        			continue;
        		}
         
                int newPixel = convolvePixel(image.getSubimage(j, i, 3, 3));        //daca pixelul este valid atunci este dat ca parametru unei metoda care efectueaza blurarea propriu-zisa si returneaza un pixel neclar
                image.setRGB(j, i, newPixel);
            }
        }
        Scanner in = new Scanner(System.in);
        System.out.print("Please enter the name of the converted photo: ");
        String outFile = in.nextLine();
        try {
            ImageIO.write(image, "bmp", new File("src/proiect/" + outFile + ".bmp"));       //salveaza noua imagine cu un nume primit ca input
        } catch(IOException e) {                                                            //trateaza exceptia in caul in care salvarea nu s-a putut realiza
           e.printStackTrace();
        }
    }



    public static int convolvePixel (BufferedImage subImage) {                     //metoda care aplica masca de convolutie asupra unui pixel
    	float kernel = (float) (1.0f / Math.pow(3, 2));                            //functia Gauss pentru efectul de blur

    	int newPixel = 0;
    	int subWidth = subImage.getWidth();                                        //lungimea subimaginii extrase din imaginea procesate
    	int subHeight = subImage.getHeight();                                      //latimea subimaginii extrase din imaginea procesasta

    	int newRed = 0;                                                            //variabila in care se va calcula noua valoare a canalului rosu al pixelului
    	int newGreen = 0;                                                          //variabila in care se va calcula noua valoare a canalului verde al pixelului
    	int newBlue = 0;                                                           //variabila in care se va calcula noua valoare a canalului albastru al pixelului

    	for (int i = 0; i < subWidth; i++) {
    		for (int j = 0; j < subHeight; j++) {
    			int p = subImage.getRGB(i,j);
    			int Red = (p>>16) & 0xff;
    			int Green = (p>>8) & 0xff;
    			int Blue = p & 0xff;

    			Red *= kernel;                                                     //aplicam masca de convolutie pentru
    			Green *= kernel;                                                   //fiecare canal in parte
    			Blue *= kernel;

    			newRed += Red;                                                     //valoarea pixelului blurat pentru fiecare canal
    			newBlue += Blue;
    			newGreen += Green;
    		}
    	}
    	if (newRed > 255) {                                                        //verificam pentru fiecare culoare sa nu 
    		newRed = 255;                                                          //depaseasca limitele admise 
    	} else if (newRed < 0) {
    		newRed = 0;
    	}  

    	if (newBlue > 255) {
    		newBlue = 255;
    	} else if (newBlue < 0) {
    		newBlue = 0;	
    	} 

    	if (newGreen > 255) {
    		newGreen = 255;
    	} else if (newGreen < 0) {
    		newGreen = 0;
    	} 

    	newPixel = 0x000000FF<<24 | newRed<<16 | newGreen<<8 | newBlue;         //valoarea pixelului blurat
	
    	return newPixel;
    }

}

