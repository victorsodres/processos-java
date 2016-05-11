import java.util.Date;
import java.util.Timer;

public class ProcessosMain {

  public static void main(String[] args){
    // new Processo("Conteudo sensacional");

    System.out.print("\033[H\033[2J");

    GerenciadorProcessos gp = new GerenciadorProcessos();

    Timer timer = new Timer();
    timer.schedule(gp, 0, 100);

    gp.adicionarProcesso(new Processo("msg1"));
    sleep();
    gp.adicionarProcesso(new Processo(""));
    sleep();
    gp.adicionarProcesso(new Processo(""));
    sleep();
    gp.adicionarProcesso(new Processo(""));
    sleep();
    gp.adicionarProcesso(new Processo("msg5"));

  }

  public static void sleep(){
    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
