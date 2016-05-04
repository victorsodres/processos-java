import java.util.Date;

public class Processo {

  private final int NEW = 0;
  private final int READY = 1;
  private final int RUNNING = 2;
  private final int WAITING = 3;
  private final int TERMINATED = 4;

  private int estado;
  private long id;
  private String conteudo;
  private int proximo;

  public Processo(String conteudo){
    this.id = new Date().getTime();
    this.estado = NEW;
    this.conteudo = conteudo;
    processar();
  }

  public void processar() {
    System.out.println("Processo "+ this.id +" executando...");
    imprimirEstado("NEW");

    while(this.estado != TERMINATED) {
      switch(this.estado){
        case NEW:
          this.estado = READY;
          break;
        case READY:
          imprimirEstado("READY");
          this.estado = RUNNING;
          break;
        case RUNNING:
          imprimirEstado("RUNNING");
          this.estado = randomState();
          break;
        case WAITING:
          imprimirEstado("WAITING");
          this.estado = READY;
          break;
        default:
          System.out.println("UNKNOWN STATE");
      }
      sleep();
    }
    imprimirEstado("TERMINATED");
  }

  public void imprimirEstado(String state){
    System.out.println("ID: " + this.id + " | STATE: " + state);
  }

  public void sleep(){
    try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
  }

  public int randomState() {
    int n = randomNumber();

    if(n == 1 || n == 3 || n == 4)
      return n;
    else
      return randomState();
  }

  public int randomNumber(){
    return Integer.parseInt(String.valueOf(Math.round(Math.random() * 4)));
  }

  public void enviarMensagem(int idProcesso){

  }

  public int getEstado(){
    return this.estado;
  }

  public String  getConteudo(){
    return this.conteudo;
  }

  public void setEstado(int estado){
    this.estado = estado;
  }

  public void setConteudo(String conteudo){
    this.conteudo = conteudo;
  }

  public int getProximo(){
    return this.proximo;
  }

  public void setProximo(int proximo){
    this.proximo = proximo;
  }

}
