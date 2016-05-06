import java.util.Date;
import java.util.UUID;

public class Processo {

  private Estado estado;
  private long id;
  private String conteudo;
  private long proximo;

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
  }

  public void processar() {
    System.out.println("==== PID: "+ this.id +" STARTED =====");
    imprimirEstado();

    while(this.estado != Estado.TERMINATED) {
      switch(this.estado){
        case NEW:
          this.estado = Estado.READY;
          break;
        case READY:
          imprimirEstado();
          this.estado = Estado.RUNNING;
          break;
        case RUNNING:
          imprimirEstado();
          this.estado = randomState();
          break;
        case WAITING:
          imprimirEstado();
          this.estado = Estado.READY;
          break;
        default:
          System.out.println("UNKNOWN STATE");
      }
      sleep();
    }
    imprimirEstado();
    // System.out.println("<------------- "+ this.id +" ------------>");
  }

  public void imprimirEstado(){
    System.out.println("ID: " + this.id + " | STATE: " + this.estado);
    // System.out.print("\r======= " + this.estado);
  }

  public void sleep(){
    try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
  }

  public Estado randomState() {
    int n = randomNumber();

    if(n == 1 || n == 3 || n == 4)
      return Estado.values()[n];
    else
      return randomState();
  }

  public int randomNumber(){
    return Integer.parseInt(String.valueOf(Math.round(Math.random() * 4)));
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
    System.out.println("PID: " + this.id + " --> Next PID: " + proximo);
    this.proximo = proximo;
  }

  public long getId(){
    return this.id;
  }

  public void setId(long id){
    this.id = id;
  }

}
