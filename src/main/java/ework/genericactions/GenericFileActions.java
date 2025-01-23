package ework.genericactions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.openqa.selenium.WebDriver;

public class GenericFileActions {
	public static boolean fileExists(String path) throws URISyntaxException {
		File dlfile = GenericFileActions.getResourceFile(path);
		return dlfile.isFile();
	}
	
	public static void deleteFile(String path) {
		File dlfile = new File(path);
		dlfile.delete();
	}
	
	public static void deleteFileIfExists(String filename) throws Exception {
		File file = new File(filename);
		
		if(file.exists()) {
			file.delete();
		}
	}
	
	/** Unzip the zip file
	 * @param driver
	 * @param fileName
	 */
	@SuppressWarnings("unused")
	public static void unzipFile (WebDriver driver, String fileName) {
		try {
			ZipFile zipFile = new ZipFile(fileName);
			Enumeration<?> enu = zipFile.entries();
			while (enu.hasMoreElements()) {
				ZipEntry zipEntry = (ZipEntry) enu.nextElement();

				String name = zipEntry.getName();
				long size = zipEntry.getSize();
				long compressedSize = zipEntry.getCompressedSize();
//				System.out.printf("File name: %-20s | File size: %6d | compressed size: %6d\n", 
//						name, size, compressedSize);

				File file = new File(name);
				if (name.endsWith("/")) {
					file.mkdirs();
					continue;
				}

				File parent = file.getParentFile();
				if (parent != null) {
					parent.mkdirs();
				}

				InputStream is = zipFile.getInputStream(zipEntry);
				FileOutputStream fos = new FileOutputStream(file);
				byte[] bytes = new byte[1024];
				int length;
				while ((length = is.read(bytes)) >= 0) {
					fos.write(bytes, 0, length);
				}
				is.close();
				fos.close();

			}
			zipFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getProperty(String path,String prop, String file) {
        String property = null;
        if(file==null) {
        	file = "prop.properties";
        }
        
        if(!(path == null)) {
        	try {
        		Properties	p   = new Properties();
                InputStream is	= new FileInputStream(path+file);
                p.load(is);
                property=p.getProperty(prop);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
        	try {
        		Properties  p   = new Properties();
                InputStream is	= new FileInputStream(file);
                p.load(is);
                property=p.getProperty(prop);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    
        
        return property;
    }
    
    public static String getProperty(String prop) {
    	return getProperty(null,prop,null);
    }
    
    public static String getProperty(String prop, String file) {
    	return getProperty(null,prop,file);
    }
    
	
    public static String getEnvProperty(String path,String prop) {
    	String property = null;
        
        if(!(path == null)) {
	        try {
	        	Properties  p   = new Properties();
	            InputStream is	= new FileInputStream(GenericFileActions.getResourceFile(path + "envprop.properties"));
	            p.load(is);
	            property=p.getProperty(prop);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
        } else {
        	
        	try {
 	            Properties	p    = new Properties();
 	            InputStream is	= new FileInputStream(GenericFileActions.getResourceFile("envprop.properties"));
 	            p.load(is);
 	            property=p.getProperty(prop);
 	        } catch (Exception e) {
 	            e.printStackTrace();
 	        }
        }
        
        return property;
    }	

    public static String getEnvProperty(String prop) {
    	return getEnvProperty(null,prop);
    }
    
    public static File getResourceFile(String path) throws URISyntaxException {
    	ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    	URL res = classloader.getResource(path);
    	
    	File file = Paths.get(res.toURI()).toFile();
    	
    	return file;
    }
}
