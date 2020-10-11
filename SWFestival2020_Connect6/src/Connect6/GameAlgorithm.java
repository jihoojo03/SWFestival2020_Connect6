package Connect6;

public class GameAlgorithm {
	public static final double ONE_OPEN1 = 0.5;
	public static final double ONE_OPEN2 = 1;
	public static final double TWO_OPEN1 = 2;
	public static final double TWO_OPEN2 = 5;
	
	private int amount;			// 총 돌갯수
	private int currentTurn;	// 0 : 흑, 1 : 백
	private int stone[][];		// -1 : default, 0 : 흑, 1 : 백, 2 : 중립돌, 99 : 벽
	private int nextStone[][][];	// -1 : default, 0 : 흑, 1 : 백, 2 : 중립돌, 99 : 벽
	private boolean isFirst;
	private boolean isAttack;
	
	double[][] all1 = new double[21][21];
	double[][] hor1 = new double[21][21];
	double[][] ver1 = new double[21][21];
	double[][] left1 = new double[21][21];
	double[][] right1 = new double[21][21];
	
	double[][] all2 = new double[21][21];
	double[][] hor2 = new double[21][21];
	double[][] ver2 = new double[21][21];
	double[][] left2 = new double[21][21];
	double[][] right2 = new double[21][21];
	
	int nextX1;
	int nextY1;
	int nextX2;
	int nextY2;
	
	private double minDefence = 1500.0;
	
	GameAlgorithm(){
		amount = 0;
		currentTurn = 0;
		isFirst = true;
		isAttack = true;
		
		stone = new int[21][21];
		for(int i = 0; i < 21; i++) {
			for(int j = 0; j < 21; j++) {
				stone[i][j] = -1;
			}
		}
		for(int i = 0; i < 21; i++) {
			stone[i][0] = 99;
			stone[0][i] = 99;
			stone[i][20] = 99;
			stone[20][i] = 99;
		}
		
		nextStone = new int[4][21][21];
		for(int k = 0; k < 4; k++) {
			for(int i = 0; i < 21; i++) {
				for(int j = 0; j < 21; j++) {
					nextStone[k][i][j] = -1;
				}
			}
		}
		
		for(int k = 0; k < 4; k++) {
			for(int i = 0; i < 21; i++) {
				nextStone[k][i][0] = 99;
				nextStone[k][0][i] = 99;
				nextStone[k][i][20] = 99;
				nextStone[k][20][i] = 99;
			}
		}
	}
	
	public int getNextX1() {
		return nextX1;
	}

	public void setNextX1(int nextX1) {
		this.nextX1 = nextX1;
	}

	public int getNextY1() {
		return nextY1;
	}

	public void setNextY1(int nextY1) {
		this.nextY1 = nextY1;
	}
	
	public int getNextX2() {
		return nextX2;
	}

	public void setNextX2(int nextX2) {
		this.nextX2 = nextX2;
	}

	public int getNextY2() {
		return nextY2;
	}

