package daviSousaSoares.banco;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.lang.Integer;;

public class EstruturaArquivo extends EstruturaDeDadosDeContas {
    protected File meuArquivoR;

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

    public void cadastrar(Conta c) {
        JSONParser parser = new JSONParser();
        JSONArray contasArray = new JSONArray();

  
        try (FileReader reader = new FileReader(meuArquivoR)) {
            Object parsedData = parser.parse(reader);
            if (parsedData instanceof JSONArray) {
                contasArray = (JSONArray) parsedData;
            }
        } catch (IOException | ParseException e) {
            System.out.println("Error parsing file, initializing with an empty array.");
            contasArray = new JSONArray();
        }


        JSONObject j = new JSONObject();
        j.put("numero", c.getNumero());
        j.put("dono", pessoaToJson(c.getDono()));

        contasArray.add(j);


        try (FileWriter meuArquivoW = new FileWriter(meuArquivoR)) {
            meuArquivoW.write(contasArray.toJSONString());
            meuArquivoW.flush();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public Conta pesquisar(int num) {
    	JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(meuArquivoR)) {

            JSONArray contasArray = (JSONArray) parser.parse(reader);

 
            for (Object obj : contasArray) {
                JSONObject contaObj = (JSONObject) obj;
                
                long numero = (long) contaObj.get("numero");
                JSONObject dono = (JSONObject) contaObj.get("dono");
                
                if (numero == num) {
                	Pessoa p = new Pessoa((int) (long) dono.get("cpf"),"123");//muito funny o duplo typecast kkkk(não questione!)
                	p.setSenha((String) dono.get("senha"));
                    ContaComum c = new ContaComum((int) numero,p);
                    p.setConta(null);
                	return c;
                }

            }
        } catch (IOException | ParseException e) {
            System.out.println("Error reading or parsing the file.");
            e.printStackTrace();
        }
        
        return null;
    }
    public JSONObject pessoaToJson(Pessoa pessoa) {
        JSONObject jsonPessoa = new JSONObject();
        jsonPessoa.put("name", pessoa.getNome());
        jsonPessoa.put("cpf", pessoa.getCpf());
        jsonPessoa.put("senha", pessoa.getSenha());
        
        return jsonPessoa;
    }
}
