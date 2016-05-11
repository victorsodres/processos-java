public class Message{

  private String conteudo;
  private long remetente;
  private long destinatario;

  public Message(String conteudo, long remetente, long destinatario){
    this.conteudo = conteudo;
    this.remetente = remetente;
    this.destinatario = destinatario;
  }

  public Message(String conteudo){
    this.conteudo = conteudo;
  }

  public String getConteudo(){
    return this.conteudo;
  }

  public void setConteudo(String conteudo){
    this.conteudo = conteudo;
  }

  public long getRemetente(){
    return this.remetente;
  }

  public long getDestinatario(){
    return this.destinatario;
  }

}
