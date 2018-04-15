public class Board {

    public static final int SIZE = 8;
    // You can call this variable by Board.SIZE.

	private Piece[][] pieces;
    private boolean isFireTurn;

    public Board() {
        pieces = new Piece[SIZE][SIZE];
        isFireTurn = true;
    }

    /** Makes a custom Board. Not a completely safe operation because you could do
    * some bad stuff here, but this is for the purposes of testing out hash
    * codes so let's forgive the author. 
    */
    public Board(Piece[][] pieces) {
        this.pieces = pieces;
        isFireTurn = true;
    }

	@Override
	public boolean equals(Object o) {
        if(o instanceof Board) {
            for(int i = 0; i < SIZE; i += 1) {
                for(int j = 0; j < SIZE; j += 1) {
                    Piece p1 = pieces[i][j];
                    Piece p2 = ((Board) o).pieces[i][j];
                    if(!(p1.equals(p2))) return false;
                }
            }
            return true;
        }
        return false;
	}

    @Override
    public int hashCode() {
        int result = 0;
        for(int i = 0; i < SIZE; i += 1) {
            for(int j = 0; j < SIZE; j += 1) {
                if(pieces[i][j] != null) result += pieces[i][j].hashCode();
            }
        }
        return result;
    }
}