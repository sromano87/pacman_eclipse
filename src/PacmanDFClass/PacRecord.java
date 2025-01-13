package PacmanDFClass;

import java.io.RandomAccessFile;
import java.io.File;
import java.io.IOException;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JOptionPane;

public class PacRecord {
	private Record[] record = new Record[10];
	private final int SIZE = 40;

	public PacRecord() {
		File file = new File("PacRecord.dat");

		for (int i = 0; i < 10; i++)
			record[i] = new Record("_", 0, 0);

		if (!file.exists()) {
			try {
				RandomAccessFile output = new RandomAccessFile(file, "rw");

				for (int i = 0; i < 10; i++) {
					output.seek(i * SIZE);

					writeName(output, record[i].name);
					output.writeInt(record[i].level);
					output.writeInt(record[i].score);
				}

				output.close();
			} catch (IOException exception) {
				JOptionPane.showMessageDialog(null, "Errore nella gestione dei record", "Errore",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void writeName(RandomAccessFile file, String name) throws IOException {
		StringBuffer buffer = null;

		if (name != null)
			buffer = new StringBuffer(name);
		else
			buffer = new StringBuffer(16);

		buffer.setLength(16);

		file.writeChars(buffer.toString());
	}

	private void write() {
		try {
			RandomAccessFile output = new RandomAccessFile("PacRecord.dat", "rw");

			ordin();
			for (int i = 0; i < 10; i++) {
				output.seek(i * SIZE);

				writeName(output, record[i].name);
				output.writeInt(record[i].level);
				output.writeInt(record[i].score);
			}

			output.close();
		} catch (IOException exception) {
			JOptionPane.showMessageDialog(null, "Errore nella gestione dei record", "Errore",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private String readName(RandomAccessFile file) throws IOException {
		char[] name = new char[16];

		for (int i = 0; i < name.length; i++)
			name[i] = file.readChar();

		return new String(name).replace('\0', ' ');
	}

	private void read() {
		try {
			RandomAccessFile input = new RandomAccessFile("PacRecord.dat", "r");

			int n = 0;
			while (n < 10) {
				record[n].name = readName(input);
				record[n].level = input.readInt();
				record[n].score = input.readInt();
				n++;
			}

			input.close();
			ordin();
		} catch (IOException exception) {
			JOptionPane.showMessageDialog(null, "Errore nella gestione dei record", "Errore",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void ordin() {
		Record r = new Record("", 0, 0);

		for (int i = 0; i < 9; i++)
			for (int j = i + 1; j < 10; j++)
				if (record[i].score < record[j].score) {
					r = record[i];
					record[i] = record[j];
					record[j] = r;
				}
	}

	public void endGame(int level, int score) {
		File file = new File("PacRecord.dat");

		if (file.exists()) {
			for (int i = 0; i < 10; i++) {
				if (score < record[i].score) {
					String name = JOptionPane.showInputDialog(null,
							"Complimenti, nuovo record!\nInserisci il tuo nome:", "Nuovo record",
							JOptionPane.PLAIN_MESSAGE);
					if (name != null) {
						record[9].name = name;
						record[9].level = level;
						record[9].score = score;

						write();
					}
					break;
				}
			}
			int input = JOptionPane.showConfirmDialog(null, "Vuoi iniziare una nuova partita?", "Nuova partita", JOptionPane.YES_NO_OPTION);
			if (input != JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}
	}

	public void print(Graphics g) {
		File file = new File("PacRecord.dat");

		if (file.exists()) {
			read();

			g.setColor(Color.BLUE);
			g.fillRect(4 * 30, 30, 13 * 30, 13 * 30);
			g.setColor(Color.YELLOW);
			g.drawString("Classifica Top 10", 8 * 30, 2 * 30);
			g.setFont(new Font("Serif", Font.BOLD + Font.ITALIC, 16));
			g.drawString("    Nome                       Livello                      Punteggio", 5 * 30, 3 * 30);
			int count = 3 * 40;
			for (int i = 1; i <= 10; i++) {
				g.drawString("" + i, 4 * 30 + 10, count);
				count += 30;
			}

			g.setFont(new Font("Serif", Font.BOLD, 16));
			count = 3 * 40;
			for (int i = 0; i < 10; i++) {
				String stringName = record[i].name.replace('_', ' ');
				g.drawString(stringName, 4 * 30 + 30, count);
				if (record[i].level != 0) {
					g.drawString("" + record[i].level, 11 * 30 - 15, count);
					g.drawString("" + record[i].score, 15 * 30, count);
				}
				count += 30;
			}
		} else {
			g.setColor(Color.BLUE);
			g.fillRect(4 * 30, 30, 13 * 30, 13 * 30);
			g.setColor(Color.YELLOW);
			g.drawString("Classifica Top 10", 8 * 30, 2 * 30);
			g.setFont(new Font("Serif", Font.BOLD + Font.ITALIC, 16));
			g.drawString("    Nome                       Livello                      Punteggio", 5 * 30, 3 * 30);
			int count = 3 * 40;
			for (int i = 1; i <= 10; i++) {
				g.drawString("" + i, 4 * 30 + 10, count);
				count += 30;
			}
		}
	}
}

class Record {
	String name;
	int level;
	int score;

	Record(String name, int level, int score) {
		this.name = name;
		this.level = level;
		this.score = score;
	}
}