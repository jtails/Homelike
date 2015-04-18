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
 * (build: 2014-10-28 17:08:27 UTC)
 * on 2014-11-06 at 03:34:13 UTC 
 * Modify at your own risk.
 */

package mx.jtails.provider.homelike.api.model;

/**
 * Model definition for Proveedor.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the horariosproveedorendpoint. For a detailed explanation
 * see:
 * <a href="http://code.google.com/p/google-http-java-client/wiki/JSON">http://code.google.com/p/google-http-java-client/wiki/JSON</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class Proveedor extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer calificacion;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String calle;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String calle1;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String calle2;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String colonia;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String cp;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String delegacion;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String estado;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private com.google.api.client.util.DateTime fechaHoraCreacion;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private com.google.api.client.util.DateTime fechaHoraUltimoPedido;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<HorariosProveedor> horarios;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer idProveedor;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String latitud;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String logo;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String longitud;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String nelatitud;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String nelongitud;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String nexterior;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String ninterior;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String nombre;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer numClientes;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer numPedidos;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String pais;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<Producto> productos;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String razonSocial;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String referencia1;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String referencia2;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String rfc;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private Servicio servicio;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String slogan;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer status;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String swlatitud;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String swlongitud;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String telefono;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String usuario;

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getCalificacion() {
    return calificacion;
  }

  /**
   * @param calificacion calificacion or {@code null} for none
   */
  public Proveedor setCalificacion(java.lang.Integer calificacion) {
    this.calificacion = calificacion;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getCalle() {
    return calle;
  }

  /**
   * @param calle calle or {@code null} for none
   */
  public Proveedor setCalle(java.lang.String calle) {
    this.calle = calle;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getCalle1() {
    return calle1;
  }

  /**
   * @param calle1 calle1 or {@code null} for none
   */
  public Proveedor setCalle1(java.lang.String calle1) {
    this.calle1 = calle1;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getCalle2() {
    return calle2;
  }

  /**
   * @param calle2 calle2 or {@code null} for none
   */
  public Proveedor setCalle2(java.lang.String calle2) {
    this.calle2 = calle2;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getColonia() {
    return colonia;
  }

  /**
   * @param colonia colonia or {@code null} for none
   */
  public Proveedor setColonia(java.lang.String colonia) {
    this.colonia = colonia;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getCp() {
    return cp;
  }

  /**
   * @param cp cp or {@code null} for none
   */
  public Proveedor setCp(java.lang.String cp) {
    this.cp = cp;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getDelegacion() {
    return delegacion;
  }

  /**
   * @param delegacion delegacion or {@code null} for none
   */
  public Proveedor setDelegacion(java.lang.String delegacion) {
    this.delegacion = delegacion;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getEstado() {
    return estado;
  }

  /**
   * @param estado estado or {@code null} for none
   */
  public Proveedor setEstado(java.lang.String estado) {
    this.estado = estado;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public com.google.api.client.util.DateTime getFechaHoraCreacion() {
    return fechaHoraCreacion;
  }

  /**
   * @param fechaHoraCreacion fechaHoraCreacion or {@code null} for none
   */
  public Proveedor setFechaHoraCreacion(com.google.api.client.util.DateTime fechaHoraCreacion) {
    this.fechaHoraCreacion = fechaHoraCreacion;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public com.google.api.client.util.DateTime getFechaHoraUltimoPedido() {
    return fechaHoraUltimoPedido;
  }

  /**
   * @param fechaHoraUltimoPedido fechaHoraUltimoPedido or {@code null} for none
   */
  public Proveedor setFechaHoraUltimoPedido(com.google.api.client.util.DateTime fechaHoraUltimoPedido) {
    this.fechaHoraUltimoPedido = fechaHoraUltimoPedido;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<HorariosProveedor> getHorarios() {
    return horarios;
  }

  /**
   * @param horarios horarios or {@code null} for none
   */
  public Proveedor setHorarios(java.util.List<HorariosProveedor> horarios) {
    this.horarios = horarios;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getIdProveedor() {
    return idProveedor;
  }

  /**
   * @param idProveedor idProveedor or {@code null} for none
   */
  public Proveedor setIdProveedor(java.lang.Integer idProveedor) {
    this.idProveedor = idProveedor;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getLatitud() {
    return latitud;
  }

  /**
   * @param latitud latitud or {@code null} for none
   */
  public Proveedor setLatitud(java.lang.String latitud) {
    this.latitud = latitud;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getLogo() {
    return logo;
  }

  /**
   * @param logo logo or {@code null} for none
   */
  public Proveedor setLogo(java.lang.String logo) {
    this.logo = logo;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getLongitud() {
    return longitud;
  }

  /**
   * @param longitud longitud or {@code null} for none
   */
  public Proveedor setLongitud(java.lang.String longitud) {
    this.longitud = longitud;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getNelatitud() {
    return nelatitud;
  }

  /**
   * @param nelatitud nelatitud or {@code null} for none
   */
  public Proveedor setNelatitud(java.lang.String nelatitud) {
    this.nelatitud = nelatitud;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getNelongitud() {
    return nelongitud;
  }

  /**
   * @param nelongitud nelongitud or {@code null} for none
   */
  public Proveedor setNelongitud(java.lang.String nelongitud) {
    this.nelongitud = nelongitud;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getNexterior() {
    return nexterior;
  }

  /**
   * @param nexterior nexterior or {@code null} for none
   */
  public Proveedor setNexterior(java.lang.String nexterior) {
    this.nexterior = nexterior;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getNinterior() {
    return ninterior;
  }

  /**
   * @param ninterior ninterior or {@code null} for none
   */
  public Proveedor setNinterior(java.lang.String ninterior) {
    this.ninterior = ninterior;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getNombre() {
    return nombre;
  }

  /**
   * @param nombre nombre or {@code null} for none
   */
  public Proveedor setNombre(java.lang.String nombre) {
    this.nombre = nombre;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getNumClientes() {
    return numClientes;
  }

  /**
   * @param numClientes numClientes or {@code null} for none
   */
  public Proveedor setNumClientes(java.lang.Integer numClientes) {
    this.numClientes = numClientes;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getNumPedidos() {
    return numPedidos;
  }

  /**
   * @param numPedidos numPedidos or {@code null} for none
   */
  public Proveedor setNumPedidos(java.lang.Integer numPedidos) {
    this.numPedidos = numPedidos;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getPais() {
    return pais;
  }

  /**
   * @param pais pais or {@code null} for none
   */
  public Proveedor setPais(java.lang.String pais) {
    this.pais = pais;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<Producto> getProductos() {
    return productos;
  }

  /**
   * @param productos productos or {@code null} for none
   */
  public Proveedor setProductos(java.util.List<Producto> productos) {
    this.productos = productos;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getRazonSocial() {
    return razonSocial;
  }

  /**
   * @param razonSocial razonSocial or {@code null} for none
   */
  public Proveedor setRazonSocial(java.lang.String razonSocial) {
    this.razonSocial = razonSocial;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getReferencia1() {
    return referencia1;
  }

  /**
   * @param referencia1 referencia1 or {@code null} for none
   */
  public Proveedor setReferencia1(java.lang.String referencia1) {
    this.referencia1 = referencia1;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getReferencia2() {
    return referencia2;
  }

  /**
   * @param referencia2 referencia2 or {@code null} for none
   */
  public Proveedor setReferencia2(java.lang.String referencia2) {
    this.referencia2 = referencia2;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getRfc() {
    return rfc;
  }

  /**
   * @param rfc rfc or {@code null} for none
   */
  public Proveedor setRfc(java.lang.String rfc) {
    this.rfc = rfc;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public Servicio getServicio() {
    return servicio;
  }

  /**
   * @param servicio servicio or {@code null} for none
   */
  public Proveedor setServicio(Servicio servicio) {
    this.servicio = servicio;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getSlogan() {
    return slogan;
  }

  /**
   * @param slogan slogan or {@code null} for none
   */
  public Proveedor setSlogan(java.lang.String slogan) {
    this.slogan = slogan;
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
  public Proveedor setStatus(java.lang.Integer status) {
    this.status = status;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getSwlatitud() {
    return swlatitud;
  }

  /**
   * @param swlatitud swlatitud or {@code null} for none
   */
  public Proveedor setSwlatitud(java.lang.String swlatitud) {
    this.swlatitud = swlatitud;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getSwlongitud() {
    return swlongitud;
  }

  /**
   * @param swlongitud swlongitud or {@code null} for none
   */
  public Proveedor setSwlongitud(java.lang.String swlongitud) {
    this.swlongitud = swlongitud;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getTelefono() {
    return telefono;
  }

  /**
   * @param telefono telefono or {@code null} for none
   */
  public Proveedor setTelefono(java.lang.String telefono) {
    this.telefono = telefono;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getUsuario() {
    return usuario;
  }

  /**
   * @param usuario usuario or {@code null} for none
   */
  public Proveedor setUsuario(java.lang.String usuario) {
    this.usuario = usuario;
    return this;
  }

  @Override
  public Proveedor set(String fieldName, Object value) {
    return (Proveedor) super.set(fieldName, value);
  }

  @Override
  public Proveedor clone() {
    return (Proveedor) super.clone();
  }

}