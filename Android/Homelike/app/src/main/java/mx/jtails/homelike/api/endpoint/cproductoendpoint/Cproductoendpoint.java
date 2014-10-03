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
 * on 2014-10-03 at 04:13:35 UTC 
 * Modify at your own risk.
 */

package mx.jtails.homelike.api.endpoint.cproductoendpoint;

/**
 * Service definition for Cproductoendpoint (v1).
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
 * This service uses {@link CproductoendpointRequestInitializer} to initialize global parameters via its
 * {@link Builder}.
 * </p>
 *
 * @since 1.3
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public class Cproductoendpoint extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient {

  // Note: Leave this static initializer at the top of the file.
  static {
    com.google.api.client.util.Preconditions.checkState(
        com.google.api.client.googleapis.GoogleUtils.MAJOR_VERSION == 1 &&
        com.google.api.client.googleapis.GoogleUtils.MINOR_VERSION >= 15,
        "You are currently running with version %s of google-api-client. " +
        "You need at least version 1.15 of google-api-client to run version " +
        "1.16.0-rc of the cproductoendpoint library.", com.google.api.client.googleapis.GoogleUtils.VERSION);
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
  public static final String DEFAULT_SERVICE_PATH = "cproductoendpoint/v1/";

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
  public Cproductoendpoint(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
      com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
    this(new Builder(transport, jsonFactory, httpRequestInitializer));
  }

  /**
   * @param builder builder
   */
  Cproductoendpoint(Builder builder) {
    super(builder);
  }

  @Override
  protected void initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest<?> httpClientRequest) throws java.io.IOException {
    super.initialize(httpClientRequest);
  }

  /**
   * Create a request for the method "listCProducto".
   *
   * This request holds the parameters needed by the the cproductoendpoint server.  After setting any
   * optional parameters, call the {@link ListCProducto#execute()} method to invoke the remote
   * operation.
   *
   * @return the request
   */
  public ListCProducto listCProducto() throws java.io.IOException {
    ListCProducto result = new ListCProducto();
    initialize(result);
    return result;
  }

  public class ListCProducto extends CproductoendpointRequest<mx.jtails.homelike.api.model.CollectionResponseCProducto> {

    private static final String REST_PATH = "cproducto";

    /**
     * Create a request for the method "listCProducto".
     *
     * This request holds the parameters needed by the the cproductoendpoint server.  After setting
     * any optional parameters, call the {@link ListCProducto#execute()} method to invoke the remote
     * operation. <p> {@link ListCProducto#initialize(com.google.api.client.googleapis.services.Abstra
     * ctGoogleClientRequest)} must be called to initialize this instance immediately after invoking
     * the constructor. </p>
     *
     * @since 1.13
     */
    protected ListCProducto() {
      super(Cproductoendpoint.this, "GET", REST_PATH, null, mx.jtails.homelike.api.model.CollectionResponseCProducto.class);
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
    public ListCProducto setAlt(java.lang.String alt) {
      return (ListCProducto) super.setAlt(alt);
    }

    @Override
    public ListCProducto setFields(java.lang.String fields) {
      return (ListCProducto) super.setFields(fields);
    }

    @Override
    public ListCProducto setKey(java.lang.String key) {
      return (ListCProducto) super.setKey(key);
    }

    @Override
    public ListCProducto setOauthToken(java.lang.String oauthToken) {
      return (ListCProducto) super.setOauthToken(oauthToken);
    }

    @Override
    public ListCProducto setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (ListCProducto) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public ListCProducto setQuotaUser(java.lang.String quotaUser) {
      return (ListCProducto) super.setQuotaUser(quotaUser);
    }

    @Override
    public ListCProducto setUserIp(java.lang.String userIp) {
      return (ListCProducto) super.setUserIp(userIp);
    }

    @Override
    public ListCProducto set(String parameterName, Object value) {
      return (ListCProducto) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "listCProductoByServicio".
   *
   * This request holds the parameters needed by the the cproductoendpoint server.  After setting any
   * optional parameters, call the {@link ListCProductoByServicio#execute()} method to invoke the
   * remote operation.
   *
   * @return the request
   */
  public ListCProductoByServicio listCProductoByServicio() throws java.io.IOException {
    ListCProductoByServicio result = new ListCProductoByServicio();
    initialize(result);
    return result;
  }

  public class ListCProductoByServicio extends CproductoendpointRequest<mx.jtails.homelike.api.model.CProductoCollection> {

    private static final String REST_PATH = "listCProductoByServicio";

    /**
     * Create a request for the method "listCProductoByServicio".
     *
     * This request holds the parameters needed by the the cproductoendpoint server.  After setting
     * any optional parameters, call the {@link ListCProductoByServicio#execute()} method to invoke
     * the remote operation. <p> {@link ListCProductoByServicio#initialize(com.google.api.client.googl
     * eapis.services.AbstractGoogleClientRequest)} must be called to initialize this instance
     * immediately after invoking the constructor. </p>
     *
     * @since 1.13
     */
    protected ListCProductoByServicio() {
      super(Cproductoendpoint.this, "GET", REST_PATH, null, mx.jtails.homelike.api.model.CProductoCollection.class);
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
    public ListCProductoByServicio setAlt(java.lang.String alt) {
      return (ListCProductoByServicio) super.setAlt(alt);
    }

    @Override
    public ListCProductoByServicio setFields(java.lang.String fields) {
      return (ListCProductoByServicio) super.setFields(fields);
    }

    @Override
    public ListCProductoByServicio setKey(java.lang.String key) {
      return (ListCProductoByServicio) super.setKey(key);
    }

    @Override
    public ListCProductoByServicio setOauthToken(java.lang.String oauthToken) {
      return (ListCProductoByServicio) super.setOauthToken(oauthToken);
    }

    @Override
    public ListCProductoByServicio setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (ListCProductoByServicio) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public ListCProductoByServicio setQuotaUser(java.lang.String quotaUser) {
      return (ListCProductoByServicio) super.setQuotaUser(quotaUser);
    }

    @Override
    public ListCProductoByServicio setUserIp(java.lang.String userIp) {
      return (ListCProductoByServicio) super.setUserIp(userIp);
    }

    @Override
    public ListCProductoByServicio set(String parameterName, Object value) {
      return (ListCProductoByServicio) super.set(parameterName, value);
    }
  }

  /**
   * Builder for {@link Cproductoendpoint}.
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

    /** Builds a new instance of {@link Cproductoendpoint}. */
    @Override
    public Cproductoendpoint build() {
      return new Cproductoendpoint(this);
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
     * Set the {@link CproductoendpointRequestInitializer}.
     *
     * @since 1.12
     */
    public Builder setCproductoendpointRequestInitializer(
        CproductoendpointRequestInitializer cproductoendpointRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(cproductoendpointRequestInitializer);
    }

    @Override
    public Builder setGoogleClientRequestInitializer(
        com.google.api.client.googleapis.services.GoogleClientRequestInitializer googleClientRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(googleClientRequestInitializer);
    }
  }
}
