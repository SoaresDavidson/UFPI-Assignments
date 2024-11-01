package daviSousaSoares.banco;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.ArrayList;;

public class EstruturaArquivo extends EstruturaDeDadosDeContas {
	
    protected File meuArquivoR;
    protected ArrayList<Conta> tiposContas = new ArrayList<Conta>();
    
    public EstruturaArquivo() {
        try {
            meuArquivoR = new File("Contas.json");
            if (!meuArquivoR.exists()) {
                meuArquivoR.createNewFile();
                try (FileWriter writer = new FileWriter(meuArquivoR)) {
                    writer.write("[]");
                    writer.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public JSONArray leArquivo(File arquivo) {
    	JSONParser parser = new JSONParser();
        JSONArray contasArray = new JSONArray();
        
    	try (FileReader reader = new FileReader(arquivo)) {
            Object parsedData = parser.parse(reader);
            if (parsedData instanceof JSONArray) {
                contasArray = (JSONArray) parsedData;
            }
        } catch (IOException | ParseException e) {
            System.out.println("Error parsing file, initializing with an empty array.");
            contasArray = new JSONArray();
        }
    	
    	return contasArray;
    }
    
    public void escreveArquivo(JSONArray contasArray) {
    	try (FileWriter meuArquivoW = new FileWriter(meuArquivoR)) {
            meuArquivoW.write(contasArray.toJSONString());
            meuArquivoW.flush();
            meuArquivoW.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public void cadastrar(Conta c) {
    	if (pesquisar(c.getNumero()) != null) return;
    	
        JSONArray contasArray = leArquivo(meuArquivoR);


        JSONObject j = new JSONObject();
        j.put("dono", c.pessoaToJson(c.getDono()));
        j.put("numero", c.getNumero());
        j.put("saldo", c.getSaldo());
        j.put("extrato", c.getExtrato());
        j.put("tipo", c.getClass().getSimpleName());
        
        if (c instanceof ContaImposto) {
        	j.put("imposto", ((ContaImposto) c).getImposto());//typecasting entre parenteses para so depois usar o metodo da ContaImposto
        }
        //else {
        	//j.put("imposto", null);
        //}
        if (c instanceof ContaEspecial) {
        	j.put("limite", ((ContaEspecial) c).getLimite());//typecasting entre parenteses para so depois usar o metodo da ContaImposto
        }
        //else {
        	//j.put("limite", null);
        //}

        contasArray.add(j);


        escreveArquivo(contasArray);
    }

    public Conta pesquisar(int num) {
    	JSONArray contasArray = leArquivo(meuArquivoR);
        for (Object obj : contasArray) {
        	
            JSONObject contaObj = (JSONObject) obj;
            long numero = (long) contaObj.get("numero");
           
            if (numero == num) {
            	String tipo = (String) contaObj.get("tipo");
            	JSONObject dono = (JSONObject) contaObj.get("dono");
            	
            	Pessoa p = new Pessoa((int) (long) dono.get("cpf"), (String) dono.get("senha"));//muito funny o duplo typecast kkkk(não questione!)
            	p.setNome((String) dono.get("name"));
            	Conta c = null;
            	
            	if (tipo.equals("ContaComum")) {
            		c = new ContaComum((int) numero,p);
            	}
            	
            	if (tipo.equals("Poupanca")) {
            		c = new Poupanca((int) numero,p);
            	}
            	
            	if (tipo.equals("ContaImposto")) {
            		double imposto = (double) contaObj.get("imposto");
            		c = new ContaImposto((int) numero, p, imposto);

            	}
            	if (tipo.equals("ContaEspecial")) {
            		double limite = (double) contaObj.get("limite");
            		c = new ContaEspecial((int) numero, p, limite);
            	}
            	c.setSaldo((double) contaObj.get("saldo"));
            	c.setExtrato((String) contaObj.get("extrato"));
            	p.setConta(c);
            	return c;
            }
           

        }

        return null;
    }
    
    public void update(Conta c) {
    	JSONArray contasArray = leArquivo(meuArquivoR);
    	for (Object obj : contasArray) {
    		JSONObject contaObj = (JSONObject) obj;
    		long numero = (long) contaObj.get("numero");
    		if (numero == c.getNumero()) {
    			contaObj.put("dono", c.pessoaToJson(c.getDono()));
    			contaObj.put("numero", c.getNumero());
    			contaObj.put("saldo", c.getSaldo());
    			contaObj.put("extrato", c.getExtrato());
    			contaObj.put("tipo", c.getClass().getSimpleName());
    			break;
    		}
    	}
    	escreveArquivo(contasArray);
    }
    
}
