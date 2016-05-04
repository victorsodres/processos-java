import java.util.Date;
public class ProcessosMain {

  public static void main(String[] args){
    GerenciadorProcessos gp = new GerenciadorProcessos();

    gp.adicionarProcesso(new Processo("Conteudo sensacional"));
    gp.adicionarProcesso(new Processo("Conteudo da orals"));
  }

}
