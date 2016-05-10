import java.util.Date;
import java.util.Timer;

public class ProcessosMain {

  public static void main(String[] args){
    // new Processo("Conteudo sensacional");

    System.out.print("\033[H\033[2J");

    GerenciadorProcessos gp = new GerenciadorProcessos();

    gp.adicionarProcesso(new Processo("msg1"));
    gp.adicionarProcesso(new Processo("msg2"));
    gp.adicionarProcesso(new Processo("msg3"));
    gp.adicionarProcesso(new Processo("msg4"));
    gp.adicionarProcesso(new Processo("msg5"));


    Timer timer = new Timer();
    timer.schedule(gp, 0, 100);

  }

}
