package pl.krzysiek.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.persistence.*;

@Entity
@Table(name = "allegro_token")
public class AllegroResponseApi {

    @SerializedName("id")
    private int id;
    @SerializedName("access_token")
    private String accessToken;
    @SerializedName("token_type")
    private String tokenType;
    @SerializedName("refresh_token")
    private String refreshToken;
    @SerializedName("expires_in")
    private Integer expiresIn;
    @SerializedName("scope")
    private String scope;
    @SerializedName("jti")
    private String jti;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    @Basic
    @Column(name = "access_token")
    public String getAccessToken() {
        return accessToken;
    }


    @Basic
    @Column(name = "token_type")
    public String getTokenType() {
        return tokenType;
    }

    @Basic
    @Column(name = "refresh_token")
    public String getRefreshToken() {
        return refreshToken;
    }


    @Basic
    @Column(name = "expires_in")
    public Integer getExpiresIn() {
        return expiresIn;
    }


    @Basic
    @Column(name = "scope")
    public String getScope() {
        return scope;
    }


    @Basic
    @Column(name = "jti")
    public String getJti() {
        return jti;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public void setJti(String jti) {
        this.jti = jti;
    }

    public AllegroResponseApi(String accessToken, String tokenType, String refreshToken, Integer expiresIn, String scope, String jti) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.refreshToken = refreshToken;
        this.expiresIn = expiresIn;
        this.scope = scope;
        this.jti = jti;
    }

    public AllegroResponseApi() {
    }
}