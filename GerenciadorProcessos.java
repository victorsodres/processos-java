import java.util.List;
import java.util.ArrayList;
import java.util.TimerTask;
import java.util.Iterator;

public class GerenciadorProcessos extends TimerTask {

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

  public void run(){
    limparConsole();
    List<Processo> listaOrdenada = ordenarListaLigada(listaProcessos);
    imprimirProcessos(listaOrdenada);
  }

  private void limparConsole(){
    System.out.print("\033[H\033[2J");
  }

  public void adicionarProcesso(Processo processo){
    listaProcessos.add(processo);
    setPrimeiro(listaProcessos.get(0).getId());
    setUltimo(listaProcessos.get(listaProcessos.size() - 1).getId());

    verificaProximo(processo);

    //System.out.println("Primeiro lista: " + getPrimeiro() + " - Último lista: " + getUltimo());
  }

  // TODO adicionar o id deste processo no próximo do processo anterior a ele na lista
  public void verificaProximo(Processo processo) {
    if(listaProcessos.size() > 1) {
      Processo pAnterior = listaProcessos.get(listaProcessos.size() - 2);
      pAnterior.setProximo(processo.getId());
    }
  }

  private List<Processo> ordenarListaLigada(List<Processo> lista){
    List<Processo> newList = new ArrayList<Processo>();

    if(lista.size() > 0){
      newList.add(findProcesso(lista, primeiro));
      newList = adicionaProximo(newList);
    }

    return newList;
  }

  private List<Processo> adicionaProximo(List<Processo> lista){
    long nextId = lista.get(lista.size() -1).getProximo();

    if(nextId == 0)
      return lista;

    lista.add(findProcesso(listaProcessos, nextId));
    lista = adicionaProximo(lista);

    return lista;

  }

  private Processo findProcesso(List<Processo> list, long id){
    for(Processo proc: list){
      if(proc.getId() == id)
        return proc;
    }

    return null;
  }

  public void imprimirProcessos(List<Processo> lista){

    //Primeira linha
    for(Processo proc: lista ){
      if(!Estado.TERMINATED.equals(proc.getEstado())){
        print("|===================|");
      }
    }

    print("\n");

    for(Processo proc: lista){
      if(!Estado.TERMINATED.equals(proc.getEstado()))
      {
        print("|");
        print(completarString(19, "ID: " + proc.getId()));
        print("|");
      }
    }

    print("\n");

    for(Processo proc: lista){
      if(!Estado.TERMINATED.equals(proc.getEstado()))
      {
      print("|");
      print(completarString(19, "ESTADO: " + proc.getEstado()));
      print("|");
      }
    }

    print("\n");

    for(Processo proc: lista ){
      if(!Estado.TERMINATED.equals(proc.getEstado()))
      {
      print("|===================|");
    }
    }

    print("\n");
  }

  private void print(String str){
    System.out.print(str);
  }

  private String completarString(Integer qtd, String str){
    return String.format("%-" + qtd + "s", str);
  }

  //FIXME Ajustar a impressão e antes de remover o processo parado, tem que colocar
  // no início e pegar o indicador de próximo dele e passar para o processo que era o anterior a ele
  public void checarProcessosParados(){
    while(listaProcessos != null) {
      for (Iterator<Processo> iter = listaProcessos.listIterator(); iter.hasNext(); ) {
        Processo p = iter.next();
        if (Estado.TERMINATED.equals(p.getEstado())) {
            iter.remove();
        }
      }
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
