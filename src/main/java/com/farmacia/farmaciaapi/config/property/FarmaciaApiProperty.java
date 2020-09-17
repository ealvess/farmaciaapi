package com.farmacia.farmaciaapi.config.property;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("farmacia")
public class FarmaciaApiProperty {

	private String originPermitida = "http://localhost:8000";

	private final Seguranca seguranca = new Seguranca();
	
	private final Auth auth = new Auth();
    private final OAuth2 oauth2 = new OAuth2();


	public Seguranca getSeguranca() {
		return seguranca;
	}

	public String getOriginPermitida() {
		return originPermitida;
	}

	public void setOriginPermitida(String originPermitida) {
		this.originPermitida = originPermitida;
	}

	public static class Seguranca {

		private boolean enableHttps;

		public boolean isEnableHttps() {
			return enableHttps;
		}

		public void setEnableHttps(boolean enableHttps) {
			this.enableHttps = enableHttps;
		}

	}
	
	 public static class Auth {
	        private String tokenSecret;
	        private long tokenExpirationMsec;

	        public String getTokenSecret() {
	            return tokenSecret;
	        }

	        public void setTokenSecret(String tokenSecret) {
	            this.tokenSecret = tokenSecret;
	        }

	        public long getTokenExpirationMsec() {
	            return tokenExpirationMsec;
	        }

	        public void setTokenExpirationMsec(long tokenExpirationMsec) {
	            this.tokenExpirationMsec = tokenExpirationMsec;
	        }
	    }

	    public static final class OAuth2 {
	        private List<String> authorizedRedirectUris = new ArrayList<>();

	        public List<String> getAuthorizedRedirectUris() {
	            return authorizedRedirectUris;
	        }

	        public OAuth2 authorizedRedirectUris(List<String> authorizedRedirectUris) {
	            this.authorizedRedirectUris = authorizedRedirectUris;
	            return this;
	        }
	    }

	    public Auth getAuth() {
	        return auth;
	    }

	    public OAuth2 getOauth2() {
	        return oauth2;
	    }

}
