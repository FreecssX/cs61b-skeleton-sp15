public class Piece {
	
	private boolean isFire;
	private Board b;
	private int x;
	private int y;
	private int preX; // x position before the latest move
	private int preY; // y position before the latest move
	private String type;
	private boolean crowned;
	
	public Piece(boolean isFire, Board b, int x, int y, String type) {
		this.isFire = isFire;
		this.b = b;
		this.x = x;
		this.y = y;
		this.preX = x;
		this.preY = y;
		this.type = type;
		this.crowned = false;
	}

	public boolean isFire() {
		return isFire;
	}

	public int side() {
		if (isFire()) return 0;
		else return 1;
	}

	public boolean isKing() {
		return crowned;
	}

	public boolean isBomb() {
		return type.equals("bomb");
	}

	public boolean isShield() {
		return type.equals("shield");
	}

	public void move(int x, int y) {
		b.remove(this.x, this.y);
		this.x = x;
		this.y = y;
		boolean captured = hasCaptured();
		if (captured) {
			int capturedX = (this.preX + x) / 2;
			int capturedY = (this.preY + y) / 2;
			b.remove(capturedX, capturedY); 
			if (this.isBomb()) {
 				for (int i = -1; i < 2; i += 1) {
 					for (int j = -1; j < 2; j += 1) {
 						Piece p = b.pieceAt(x + i, y + j);
 						if (p != null && !p.isShield()) {
 							b.remove(x + i, y + j);
 						}
 					}
 				}	
 			}
 		}
 		b.place(this, x, y);
 		if (this.isBomb() && captured) {
 			b.remove(x, y);
 		}
 		if (this.side() == 0 && y == 7) {
 			this.crowned = true;
 		} else if (this.side() == 1 && y == 0) {
 			this.crowned = true;
 		}
 		doneCapturing();
	}

	public boolean hasCaptured() {
		return (Math.abs(x - preX) == 2);
	}

	public void doneCapturing() {
		preX = x;
		preY = y;
	}

}