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
 * on 2014-11-06 at 03:32:02 UTC 
 * Modify at your own risk.
 */

package mx.jtails.homelike.api.endpoint.proveedorendpoint;

/**
 * Service definition for Proveedorendpoint (v1).
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
 * This service uses {@link ProveedorendpointRequestInitializer} to initialize global parameters via its
 * {@link Builder}.
 * </p>
 *
 * @since 1.3
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public class Proveedorendpoint extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient {

  // Note: Leave this static initializer at the top of the file.
  static {
    com.google.api.client.util.Preconditions.checkState(
        com.google.api.client.googleapis.GoogleUtils.MAJOR_VERSION == 1 &&
        com.google.api.client.googleapis.GoogleUtils.MINOR_VERSION >= 15,
        "You are currently running with version %s of google-api-client. " +
        "You need at least version 1.15 of google-api-client to run version " +
        "1.16.0-rc of the proveedorendpoint library.", com.google.api.client.googleapis.GoogleUtils.VERSION);
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
  public static final String DEFAULT_SERVICE_PATH = "proveedorendpoint/v1/";

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
  public Proveedorendpoint(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
      com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
    this(new Builder(transport, jsonFactory, httpRequestInitializer));
  }

  /**
   * @param builder builder
   */
  Proveedorendpoint(Builder builder) {
    super(builder);
  }

  @Override
  protected void initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest<?> httpClientRequest) throws java.io.IOException {
    super.initialize(httpClientRequest);
  }

  /**
   * Create a request for the method "getProveedor".
   *
   * This request holds the parameters needed by the the proveedorendpoint server.  After setting any
   * optional parameters, call the {@link GetProveedor#execute()} method to invoke the remote
   * operation.
   *
   * @param id
   * @return the request
   */
  public GetProveedor getProveedor(java.lang.Long id) throws java.io.IOException {
    GetProveedor result = new GetProveedor(id);
    initialize(result);
    return result;
  }

  public class GetProveedor extends ProveedorendpointRequest<mx.jtails.homelike.api.model.Proveedor> {

    private static final String REST_PATH = "proveedor/{id}";

    /**
     * Create a request for the method "getProveedor".
     *
     * This request holds the parameters needed by the the proveedorendpoint server.  After setting
     * any optional parameters, call the {@link GetProveedor#execute()} method to invoke the remote
     * operation. <p> {@link
     * GetProveedor#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
     * must be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @param id
     * @since 1.13
     */
    protected GetProveedor(java.lang.Long id) {
      super(Proveedorendpoint.this, "GET", REST_PATH, null, mx.jtails.homelike.api.model.Proveedor.class);
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
    public GetProveedor setAlt(java.lang.String alt) {
      return (GetProveedor) super.setAlt(alt);
    }

    @Override
    public GetProveedor setFields(java.lang.String fields) {
      return (GetProveedor) super.setFields(fields);
    }

    @Override
    public GetProveedor setKey(java.lang.String key) {
      return (GetProveedor) super.setKey(key);
    }

    @Override
    public GetProveedor setOauthToken(java.lang.String oauthToken) {
      return (GetProveedor) super.setOauthToken(oauthToken);
    }

    @Override
    public GetProveedor setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (GetProveedor) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public GetProveedor setQuotaUser(java.lang.String quotaUser) {
      return (GetProveedor) super.setQuotaUser(quotaUser);
    }

    @Override
    public GetProveedor setUserIp(java.lang.String userIp) {
      return (GetProveedor) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.Long id;

    /**

     */
    public java.lang.Long getId() {
      return id;
    }

    public GetProveedor setId(java.lang.Long id) {
      this.id = id;
      return this;
    }

    @Override
    public GetProveedor set(String parameterName, Object value) {
      return (GetProveedor) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "insertProveedor".
   *
   * This request holds the parameters needed by the the proveedorendpoint server.  After setting any
   * optional parameters, call the {@link InsertProveedor#execute()} method to invoke the remote
   * operation.
   *
   * @param content the {@link mx.jtails.homelike.api.model.Proveedor}
   * @return the request
   */
  public InsertProveedor insertProveedor(mx.jtails.homelike.api.model.Proveedor content) throws java.io.IOException {
    InsertProveedor result = new InsertProveedor(content);
    initialize(result);
    return result;
  }

  public class InsertProveedor extends ProveedorendpointRequest<mx.jtails.homelike.api.model.Proveedor> {

    private static final String REST_PATH = "proveedor";

    /**
     * Create a request for the method "insertProveedor".
     *
     * This request holds the parameters needed by the the proveedorendpoint server.  After setting
     * any optional parameters, call the {@link InsertProveedor#execute()} method to invoke the remote
     * operation. <p> {@link InsertProveedor#initialize(com.google.api.client.googleapis.services.Abst
     * ractGoogleClientRequest)} must be called to initialize this instance immediately after invoking
     * the constructor. </p>
     *
     * @param content the {@link mx.jtails.homelike.api.model.Proveedor}
     * @since 1.13
     */
    protected InsertProveedor(mx.jtails.homelike.api.model.Proveedor content) {
      super(Proveedorendpoint.this, "POST", REST_PATH, content, mx.jtails.homelike.api.model.Proveedor.class);
    }

    @Override
    public InsertProveedor setAlt(java.lang.String alt) {
      return (InsertProveedor) super.setAlt(alt);
    }

    @Override
    public InsertProveedor setFields(java.lang.String fields) {
      return (InsertProveedor) super.setFields(fields);
    }

    @Override
    public InsertProveedor setKey(java.lang.String key) {
      return (InsertProveedor) super.setKey(key);
    }

    @Override
    public InsertProveedor setOauthToken(java.lang.String oauthToken) {
      return (InsertProveedor) super.setOauthToken(oauthToken);
    }

    @Override
    public InsertProveedor setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (InsertProveedor) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public InsertProveedor setQuotaUser(java.lang.String quotaUser) {
      return (InsertProveedor) super.setQuotaUser(quotaUser);
    }

    @Override
    public InsertProveedor setUserIp(java.lang.String userIp) {
      return (InsertProveedor) super.setUserIp(userIp);
    }

    @Override
    public InsertProveedor set(String parameterName, Object value) {
      return (InsertProveedor) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "listProveedoresinRange".
   *
   * This request holds the parameters needed by the the proveedorendpoint server.  After setting any
   * optional parameters, call the {@link ListProveedoresinRange#execute()} method to invoke the
   * remote operation.
   *
   * @param idServicio
   * @param latitud
   * @param longitud
   * @return the request
   */
  public ListProveedoresinRange listProveedoresinRange(java.lang.Integer idServicio, java.lang.String latitud, java.lang.String longitud) throws java.io.IOException {
    ListProveedoresinRange result = new ListProveedoresinRange(idServicio, latitud, longitud);
    initialize(result);
    return result;
  }

  public class ListProveedoresinRange extends ProveedorendpointRequest<mx.jtails.homelike.api.model.ProveedorCollection> {

    private static final String REST_PATH = "listProveedoresinRange";

    /**
     * Create a request for the method "listProveedoresinRange".
     *
     * This request holds the parameters needed by the the proveedorendpoint server.  After setting
     * any optional parameters, call the {@link ListProveedoresinRange#execute()} method to invoke the
     * remote operation. <p> {@link ListProveedoresinRange#initialize(com.google.api.client.googleapis
     * .services.AbstractGoogleClientRequest)} must be called to initialize this instance immediately
     * after invoking the constructor. </p>
     *
     * @param idServicio
     * @param latitud
     * @param longitud
     * @since 1.13
     */
    protected ListProveedoresinRange(java.lang.Integer idServicio, java.lang.String latitud, java.lang.String longitud) {
      super(Proveedorendpoint.this, "GET", REST_PATH, null, mx.jtails.homelike.api.model.ProveedorCollection.class);
      this.idServicio = com.google.api.client.util.Preconditions.checkNotNull(idServicio, "Required parameter idServicio must be specified.");
      this.latitud = com.google.api.client.util.Preconditions.checkNotNull(latitud, "Required parameter latitud must be specified.");
      this.longitud = com.google.api.client.util.Preconditions.checkNotNull(longitud, "Required parameter longitud must be specified.");
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
    public ListProveedoresinRange setAlt(java.lang.String alt) {
      return (ListProveedoresinRange) super.setAlt(alt);
    }

    @Override
    public ListProveedoresinRange setFields(java.lang.String fields) {
      return (ListProveedoresinRange) super.setFields(fields);
    }

    @Override
    public ListProveedoresinRange setKey(java.lang.String key) {
      return (ListProveedoresinRange) super.setKey(key);
    }

    @Override
    public ListProveedoresinRange setOauthToken(java.lang.String oauthToken) {
      return (ListProveedoresinRange) super.setOauthToken(oauthToken);
    }

    @Override
    public ListProveedoresinRange setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (ListProveedoresinRange) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public ListProveedoresinRange setQuotaUser(java.lang.String quotaUser) {
      return (ListProveedoresinRange) super.setQuotaUser(quotaUser);
    }

    @Override
    public ListProveedoresinRange setUserIp(java.lang.String userIp) {
      return (ListProveedoresinRange) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.Integer idServicio;

    /**

     */
    public java.lang.Integer getIdServicio() {
      return idServicio;
    }

    public ListProveedoresinRange setIdServicio(java.lang.Integer idServicio) {
      this.idServicio = idServicio;
      return this;
    }

    @com.google.api.client.util.Key
    private java.lang.String latitud;

    /**

     */
    public java.lang.String getLatitud() {
      return latitud;
    }

    public ListProveedoresinRange setLatitud(java.lang.String latitud) {
      this.latitud = latitud;
      return this;
    }

    @com.google.api.client.util.Key
    private java.lang.String longitud;

    /**

     */
    public java.lang.String getLongitud() {
      return longitud;
    }

    public ListProveedoresinRange setLongitud(java.lang.String longitud) {
      this.longitud = longitud;
      return this;
    }

    @Override
    public ListProveedoresinRange set(String parameterName, Object value) {
      return (ListProveedoresinRange) super.set(parameterName, value);
    }
  }

  /**
   * Builder for {@link Proveedorendpoint}.
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

    /** Builds a new instance of {@link Proveedorendpoint}. */
    @Override
    public Proveedorendpoint build() {
      return new Proveedorendpoint(this);
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
     * Set the {@link ProveedorendpointRequestInitializer}.
     *
     * @since 1.12
     */
    public Builder setProveedorendpointRequestInitializer(
        ProveedorendpointRequestInitializer proveedorendpointRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(proveedorendpointRequestInitializer);
    }

    @Override
    public Builder setGoogleClientRequestInitializer(
        com.google.api.client.googleapis.services.GoogleClientRequestInitializer googleClientRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(googleClientRequestInitializer);
    }
  }
}
