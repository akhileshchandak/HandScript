 
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

import fontastic.Fontastic;
import processing.core.PApplet;
import processing.core.PVector;
 class TestProcess extends PApplet {  
         @Override
	 public void setup(){
	    size(100,100); 
	    background(0);
	 } 
	}
class Coordinates
{
    int x, y;
}
/** This program demonstrates how to resize an image. */
public class Handscript {
 
    /**
     * Resizes an image to a absolute width and height (the image may not be
     * proportional)
     * @param inputImagePath Path of the original image
     * @param outputImagePath Path to save the resized image
     * @param scaledWidth absolute width in pixels
     * @param scaledHeight absolute height in pixels
     * @throws IOException
     */
    public static void resize(String inputImagePath,
             int scaledWidth, int scaledHeight)
            throws IOException {
        // reads input image
        File inputFile = new File(inputImagePath);
        BufferedImage inputImage = ImageIO.read(inputFile);
        
        // creates output image
        BufferedImage outputImage = new BufferedImage(scaledWidth,
                scaledHeight, inputImage.getType());
 
        // scales the input image to the output image
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();
 
        // extracts extension of output file
        String formatName = inputImagePath.substring(inputImagePath
                .lastIndexOf(".") + 1);
 
        // writes to output file
        ImageIO.write(outputImage, formatName, new File(inputImagePath));
    }
        public static int colorToRGB(int alpha, int red, int green, int blue) {
            int newPixel = 0;
            newPixel += alpha;
            newPixel = newPixel << 8;
            newPixel += red; newPixel = newPixel << 8;
            newPixel += green; newPixel = newPixel << 8;
            newPixel += blue;

            return newPixel;
        }
      public static BufferedImage getTransparentImage(
        BufferedImage image, Color transparent) {
        // must have a transparent image
        BufferedImage img = new BufferedImage(
            image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_ARGB);
        Graphics2D g= img.createGraphics();
        for (int x=0; x<img.getWidth(); x++) {
            for (int y=0; y<img.getHeight(); y++) {
                if (image.getRGB(x,y)!=transparent.getRGB()) {
                    img.setRGB( x,y, image.getRGB(x,y) );
                }
            }
        }
        g.dispose();
        return img;
    }
     
