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
 * on 2014-09-27 at 05:09:07 UTC 
 * Modify at your own risk.
 */

package mx.jtails.homelike.api.model;

/**
 * Model definition for Tpedido.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the tpedidoendpoint. For a detailed explanation see:
 * <a href="http://code.google.com/p/google-http-java-client/wiki/JSON">http://code.google.com/p/google-http-java-client/wiki/JSON</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class Tpedido extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private TpedidoPK key;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private BigInteger total;

  /**
   * @return value or {@code null} for none
   */
  public TpedidoPK getKey() {
    return key;
  }

  /**
   * @param key key or {@code null} for none
   */
  public Tpedido setKey(TpedidoPK key) {
    this.key = key;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public BigInteger getTotal() {
    return total;
  }

  /**
   * @param total total or {@code null} for none
   */
  public Tpedido setTotal(BigInteger total) {
    this.total = total;
    return this;
  }

  @Override
  public Tpedido set(String fieldName, Object value) {
    return (Tpedido) super.set(fieldName, value);
  }

  @Override
  public Tpedido clone() {
    return (Tpedido) super.clone();
  }

}
