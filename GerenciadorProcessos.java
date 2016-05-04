import java.util.List;
import java.util.ArrayList;

public class GerenciadorProcessos {

  private int primeiro;
  private int ultimo;
  private List<Processo> listaProcessos;

  public GerenciadorProcessos(){
    criaListaProcessos();
  }

  public int getPrimeiro(){
    return this.primeiro;
  }

  public int getUltimo(){
    return this.ultimo;
  }

  public void setUltimo(int ultimo){
    this.ultimo = ultimo;
  }

  public void setPrimeiro(int primeiro){
    this.primeiro = primeiro;
  }

  public List<Processo> getListaProcessos(){
    return this.listaProcessos;
  }

  public List<Processo> criaListaProcessos(){
    this.listaProcessos = new ArrayList<Processo>();
    return this.listaProcessos;
  }
}
