package br.edu.insper.desagil.aula10;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class FileManager {
	// Não precisa entender o código abaixo. (mas
	// claro que pode perguntar se estiver curioso)

	private CharsetDecoder decoder;

	public FileManager() {
		this.decoder = Charset.forName("UTF-8").newDecoder();
	}

	// Não precisa entender o código acima. (mas
	// claro que pode perguntar se estiver curioso)

	public String load(String path) throws LoadException {
		String content = null;

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), decoder))) {
			String line;
			StringBuilder builder = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				builder.append(line + '\n');
			}
			content = builder.toString();
		} catch (FileNotFoundException exception) {
			throw new LoadException("Arquivo não encontrado: " + exception.getMessage());
		} catch (IOException readException) {
			throw new LoadException("Erro de leitura: " + readException.getMessage());
		}

		return content;
	}

	public void save(String path, String content) throws SaveException {
		try (FileWriter writer = new FileWriter(path)) {
			writer.write(content);
		} catch (IOException writeException) {
			throw new SaveException("Erro de escrita: " + writeException.getMessage());
		}
	}
}
