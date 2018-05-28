class TestClass {
  private byte[][] status_ = new byte[Uttt.board_size_][Uttt.board_size_];
  boolean changed;
  int count;
  int c;
  public TestClass(){
    for (int i=0 ; i<Uttt.board_size_ ; i++ ) {
      for (int j=0 ; j<Uttt.board_size_ ; j++ ) {
        status_[i][j] = 0;
      }
    }
  }
  public static void main(String[] args) {
    TestClass t= new TestClass();
    t.startTest();
  }
  public void startTest() {
    byte result=Uttt.E;
    do{
      result=Game.checkIfWon(status_);
      if(result!=Uttt.E){
        count++;
        displaylocal();
        System.out.println("win num = "+count);
      }
      changed=false;
      incrementboard();
    }while (changed);
  }
  private void incrementboard() {
    for (int i=0 ; i<Uttt.board_size_ ; i++ ) {
      for (int j=0 ; j<Uttt.board_size_ ; j++ ) {
        System.out.println(c);
        c++;
        if (status_[i][j] == 2) {
          continue;
        }else {
          changed= true;
          status_[i][j]++;
          for (int a=0;a<i ;a++ ) {
            for (int b=0;b<Uttt.board_size_ ; b++ ) {
              status_[a][b]=0;
            }
          }
          for (int a=0; a<j ; a++ ) {
            status_[i][a]=0;
          }
          return;
        }
      }
    }
  }
  private void displaylocal() {
    System.out.println("");
    for (int i=0 ; i<Uttt.board_size_ ; i++ ) {
      for (int j=0 ; j<Uttt.board_size_ ; j++ ) {
        System.out.print( Board.disp[status_[i][j]] );
      }
      System.out.println("");
    }
    System.out.println("");
  }
}
