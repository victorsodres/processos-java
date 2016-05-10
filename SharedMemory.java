public final class SharedMemory{

  private Message msg = null;
  private static SharedMemory INSTANCE;

  private SharedMemory(){}

  public static synchronized SharedMemory getInstance(){
    if (INSTANCE == null)
      INSTANCE = new SharedMemory();

    return INSTANCE;
  }

  public Message getMessage(){
    // if(msg.getDestinatario() == id){
      // msg = null;
      return msg;
    // }

    // return null;
  }

  public void addMessage(Message msg){
    //if(this.msg == null)
      this.msg = msg;
  }

}
