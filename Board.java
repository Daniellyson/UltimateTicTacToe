class Board {
  private Miniboard[] uboard;
  private byte[] status;
  private int[] numresults; // number of miniboards decided for each X and O

  public Board() {
    uboard = new Miniboard[3][3];
    for (int i=0 ; i<3 ; i++ ) {
      for ( int j=0 ; j<3 ; j++ ) {
        uboard[i][j] = new Miniboard();
      }
    }
    status = new byte[3][3];
    numresults = new int[2];
  }

  public void set() {
    // some code
    if ( numresults[0]>=3 || numresults[1]>=3 ) {
      return (Game.checkifwon(status));
    }
    else {
      return 0;
    }
  }

  public void display() {

  }
}
