package pl.system.cv;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Image;

class AppConfig {
	private String resourcePath;
	private String jsonDataFile;
	private String outputFile;
		
	
	public AppConfig(String propertiesFileName) throws Exception{
		configure( propertiesFileName );
	}
	
	public String getJsonDataFileName(){
		return resourcePath + '/' + jsonDataFile; 
	}
	
	public String getResourcePath(){
		return resourcePath; 
	}
	
	public String getOutputFile(){
		return outputFile; 
	}
	
	private void configure( String propertiesFileName ) throws Exception{
		Properties p = new Properties();
		InputStream inStream = getClass().getClassLoader().getResourceAsStream(propertiesFileName);
		if(inStream != null){
			p.load(inStream);
		}else{
			throw new Exception("Cant find properties file");
		}
		
		System.out.println(p.toString());
		
		resourcePath = 	p.getProperty("resource_path");
		jsonDataFile = 	p.getProperty("json_data_file");
		outputFile = 	p.getProperty("output_file");
	}

	@Override
	public String toString() {
		return "AppConfig [resourcePath=" + resourcePath + ", jsonDataFile=" + jsonDataFile + ", outputFile="
				+ outputFile + "]";
	}	
}

public class App {
	static JsonElement readJsonData(String fileName) throws FileNotFoundException {
		Gson gson = new Gson();
		
		JsonReader reader = new JsonReader(
				new InputStreamReader(
						new FileInputStream(fileName), StandardCharsets.UTF_8) );
		
		return new JsonParser().parse(reader);
	}
	
	static String PROPERTIES_FILE_NAME="application.properties";
	
	public static void main(String[] args) throws Exception {
		AppConfig appConfig = new AppConfig( PROPERTIES_FILE_NAME );		
		System.out.println(appConfig.toString());
		
		JsonElement data = readJsonData(appConfig.getJsonDataFileName());		
		CvPdf renderer = new CvPdf();
		byte[] pdf = renderer.renderPdf(data, appConfig.getResourcePath() );
		//byte[] pdf = renderer.renderTestPdf(data, appConfig.getResourcePath());

		FileOutputStream fos;
		try {
			fos = new FileOutputStream( appConfig.getOutputFile() );
			fos.write(pdf);
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
