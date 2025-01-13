import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import PacmanDFClass.PacMap;
import PacmanDFClass.PacEnemy;
import PacmanDFClass.PacRecord;

public class PacmanDF extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private PacMap map;
	private PacEnemy enemy;
	private PacEnemy enemy2;
	private PacEnemy enemy3;
	private PacEnemy enemy4;
	private Icon pacmanDX;
	private Icon pacmanSX;
	private Icon pacmanUP;
	private Icon pacmanDW;
	private Icon star;
	private Point pacmanCoords;
	private boolean[] flagMovement = new boolean[4];
	private boolean[] flagDirection = new boolean[4];
	private boolean[] visibleEnemy = new boolean[4];
	private boolean flagStarEast = true;
	private boolean flagStarWest = true;
	private boolean ifEast = true;
	private boolean ifWest = true;
	private boolean isEndedLevel = false;
	private boolean isEscape = false;
	private boolean endGame = false;
	private Timer timer;
	private int currentDirection;
	private int currentScore = 0;
	private int currentLife = 3;
	private int currentLevel = 1;
	private int ifEastCounter = 0;
	private int ifWestCounter = 0;
	private int currentBall = 0;
	private int tmpLevel;
	private int tmpScore;
	private int[] enemyCounter = new int[4];
	private PacRecord pacRecord;

	public PacmanDF() {
		setFocusable(true);

		map = new PacMap();
		enemy = new PacEnemy();
		enemy2 = new PacEnemy();
		enemy3 = new PacEnemy();
		enemy4 = new PacEnemy();

		pacmanDX = new ImageIcon(getClass().getResource("pacmanDX.gif"));
		pacmanSX = new ImageIcon(getClass().getResource("pacmanSX.gif"));
		pacmanUP = new ImageIcon(getClass().getResource("pacmanUP.gif"));
		pacmanDW = new ImageIcon(getClass().getResource("pacmanDW.gif"));
		star = new ImageIcon(getClass().getResource("star.gif"));

		pacmanCoords = new Point(0, 0);

		for (int i = 0; i < 4; i++) {
			flagMovement[i] = false;
			flagDirection[i] = false;
			visibleEnemy[i] = true;
		}

		pacRecord = new PacRecord();

		timer = new Timer(16, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (endGame) {
					pacRecord.endGame(tmpLevel, tmpScore);
					tmpLevel = 0;
					tmpScore = 0;
					endGame = false;
				}

				if (!visibleEnemy[0]) {
					enemyCounter[0]++;
					if (enemyCounter[0] > 400) {
						visibleEnemy[0] = true;
						enemy.setCoords(0, 0);
						enemyCounter[0] = 0;
					}
				}
				if (!visibleEnemy[1]) {
					enemyCounter[1]++;
					if (enemyCounter[1] > 400) {
						visibleEnemy[1] = true;
						enemy2.setCoords(20 * 30, 14 * 30);
						enemyCounter[1] = 0;
					}
				}
				if (!visibleEnemy[2]) {
					enemyCounter[2]++;
					if (enemyCounter[2] > 400) {
						visibleEnemy[2] = true;
						enemy3.setCoords(0, 0);
						enemyCounter[2] = 0;
					}
				}
				if (!visibleEnemy[3]) {
					enemyCounter[3]++;
					if (enemyCounter[3] > 400) {
						visibleEnemy[3] = true;
						enemy4.setCoords(20 * 30, 14 * 30);
						enemyCounter[3] = 0;
					}
				}

				if (visibleEnemy[0])
					enemy.moveEnemy(pacmanCoords, map);
				if (visibleEnemy[1])
					enemy2.moveEnemy(pacmanCoords, map);
				if (visibleEnemy[2])
					enemy3.moveEnemy(pacmanCoords, map);
				if (visibleEnemy[3])
					enemy4.moveEnemy(pacmanCoords, map);

				if (map.isEat(pacmanCoords.x, pacmanCoords.y)) {
					currentScore--;
					currentBall++;
					if (currentBall == 192) {
						currentBall = 0;
						isEndedLevel = true;
						timer.stop();
					}
				}

				if (pacmanCoords.x / 30 == 20 && pacmanCoords.y / 30 == 0) {
					if (flagStarEast)
						currentScore += 10;
					flagStarEast = false;
				}

				if (pacmanCoords.x / 30 == 0 && pacmanCoords.y / 30 == 14) {
					if (flagStarWest)
						currentScore += 10;
					flagStarWest = false;
				}

				if ((!flagStarEast && ifEast) || (!flagStarWest && ifWest)) {
					if (visibleEnemy[0]) {
						if (enemy.isDeath(pacmanCoords)) {
							visibleEnemy[0] = false;
							currentScore += currentLevel * 5;
						}
					}
					if (visibleEnemy[1]) {
						if (enemy2.isDeath(pacmanCoords)) {
							visibleEnemy[1] = false;
							currentScore += currentLevel * 5;
						}
					}
					if (visibleEnemy[2]) {
						if (enemy3.isDeath(pacmanCoords)) {
							visibleEnemy[2] = false;
							currentScore += currentLevel * 5;
						}
					}
					if (visibleEnemy[3]) {
						if (enemy4.isDeath(pacmanCoords)) {
							visibleEnemy[3] = true;
							currentScore += currentLevel * 5;
						}
					}
				} else {
					if (visibleEnemy[0]) {
						if (enemy.isDeath(pacmanCoords))
							timer.stop();
					}
					if (visibleEnemy[1]) {
						if (enemy2.isDeath(pacmanCoords))
							timer.stop();
					}
					if (visibleEnemy[2]) {
						if (enemy3.isDeath(pacmanCoords))
							timer.stop();
					}
					if (visibleEnemy[3]) {
						if (enemy4.isDeath(pacmanCoords))
							timer.stop();
					}
				}

				if (flagMovement[0]) {
					if (pacmanCoords.x < 600 && map.isWall(pacmanCoords.x + 30, pacmanCoords.y)) {
						if (flagDirection[2]) {
							if (pacmanCoords.x % 30 == 0) {
								flagMovement[2] = true;
								flagMovement[0] = false;

								flagDirection[2] = false;
							} else {
								pacmanCoords.x += 2;
								currentDirection = 0;
							}
						} else if (flagDirection[3]) {
							if (pacmanCoords.x % 30 == 0) {
								flagMovement[3] = true;
								flagMovement[0] = false;

								flagDirection[3] = false;
							} else {
								pacmanCoords.x += 2;
								currentDirection = 0;
							}
						} else {
							pacmanCoords.x += 2;
							currentDirection = 0;
						}
					} else {
						flagMovement[0] = false;
						if (flagDirection[2]) {
							flagMovement[2] = true;
							flagDirection[2] = false;
						} else if (flagDirection[3]) {
							flagMovement[3] = true;
							flagDirection[3] = false;
						}
					}
				} else if (flagMovement[1]) {
					if (pacmanCoords.x > 0 && !map.isWall(pacmanCoords.x - 1, pacmanCoords.y)) {
						if (flagDirection[2]) {
							if (pacmanCoords.x % 30 == 0) {
								flagMovement[2] = true;
								flagMovement[1] = false;

								flagDirection[2] = false;
							} else {
								pacmanCoords.x -= 2;
								currentDirection = 1;
							}
						} else if (flagDirection[3]) {
							if (pacmanCoords.x % 30 == 0) {
								flagMovement[3] = true;
								flagMovement[1] = false;

								flagDirection[3] = false;
							} else {
								pacmanCoords.x -= 2;
								currentDirection = 1;
							}
						} else {
							pacmanCoords.x -= 2;
							currentDirection = 1;
						}
					} else {
						flagMovement[1] = false;
						if (flagDirection[2]) {
							flagMovement[2] = true;
							flagDirection[2] = false;
						} else if (flagDirection[3]) {
							flagMovement[3] = true;
							flagDirection[3] = false;
						}
					}
				} else if (flagMovement[2]) {
					if (pacmanCoords.y > 0 && !map.isWall(pacmanCoords.x, pacmanCoords.y - 1)) {
						if (flagDirection[0]) {
							if (pacmanCoords.y % 30 == 0) {
								flagMovement[0] = true;
								flagMovement[2] = false;

								flagDirection[0] = false;
							} else {
								pacmanCoords.y -= 2;
								currentDirection = 2;
							}
						} else if (flagDirection[1]) {
							if (pacmanCoords.y % 30 == 0) {
								flagMovement[1] = true;
								flagMovement[2] = false;

								flagDirection[1] = false;
							} else {
								pacmanCoords.y -= 2;
								currentDirection = 2;
							}
						} else {
							pacmanCoords.y -= 2;
							currentDirection = 2;
						}
					} else {
						flagMovement[2] = false;
						if (flagDirection[0]) {
							flagMovement[0] = true;
							flagDirection[0] = false;
						} else if (flagDirection[1]) {
							flagMovement[1] = true;
							flagDirection[1] = false;
						}
					}
				} else if (flagMovement[3]) {
					if (pacmanCoords.y < 420 && !map.isWall(pacmanCoords.x, pacmanCoords.y + 30)) {
						if (flagDirection[0]) {
							if (pacmanCoords.y % 30 == 0) {
								flagMovement[0] = true;
								flagMovement[3] = false;

								flagDirection[0] = false;
							} else {
								pacmanCoords.y += 2;
								currentDirection = 3;
							}
						} else if (flagDirection[1]) {
							if (pacmanCoords.y % 30 == 0) {
								flagMovement[1] = true;
								flagMovement[3] = false;

								flagDirection[1] = false;
							} else {
								pacmanCoords.y += 2;
								currentDirection = 3;
							}
						} else {
							pacmanCoords.y += 2;
							currentDirection = 3;
						}
					} else {
						flagMovement[3] = false;
						if (flagDirection[0]) {
							flagMovement[0] = true;
							flagDirection[0] = false;
						} else if (flagDirection[1]) {
							flagMovement[1] = true;
							flagDirection[1] = false;
						}
					}
				}

				repaint();
			}
		});
		timer.start();

		addKeyListener(new PacmanHandler());
	}

	private class PacmanHandler extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			if (isEscape) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_ESCAPE:
					isEscape = false;
					timer.restart();
				}
			} else {
				flagDirection[0] = false;
				flagDirection[1] = false;
				flagDirection[2] = false;
				flagDirection[3] = false;

				switch (e.getKeyCode()) {
				case KeyEvent.VK_RIGHT:
					if (flagMovement[2] || flagMovement[3])
						flagDirection[0] = true;
					else {
						flagMovement[0] = true;
						flagMovement[1] = false;
					}
					break;

				case KeyEvent.VK_LEFT:
					if (flagMovement[2] || flagMovement[3])
						flagDirection[1] = true;
					else {
						flagMovement[0] = false;
						flagMovement[1] = true;
					}
					break;

				case KeyEvent.VK_UP:
					if (flagMovement[0] || flagMovement[1])
						flagDirection[2] = true;
					else {
						flagMovement[2] = true;
						flagMovement[3] = false;
					}
					break;

				case KeyEvent.VK_DOWN:
					if (flagMovement[0] || flagMovement[1])
						flagDirection[3] = true;
					else {
						flagMovement[2] = false;
						flagMovement[3] = true;
					}
					break;

				case KeyEvent.VK_ESCAPE:
					isEscape = true;
					timer.stop();
				}
			}
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		map.drawMap(g);
		if (flagStarEast)
			star.paintIcon(this, g, 20 * 30, 0);
		if (flagStarWest)
			star.paintIcon(this, g, 0, 14 * 30);

		g.setColor(Color.YELLOW);
		if (!flagStarEast && ifEast) {
			if (timer.isRunning())
				ifEastCounter++;
			if (ifEastCounter > 700) {
				if (ifEastCounter % 2 == 0)
					for (int i = 0; i < 3; i++)
						g.drawOval(pacmanCoords.x - 5 + i, pacmanCoords.y - 5 + i, 40 - i * 2, 40 - i * 2);
			} else
				for (int i = 0; i < 3; i++)
					g.drawOval(pacmanCoords.x - 5 + i, pacmanCoords.y - 5 + i, 40 - i * 2, 40 - i * 2);

			if (ifEastCounter == 1000)
				ifEast = false;
		}

		if (!flagStarWest && ifWest) {
			if (timer.isRunning())
				ifWestCounter++;
			if (ifWestCounter > 700) {
				if (ifWestCounter % 2 == 0)
					for (int i = 0; i < 3; i++)
						g.drawOval(pacmanCoords.x - 5 + i, pacmanCoords.y - 5 + i, 40 - i * 2, 40 - i * 2);
			} else
				for (int i = 0; i < 3; i++)
					g.drawOval(pacmanCoords.x - 5 + i, pacmanCoords.y - 5 + i, 40 - i * 2, 40 - i * 2);

			if (ifWestCounter == 1000)
				ifWest = false;
		}

		if (visibleEnemy[0])
			enemy.paintEnemy(this, g);
		if (visibleEnemy[1])
			enemy2.paintEnemy(this, g);
		if (visibleEnemy[2])
			enemy3.paintEnemy(this, g);
		if (visibleEnemy[3])
			enemy4.paintEnemy(this, g);

		g.setFont(new Font("Serif", Font.BOLD, 20));
		g.drawString("Punteggio = " + currentScore, 10, 475);
		g.drawString("Vite = " + currentLife, 170, 475);
		g.drawString("Livello = " + currentLevel, 280, 475);
		g.setFont(new Font("Serif", Font.BOLD, 14));
		g.drawString("Premi ESC per vedere la classifica", 415, 475);

		g.setColor(Color.BLUE);
		g.fillRect(0, 450, 630, 5);

		if (timer.isRunning()) {
			switch (currentDirection) {
			case 0:
				pacmanDX.paintIcon(this, g, pacmanCoords.x, pacmanCoords.y);
				break;

			case 1:
				pacmanSX.paintIcon(this, g, pacmanCoords.x, pacmanCoords.y);
				break;

			case 2:
				pacmanUP.paintIcon(this, g, pacmanCoords.x, pacmanCoords.y);
				break;

			case 3:
				pacmanDW.paintIcon(this, g, pacmanCoords.x, pacmanCoords.y);
			}
		} else {
			if (isEscape) {
				switch (currentDirection) {
				case 0:
					pacmanDX.paintIcon(this, g, pacmanCoords.x, pacmanCoords.y);
					break;

				case 1:
					pacmanSX.paintIcon(this, g, pacmanCoords.x, pacmanCoords.y);
					break;

				case 2:
					pacmanUP.paintIcon(this, g, pacmanCoords.x, pacmanCoords.y);
					break;

				case 3:
					pacmanDW.paintIcon(this, g, pacmanCoords.x, pacmanCoords.y);
				}

				for (int i = 0; i < 630; i += 3)
					for (int j = 0; j < 4540; j += 3) {
						g.setColor(Color.BLACK);
						g.drawRect(i, j, 3, 3);
					}

				pacRecord.print(g);

				timer.stop();
			} else if (isEndedLevel) {
				isEndedLevel = false;
				currentScore += currentLevel * 50;
				currentLevel++;
				currentLife++;
				flagStarEast = true;
				flagStarWest = true;
				ifEast = true;
				ifWest = true;
				ifEastCounter = 0;
				ifWestCounter = 0;
				for (int i = 0; i < 4; i++) {
					flagMovement[i] = false;
					flagDirection[i] = false;
					visibleEnemy[i] = true;
				}
				map.setBalls();
				pacmanCoords.x = 0;
				pacmanCoords.y = 0;
				enemy.setCoords(20 * 30, 14 * 30);
				enemy2.setCoords(20 * 30, 14 * 30);
				enemy3.setCoords(20 * 30, 14 * 30);
				enemy4.setCoords(20 * 30, 14 * 30);
				currentDirection = 0;
				timer.restart();
			} else if (currentLife > 1) {
				currentLife--;

				for (int i = 0; i < 4; i++) {
					flagMovement[i] = false;
					flagDirection[i] = false;
					visibleEnemy[i] = true;
				}
				pacmanCoords.x = 0;
				pacmanCoords.y = 0;
				enemy.setCoords(20 * 30, 14 * 30);
				enemy2.setCoords(20 * 30, 14 * 30);
				enemy3.setCoords(20 * 30, 14 * 30);
				enemy4.setCoords(20 * 30, 14 * 30);
				currentDirection = 0;
				currentScore -= 10;
				timer.restart();
			} else {
				endGame = true;
				tmpLevel = currentLevel;
				tmpScore = currentScore - 10;
				flagStarEast = true;
				flagStarWest = true;
				ifEast = true;
				ifWest = true;
				ifEastCounter = 0;
				ifWestCounter = 0;
				for (int i = 0; i < 4; i++) {
					flagMovement[i] = false;
					flagDirection[i] = false;
					visibleEnemy[i] = true;
				}
				map.setBalls();
				pacmanCoords.x = 0;
				pacmanCoords.y = 0;
				enemy.setCoords(20 * 30, 14 * 30);
				enemy2.setCoords(20 * 30, 14 * 30);
				enemy3.setCoords(20 * 30, 14 * 30);
				enemy4.setCoords(20 * 30, 14 * 30);
				currentLevel = 1;
				currentScore = 0;
				currentDirection = 0;
				currentBall = 0;
				timer.restart();
			}
		}
	}

	public static void main(String[] args) {
		PacmanDF panel = new PacmanDF();
		panel.setBackground(Color.BLACK);
		panel.setPreferredSize(new Dimension(637, 513));

		JFrame frame = new JFrame("Pacman DF");
		frame.setSize(637, 513);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Toolkit kit = Toolkit.getDefaultToolkit();
		Image logo = kit.getImage("logo.gif");
		frame.setIconImage(logo);

		frame.add(panel);

		frame.setVisible(true);
	}
}