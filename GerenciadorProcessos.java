import java.util.List;
import java.util.ArrayList;

public class GerenciadorProcessos {

  private long primeiro;
  private long ultimo;
  private List<Processo> listaProcessos;

  public GerenciadorProcessos(){
    criaListaProcessos();

    new Thread(new Runnable() {
      @Override
      public void run() {
        checarProcessosParados();
      }
    }).start();
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

  //FIXME Ajustar a impressão e antes de remover o processo parado, tem que colocar
  // no início e pegar o indicador de próximo dele e passar para o processo que era o anterior a ele
  public void checarProcessosParados(){
    Processo p = null;
    boolean excluir = false;
    while(listaProcessos != null) {
      System.out.print("\r>> Processos na lista: " + listaProcessos.size());

      for (int i = 0; i < listaProcessos.size(); i++) {
        p = listaProcessos.get(i);
        if (Estado.TERMINATED.equals(p.getEstado())) {
          System.out.println(" >>> REMOVIDO: " + p.getId());
          listaProcessos.remove(i);
          excluir = true;
        }
      }

      if(listaProcessos.size() == 0 && excluir)
        listaProcessos = null;
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
