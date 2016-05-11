import java.util.Date;
import java.util.UUID;
import java.util.TimerTask;
import java.util.Timer;
import java.util.List;

public class Processo {

  // Diminuir o tempo para demonstração
  private final int TIME = 1000;
  private Estado estado;
  private long id;
  private String conteudo;
  private long proximo;

  // public void run(){
  //   if(!Estado.TERMINATED.equals(estado)){
  //     MandarMensagem();
  //   }
  // }

  public Processo(String conteudo){
    this.id = Math.abs(UUID.randomUUID().hashCode());
    this.estado = Estado.NEW;
    this.conteudo = conteudo;

    new Thread(new Runnable() {
      @Override
      public void run() {
        processar();
      }
    }).start();

    // new Thread(new Runnable() {
    //   @Override
    //   public void run() {
    //
    //   }
    // }).start();

  }

  public void processar() {
    while(this.estado != Estado.TERMINATED || this.estado != Estado.DEAD) {
      switch(this.estado){
        case NEW:
          this.estado = Estado.READY;
          break;
        case READY:
          this.estado = Estado.RUNNING;
          break;
        case RUNNING:
          if (randomNumber(100) < 50 && this.conteudo.isEmpty()) {
            List<Message> list = SharedMemory.getInstance().getListaMensagens();
            if(list.size() > 0){
              int n = randomNumber(list.size()-1);
              this.conteudo = SharedMemory.getInstance().getAndRemoveMessage(n).getConteudo();
            }
          } else {
            mandarMensagem();
          }
          this.estado = randomState();
          break;
        case WAITING:
          this.estado = Estado.READY;
          break;
        default:
          System.out.print("");
      }
      sleep();
    }
  }

  public void imprimirEstado(){
    System.out.println("ID: " + this.id + " | STATE: " + this.estado);
    // System.out.print("\r======= " + this.estado);
  }

  public void sleep(){
    try {
			Thread.sleep(TIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
  }

  public Estado randomState() {
    int n = randomNumber(4);

    if(n == 1 || n == 3 || n == 4)
      return Estado.values()[n];
    else
      return randomState();
  }

  public int randomNumber(int number){
    return Integer.parseInt(String.valueOf(Math.round(Math.random() * number)));
  }

  public void enviarMensagem(int idProcesso){

  }

  public Estado getEstado(){
    return this.estado;
  }

  public void setEstado(Estado estado){
    this.estado = estado;
  }

  public String  getConteudo(){
    return this.conteudo;
  }

  public void setConteudo(String conteudo){
    this.conteudo = conteudo;
  }

  public long getProximo(){
    return this.proximo;
  }

  public void setProximo(long proximo){
    this.proximo = proximo;
  }

  public long getId(){
    return this.id;
  }

  public void setId(long id){
    this.id = id;
  }

  public void mandarMensagem(){
    if(this.conteudo != null && !this.conteudo.isEmpty()){
      SharedMemory.getInstance().addMessage(new Message(this.conteudo));
      this.conteudo = "";
    }
  }

}
