package PacmanDFClass;

import java.awt.Graphics;
import java.awt.Color;

public class PacMap {
	private PacCell[][] map;

	public PacMap() {
		map = new PacCell[21][15];

		for (int i = 0; i < 21; i++) {
			for (int j = 0; j < 15; j++) {
				map[i][j] = new PacCell(i * 30, i * 30 + 30, j * 30, j * 30 + 30, false, false);
			}
		}

		map[10][0].wallCell = true;
		map[19][0].wallCell = true;

		map[1][1].wallCell = true;
		map[2][1].wallCell = true;
		map[3][1].wallCell = true;
		map[4][1].wallCell = true;
		map[5][1].wallCell = true;
		map[7][1].wallCell = true;
		map[8][1].wallCell = true;
		map[12][1].wallCell = true;
		map[13][1].wallCell = true;
		map[15][1].wallCell = true;
		map[16][1].wallCell = true;
		map[17][1].wallCell = true;
		map[18][1].wallCell = true;
		map[19][1].wallCell = true;

		map[3][2].wallCell = true;
		map[7][2].wallCell = true;
		map[10][2].wallCell = true;
		map[13][2].wallCell = true;
		map[17][2].wallCell = true;

		map[1][3].wallCell = true;
		map[3][3].wallCell = true;
		map[5][3].wallCell = true;
		map[6][3].wallCell = true;
		map[7][3].wallCell = true;
		map[9][3].wallCell = true;
		map[10][3].wallCell = true;
		map[11][3].wallCell = true;
		map[13][3].wallCell = true;
		map[14][3].wallCell = true;
		map[15][3].wallCell = true;
		map[17][3].wallCell = true;
		map[19][3].wallCell = true;
		map[20][3].wallCell = true;

		map[7][4].wallCell = true;
		map[10][4].wallCell = true;
		map[13][4].wallCell = true;

		map[1][5].wallCell = true;
		map[2][5].wallCell = true;
		map[3][5].wallCell = true;
		map[5][5].wallCell = true;
		map[7][5].wallCell = true;
		map[8][5].wallCell = true;
		map[10][5].wallCell = true;
		map[12][5].wallCell = true;
		map[13][5].wallCell = true;
		map[15][5].wallCell = true;
		map[17][5].wallCell = true;
		map[18][5].wallCell = true;
		map[19][5].wallCell = true;

		map[5][6].wallCell = true;
		map[15][6].wallCell = true;

		map[1][7].wallCell = true;
		map[3][7].wallCell = true;
		map[4][7].wallCell = true;
		map[5][7].wallCell = true;
		map[6][7].wallCell = true;
		map[7][7].wallCell = true;
		map[9][7].wallCell = true;
		map[11][7].wallCell = true;
		map[13][7].wallCell = true;
		map[14][7].wallCell = true;
		map[15][7].wallCell = true;
		map[16][7].wallCell = true;
		map[17][7].wallCell = true;
		map[19][7].wallCell = true;

		map[5][8].wallCell = true;
		map[15][8].wallCell = true;

		map[1][9].wallCell = true;
		map[2][9].wallCell = true;
		map[3][9].wallCell = true;
		map[5][9].wallCell = true;
		map[7][9].wallCell = true;
		map[8][9].wallCell = true;
		map[10][9].wallCell = true;
		map[12][9].wallCell = true;
		map[13][9].wallCell = true;
		map[15][9].wallCell = true;
		map[17][9].wallCell = true;
		map[18][9].wallCell = true;
		map[19][9].wallCell = true;

		map[7][10].wallCell = true;
		map[10][10].wallCell = true;
		map[13][10].wallCell = true;

		map[0][11].wallCell = true;
		map[1][11].wallCell = true;
		map[3][11].wallCell = true;
		map[5][11].wallCell = true;
		map[6][11].wallCell = true;
		map[7][11].wallCell = true;
		map[9][11].wallCell = true;
		map[10][11].wallCell = true;
		map[11][11].wallCell = true;
		map[13][11].wallCell = true;
		map[14][11].wallCell = true;
		map[15][11].wallCell = true;
		map[17][11].wallCell = true;
		map[19][11].wallCell = true;

		map[3][12].wallCell = true;
		map[7][12].wallCell = true;
		map[10][12].wallCell = true;
		map[13][12].wallCell = true;
		map[17][12].wallCell = true;

		map[1][13].wallCell = true;
		map[2][13].wallCell = true;
		map[3][13].wallCell = true;
		map[4][13].wallCell = true;
		map[5][13].wallCell = true;
		map[7][13].wallCell = true;
		map[8][13].wallCell = true;
		map[12][13].wallCell = true;
		map[13][13].wallCell = true;
		map[15][13].wallCell = true;
		map[16][13].wallCell = true;
		map[17][13].wallCell = true;
		map[18][13].wallCell = true;
		map[19][13].wallCell = true;

		map[1][14].wallCell = true;
		map[10][14].wallCell = true;

		for (int i = 0; i < 21; i++) {
			for (int j = 0; j < 15; j++) {
				if (!isWall(i * 30, j * 30))
					map[i][j].ballCell = true;
				else
					map[i][j].ballCell = false;
			}
		}

		map[0][0].ballCell = false;
		map[20][0].ballCell = false;
		map[0][14].ballCell = false;
	}

	public void setBalls() {
		for (int i = 0; i < 21; i++) {
			for (int j = 0; j < 15; j++) {
				if (!isWall(i * 30, j * 30))
					map[i][j].ballCell = true;
				else
					map[i][j].ballCell = false;
			}
		}

		map[0][0].ballCell = false;
		map[20][0].ballCell = false;
		map[0][14].ballCell = false;
	}

	private int getMinX(int r, int c) {
		return map[r][c].minValueX;
	}

	private int getMaxX(int r, int c) {
		return map[r][c].maxValueX;
	}

	private int getMinY(int r, int c) {
		return map[r][c].minValueY;
	}

	private int getMaxY(int r, int c) {
		return map[r][c].maxValueY;
	}

	public boolean isWall(int x, int y) {
		int r, c;

		if (x >= 630)
			r = 20;
		else
			r = x / 30;

		if (y >= 450)
			c = 14;
		else
			c = y / 30;

		return map[r][c].wallCell;
	}

	public void drawMap(Graphics g) {
		for (int i = 0; i < 21; i++)
			for (int j = 0; j < 15; j++) {
				if (map[i][j].wallCell) {
					g.setColor(Color.BLUE);
					g.fillRoundRect(getMinX(i, j) + 1, getMinY(i, j) + 1, 29, 29, 10, 10);
				}

				if (map[i][j].ballCell) {
					g.setColor(Color.YELLOW);
					g.fillOval(getMinX(i, j) + 15, getMinY(i, j) + 15, 5, 5);
				}
			}
	}

	public boolean isEat(int x, int y) {
		int r, c;

		if (x >= 630)
			r = 20;
		else
			r = x / 30;

		if (y >= 450)
			c = 14;
		else
			c = y / 30;

		if (map[r][c].ballCell) {
			map[r][c].ballCell = false;
			return true;
		} else
			return false;
	}

	class PacCell {
		final int minValueX;
		final int maxValueX;
		final int minValueY;
		final int maxValueY;
		boolean wallCell;
		boolean ballCell;

		PacCell(int minX, int maxX, int minY, int maxY, boolean statusWall, boolean statusBall) {
			minValueX = minX;
			maxValueX = maxX;
			minValueY = minY;
			maxValueY = maxY;
			wallCell = statusWall;
			ballCell = statusBall;
		}
	}
}