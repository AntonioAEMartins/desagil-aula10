package br.edu.insper.desagil.aula10;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileManager {
	// Não precisa entender o código abaixo. (mas
	// claro que pode perguntar se estiver curioso)

	private List<String> log;
	private CharsetDecoder decoder;

	public FileManager() {
		this.log = new ArrayList<>();
		this.decoder = Charset.forName("UTF-8").newDecoder();
	}

	public List<String> getLog() {
		return Collections.unmodifiableList(log);
	}

	// Não precisa entender o código acima. (mas
	// claro que pode perguntar se estiver curioso)

	public String load(String path) {
		String content;

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), decoder));
			log.add("Leitor aberto");

			String line;
			StringBuilder builder = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				builder.append(line + '\n');
			}
			content = builder.toString();
			log.add("Conteúdo lido");

			reader.close();
			log.add("Leitor fechado");
		} catch (FileNotFoundException exception) {
			log.add("Arquivo não encontrado: " + exception.getMessage());
			content = null;
		} catch (IOException exception) {
			log.add("Erro de leitura: " + exception.getMessage());
			content = null;
		}

		return content;
	}

	public void save(String path, String content) {
		try {
			FileWriter writer = new FileWriter(path);
			log.add("Escritor aberto");

			writer.write(content);
			log.add("Conteúdo escrito");

			writer.close();
			log.add("Escritor fechado");
		} catch (IOException exception) {
			log.add("Erro de escrita: " + exception.getMessage());
		}
	}
}
