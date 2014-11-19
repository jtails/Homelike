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
 * Model definition for Producto.
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
public final class Producto extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Float costoUnitario;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private CProducto cproducto;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String descripcion;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer idProducto;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String presentacion;

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
  public java.lang.Float getCostoUnitario() {
    return costoUnitario;
  }

  /**
   * @param costoUnitario costoUnitario or {@code null} for none
   */
  public Producto setCostoUnitario(java.lang.Float costoUnitario) {
    this.costoUnitario = costoUnitario;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public CProducto getCproducto() {
    return cproducto;
  }

  /**
   * @param cproducto cproducto or {@code null} for none
   */
  public Producto setCproducto(CProducto cproducto) {
    this.cproducto = cproducto;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getDescripcion() {
    return descripcion;
  }

  /**
   * @param descripcion descripcion or {@code null} for none
   */
  public Producto setDescripcion(java.lang.String descripcion) {
    this.descripcion = descripcion;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getIdProducto() {
    return idProducto;
  }

  /**
   * @param idProducto idProducto or {@code null} for none
   */
  public Producto setIdProducto(java.lang.Integer idProducto) {
    this.idProducto = idProducto;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getPresentacion() {
    return presentacion;
  }

  /**
   * @param presentacion presentacion or {@code null} for none
   */
  public Producto setPresentacion(java.lang.String presentacion) {
    this.presentacion = presentacion;
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
  public Producto setProveedor(Proveedor proveedor) {
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
  public Producto setStatus(java.lang.Integer status) {
    this.status = status;
    return this;
  }

  @Override
  public Producto set(String fieldName, Object value) {
    return (Producto) super.set(fieldName, value);
  }

  @Override
  public Producto clone() {
    return (Producto) super.clone();
  }

}
