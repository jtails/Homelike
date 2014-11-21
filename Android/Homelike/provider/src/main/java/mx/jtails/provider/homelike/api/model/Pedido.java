/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * This code was generated by https://code.google.com/p/google-apis-client-generator/
 * (build: 2014-07-22 21:53:01 UTC)
 * on 2014-10-03 at 08:02:16 UTC 
 * Modify at your own risk.
 */

package mx.jtails.provider.homelike.api.model;

/**
 * Model definition for Pedido.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the pedidoendpoint. For a detailed explanation see:
 * <a href="http://code.google.com/p/google-http-java-client/wiki/JSON">http://code.google.com/p/google-http-java-client/wiki/JSON</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class Pedido extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer calificacion;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private CantidadPago cantidadPago;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String comentarioCliente;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String comentarioEntregaCliente;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String comentarioEntregaProveedor;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String comentarioProveedor;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private Cuenta cuenta;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<DetallePedido> detallePedido;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<DetallePedido> detallePedidos;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private Direccion direccion;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private Dispositivo dispositivo;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private com.google.api.client.util.DateTime fechaHoraAceptacion;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private com.google.api.client.util.DateTime fechaHoraEntrega;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private com.google.api.client.util.DateTime fechaHoraPedido;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer idPedido;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private Proveedor proveedor;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer status;

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getCalificacion() {
    return calificacion;
  }

  /**
   * @param calificacion calificacion or {@code null} for none
   */
  public Pedido setCalificacion(java.lang.Integer calificacion) {
    this.calificacion = calificacion;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public CantidadPago getCantidadPago() {
    return cantidadPago;
  }

  /**
   * @param cantidadPago cantidadPago or {@code null} for none
   */
  public Pedido setCantidadPago(CantidadPago cantidadPago) {
    this.cantidadPago = cantidadPago;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getComentarioCliente() {
    return comentarioCliente;
  }

  /**
   * @param comentarioCliente comentarioCliente or {@code null} for none
   */
  public Pedido setComentarioCliente(java.lang.String comentarioCliente) {
    this.comentarioCliente = comentarioCliente;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getComentarioEntregaCliente() {
    return comentarioEntregaCliente;
  }

  /**
   * @param comentarioEntregaCliente comentarioEntregaCliente or {@code null} for none
   */
  public Pedido setComentarioEntregaCliente(java.lang.String comentarioEntregaCliente) {
    this.comentarioEntregaCliente = comentarioEntregaCliente;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getComentarioEntregaProveedor() {
    return comentarioEntregaProveedor;
  }

  /**
   * @param comentarioEntregaProveedor comentarioEntregaProveedor or {@code null} for none
   */
  public Pedido setComentarioEntregaProveedor(java.lang.String comentarioEntregaProveedor) {
    this.comentarioEntregaProveedor = comentarioEntregaProveedor;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getComentarioProveedor() {
    return comentarioProveedor;
  }

  /**
   * @param comentarioProveedor comentarioProveedor or {@code null} for none
   */
  public Pedido setComentarioProveedor(java.lang.String comentarioProveedor) {
    this.comentarioProveedor = comentarioProveedor;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public Cuenta getCuenta() {
    return cuenta;
  }

  /**
   * @param cuenta cuenta or {@code null} for none
   */
  public Pedido setCuenta(Cuenta cuenta) {
    this.cuenta = cuenta;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<DetallePedido> getDetallePedido() {
    return detallePedido;
  }

  /**
   * @param detallePedido detallePedido or {@code null} for none
   */
  public Pedido setDetallePedido(java.util.List<DetallePedido> detallePedido) {
    this.detallePedido = detallePedido;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<DetallePedido> getDetallePedidos() {
    return detallePedidos;
  }

  /**
   * @param detallePedidos detallePedidos or {@code null} for none
   */
  public Pedido setDetallePedidos(java.util.List<DetallePedido> detallePedidos) {
    this.detallePedidos = detallePedidos;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public Direccion getDireccion() {
    return direccion;
  }

  /**
   * @param direccion direccion or {@code null} for none
   */
  public Pedido setDireccion(Direccion direccion) {
    this.direccion = direccion;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public Dispositivo getDispositivo() {
    return dispositivo;
  }

  /**
   * @param dispositivo dispositivo or {@code null} for none
   */
  public Pedido setDispositivo(Dispositivo dispositivo) {
    this.dispositivo = dispositivo;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public com.google.api.client.util.DateTime getFechaHoraAceptacion() {
    return fechaHoraAceptacion;
  }

  /**
   * @param fechaHoraAceptacion fechaHoraAceptacion or {@code null} for none
   */
  public Pedido setFechaHoraAceptacion(com.google.api.client.util.DateTime fechaHoraAceptacion) {
    this.fechaHoraAceptacion = fechaHoraAceptacion;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public com.google.api.client.util.DateTime getFechaHoraEntrega() {
    return fechaHoraEntrega;
  }

  /**
   * @param fechaHoraEntrega fechaHoraEntrega or {@code null} for none
   */
  public Pedido setFechaHoraEntrega(com.google.api.client.util.DateTime fechaHoraEntrega) {
    this.fechaHoraEntrega = fechaHoraEntrega;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public com.google.api.client.util.DateTime getFechaHoraPedido() {
    return fechaHoraPedido;
  }

  /**
   * @param fechaHoraPedido fechaHoraPedido or {@code null} for none
   */
  public Pedido setFechaHoraPedido(com.google.api.client.util.DateTime fechaHoraPedido) {
    this.fechaHoraPedido = fechaHoraPedido;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getIdPedido() {
    return idPedido;
  }

  /**
   * @param idPedido idPedido or {@code null} for none
   */
  public Pedido setIdPedido(java.lang.Integer idPedido) {
    this.idPedido = idPedido;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public Proveedor getProveedor() {
    return proveedor;
  }

  /**
   * @param proveedor proveedor or {@code null} for none
   */
  public Pedido setProveedor(Proveedor proveedor) {
    this.proveedor = proveedor;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getStatus() {
    return status;
  }

  /**
   * @param status status or {@code null} for none
   */
  public Pedido setStatus(java.lang.Integer status) {
    this.status = status;
    return this;
  }

  @Override
  public Pedido set(String fieldName, Object value) {
    return (Pedido) super.set(fieldName, value);
  }

  @Override
  public Pedido clone() {
    return (Pedido) super.clone();
  }

}
