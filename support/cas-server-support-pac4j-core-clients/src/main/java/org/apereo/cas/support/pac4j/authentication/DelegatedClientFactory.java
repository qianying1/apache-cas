package org.apereo.cas.support.pac4j.authentication;

import com.github.scribejava.core.model.Verb;
import com.nimbusds.jose.JWSAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apereo.cas.configuration.model.support.pac4j.Pac4jDelegatedAuthenticationProperties;
import org.pac4j.cas.client.CasClient;
import org.pac4j.cas.config.CasConfiguration;
import org.pac4j.cas.config.CasProtocol;
import org.pac4j.core.client.BaseClient;
import org.pac4j.oauth.client.BitbucketClient;
import org.pac4j.oauth.client.DropBoxClient;
import org.pac4j.oauth.client.FacebookClient;
import org.pac4j.oauth.client.FoursquareClient;
import org.pac4j.oauth.client.GenericOAuth20Client;
import org.pac4j.oauth.client.GitHubClient;
import org.pac4j.oauth.client.Google2Client;
import org.pac4j.oauth.client.LinkedIn2Client;
import org.pac4j.oauth.client.OrcidClient;
import org.pac4j.oauth.client.PayPalClient;
import org.pac4j.oauth.client.TwitterClient;
import org.pac4j.oauth.client.WindowsLiveClient;
import org.pac4j.oauth.client.WordPressClient;
import org.pac4j.oauth.client.YahooClient;
import org.pac4j.oidc.client.AzureAdClient;
import org.pac4j.oidc.client.GoogleOidcClient;
import org.pac4j.oidc.client.KeycloakOidcClient;
import org.pac4j.oidc.client.OidcClient;
import org.pac4j.oidc.config.OidcConfiguration;
import org.pac4j.saml.client.SAML2Client;
import org.pac4j.saml.client.SAML2ClientConfiguration;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * This is {@link DelegatedClientFactory}.
 *
 * @author Misagh Moayyed
 * @since 5.3.0
 */
@RequiredArgsConstructor
@Slf4j
public class DelegatedClientFactory {
    /**
     * The Pac 4 j properties.
     */
    private final Pac4jDelegatedAuthenticationProperties pac4jProperties;

    /**
     * Configure github client.
     *
     * @param properties the properties
     */
    protected void configureGithubClient(final Collection<BaseClient> properties) {
        final Pac4jDelegatedAuthenticationProperties.Github github = pac4jProperties.getGithub();
        if (StringUtils.isNotBlank(github.getId()) && StringUtils.isNotBlank(github.getSecret())) {
            final GitHubClient client = new GitHubClient(github.getId(), github.getSecret());
            setClientName(client, github.getClientName());
            LOGGER.debug("Created client [{}] with identifier [{}]", client.getName(), client.getKey());
            properties.add(client);
        }
    }

    /**
     * Configure dropbox client.
     *
     * @param properties the properties
     */
    protected void configureDropboxClient(final Collection<BaseClient> properties) {
        final Pac4jDelegatedAuthenticationProperties.Dropbox db = pac4jProperties.getDropbox();
        if (StringUtils.isNotBlank(db.getId()) && StringUtils.isNotBlank(db.getSecret())) {
            final DropBoxClient client = new DropBoxClient(db.getId(), db.getSecret());
            setClientName(client, db.getClientName());
            LOGGER.debug("Created client [{}] with identifier [{}]", client.getName(), client.getKey());
            properties.add(client);
        }
    }

    /**
     * Configure orcid client.
     *
     * @param properties the properties
     */
    protected void configureOrcidClient(final Collection<BaseClient> properties) {
        final Pac4jDelegatedAuthenticationProperties.Orcid db = pac4jProperties.getOrcid();
        if (StringUtils.isNotBlank(db.getId()) && StringUtils.isNotBlank(db.getSecret())) {
            final OrcidClient client = new OrcidClient(db.getId(), db.getSecret());
            setClientName(client, db.getClientName());
            LOGGER.debug("Created client [{}] with identifier [{}]", client.getName(), client.getKey());
            properties.add(client);
        }
    }

    /**
     * Configure windows live client.
     *
     * @param properties the properties
     */
    protected void configureWindowsLiveClient(final Collection<BaseClient> properties) {
        final Pac4jDelegatedAuthenticationProperties.WindowsLive live = pac4jProperties.getWindowsLive();
        if (StringUtils.isNotBlank(live.getId()) && StringUtils.isNotBlank(live.getSecret())) {
            final WindowsLiveClient client = new WindowsLiveClient(live.getId(), live.getSecret());
            setClientName(client, live.getClientName());
            LOGGER.debug("Created client [{}] with identifier [{}]", client.getName(), client.getKey());
            properties.add(client);
        }
    }

