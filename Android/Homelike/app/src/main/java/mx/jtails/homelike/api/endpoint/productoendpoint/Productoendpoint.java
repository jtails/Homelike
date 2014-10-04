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
 * on 2014-10-03 at 08:02:24 UTC 
 * Modify at your own risk.
 */

package mx.jtails.homelike.api.endpoint.productoendpoint;

/**
 * Service definition for Productoendpoint (v1).
 *
 * <p>
 * This is an API
 * </p>
 *
 * <p>
 * For more information about this service, see the
 * <a href="" target="_blank">API Documentation</a>
 * </p>
 *
 * <p>
 * This service uses {@link ProductoendpointRequestInitializer} to initialize global parameters via its
 * {@link Builder}.
 * </p>
 *
 * @since 1.3
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public class Productoendpoint extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient {

  // Note: Leave this static initializer at the top of the file.
  static {
    com.google.api.client.util.Preconditions.checkState(
        com.google.api.client.googleapis.GoogleUtils.MAJOR_VERSION == 1 &&
        com.google.api.client.googleapis.GoogleUtils.MINOR_VERSION >= 15,
        "You are currently running with version %s of google-api-client. " +
        "You need at least version 1.15 of google-api-client to run version " +
        "1.16.0-rc of the productoendpoint library.", com.google.api.client.googleapis.GoogleUtils.VERSION);
  }

  /**
   * The default encoded root URL of the service. This is determined when the library is generated
   * and normally should not be changed.
   *
   * @since 1.7
   */
  public static final String DEFAULT_ROOT_URL = "https://steam-form-673.appspot.com/_ah/api/";

  /**
   * The default encoded service path of the service. This is determined when the library is
   * generated and normally should not be changed.
   *
   * @since 1.7
   */
  public static final String DEFAULT_SERVICE_PATH = "productoendpoint/v1/";

  /**
   * The default encoded base URL of the service. This is determined when the library is generated
   * and normally should not be changed.
   */
  public static final String DEFAULT_BASE_URL = DEFAULT_ROOT_URL + DEFAULT_SERVICE_PATH;

  /**
   * Constructor.
   *
   * <p>
   * Use {@link Builder} if you need to specify any of the optional parameters.
   * </p>
   *
   * @param transport HTTP transport, which should normally be:
   *        <ul>
   *        <li>Google App Engine:
   *        {@code com.google.api.client.extensions.appengine.http.UrlFetchTransport}</li>
   *        <li>Android: {@code newCompatibleTransport} from
   *        {@code com.google.api.client.extensions.android.http.AndroidHttp}</li>
   *        <li>Java: {@link com.google.api.client.googleapis.javanet.GoogleNetHttpTransport#newTrustedTransport()}
   *        </li>
   *        </ul>
   * @param jsonFactory JSON factory, which may be:
   *        <ul>
   *        <li>Jackson: {@code com.google.api.client.json.jackson2.JacksonFactory}</li>
   *        <li>Google GSON: {@code com.google.api.client.json.gson.GsonFactory}</li>
   *        <li>Android Honeycomb or higher:
   *        {@code com.google.api.client.extensions.android.json.AndroidJsonFactory}</li>
   *        </ul>
   * @param httpRequestInitializer HTTP request initializer or {@code null} for none
   * @since 1.7
   */
  public Productoendpoint(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
      com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
    this(new Builder(transport, jsonFactory, httpRequestInitializer));
  }

  /**
   * @param builder builder
   */
  Productoendpoint(Builder builder) {
    super(builder);
  }

  @Override
  protected void initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest<?> httpClientRequest) throws java.io.IOException {
    super.initialize(httpClientRequest);
  }

  /**
   * Create a request for the method "insertProducto".
   *
   * This request holds the parameters needed by the the productoendpoint server.  After setting any
   * optional parameters, call the {@link InsertProducto#execute()} method to invoke the remote
   * operation.
   *
   * @param content the {@link mx.jtails.homelike.api.model.Producto}
   * @return the request
   */
  public InsertProducto insertProducto(mx.jtails.homelike.api.model.Producto content) throws java.io.IOException {
    InsertProducto result = new InsertProducto(content);
    initialize(result);
    return result;
  }

  public class InsertProducto extends ProductoendpointRequest<mx.jtails.homelike.api.model.Producto> {

    private static final String REST_PATH = "producto";

    /**
     * Create a request for the method "insertProducto".
     *
     * This request holds the parameters needed by the the productoendpoint server.  After setting any
     * optional parameters, call the {@link InsertProducto#execute()} method to invoke the remote
     * operation. <p> {@link InsertProducto#initialize(com.google.api.client.googleapis.services.Abstr
     * actGoogleClientRequest)} must be called to initialize this instance immediately after invoking
     * the constructor. </p>
     *
     * @param content the {@link mx.jtails.homelike.api.model.Producto}
     * @since 1.13
     */
    protected InsertProducto(mx.jtails.homelike.api.model.Producto content) {
      super(Productoendpoint.this, "POST", REST_PATH, content, mx.jtails.homelike.api.model.Producto.class);
    }

    @Override
    public InsertProducto setAlt(java.lang.String alt) {
      return (InsertProducto) super.setAlt(alt);
    }

    @Override
    public InsertProducto setFields(java.lang.String fields) {
      return (InsertProducto) super.setFields(fields);
    }

    @Override
    public InsertProducto setKey(java.lang.String key) {
      return (InsertProducto) super.setKey(key);
    }

    @Override
    public InsertProducto setOauthToken(java.lang.String oauthToken) {
      return (InsertProducto) super.setOauthToken(oauthToken);
    }

    @Override
    public InsertProducto setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (InsertProducto) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public InsertProducto setQuotaUser(java.lang.String quotaUser) {
      return (InsertProducto) super.setQuotaUser(quotaUser);
    }

    @Override
    public InsertProducto setUserIp(java.lang.String userIp) {
      return (InsertProducto) super.setUserIp(userIp);
    }

    @Override
    public InsertProducto set(String parameterName, Object value) {
      return (InsertProducto) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "listProducto".
   *
   * This request holds the parameters needed by the the productoendpoint server.  After setting any
   * optional parameters, call the {@link ListProducto#execute()} method to invoke the remote
   * operation.
   *
   * @return the request
   */
  public ListProducto listProducto() throws java.io.IOException {
    ListProducto result = new ListProducto();
    initialize(result);
    return result;
  }

  public class ListProducto extends ProductoendpointRequest<mx.jtails.homelike.api.model.CollectionResponseProducto> {

    private static final String REST_PATH = "producto";

    /**
     * Create a request for the method "listProducto".
     *
     * This request holds the parameters needed by the the productoendpoint server.  After setting any
     * optional parameters, call the {@link ListProducto#execute()} method to invoke the remote
     * operation. <p> {@link
     * ListProducto#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
     * must be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @since 1.13
     */
    protected ListProducto() {
      super(Productoendpoint.this, "GET", REST_PATH, null, mx.jtails.homelike.api.model.CollectionResponseProducto.class);
    }

    @Override
    public com.google.api.client.http.HttpResponse executeUsingHead() throws java.io.IOException {
      return super.executeUsingHead();
    }

    @Override
    public com.google.api.client.http.HttpRequest buildHttpRequestUsingHead() throws java.io.IOException {
      return super.buildHttpRequestUsingHead();
    }

    @Override
    public ListProducto setAlt(java.lang.String alt) {
      return (ListProducto) super.setAlt(alt);
    }

    @Override
    public ListProducto setFields(java.lang.String fields) {
      return (ListProducto) super.setFields(fields);
    }

    @Override
    public ListProducto setKey(java.lang.String key) {
      return (ListProducto) super.setKey(key);
    }

    @Override
    public ListProducto setOauthToken(java.lang.String oauthToken) {
      return (ListProducto) super.setOauthToken(oauthToken);
    }

    @Override
    public ListProducto setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (ListProducto) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public ListProducto setQuotaUser(java.lang.String quotaUser) {
      return (ListProducto) super.setQuotaUser(quotaUser);
    }

    @Override
    public ListProducto setUserIp(java.lang.String userIp) {
      return (ListProducto) super.setUserIp(userIp);
    }

    @Override
    public ListProducto set(String parameterName, Object value) {
      return (ListProducto) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "listProductosByProveedor".
   *
   * This request holds the parameters needed by the the productoendpoint server.  After setting any
   * optional parameters, call the {@link ListProductosByProveedor#execute()} method to invoke the
   * remote operation.
   *
   * @return the request
   */
  public ListProductosByProveedor listProductosByProveedor() throws java.io.IOException {
    ListProductosByProveedor result = new ListProductosByProveedor();
    initialize(result);
    return result;
  }

  public class ListProductosByProveedor extends ProductoendpointRequest<mx.jtails.homelike.api.model.ProductoCollection> {

    private static final String REST_PATH = "listProductosByProveedor";

    /**
     * Create a request for the method "listProductosByProveedor".
     *
     * This request holds the parameters needed by the the productoendpoint server.  After setting any
     * optional parameters, call the {@link ListProductosByProveedor#execute()} method to invoke the
     * remote operation. <p> {@link ListProductosByProveedor#initialize(com.google.api.client.googleap
     * is.services.AbstractGoogleClientRequest)} must be called to initialize this instance
     * immediately after invoking the constructor. </p>
     *
     * @since 1.13
     */
    protected ListProductosByProveedor() {
      super(Productoendpoint.this, "GET", REST_PATH, null, mx.jtails.homelike.api.model.ProductoCollection.class);
    }

    @Override
    public com.google.api.client.http.HttpResponse executeUsingHead() throws java.io.IOException {
      return super.executeUsingHead();
    }

    @Override
    public com.google.api.client.http.HttpRequest buildHttpRequestUsingHead() throws java.io.IOException {
      return super.buildHttpRequestUsingHead();
    }

    @Override
    public ListProductosByProveedor setAlt(java.lang.String alt) {
      return (ListProductosByProveedor) super.setAlt(alt);
    }

    @Override
    public ListProductosByProveedor setFields(java.lang.String fields) {
      return (ListProductosByProveedor) super.setFields(fields);
    }

    @Override
    public ListProductosByProveedor setKey(java.lang.String key) {
      return (ListProductosByProveedor) super.setKey(key);
    }

    @Override
    public ListProductosByProveedor setOauthToken(java.lang.String oauthToken) {
      return (ListProductosByProveedor) super.setOauthToken(oauthToken);
    }

    @Override
    public ListProductosByProveedor setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (ListProductosByProveedor) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public ListProductosByProveedor setQuotaUser(java.lang.String quotaUser) {
      return (ListProductosByProveedor) super.setQuotaUser(quotaUser);
    }

    @Override
    public ListProductosByProveedor setUserIp(java.lang.String userIp) {
      return (ListProductosByProveedor) super.setUserIp(userIp);
    }

    @Override
    public ListProductosByProveedor set(String parameterName, Object value) {
      return (ListProductosByProveedor) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "removeProducto".
   *
   * This request holds the parameters needed by the the productoendpoint server.  After setting any
   * optional parameters, call the {@link RemoveProducto#execute()} method to invoke the remote
   * operation.
   *
   * @param id
   * @return the request
   */
  public RemoveProducto removeProducto(java.lang.Long id) throws java.io.IOException {
    RemoveProducto result = new RemoveProducto(id);
    initialize(result);
    return result;
  }

  public class RemoveProducto extends ProductoendpointRequest<Void> {

    private static final String REST_PATH = "producto/{id}";

    /**
     * Create a request for the method "removeProducto".
     *
     * This request holds the parameters needed by the the productoendpoint server.  After setting any
     * optional parameters, call the {@link RemoveProducto#execute()} method to invoke the remote
     * operation. <p> {@link RemoveProducto#initialize(com.google.api.client.googleapis.services.Abstr
     * actGoogleClientRequest)} must be called to initialize this instance immediately after invoking
     * the constructor. </p>
     *
     * @param id
     * @since 1.13
     */
    protected RemoveProducto(java.lang.Long id) {
      super(Productoendpoint.this, "DELETE", REST_PATH, null, Void.class);
      this.id = com.google.api.client.util.Preconditions.checkNotNull(id, "Required parameter id must be specified.");
    }

    @Override
    public RemoveProducto setAlt(java.lang.String alt) {
      return (RemoveProducto) super.setAlt(alt);
    }

    @Override
    public RemoveProducto setFields(java.lang.String fields) {
      return (RemoveProducto) super.setFields(fields);
    }

    @Override
    public RemoveProducto setKey(java.lang.String key) {
      return (RemoveProducto) super.setKey(key);
    }

    @Override
    public RemoveProducto setOauthToken(java.lang.String oauthToken) {
      return (RemoveProducto) super.setOauthToken(oauthToken);
    }

    @Override
    public RemoveProducto setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (RemoveProducto) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public RemoveProducto setQuotaUser(java.lang.String quotaUser) {
      return (RemoveProducto) super.setQuotaUser(quotaUser);
    }

    @Override
    public RemoveProducto setUserIp(java.lang.String userIp) {
      return (RemoveProducto) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.Long id;

    /**

     */
    public java.lang.Long getId() {
      return id;
    }

    public RemoveProducto setId(java.lang.Long id) {
      this.id = id;
      return this;
    }

    @Override
    public RemoveProducto set(String parameterName, Object value) {
      return (RemoveProducto) super.set(parameterName, value);
    }
  }

  /**
   * Builder for {@link Productoendpoint}.
   *
   * <p>
   * Implementation is not thread-safe.
   * </p>
   *
   * @since 1.3.0
   */
  public static final class Builder extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient.Builder {

    /**
     * Returns an instance of a new builder.
     *
     * @param transport HTTP transport, which should normally be:
     *        <ul>
     *        <li>Google App Engine:
     *        {@code com.google.api.client.extensions.appengine.http.UrlFetchTransport}</li>
     *        <li>Android: {@code newCompatibleTransport} from
     *        {@code com.google.api.client.extensions.android.http.AndroidHttp}</li>
     *        <li>Java: {@link com.google.api.client.googleapis.javanet.GoogleNetHttpTransport#newTrustedTransport()}
     *        </li>
     *        </ul>
     * @param jsonFactory JSON factory, which may be:
     *        <ul>
     *        <li>Jackson: {@code com.google.api.client.json.jackson2.JacksonFactory}</li>
     *        <li>Google GSON: {@code com.google.api.client.json.gson.GsonFactory}</li>
     *        <li>Android Honeycomb or higher:
     *        {@code com.google.api.client.extensions.android.json.AndroidJsonFactory}</li>
     *        </ul>
     * @param httpRequestInitializer HTTP request initializer or {@code null} for none
     * @since 1.7
     */
    public Builder(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
        com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
      super(
          transport,
          jsonFactory,
          DEFAULT_ROOT_URL,
          DEFAULT_SERVICE_PATH,
          httpRequestInitializer,
          false);
    }

    /** Builds a new instance of {@link Productoendpoint}. */
    @Override
    public Productoendpoint build() {
      return new Productoendpoint(this);
    }

    @Override
    public Builder setRootUrl(String rootUrl) {
      return (Builder) super.setRootUrl(rootUrl);
    }

    @Override
    public Builder setServicePath(String servicePath) {
      return (Builder) super.setServicePath(servicePath);
    }

    @Override
    public Builder setHttpRequestInitializer(com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
      return (Builder) super.setHttpRequestInitializer(httpRequestInitializer);
    }

    @Override
    public Builder setApplicationName(String applicationName) {
      return (Builder) super.setApplicationName(applicationName);
    }

    @Override
    public Builder setSuppressPatternChecks(boolean suppressPatternChecks) {
      return (Builder) super.setSuppressPatternChecks(suppressPatternChecks);
    }

    @Override
    public Builder setSuppressRequiredParameterChecks(boolean suppressRequiredParameterChecks) {
      return (Builder) super.setSuppressRequiredParameterChecks(suppressRequiredParameterChecks);
    }

    @Override
    public Builder setSuppressAllChecks(boolean suppressAllChecks) {
      return (Builder) super.setSuppressAllChecks(suppressAllChecks);
    }

    /**
     * Set the {@link ProductoendpointRequestInitializer}.
     *
     * @since 1.12
     */
    public Builder setProductoendpointRequestInitializer(
        ProductoendpointRequestInitializer productoendpointRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(productoendpointRequestInitializer);
    }

    @Override
    public Builder setGoogleClientRequestInitializer(
        com.google.api.client.googleapis.services.GoogleClientRequestInitializer googleClientRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(googleClientRequestInitializer);
    }
  }
}