    public static BufferedImage colorToAlpha(BufferedImage raw, Color remove)
{
    int WIDTH = raw.getWidth();
    int HEIGHT = raw.getHeight();
    BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_ARGB);
    int pixels[]=new int[WIDTH*HEIGHT];
    raw.getRGB(0, 0, WIDTH, HEIGHT, pixels, 0, WIDTH);
    for(int i=0; i<pixels.length;i++)
    {
        if (pixels[i] == remove.getRGB()) 
        {
        pixels[i] = 0x00ffffff;
        }
    }
    image.setRGB(0, 0, WIDTH, HEIGHT, pixels, 0, WIDTH);
    return image;
}  
    public static void BufferedCrop(String raw) throws IOException
    {
        int k=0;
        File inputFile = new File(raw);
        BufferedImage inputImage = ImageIO.read(inputFile);
       for(int i=0;i<1000;i+=100)
       {
           for(int j=0;j<900;j+=100)
           {
            BufferedImage croppedImage = inputImage.getSubimage(i, j, 90, 90);
            String formatName = raw.substring(raw.lastIndexOf(".") + 1);
            ImageIO.write(croppedImage, formatName,new File("D:\\photo\\n"+k+".jpg") );   
            k++;
           }
       }
    }
   
    public static void main(String[] args) {
        String inputImagePath = "D:/Photo/puppy.jpg";
        
         
        try {
            // resize to a fixed width (not proportional)
            int scaledWidth = 1000;
            int scaledHeight = 900;
           Handscript.resize(inputImagePath,scaledWidth, scaledHeight);
            
            BufferedImage img = ImageIO.read(new File(inputImagePath) );
              // Create a graphics which can be used to draw into the buffered image

            /*Graphic graphi = img.createGraphics();

            Color black=new Color(0,0,0);
            Color white=new Color(255,255,255);
            for(int i=0;i<img.getWidth();i++)
            {
                for(int j=0;j<img.getHeight();j++)
                {  
                    Color c = new Color(img.getRGB(i, j));
                    int r=(c.getRed()+c.getGreen()+c.getBlue())/3;
                    if(r<(255/2))
                        img.set
                    else
                        img.setRGB(i, j, (225*65536)+(225*256)+225);
                }
           }
             String formatName = inputImagePath.substring(inputImagePath
                .lastIndexOf(".") + 1);
         ImageIO.write(img, formatName, new File(inputImagePath));
          BufferedImage raw = ImageIO.read(new File(inputImagePath) );
            Color remove=new Color(255,255,255,0);
         int WIDTH = raw.getWidth();
    int HEIGHT = raw.getHeight();
    BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_ARGB);
    int pixels[]=new int[WIDTH*HEIGHT];
    raw.getRGB(0, 0, WIDTH, HEIGHT, pixels, 0, WIDTH);
    for(int i=0; i<pixels.length;i++)
    {
        if (pixels[i] == remove.getRGB()) 
        {
        pixels[i] = 0x00ffffff;
        }
    }
    image.setRGB(0, 0, WIDTH, HEIGHT, pixels, 0, WIDTH);
    
            formatName = inputImagePath.substring(inputImagePath
                .lastIndexOf(".") + 1);
         ImageIO.write(image, formatName, new File(inputImagePath));
            // create a cropped image from the original image
          //   BufferedImage croppedImage = inputImagePath.getSubimage(0, 0, 250, 0xfa);
 */
             BufferedImage original = img;
         BufferedImage binarized = new BufferedImage(original.getWidth(), original.getHeight(),BufferedImage.TYPE_BYTE_BINARY);

         int avg;
         int newPixel;
         int threshold =150;

            for(int i=0; i<original.getWidth(); i++) 
            {
                for(int j=0; j<original.getHeight(); j++)
                {

                    // Get pixels
                  avg = new Color(original.getRGB(i, j)).getRed();
                  avg+=new Color(original.getRGB(i, j)).getGreen();
                  avg+=new Color(original.getRGB(i, j)).getBlue();
                  avg=avg/3;
                  int alpha = new Color(original.getRGB(i, j)).getAlpha();

                  if(avg < threshold)
                    {
                        newPixel = 0;
                    }
                    else
                    {
                        newPixel = 255;
                    }
                    newPixel = colorToRGB(0, newPixel, newPixel, newPixel);
                    //System.out.println(""+newPixel);
                    binarized.setRGB(i, j, newPixel);

                }
            } 
            ImageIO.write(binarized, "jpg",new File(inputImagePath) );
            
            /*final BufferedImage trans = getTransparentImage(
            binarized, Color.WHITE);
            ImageIO.write(trans, "jpg",new File(inputImagePath) );*/
         /*BufferedImage raw = binarized;
         BufferedImage nimg= new BufferedImage(original.getWidth(), original.getHeight(),BufferedImage.TYPE_BYTE_BINARY);
         for(int i=0; i<raw.getWidth();i++)
    {
        for(int j=0;j<raw.getHeight();j++)
        {
            //System.out.print(""+raw.getRGB(i,j));
        if(raw.getRGB(i, j)!=(-1)) 
        {
        int c=colorToRGB(0,255,255,255);
        nimg.setRGB(i,j,c);
        //System.out.println("inside");
        //System.out.print(""+nimg.getRGB(i, j));
        }
        }
        System.out.println("");
    }
    
           String formatName = inputImagePath.substring(inputImagePath
                .lastIndexOf(".") + 1);
         ImageIO.write(nimg, formatName, new File("D:\\photo\\new.jpg"));
   */
            char[] letters={'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
            		        'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
            		        '1','2','3','4','5','6','7','8','9','0','@','#','$','%','&','-','+',
            		        '(',')','/','*',',','\'',':',';','!','?','\\','{','}','[',']','=',
            		        '$','^','_'};

            BufferedCrop(inputImagePath);//cropping 
            PApplet pa = new TestProcess();
            Fontastic f = new Fontastic(pa,"test");
      		f.setAuthor("Simi"); 
      		f.setAdvanceWidth(250);
            for(int k=0;k<88;k++)
            {
            BufferedImage raw = ImageIO.read(new File("D:\\photo\\n"+k+".jpg"));
            int WIDTH=100,HEIGHT=100;
                        
            for(int i=0,l=0; i<WIDTH;i++,l++)
            {    
             PVector[] point = new PVector[1000]; 
            for(int j=0;j<HEIGHT;j++)
            {  
                if (raw.getRGB(i, j)!=-1) 
                {
                    Coordinates co=new Coordinates();
                    co.x=i;
                    co.y=j;
                    point[l] = new PVector(i, j);//point index ='l' 
                    System.out.println(i+"\t"+j);
                }
                System.out.println("");
                 //BufferedImage croppedImage = raw.getSubimage(0, 0, 250, 0xfa);
                f.addGlyph(letters[k]). addContour(point);

        		
            }
            }
            }
            f.buildFont();
    		f.cleanup();
            
          
    
    //raw.setRGB(0, 0, WIDTH, HEIGHT, pixels, 0, WIDTH);
    
        /* String formatName = inputImagePath.substring(inputImagePath
                .lastIndexOf(".") + 1);
         ImageIO.write(raw, formatName, new File("D:\\photo\\new.jpg"));*/
            
         
        }catch (IOException ex) {
            System.out.println("Error resizing the image.");
            ex.printStackTrace();
        }
    }
 
}