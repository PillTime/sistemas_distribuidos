import java.io.Console;
import java.io.File;


public class client {
	public static void main(String[] args) {
		final String prompt = " > ";
		final String programName = "FixNetCD";
		final String programFolder = System.getenv("HOME") + "/.local/share/" + programName;

		Console console = System.console();
		File folder = new File(programFolder);

		// Criar a pasta se não existir
		try {
			if (!folder.exists()) {
				folder.mkdirs();
			}
		}
		catch (Exception e) {
			System.out.println("O programa crashou a criar as pastas para guardar informação. RIP");
			e.printStackTrace();
			System.exit(1);
		}
		// Defini-la como a pasta atual do programa
		System.setProperty("user.dir", programFolder);

		while (true) {
			parseInput(console.readLine(prompt));
		}
	}

	private static void parseInput(String input) {
		if (input == null) {
			System.out.println("exit");
			System.exit(0);
		}

		// Usar espaços (e TABs) como separadores se palavras
		String[] words = input.split("\\s+");

		// Muitos `if`s, mais bonito em Rust
		if (words.length > 0) {
			if (words[0].matches("exit")) {
				System.exit(0);
			}
			else if (words[0].matches("help")) {
				doHelp();
			}
			else if (words[0].matches("seeder")) {
				if (words.length > 1) {
					if (words[1].matches("list")) {
						if (words.length == 2) {
							doSeederList();
						}
						else {
							System.out.println("`seeder list` não usa argumentos.");
						}
					}
					else if (words[1].matches("search")) {
						if (words.length > 2) {
							doSeederSearch();
						}
						else {
							System.out.println("`seeder search` precisa de argumentos. Faz `help` para veres um exemplo.");
						}
					}
					else {
						System.out.println("Comando desconheido. Faz `help` para ver os comandos possíveis.");
					}
				}
				else {
					System.out.println("`seeder` precisa de argumentos. Faz `help` para veres alguns exemplos.");
				}
			}
			else if (words[0].matches("download")) {
				if (words.length > 1) {
					doDownload();
				}
				else {
					System.out.println("`download` precisa de argumentos. Faz `help` para veres um exemplo.");
				}
			}
			else if (words[0].matches("list")) {
				if (words.length > 1) {
					if (words[1].matches("files") && words.length == 2) {
						doListFiles();
					}
					else {
						System.out.println("Comando desconheido. Faz `help` para ver os comandos possíveis.");
					}
				}
				else {					
					System.out.println("`list` precisa de um argumento. Faz `help` para veres um exemplo.");
				}
			}
			else if (words[0].matches("info")) {
				if (words.length > 1) {
					doInfo();
				}
				else {
					System.out.println("`info` precisa de argumentos. Faz `help` para veres um exemplo.");
				}
			}
			else if (words[0].matches("play")) {
				if (words.length > 1) {
					doPlay(words);
				}
				else {
					System.out.println("`play` precisa de argumentos. Faz `help` para veres um exemplo.");
				}
			}
			else {
				System.out.println("Comando desconheido. Faz `help` para ver os comandos possíveis.");
			}
		}
	}

	private static void doHelp() {
		System.out.println("================================================================");
		System.out.println(" Mostrar os comandos todos:");
		System.out.println("     help");
		System.out.println(" Sair do programa:");
		System.out.println("     exit");
		System.out.println(" Mostrar a lista de seeders:");
		System.out.println("     seeder list");
		System.out.println(" Mostrar a lista de seeders que contêm certas keywords:");
		System.out.println("     seeder search [keyword1, keyword2, ...]");
		System.out.println(" Fazer download de um ficheiro:");
		System.out.println("     download [file]");
		System.out.println(" Mostrar todos os ficheiros:");
		System.out.println("     list files");
		System.out.println(" Mostrar a informação de um ficheiro:");
		System.out.println("     info [file]");
		System.out.println(" Reproduzir um ficheiro:");
		System.out.println("     play [file]");
		System.out.println("================================================================");
	}

	private static void doSeederList() {
	}

	private static void doSeederSearch() {
	}

	private static void doDownload() {
	}

	private static void doListFiles() {
	}

	private static void doInfo() {
	}

	private static void doPlay(String[] words) {
		// O Java é estúpido no que toca a mudar o diretório atual
		// Por isso é que isto pode parecer meio esquisito
		// A propriedade "user.dir" é o diretório atual aqui
		try {
			File file = new File(System.getProperty("user.dir"), words[1]);
			
			if (file.exists()) {
				Runtime.getRuntime().exec("ffplay " + file.getAbsolutePath());
			}
			else {
				System.out.println("O ficheiro não existe.");
			}
		}
		catch (Exception e) {
			System.out.println("A tentar correr `ffplay` o programa crashou. RIP");
			e.printStackTrace();
			System.exit(1);
		}
	}
}
