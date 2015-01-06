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
 * (build: 2014-11-17 18:43:33 UTC)
 * on 2014-12-12 at 04:39:05 UTC 
 * Modify at your own risk.
 */

package mx.jtails.homelike.api.model;

/**
 * Model definition for CantidadPago.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the pedidoendpoint. For a detailed explanation see:
 * <a href="http://code.google.com/p/google-http-java-client/wiki/JSON">http://code.google.com/p/google-http-java-client/wiki/JSON</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class CantidadPago extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer cantidadNumero;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String cantidadTexto;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer idCantidadPago;

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getCantidadNumero() {
    return cantidadNumero;
  }

  /**
   * @param cantidadNumero cantidadNumero or {@code null} for none
   */
  public CantidadPago setCantidadNumero(java.lang.Integer cantidadNumero) {
    this.cantidadNumero = cantidadNumero;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getCantidadTexto() {
    return cantidadTexto;
  }

  /**
   * @param cantidadTexto cantidadTexto or {@code null} for none
   */
  public CantidadPago setCantidadTexto(java.lang.String cantidadTexto) {
    this.cantidadTexto = cantidadTexto;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getIdCantidadPago() {
    return idCantidadPago;
  }

  /**
   * @param idCantidadPago idCantidadPago or {@code null} for none
   */
  public CantidadPago setIdCantidadPago(java.lang.Integer idCantidadPago) {
    this.idCantidadPago = idCantidadPago;
    return this;
  }

  @Override
  public CantidadPago set(String fieldName, Object value) {
    return (CantidadPago) super.set(fieldName, value);
  }

  @Override
  public CantidadPago clone() {
    return (CantidadPago) super.clone();
  }

}
