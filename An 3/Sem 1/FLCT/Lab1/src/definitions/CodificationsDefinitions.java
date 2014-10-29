package definitions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

public class CodificationsDefinitions {
	
	private Map<String, Integer> reservedWords;
	private Map<String, Integer> operators;
	private Map<String, Integer> separators;
	
	public CodificationsDefinitions() {
		try {
			this.reservedWords = this.codificationFromFile("reserved_words.txt");
			this.operators = this.codificationFromFile("operators.txt");
			this.separators = this.codificationFromFile("separators.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Integer codeForString(String string) {
		Integer code = null;
		
		code = this.reservedWords.get(string);
		if (code == null) {
			code = this.operators.get(string);
			
			if (code == null) {
				code = this.separators.get(string);
			}
		}
		
		return code;
	}
	
	public Boolean isSeparator(char c) {
		String operator = new String();
		operator += c;
		
		Integer code = null;
		code = this.separators.get(operator);
		
		return code != null;
	}
	
	private Map<String, Integer> codificationFromFile(String filename) throws IOException {
		Stream<String> file = Files.lines(Paths.get(filename));
		
		Map<String, Integer> codes = new LinkedHashMap<String, Integer>();
		
		file.map(s -> s.split(" "))
			.forEach(tokens -> codes.put(tokens[0], Integer.parseInt(tokens[1])));
		
		file.close();
		
		return codes;
	}
}