    /**
     * Configure yahoo client.
     *
     * @param properties the properties
     */
    protected void configureYahooClient(final Collection<BaseClient> properties) {
        final Pac4jDelegatedAuthenticationProperties.Yahoo yahoo = pac4jProperties.getYahoo();
        if (StringUtils.isNotBlank(yahoo.getId()) && StringUtils.isNotBlank(yahoo.getSecret())) {
            final YahooClient client = new YahooClient(yahoo.getId(), yahoo.getSecret());
            setClientName(client, yahoo.getClientName());
            LOGGER.debug("Created client [{}] with identifier [{}]", client.getName(), client.getKey());
            properties.add(client);
        }
    }

    /**
     * Configure foursquare client.
     *
     * @param properties the properties
     */
    protected void configureFoursquareClient(final Collection<BaseClient> properties) {
        final Pac4jDelegatedAuthenticationProperties.Foursquare foursquare = pac4jProperties.getFoursquare();
        if (StringUtils.isNotBlank(foursquare.getId()) && StringUtils.isNotBlank(foursquare.getSecret())) {
            final FoursquareClient client = new FoursquareClient(foursquare.getId(), foursquare.getSecret());
            setClientName(client, foursquare.getClientName());
            LOGGER.debug("Created client [{}] with identifier [{}]", client.getName(), client.getKey());
            properties.add(client);
        }
    }

    /**
     * Configure google client.
     *
     * @param properties the properties
     */
    protected void configureGoogleClient(final Collection<BaseClient> properties) {
        final Pac4jDelegatedAuthenticationProperties.Google google = pac4jProperties.getGoogle();
        final Google2Client client = new Google2Client(google.getId(), google.getSecret());
        if (StringUtils.isNotBlank(google.getId()) && StringUtils.isNotBlank(google.getSecret())) {
            setClientName(client, google.getClientName());
            if (StringUtils.isNotBlank(google.getScope())) {
                client.setScope(Google2Client.Google2Scope.valueOf(google.getScope().toUpperCase()));
            }
            LOGGER.debug("Created client [{}] with identifier [{}]", client.getName(), client.getKey());
            properties.add(client);
        }
    }

    /**
     * Configure facebook client.
     *
     * @param properties the properties
     */
    protected void configureFacebookClient(final Collection<BaseClient> properties) {
        final Pac4jDelegatedAuthenticationProperties.Facebook fb = pac4jProperties.getFacebook();
        if (StringUtils.isNotBlank(fb.getId()) && StringUtils.isNotBlank(fb.getSecret())) {
            final FacebookClient client = new FacebookClient(fb.getId(), fb.getSecret());
            setClientName(client, fb.getClientName());
            if (StringUtils.isNotBlank(fb.getScope())) {
                client.setScope(fb.getScope());
            }

            if (StringUtils.isNotBlank(fb.getFields())) {
                client.setFields(fb.getFields());
            }
            LOGGER.debug("Created client [{}] with identifier [{}]", client.getName(), client.getKey());
            properties.add(client);
        }
    }

    /**
     * Configure linked in client.
     *
     * @param properties the properties
     */
    protected void configureLinkedInClient(final Collection<BaseClient> properties) {
        final Pac4jDelegatedAuthenticationProperties.LinkedIn ln = pac4jProperties.getLinkedIn();
        if (StringUtils.isNotBlank(ln.getId()) && StringUtils.isNotBlank(ln.getSecret())) {
            final LinkedIn2Client client = new LinkedIn2Client(ln.getId(), ln.getSecret());
            setClientName(client, ln.getClientName());
            if (StringUtils.isNotBlank(ln.getScope())) {
                client.setScope(ln.getScope());
            }

            if (StringUtils.isNotBlank(ln.getFields())) {
                client.setFields(ln.getFields());
            }
            LOGGER.debug("Created client [{}] with identifier [{}]", client.getName(), client.getKey());
            properties.add(client);
        }
    }

    /**
     * Configure twitter client.
     *
     * @param properties the properties
     */
    protected void configureTwitterClient(final Collection<BaseClient> properties) {
        final Pac4jDelegatedAuthenticationProperties.Twitter twitter = pac4jProperties.getTwitter();
        if (StringUtils.isNotBlank(twitter.getId()) && StringUtils.isNotBlank(twitter.getSecret())) {
            final TwitterClient client = new TwitterClient(twitter.getId(), twitter.getSecret());
            setClientName(client, twitter.getClientName());
            LOGGER.debug("Created client [{}] with identifier [{}]", client.getName(), client.getKey());
            properties.add(client);
        }
    }

