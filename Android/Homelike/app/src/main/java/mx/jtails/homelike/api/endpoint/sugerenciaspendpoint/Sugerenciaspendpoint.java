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
 * on 2014-10-03 at 08:02:46 UTC 
 * Modify at your own risk.
 */

package mx.jtails.homelike.api.endpoint.sugerenciaspendpoint;

/**
 * Service definition for Sugerenciaspendpoint (v1).
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
 * This service uses {@link SugerenciaspendpointRequestInitializer} to initialize global parameters via its
 * {@link Builder}.
 * </p>
 *
 * @since 1.3
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public class Sugerenciaspendpoint extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient {

  // Note: Leave this static initializer at the top of the file.
  static {
    com.google.api.client.util.Preconditions.checkState(
        com.google.api.client.googleapis.GoogleUtils.MAJOR_VERSION == 1 &&
        com.google.api.client.googleapis.GoogleUtils.MINOR_VERSION >= 15,
        "You are currently running with version %s of google-api-client. " +
        "You need at least version 1.15 of google-api-client to run version " +
        "1.16.0-rc of the sugerenciaspendpoint library.", com.google.api.client.googleapis.GoogleUtils.VERSION);
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
  public static final String DEFAULT_SERVICE_PATH = "sugerenciaspendpoint/v1/";

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
  public Sugerenciaspendpoint(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
      com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
    this(new Builder(transport, jsonFactory, httpRequestInitializer));
  }

  /**
   * @param builder builder
   */
  Sugerenciaspendpoint(Builder builder) {
    super(builder);
  }

  @Override
  protected void initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest<?> httpClientRequest) throws java.io.IOException {
    super.initialize(httpClientRequest);
  }

  /**
   * Create a request for the method "insertSugerenciasp".
   *
   * This request holds the parameters needed by the the sugerenciaspendpoint server.  After setting
   * any optional parameters, call the {@link InsertSugerenciasp#execute()} method to invoke the
   * remote operation.
   *
   * @param content the {@link mx.jtails.homelike.api.model.Sugerenciasp}
   * @return the request
   */
  public InsertSugerenciasp insertSugerenciasp(mx.jtails.homelike.api.model.Sugerenciasp content) throws java.io.IOException {
    InsertSugerenciasp result = new InsertSugerenciasp(content);
    initialize(result);
    return result;
  }

  public class InsertSugerenciasp extends SugerenciaspendpointRequest<mx.jtails.homelike.api.model.Sugerenciasp> {

    private static final String REST_PATH = "sugerenciasp";

    /**
     * Create a request for the method "insertSugerenciasp".
     *
     * This request holds the parameters needed by the the sugerenciaspendpoint server.  After setting
     * any optional parameters, call the {@link InsertSugerenciasp#execute()} method to invoke the
     * remote operation. <p> {@link InsertSugerenciasp#initialize(com.google.api.client.googleapis.ser
     * vices.AbstractGoogleClientRequest)} must be called to initialize this instance immediately
     * after invoking the constructor. </p>
     *
     * @param content the {@link mx.jtails.homelike.api.model.Sugerenciasp}
     * @since 1.13
     */
    protected InsertSugerenciasp(mx.jtails.homelike.api.model.Sugerenciasp content) {
      super(Sugerenciaspendpoint.this, "POST", REST_PATH, content, mx.jtails.homelike.api.model.Sugerenciasp.class);
    }

    @Override
    public InsertSugerenciasp setAlt(java.lang.String alt) {
      return (InsertSugerenciasp) super.setAlt(alt);
    }

    @Override
    public InsertSugerenciasp setFields(java.lang.String fields) {
      return (InsertSugerenciasp) super.setFields(fields);
    }

    @Override
    public InsertSugerenciasp setKey(java.lang.String key) {
      return (InsertSugerenciasp) super.setKey(key);
    }

    @Override
    public InsertSugerenciasp setOauthToken(java.lang.String oauthToken) {
      return (InsertSugerenciasp) super.setOauthToken(oauthToken);
    }

    @Override
    public InsertSugerenciasp setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (InsertSugerenciasp) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public InsertSugerenciasp setQuotaUser(java.lang.String quotaUser) {
      return (InsertSugerenciasp) super.setQuotaUser(quotaUser);
    }

    @Override
    public InsertSugerenciasp setUserIp(java.lang.String userIp) {
      return (InsertSugerenciasp) super.setUserIp(userIp);
    }

    @Override
    public InsertSugerenciasp set(String parameterName, Object value) {
      return (InsertSugerenciasp) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "listSugerenciasp".
   *
   * This request holds the parameters needed by the the sugerenciaspendpoint server.  After setting
   * any optional parameters, call the {@link ListSugerenciasp#execute()} method to invoke the remote
   * operation.
   *
   * @return the request
   */
  public ListSugerenciasp listSugerenciasp() throws java.io.IOException {
    ListSugerenciasp result = new ListSugerenciasp();
    initialize(result);
    return result;
  }

  public class ListSugerenciasp extends SugerenciaspendpointRequest<mx.jtails.homelike.api.model.CollectionResponseSugerenciasp> {

    private static final String REST_PATH = "sugerenciasp";

    /**
     * Create a request for the method "listSugerenciasp".
     *
     * This request holds the parameters needed by the the sugerenciaspendpoint server.  After setting
     * any optional parameters, call the {@link ListSugerenciasp#execute()} method to invoke the
     * remote operation. <p> {@link ListSugerenciasp#initialize(com.google.api.client.googleapis.servi
     * ces.AbstractGoogleClientRequest)} must be called to initialize this instance immediately after
     * invoking the constructor. </p>
     *
     * @since 1.13
     */
    protected ListSugerenciasp() {
      super(Sugerenciaspendpoint.this, "GET", REST_PATH, null, mx.jtails.homelike.api.model.CollectionResponseSugerenciasp.class);
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
    public ListSugerenciasp setAlt(java.lang.String alt) {
      return (ListSugerenciasp) super.setAlt(alt);
    }

    @Override
    public ListSugerenciasp setFields(java.lang.String fields) {
      return (ListSugerenciasp) super.setFields(fields);
    }

    @Override
    public ListSugerenciasp setKey(java.lang.String key) {
      return (ListSugerenciasp) super.setKey(key);
    }

    @Override
    public ListSugerenciasp setOauthToken(java.lang.String oauthToken) {
      return (ListSugerenciasp) super.setOauthToken(oauthToken);
    }

    @Override
    public ListSugerenciasp setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (ListSugerenciasp) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public ListSugerenciasp setQuotaUser(java.lang.String quotaUser) {
      return (ListSugerenciasp) super.setQuotaUser(quotaUser);
    }

    @Override
    public ListSugerenciasp setUserIp(java.lang.String userIp) {
      return (ListSugerenciasp) super.setUserIp(userIp);
    }

    @Override
    public ListSugerenciasp set(String parameterName, Object value) {
      return (ListSugerenciasp) super.set(parameterName, value);
    }
  }

  /**
   * Builder for {@link Sugerenciaspendpoint}.
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

    /** Builds a new instance of {@link Sugerenciaspendpoint}. */
    @Override
    public Sugerenciaspendpoint build() {
      return new Sugerenciaspendpoint(this);
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
     * Set the {@link SugerenciaspendpointRequestInitializer}.
     *
     * @since 1.12
     */
    public Builder setSugerenciaspendpointRequestInitializer(
        SugerenciaspendpointRequestInitializer sugerenciaspendpointRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(sugerenciaspendpointRequestInitializer);
    }

    @Override
    public Builder setGoogleClientRequestInitializer(
        com.google.api.client.googleapis.services.GoogleClientRequestInitializer googleClientRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(googleClientRequestInitializer);
    }
  }
}
