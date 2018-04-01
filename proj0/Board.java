public class Board {
	private Piece[][] pieces;
	private boolean shouldBeEmpty;
	private int N; // The size of the board is N X N 
	private int side; // who is taking the turn
	private int move; //num of moves during one's turn 
	private int piecePosX; // x position of selected piece
	private int piecePosY; // y position of selected piece
	private boolean hasCaptured; // true if has captured


	
	public Board(boolean shouldBeEmpty) {
		this.shouldBeEmpty = shouldBeEmpty;
		side = 0;
		N = 8;
		move = 0;
		piecePosX = -1;
		piecePosY = -1;
		hasCaptured = false;
		pieces = new Piece[N][N];
		if (!shouldBeEmpty) {
			for(int i = 0; i < N; i += 1) {
				for (int j = 0; j < N; j += 1) {
					if(j == 0 && (i + j) % 2 == 0) {
						pieces[i][j] = new Piece(true, this, i, j, "pawn");
					} else if (j == 1 && (i + j) % 2 == 0) {
						pieces[i][j] = new Piece(true, this, i, j, "shield");
					} else if (j == 2 && (i + j) % 2 == 0) {
						pieces[i][j] = new Piece(true, this, i, j, "bomb");
					} else if (j == 7 && (i + j) % 2 == 0) {
						pieces[i][j] = new Piece(false, this, i, j, "pawn");
					} else if (j == 6 && (i + j) % 2 == 0) {
						pieces[i][j] = new Piece(false, this, i, j, "shield");
					} else if (j == 5 && (i + j) % 2 == 0) {
						pieces[i][j] = new Piece(false, this, i, j, "bomb");
					}
				}
			}
		}
		drawBoard();
	}
	
	public void drawBoard() {
		for (int i = 0; i < N; i += 1) {
			for (int j = 0; j < N; j += 1) {
				if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                if (i == piecePosX && j == piecePosY) {
                	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                	StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                }
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				Piece p = pieces[i][j];				
				if(p != null) {
					String t = getName(p);
					StdDrawPlus.picture(i + .5, j + .5, "img/" + t + ".png", 1, 1);
				}
			}
		}
	}

	private String getName(Piece p) {
		String t;
		if (p.isFire()) {
			if (p.isKing()) {
				if (p.isBomb()) t = "bomb" + "-fire-crowned";
				else if (p.isShield()) t = "shield" + "-fire-crowned";
				else t = "pawn" + "-fire-crowned";
			} else {
				if (p.isBomb()) t = "bomb" + "-fire";
				else if (p.isShield()) t = "shield" + "-fire";
				else t = "pawn" + "-fire";
			}
		} else {
			if (p.isKing()) {
				if (p.isBomb()) t = "bomb" + "-water-crowned";
				else if (p.isShield()) t = "shield" + "-water-crowned";
				else t = "pawn" + "-water-crowned";
			} else {
				if (p.isBomb()) t = "bomb" + "-water";
				else if (p.isShield()) t = "shield" + "-water";
				else t = "pawn" + "-water";
			}
		}
		return t;
	}
	
	private String findPosition(Piece p) {
		for (int i = 0; i < N; i += 1) {
			for (int j = 0; j < N; j += 1) {
				if (pieces[i][j] == p) {
					return Integer.toString(i) + Integer.toString(j);
				}
			}
		}
		return "null";
	}
	
	public Piece pieceAt(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= N) {
			return null;
		} else {
			return pieces[x][y];
		}
	}
	
	public boolean canSelect(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= N) {
			return false;
		} else {
			Piece p = pieceAt(x, y);
			if (p == null) {
				if (piecePosX == -1) {
					return false;
				} else {
					Piece pPre = pieceAt(piecePosX, piecePosY);
					int diffX = piecePosX - x;
					int diffY = piecePosY - y;
					if (Math.abs(diffX) == 1 && Math.abs(diffY) == 1) {
						if (move == 0) {
							if (pPre.isKing()) {
								return true;
							} else if (pPre.isFire()) {
								if (diffY < 0) {
									return true;
								} else {
									return false;
								}
							} else {
								if (diffY > 0) {
									return true;
								} else {
									return false;
								}
							}
							
						} else {
							return false;
						}
					} else if (Math.abs(diffX) == 2 && Math.abs(diffY) == 2 && move != 1) {
						Piece p1 = pieceAt((piecePosX + x) / 2, (piecePosY + y) / 2);
						if (p1 == null) {
							return false;
						} else if (p1.side() != side) {
							if (pPre.isKing()) {
								return true;
							} else if (pPre.isFire()) {
								if (diffY < 0) {
									return true;
								} else {
									return false;
								}
							} else {
								if (diffY > 0) {
									return true;
								} else {
									return false;
								}
							}
						} else {
							return false;
						}
					} else {
						return false;
					}
				}
			} else {
				if (move == 0) {
					if (p.side() == side) {
						return true;
					} else {
						return false;
					}
				} else if (move >= 2){
					if (piecePosX == x) {
						return true;
					} else {
						return false;
					}
				} else {
					return false;
				}
			}
		}
	}

	private boolean captured(Piece p) {
		String posStr = findPosition(p);
		if (p != null && posStr != "null") {
			int posX = Integer.parseInt(posStr) / 10;
			int posY = Integer.parseInt(posStr) % 10;
			return Math.abs(posX - piecePosX) == 2 && Math.abs(posY - piecePosY) == 2;
		}
		return false;
	}

	public void select(int x, int y) {
		Piece p = pieceAt(x, y);
		if (p != null) {
			piecePosX = x;
			piecePosY = y;
		} else {
			Piece p1 = pieceAt(piecePosX, piecePosY);
			p1.move(x, y);
			if (captured(p1)) {
				move += 2;
				piecePosX = x;
				piecePosY = y;
				if (p1.isBomb()) {
					piecePosX = -1;
					piecePosY = -1;
				}
			} else {
				move += 1;
				piecePosX = -1;
				piecePosY = -1;
			}
		}
	}

	public boolean canEndTurn() {
		return move > 0;
	}

	public void endTurn() {
		side = 1 - side;
		move = 0;
		piecePosX = -1;
		piecePosY = -1;
	}

	public String winner() {
		int fireNum = getNum(0);
		int waterNum = getNum(1);
		if (fireNum == 0 && waterNum == 0) {
			return "No one";
		} else if (fireNum == 0 && waterNum > 0) {
			return "Water";
		} else if (fireNum > 0 && waterNum == 0) {
			return "Fire";
		} else {
			return null;
		}
	}

	private int getNum(int type) {
		int Num = 0;
		for (int i = 0; i < N; i += 1) {
			for (int j = 0; j < N; j += 1) {
				if (pieces[i][j] != null && pieces[i][j].side() == type) {
					Num += 1;
				}
			}
		}
		return Num;
	}



	public void place(Piece p, int x, int y) {
		pieces[x][y] = p;
	}

	public Piece remove(int x, int y) {
		Piece p = pieces[x][y];
		pieces[x][y] = null;
		return p;
	}

	public static void main(String[] args) {
		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
		Board b = new Board(false);
		while (true) {
			int xPos = -1;
			int yPos = -1;
			if (StdDrawPlus.mousePressed()) {
				xPos = (int) (StdDrawPlus.mouseX());
				yPos = (int) (StdDrawPlus.mouseY());
				if (b.canSelect(xPos, yPos)) {
					b.select(xPos, yPos);
				}
			}
			b.drawBoard();
			StdDrawPlus.show(100);
			if (StdDrawPlus.isSpacePressed() && b.canEndTurn()) {
				b.endTurn();
			}
			if (StdDrawPlus.isKeyPressed('E')) {
				System.out.println(b.winner());
			}

		}
	}
}