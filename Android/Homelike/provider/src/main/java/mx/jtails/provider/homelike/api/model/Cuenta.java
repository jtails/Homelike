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
 * on 2014-10-03 at 08:02:01 UTC 
 * Modify at your own risk.
 */

package mx.jtails.provider.homelike.api.model;

/**
 * Model definition for Cuenta.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the sugerenciascendpoint. For a detailed explanation see:
 * <a href="http://code.google.com/p/google-http-java-client/wiki/JSON">http://code.google.com/p/google-http-java-client/wiki/JSON</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class Cuenta extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<Direccion> direcciones;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<Dispositivo> dispositivos;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private com.google.api.client.util.DateTime fechaHoraCreacion;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer idCuenta;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer numPedidos;

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
  public java.util.List<Direccion> getDirecciones() {
    return direcciones;
  }

  /**
   * @param direcciones direcciones or {@code null} for none
   */
  public Cuenta setDirecciones(java.util.List<Direccion> direcciones) {
    this.direcciones = direcciones;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<Dispositivo> getDispositivos() {
    return dispositivos;
  }

  /**
   * @param dispositivos dispositivos or {@code null} for none
   */
  public Cuenta setDispositivos(java.util.List<Dispositivo> dispositivos) {
    this.dispositivos = dispositivos;
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
  public Cuenta setFechaHoraCreacion(com.google.api.client.util.DateTime fechaHoraCreacion) {
    this.fechaHoraCreacion = fechaHoraCreacion;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getIdCuenta() {
    return idCuenta;
  }

  /**
   * @param idCuenta idCuenta or {@code null} for none
   */
  public Cuenta setIdCuenta(java.lang.Integer idCuenta) {
    this.idCuenta = idCuenta;
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
  public Cuenta setNumPedidos(java.lang.Integer numPedidos) {
    this.numPedidos = numPedidos;
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
  public Cuenta setTelefono(java.lang.String telefono) {
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
  public Cuenta setUsuario(java.lang.String usuario) {
    this.usuario = usuario;
    return this;
  }

  @Override
  public Cuenta set(String fieldName, Object value) {
    return (Cuenta) super.set(fieldName, value);
  }

  @Override
  public Cuenta clone() {
    return (Cuenta) super.clone();
  }

}
