// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.mylearnings.msalwebsample;

/**
 * Object containing configuration data for the application. Spring will automatically wire the
 * values by grabbing them from application.properties file
 */
class BasicConfiguration {

    private String clientId="<Azure-ClientId>";
    private String authority="https://login.windows.net/7042450b-0e6f-49a1-a8f2-ed99de7dc292";
    private String redirectUriSignin="https://<domain-name>/aadCallbackServlet";
    private String redirectUriGraph="https://<domain-name>/index.html";
    private String secretKey="<Azure-Secret key>";
    private String msGraphEndpointHost="https://graph.microsoft.com/";

    public String getAuthority(){
        if (!authority.endsWith("/")) {
            authority += "/";
        }
        return authority;
    }

    public String getClientId() {
        return clientId;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getRedirectUriSignin() {
        return redirectUriSignin;
    }

    public void setRedirectUriSignin(String redirectUriSignin) {
        this.redirectUriSignin = redirectUriSignin;
    }

    public String getRedirectUriGraph() {
        return redirectUriGraph;
    }

    public void setRedirectUriGraph(String redirectUriGraph) {
        this.redirectUriGraph = redirectUriGraph;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public void setMsGraphEndpointHost(String msGraphEndpointHost) {
        this.msGraphEndpointHost = msGraphEndpointHost;
    }

    public String getMsGraphEndpointHost(){
        return msGraphEndpointHost;
    }
}