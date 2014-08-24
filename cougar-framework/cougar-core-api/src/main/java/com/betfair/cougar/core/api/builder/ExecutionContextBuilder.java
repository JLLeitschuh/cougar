package com.betfair.cougar.core.api.builder;

import com.betfair.cougar.api.ExecutionContext;
import com.betfair.cougar.api.RequestUUID;
import com.betfair.cougar.api.geolocation.GeoLocationDetails;
import com.betfair.cougar.api.security.IdentityChain;

import java.util.Date;

/**
 * Builds ExecutionContext
 */
public class ExecutionContextBuilder extends BaseExecutionContextBuilder<ExecutionContextBuilder> implements Builder<ExecutionContext> {

    private IdentityChain chain;

    @Override
    protected int getNumSpecificComponents() {
        return 1;
    }

    public ExecutionContextBuilder setIdentity(IdentityChain chain) {
        this.chain = chain;
        beenSet(0);
        return this;
    }

    public ExecutionContextBuilder setIdentity(Builder<IdentityChain> chain) {
        this.chain = chain.build();
        beenSet(0);
        return this;
    }

    @Override
    public ExecutionContext build() {
        checkReady();
        return new ExecutionContextImpl();
    }

    private class ExecutionContextImpl implements ExecutionContext {


        @Override
        public IdentityChain getIdentity() {
            return chain;
        }

        @Override
        public GeoLocationDetails getLocation() {
            return location;
        }

        @Override
        public RequestUUID getRequestUUID() {
            return uuid;
        }

        @Override
        public Date getReceivedTime() {
            return receivedTime;
        }

        @Override
        public boolean traceLoggingEnabled() {
            return traceLoggingEnabled;
        }

        @Override
        public int getTransportSecurityStrengthFactor() {
            return transportSecurityStrengthFactor;
        }

        @Override
        public boolean isTransportSecure() {
            return transportSecurityStrengthFactor > 1;
        }

        @Override
        public Date getRequestTime() {
            return requestTime;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();

            sb.append("geoLocationDetails=").append(getLocation()).append("|");
            sb.append("identityChain=").append(chain).append("|");
            sb.append("requestUUID=").append(uuid).append("|");
            sb.append("receivedTime=").append(receivedTime).append("|");
            sb.append("requestTime=").append(requestTime).append("|");
            sb.append("traceLoggingEnabled=").append(traceLoggingEnabled);

            return sb.toString();
        }
    }
}
