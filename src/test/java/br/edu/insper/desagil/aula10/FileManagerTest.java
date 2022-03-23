package br.edu.insper.desagil.aula10;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FileManagerTest {
	private FileManager f;

	@BeforeEach
	private void setUp() throws IOException {
		f = new FileManager();
	}

	@Test
	public void testLoad() {
		String path = "entrada.txt";
		String content = f.load(path);
		assertEquals("hello world\n", content);
		List<String> log = f.getLog();
		assertEquals(3, log.size());
		assertEquals("Leitor aberto", log.get(0));
		assertEquals("Conteúdo lido", log.get(1));
		assertEquals("Leitor fechado", log.get(2));
	}

	@Test
	public void testLoadMissing() {
		String path = "missing.txt";
		String content = f.load(path);
		assertNull(content);
		List<String> log = f.getLog();
		assertEquals(1, log.size());
		assertTrue(log.get(0).startsWith("Arquivo não encontrado: "));
	}

	@Test
	public void testLoadInvalid() {
		String path = "binario.txt";
		String content = f.load(path);
		assertNull(content);
		List<String> log = f.getLog();
		assertEquals(3, log.size());
		assertEquals("Leitor aberto", log.get(0));
		assertTrue(log.get(1).startsWith("Erro de leitura: "));
		assertEquals("Leitor fechado", log.get(2));
	}

	@Test
	public void testSave() {
		String path = "saida.txt";
		String content = "goodbye universe\n";
		f.save(path, content);
		List<String> log = f.getLog();
		assertEquals(3, log.size());
		assertEquals("Escritor aberto", log.get(0));
		assertEquals("Conteúdo escrito", log.get(1));
		assertEquals("Escritor fechado", log.get(2));
	}
}
