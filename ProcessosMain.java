import java.util.Date;
import java.util.Timer;

public class ProcessosMain {

  public static void main(String[] args){
    // new Processo("Conteudo sensacional");

    System.out.print("\033[H\033[2J");

    GerenciadorProcessos gp = new GerenciadorProcessos();
    //
    gp.adicionarProcesso(new Processo("Conteudo sensacional"));
    gp.adicionarProcesso(new Processo("Conteudo da huea"));
    gp.adicionarProcesso(new Processo("Hauihdas"));
    gp.adicionarProcesso(new Processo("Testinho "));
    gp.adicionarProcesso(new Processo("Hahhah muito teste"));


    Timer timer = new Timer();
    timer.schedule(gp, 0, 100);

  }

}
