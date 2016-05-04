public class ProcessosMain {

  private final int NEW = 0;
  private final int READY = 1;
  private final int RUNNING = 3;
  private final int WAITING = 4;
  private final int TERMINATED = 5;

  public static void main(String[] args){
    GerenciadorProcessos gp = new GerenciadorProcessos();

    // new Thread(new Runnable() {
    //   @Override
    //   public void run() {}
    // ).run();

    gp.getListaProcessos().add(new Processo("Conteudo sensacional"));
    gp.getListaProcessos().add(new Processo("Conteudo da orals"));
  }

}
