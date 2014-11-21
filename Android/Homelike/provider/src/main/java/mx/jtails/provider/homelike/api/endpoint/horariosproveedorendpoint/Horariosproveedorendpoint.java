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

package mx.jtails.provider.homelike.api.endpoint.horariosproveedorendpoint;

/**
 * Service definition for Horariosproveedorendpoint (v1).
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
 * This service uses {@link HorariosproveedorendpointRequestInitializer} to initialize global parameters via its
 * {@link Builder}.
 * </p>
 *
 * @since 1.3
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public class Horariosproveedorendpoint extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient {

  // Note: Leave this static initializer at the top of the file.
  static {
    com.google.api.client.util.Preconditions.checkState(
        com.google.api.client.googleapis.GoogleUtils.MAJOR_VERSION == 1 &&
        com.google.api.client.googleapis.GoogleUtils.MINOR_VERSION >= 15,
        "You are currently running with version %s of google-api-client. " +
        "You need at least version 1.15 of google-api-client to run version " +
        "1.16.0-rc of the horariosproveedorendpoint library.", com.google.api.client.googleapis.GoogleUtils.VERSION);
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
  public static final String DEFAULT_SERVICE_PATH = "horariosproveedorendpoint/v1/";

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
  public Horariosproveedorendpoint(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
      com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
    this(new Builder(transport, jsonFactory, httpRequestInitializer));
  }

  /**
   * @param builder builder
   */
  Horariosproveedorendpoint(Builder builder) {
    super(builder);
  }

  @Override
  protected void initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest<?> httpClientRequest) throws java.io.IOException {
    super.initialize(httpClientRequest);
  }

  /**
   * Create a request for the method "deleteHorariosProveedor".
   *
   * This request holds the parameters needed by the the horariosproveedorendpoint server.  After
   * setting any optional parameters, call the {@link DeleteHorariosProveedor#execute()} method to
   * invoke the remote operation.
   *
   * @param content the {@link mx.jtails.provider.homelike.api.model.HorariosProveedor}
   * @return the request
   */
  public DeleteHorariosProveedor deleteHorariosProveedor(mx.jtails.provider.homelike.api.model.HorariosProveedor content) throws java.io.IOException {
    DeleteHorariosProveedor result = new DeleteHorariosProveedor(content);
    initialize(result);
    return result;
  }

  public class DeleteHorariosProveedor extends HorariosproveedorendpointRequest<Void> {

    private static final String REST_PATH = "deleteHorariosProveedor";

    /**
     * Create a request for the method "deleteHorariosProveedor".
     *
     * This request holds the parameters needed by the the horariosproveedorendpoint server.  After
     * setting any optional parameters, call the {@link DeleteHorariosProveedor#execute()} method to
     * invoke the remote operation. <p> {@link DeleteHorariosProveedor#initialize(com.google.api.clien
     * t.googleapis.services.AbstractGoogleClientRequest)} must be called to initialize this instance
     * immediately after invoking the constructor. </p>
     *
     * @param content the {@link mx.jtails.provider.homelike.api.model.HorariosProveedor}
     * @since 1.13
     */
    protected DeleteHorariosProveedor(mx.jtails.provider.homelike.api.model.HorariosProveedor content) {
      super(Horariosproveedorendpoint.this, "DELETE", REST_PATH, content, Void.class);
    }

    @Override
    public DeleteHorariosProveedor setAlt(java.lang.String alt) {
      return (DeleteHorariosProveedor) super.setAlt(alt);
    }

    @Override
    public DeleteHorariosProveedor setFields(java.lang.String fields) {
      return (DeleteHorariosProveedor) super.setFields(fields);
    }

    @Override
    public DeleteHorariosProveedor setKey(java.lang.String key) {
      return (DeleteHorariosProveedor) super.setKey(key);
    }

    @Override
    public DeleteHorariosProveedor setOauthToken(java.lang.String oauthToken) {
      return (DeleteHorariosProveedor) super.setOauthToken(oauthToken);
    }

    @Override
    public DeleteHorariosProveedor setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (DeleteHorariosProveedor) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public DeleteHorariosProveedor setQuotaUser(java.lang.String quotaUser) {
      return (DeleteHorariosProveedor) super.setQuotaUser(quotaUser);
    }

    @Override
    public DeleteHorariosProveedor setUserIp(java.lang.String userIp) {
      return (DeleteHorariosProveedor) super.setUserIp(userIp);
    }

    @Override
    public DeleteHorariosProveedor set(String parameterName, Object value) {
      return (DeleteHorariosProveedor) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "getHorariosProveedor".
   *
   * This request holds the parameters needed by the the horariosproveedorendpoint server.  After
   * setting any optional parameters, call the {@link GetHorariosProveedor#execute()} method to invoke
   * the remote operation.
   *
   * @return the request
   */
  public GetHorariosProveedor getHorariosProveedor() throws java.io.IOException {
    GetHorariosProveedor result = new GetHorariosProveedor();
    initialize(result);
    return result;
  }

  public class GetHorariosProveedor extends HorariosproveedorendpointRequest<mx.jtails.provider.homelike.api.model.HorariosProveedorCollection> {

    private static final String REST_PATH = "getHorariosProveedor";

    /**
     * Create a request for the method "getHorariosProveedor".
     *
     * This request holds the parameters needed by the the horariosproveedorendpoint server.  After
     * setting any optional parameters, call the {@link GetHorariosProveedor#execute()} method to
     * invoke the remote operation. <p> {@link GetHorariosProveedor#initialize(com.google.api.client.g
     * oogleapis.services.AbstractGoogleClientRequest)} must be called to initialize this instance
     * immediately after invoking the constructor. </p>
     *
     * @since 1.13
     */
    protected GetHorariosProveedor() {
      super(Horariosproveedorendpoint.this, "GET", REST_PATH, null, mx.jtails.provider.homelike.api.model.HorariosProveedorCollection.class);
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
    public GetHorariosProveedor setAlt(java.lang.String alt) {
      return (GetHorariosProveedor) super.setAlt(alt);
    }

    @Override
    public GetHorariosProveedor setFields(java.lang.String fields) {
      return (GetHorariosProveedor) super.setFields(fields);
    }

    @Override
    public GetHorariosProveedor setKey(java.lang.String key) {
      return (GetHorariosProveedor) super.setKey(key);
    }

    @Override
    public GetHorariosProveedor setOauthToken(java.lang.String oauthToken) {
      return (GetHorariosProveedor) super.setOauthToken(oauthToken);
    }

    @Override
    public GetHorariosProveedor setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (GetHorariosProveedor) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public GetHorariosProveedor setQuotaUser(java.lang.String quotaUser) {
      return (GetHorariosProveedor) super.setQuotaUser(quotaUser);
    }

    @Override
    public GetHorariosProveedor setUserIp(java.lang.String userIp) {
      return (GetHorariosProveedor) super.setUserIp(userIp);
    }

    @Override
    public GetHorariosProveedor set(String parameterName, Object value) {
      return (GetHorariosProveedor) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "insertHorariosProveedor".
   *
   * This request holds the parameters needed by the the horariosproveedorendpoint server.  After
   * setting any optional parameters, call the {@link InsertHorariosProveedor#execute()} method to
   * invoke the remote operation.
   *
   * @param content the {@link mx.jtails.provider.homelike.api.model.HorariosProveedor}
   * @return the request
   */
  public InsertHorariosProveedor insertHorariosProveedor(mx.jtails.provider.homelike.api.model.HorariosProveedor content) throws java.io.IOException {
    InsertHorariosProveedor result = new InsertHorariosProveedor(content);
    initialize(result);
    return result;
  }

  public class InsertHorariosProveedor extends HorariosproveedorendpointRequest<mx.jtails.provider.homelike.api.model.HorariosProveedor> {

    private static final String REST_PATH = "horariosproveedor";

    /**
     * Create a request for the method "insertHorariosProveedor".
     *
     * This request holds the parameters needed by the the horariosproveedorendpoint server.  After
     * setting any optional parameters, call the {@link InsertHorariosProveedor#execute()} method to
     * invoke the remote operation. <p> {@link InsertHorariosProveedor#initialize(com.google.api.clien
     * t.googleapis.services.AbstractGoogleClientRequest)} must be called to initialize this instance
     * immediately after invoking the constructor. </p>
     *
     * @param content the {@link mx.jtails.provider.homelike.api.model.HorariosProveedor}
     * @since 1.13
     */
    protected InsertHorariosProveedor(mx.jtails.provider.homelike.api.model.HorariosProveedor content) {
      super(Horariosproveedorendpoint.this, "POST", REST_PATH, content, mx.jtails.provider.homelike.api.model.HorariosProveedor.class);
    }

    @Override
    public InsertHorariosProveedor setAlt(java.lang.String alt) {
      return (InsertHorariosProveedor) super.setAlt(alt);
    }

    @Override
    public InsertHorariosProveedor setFields(java.lang.String fields) {
      return (InsertHorariosProveedor) super.setFields(fields);
    }

    @Override
    public InsertHorariosProveedor setKey(java.lang.String key) {
      return (InsertHorariosProveedor) super.setKey(key);
    }

    @Override
    public InsertHorariosProveedor setOauthToken(java.lang.String oauthToken) {
      return (InsertHorariosProveedor) super.setOauthToken(oauthToken);
    }

    @Override
    public InsertHorariosProveedor setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (InsertHorariosProveedor) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public InsertHorariosProveedor setQuotaUser(java.lang.String quotaUser) {
      return (InsertHorariosProveedor) super.setQuotaUser(quotaUser);
    }

    @Override
    public InsertHorariosProveedor setUserIp(java.lang.String userIp) {
      return (InsertHorariosProveedor) super.setUserIp(userIp);
    }

    @Override
    public InsertHorariosProveedor set(String parameterName, Object value) {
      return (InsertHorariosProveedor) super.set(parameterName, value);
    }
  }

  /**
   * Builder for {@link Horariosproveedorendpoint}.
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

    /** Builds a new instance of {@link Horariosproveedorendpoint}. */
    @Override
    public Horariosproveedorendpoint build() {
      return new Horariosproveedorendpoint(this);
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
     * Set the {@link HorariosproveedorendpointRequestInitializer}.
     *
     * @since 1.12
     */
    public Builder setHorariosproveedorendpointRequestInitializer(
        HorariosproveedorendpointRequestInitializer horariosproveedorendpointRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(horariosproveedorendpointRequestInitializer);
    }

    @Override
    public Builder setGoogleClientRequestInitializer(
        com.google.api.client.googleapis.services.GoogleClientRequestInitializer googleClientRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(googleClientRequestInitializer);
    }
  }
}
