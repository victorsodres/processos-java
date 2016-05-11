import java.util.List;
import java.util.ArrayList;
import java.util.TimerTask;
import java.util.Iterator;

public class GerenciadorProcessos extends TimerTask {

  private long primeiro;
  private long ultimo;
  private List<Processo> listaProcessos;
  private List<Processo> listaProcessosMortos;
  private SharedMemory sharedMemory = SharedMemory.getInstance();

  public GerenciadorProcessos(){
    criaListaProcessos();
  }

  //Comentei esse de lista ordenada pq o efeito acaba sendo o mesmo de só listar os processos
  // Pra testar descomenta os dois juntos
  public void run(){
    limparConsole();
    // List<Processo> listaOrdenada = ordenarListaLigada();
    // imprimirProcessos(listaOrdenada);
    print("\n>>> RUNNING\n");
    imprimirProcessos(listaProcessos);
    print("\n\n>>> DEAD\n");
    imprimirProcessos(listaProcessosMortos);
    imprimirSharedMemory();
    checarProcessosParados();
  }

  private void limparConsole(){
    System.out.print("\033[H\033[2J");
  }

  public void adicionarProcesso(Processo processo) {
    listaProcessos.add(processo);
    setPrimeiro(listaProcessos.get(0).getId());
    setUltimo(listaProcessos.get(listaProcessos.size() - 1).getId());

    verificaProximo(processo);
  }

  public void verificaProximo(Processo processo) {
    if(listaProcessos.size() > 1) {
      Processo pAnterior = listaProcessos.get(listaProcessos.size() - 2);
      pAnterior.setProximo(processo.getId());
    }
  }

  private List<Processo> ordenarListaLigada(List<Processo> lista){
    List<Processo> newList = new ArrayList<Processo>();

    if(lista.size() > 0){
      newList.add(findProcesso(primeiro));
      newList = adicionaProximo(newList);
    }

    return newList;
  }

  private List<Processo> adicionaProximo(List<Processo> lista){
    long nextId = lista.get(lista.size() -1).getProximo();

    if(nextId == 0)
      return lista;

    lista.add(findProcesso(nextId));
    lista = adicionaProximo(lista);

    return lista;

  }

  private Processo findProcesso(long id){
    for(Processo proc: listaProcessos)
      if(proc.getId() == id)
        return proc;

    return null;
  }

  private Processo findProcessoByProximo(long idProximo){
    for(Processo proc: listaProcessos)
      if(proc.getProximo() == idProximo)
        return proc;

    return null;
  }

  public void imprimirSharedMemory(){
    print("\n\n");
    print("|===================|");
    print("\n");
    imprimirAttrs("MEMORY", "SHARED");

    for(Message msg: sharedMemory.getListaMensagens()){
      if(msg.getConteudo() != "")
        print("\n|" + completarString(19, "- " + msg.getConteudo()) + "|");
    }
    print("\n|===================|");
  }

  public void imprimirProcessos(List<Processo> lista){

    //Primeira linha
    for(Processo proc: lista ){
      print("|===================|");
    }
    print("\n");

    //Atributos

    for(int i = 0; i < lista.size(); i++){
      imprimirAttrs("POS", String.valueOf(i));
    }
    print("\n");

    for(Processo proc: lista){
      imprimirAttrs("ID", String.valueOf(proc.getId()));
    }
    print("\n");

    for(Processo proc: lista){
      imprimirAttrs("ESTADO", proc.getEstado().toString());
    }
    print("\n");

    for(Processo proc: lista){
      imprimirAttrs("CONTEUDO", proc.getConteudo());
    }
    print("\n");

    for(Processo proc: lista){
      imprimirAttrs("PROXIMO", String.valueOf(proc.getProximo()));
    }
    print("\n");

    //Ultima Linha

    for(Processo proc: lista ){
      print("|===================|");
    }
    print("\n");
  }

  private void print(String str){
    System.out.print(str);
  }

  private void imprimirAttrs(String tipo, String valor) {
    print("|");
    print(completarString(19, String.format("%s: %s", tipo, valor)));
    print("|");
  }

  private String completarString(Integer qtd, String str){
    return String.format("%-" + qtd + "s", str);
  }

  /**
  * Ação: Quando um processo (B) for terminado, o anterior a ele (A) receberá o ID do próximo (C)
  * para que ele (B) possa ser removido da lista.
  *
  * obs. Coloquei o estado DEAD em vez de remover apenas para a visibilidade
  * quando for impresso os processos.
  */
  public void checarProcessosParados(){
    Processo p = null;

    for (int i = 0; i < listaProcessos.size(); i++) {
      p = listaProcessos.get(i);
      if (Estado.TERMINATED.equals(p.getEstado())) {
        Processo pAnterior = findProcessoByProximo(p.getId());
        if(pAnterior != null)
          pAnterior.setProximo(p.getProximo());
        listaProcessos.remove(i);
        p.mandarMensagem();
        p.setEstado(Estado.DEAD);
        // p.setProximo(0);
        listaProcessosMortos.add(0, p);
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
    this.listaProcessosMortos = new ArrayList<Processo>();
    return this.listaProcessos;
  }
}
