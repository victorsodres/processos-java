import java.util.List;
import java.util.ArrayList;

public final class SharedMemory{

  private Message msg = null;
  private List<Message> listaMensagens = new ArrayList<>(4);
  private static SharedMemory INSTANCE;

  private SharedMemory(){}

  public static synchronized SharedMemory getInstance(){
    if (INSTANCE == null)
      INSTANCE = new SharedMemory();

    return INSTANCE;
  }

  public Message getMessage(){
      return msg;
  }

  public Message getAndRemoveMessage(int pos){
    Message cont = this.listaMensagens.get(pos);
    this.listaMensagens.remove(pos);
    return cont;
  }

  public List<Message> getListaMensagens(){
      return this.listaMensagens;
  }

  public void addMessage(Message msg){
      listaMensagens.add(msg);
  }

}