    /**
     * Configure wordpress client.
     *
     * @param properties the properties
     */
    protected void configureWordpressClient(final Collection<BaseClient> properties) {
        final Pac4jDelegatedAuthenticationProperties.Wordpress wp = pac4jProperties.getWordpress();
        if (StringUtils.isNotBlank(wp.getId()) && StringUtils.isNotBlank(wp.getSecret())) {
            final WordPressClient client = new WordPressClient(wp.getId(), wp.getSecret());
            setClientName(client, wp.getClientName());
            LOGGER.debug("Created client [{}] with identifier [{}]", client.getName(), client.getKey());
            properties.add(client);
        }
    }

    /**
     * Configure bitbucket client.
     *
     * @param properties the properties
     */
    protected void configureBitbucketClient(final Collection<BaseClient> properties) {
        final Pac4jDelegatedAuthenticationProperties.Bitbucket bb = pac4jProperties.getBitbucket();
        if (StringUtils.isNotBlank(bb.getId()) && StringUtils.isNotBlank(bb.getSecret())) {
            final BitbucketClient client = new BitbucketClient(bb.getId(), bb.getSecret());
            setClientName(client, bb.getClientName());
            LOGGER.debug("Created client [{}] with identifier [{}]", client.getName(), client.getKey());
            properties.add(client);
        }
    }

    /**
     * Configure paypal client.
     *
     * @param properties the properties
     */
    protected void configurePaypalClient(final Collection<BaseClient> properties) {
        final Pac4jDelegatedAuthenticationProperties.Paypal paypal = pac4jProperties.getPaypal();
        if (StringUtils.isNotBlank(paypal.getId()) && StringUtils.isNotBlank(paypal.getSecret())) {
            final PayPalClient client = new PayPalClient(paypal.getId(), paypal.getSecret());
            setClientName(client, paypal.getClientName());
            LOGGER.debug("Created client [{}] with identifier [{}]", client.getName(), client.getKey());
            properties.add(client);
        }
    }

    /**
     * Sets client name.
     *
     * @param client     the client
     * @param clientName the client name
     */
    protected void setClientName(final BaseClient client, final String clientName) {
        if (StringUtils.isNotBlank(clientName)) {
            client.setName(clientName);
        }
    }

    /**
     * Configure cas client.
     *
     * @param properties the properties
     */
    protected void configureCasClient(final Collection<BaseClient> properties) {
        final AtomicInteger index = new AtomicInteger();
        pac4jProperties.getCas()
            .stream()
            .filter(cas -> StringUtils.isNotBlank(cas.getLoginUrl()))
            .forEach(cas -> {
                final CasConfiguration cfg = new CasConfiguration(cas.getLoginUrl(), CasProtocol.valueOf(cas.getProtocol()));
                final CasClient client = new CasClient(cfg);
                final int count = index.intValue();
                if (StringUtils.isNotBlank(cas.getClientName())) {
                    client.setName(cas.getClientName());
                } else if (count > 0) {
                    client.setName(client.getClass().getSimpleName() + count);
                }
                index.incrementAndGet();
                LOGGER.debug("Created client [{}]", client);
                properties.add(client);
            });
    }

    /**
     * Configure saml client.
     *
     * @param properties the properties
     */
    protected void configureSamlClient(final Collection<BaseClient> properties) {
        final AtomicInteger index = new AtomicInteger();
        pac4jProperties.getSaml()
            .stream()
            .filter(saml -> StringUtils.isNotBlank(saml.getKeystorePath())
                && StringUtils.isNotBlank(saml.getIdentityProviderMetadataPath())
                && StringUtils.isNotBlank(saml.getServiceProviderEntityId())
                && StringUtils.isNotBlank(saml.getServiceProviderMetadataPath()))
            .forEach(saml -> {
                final SAML2ClientConfiguration cfg = new SAML2ClientConfiguration(saml.getKeystorePath(),
                    saml.getKeystorePassword(),
                    saml.getPrivateKeyPassword(), saml.getIdentityProviderMetadataPath());
                cfg.setMaximumAuthenticationLifetime(saml.getMaximumAuthenticationLifetime());
                cfg.setServiceProviderEntityId(saml.getServiceProviderEntityId());
                cfg.setServiceProviderMetadataPath(saml.getServiceProviderMetadataPath());
                cfg.setDestinationBindingType(saml.getDestinationBinding());
                cfg.setForceAuth(saml.isForceAuth());
                cfg.setPassive(saml.isPassive());
                cfg.setWantsAssertionsSigned(saml.isWantsAssertionsSigned());
                cfg.setAttributeConsumingServiceIndex(saml.getAttributeConsumingServiceIndex());

                if (StringUtils.isNotBlank(saml.getAuthnContextClassRef())) {
                    cfg.setComparisonType(saml.getAuthnContextComparisonType().toUpperCase());
                    cfg.setAuthnContextClassRef(saml.getAuthnContextClassRef());
                }
                if (StringUtils.isNotBlank(saml.getKeystoreAlias())) {
                    cfg.setKeystoreAlias(saml.getKeystoreAlias());
                }
                if (StringUtils.isNotBlank(saml.getNameIdPolicyFormat())) {
                    cfg.setNameIdPolicyFormat(saml.getNameIdPolicyFormat());
                }
                final SAML2Client client = new SAML2Client(cfg);
                final int count = index.intValue();
                if (saml.getClientName() != null) {
                    client.setName(saml.getClientName());
                } else if (count > 0) {
                    client.setName(client.getClass().getSimpleName() + count);
                }

                index.incrementAndGet();
                LOGGER.debug("Created delegated client [{}]", client);
                properties.add(client);
            });
    }

