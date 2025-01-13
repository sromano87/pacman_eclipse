package PacmanDFClass;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;

public class PacEnemy {
	private Icon enemy;
	private Point enemyCoords;
	private boolean[] flagMovement = new boolean[4];
	private Random random;
	private int randomNumber = 0;

	public PacEnemy() {
		this(20 * 30, 14 * 30);
	}

	public PacEnemy(int r, int c) {
		enemy = new ImageIcon(getClass().getResource("enemy.gif"));
		enemyCoords = new Point(r, c);

		for (int i = 0; i < 4; i++)
			flagMovement[i] = false;

		random = new Random();

		Timer timer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				randomNumber = random.nextInt(2);
			}
		});
		timer.start();
	}

	public void moveEnemy(Point pacmanCoords, PacMap map) {
		if (randomNumber == 0) {
			if (enemyCoords.x <= pacmanCoords.x && enemyCoords.x < 600) {
				if (!map.isWall(enemyCoords.x + 30, enemyCoords.y) && enemyCoords.y % 30 == 0) {
					flagMovement[0] = true;
					flagMovement[1] = false;
					flagMovement[2] = false;
					flagMovement[3] = false;
				} else if (!map.isWall(enemyCoords.x, enemyCoords.y - 1) && enemyCoords.x % 30 == 0
						&& !flagMovement[3]) {
					flagMovement[0] = false;
					flagMovement[1] = false;
					flagMovement[2] = true;
					flagMovement[3] = false;
				} else if (!map.isWall(enemyCoords.x, enemyCoords.y + 30) && enemyCoords.x % 30 == 0
						&& !flagMovement[2]) {
					flagMovement[0] = false;
					flagMovement[1] = false;
					flagMovement[2] = false;
					flagMovement[3] = true;
				}
			} else if (enemyCoords.x > pacmanCoords.x && enemyCoords.x > 0) {
				if (!map.isWall(enemyCoords.x - 1, enemyCoords.y) && enemyCoords.y % 30 == 0 && !flagMovement[0]) {
					flagMovement[0] = false;
					flagMovement[1] = true;
					flagMovement[2] = false;
					flagMovement[3] = false;
				} else if (!map.isWall(enemyCoords.x, enemyCoords.y - 1) && enemyCoords.x % 30 == 0
						&& !flagMovement[3]) {
					flagMovement[0] = false;
					flagMovement[1] = false;
					flagMovement[2] = true;
					flagMovement[3] = false;
				} else if (!map.isWall(enemyCoords.x, enemyCoords.y + 30) && enemyCoords.x % 30 == 0
						&& !flagMovement[2]) {
					flagMovement[0] = false;
					flagMovement[1] = false;
					flagMovement[2] = false;
					flagMovement[3] = true;
				}
			} else if (enemyCoords.y <= pacmanCoords.y && enemyCoords.y < 420) {
				if (!map.isWall(enemyCoords.x, enemyCoords.y + 30) && enemyCoords.x % 30 == 0) {
					flagMovement[0] = false;
					flagMovement[1] = false;
					flagMovement[2] = false;
					flagMovement[3] = true;
				} else if (!map.isWall(enemyCoords.x + 30, enemyCoords.y) && enemyCoords.y % 30 == 0
						&& !flagMovement[1]) {
					flagMovement[0] = true;
					flagMovement[1] = false;
					flagMovement[2] = false;
					flagMovement[3] = false;
				} else if (!map.isWall(enemyCoords.x - 1, enemyCoords.y) && enemyCoords.y % 30 == 0
						&& !flagMovement[0]) {
					flagMovement[0] = false;
					flagMovement[1] = true;
					flagMovement[2] = false;
					flagMovement[3] = false;
				}
			} else if (enemyCoords.y > pacmanCoords.y && enemyCoords.y > 0) {
				if (!map.isWall(enemyCoords.x, enemyCoords.y - 1) && enemyCoords.x % 30 == 0) {
					flagMovement[0] = false;
					flagMovement[1] = false;
					flagMovement[2] = true;
					flagMovement[3] = false;
				} else if (!map.isWall(enemyCoords.x + 30, enemyCoords.y) && enemyCoords.y % 30 == 0
						&& !flagMovement[1]) {
					flagMovement[0] = true;
					flagMovement[1] = false;
					flagMovement[2] = false;
					flagMovement[3] = false;
				} else if (!map.isWall(enemyCoords.x - 1, enemyCoords.y) && enemyCoords.y % 30 == 0
						&& !flagMovement[0]) {
					flagMovement[0] = false;
					flagMovement[1] = true;
					flagMovement[2] = false;
					flagMovement[3] = false;
				}
			}
		} else {
			if (enemyCoords.y <= pacmanCoords.y && enemyCoords.y < 420) {
				if (!map.isWall(enemyCoords.x, enemyCoords.y + 30) && enemyCoords.x % 30 == 0) {
					flagMovement[0] = false;
					flagMovement[1] = false;
					flagMovement[2] = false;
					flagMovement[3] = true;
				} else if (!map.isWall(enemyCoords.x + 30, enemyCoords.y) && enemyCoords.y % 30 == 0
						&& !flagMovement[1]) {
					flagMovement[0] = true;
					flagMovement[1] = false;
					flagMovement[2] = false;
					flagMovement[3] = false;
				} else if (!map.isWall(enemyCoords.x - 1, enemyCoords.y) && enemyCoords.y % 30 == 0
						&& !flagMovement[0]) {
					flagMovement[0] = false;
					flagMovement[1] = true;
					flagMovement[2] = false;
					flagMovement[3] = false;
				}
			} else if (enemyCoords.y > pacmanCoords.y && enemyCoords.y > 0) {
				if (!map.isWall(enemyCoords.x, enemyCoords.y - 1) && enemyCoords.x % 30 == 0) {
					flagMovement[0] = false;
					flagMovement[1] = false;
					flagMovement[2] = true;
					flagMovement[3] = false;
				} else if (!map.isWall(enemyCoords.x + 30, enemyCoords.y) && enemyCoords.y % 30 == 0
						&& !flagMovement[1]) {
					flagMovement[0] = true;
					flagMovement[1] = false;
					flagMovement[2] = false;
					flagMovement[3] = false;
				} else if (!map.isWall(enemyCoords.x - 1, enemyCoords.y) && enemyCoords.y % 30 == 0
						&& !flagMovement[0]) {
					flagMovement[0] = false;
					flagMovement[1] = true;
					flagMovement[2] = false;
					flagMovement[3] = false;
				}
			} else if (enemyCoords.x <= pacmanCoords.x && enemyCoords.x < 600) {
				if (!map.isWall(enemyCoords.x + 30, enemyCoords.y) && enemyCoords.y % 30 == 0) {
					flagMovement[0] = true;
					flagMovement[1] = false;
					flagMovement[2] = false;
					flagMovement[3] = false;
				} else if (!map.isWall(enemyCoords.x, enemyCoords.y - 1) && enemyCoords.x % 30 == 0
						&& !flagMovement[3]) {
					flagMovement[0] = false;
					flagMovement[1] = false;
					flagMovement[2] = true;
					flagMovement[3] = false;
				} else if (!map.isWall(enemyCoords.x, enemyCoords.y + 30) && enemyCoords.x % 30 == 0
						&& !flagMovement[2]) {
					flagMovement[0] = false;
					flagMovement[1] = false;
					flagMovement[2] = false;
					flagMovement[3] = true;
				}
			} else if (enemyCoords.x > pacmanCoords.x && enemyCoords.x > 0) {
				if (!map.isWall(enemyCoords.x - 1, enemyCoords.y) && enemyCoords.y % 30 == 0 && !flagMovement[0]) {
					flagMovement[0] = false;
					flagMovement[1] = true;
					flagMovement[2] = false;
					flagMovement[3] = false;
				} else if (!map.isWall(enemyCoords.x, enemyCoords.y - 1) && enemyCoords.x % 30 == 0
						&& !flagMovement[3]) {
					flagMovement[0] = false;
					flagMovement[1] = false;
					flagMovement[2] = true;
					flagMovement[3] = false;
				} else if (!map.isWall(enemyCoords.x, enemyCoords.y + 30) && enemyCoords.x % 30 == 0
						&& !flagMovement[2]) {
					flagMovement[0] = false;
					flagMovement[1] = false;
					flagMovement[2] = false;
					flagMovement[3] = true;
				}
			}
		}

		if (flagMovement[0] && !map.isWall(enemyCoords.x + 30, enemyCoords.y) && enemyCoords.x < 600)
			enemyCoords.x += 2;
		else if (flagMovement[1] && !map.isWall(enemyCoords.x - 1, enemyCoords.y) && enemyCoords.x > 0)
			enemyCoords.x -= 2;
		else if (flagMovement[2] && !map.isWall(enemyCoords.x, enemyCoords.y - 1) && enemyCoords.y > 0)
			enemyCoords.y -= 2;
		else if (flagMovement[3] && !map.isWall(enemyCoords.x, enemyCoords.y + 30) && enemyCoords.y < 420)
			enemyCoords.y += 2;
	}

	public void setCoords(int x, int y) {
		enemyCoords.x = x;
		enemyCoords.y = y;
	}

	public boolean isDeath(Point pacmanCoords) {
		if (enemyCoords.x / 30 == pacmanCoords.x / 30 || enemyCoords.y / 30 == pacmanCoords.y / 30)
			return true;
		return false;
	}

	public void paintEnemy(JComponent component, Graphics g) {
		enemy.paintIcon(component, g, enemyCoords.x, enemyCoords.y);
	}
}