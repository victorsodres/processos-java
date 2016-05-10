public class Message{

  private String Conteudo;
  private long Remetente;
  private long Destinatario;

  public Message(String conteudo, long remetente, long destinatario){
    this.Conteudo = conteudo;
    this.Remetente = remetente;
    this.Destinatario = destinatario;
  }

  public String getConteudo(){
    return this.Conteudo;
  }

  public long getRemetente(){
    return this.Remetente;
  }

  public long getDestinatario(){
    return this.Destinatario;
  }

}
