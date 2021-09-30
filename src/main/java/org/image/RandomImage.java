package org.image;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;


public class RandomImage {
    public static void generateImage(String fileLocation) {
        int width = 640;
        int height = 320;
        //create buffered image object img
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        //file object
        File f = null;
        //create random image pixel by pixel
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                int a = (int)(Math.random()*255); //alpha
                int r = (int)(Math.random()*255); //red
                int g = (int)(Math.random()*255); //green
                int b = (int)(Math.random()*255); //blue

                int p = (a<<24) | (r<<16) | (g<<8) | b; //pixel

                img.setRGB(x, y, p);
            }
        }
        //write image
        try{
            f = new File(fileLocation);
            ImageIO.write(img, "png", f);
        }catch(IOException e){
            System.out.println("Error: " + e);
        }
    }

    public static void generateXML(String imageFileLocation, String folderName, String imageName, String xmlFileLocation) {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            //add elements to Document
            Element rootElement =
                    doc.createElementNS("http://dv.newbay.com/ns/1.0", "files");
            //append root element to document
            doc.appendChild(rootElement);

            String sha256Checksum = getSHA256Checksum(imageFileLocation);
            int fileSize = RandomImage.getFileSize(imageFileLocation);
            //append first child element to root element
            rootElement.appendChild(getFile(doc, "/"+folderName, imageName, String.valueOf(fileSize), sha256Checksum, "image/png"));

            //for output to file, console
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            //for pretty print
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);

            //write to console or file
            //StreamResult console = new StreamResult(System.out);
            StreamResult file = new StreamResult(new File(xmlFileLocation));

            //write data
            //transformer.transform(source, console);
            transformer.transform(source, file);
            //System.out.println("DONE");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Node getFile(Document doc, String parentPath, String name, String size, String checksum, String systemAttribute) {
        Element file = doc.createElement("file");
        //set id attribute
        //file.setAttribute("id", id);
        //create name element
        file.appendChild(getFileElements(doc, file, "parentPath", parentPath));
        //create age element
        file.appendChild(getFileElements(doc, file, "name", name));
        //create role element
        file.appendChild(getFileElements(doc, file, "size", size));
        //create gender element
        file.appendChild(getFileElements(doc, file, "checksum", checksum));
        file.appendChild(getFileElementsWithAttribute(doc, file, "systemAttribute", systemAttribute, "name", "Mime-Type"));
        return file;
    }


    //utility method to create text node
    private static Node getFileElements(Document doc, Element element, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }

    private static Node getFileElementsWithAttribute(Document doc, Element element, String name, String value, String attributeName, String attributeValue) {
        Element node = doc.createElement(name);
        node.setAttribute(attributeName, attributeValue);
        node.appendChild(doc.createTextNode(value));
        return node;
    }

    public static void deletFile(String filePath) {
        try{
            File file = new File(filePath);
            file.delete();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static String getSHA256Checksum(String fileLocation) throws IOException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        FileInputStream fis = new FileInputStream(fileLocation);
        byte[] dataBytes = new byte[1024];
        int nread = 0;
        while ((nread = fis.read(dataBytes)) != -1) {
            md.update(dataBytes, 0, nread);
        };
        byte[] mdbytes = md.digest();
        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < mdbytes.length; i++) {
            sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    public static int getFileSize(String fileLocation) throws IOException, NoSuchAlgorithmException {
        File file =new File(fileLocation);
        int bytes = 0;
        if(file.exists()) {
            bytes = (int)file.length();
        }
        return bytes;
    }
}