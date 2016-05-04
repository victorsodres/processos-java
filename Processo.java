import java.util.Date;

public class Processo {

  private Estado estado;
  private int id;
  private String conteudo;
  private int proximo;

  public Processo(String conteudo){
    this.id = (int) new Date().getTime();
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
    System.out.println("Processo "+ this.id +" executando...");
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
    System.out.println("<------------------------->");
  }

  public void imprimirEstado(){
    System.out.println("ID: " + this.id + " | STATE: " + this.estado);
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

  public int getProximo(){
    return this.proximo;
  }

  public void setProximo(int proximo){
    this.proximo = proximo;
  }

  public int getId(){
    return this.id;
  }

  public void setId(int id){
    this.id = id;
  }

}
