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
 * on 2014-09-27 at 05:08:46 UTC 
 * Modify at your own risk.
 */

package mx.jtails.homelike.api.model;

/**
 * Model definition for Sugerenciasp.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the sugerenciaspendpoint. For a detailed explanation see:
 * <a href="http://code.google.com/p/google-http-java-client/wiki/JSON">http://code.google.com/p/google-http-java-client/wiki/JSON</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class Sugerenciasp extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private com.google.api.client.util.DateTime fechaHoraSugerencia;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer idSugerencia;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private Proveedor proveedor;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String sugerencia;

  /**
   * @return value or {@code null} for none
   */
  public com.google.api.client.util.DateTime getFechaHoraSugerencia() {
    return fechaHoraSugerencia;
  }

  /**
   * @param fechaHoraSugerencia fechaHoraSugerencia or {@code null} for none
   */
  public Sugerenciasp setFechaHoraSugerencia(com.google.api.client.util.DateTime fechaHoraSugerencia) {
    this.fechaHoraSugerencia = fechaHoraSugerencia;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getIdSugerencia() {
    return idSugerencia;
  }

  /**
   * @param idSugerencia idSugerencia or {@code null} for none
   */
  public Sugerenciasp setIdSugerencia(java.lang.Integer idSugerencia) {
    this.idSugerencia = idSugerencia;
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
  public Sugerenciasp setProveedor(Proveedor proveedor) {
    this.proveedor = proveedor;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getSugerencia() {
    return sugerencia;
  }

  /**
   * @param sugerencia sugerencia or {@code null} for none
   */
  public Sugerenciasp setSugerencia(java.lang.String sugerencia) {
    this.sugerencia = sugerencia;
    return this;
  }

  @Override
  public Sugerenciasp set(String fieldName, Object value) {
    return (Sugerenciasp) super.set(fieldName, value);
  }

  @Override
  public Sugerenciasp clone() {
    return (Sugerenciasp) super.clone();
  }

}
