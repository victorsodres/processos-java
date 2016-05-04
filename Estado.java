public enum Estado {
  NEW(0), READY(1), RUNNING(2), WAITING(3), TERMINATED(4);

  public int valorEstado;

  Estado(int valor){
    valorEstado = valor;
  }

}