    /**
     * Configure o auth 20 client.
     *
     * @param properties the properties
     */
    protected void configureOAuth20Client(final Collection<BaseClient> properties) {
        final AtomicInteger index = new AtomicInteger();
        pac4jProperties.getOauth2()
            .stream()
            .filter(oauth -> StringUtils.isNotBlank(oauth.getId()) && StringUtils.isNotBlank(oauth.getSecret()))
            .forEach(oauth -> {
                final GenericOAuth20Client client = new GenericOAuth20Client();
                client.setKey(oauth.getId());
                client.setSecret(oauth.getSecret());
                client.setProfileAttrs(oauth.getProfileAttrs());
                client.setProfileNodePath(oauth.getProfilePath());
                client.setProfileUrl(oauth.getProfileUrl());
                client.setProfileVerb(Verb.valueOf(oauth.getProfileVerb().toUpperCase()));
                client.setTokenUrl(oauth.getTokenUrl());
                client.setAuthUrl(oauth.getAuthUrl());
                client.setCustomParams(oauth.getCustomParams());
                final int count = index.intValue();
                if (StringUtils.isNotBlank(oauth.getClientName())) {
                    client.setName(oauth.getClientName());
                } else if (count > 0) {
                    client.setName(client.getClass().getSimpleName() + count);
                }
                index.incrementAndGet();
                LOGGER.debug("Created client [{}]", client);
                properties.add(client);
            });
    }

    /**
     * Configure oidc client.
     *
     * @param properties the properties
     */
    protected void configureOidcClient(final Collection<BaseClient> properties) {
        final AtomicInteger index = new AtomicInteger();
        pac4jProperties.getOidc()
            .stream()
            .filter(oidc -> StringUtils.isNotBlank(oidc.getId()) && StringUtils.isNotBlank(oidc.getSecret()))
            .forEach(oidc -> {

                final OidcConfiguration cfg = new OidcConfiguration();
                if (StringUtils.isNotBlank(oidc.getScope())) {
                    cfg.setScope(oidc.getScope());
                }
                cfg.setUseNonce(oidc.isUseNonce());
                cfg.setSecret(oidc.getSecret());
                cfg.setClientId(oidc.getId());

                if (StringUtils.isNotBlank(oidc.getPreferredJwsAlgorithm())) {
                    cfg.setPreferredJwsAlgorithm(JWSAlgorithm.parse(oidc.getPreferredJwsAlgorithm().toUpperCase()));
                }
                cfg.setMaxClockSkew(oidc.getMaxClockSkew());
                cfg.setDiscoveryURI(oidc.getDiscoveryUri());
                cfg.setCustomParams(oidc.getCustomParams());

                final OidcClient client;
                switch (oidc.getType().toUpperCase()) {
                    case "GOOGLE":
                        client = new GoogleOidcClient(cfg);
                        break;
                    case "AZURE":
                        client = new AzureAdClient(cfg);
                        break;
                    case "KEYCLOAK":
                        client = new KeycloakOidcClient(cfg);
                        break;
                    case "GENERIC":
                    default:
                        client = new OidcClient(cfg);
                        break;
                }
                final int count = index.intValue();
                if (StringUtils.isNotBlank(oidc.getClientName())) {
                    client.setName(oidc.getClientName());
                } else if (count > 0) {
                    client.setName(client.getClass().getSimpleName() + count);
                }
                index.incrementAndGet();
                LOGGER.debug("Created client [{}]", client);
                properties.add(client);
            });
    }

    /**
     * Build set of clients configured.
     *
     * @return the set
     */
    public Set<BaseClient> build() {
        final Set<BaseClient> clients = new LinkedHashSet<>();

        configureCasClient(clients);
        configureFacebookClient(clients);
        configureOidcClient(clients);
        configureOAuth20Client(clients);
        configureSamlClient(clients);
        configureTwitterClient(clients);
        configureDropboxClient(clients);
        configureFoursquareClient(clients);
        configureGithubClient(clients);
        configureGoogleClient(clients);
        configureWindowsLiveClient(clients);
        configureYahooClient(clients);
        configureLinkedInClient(clients);
        configurePaypalClient(clients);
        configureWordpressClient(clients);
        configureBitbucketClient(clients);
        configureOrcidClient(clients);

        return clients;
    }
}