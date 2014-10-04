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
 * on 2014-10-03 at 08:01:45 UTC 
 * Modify at your own risk.
 */

package mx.jtails.homelike.api.endpoint.cuentaendpoint;

/**
 * Service definition for Cuentaendpoint (v1).
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
 * This service uses {@link CuentaendpointRequestInitializer} to initialize global parameters via its
 * {@link Builder}.
 * </p>
 *
 * @since 1.3
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public class Cuentaendpoint extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient {

  // Note: Leave this static initializer at the top of the file.
  static {
    com.google.api.client.util.Preconditions.checkState(
        com.google.api.client.googleapis.GoogleUtils.MAJOR_VERSION == 1 &&
        com.google.api.client.googleapis.GoogleUtils.MINOR_VERSION >= 15,
        "You are currently running with version %s of google-api-client. " +
        "You need at least version 1.15 of google-api-client to run version " +
        "1.16.0-rc of the cuentaendpoint library.", com.google.api.client.googleapis.GoogleUtils.VERSION);
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
  public static final String DEFAULT_SERVICE_PATH = "cuentaendpoint/v1/";

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
  public Cuentaendpoint(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
      com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
    this(new Builder(transport, jsonFactory, httpRequestInitializer));
  }

  /**
   * @param builder builder
   */
  Cuentaendpoint(Builder builder) {
    super(builder);
  }

  @Override
  protected void initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest<?> httpClientRequest) throws java.io.IOException {
    super.initialize(httpClientRequest);
  }

  /**
   * Create a request for the method "getClienteswithPedidoByProveedor".
   *
   * This request holds the parameters needed by the the cuentaendpoint server.  After setting any
   * optional parameters, call the {@link GetClienteswithPedidoByProveedor#execute()} method to invoke
   * the remote operation.
   *
   * @return the request
   */
  public GetClienteswithPedidoByProveedor getClienteswithPedidoByProveedor() throws java.io.IOException {
    GetClienteswithPedidoByProveedor result = new GetClienteswithPedidoByProveedor();
    initialize(result);
    return result;
  }

  public class GetClienteswithPedidoByProveedor extends CuentaendpointRequest<mx.jtails.homelike.api.model.Proveedor> {

    private static final String REST_PATH = "getClienteswithPedidoByProveedor";

    /**
     * Create a request for the method "getClienteswithPedidoByProveedor".
     *
     * This request holds the parameters needed by the the cuentaendpoint server.  After setting any
     * optional parameters, call the {@link GetClienteswithPedidoByProveedor#execute()} method to
     * invoke the remote operation. <p> {@link GetClienteswithPedidoByProveedor#initialize(com.google.
     * api.client.googleapis.services.AbstractGoogleClientRequest)} must be called to initialize this
     * instance immediately after invoking the constructor. </p>
     *
     * @since 1.13
     */
    protected GetClienteswithPedidoByProveedor() {
      super(Cuentaendpoint.this, "GET", REST_PATH, null, mx.jtails.homelike.api.model.Proveedor.class);
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
    public GetClienteswithPedidoByProveedor setAlt(java.lang.String alt) {
      return (GetClienteswithPedidoByProveedor) super.setAlt(alt);
    }

    @Override
    public GetClienteswithPedidoByProveedor setFields(java.lang.String fields) {
      return (GetClienteswithPedidoByProveedor) super.setFields(fields);
    }

    @Override
    public GetClienteswithPedidoByProveedor setKey(java.lang.String key) {
      return (GetClienteswithPedidoByProveedor) super.setKey(key);
    }

    @Override
    public GetClienteswithPedidoByProveedor setOauthToken(java.lang.String oauthToken) {
      return (GetClienteswithPedidoByProveedor) super.setOauthToken(oauthToken);
    }

    @Override
    public GetClienteswithPedidoByProveedor setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (GetClienteswithPedidoByProveedor) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public GetClienteswithPedidoByProveedor setQuotaUser(java.lang.String quotaUser) {
      return (GetClienteswithPedidoByProveedor) super.setQuotaUser(quotaUser);
    }

    @Override
    public GetClienteswithPedidoByProveedor setUserIp(java.lang.String userIp) {
      return (GetClienteswithPedidoByProveedor) super.setUserIp(userIp);
    }

    @Override
    public GetClienteswithPedidoByProveedor set(String parameterName, Object value) {
      return (GetClienteswithPedidoByProveedor) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "getCuenta".
   *
   * This request holds the parameters needed by the the cuentaendpoint server.  After setting any
   * optional parameters, call the {@link GetCuenta#execute()} method to invoke the remote operation.
   *
   * @param id
   * @return the request
   */
  public GetCuenta getCuenta(java.lang.Long id) throws java.io.IOException {
    GetCuenta result = new GetCuenta(id);
    initialize(result);
    return result;
  }

  public class GetCuenta extends CuentaendpointRequest<mx.jtails.homelike.api.model.Cuenta> {

    private static final String REST_PATH = "getCuenta";

    /**
     * Create a request for the method "getCuenta".
     *
     * This request holds the parameters needed by the the cuentaendpoint server.  After setting any
     * optional parameters, call the {@link GetCuenta#execute()} method to invoke the remote
     * operation. <p> {@link
     * GetCuenta#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
     * must be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @param id
     * @since 1.13
     */
    protected GetCuenta(java.lang.Long id) {
      super(Cuentaendpoint.this, "GET", REST_PATH, null, mx.jtails.homelike.api.model.Cuenta.class);
      this.id = com.google.api.client.util.Preconditions.checkNotNull(id, "Required parameter id must be specified.");
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
    public GetCuenta setAlt(java.lang.String alt) {
      return (GetCuenta) super.setAlt(alt);
    }

    @Override
    public GetCuenta setFields(java.lang.String fields) {
      return (GetCuenta) super.setFields(fields);
    }

    @Override
    public GetCuenta setKey(java.lang.String key) {
      return (GetCuenta) super.setKey(key);
    }

    @Override
    public GetCuenta setOauthToken(java.lang.String oauthToken) {
      return (GetCuenta) super.setOauthToken(oauthToken);
    }

    @Override
    public GetCuenta setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (GetCuenta) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public GetCuenta setQuotaUser(java.lang.String quotaUser) {
      return (GetCuenta) super.setQuotaUser(quotaUser);
    }

    @Override
    public GetCuenta setUserIp(java.lang.String userIp) {
      return (GetCuenta) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.Long id;

    /**

     */
    public java.lang.Long getId() {
      return id;
    }

    public GetCuenta setId(java.lang.Long id) {
      this.id = id;
      return this;
    }

    @Override
    public GetCuenta set(String parameterName, Object value) {
      return (GetCuenta) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "getCuentaByUser".
   *
   * This request holds the parameters needed by the the cuentaendpoint server.  After setting any
   * optional parameters, call the {@link GetCuentaByUser#execute()} method to invoke the remote
   * operation.
   *
   * @return the request
   */
  public GetCuentaByUser getCuentaByUser() throws java.io.IOException {
    GetCuentaByUser result = new GetCuentaByUser();
    initialize(result);
    return result;
  }

  public class GetCuentaByUser extends CuentaendpointRequest<mx.jtails.homelike.api.model.Cuenta> {

    private static final String REST_PATH = "getCuentaByUser";

    /**
     * Create a request for the method "getCuentaByUser".
     *
     * This request holds the parameters needed by the the cuentaendpoint server.  After setting any
     * optional parameters, call the {@link GetCuentaByUser#execute()} method to invoke the remote
     * operation. <p> {@link GetCuentaByUser#initialize(com.google.api.client.googleapis.services.Abst
     * ractGoogleClientRequest)} must be called to initialize this instance immediately after invoking
     * the constructor. </p>
     *
     * @since 1.13
     */
    protected GetCuentaByUser() {
      super(Cuentaendpoint.this, "GET", REST_PATH, null, mx.jtails.homelike.api.model.Cuenta.class);
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
    public GetCuentaByUser setAlt(java.lang.String alt) {
      return (GetCuentaByUser) super.setAlt(alt);
    }

    @Override
    public GetCuentaByUser setFields(java.lang.String fields) {
      return (GetCuentaByUser) super.setFields(fields);
    }

    @Override
    public GetCuentaByUser setKey(java.lang.String key) {
      return (GetCuentaByUser) super.setKey(key);
    }

    @Override
    public GetCuentaByUser setOauthToken(java.lang.String oauthToken) {
      return (GetCuentaByUser) super.setOauthToken(oauthToken);
    }

    @Override
    public GetCuentaByUser setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (GetCuentaByUser) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public GetCuentaByUser setQuotaUser(java.lang.String quotaUser) {
      return (GetCuentaByUser) super.setQuotaUser(quotaUser);
    }

    @Override
    public GetCuentaByUser setUserIp(java.lang.String userIp) {
      return (GetCuentaByUser) super.setUserIp(userIp);
    }

    @Override
    public GetCuentaByUser set(String parameterName, Object value) {
      return (GetCuentaByUser) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "insertCuenta".
   *
   * This request holds the parameters needed by the the cuentaendpoint server.  After setting any
   * optional parameters, call the {@link InsertCuenta#execute()} method to invoke the remote
   * operation.
   *
   * @param content the {@link mx.jtails.homelike.api.model.Cuenta}
   * @return the request
   */
  public InsertCuenta insertCuenta(mx.jtails.homelike.api.model.Cuenta content) throws java.io.IOException {
    InsertCuenta result = new InsertCuenta(content);
    initialize(result);
    return result;
  }

  public class InsertCuenta extends CuentaendpointRequest<mx.jtails.homelike.api.model.Cuenta> {

    private static final String REST_PATH = "cuenta";

    /**
     * Create a request for the method "insertCuenta".
     *
     * This request holds the parameters needed by the the cuentaendpoint server.  After setting any
     * optional parameters, call the {@link InsertCuenta#execute()} method to invoke the remote
     * operation. <p> {@link
     * InsertCuenta#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
     * must be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @param content the {@link mx.jtails.homelike.api.model.Cuenta}
     * @since 1.13
     */
    protected InsertCuenta(mx.jtails.homelike.api.model.Cuenta content) {
      super(Cuentaendpoint.this, "POST", REST_PATH, content, mx.jtails.homelike.api.model.Cuenta.class);
    }

    @Override
    public InsertCuenta setAlt(java.lang.String alt) {
      return (InsertCuenta) super.setAlt(alt);
    }

    @Override
    public InsertCuenta setFields(java.lang.String fields) {
      return (InsertCuenta) super.setFields(fields);
    }

    @Override
    public InsertCuenta setKey(java.lang.String key) {
      return (InsertCuenta) super.setKey(key);
    }

    @Override
    public InsertCuenta setOauthToken(java.lang.String oauthToken) {
      return (InsertCuenta) super.setOauthToken(oauthToken);
    }

    @Override
    public InsertCuenta setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (InsertCuenta) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public InsertCuenta setQuotaUser(java.lang.String quotaUser) {
      return (InsertCuenta) super.setQuotaUser(quotaUser);
    }

    @Override
    public InsertCuenta setUserIp(java.lang.String userIp) {
      return (InsertCuenta) super.setUserIp(userIp);
    }

    @Override
    public InsertCuenta set(String parameterName, Object value) {
      return (InsertCuenta) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "listClientesinRange".
   *
   * This request holds the parameters needed by the the cuentaendpoint server.  After setting any
   * optional parameters, call the {@link ListClientesinRange#execute()} method to invoke the remote
   * operation.
   *
   * @return the request
   */
  public ListClientesinRange listClientesinRange() throws java.io.IOException {
    ListClientesinRange result = new ListClientesinRange();
    initialize(result);
    return result;
  }

  public class ListClientesinRange extends CuentaendpointRequest<mx.jtails.homelike.api.model.CuentaCollection> {

    private static final String REST_PATH = "listClientesinRange";

    /**
     * Create a request for the method "listClientesinRange".
     *
     * This request holds the parameters needed by the the cuentaendpoint server.  After setting any
     * optional parameters, call the {@link ListClientesinRange#execute()} method to invoke the remote
     * operation. <p> {@link ListClientesinRange#initialize(com.google.api.client.googleapis.services.
     * AbstractGoogleClientRequest)} must be called to initialize this instance immediately after
     * invoking the constructor. </p>
     *
     * @since 1.13
     */
    protected ListClientesinRange() {
      super(Cuentaendpoint.this, "GET", REST_PATH, null, mx.jtails.homelike.api.model.CuentaCollection.class);
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
    public ListClientesinRange setAlt(java.lang.String alt) {
      return (ListClientesinRange) super.setAlt(alt);
    }

    @Override
    public ListClientesinRange setFields(java.lang.String fields) {
      return (ListClientesinRange) super.setFields(fields);
    }

    @Override
    public ListClientesinRange setKey(java.lang.String key) {
      return (ListClientesinRange) super.setKey(key);
    }

    @Override
    public ListClientesinRange setOauthToken(java.lang.String oauthToken) {
      return (ListClientesinRange) super.setOauthToken(oauthToken);
    }

    @Override
    public ListClientesinRange setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (ListClientesinRange) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public ListClientesinRange setQuotaUser(java.lang.String quotaUser) {
      return (ListClientesinRange) super.setQuotaUser(quotaUser);
    }

    @Override
    public ListClientesinRange setUserIp(java.lang.String userIp) {
      return (ListClientesinRange) super.setUserIp(userIp);
    }

    @Override
    public ListClientesinRange set(String parameterName, Object value) {
      return (ListClientesinRange) super.set(parameterName, value);
    }
  }

  /**
   * Builder for {@link Cuentaendpoint}.
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

    /** Builds a new instance of {@link Cuentaendpoint}. */
    @Override
    public Cuentaendpoint build() {
      return new Cuentaendpoint(this);
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
     * Set the {@link CuentaendpointRequestInitializer}.
     *
     * @since 1.12
     */
    public Builder setCuentaendpointRequestInitializer(
        CuentaendpointRequestInitializer cuentaendpointRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(cuentaendpointRequestInitializer);
    }

    @Override
    public Builder setGoogleClientRequestInitializer(
        com.google.api.client.googleapis.services.GoogleClientRequestInitializer googleClientRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(googleClientRequestInitializer);
    }
  }
}
