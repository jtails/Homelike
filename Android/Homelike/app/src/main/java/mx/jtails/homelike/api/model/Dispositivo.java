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
 * on 2014-09-27 at 05:07:24 UTC 
 * Modify at your own risk.
 */

package mx.jtails.homelike.api.model;

/**
 * Model definition for Dispositivo.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the pedidoendpoint. For a detailed explanation see:
 * <a href="http://code.google.com/p/google-http-java-client/wiki/JSON">http://code.google.com/p/google-http-java-client/wiki/JSON</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class Dispositivo extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private Cuenta cuenta;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer esDefault;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private com.google.api.client.util.DateTime fechaHoraCreacion;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String gcmid;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer idDispositivo;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String imei;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String modelo;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String plataforma;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer status;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String tipoDispositivo;

  /**
   * @return value or {@code null} for none
   */
  public Cuenta getCuenta() {
    return cuenta;
  }

  /**
   * @param cuenta cuenta or {@code null} for none
   */
  public Dispositivo setCuenta(Cuenta cuenta) {
    this.cuenta = cuenta;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getEsDefault() {
    return esDefault;
  }

  /**
   * @param esDefault esDefault or {@code null} for none
   */
  public Dispositivo setEsDefault(java.lang.Integer esDefault) {
    this.esDefault = esDefault;
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
  public Dispositivo setFechaHoraCreacion(com.google.api.client.util.DateTime fechaHoraCreacion) {
    this.fechaHoraCreacion = fechaHoraCreacion;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getGcmid() {
    return gcmid;
  }

  /**
   * @param gcmid gcmid or {@code null} for none
   */
  public Dispositivo setGcmid(java.lang.String gcmid) {
    this.gcmid = gcmid;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getIdDispositivo() {
    return idDispositivo;
  }

  /**
   * @param idDispositivo idDispositivo or {@code null} for none
   */
  public Dispositivo setIdDispositivo(java.lang.Integer idDispositivo) {
    this.idDispositivo = idDispositivo;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getImei() {
    return imei;
  }

  /**
   * @param imei imei or {@code null} for none
   */
  public Dispositivo setImei(java.lang.String imei) {
    this.imei = imei;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getModelo() {
    return modelo;
  }

  /**
   * @param modelo modelo or {@code null} for none
   */
  public Dispositivo setModelo(java.lang.String modelo) {
    this.modelo = modelo;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getPlataforma() {
    return plataforma;
  }

  /**
   * @param plataforma plataforma or {@code null} for none
   */
  public Dispositivo setPlataforma(java.lang.String plataforma) {
    this.plataforma = plataforma;
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
  public Dispositivo setStatus(java.lang.Integer status) {
    this.status = status;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getTipoDispositivo() {
    return tipoDispositivo;
  }

  /**
   * @param tipoDispositivo tipoDispositivo or {@code null} for none
   */
  public Dispositivo setTipoDispositivo(java.lang.String tipoDispositivo) {
    this.tipoDispositivo = tipoDispositivo;
    return this;
  }

  @Override
  public Dispositivo set(String fieldName, Object value) {
    return (Dispositivo) super.set(fieldName, value);
  }

  @Override
  public Dispositivo clone() {
    return (Dispositivo) super.clone();
  }

}
