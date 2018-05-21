class Game {
  private String[] pname;
  private String pOname;
  private int result;

  public Game () {
    pname = new String[2]; //1-1 = X, 2-1 = O
    // input name of both players and initialize instance variables
  }

  public void start() {
    Board b = new Board();
    int turn=1; // 1= X, 2= O
    int eligible=0; // 0 = All, 1-9=only one
    while (result==0) {
      b.display();
      System.out.println(pname[turn-1] + " its your move");
      if (eligible==0) {
        System.out.println("Choose the zone you wanna play");
        //input and set eligible
      }
      System.out.println("Where you wanna play in zone " + eligible);
      //input
      result = b.set();
      // more code
    }
  }

  public static int checkifwon(byte[] b) {
    // returns 0(nobody), 1(X) or 2(O), depending on who won
  }
}
