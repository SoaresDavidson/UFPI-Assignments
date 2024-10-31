package daviSousaSoares.banco;
import java.io.FileWriter;
import org.json.simple.JSONObject;
import java.io.File;
import java.io.FileNotFoundException; 
import java.io.IOException;
import java.util.Scanner;
public class ContaArquivo extends Conta{
	public ContaArquivo(int n, Pessoa p) {
		super(n, p);
		try {
			File myObj = new File("conta"+n+".json");
	      	myObj.createNewFile();
	      	FileWriter myObjW = new FileWriter("conta"+n+".json");
	      	JSONObject file = new JSONObject();
			file.put("numero", n);
			file.put("dono", p);
			myObjW.write(file.toJSONString());
			
	    } catch (IOException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }
		
		
	}
	
	public boolean debito (double val, String senha) {
		return false;
	}
}
