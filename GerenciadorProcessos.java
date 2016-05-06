import java.util.List;
import java.util.ArrayList;

public class GerenciadorProcessos {

  private long primeiro;
  private long ultimo;
  private List<Processo> listaProcessos;

  public GerenciadorProcessos(){
    criaListaProcessos();
  }

  public void adicionarProcesso(Processo processo){
    listaProcessos.add(processo);
    setPrimeiro(listaProcessos.get(0).getId());
    setUltimo(listaProcessos.get(listaProcessos.size() - 1).getId());

    verificaProximo(processo);

    System.out.println("Primeiro lista: " + getPrimeiro() + " - Último lista: " + getUltimo());
  }

  // TODO adicionar o id deste processo no próximo do processo anterior a ele na lista
  public void verificaProximo(Processo processo) {
    if(listaProcessos.size() > 1) {
      Processo pAnterior = listaProcessos.get(listaProcessos.size() - 2);
      pAnterior.setProximo(processo.getId());
    }
  }

  public long getPrimeiro(){
    return this.primeiro;
  }

  public long getUltimo(){
    return this.ultimo;
  }

  public void setUltimo(long ultimo){
    this.ultimo = ultimo;
  }

  public void setPrimeiro(long primeiro){
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