	public void setNextY2(int nextY2) {
		this.nextY2 = nextY2;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getCurrentTurn() {
		return currentTurn;
	}

	public void setCurrentTurn(int currentTurn) {
		this.currentTurn = currentTurn;
	}

	public int[][] getStone() {
		return stone;
	}
	
	public void setStone(int x, int y, int stone) {
		this.stone[x][y] = stone;
	}
	
	public boolean isStone(int x, int y) {
		if(this.stone[x][y] == -1) return false;
		else return true;
	}
	
	public boolean isFirst() {
		return isFirst;
	}

	public void setFirst(boolean isFirst) {
		this.isFirst = isFirst;
	}

	public void launch() {
		amount++;
		if(amount % 4 == 0 || amount % 4 == 1) {
			currentTurn = 0;
		}
		else if(amount % 4 == 2 || amount % 4 == 3) {
			currentTurn = 1;
		}
	}

	public int win(int x, int y) {
		int highWin = 1;
		
		if(stone[x - 1][y] == currentTurn) {		// 1번
			if(stone[x + 1][y] == currentTurn) {
				highWin = winCount(x - 1, y, 1) + winCount(x + 1, y, 5) + 1;
			}
			else {
				highWin = winCount(x - 1, y, 1) + 1;
			}
		}
		if(stone[x][y - 1] == currentTurn) {		// 2번
			if(stone[x][y + 1] == currentTurn) {
				if(winCount(x, y - 1, 2) + winCount(x, y + 1, 6) + 1 > highWin)
					highWin = winCount(x, y - 1, 2) + winCount(x, y + 1, 6) + 1;
			}
			else {
				if(winCount(x, y - 1, 2) + 1 > highWin)
					highWin = winCount(x, y - 1, 2) + 1;
			}	
		}
		if(stone[x - 1][y - 1] == currentTurn) {	// 3번
			if(stone[x + 1][y + 1] == currentTurn) {
				if(winCount(x - 1, y - 1, 3) + winCount(x + 1, y + 1, 7) + 1 > highWin)
					highWin = winCount(x - 1, y - 1, 3) + winCount(x + 1, y + 1, 7) + 1;
			}
			else {
				if(winCount(x - 1, y - 1, 3) + 1 > highWin)
					highWin = winCount(x - 1, y - 1, 3) + 1;
			}	
		}
		if(stone[x - 1][y + 1] == currentTurn) {	// 4번
			if(stone[x + 1][y - 1] == currentTurn) {
				if(winCount(x - 1, y - 1, 3) + winCount(x + 1, y + 1, 7) + 1 > highWin)
					highWin = winCount(x - 1, y + 1, 4) + winCount(x + 1, y - 1, 8) + 1;
			}
			else {
				if(winCount(x - 1, y - 1, 3) + 1 > highWin)
					highWin = winCount(x - 1, y + 1, 4) + 1;
			}	
		}
		if(stone[x + 1][y] == currentTurn) {		// 5번
			if(stone[x - 1][y] == currentTurn) {
				if(winCount(x + 1, y, 5) + winCount(x - 1, y, 1) + 1 > highWin)
					highWin = winCount(x + 1, y, 5) + winCount(x - 1, y, 1) + 1;
			}
			else {
				if(winCount(x + 1, y, 5) + 1 > highWin)
					highWin = winCount(x + 1, y, 5) + 1;
			}	
		}
		if(stone[x][y + 1] == currentTurn) {		// 6번
			if(stone[x][y - 1] == currentTurn) {
				if(winCount(x, y + 1, 6) + winCount(x, y - 1, 2) + 1 > highWin)
					highWin = winCount(x, y + 1, 6) + winCount(x, y - 1, 2) + 1;
			}
			else {
				if(winCount(x, y + 1, 6) + 1 > highWin)
					highWin = winCount(x, y + 1, 6) + 1;
			}	
		}
		if(stone[x + 1][y + 1] == currentTurn) {	// 7번
			if(stone[x - 1][y - 1] == currentTurn) {
				if(winCount(x + 1, y + 1, 7) + winCount(x - 1, y - 1, 3) + 1 > highWin)
					highWin = winCount(x + 1, y + 1, 7) + winCount(x - 1, y - 1, 3) + 1;
			}
			else {
				if(winCount(x + 1, y + 1, 7) + 1 > highWin)
					highWin = winCount(x + 1, y + 1, 7) + 1;
			}	

		}
		if(stone[x + 1][y - 1] == currentTurn){		// 8번
			if(stone[x - 1][y + 1] == currentTurn) {
				if(winCount(x + 1, y - 1, 8) + winCount(x - 1, y + 1, 4) + 1 > highWin)
					highWin = winCount(x + 1, y - 1, 8) + winCount(x - 1, y + 1, 4) + 1;
			}
			else {
				if(winCount(x + 1, y - 1, 8) + 1 > highWin)
					highWin = winCount(x + 1, y - 1, 8) + 1;
			}	
		}
		return highWin;	
	}
	
	public int winCount(int x, int y, int direction) {
		if(direction == 1) {
			if(stone[x - 1][y] != currentTurn) return 1;
			else return winCount(x - 1, y, 1) + 1;
		}else if(direction == 2) {
			if(stone[x][y - 1] != currentTurn) return 1;
			else return winCount(x, y - 1, 2) + 1;
		}else if(direction == 3) {
			if(stone[x - 1][y - 1] != currentTurn) return 1;
			else return winCount(x - 1, y - 1, 3) + 1;
		}
		else if(direction == 4) {
			if(stone[x - 1][y + 1] != currentTurn) return 1;
			else return winCount(x - 1, y + 1, 4) + 1;
		}
		else if(direction == 5) {
			if(stone[x + 1][y] != currentTurn) return 1;
			else return winCount(x + 1, y, 5) + 1;
		}
		else if(direction == 6) {
			if(stone[x][y + 1] != currentTurn) return 1;
			else return winCount(x, y + 1, 6) + 1;
		}
		else if(direction == 7) {
			if(stone[x + 1][y + 1] != currentTurn) return 1;
			else return winCount(x + 1, y + 1, 7) + 1;
		}
		else if(direction == 8) {
			if(stone[x + 1][y - 1] != currentTurn) return 1;
			else return winCount(x + 1, y - 1, 8) + 1;
		}
		else return 300;
	}
	
	public void resetNextStone() {
		for(int k = 0; k < 4; k++) {
			for(int i = 1; i < 20; i++) {
				for(int j = 1; j < 20; j++) {
					nextStone[k][i][j] = stone[i][j];
				}
			}
		}
	}
	
	public void reset() {
		amount = 0;
		currentTurn = 0;
		
		for(int i = 0; i < 21; i++) {
			for(int j = 0; j < 21; j++) {
				stone[i][j] = -1;
			}
		}
		for(int i = 0; i < 21; i++) {
			stone[i][0] = 99;
			stone[0][i] = 99;
			stone[i][20] = 99;
			stone[20][i] = 99;
		}
	}
	
	public void resetGame() {
		amount = 0;
		currentTurn = 0;
		
		for(int i = 0; i < 21; i++) {
			for(int j = 0; j < 21; j++) {
				if(stone[i][j] == 0 || stone[i][j] == 1)
					stone[i][j] = -1;
			}
		}
	}
	
	public void show() {
		for(int i = 0; i < 21; i++) {
			for(int j = 0; j < 21; j++) {
				System.out.print(stone[j][i] + " ");
			}
			System.out.println();
		}
	}
	
	public void showNextMove() {
		for(int i = 0; i < 21; i++) {
			for(int j = 0; j < 21; j++) {
				System.out.print(all1[j][i] + " ");
			}
			System.out.println();
		}
		
		for(int i = 0; i < 21; i++) {
			for(int j = 0; j < 21; j++) {
				System.out.print(all2[j][i] + " ");
			}
			System.out.println();
		}
	}
	
	public double connect6ShapeScore(int consecutive, int openEnds, int currentTurn) {	// shape에 따른 가중치 부여
		if (openEnds == 0 && consecutive < 6)
			return 0;
		switch (consecutive) {
			case 6:				// 돌 6개
				return 200000;	// 우승!
			case 5:				// 돌 5개
				switch (openEnds) {
					case 0:
						if(amount % 4 == 2 || amount % 4 == 0)
							return 2000;
					case 1:		// 한쪽 끝만 열린 경우
						if(amount % 4 == 2 || amount % 4 == 0) 	
							return 5000;
						else {
							if(isAttack) return 25;
							else return 10000;
						}

					case 2:		// 양쪽 끝이 열린 경우
						if(amount % 4 == 2 || amount % 4 == 0)
							return 100000;
						else {
							if(isAttack) return 40;	
							return 20000;
						}	
				}
			case 4:				// 돌 4개
				switch (openEnds) {
					case 1:		// 한쪽 끝만 열린 경우
						return 50;
					case 2:		// 양쪽 끝이 열린 경우
						if(amount % 4 == 2 || amount % 4 == 0)
							return 1000;
						else 
							return 5000;
				}
			case 3:				// 돌 3개
				switch (openEnds) {
					case 1:		// 한쪽 끝만 열린 경우
						return 7;
					case 2:		// 양쪽 끝이 열린 경우
						return 30;
				}
			case 2:				// 돌 2개
				switch (openEnds) {
					case 1:		// 한쪽 끝만 열린 경우
						return 2;
					case 2:		// 양쪽 끝이 열린 경우
						return 10;
				}
			case 1:				// 돌 1개
				switch (openEnds) {	
					case 1:		// 한쪽 끝만 열린 경우
						return 0.5;
					case 2:		// 양쪽 끝이 열린 경우
						return 1;
				}
			default:
				return 0;
		}
	}

	
	public double analyzeHorizontalSets(int current_turn) {
		double score = 0;
		int countConsecutive = 0;
		int openEnds = 0;

		for (int i = 0; i < 21; i++) {
			for (int j = 0; j < 21; j++) {
				if (stone[j][i] == current_turn)									// 돌이 검정색이 되면 연속점 1증가
					countConsecutive++;
				else if (stone[j][i] == -1 && countConsecutive > 0) {	// 연속점에서 열린 점으로 끝났을 경우
					if(countConsecutive == 2) {
						if(j + 4 < 21) {
							if(stone[j + 1][i] == current_turn && stone[j + 2][i] == current_turn && stone[j + 3][i] == current_turn && stone[j + 4][i] == -1) {
								openEnds++;
								return score += connect6ShapeScore(5,
										openEnds, current_turn); 		// currentTurn is black
							}	
							else if(stone[j + 1][i] == current_turn && stone[j + 2][i] == current_turn && stone[j + 3][i] == current_turn && stone[j + 4][i] != current_turn)
								return score += connect6ShapeScore(5,
										openEnds, current_turn); 		// currentTurn is black
							if(stone[j + 1][i] == current_turn && stone[j + 2][i] == current_turn && stone[j + 3][i] == -1) {
								openEnds++;
								return score += connect6ShapeScore(4,
										openEnds, current_turn); 		// currentTurn is black
							}
							if(stone[j + 1][i] == current_turn && stone[j + 2][i] == current_turn && stone[j + 3][i] != current_turn)
								return score += connect6ShapeScore(4,
										openEnds, current_turn); 		// currentTurn is black
						}
					}
					else if(countConsecutive == 3) {
						if(j + 3 < 21) {
							if(stone[j + 1][i] == current_turn && stone[j + 2][i] == current_turn && stone[j + 3][i] == -1) {
								openEnds++;
								return score += connect6ShapeScore(5,
										openEnds, current_turn); 		// currentTurn is black
							}								
							else if(stone[j + 1][i] == current_turn && stone[j + 2][i] == current_turn && stone[j + 3][i] != current_turn)
								return score += connect6ShapeScore(5,
										openEnds, current_turn); 		// currentTurn is black
							if(stone[j + 1][i] == current_turn && stone[j + 2][i] == -1) {
								openEnds++;
								return score += connect6ShapeScore(4,
										openEnds, current_turn); 		// currentTurn is black
							}
							if(stone[j + 1][i] == current_turn && stone[j + 2][i] != current_turn)
								return score += connect6ShapeScore(4,
										openEnds, current_turn); 		// currentTurn is black						
						}
					}
					else if(countConsecutive == 4) {
						if(j + 2 < 21) {
							if(stone[j + 1][i] == current_turn && stone[j + 2][i] == -1) {
								openEnds++;
								return score += connect6ShapeScore(5,
										openEnds, current_turn); 		// currentTurn is black
							}
							else if(stone[j + 1][i] ==current_turn && stone[j + 2][i] != current_turn)
								return score += connect6ShapeScore(5,
										openEnds, current_turn); 		// currentTurn is black
						}
					}
					
					openEnds++;
					score += connect6ShapeScore(countConsecutive,
						openEnds, current_turn); 		// currentTurn is black
					countConsecutive = 0;
					openEnds = 1;
				}
				else if (stone[j][i] == -1)								// 빈 점이 그냥 등장할 경우
					openEnds = 1;
				else if (countConsecutive > 0) {						// 연속점이 다른 돌에 만나서 끝났을 경우
					score += connect6ShapeScore(countConsecutive,
						openEnds, current_turn); 		// currentTurn is black
					countConsecutive = 0;
					openEnds = 0;
				}
				else openEnds = 0;										// 빈 점이 벽에 만나서 끝났을 경우
			}
			if (countConsecutive > 0)									// 연속점이 벽에 만나서 끝났을 경우
				score += connect6ShapeScore(countConsecutive,
					openEnds, current_turn);			// currentTurn is black
			countConsecutive = 0;
			openEnds = 0;
		}
		return score;
	}
	
	
	public double analyzeVerticalSets(int current_turn) {
		double score = 0;
		int countConsecutive = 0;
		int openEnds = 0;

		for (int i = 0; i < 21; i++) {
			for (int j = 0; j < 21; j++) {
				if (stone[i][j] == current_turn)									// 돌이 검정색이 되면 연속점 1증가
					countConsecutive++;
				else if (stone[i][j] == -1 && countConsecutive > 0) {	// 연속점이 다른 돌에 만나서 끝났을 경우
					if(countConsecutive == 2) {
						if(j + 4 < 21) {
							if(stone[i][j + 1] == current_turn && stone[i][j + 2] == current_turn && stone[i][j + 3] == current_turn && stone[i][j + 4] == -1) {
								openEnds++;
								return score += connect6ShapeScore(5,
										openEnds, current_turn); 		// currentTurn is black
							}
							else if(stone[i][j + 1] == current_turn && stone[i][j + 2] == current_turn && stone[i][j + 3] == current_turn && stone[i][j + 4] != current_turn)
								return score += connect6ShapeScore(5,
										openEnds, current_turn); 		// currentTurn is black
							if(stone[i][j + 1] == 0 && stone[i][j + 2] == current_turn && stone[i][j + 3] == -1) {
								openEnds++;
								return score += connect6ShapeScore(4,
										openEnds, current_turn); 		// currentTurn is black
							}
							else if(stone[i][j + 1] == 0 && stone[i][j + 2] == current_turn && stone[i][j + 3] != current_turn)
								return score += connect6ShapeScore(4,
										openEnds, current_turn); 		// currentTurn is black
						
						}
					}
					else if(countConsecutive == 3) {
						if(j + 3 < 21) {
							if(stone[i][j + 1] == current_turn && stone[i][j + 2] == current_turn && stone[i][j + 3] == -1) {
								openEnds++;
								return score += connect6ShapeScore(5,
										openEnds, current_turn); 		// currentTurn is black
							}
							else if(stone[i][j + 1] == current_turn && stone[i][j + 2] == current_turn && stone[i][j + 3] != current_turn)
								return score += connect6ShapeScore(5,
										openEnds, current_turn); 		// currentTurn is black
							
							if(stone[i][j + 1] == current_turn && stone[i][j + 2] == -1) {
								openEnds++;
								return score += connect6ShapeScore(4,
										openEnds, current_turn); 		// currentTurn is black
							}
							else if(stone[i][j + 1] == current_turn && stone[i][j + 2] != current_turn)
								return score += connect6ShapeScore(4,
										openEnds, current_turn); 		// currentTurn is black
						}
					}
					else if(countConsecutive == 4) {
						if(i + 2 < 21) {
							if(stone[i][j + 1] == current_turn && stone[i][j + 2] == -1) {
								openEnds++;
								return score += connect6ShapeScore(5,
										openEnds, current_turn); 		// currentTurn is black
							}
							else if(stone[i][j + 1] == current_turn && stone[i][j + 2] != current_turn)
								return score += connect6ShapeScore(5,
										openEnds, current_turn); 		// currentTurn is black
						}
					}
					
					openEnds++;
					score += connect6ShapeScore(countConsecutive,
						openEnds, current_turn); 		// currentTurn is black
					countConsecutive = 0;
					openEnds = 1;
				}
				else if (stone[i][j] == -1)								// 빈 점이 그냥 등장할 경우
					openEnds = 1;
				else if (countConsecutive > 0) {						// 연속점이 다른 돌에 만나서 끝났을 경우
					score += connect6ShapeScore(countConsecutive,
						openEnds, current_turn); 		// currentTurn is black
					countConsecutive = 0;
					openEnds = 0;
				}
				else openEnds = 0;										// 빈 점이 벽에 만나서 끝났을 경우
			}
			if (countConsecutive > 0)									// 연속점이 벽에 만나서 끝났을 경우
				score += connect6ShapeScore(countConsecutive,
					openEnds, current_turn);			// currentTurn is black
			countConsecutive = 0;
			openEnds = 0;
		}
		return score;
	}
	
	
	public double analyzeLeftDiagonalSets(int current_turn) {
		double score = 0;
		int countConsecutive = 0;
		int openEnds = 0;
		int n = 21;	
		
		for(int i = 0; i <= 2 * n - 2; i++) {
			int lb, ub;
			if(21 <= i) lb = - (2 * n - 2 - i);
			else lb = - i;
			ub = -lb;
			
			for(int diff = lb; diff <= ub; diff += 2) {
				int x = (i + diff) >> 1;
				int y = i - x;
				
				if (stone[x][y] == current_turn)									// 돌이 검정색이 되면 연속점 1증가
					countConsecutive++;
				else if (stone[x][y] == -1 && countConsecutive > 0) {	// 연속점이 다른 돌에 만나서 끝났을 경우
					
					if(countConsecutive == 2) {
						if(20 >= i && x + 4 < i || 20 < i && y + 16 > i) {
							if(stone[x + 1][y - 1] == current_turn && stone[x + 2][y - 2] == current_turn && stone[x + 3][y - 3] == current_turn && stone[x + 4][y - 4] == -1) {
								openEnds++;
								return score += connect6ShapeScore(5,
										openEnds, current_turn); 		// currentTurn is black
							}
							else if(stone[x + 1][y - 1] == current_turn && stone[x + 2][y - 2] == current_turn && stone[x + 3][y - 3] == current_turn && stone[x + 4][y - 4] != current_turn)
								return score += connect6ShapeScore(5,
										openEnds, current_turn); 		// currentTurn is black
							if(stone[x + 1][y - 1] == current_turn && stone[x + 2][y - 2] == current_turn && stone[x + 3][y - 3] == -1) {
								openEnds++;
								return score += connect6ShapeScore(4,
										openEnds, current_turn); 		// currentTurn is black
							}
							else if(stone[x + 1][y - 1] == current_turn && stone[x + 2][y - 2] == current_turn && stone[x + 3][y - 3] != current_turn)
								return score += connect6ShapeScore(4,
										openEnds, current_turn); 		// currentTurn is black
						}
					}
					else if(countConsecutive == 3) {
						if(20 >= i && x + 3 < i || 20 < i && y + 17 > i) {
							if(stone[x + 1][y - 1] == current_turn && stone[x + 2][y - 2] == current_turn && stone[x + 3][y - 3] == -1) {
								openEnds++;
								return score += connect6ShapeScore(5,
										openEnds, current_turn); 		// currentTurn is black
							}
							else if(stone[x + 1][y - 1] == current_turn && stone[x + 2][y - 2] == current_turn && stone[x + 3][y - 3] != current_turn)
								return score += connect6ShapeScore(5,
										openEnds, current_turn); 		// currentTurn is black
							if(stone[x + 1][y - 1] == current_turn && stone[x + 2][y - 2] == -1) {
								openEnds++;
								return score += connect6ShapeScore(4,
										openEnds, current_turn); 		// currentTurn is black
							}
							else if(stone[x + 1][y - 1] == current_turn && stone[x + 2][y - 2] != current_turn)
								return score += connect6ShapeScore(4,
										openEnds, current_turn); 		// currentTurn is black
						}
					}
					else if(countConsecutive == 4) {
						if(20 >= i && x + 2 < i || 20 < i && y + 18 > i) {
							if(stone[x + 1][y - 1] == current_turn && stone[x + 2][y - 2] == -1) {
								openEnds++;
								return score += connect6ShapeScore(5,
										openEnds, current_turn); 		// currentTurn is black
							}
							else if(stone[x + 1][y - 1] == current_turn && stone[x + 2][y - 2] != current_turn)
								return score += connect6ShapeScore(5,
										openEnds, current_turn); 		// currentTurn is black
						}
					}
					
					openEnds++;
					score += connect6ShapeScore(countConsecutive,
						openEnds, current_turn); 		// currentTurn is black
					countConsecutive = 0;
					openEnds = 1;
				}
				else if (stone[x][y] == -1)								// 빈 점이 그냥 등장할 경우
					openEnds = 1;
				else if (countConsecutive > 0) {						// 연속점이 다른 돌에 만나서 끝났을 경우
					score += connect6ShapeScore(countConsecutive,
						openEnds, current_turn); 		// currentTurn is black
					countConsecutive = 0;
					openEnds = 0;
				}
				else openEnds = 0;					
			}
			if (countConsecutive > 0)									// 연속점이 벽에 만나서 끝났을 경우
				score += connect6ShapeScore(countConsecutive,
					openEnds, current_turn);			// currentTurn is black
			countConsecutive = 0;
			openEnds = 0;
		}
		return score;
	}
	
	
	public double analyzeRightDiagonalSets(int current_turn) {
		double score = 0;
		int countConsecutive = 0;
		int openEnds = 0;
		int n = 21;
		
		for(int i = 0; i <= 2 * n - 2; i++) {
			int lb, ub;
			if(21 <= i) lb = - (2 * n - 2 - i);
			else lb = - i;
			ub = -lb;
			
			for(int diff = lb; diff <= ub; diff += 2) {
				int x = (i + diff) >> 1;
				int y = 20 - i + x;
				
				if (stone[x][y] == current_turn)									// 돌이 검정색이 되면 연속점 1증가
					countConsecutive++;
				else if (stone[x][y] == -1 && countConsecutive > 0) {	// 연속점이 다른 돌에 만나서 끝났을 경우
					
					if(countConsecutive == 2) {
						if(20 >= i && x + 4 < i || 20 < i && y + 16 > i) {
							if(stone[x + 1][y + 1] == current_turn && stone[x + 2][y + 2] == current_turn && stone[x + 3][y + 3] == current_turn && stone[x + 4][y + 4] == -1) {
								openEnds++;
								return score += connect6ShapeScore(5,
										openEnds, current_turn); 		// currentTurn is black
							}
							else if(stone[x + 1][y + 1] == current_turn && stone[x + 2][y + 2] == current_turn && stone[x + 3][y + 3] == current_turn && stone[x + 4][y + 4] != current_turn)
								return score += connect6ShapeScore(5,
										openEnds, current_turn); 		// currentTurn is black
							if(stone[x + 1][y + 1] == current_turn && stone[x + 2][y + 2] == current_turn && stone[x + 3][y + 3] == -1) {
								openEnds++;
								return score += connect6ShapeScore(4,
										openEnds, current_turn); 		// currentTurn is black
							}
							else if(stone[x + 1][y + 1] == current_turn && stone[x + 2][y + 2] == current_turn && stone[x + 3][y + 3] != current_turn)
								return score += connect6ShapeScore(4,
										openEnds, current_turn); 		// currentTurn is black
						}
					}
					else if(countConsecutive == 3) {
						if(20 >= i && x + 3 < i || 20 < i && y + 17 > i) {
							if(stone[x + 1][y - 1] == current_turn && stone[x + 2][y - 2] == current_turn && stone[x + 3][y - 3] == -1) {
								openEnds++;
								return score += connect6ShapeScore(5,
										openEnds, current_turn); 		// currentTurn is black
							}
							else if(stone[x + 1][y - 1] == current_turn && stone[x + 2][y - 2] == current_turn && stone[x + 3][y - 3] != current_turn)
								return score += connect6ShapeScore(5,
										openEnds, current_turn); 		// currentTurn is black
							if(stone[x + 1][y - 1] == current_turn && stone[x + 2][y - 2] == -1) {
								openEnds++;
								return score += connect6ShapeScore(4,
										openEnds, current_turn); 		// currentTurn is black
							}
							else if(stone[x + 1][y - 1] == current_turn && stone[x + 2][y - 2] != current_turn)
								return score += connect6ShapeScore(4,
										openEnds, current_turn); 		// currentTurn is black
						}
					}
					else if(countConsecutive == 4) {
						if(20 >= i && x + 2 < i || 20 < i && y + 18 > i) {
							if(stone[x + 1][y - 1] == current_turn && stone[x + 2][y - 2] == -1) {
								openEnds++;
								return score += connect6ShapeScore(5,
										openEnds, current_turn); 		// currentTurn is black
							}
							else if(stone[x + 1][y - 1] == current_turn && stone[x + 2][y - 2] != current_turn)
								return score += connect6ShapeScore(5,
										openEnds, current_turn); 		// currentTurn is black
						}
					}
					
					openEnds++;
					score += connect6ShapeScore(countConsecutive,
						openEnds, current_turn); 		// currentTurn is black
					countConsecutive = 0;
					openEnds = 1;
				}
				else if (stone[x][y] == -1)								// 빈 점이 그냥 등장할 경우
					openEnds = 1;
				else if (countConsecutive > 0) {						// 연속점이 다른 돌에 만나서 끝났을 경우
					score += connect6ShapeScore(countConsecutive,
						openEnds, current_turn); 		// currentTurn is black
					countConsecutive = 0;
					openEnds = 0;
				}
				else openEnds = 0;					
			}
			if (countConsecutive > 0)									// 연속점이 벽에 만나서 끝났을 경우
				score += connect6ShapeScore(countConsecutive,
					openEnds, current_turn);			// currentTurn is black
			countConsecutive = 0;
			openEnds = 0;
		}
		return score;
	}

	public void findOptimal() {
		amount++;
		System.out.println("Amount : " +amount);
		
		resetNextStone();

		if(findConnect6Move()) {
			System.out.println("나는 6개 무친 돌");
			return;
		}
		else if(findDefenceMove()) {
			System.out.println("나는 방어 무친 돌");
			return;
		}
		else if(findAttackMove()) {
			System.out.println("나는 공격 무친 돌");
			return;
		}

	}
	
	public boolean findConnect6Move() {
		isAttack = true;
		if(isFirst) {
			for(int i = 1; i < 20; i++) {
				for(int j = 1; j < 20; j++) {
					if(stone[i][j] != -1) {			// 돌이면
						all2[i][j] = -999;
					}
					else if(stone[i][j] == -1) {	// 빈칸이면
						stone[i][j] = 1;
						
						hor2[i][j] = analyzeHorizontalSets(1);
						ver2[i][j] = analyzeVerticalSets(1);
						left2[i][j] = analyzeLeftDiagonalSets(1);
						right2[i][j] = analyzeRightDiagonalSets(1);
						all2[i][j] = hor2[i][j] + ver2[i][j] + left2[i][j] + right2[i][j];
						
						stone[i][j] = -1;
					}
					
				}
			}
			
			double max = 0.0;
			int maxX = 0;
			int maxY = 0;
			
			for(int i = 1; i < 20; i++) {
				for(int j = 1; j < 20; j++) {
					if(all2[i][j] > 50000 && all2[i][j] > max) {
						max = all2[i][j];
						maxX = i;
						maxY = j;
					}
				} 
			}
			
			System.out.println(maxX + " asdf " + maxY);
			if(maxX != 0 && maxY != 0) {
				nextX1 = maxX - 1;
				nextY1 = maxY - 1;
				System.out.println(nextX1 + " / " + nextY1);
				stone[maxX][maxY] = 1;
				return true;
			}
			
		}
		else {
			for(int i = 1; i < 20; i++) {
				for(int j = 1; j < 20; j++) {
					if(stone[i][j] != -1) {
						all1[i][j] = -999;
					}
					else if(stone[i][j] == -1) {
						stone[i][j] = 0;
						
						hor1[i][j] = analyzeHorizontalSets(0);
						ver1[i][j] = analyzeVerticalSets(0);
						left1[i][j] = analyzeLeftDiagonalSets(0);
						right1[i][j] = analyzeRightDiagonalSets(0);
						all1[i][j] = hor1[i][j] + ver1[i][j] + left1[i][j] + right1[i][j];
						
						stone[i][j] = -1;
					}
					
				}
			}
			
			double max = 0.0;
			int maxX = 0;
			int maxY = 0;
			
			for(int i = 1; i < 20; i++) {
				for(int j = 1; j < 20; j++) {
					if(all1[i][j] > 50000 && all1[i][j] > max) {
						max = all1[i][j];
						maxX = i;
						maxY = j;
					}
				} 
			}
			
			if(maxX != 0 && maxY != 0) {
				nextX1 = maxX - 1;
				nextY1 = maxY - 1;
				System.out.println(nextX1 + " / " + nextY1);
				stone[maxX][maxY] = 0;
				return true;
			}
		}
		return false;
	}
	
	public boolean findDefenceMove() {
		isAttack = false;
		if(isFirst) {
			for(int i = 1; i < 20; i++) {
				for(int j = 1; j < 20; j++) {
					if(stone[i][j] != -1) {
						all1[i][j] = -999;
					}
					else if(stone[i][j] == -1) {
						stone[i][j] = 0;
						
						hor1[i][j] = analyzeHorizontalSets(0);
						ver1[i][j] = analyzeVerticalSets(0);
						left1[i][j] = analyzeLeftDiagonalSets(0);
						right1[i][j] = analyzeRightDiagonalSets(0);
						all1[i][j] = hor1[i][j] + ver1[i][j] + left1[i][j] + right1[i][j];
						
						stone[i][j] = -1;
					}
				}
			}
			
			double max = all1[0][0];
			int maxX = 0;
			int maxY = 0;
			
			for(int i = 1; i < 20; i++) {
				for(int j = 1; j < 20; j++) {
					if(all1[i][j] > max) {
						max = all1[i][j];
						maxX = i;
						maxY = j;
					}
				}
			}
			
			System.out.println(maxX + " % " + maxY);
			if(amount <= 7) {
				nextX1 = maxX - 1;
				nextY1 = maxY - 1;
				stone[maxX][maxY] = 1;
				return true;
			}
			if(max > minDefence) {
				nextX1 = maxX - 1;
				nextY1 = maxY - 1;
				stone[maxX][maxY] = 1;
				return true;
			}
			else {
				return false;
			}
			
		}
		else {
			for(int i = 1; i < 20; i++) {
				for(int j = 1; j < 20; j++) {
					if(stone[i][j] != -1) {
						all2[i][j] = -999;
					}
					else if(stone[i][j] == -1) {
						stone[i][j] = 1;
						
						hor2[i][j] = analyzeHorizontalSets(1);
						ver2[i][j] = analyzeVerticalSets(1);
						left2[i][j] = analyzeLeftDiagonalSets(1);
						right2[i][j] = analyzeRightDiagonalSets(1);
						all2[i][j] = hor2[i][j] + ver2[i][j] + left2[i][j] + right2[i][j];
						
						stone[i][j] = -1;
					}
				}
			}
			
			double max = all2[0][0];
			int maxX = 0;
			int maxY = 0;
			
			for(int i = 1; i < 20; i++) {
				for(int j = 1; j < 20; j++) {
					if(all2[i][j] > max) {
						max = all2[i][j];
						maxX = i;
						maxY = j;
					}
				}
			}
			
			System.out.println(maxX + " % " + maxY);
			if(amount <= 5) {
				nextX1 = maxX - 1;
				nextY1 = maxY - 1;
				stone[maxX][maxY] = 0;
				return true;
			}
			if(max > minDefence) {
				nextX1 = maxX - 1;
				nextY1 = maxY - 1;
				stone[maxX][maxY] = 0;
				return true;
			}
			else {
				return false;
			}
		}
	}
	
	public boolean findAttackMove() {	
		isAttack = true;
		if(isFirst) {
			double max = 0;
			int maxX = 0;
			int maxY = 0;
			
			for(int i = 1; i < 20; i++) {
				for(int j = 1; j < 20; j++) {
					if(all2[i][j] > max) {
						max = all2[i][j];
						maxX = i;
						maxY = j;
					}
				}
			}
			
			System.out.println(maxX + " / " + maxY);
			nextX1 = maxX - 1;
			nextY1 = maxY - 1;
			stone[maxX][maxY] = 1;
			return true;

		}
		else {
			
			double max = 0;
			int maxX = 0;
			int maxY = 0;
			
			for(int i = 1; i < 20; i++) {
				for(int j = 1; j < 20; j++) {
					if(all1[i][j] > max) {
						max = all1[i][j];
						maxX = i;
						maxY = j;
					}
				}
			}
			
			System.out.println(maxX + " / " + maxY);
			nextX1 = maxX - 1;
			nextY1 = maxY - 1;
			stone[maxX][maxY] = 0;
			return true;

		}
		
	}
	
//	public void minimax() {
//		amount++;
//		System.out.println("Amount : " +amount);
//		
//		resetNextStone();
//
//		if(findConnect6Move_min1()) {
//			System.out.println("나는 6개 무친 돌");
//			amount++;
//			System.out.println("Amount : " +amount);
//			findConnect6Move_min2();
//			System.out.println("나는 6개 무친 돌");
//			return;
//		}
//		else if(findDefenceMove_min()) {
//			System.out.println("나는 방어 무친 돌");
//		}
//		else if(findAttackMove_min()) {
//			System.out.println("나는 공격 무친 돌");
//		}
//		
//		
//	}
//	
//	public boolean findConnect6Move_min1() {
//		if(isFirst) {
//			for(int i = 1; i < 20; i++) {
//				for(int j = 1; j < 20; j++) {
//					if(stone[i][j] != -1) {			// 돌이면
//						all2[i][j] = -999;
//					}
//					else if(stone[i][j] == -1) {	// 빈칸이면
//						stone[i][j] = 1;
//						
//						hor2[i][j] = analyzeHorizontalSets(1);
//						ver2[i][j] = analyzeVerticalSets(1);
//						left2[i][j] = analyzeLeftDiagonalSets(1);
//						right2[i][j] = analyzeRightDiagonalSets(1);
//						all2[i][j] = hor2[i][j] + ver2[i][j] + left2[i][j] + right2[i][j];
//						
//						stone[i][j] = -1;
//					}
//					
//				}
//			}
//			
//			double max = 0.0;
//			int maxX = 0;
//			int maxY = 0;
//			
//			for(int i = 1; i < 20; i++) {
//				for(int j = 1; j < 20; j++) {
//					if(all2[i][j] > 50000 && all2[i][j] > max) {
//						max = all2[i][j];
//						maxX = i;
//						maxY = j;
//					}
//				} 
//			}
//			
//			System.out.println(maxX + " asdf " + maxY);
//			if(maxX != 0 && maxY != 0) {
//				nextX1 = maxX - 1;
//				nextY1 = maxY - 1;
//				System.out.println(nextX1 + " / " + nextY1);
//				stone[maxX][maxY] = 1;
//				return true;
//			}
//			
//		}
//		else {
//			for(int i = 1; i < 20; i++) {
//				for(int j = 1; j < 20; j++) {
//					if(stone[i][j] != -1) {
//						all1[i][j] = -999;
//					}
//					else if(stone[i][j] == -1) {
//						stone[i][j] = 0;
//						
//						hor1[i][j] = analyzeHorizontalSets(0);
//						ver1[i][j] = analyzeVerticalSets(0);
//						left1[i][j] = analyzeLeftDiagonalSets(0);
//						right1[i][j] = analyzeRightDiagonalSets(0);
//						all1[i][j] = hor1[i][j] + ver1[i][j] + left1[i][j] + right1[i][j];
//						
//						stone[i][j] = -1;
//					}
//					
//				}
//			}
//			
//			double max = 0.0;
//			int maxX = 0;
//			int maxY = 0;
//			
//			for(int i = 1; i < 20; i++) {
//				for(int j = 1; j < 20; j++) {
//					if(all1[i][j] > 50000 && all1[i][j] > max) {
//						max = all1[i][j];
//						maxX = i;
//						maxY = j;
//					}
//				} 
//			}
//			
//			if(maxX != 0 && maxY != 0) {
//				nextX1 = maxX - 1;
//				nextY1 = maxY - 1;
//				System.out.println(nextX1 + " / " + nextY1);
//				stone[maxX][maxY] = 0;
//				return true;
//			}
//		}
//		return false;
//	}
//	
//	public boolean findConnect6Move_min2() {
//		if(isFirst) {
//			for(int i = 1; i < 20; i++) {
//				for(int j = 1; j < 20; j++) {
//					if(stone[i][j] != -1) {			// 돌이면
//						all2[i][j] = -999;
//					}
//					else if(stone[i][j] == -1) {	// 빈칸이면
//						stone[i][j] = 1;
//						
//						hor2[i][j] = analyzeHorizontalSets(1);
//						ver2[i][j] = analyzeVerticalSets(1);
//						left2[i][j] = analyzeLeftDiagonalSets(1);
//						right2[i][j] = analyzeRightDiagonalSets(1);
//						all2[i][j] = hor2[i][j] + ver2[i][j] + left2[i][j] + right2[i][j];
//						
//						stone[i][j] = -1;
//					}
//					
//				}
//			}
//			
//			double max = 0.0;
//			int maxX = 0;
//			int maxY = 0;
//			
//			for(int i = 1; i < 20; i++) {
//				for(int j = 1; j < 20; j++) {
//					if(all2[i][j] > 50000 && all2[i][j] > max) {
//						max = all2[i][j];
//						maxX = i;
//						maxY = j;
//					}
//				} 
//			}
//			
//			System.out.println(maxX + " asdf " + maxY);
//			if(maxX != 0 && maxY != 0) {
//				nextX2 = maxX - 1;
//				nextY2 = maxY - 1;
//				System.out.println(nextX2 + " / " + nextY2);
//				stone[maxX][maxY] = 1;
//				return true;
//			}
//			
//		}
//		else {
//			for(int i = 1; i < 20; i++) {
//				for(int j = 1; j < 20; j++) {
//					if(stone[i][j] != -1) {
//						all1[i][j] = -999;
//					}
//					else if(stone[i][j] == -1) {
//						stone[i][j] = 0;
//						
//						hor1[i][j] = analyzeHorizontalSets(0);
//						ver1[i][j] = analyzeVerticalSets(0);
//						left1[i][j] = analyzeLeftDiagonalSets(0);
//						right1[i][j] = analyzeRightDiagonalSets(0);
//						all1[i][j] = hor1[i][j] + ver1[i][j] + left1[i][j] + right1[i][j];
//						
//						stone[i][j] = -1;
//					}
//					
//				}
//			}
//			
//			double max = 0.0;
//			int maxX = 0;
//			int maxY = 0;
//			
//			for(int i = 1; i < 20; i++) {
//				for(int j = 1; j < 20; j++) {
//					if(all1[i][j] > 50000 && all1[i][j] > max) {
//						max = all1[i][j];
//						maxX = i;
//						maxY = j;
//					}
//				} 
//			}
//			
//			if(maxX != 0 && maxY != 0) {
//				nextX2 = maxX - 1;
//				nextY2 = maxY - 1;
//				System.out.println(nextX2 + " / " + nextY2);
//				stone[maxX][maxY] = 0;
//				return true;
//			}
//		}
//		return false;
//	}
//	
//	public boolean findDefenceMove_min() {
//		if(isFirst) {
//			for(int i = 1; i < 20; i++) {
//				for(int j = 1; j < 20; j++) {
//					if(nextStone[0][i][j] != -1) {
//						all1[i][j] = -999;
//					}
//					else if(nextStone[0][i][j] == -1) {
//						nextStone[0][i][j] = 0;
//						
//						hor1[i][j] = analyzeHorizontalSets(0);
//						ver1[i][j] = analyzeVerticalSets(0);
//						left1[i][j] = analyzeLeftDiagonalSets(0);
//						right1[i][j] = analyzeRightDiagonalSets(0);
//						all1[i][j] = hor1[i][j] + ver1[i][j] + left1[i][j] + right1[i][j];
//						
//						nextStone[0][i][j] = -1;
//					}
//				}
//			}
//			
//			double max = 0.0;
//			double nextMax = 0.0;
//			int maxX = 0;
//			int maxY = 0;
//			int nextmaxX = 0;
//			int nextmaxY = 0;
//			
//			for(int i = 1; i < 20; i++) {
//				for(int j = 1; j < 20; j++) {
//					if(all1[i][j] > max) {
//						nextMax = max;
//						nextmaxX = maxX;
//						nextmaxY = maxY;
//						max = all1[i][j];
//						maxX = i;
//						maxY = j;
//					}
//				}
//			}
//			
//			System.out.println(maxX + " % " + maxY);
//			if(amount <= 5) {
//				nextX1 = maxX - 1;
//				nextY1 = maxY - 1;
//				nextX2 = nextmaxX - 1;
//				nextY2 = nextmaxY - 1;
//				nextStone[0][maxX][maxY] = 1;
//				nextStone[1][maxX][maxY] = 1;
//				nextStone[2][nextmaxX][nextmaxY] = 1;
//				nextStone[3][nextmaxX][nextmaxY] = 1;
//				return true;
//			}
//			if(max > minDefence) {
//				nextX1 = maxX - 1;
//				nextY1 = maxY - 1;
//				nextX2 = nextmaxX - 1;
//				nextY2 = nextmaxY - 1;
//				nextStone[0][maxX][maxY] = 1;
//				nextStone[1][maxX][maxY] = 1;
//				nextStone[2][nextmaxX][nextmaxY] = 1;
//				nextStone[3][nextmaxX][nextmaxY] = 1;
//				return true;
//			}
//			else {
//				return false;
//			}
//			
//		}
//		else {
//			for(int i = 1; i < 20; i++) {
//				for(int j = 1; j < 20; j++) {
//					if(nextStone[0][i][j] != -1) {
//						all2[i][j] = -999;
//					}
//					else if(nextStone[0][i][j] == -1) {
//						nextStone[0][i][j] = 1;
//						
//						hor2[i][j] = analyzeHorizontalSets(1);
//						ver2[i][j] = analyzeVerticalSets(1);
//						left2[i][j] = analyzeLeftDiagonalSets(1);
//						right2[i][j] = analyzeRightDiagonalSets(1);
//						all2[i][j] = hor2[i][j] + ver2[i][j] + left2[i][j] + right2[i][j];
//						
//						nextStone[0][i][j] = -1;
//					}
//				}
//			}
//			
//			double max = 0.0;
//			double nextMax = 0.0;
//			int maxX = 0;
//			int maxY = 0;
//			int nextmaxX = 0;
//			int nextmaxY = 0;
//			
//			for(int i = 1; i < 20; i++) {
//				for(int j = 1; j < 20; j++) {
//					if(all2[i][j] > max) {
//						nextMax = max;
//						nextmaxX = maxX;
//						nextmaxY = maxY;
//						max = all1[i][j];
//						maxX = i;
//						maxY = j;
//					}
//				}
//			}
//			
//			System.out.println(maxX + " % " + maxY);
//			if(amount <= 5) {
//				nextX1 = maxX - 1;
//				nextY1 = maxY - 1;
//				nextX2 = nextmaxX - 1;
//				nextY2 = nextmaxY - 1;
//				nextStone[0][maxX][maxY] = 0;
//				nextStone[1][maxX][maxY] = 0;
//				nextStone[2][nextmaxX][nextmaxY] = 0;
//				nextStone[3][nextmaxX][nextmaxY] = 0;
//				return true;
//			}
//			if(max > minDefence) {
//				nextX1 = maxX - 1;
//				nextY1 = maxY - 1;
//				nextX2 = nextmaxX - 1;
//				nextY2 = nextmaxY - 1;
//				nextStone[0][maxX][maxY] = 0;
//				nextStone[1][maxX][maxY] = 0;
//				nextStone[2][nextmaxX][nextmaxY] = 0;
//				nextStone[3][nextmaxX][nextmaxY] = 0;
//				return true;
//			}
//			else {
//				return false;
//			}
//		}
//	}
//	
//	public boolean findAttackMove_min() {	
//		if(isFirst) {
//			double max = 0;
//			int maxX = 0;
//			int maxY = 0;
//			
//			for(int i = 1; i < 20; i++) {
//				for(int j = 1; j < 20; j++) {
//					if(all2[i][j] > max) {
//						max = all2[i][j];
//						maxX = i;
//						maxY = j;
//					}
//				}
//			}
//			
//			System.out.println(maxX + " / " + maxY);
//			nextX1 = maxX - 1;
//			nextY1 = maxY - 1;
//			nextStone[0][maxX][maxY] = 1;
//			return true;
//
//		}
//		else {
//			
//			double max = 0;
//			int maxX = 0;
//			int maxY = 0;
//			
//			for(int i = 1; i < 20; i++) {
//				for(int j = 1; j < 20; j++) {
//					if(all1[i][j] > max) {
//						max = all1[i][j];
//						maxX = i;
//						maxY = j;
//					}
//				}
//			}
//			
//			System.out.println(maxX + " / " + maxY);
//			nextX1 = maxX - 1;
//			nextY1 = maxY - 1;
//			nextStone[0][maxX][maxY] = 0;
//			return true;
//
//		}
//		
//	}
//	
//	public void findScore() {
//		amount++;
//		setStone(10, 10, 0);
//		
//		while(true) {
//			findOptimal();
//			if(win(nextX1 + 1, nextX1 + 1) == 6) {
//				if(getCurrentTurn() == 0) {
//					System.out.println("P1 승리");
//				}
//				else if(getCurrentTurn() == 1) {
//					System.out.println("P2 승리");
//				}
//				break;
//			}
//		}
//	}
//	
}
